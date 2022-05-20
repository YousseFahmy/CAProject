package memory;
import translators.BinaryDecimalTranslator;

public abstract class Word {
    private static final int BITS_PER_WORD = 32;
    
    int content; //Default Access

    public Word(){
        this.setContent(0);
    }

    public Word(int decimalValue){
        this.setContent(decimalValue);
    }

    public Word(String binaryValue){
        this(BinaryDecimalTranslator.ParseBinarySigned(binaryValue));
    }

    public String getBinaryContent(){
        String binaryValue = BinaryDecimalTranslator.DecimalToBinary(content);
        return BinaryDecimalTranslator.padNumber(binaryValue, BITS_PER_WORD);
    }

    public int getDecimalContent(){
        return content;
    }

    public void setContent(int decimalContent){
        this.content = decimalContent;
    }
}
