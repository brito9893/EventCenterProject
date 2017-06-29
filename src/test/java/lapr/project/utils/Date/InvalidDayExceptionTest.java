package lapr.project.utils.Date;

import org.junit.*;

import static org.junit.Assert.*;

/**
 * Unit testing for InvalidDayException class
 * <p>
 * Created by vitor on 04/06/2017.
 */
public class InvalidDayExceptionTest {
    @Test
    public void ensureInvalidDayExceptionDeploys() {
        boolean thrown = false;
        try {
            Date d = new Date(2017, 6, 32);
        } catch (Exception e) {
            thrown = true;
        }

        assertTrue(thrown);
    }
}
