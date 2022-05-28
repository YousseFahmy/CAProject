package translators;

import java.util.Set;

import exceptions.FilletException;
import exceptions.InvalidInstructionOpcodeException;

public class AssemblyBinaryParser {
    private static Set<String> RTypeOperations = Set.of("ADD", "SUB", "MUL", "AND", "LSL", "LSR");
    private static Set<String> ITypeOperations = Set.of("MOVI", "JEQ", "XORI", "MOVR", "MOVM");
    private static Set<String> JTypeOperations = Set.of("JMP");

    public static int translate(String assemblyInstruction){
        String[] splitInstruction = assemblyInstruction.split(" ");
        String opcodeString = splitInstruction[0];
        if(RTypeOperations.contains(opcodeString)){
            return translateRTypeInstruction(splitInstruction);
        }

        if(ITypeOperations.contains(opcodeString)){
            return translateITypeInstruction(splitInstruction);
        }

        if(JTypeOperations.contains(opcodeString)){
            int opcode = translateOpcode(splitInstruction[0]);
            int address = Integer.parseInt(splitInstruction[1]);
            return buildJTypeInstruction(opcode, address);
        }

        throw new InvalidInstructionOpcodeException();
    }

    
    private static int translateRTypeInstruction(String[] splitInstruction) {
        int opcode = translateOpcode(splitInstruction[0]);
        int r1 = translateRegister(splitInstruction[1]);
        int r2 = translateRegister(splitInstruction[2]);
        int r3 = 0;
        int shamt = 0;
        
        if(splitInstruction[0].equals("LSL") || splitInstruction[0].equals("LSR")){
            shamt = Integer.parseInt(splitInstruction[3]);
        }else{
            r3 = translateRegister(splitInstruction[3]);
        }
        
        return buildRTypeInstruction(opcode, r1, r2, r3, shamt);
    }
    
    private static int translateITypeInstruction(String[] splitInstruction) {
        int opcode = translateOpcode(splitInstruction[0]);
        int r1 = translateRegister(splitInstruction[1]);
        int r2 = 0;
        int immediate = 0;

        if(!splitInstruction[0].equals("MOVI")){
            r2 = translateRegister(splitInstruction[2]);
            immediate = Integer.parseInt(splitInstruction[3]);
        }else{
            immediate = Integer.parseInt(splitInstruction[2]);
        }

        return buildITypeInstruction(opcode, r1, r2, immediate);
    }

    private static int translateOpcode(String opcodeString){
        switch(opcodeString.toUpperCase()){
            case "ADD": return 0b0000;
            case "SUB": return 0b0001;
            case "MUL": return 0b0010;
            case "MOVI": return 0b0011;
            case "JEQ": return 0b0100;
            case "AND": return 0b0101;
            case "XORI": return 0b0110;
            case "JMP": return 0b0111;
            case "LSL": return 0b1000;
            case "LSR": return 0b1001;
            case "MOVR": return 0b1010;
            case "MOVM": return 0b1011;
        }
        throw new InvalidInstructionOpcodeException();
    }

    private static int translateRegister(String register){
        String registerNumber = register.substring(1);
        int registerInt = Integer.parseInt(registerNumber);
        if(registerInt > 31) throw new FilletException("Register number cannot exceed 31");
        return registerInt;
    }

    private static int buildRTypeInstruction(int opcode, int R1, int R2, int R3, int shamt){
        int instruction = 0;
        instruction = instruction | (opcode << 28);
        instruction = instruction | (R1 << 23);
        instruction = instruction | (R2 << 18);
        instruction = instruction | (R3 << 13);
        instruction = instruction | shamt;
        return instruction;
    }

    private static int buildITypeInstruction(int opcode, int R1, int R2, int immediate){
        int immediateMask = 0b00000000000000_111111111111111111;
        immediate = immediate & immediateMask;
        
        int instruction = 0;
        instruction = instruction | (opcode << 28);
        instruction = instruction | (R1 << 23);
        instruction = instruction | (R2 << 18);

        instruction = instruction | immediate;
        return instruction;
    }

    private static int buildJTypeInstruction(int opcode, int address){
        int instruction = 0;
        instruction = instruction | (opcode << 28);
        instruction = instruction | address;
        return instruction;
    }
    
}
