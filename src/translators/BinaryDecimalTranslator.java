package translators;
public class BinaryDecimalTranslator{

    public static int ParseBinarySigned(String binaryNumber){
        if(isPositive(binaryNumber)){
            return Integer.parseInt(binaryNumber, 2);
        }else{
            String positiveNumber = twosComplement(binaryNumber);
            return -1 * Integer.parseInt(positiveNumber, 2);
        }
    }

    public static int ParseBinaryUnsigned(String binaryNumber){
        return Integer.parseInt(binaryNumber, 2);
    }

    private static boolean isPositive(String binaryNumber){
        return binaryNumber.charAt(0) == '0';
    }

    private static String twosComplement(String binaryNumber){
        StringBuilder manipulated = new StringBuilder(binaryNumber);
        manipulated = manipulated.reverse();

        int firstOneIdx = manipulated.indexOf("1");
        for(int i = 0; i < manipulated.length(); i++){
            if(i <= firstOneIdx) continue;

            if(manipulated.charAt(i) == '0') {
                manipulated.setCharAt(i, '1');
                continue;
            }

            if(manipulated.charAt(i) == '1') {
                manipulated.setCharAt(i, '0');
                continue;
            }
        }

        return manipulated.reverse().toString();

    }

    public static String DecimalToBinary(int decimalNumber){
        return Integer.toBinaryString(decimalNumber);
    }

    public static String padNumber(String binaryNumber, int digits){
        if(binaryNumber.length() == digits) return binaryNumber;
        if(binaryNumber.length() > digits) throw new IllegalArgumentException("Number already longer than required digits.");
        String padding = binaryNumber.charAt(0) == '0' ? 
        "0".repeat(digits - binaryNumber.length()) : 
        "1".repeat(digits - binaryNumber.length());
        return padding + binaryNumber;
    }

}