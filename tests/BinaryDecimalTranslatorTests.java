package tests;
import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import translators.BinaryDecimalTranslator;

public class BinaryDecimalTranslatorTests {
    
    @Nested
    class BinaryToDecimal{
        @Test
        void compute27Binary(){
            String binaryNumber = "11011";
            int decimalConverion = BinaryDecimalTranslator.ParseBinaryUnsigned(binaryNumber);
            assertEquals(27, decimalConverion);
        }

        @Test
        void compute82Binary(){
            String binaryNumber = "1010010";
            int decimalConverion = BinaryDecimalTranslator.ParseBinaryUnsigned(binaryNumber);
            assertEquals(82, decimalConverion);
        }

        @Test
        void compute12Binary(){
            String binaryNumber = "1100";
            int decimalConverion = BinaryDecimalTranslator.ParseBinaryUnsigned(binaryNumber);
            assertEquals(12, decimalConverion);
        }

        @Test
        void computeNegative27Binary(){
            String number = "11111111111111111111111111100101";
            int negDecimal = BinaryDecimalTranslator.ParseBinarySigned(number);
            int correctValue = -27;
            assertEquals(correctValue, negDecimal);
        }
    } 

    @Nested
    class DecimalToBinary{
        @Test
        void compute23Decimal(){
            int decimalNumber = 23;
            String binaryConversion = BinaryDecimalTranslator.DecimalToBinary(decimalNumber);
            assertEquals("10111", binaryConversion);
        }

        @Test
        void compute74Decimal(){
            int decimalNumber = 74;
            String binaryConversion = BinaryDecimalTranslator.DecimalToBinary(decimalNumber);
            assertEquals("1001010", binaryConversion);
        }

        @Test
        void compute46Decimal(){
            int decimalNumber = 46;
            String binaryConversion = BinaryDecimalTranslator.DecimalToBinary(decimalNumber);
            assertEquals("101110", binaryConversion);
        }

        @Test
        void computeNegative27Decimal(){
            int number = -27;
            String correctValue = "11111111111111111111111111100101";
            String negBinary = BinaryDecimalTranslator.DecimalToBinary(number);
            assertEquals(correctValue, negBinary);
        }
    }

    @Nested
    class NumberPadding{
        @Test
        void negativePaddingThrowsException(){
            String binaryNumber = "10010";
            assertThrows(IllegalArgumentException.class, () -> {BinaryDecimalTranslator.padNumber(binaryNumber, -2);});
        }

        @Test
        void paddingLessThanNumberLengthThrowsException(){
            String binaryNumber = "10010";
            assertThrows(IllegalArgumentException.class, () -> {BinaryDecimalTranslator.padNumber(binaryNumber, 4);});
        }

        @Test
        void digitsEqualToNumberLengthReturnsSame(){
            String binaryNumber = "10010";
            String paddedNumber = BinaryDecimalTranslator.padNumber(binaryNumber, binaryNumber.length());
            assertEquals(binaryNumber, paddedNumber);
        }

        @Test
        void digitsGreaterThanNumberLengthReturnsCorrect(){
            String binaryNumber = "10010";
            String paddedNumber = BinaryDecimalTranslator.padNumber(binaryNumber, 6);
            assertEquals("110010", paddedNumber);
        }

        @Test
        void padZeros(){
            String binaryNumber = "010";
            String paddedNumber = BinaryDecimalTranslator.padNumber(binaryNumber, 6);
            assertEquals("000010", paddedNumber);
        }
    
        @Test
        void padOnes(){
            String binaryNumber = "101";
            String paddedNumber = BinaryDecimalTranslator.padNumber(binaryNumber, 6);
            assertEquals("111101", paddedNumber);
        }
    }

    
}
