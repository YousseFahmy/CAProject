package main;

import clock.Clock;
import clock.ClockState;
import instructions.ITypeInstruction;
import instructions.JTypeInstruction;
import instructions.MoveToMemoryInstruction;
import instructions.MoveToRegisterInstruction;
import instructions.NoOpInstruction;
import instructions.RTypeInstruction;
import memory.Instruction;
import memory.Memory;
import memory.RegisterFile;
import memory.Word;
import translators.Decoder;

public class CPU {
    private static final int PC_REGISTER_NUMBER = 32;

    private static CPU instance;

    private RegisterFile registerFile;
    private Memory memory;
    private Clock clock;

    private Word fetchResult;
    private Instruction decodeResult = NoOpInstruction.get();
    private Instruction executeResult = NoOpInstruction.get();

    private CPU(){
        this.registerFile = RegisterFile.getInstance();
        this.memory = Memory.getInstance();
        this.clock = Clock.getInstance();
    }

    public static CPU getInstance(){
        if(instance == null) instance = new CPU();
        return instance;
    }

    public void run(){
        // TODO
        do{
            runTick();
        }while(fetchResult.getDecimalContent() != 0);

        for(int i = 0; i < 4; i++){
            runTick();
        }
        System.out.println("DONE!!!!!!!!!!!!!!!");
    }

    private void runTick() {
        if(clock.getCurrentStage() == ClockState.FETCH){
            writeBack(executeResult);
            executeResult = execute(decodeResult);
            decodeResult = decode(fetchResult);
            fetchResult = fetch();
        }else{
            memoryAccess(executeResult);
            executeControl(decodeResult);
        }
        clock.nextTick();
    }

    public Word fetch(){
        int programCounter = registerFile.getRegisterDecimalContent(PC_REGISTER_NUMBER);
        Word fetchedInstruction = memory.getContentOfMemoryAddress(programCounter);
        registerFile.setRegisterContent(PC_REGISTER_NUMBER, ++programCounter);
        return fetchedInstruction;
    }

    public Instruction decode(Word fetchedInstruction){
        if(fetchedInstruction == null) return NoOpInstruction.get();
        return Decoder.translate(fetchedInstruction);
    }

    public Instruction execute(Instruction decodedInstruction){
        if(decodedInstruction == null) return null;
        decodedInstruction.execute();
        return decodedInstruction;
    }

    public void executeControl(Instruction decodedInstruction){
        if(!(decodedInstruction instanceof JTypeInstruction)) return;
        // SET PC TO EXECUTION RESULT
    }

    public void memoryAccess(Instruction executedInstruction){
        // TODO
        // if decodedInstruction needs memoryAccess
        // access memory with execution result

        if(!executedInstruction.needsMemory()) return;
        if(executedInstruction instanceof MoveToRegisterInstruction){
            MoveToRegisterInstruction memInstruction = (MoveToRegisterInstruction) executedInstruction;
            int address = memInstruction.getExecutionResult();
            Word content = memory.getContentOfMemoryAddress(address);
            memInstruction.setExecutionResult(content.getDecimalContent());
        }else{
            MoveToMemoryInstruction memInstruction = (MoveToMemoryInstruction) executedInstruction;
            int address = memInstruction.getR2Contents() + memInstruction.getImmediate();
            int content = memInstruction.getExecutionResult();
            memory.setContentOfMemoryAddress(address, content);
        }
    }

    public void writeBack(Instruction executedInstruction){
        if(executedInstruction == null) return;
        if(! executedInstruction.needsWriteBack()) return;
        if(executedInstruction instanceof RTypeInstruction){
            RTypeInstruction castInstruction = (RTypeInstruction) executedInstruction;
            int destinationRegister = castInstruction.getR1();
            int newValue = castInstruction.getExecutionResult();
            if(destinationRegister == 0) return;
            registerFile.setRegisterContent(destinationRegister, newValue);
        }else{
            ITypeInstruction castInstruction = (ITypeInstruction) executedInstruction;
            int destinationRegister = castInstruction.getR1();
            int newValue = castInstruction.getExecutionResult();
            if(destinationRegister == 0) return;
            registerFile.setRegisterContent(destinationRegister, newValue);
        }
    }

}
