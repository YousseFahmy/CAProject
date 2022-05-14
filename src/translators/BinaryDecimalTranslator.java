package translators;
public class BinaryDecimalTranslator{

    public static int ParseBinarySigned(String binaryNumber){
        return (int) Long.parseLong(binaryNumber, 2);
    }

    public static int ParseBinaryUnsigned(String binaryNumber){
        return Integer.parseInt(binaryNumber, 2);
    }

    public static String DecimalToBinary(int decimalNumber){
        return Integer.toBinaryString(decimalNumber);
    }

    public static String padUnsignedNumber(String binaryNumber, int digits){
        if(binaryNumber.length() == digits) return binaryNumber;
        if(binaryNumber.length() > digits) throw new IllegalArgumentException("Number already longer than required digits.");
        String padding = "0".repeat(digits - binaryNumber.length());
        return padding + binaryNumber;
    }

    public static String padSignedNumber(String binaryNumber, int digits){
        if(binaryNumber.length() == digits) return binaryNumber;
        if(binaryNumber.length() > digits) throw new IllegalArgumentException("Number already longer than required digits.");
        String padding = binaryNumber.charAt(0) == '0' ? 
        "0".repeat(digits - binaryNumber.length()) : 
        "1".repeat(digits - binaryNumber.length());
        return padding + binaryNumber;
    }

}