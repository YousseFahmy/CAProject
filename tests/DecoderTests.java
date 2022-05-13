package tests;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.Test;

import instructions.*;
import translators.Decoder;

public class DecoderTests {
    
    private String createRTypeInstructionString(String opcode, String r1, String r2, String r3, String shamt){
        return opcode + r1 + r2 + r3 + shamt;
    }

    @Test
    public void addR1R2R3(){
        String instructionString = createRTypeInstructionString("0000", "00001", "00010", "00011", "0000000000000");
        Instruction instruction = new Instruction(instructionString);
        Instruction decodedInstruction = Decoder.translate(instruction);
        assertTrue(decodedInstruction instanceof AddInstruction);
    }

}
