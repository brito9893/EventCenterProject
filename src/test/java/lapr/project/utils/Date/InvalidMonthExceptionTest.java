package lapr.project.utils.Date;

import org.junit.Test;

import static org.junit.Assert.assertTrue;

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
