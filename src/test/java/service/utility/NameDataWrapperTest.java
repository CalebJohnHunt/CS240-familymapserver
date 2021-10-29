package service.utility;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class NameDataWrapperTest {
    @Test
    public void fRandomPass() {
        String fname = NameDataWrapper.fRandom();
        assertNotNull(fname);
    }

    @Test
    public void mRandomPass() {
        String mname = NameDataWrapper.mRandom();
        assertNotNull(mname);
    }

    @Test
    public void sRandomPass() {
        String sname = NameDataWrapper.sRandom();
        assertNotNull(sname);
    }
}
