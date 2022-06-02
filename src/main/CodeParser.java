package main;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import memory.Memory;
import translators.AssemblyBinaryParser;

public class CodeParser {

    public static void readAndParse(String programFileName){
        try(BufferedReader reader = new BufferedReader(new FileReader(programFileName))){
            String instructionLine;
            Memory memory = Memory.getInstance();
            int memoryPointer = 0;

            while((instructionLine = reader.readLine()) != null){
                if(instructionLine.charAt(0) == '#') continue;
                int parsedInstruction = AssemblyBinaryParser.translate(instructionLine);
                memory.setContentOfMemoryAddress(memoryPointer++, parsedInstruction);
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
