package main;

import clock.Clock;
import memory.Instruction;
import memory.Memory;
import memory.RegisterFile;
import memory.Word;
import translators.Decoder;

public class CPU {
    
    private final String codeFilePath = "";

    private static CPU instance;

    private RegisterFile registerFile;
    private Memory memory;
    private Clock clock;

    private CPU(){
        this.registerFile = RegisterFile.getInstance();
        this.memory = Memory.getInstance();
        this.clock = Clock.getInstance();

        CodeParser.readAndParse(codeFilePath);
    }

    public CPU getInstance(){
        return instance == null ? new CPU() : instance;
    }

    public void run(){
        // TODO
        // while nextInstruction != 0
        // if(clock.state == FETCH)
        // writeback execute decode fetch
        // else
        // writeback memory execute decode
        // print analysis
    }

    public Word fetch(){
        int programCounter = registerFile.getRegisterDecimalContent(0);
        Word fetchedInstruction = memory.getContentOfMemoryAddress(programCounter);
        registerFile.setRegisterContent(0, ++programCounter);
        return fetchedInstruction;
    }

    public Instruction decode(Word fetchedInstruction){
        return Decoder.translate(fetchedInstruction);
    }

    public int execute(Instruction decodedInstruction){
        return decodedInstruction.execute();
    }

    public void memoryAccess(Instruction decodedInstruction){
        // TODO
        // if decodedInstruction needs memoryAccess
        // access memory with execution result
    }

    public void writeBack(Instruction decodedInstruction){
        // TODO
        // if decodedInstruction needs writeBack
        // write in registers execution result
        // if destination is R0 -> return
    }

}
