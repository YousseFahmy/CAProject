package memory;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
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
        try(BufferedWriter writer = new BufferedWriter(new FileWriter("output/memoryFile.txt"))){
            for(int i = 0; i < MEMORY_SIZE; i++){
                writer.write("M["+i+"]: " + getContentOfMemoryAddress(i).getBinaryContent());
                writer.newLine();
            }
            System.out.println("Memory Contents Sucessfully Written to output/memoryFile.txt");
        }catch(IOException e){
            e.printStackTrace();
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
