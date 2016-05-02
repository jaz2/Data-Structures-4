import static org.junit.Assert.*;

//import java.awt.Rectangle;
import student.TestCase;
import org.junit.Before;
import org.junit.Test;

/**
 * Tests the Rect class
 * @author Jazmine Zurita and Jessica McCready
 * @version Feb 03 2016
 *
 */
public class RectTest extends TestCase {
    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        //set up
    }

    /**
     * Tests that the Rect class works
     */
    @Test
    public void testRect1() {
        Rect r = new Rect("hi", 1, 2, 3, 4);
        assertEquals("hi", r.getName());
        assertEquals(1, r.getX());
        assertEquals(2, r.getY());
        assertEquals(3, r.getWidth());
        assertEquals(4, r.getHeight());
        assertEquals("1, 2, 3, 4", r.toString());
    }

    /**
     * tests the equals method
     */
    @Test
    public void testEquals()
    {
        Rect r1 = new Rect("hi", 1, 2, 3, 4);
        Rect r2 = new Rect("hello", 1, 2, 3, 4);
        assertTrue(r1.equals(r2));
        Rect r3 = new Rect("hello", 2, 1, 3, 4);
        Rect r4 = new Rect("hello", 1, 1, 3, 5);
        Rect r5 = new Rect("hello", 1, 2, 4, 5);
        Rect r6 = new Rect("hello", 1, 2, 3, 5);
        assertFalse(r1.equals(r3));
        assertFalse(r1.equals(r4));
        assertFalse(r1.equals(r5));
        assertFalse(r1.equals(r6));        
    }

    /**
     * Tests the intersects
     */
    @Test
    public void testIntersects()
    {
        Rect r1 = new Rect("hi", 1, 2, 3, 4);
        Rect r2 = new Rect("hello", 1, 2, 3, 4);
        assertTrue(r1.intersects(r2));
        Rect r3 = new Rect("hi", 4, 2, 3, 4);
        Rect r4 = new Rect("hello", 5, 2, 3, 4);
        Rect r5 = new Rect("hi", 1, 7, 3, 4);
        Rect r6 = new Rect("hello", 1, 6, 3, 4);
        assertFalse(r1.intersects(r3));
        assertFalse(r1.intersects(r4));
        assertFalse(r1.intersects(r5));
        assertFalse(r1.intersects(r6));            
    }
}
