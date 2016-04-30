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

//    /**
//     * This method gets you credit for testing a good
//     * set of parameters.
//     * @throws IOException 
//     * @throws ClassNotFoundException 
//     */
//    public void testGoodParams() throws IOException, ClassNotFoundException {
//        String[] params = { "commands.txt", "dataFile.dat", "5", "4096" };
//        RectangleDisk.main(params);
//        assertFuzzyEquals("Found expected parameter list.", 
//            systemOut().getHistory());
   // }
    
    
    public void testMoreParams() throws IOException, ClassNotFoundException 
    {
    	String[] params = {"sampleInput.txt", "dataFile.dat", "5", "4096" };
    	RectangleDisk.main(params);
    	assertFuzzyEquals("Rectangle inserted: (rectA, 1, 0, 2, 4)\n"
    			+ "Rectangle inserted: (b, 2, 0, 4, 8)\n"
    			+ "Rectangle inserted: (rectC, 4, 0, 9, 6)\n"
    			+ "SkipList dump:\n"
    			+ "Node has depth: 2, Value (null)\n"
    			+ "Node has depth: 2, Value (rectA, 1, 0, 2, 4)\n"
    			+ "Node has depth: 1, Value (b, 2, 0, 4, 8)\n"
    			+ "Node has depth: 2, Value (rectC, 4, 0, 9, 6)\n"
    			+ "Freelist Blocks\n"
    			+ "(0, 75)\n"
    			+ "(1001, 23)\n"
    			+ "Rectangle removed: (b, 2, 0, 4, 8)\n"
    			+ "SkipList dump\n:"
    			+ "Node has depth: 2, Value (null)\n"
    			+ "Node has depth: 2, Value (rectA, 1, 0, 2, 4)\n"
    			+ "Node has depth: 2, Value (rectC, 4, 0, 9, 6)\n"
    			+ "Freelist Blocks:\n"
    			+ "(0, 75)\n"
    			+ "(439, 277)\n"
    			+ "(1001, 23)", 
                systemOut().getHistory());
    }
}
