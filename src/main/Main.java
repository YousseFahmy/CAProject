package main;

import memory.Memory;
import memory.RegisterFile;

public class Main {

    private static final String codeFilePath = "assemblyFiles/program5.txt";

    public static void main(String[] args) {
        CPU cpu = CPU.getInstance();
        CodeParser.readAndParse(codeFilePath);
        cpu.run();
        RegisterFile.getInstance().printContent();
        Memory.getInstance().printContent();
    }
}
