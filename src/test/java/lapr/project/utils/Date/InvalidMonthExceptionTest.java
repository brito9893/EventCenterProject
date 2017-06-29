package lapr.project.utils.Date;

import org.junit.*;

import static org.junit.Assert.*;

/**
 * Unit testing for InvalidMonthException class
 * <p>
 * Created by vitor on 04/06/2017.
 */
public class InvalidMonthExceptionTest {
    @Test
    public void ensureInvalidMonthException() {
        boolean thrown = false;
        try {
            Date d = new Date(2017, 15, 2);
        } catch (Exception e) {
            thrown = true;
        }

        assertTrue(thrown);
    }
}
