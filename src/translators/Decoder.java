package translators;

import java.util.AbstractMap.SimpleEntry;
import java.util.Map.Entry;
import java.util.Set;

import exceptions.FilletException;
import exceptions.InvalidInstructionOpcodeException;
import instructions.AddInstruction;
import instructions.AndInstruction;
import instructions.ExclusiveOrImmediateInstruction;
import instructions.ITypeInstruction;
import instructions.JTypeInstruction;
import instructions.JumpIfEqualInstruction;
import instructions.JumpInstruction;
import instructions.LogicalShiftLeftInstruction;
import instructions.LogicalShiftRightInstruction;
import instructions.MoveImmediateInstruction;
import instructions.MoveToMemoryInstruction;
import instructions.MoveToRegisterInstruction;
import instructions.MultiplyInstruction;
import instructions.NoOpInstruction;
import instructions.RTypeInstruction;
import instructions.SubtractInstruction;
import memory.Instruction;
import memory.RegisterFile;
import memory.Word;

public abstract class Decoder {

    private static final Set<Integer> RTypeOpcodes = Set.of(0, 1, 2, 5, 8, 9);
    private static final Set<Integer> ITypeOpcodes = Set.of(3, 4, 6, 10, 11);
    private static final Set<Integer> JTypeOpcodes = Set.of(7);
    private static final int R1_BIT_MASK = 0b00001111100000000000000000000000;
    private static final int R2_BIT_MASK = 0b00000000011111000000000000000000;
    private static final int R3_BIT_MASK = 0b00000000000000111110000000000000;
    private static final int SHAMT_BIT_MASK = 0b00000000000000000001111111111111;
    private static final int IMMEDIATE_BIT_MASK = 0b00000000000000111111111111111111;
    private static final int ADDRESS_BIT_MASK = 0b00001111111111111111111111111111;

    private static RegisterFile registerFile = RegisterFile.getInstance();
    private static Entry<Integer, Integer> lastInstruction = new SimpleEntry<Integer,Integer>(0,0);
    private static Entry<Integer, Integer> beforeLastInstruction = new SimpleEntry<Integer,Integer>(0,0);


    public static Instruction translate(Word instructionWord) {
        Instruction instruction = new Instruction(instructionWord.getBinaryContent());
        int instructionOpcode = instruction.getOpcode();

        if(instruction.getDecimalContent() == NoOpInstruction.NO_OP_INSTRUCTION_BINARY) return NoOpInstruction.get();

        if (isRType(instructionOpcode))
            return decodeRType(instruction);
        if (isIType(instructionOpcode))
            return decodeIType(instruction);
        if (isJType(instructionOpcode))
            return decodeJType(instruction);
        throw new FilletException();
    }

    private static boolean isRType(int opcode) {
        return RTypeOpcodes.contains(opcode);
    }

    private static boolean isIType(int opcode) {
        return ITypeOpcodes.contains(opcode);
    }

    private static boolean isJType(int opcode) {
        return JTypeOpcodes.contains(opcode);
    }

    private static Instruction decodeRType(Instruction instruction) {
        RTypeInstruction decodedInstruction;
        switch (instruction.getOpcode()) {
            case 0:
                decodedInstruction = new AddInstruction(instruction);
                break;
            case 1:
                decodedInstruction = new SubtractInstruction(instruction);
                break;
            case 2:
                decodedInstruction = new MultiplyInstruction(instruction);
                break;
            case 5:
                decodedInstruction = new AndInstruction(instruction);
                break;
            case 8:
                decodedInstruction = new LogicalShiftLeftInstruction(instruction);
                break;
            case 9:
                decodedInstruction = new LogicalShiftRightInstruction(instruction);
                break;
            default:
                throw new InvalidInstructionOpcodeException();
        }

        int r1 = parseR1(decodedInstruction);
        int r1Contents = getRegisterContent(r1);
        decodedInstruction.setR1(r1);
        decodedInstruction.setR1Contents(r1Contents);

        int r2 = parseR2(decodedInstruction);
        int r2Contents = getRegisterContent(r2);
        decodedInstruction.setR2(r2);
        decodedInstruction.setR2Contents(r2Contents);

        int r3 = parseR3(decodedInstruction);
        int r3Contents = getRegisterContent(r3);
        decodedInstruction.setR3(r3);
        decodedInstruction.setR3Contents(r3Contents);

        int shamt = parseShamt(decodedInstruction);
        decodedInstruction.setShamt(shamt);

        shiftRegistersLeft();

        return decodedInstruction;
    }

