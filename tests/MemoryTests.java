import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import exceptions.AddressOutOfBoundsException;
import memory.Memory;
import memory.Word;

public class MemoryTests {

    @Test
    void putThreeInAddressTen() {
        Memory memory = Memory.getInstance();
        memory.setContentOfMemoryAddress(10, 3);
        Word content = memory.getContentOfMemoryAddress(10);
        int contentValue = content.getDecimalContent();

        assertEquals(contentValue, 3);
    }

    @Test
    void putNegativeThreeInAddressTwelve() {
        Memory memory = Memory.getInstance();
        memory.setContentOfMemoryAddress(12, -3);
        Word content = memory.getContentOfMemoryAddress(12);
        int contentValue = content.getDecimalContent();

        assertEquals(contentValue, -3);
    }

    @Test
    void address5000ThrowsAddressOutOfBoundsException() {
        Memory memory = Memory.getInstance();
        assertThrows(AddressOutOfBoundsException.class, () -> {
            memory.getContentOfMemoryAddress(5000);
        });
    }

}
