package InputHandler;

import com.Utils.InputHandler.InputHandler;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class IsValidLongTest {

    @Test
    public void correctlyValidatesAValidLong(){
        assertTrue(InputHandler.isValidLong("999999999999"));
    }

    @Test
    public void correctlyValidatesAValidNegativeLong(){
        assertTrue(InputHandler.isValidLong("-999999999999"));
    }

    @Test
    public void correctlyIdentifiesAnInvalidLong(){
        assertFalse(InputHandler.isValidLong("999999999999999999999999999999"));
    }

    @Test
    public void correctlyValidatesAnInvalidNegativeLong(){
        assertFalse(InputHandler.isValidLong("-999999999999999999999999"));
    }

    @Test
    public void correctlyIdentifiesStringAsInvalidLong(){
        assertFalse(InputHandler.isValidLong("A very long variable"));
    }

    @Test
    public void correctlyIdentifiesBlankAsInvalidLong(){
        assertFalse(InputHandler.isValidLong(""));
    }
}