    private static Instruction decodeIType(Instruction instruction) {
        ITypeInstruction decodedInstruction;
        switch (instruction.getOpcode()) {
            case 3:
                decodedInstruction = new MoveImmediateInstruction(instruction);
                break;
            case 4:
                decodedInstruction = new JumpIfEqualInstruction(instruction);
                break;
            case 6:
                decodedInstruction = new ExclusiveOrImmediateInstruction(instruction);
                break;
            case 10:
                decodedInstruction = new MoveToRegisterInstruction(instruction);
                break;
            case 11:
                decodedInstruction = new MoveToMemoryInstruction(instruction);
                break;
            default:
                throw new InvalidInstructionOpcodeException();
        }

        int r1 = parseR1(decodedInstruction);
        int r1Contents = getRegisterContent(r1);
        decodedInstruction.setR1(r1);
        decodedInstruction.setR1Contents(r1Contents);

        int r2 = parseR2(decodedInstruction);
        int r2Contents = getRegisterContent(r2);
        decodedInstruction.setR2(r2);
        decodedInstruction.setR2Contents(r2Contents);

        int immediate = parseImmediate(decodedInstruction);
        decodedInstruction.setImmediate(immediate);

        shiftRegistersLeft();

        return decodedInstruction;
    }

    private static Instruction decodeJType(Instruction instruction) {
        JTypeInstruction decodedInstruction;
        switch (instruction.getOpcode()) {
            case 7:
                decodedInstruction = new JumpInstruction(instruction);
                break;
            default:
                throw new InvalidInstructionOpcodeException();
        }

        int address = parseAddress(decodedInstruction);
        decodedInstruction.setAddress(address);

        return decodedInstruction;
    }

    private static int getRegisterContent(int registerNumber){
        if(lastInstruction.getKey() == registerNumber){
            return lastInstruction.getValue();
        }else if(beforeLastInstruction.getKey() == registerNumber){
            return beforeLastInstruction.getValue();
        }else{
            return registerFile.getRegisterDecimalContent(registerNumber);
        }
    } 

    public static void setLastInstruction(int registerNumber, int registerValue){
        lastInstruction = new SimpleEntry<>(registerNumber, registerValue);
    }

    private static void shiftRegistersLeft(){
        beforeLastInstruction = lastInstruction;
        beforeLastInstruction = new SimpleEntry<>(0,0);
    }

    private static int parseR1(Instruction decodedInstruction) {
        int shiftRequired = Integer.numberOfTrailingZeros(R1_BIT_MASK);
        return (decodedInstruction.getDecimalContent() & R1_BIT_MASK) >>> shiftRequired;
    }

    private static int parseR2(Instruction decodedInstruction) {
        int shiftRequired = Integer.numberOfTrailingZeros(R2_BIT_MASK);
        return (decodedInstruction.getDecimalContent() & R2_BIT_MASK) >>> shiftRequired;
    }

    private static int parseR3(RTypeInstruction decodedInstruction) {
        int shiftRequired = Integer.numberOfTrailingZeros(R3_BIT_MASK);
        return (decodedInstruction.getDecimalContent() & R3_BIT_MASK) >>> shiftRequired;
    }

    private static int parseShamt(RTypeInstruction decodedInstruction) {
        int shiftRequired = Integer.numberOfTrailingZeros(SHAMT_BIT_MASK);
        return (decodedInstruction.getDecimalContent() & SHAMT_BIT_MASK) >>> shiftRequired;
    }

    private static int parseImmediate(ITypeInstruction decodedInstruction) {
        int shiftRequired = Integer.numberOfTrailingZeros(IMMEDIATE_BIT_MASK);
        return (decodedInstruction.getDecimalContent() & IMMEDIATE_BIT_MASK) >>> shiftRequired;
    }

    private static int parseAddress(JTypeInstruction decodedInstruction) {
        int shiftRequired = Integer.numberOfTrailingZeros(ADDRESS_BIT_MASK);
        return (decodedInstruction.getDecimalContent() & ADDRESS_BIT_MASK) >>> shiftRequired;
    }

}
