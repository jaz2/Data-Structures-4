import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
 * 
 */

/**
 * @author Jazmine Zurita and Jessica McCready
 * @version May 1 2016
 *
 */
public class SkipNodeTest {

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
    }

    /**
     * Simple test for this class
     */
    @Test
    public void testNode() {
        SkipNode s = new SkipNode(3, 10);
        SkipNode y = new SkipNode(5, 4);
        SkipNode x = new SkipNode(3, 10);
        assertEquals(3, s.element());
        assertEquals(10, s.getLevel());
        assertTrue(s.equalss(x));
        assertFalse(y.equalss(s));
    }
}
