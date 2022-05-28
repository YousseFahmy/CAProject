import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import translators.AssemblyBinaryParser;

public class AssemblyBinaryParserTests {
    
    @Nested
    class translateOpcodes {
        @Test
        void translateADD_R1_R2_R3(){
            String assemblyInstruction = "ADD R1 R2 R3";
            int binaryInstruction = 0b0000_00001_00010_00011_0000000000000;
            int translatedInstruction = AssemblyBinaryParser.translate(assemblyInstruction);
            assertEquals(binaryInstruction, translatedInstruction);
        }
        
        @Test
        void translateSUB_R4_R5_R6(){
            String assemblyInstruction = "SUB R4 R5 R6";
            int binaryInstruction = 0b0001_00100_00101_00110_0000000000000;
            int translatedInstruction = AssemblyBinaryParser.translate(assemblyInstruction);
            assertEquals(binaryInstruction, translatedInstruction);
        }

        @Test
        void translateMUL_R7_R8_R9(){
            String assemblyInstruction = "MUL R7 R8 R9";
            int binaryInstruction = 0b0010_00111_01000_01001_0000000000000;
            int translatedInstruction = AssemblyBinaryParser.translate(assemblyInstruction);
            assertEquals(binaryInstruction, translatedInstruction);
        }

        @Test
        void translateMOVI_R10_12(){
            String assemblyInstruction = "MOVI R10 12";
            int binaryInstruction = 0b0011_01010_00000_000000000000001100;
            int translatedInstruction = AssemblyBinaryParser.translate(assemblyInstruction);
            assertEquals(binaryInstruction, translatedInstruction);
        }

        @Test
        void translateJEQ_R11_R12_50(){
            String assemblyInstruction = "JEQ R11 R12 50";
            int binaryInstruction = 0b0100_01011_01100_000000000000110010;
            int translatedInstruction = AssemblyBinaryParser.translate(assemblyInstruction);
            assertEquals(binaryInstruction, translatedInstruction);
        }

        @Test
        void translateAND_R13_R14_R15(){
            String assemblyInstruction = "AND R13 R14 R15";
            int binaryInstruction = 0b0101_01101_01110_01111_0000000000000;
            int translatedInstruction = AssemblyBinaryParser.translate(assemblyInstruction);
            assertEquals(binaryInstruction, translatedInstruction);
        }

        @Test
        void translateXORI_R16_R17_36(){
            String assemblyInstruction = "XORI R16 R17 36";
            int binaryInstruction = 0b0110_10000_10001_000000000000100100;
            int translatedInstruction = AssemblyBinaryParser.translate(assemblyInstruction);
            assertEquals(binaryInstruction, translatedInstruction);
        }

        @Test
        void translateJMP_325(){
            String assemblyInstruction = "JMP 325";
            int binaryInstruction = 0b0111_0000000000000000000101000101;
            int translatedInstruction = AssemblyBinaryParser.translate(assemblyInstruction);
            assertEquals(binaryInstruction, translatedInstruction);
        }

        @Test
        void translateLSL_R18_R19_24(){
            String assemblyInstruction = "LSL R18 R19 24";
            int binaryInstruction = 0b1000_10010_10011_00000_0000000011000;
            int translatedInstruction = AssemblyBinaryParser.translate(assemblyInstruction);
            assertEquals(binaryInstruction, translatedInstruction);
        }

        @Test
        void translateLSR_R20_R21_6(){
            String assemblyInstruction = "LSR R20 R21 6";
            int binaryInstruction = 0b1001_10100_10101_00000_0000000000110;
            int translatedInstruction = AssemblyBinaryParser.translate(assemblyInstruction);
            assertEquals(binaryInstruction, translatedInstruction);
        }

        @Test
        void translateMOVR_R21_R22_16(){
            String assemblyInstruction = "MOVR R21 R22 16";
            int binaryInstruction = 0b1010_10101_10110_000000000000010000;
            int translatedInstruction = AssemblyBinaryParser.translate(assemblyInstruction);
            assertEquals(binaryInstruction, translatedInstruction);
        }

        @Test
        void translateMOVM_R23_R24_3(){
            String assemblyInstruction = "MOVM R23 R24 3";
            int binaryInstruction = 0b1011_10111_11000_000000000000000011;
            int translatedInstruction = AssemblyBinaryParser.translate(assemblyInstruction);
            assertEquals(binaryInstruction, translatedInstruction);
        }

    }

    @Test
    void negativeImmediate(){
        String assemblyInstruction = "XORI R1 R2 -5";
        int binaryInstruction = 0b0110_00001_00010_111111111111111011;
        int translatedInstruction = AssemblyBinaryParser.translate(assemblyInstruction);
        assertEquals(binaryInstruction, translatedInstruction);
    }

}
