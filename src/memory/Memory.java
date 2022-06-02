package memory;

import java.util.ArrayList;

import exceptions.AddressOutOfBoundsException;

public class Memory {
    private static final int MEMORY_SIZE = 2048;

    private static Memory instance;

    private ArrayList<Word> memory;

    private Memory(){
        memory = new ArrayList<>(MEMORY_SIZE);
        for(int i = 0; i < MEMORY_SIZE; i++){
            memory.add(new Data());
        }
    }

    public static Memory getInstance(){
        if(instance == null) instance = new Memory();
        return instance;
    }

    public void printContent(){
        System.out.println("####### MEMORY ######");
        for(int i = 0; i < 8; i++){
            System.out.println("M["+i+"]: " + getContentOfMemoryAddress(i).getBinaryContent());
        }
    }

    public Word getContentOfMemoryAddress(int address){
        if(address > MEMORY_SIZE) throw new AddressOutOfBoundsException();
        return memory.get(address);
    }

    public void setContentOfMemoryAddress(int address, int content){
        if(address > MEMORY_SIZE) throw new AddressOutOfBoundsException();
        memory.get(address).setContent(content);
    }
}
