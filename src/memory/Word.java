package memory;
import translators.BinaryDecimalTranslator;

public abstract class Word {
    private static final int BITS_PER_WORD = 32;
    
    String content; //Default Access

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
        return content;
    }

    public int getDecimalContent(){
        return (int) Long.parseLong(content, 2);
    }

    public void setContent(int decimalContent){
        String convertedBinary = BinaryDecimalTranslator.DecimalToBinary(decimalContent);
        if(decimalContent >= 0){
            this.content = BinaryDecimalTranslator.padUnsignedNumber(convertedBinary, BITS_PER_WORD);
        }else{
            this.content = BinaryDecimalTranslator.padSignedNumber(convertedBinary, BITS_PER_WORD);
        }
        
    }
}
