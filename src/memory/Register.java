package memory;

public class Register {
    private Word value;

    public Register(){
        this.value = new Data(0);
    }

    public void setValue(int newValue){
        this.value.setContent(newValue);
    }

    public int getValue(){
        return this.value.getDecimalContent();
    }

    public String getBinaryValue(){
        return this.value.getBinaryContent();
    }

}
