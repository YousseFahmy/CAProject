package translators;

import java.util.Set;

import exceptions.FilletException;
import exceptions.InvalidInstructionOpcodeException;

import instructions.*;

public class Decoder {

    private static Set<Integer> RTypeOpcodes = Set.of(0, 1, 2, 4, 5, 8, 9);
    private static Set<Integer> ITypeOpcodes = Set.of(3, 4, 6, 10, 11);
    private static Set<Integer> JTypeOpcodes = Set.of(7);

    public static Instruction translate(Instruction instruction){
        String instructionOpcode = instruction.getOpcode();
        if(isRType(instructionOpcode)) return decodeRType(instruction);
        if(isIType(instructionOpcode)) return decodeIType(instruction);
        if(isJType(instructionOpcode)) return decodeJType(instruction);
        throw new FilletException();
    }

    private static boolean isRType(String opcode){
        int opcodeDecimal = BinaryDecimalTranslator.ParseBinaryUnsigned(opcode);
        return RTypeOpcodes.contains(opcodeDecimal);
    }

    private static boolean isIType(String opcode){
        int opcodeDecimal = BinaryDecimalTranslator.ParseBinaryUnsigned(opcode);
        return ITypeOpcodes.contains(opcodeDecimal);
    }

    private static boolean isJType(String opcode){
        int opcodeDecimal = BinaryDecimalTranslator.ParseBinaryUnsigned(opcode);
        return JTypeOpcodes.contains(opcodeDecimal);
    }

    private static Instruction decodeRType(Instruction instruction) {
        RTypeInstruction decodedInstruction = new SubtractInstruction(instruction);
        switch(instruction.getOpcode()){
            case "0000": decodedInstruction = new AddInstruction(instruction); break;
            case "0001": decodedInstruction =  new SubtractInstruction(instruction); break;
            case "0010": decodedInstruction =  new MultiplyInstruction(instruction); break;
            case "0101": decodedInstruction =  new AndInstruction(instruction); break;
            case "1000": decodedInstruction =  new LogicalShiftLeftInstruction(instruction); break;
            case "1001": decodedInstruction =  new LogicalShiftRightInstruction(instruction); break;
            // default: throw new InvalidInstructionOpcodeException();
        }

        String r1Binary = decodedInstruction.getBinaryContent().substring(4, 9);
        int r1Decimal = BinaryDecimalTranslator.ParseBinaryUnsigned(r1Binary);
        //TODO Read register content
        String r2Binary = decodedInstruction.getBinaryContent().substring(9, 14);
        int r2Decimal = BinaryDecimalTranslator.ParseBinaryUnsigned(r2Binary);

        String r3Binary = decodedInstruction.getBinaryContent().substring(14, 19);
        int r3Decimal = BinaryDecimalTranslator.ParseBinaryUnsigned(r3Binary);

        String shamtBinary = decodedInstruction.getBinaryContent().substring(19);
        int shamtDecimal = BinaryDecimalTranslator.ParseBinaryUnsigned(shamtBinary);
        
        return decodedInstruction;
    }
    
    private static Instruction decodeIType(Instruction instruction) {
        ITypeInstruction decodedInstruction;
        switch(instruction.getOpcode()){
            case "0011": decodedInstruction = new ExclusiveOrImmediateInstruction(instruction); break;
            case "0100": decodedInstruction =  new JumpIfEqualInstruction(instruction); break;
            case "0110": decodedInstruction =  new ExclusiveOrImmediateInstruction(instruction); break;
            case "1010": decodedInstruction =  new MoveToRegisterInstruction(instruction); break;
            case "1011": decodedInstruction =  new MoveToMemoryInstruction(instruction); break;
            default: throw new InvalidInstructionOpcodeException();
        }

        String r1Binary = decodedInstruction.getBinaryContent().substring(4, 9);
        int r1Decimal = BinaryDecimalTranslator.ParseBinaryUnsigned(r1Binary);
        
        String r2Binary = decodedInstruction.getBinaryContent().substring(9, 14);
        int r2Decimal = BinaryDecimalTranslator.ParseBinaryUnsigned(r2Binary);

        String immediate = decodedInstruction.getBinaryContent().substring(14);
        int immediateDecimal = BinaryDecimalTranslator.ParseBinaryUnsigned(immediate);
        
        return decodedInstruction;
    }

    private static Instruction decodeJType(Instruction instruction) {
        JTypeInstruction decodedInstruction;
        switch(instruction.getOpcode()){
            case "0111": decodedInstruction = new JumpInstruction(instruction); break;
            default: throw new InvalidInstructionOpcodeException();
        }

        String addressBinary = decodedInstruction.getBinaryContent().substring(4);
        int addressDecimal = BinaryDecimalTranslator.ParseBinaryUnsigned(addressBinary);
        
        return decodedInstruction;
    }

}
