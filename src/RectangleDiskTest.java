import java.io.IOException;

import student.TestCase;

/**
 * @author CS 3114 Staff
 * @version April 13, 2016
 */
public class RectangleDiskTest extends TestCase {

    /**
     * This method sets up the tests that follow.
     */
    public void setUp() {
        // no op
    }

    /**
     * This method gets you credit for testing a bad
     * set of parameters and for initializing the
     * Driver class.
     * @throws IOException 
     * @throws ClassNotFoundException 
     */
    public void testBadParams() throws IOException, ClassNotFoundException {
        RectangleDisk rectDisk = new RectangleDisk();
        assertNotNull(rectDisk);
        String[] params = { "bad", "params" };
        RectangleDisk.main(params);
        assertFuzzyEquals(
            "Usage: RectangleDisk <commandfile> "
            + "<diskFile> <numBuffs> <buffSize>",
            systemOut().getHistory());
    }

    /**
     * This method gets you credit for testing a good
     * set of parameters.
     * @throws IOException 
     * @throws ClassNotFoundException 
     */
    public void testGoodParams() throws IOException, ClassNotFoundException {
        String[] params = { "commands.txt", "dataFile.dat", "5", "4096" };
        RectangleDisk.main(params);
        assertFuzzyEquals("Found expected parameter list.", 
            systemOut().getHistory());
    }
}
