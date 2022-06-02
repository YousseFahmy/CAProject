package main;

import clock.Clock;
import clock.ClockState;
import instructions.ITypeInstruction;
import instructions.JTypeInstruction;
import instructions.JumpIfEqualInstruction;
import instructions.JumpInstruction;
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

    private Word fetchResult = NoOpInstruction.get();
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
        do{
            runTick();
        }while(fetchResult.getDecimalContent() != 0);

        for(int i = 0; i < 4; i++){
            runTick();
        }
    }

    private void runTick() {
        System.out.println("------------ Cycle: " + clock.getTick());
        if(clock.getCurrentStage() == ClockState.FETCH){
            printFetchCycleAnalysis();
            writeBack(executeResult);
            executeControl(decodeResult);
            decodeResult = decode(fetchResult);
            fetchResult = fetch();
        }else{
            printMemoryCycleAnalysis();
            memoryAccess(executeResult);
            executeResult = execute(decodeResult);
        }
        clock.nextTick();
    }

    private void printFetchCycleAnalysis() {
        System.out.println("Fetching instruction at " + registerFile.getRegisterDecimalContent(PC_REGISTER_NUMBER));
        System.out.println("Decoding " + fetchResult.getBinaryContent());
        System.out.println("Executing " + decodeResult.getBinaryContent());
        decodeResult.printParameters();
        System.out.println("Writing Back " + executeResult.getBinaryContent());
        executeResult.printParameters();
    }
    
    private void printMemoryCycleAnalysis() {
        System.out.println("Decoding " + fetchResult.getBinaryContent());
        System.out.println("Executing " + decodeResult.getBinaryContent());
        decodeResult.printParameters();
        System.out.println("Accessing Memory " + executeResult.getBinaryContent());
        executeResult.printParameters();
    }

    private Word fetch(){
        int programCounter = registerFile.getRegisterDecimalContent(PC_REGISTER_NUMBER);
        Word fetchedInstruction = memory.getContentOfMemoryAddress(programCounter);
        registerFile.setRegisterContent(PC_REGISTER_NUMBER, ++programCounter);
        return fetchedInstruction;
    }

    private Instruction decode(Word fetchedInstruction){
        if(fetchedInstruction == null) return NoOpInstruction.get();
        return Decoder.translate(fetchedInstruction);
    }

    private Instruction execute(Instruction decodedInstruction){
        if(decodedInstruction == null) return null;
        decodedInstruction.execute();
        return decodedInstruction;
    }

    private void executeControl(Instruction decodedInstruction){
        if(!(decodedInstruction instanceof JTypeInstruction) && !(decodedInstruction instanceof JumpIfEqualInstruction)) return;
        if(decodedInstruction instanceof JumpInstruction){
            //jump to exec
            JumpInstruction controlInstruction = (JumpInstruction) decodedInstruction;
            registerFile.setRegisterContent(PC_REGISTER_NUMBER, controlInstruction.getExecutionResult());
            flushPipeline();
        }else{
            JumpIfEqualInstruction controlInstruction = (JumpIfEqualInstruction) decodedInstruction;
            if(controlInstruction.getExecutionResult() == 0) return;
            int currentPC = registerFile.getRegisterDecimalContent(PC_REGISTER_NUMBER);
            registerFile.setRegisterContent(PC_REGISTER_NUMBER, currentPC + controlInstruction.getExecutionResult());
            flushPipeline();
        }
    }

    private void memoryAccess(Instruction executedInstruction){
        if(!executedInstruction.needsMemory()) return;
        if(executedInstruction instanceof MoveToRegisterInstruction){
            MoveToRegisterInstruction memInstruction = (MoveToRegisterInstruction) executedInstruction;
            int address = memInstruction.getExecutionResult();
            Word content = memory.getContentOfMemoryAddress(address);
            memInstruction.setExecutionResult(content.getDecimalContent());
        }else{
            MoveToMemoryInstruction memInstruction = (MoveToMemoryInstruction) executedInstruction;
            int address = memInstruction.getR2Contents() + memInstruction.getImmediate();
            int oldContent = memory.getContentOfMemoryAddress(address).getDecimalContent();
            int newContent = memInstruction.getExecutionResult();
            System.out.println("Changing address " + address + " from " + oldContent + " to " + newContent);
            memory.setContentOfMemoryAddress(address, newContent);
        }
    }

    private void writeBack(Instruction executedInstruction){
        if(executedInstruction == null) return;
        if(! executedInstruction.needsWriteBack()) return;
        if(executedInstruction instanceof RTypeInstruction){
            RTypeInstruction castInstruction = (RTypeInstruction) executedInstruction;
            int destinationRegister = castInstruction.getR1();
            int oldValue = registerFile.getRegisterDecimalContent(destinationRegister);
            int newValue = castInstruction.getExecutionResult();
            if(destinationRegister == 0) return;
            System.out.println("Changing R" + destinationRegister + " from " + oldValue + " to " + newValue);
            registerFile.setRegisterContent(destinationRegister, newValue);
        }else{
            ITypeInstruction castInstruction = (ITypeInstruction) executedInstruction;
            int destinationRegister = castInstruction.getR1();
            int oldValue = registerFile.getRegisterDecimalContent(destinationRegister);
            int newValue = castInstruction.getExecutionResult();
            if(destinationRegister == 0) return;
            System.out.println("Changing R" + destinationRegister + " from " + oldValue + " to " + newValue);
            registerFile.setRegisterContent(destinationRegister, newValue);
        }
    }

    private void flushPipeline(){
        decodeResult = NoOpInstruction.get();
        fetchResult = NoOpInstruction.get();
    }
}
