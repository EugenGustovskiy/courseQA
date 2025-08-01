package grouping;

import static org.testng.Assert.assertTrue;
import org.testng.annotations.Test;

public class GroupsTest {
    @Test(groups = "first", priority = 1)
    public void one() {
        assertTrue(true);
    }

    @Test(groups = "second", priority = 1)
    public void two() {
        assertTrue(true);
    }

    @Test(groups = "first", priority = 2)
    public void three() {
        assertTrue(true);
    }

    @Test(groups = "second", priority = 2)
    public void four() {
        assertTrue(true);
    }

    @Test(groups = "first", priority = 3)
    public void five() {
        assertTrue(true);
    }

    @Test(groups = "second", priority = 3)
    public void six() {
        assertTrue(true);
    }

    @Test(groups = "first", priority = 4)
    public void seven() {
        assertTrue(true);
    }

    @Test(groups = "second", priority = 4)
    public void eight() {
        assertTrue(true);
    }
}
