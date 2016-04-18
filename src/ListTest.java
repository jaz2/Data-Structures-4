import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
 * 
 */

/**
 * @author Jazmine Zurita and Jessica McCready
 * @version March 14 2016
 *
 */
public class ListTest {

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
    }

    /**
     * Tests the get name method
     */
    @Test
    public void testgetName()
    {
        List l = new List();
        l.insert(new Point("a", 12, 6));
        l.insert(new Point("b", 13, 6));
        l.getName(null, new Point("a", 12, 6));
        assertEquals(l.length(), 1);
    } 

    /**
     * Tests the getName when not there
     */
    @Test
    public void testgetNameNotThere()
    {
        List l = new List();
        l.insert(new Point("a", 12, 6));
        l.insert(new Point("b", 13, 6));
        l.getName(null, new Point("c", 14, 6));
        assertEquals(l.length(), 2);
    }

    /**
     * Tests when get name by name is not there
     */
    @Test
    public void testgetNameByNameNotThere()
    {
        List l = new List();
        l.insert(new Point("a", 12, 6));
        l.insert(new Point("b", 13, 6));
        l.getName("c", new Point("c", 14, 6));
        assertEquals(l.length(), 2);
    }

    /**
     * Tests get Name method
     */
    @Test
    public void testgetNameByName2()
    {
        List l = new List();
        l.insert(new Point("a", 12, 6));
        l.insert(new Point("b", 13, 6)); 
        l.getName("b", new Point("b", 13, 6));
        assertEquals(l.length(), 1);
    }

    /**
     * Tests get name method
     */
    @Test
    public void testgetNameByName4()
    {
        List l = new List();
        l.insert(new Point("a", 12, 6));
        l.insert(new Point("b", 13, 6)); 
        l.getName("a", new Point("a", 12, 6));
        assertEquals(l.length(), 1);
    }

    /**
     * Tests get name by name 3
     */
    @Test
    public void testgetNameByName3()
    {
        List l = new List();
        l.insert(new Point("a", 12, 6));
        l.insert(new Point("c", 12, 6)); 
        l.insert(new Point("b", 13, 6)); 
        l.getName("a", new Point("a", 12, 6));
        assertEquals(l.length(), 2);
    }

}
