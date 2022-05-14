import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Test;

import memory.RegisterFile;

public class RegisterFileTests {
    
    @Test
    public void insertR1Seven(){
        RegisterFile registerFile = RegisterFile.getInstance();
        registerFile.setRegisterContent(1, 7);
        int content = registerFile.getRegisterDecimalContent(1);

        assertEquals(7, content);
    }

    @Test
    public void insertR2NegativeSeven(){
        RegisterFile registerFile = RegisterFile.getInstance();
        registerFile.setRegisterContent(2, -7);
        int content = registerFile.getRegisterDecimalContent(2);

        assertEquals(-7, content);
    }

    @Test
    public void getRegisterBinaryContent(){
        RegisterFile registerFile = RegisterFile.getInstance();
        registerFile.setRegisterContent(3, 12);
        String content = registerFile.getRegisterBinaryContent(3);

        assertEquals("00000000000000000000000000001100", content);
    }

}
