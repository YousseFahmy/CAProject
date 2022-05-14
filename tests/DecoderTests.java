import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.Test;
import org.junit.jupiter.api.Nested;

import exceptions.FilletException;
import instructions.*;
import memory.Instruction;
import translators.Decoder;

public class DecoderTests {
    
    private String createRTypeInstructionString(String opcode, String r1, String r2, String r3, String shamt){
        return opcode + r1 + r2 + r3 + shamt;
    }

    private String createInstructionWithOpcode(String opcode){
        return opcode + "0".repeat(28);
    }

    @Test
    public void executeTopLevelInstructionThrowsFilletException(){
        String instructionString = createRTypeInstructionString("1101", "00000", "00000", "00000", "0000000000000");
        assertThrows(FilletException.class, () -> {
            Instruction instruction = new Instruction(instructionString);
            instruction.execute();
        });
    }

    @Test
    public void addR1R2R3(){
        String instructionString = createRTypeInstructionString("0000", "00001", "00010", "00011", "0000000000000");
        Instruction instruction = new Instruction(instructionString);
        Instruction decodedInstruction = Decoder.translate(instruction);
        assertTrue(decodedInstruction instanceof AddInstruction);
        
        int result = decodedInstruction.execute();

        assertEquals(result, 0);
    }

    @Nested
    class InstructionPolymorphism{

        @Test
        public void InstanceOfAddInstruction(){
            String instructionString = createInstructionWithOpcode("0000");
            Instruction instruction = new Instruction(instructionString);
            Instruction decoded = Decoder.translate(instruction);
            assertTrue(decoded instanceof AddInstruction);
        }
        
        @Test
        public void InstanceOfSubtractInstruction(){
            String instructionString = createInstructionWithOpcode("0001");
            Instruction instruction = new Instruction(instructionString);
            Instruction decoded = Decoder.translate(instruction);
            assertTrue(decoded instanceof SubtractInstruction);
        }

        @Test
        public void InstanceOfMultiplyInstruction(){
            String instructionString = createInstructionWithOpcode("0010");
            Instruction instruction = new Instruction(instructionString);
            Instruction decoded = Decoder.translate(instruction);
            assertTrue(decoded instanceof MultiplyInstruction);
        }

        @Test
        public void InstanceOfMoveImmediateInstruction(){
            String instructionString = createInstructionWithOpcode("0011");
            Instruction instruction = new Instruction(instructionString);
            Instruction decoded = Decoder.translate(instruction);
            assertTrue(decoded instanceof MoveImmediateInstruction);
        }

        @Test
        public void InstanceOfJumpIfEqualInstruction(){
            String instructionString = createInstructionWithOpcode("0100");
            Instruction instruction = new Instruction(instructionString);
            Instruction decoded = Decoder.translate(instruction);
            assertTrue(decoded instanceof JumpIfEqualInstruction);
        }

        @Test
        public void InstanceOfAndInstruction(){
            String instructionString = createInstructionWithOpcode("0101");
            Instruction instruction = new Instruction(instructionString);
            Instruction decoded = Decoder.translate(instruction);
            assertTrue(decoded instanceof AndInstruction);
        }

        @Test
        public void InstanceOfExclusiveOrImmediateInstruction(){
            String instructionString = createInstructionWithOpcode("0110");
            Instruction instruction = new Instruction(instructionString);
            Instruction decoded = Decoder.translate(instruction);
            assertTrue(decoded instanceof ExclusiveOrImmediateInstruction);
        }
        
        @Test
        public void InstanceOfJumpInstruction(){
            String instructionString = createInstructionWithOpcode("0111");
            Instruction instruction = new Instruction(instructionString);
            Instruction decoded = Decoder.translate(instruction);
            assertTrue(decoded instanceof JumpInstruction);
        }
        
        @Test
        public void InstanceOfLogicalShiftLeftInstruction(){
            String instructionString = createInstructionWithOpcode("1000");
            Instruction instruction = new Instruction(instructionString);
            Instruction decoded = Decoder.translate(instruction);
            assertTrue(decoded instanceof LogicalShiftLeftInstruction);
        }
        
        @Test
        public void InstanceOfLogicalShiftRightInstruction(){
            String instructionString = createInstructionWithOpcode("1001");
            Instruction instruction = new Instruction(instructionString);
            Instruction decoded = Decoder.translate(instruction);
            assertTrue(decoded instanceof LogicalShiftRightInstruction);
        }
        
        @Test
        public void InstanceOfMoveToRegisterInstruction(){
            String instructionString = createInstructionWithOpcode("1010");
            Instruction instruction = new Instruction(instructionString);
            Instruction decoded = Decoder.translate(instruction);
            assertTrue(decoded instanceof MoveToRegisterInstruction);
        }
        
        @Test
        public void InstanceOfMoveToMemoryInstruction(){
            String instructionString = createInstructionWithOpcode("1011");
            Instruction instruction = new Instruction(instructionString);
            Instruction decoded = Decoder.translate(instruction);
            assertTrue(decoded instanceof MoveToMemoryInstruction);
        }

    } 

}
