package InputHandler;

import com.Utils.InputHandler.InputHandler;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class IsValidPortTest {

    @Test
    public void correctlyValidatesAValidPort(){
        assertTrue(InputHandler.isValidPort("65534"));
    }

    @Test
    public void correctlyIdentifiesANegativePort(){
        assertFalse(InputHandler.isValidPort("-65534"));
    }

    @Test
    public void correctlyIdentifiesAnInvalidPort(){
        assertFalse(InputHandler.isValidPort("128000"));
    }

    @Test
    public void correctlyIdentifiesABlankPort(){
        assertFalse(InputHandler.isValidPort(""));
    }

    @Test
    public void correctlyIdentifiesAString(){
        assertFalse(InputHandler.isValidPort("port"));
    }
}
