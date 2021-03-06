import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import student.TestCase;

/**
 * 
 */

/**
 * @author Jazmine Zurita and Jessica McCready
 * @version March 14 2016
 *
 */
public class ListTest extends TestCase {

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        //initially had stuff
    }

//    /**
//     * Tests the get name method
//     */
//    @Test
//    public void testgetName()
//    {
//        List l = new List();
//        l.insert(new FreeBlock(12, 6));
//        l.insert(new FreeBlock(13, 9));
//        //l.getName(null, new FreeBlock("a", 12, 6));
//        assertEquals(l.length(), 1);
//    } 
//
//    /**
//     * Tests the getName when not there
//     */
//    @Test
//    public void testgetNameNotThere()
//    {
//        List l = new List();
//        l.insert(new FreeBlock(12, 6));
//        l.insert(new FreeBlock(13, 5));
//        l.getName(null, new FreeBlock("c", 14, 6));
//        assertEquals(l.length(), 2);
//    }
//
//    /**
//     * Tests when get name by name is not there
//     */
//    @Test
//    public void testgetNameByNameNotThere()
//    {
//        List l = new List();
//        l.insert(new FreeBlock("a", 12, 6));
//        l.insert(new FreeBlock("b", 13, 6));
//        l.getName("c", new FreeBlock("c", 14, 6));
//        assertEquals(l.length(), 2);
//    }
//
//    /**
//     * Tests get Name method
//     */
//    @Test
//    public void testgetNameByName2()
//    {
//        List l = new List();
//        l.insert(new FreeBlock( 12, 6));
//        l.insert(new FreeBlock("b", 13, 6)); 
//        l.getName("b", new FreeBlock(13, 6));
//        assertEquals(l.length(), 1);
//    }
//
//    /**
//     * Tests get name method
//     */
//    @Test
//    public void testgetNameByName4()
//    {
//        List l = new List();
//        l.insert(new FreeBlock("a", 12, 6));
//        l.insert(new FreeBlock("b", 13, 6)); 
//        l.getName("a", new FreeBlock("a", 12, 6));
//        assertEquals(l.length(), 1);
//    }
//
    /**
     * Tests get name by name 3
     */
    @Test
    public void testMakingMoreMem()
    {
        List l = new List();
        l.insert(new FreeBlock(12, 7));
        l.insert(new FreeBlock(12, 1)); 
        l.insert(new FreeBlock(13, 6)); 
        l.insert(new FreeBlock(14, 3));
        assertEquals(l.length(), 4);
    }

    /**
     * The test for minor list things
     */
    @Test
    public void testList()
    {
        List l = new List();
        assertTrue(l.isEmpty());
        l.insert(new FreeBlock(400, 400));
        assertFalse(l.isEmpty());
        assertEquals(1, l.length());
    }
}
