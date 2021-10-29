package service.utility;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class LocationDataWrapperTest {
    @Test
    public void randomPass() {
        Location location = LocationDataWrapper.random();
        assertNotNull(location);
    }
}
