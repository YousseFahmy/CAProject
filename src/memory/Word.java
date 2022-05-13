package memory;
import translators.BinaryDecimalTranslator;

public abstract class Word {
    private static final int BITS_PER_WORD = 32;
    
    String binaryContent; //Default Access

    public Word(){
        this.setContent(0);
    }

    public Word(int decimalValue){
        this.setContent(decimalValue);
    }

    public Word(String binaryValue){
        String paddedNumber = BinaryDecimalTranslator.padNumber(binaryValue, 32);
        this.setContent(paddedNumber);
    }

    public String getBinaryContent(){
        return binaryContent;
    }

    public int getDecimalContent(){
        return BinaryDecimalTranslator.ParseBinarySigned(binaryContent);
    }

    public void setContent(int decimalContent){
        String convertedBinary = BinaryDecimalTranslator.DecimalToBinary(decimalContent);
        this.binaryContent = BinaryDecimalTranslator.padNumber(convertedBinary, BITS_PER_WORD);
    }

    public void setContent(String binaryContent){
        if(binaryContent.length() > 32) throw new IllegalArgumentException("Words are 32 bits maximum");
        this.binaryContent = BinaryDecimalTranslator.padNumber(binaryContent, BITS_PER_WORD);
    }
}
