package InputHandler;

import com.Utils.InputHandler.InputHandler;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class IsValidTimeoutTest {

    @Test
    public void correctlyValidatesAValidTimeout(){
        assertTrue(InputHandler.isValidTimeout("30"));
    }

    @Test
    public void correctlyIdentifiesANegativeTimeout(){
        assertFalse(InputHandler.isValidTimeout("-30"));
    }

    @Test
    public void correctlyIdentifiesABlankTimeout(){
        assertFalse(InputHandler.isValidTimeout(""));
    }

}
