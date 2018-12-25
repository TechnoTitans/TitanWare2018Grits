package motor;

import org.junit.jupiter.api.Test;
import org.usfirst.frc.team1683.robot.motor.Filter;
import static org.junit.jupiter.api.Assertions.*;

public class FilterTest {

    public final double TEST_SENSITIVITY = 0.5;


    @Test
    public void testInitialSensitivity() {
        Filter filter = new Filter(TEST_SENSITIVITY);
        assertEquals(filter.getValue(), 0);
    }

    @Test
    public void testSingleUpdate() {
        // spec:
        // our implementation filter basically smooths out rapid updates of values
        // it does this by taking a percentage of the updated value (newValue)
        // and allowing the rest of the percentage to be determined by the old value
        // value = sensitivity * newValue + (1-sensitivity) * value;
        double testInput = .75;
        double targetOutput = 0.375;

        Filter filter = new Filter(TEST_SENSITIVITY); // 0.5
        filter.update(testInput);
        assertEquals(filter.getValue(), targetOutput);
    }


}
