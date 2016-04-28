import static org.junit.Assert.*;

import java.awt.Rectangle;
import java.io.IOException;

import student.TestCase;
import student.TestableRandom;

import org.junit.Before;
import org.junit.Test;

/**
 * 
 */

/**
 * @author Jazmine Zurita and Jessica McCready
 * @version Feb 02 2016
 *
 */
public class SkipListTest extends TestCase
{
    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
    }
    
    public void testEmpty() throws IOException, ClassNotFoundException
    {
    	RectangleDisk.bufSize = 4096;
    	MemoryManager m = new MemoryManager(4096, "Data.txt");
    	Rect re = new Rect("a", 1, 2, 3, 4);
        //RectangleDisk.bufSize = 4096;
        KVPair<String, Rect> p = new KVPair<String, Rect>(re.getName(), re);
        SkipList<String, Rect> s = new SkipList<String, Rect>(m);
        s.dump();
        String output = systemOut().getHistory();
        assertFuzzyEquals("SkipList dump: \n"
                + "Node has depth 1, Value (null)\n"
                + "SkipList size is: 0\n"
                + "Freelist Blocks: \n(0, 4096)", output);
    }

    /**
     * Tests the insert in SkipList
     * @throws IOException 
     * @throws ClassNotFoundException 
     */
    @Test
    public void testInsert() throws ClassNotFoundException, IOException {
    	//String st = "data.txt";
    	RectangleDisk.bufSize = 4096;
    	MemoryManager m = new MemoryManager(4096, "data.txt");
        Rect re = new Rect("a", 1, 2, 3, 4);
        //RectangleDisk.bufSize = 4096;
        KVPair<String, Rect> p = new KVPair<String, Rect>(re.getName(), re);
        SkipList<String, Rect> s = new SkipList<String, Rect>(m);
        assertTrue(s.insert(p));
        s.dump();
        String output = systemOut().getHistory();
        assertFuzzyEquals("SkipList dump: \n"
                + "Node has depth 0, Value (null)\n"
                + "Node has depth 0, Value (a, 1, 2, 3, 4)\n"
                + "SkipList size is: 1\n"
                + "Freelist Blocks: \n(" + (m.count) + ", " + m.mm.length + ")", output);
    } 

    /**
     * Tests when insert is not null
     * @throws IOException 
     * @throws ClassNotFoundException 
     */
    @Test
    public void testInsertMore() throws IOException, ClassNotFoundException
    {
    	String st = "data.txt";
    	RectangleDisk.bufSize = 4096;
    	MemoryManager m = new MemoryManager(4096, st);
        Rect re = new Rect("a", 1, 2, 3, 4);
        KVPair<String, Rect> p = new KVPair<String, Rect>(re.getName(), re);
        SkipList<String, Rect> s = new SkipList<String, Rect>(m);
        s.insert(p);

        Rect uh = new Rect("b", 1, 2, 3, 4);
        KVPair<String, Rect> k = new KVPair<String, Rect>(uh.getName(), uh);
        s.insert(k);
        s.dump();
       assertEquals(MemoryManager.fly, s.getNode(s.head).element());
       String output = systemOut().getHistory();
       assertFuzzyEquals("SkipList dump: \n"
               + "Node has depth 0, Value (null)\n"
               + "Node has depth 0, Value (a, 1, 2, 3, 4)\n"
               + "Node has depth 0, Value (b, 1, 2, 3, 4)\n"
               + "SkipList size is: 2\n"
               + "Freelist Blocks: \n(" + m.count + ", " + m.mm.length + ")", output);
    }
//
//    /**
//     * Tests remove by coord when succesful
//     */
//    @Test
//    public void testRemoveCoordYes()
//    {
//        SkipList<String, Rect> s = new SkipList<String, Rect>();
//        Rect y = new Rect("a", 0, 1, 5, 4);
//        KVPair<String, Rect> l = new KVPair<String, Rect>(y.getName(), y);
//        s.insert(l);
//
//        Rect re = new Rect("b", 1, 2, 3, 4);
//        KVPair<String, Rect> p = new KVPair<String, 
//                Rect>(re.getName(), re);        
//        s.insert(p);
//
//        Rect f = new Rect("f", 5, 200, 3, 4);
//        KVPair<String, Rect> g = new KVPair<String, Rect>(f.getName(), f);
//        s.insert(g);
//
//        s.removeByCoord(re);
//        String output = systemOut().getHistory();
//        assertFuzzyEquals("Rectangle removed: (b, 1, 2, 3, 4)", output);
//    }
//
//    /**
//     * Tests when there is more than one instance
//     */
//    @Test
//    public void testRemoveCoordMult()
//    {
//        SkipList<String, Rect> s = new SkipList<String, Rect>();
//
//        Rect i = new Rect("q", 0, 0, 1, 1);
//        KVPair<String, Rect> w = new KVPair<String, Rect>(i.getName(), i);
//        s.insert(w);
//
//        Rect re = new Rect("a", 1, 2, 3, 4);
//        KVPair<String, Rect> p = new KVPair<String, Rect>(re.getName(), re);
//
//        s.insert(p);
//
//        Rect nu = new Rect("a", 2, 4, 3, 4);
//        KVPair<String, Rect> m = new KVPair<String, Rect>(nu.getName(), nu);
//        s.insert(m);
//
//        Rect eh = new Rect("k", 6, 10, 7, 8);
//        KVPair<String, Rect> n = new KVPair<String, Rect>(eh.getName(), eh);
//        s.insert(n);
//
//        s.removeByCoord(re);
//        String output = systemOut().getHistory();
//        assertFuzzyEquals("Rectangle removed: (a, 1, 2, 3, 4)", output);
//    }
//
//    /**
//     * Tests when two rectangles have same dimensions 
//     * but different names
//     */
//    public void testRemoveCoordDouble()
//    {
//        SkipList<String, Rect> s = new SkipList<String, Rect>();
//
//        Rect i = new Rect("a", 1, 1, 30, 30);
//        KVPair<String, Rect> w = new KVPair<String, Rect>(i.getName(), i);
//        s.insert(w);
//
//        Rect re = new Rect("b", 1, 1, 30, 30);
//        KVPair<String, Rect> p = new KVPair<String, Rect>(re.getName(), re);
//
//        s.insert(p);
//
//        Rect nu = new Rect("c", 2, 4, 3, 4);
//        KVPair<String, Rect> m = new KVPair<String, Rect>(nu.getName(), nu);
//        s.insert(m);
//
//        Rect eh = new Rect("k", 6, 10, 7, 8);
//        KVPair<String, Rect> n = new KVPair<String, Rect>(eh.getName(), eh);
//        s.insert(n);
//
//        s.removeByCoord(i);
//        String output = systemOut().getHistory();
//        assertFuzzyEquals("Rectangle removed: (a, 1, 1, 30, 30)", output);
//    }
//    
//    /**
//     * Tests when you cannot find something
//     * in remove
//     */
//    @Test
//    public void testRemoveCoordNah()
//    {
//        Rect re = new Rect("a", 1, 2, 3, 4);
//        KVPair<String, Rect> p = new KVPair<String, Rect>(re.getName(), re);
//        SkipList<String, Rect> s = new SkipList<String, Rect>();
//        s.insert(p);
//        
//        Rect m = new Rect(null, 1, 2, 4, 4);
//
//        s.removeByCoord(m);
//        String outt = systemOut().getHistory();
//        assertFuzzyEquals("Rectangle not removed: (1, 2, 4, 4,)", outt);
//    }
//    
//    /**
//     * Tests when you cannot find something but the x 
//     * is equal in remove
//     *
//     */
//    @Test
//    public void testRemoveCoordNahX()
//    {
//        Rect re = new Rect("a", 1, 5, 3, 6);
//        KVPair<String, Rect> p = new KVPair<String, Rect>(re.getName(), re);
//        SkipList<String, Rect> s = new SkipList<String, Rect>();
//        s.insert(p);
//        
//        Rect m = new Rect(null, 1, 2, 4, 4);
//
//        s.removeByCoord(m);
//        String outt = systemOut().getHistory();
//        assertFuzzyEquals("Rectangle not removed: (1, 2, 4, 4,)", outt);
//    }
//    
//    /**
//     * Tests when you cannot find something but the y 
//     * is equal in remove
//     *
//     */
//    @Test
//    public void testRemoveCoordNahY()
//    {
//        Rect re = new Rect("a", 1, 2, 3, 6);
//        KVPair<String, Rect> p = new KVPair<String, Rect>(re.getName(), re);
//        SkipList<String, Rect> s = new SkipList<String, Rect>();
//        s.insert(p);
//        
//        Rect m = new Rect(null, 1, 2, 4, 4);
//
//        s.removeByCoord(m);
//        String outt = systemOut().getHistory();
//        assertFuzzyEquals("Rectangle not removed: (1, 2, 4, 4,)", outt);
//    }
//    
//    /**
//     * Tests when you cannot find something but the width 
//     * is equal in remove
//     *
//     */
//    @Test
//    public void testRemoveCoordNahW()
//    {
//        Rect re = new Rect("a", 1, 2, 4, 6);
//        KVPair<String, Rect> p = new KVPair<String, Rect>(re.getName(), re);
//        SkipList<String, Rect> s = new SkipList<String, Rect>();
//        s.insert(p);
//        
//        Rect m = new Rect(null, 1, 2, 4, 4);
//
//        s.removeByCoord(m);
//        String outt = systemOut().getHistory();
//        assertFuzzyEquals("Rectangle not removed: (1, 2, 4, 4,)", outt);
//    }   
//
//    /**
//     * Tests when x.forward[i] is null
//     */
//    @Test
//    public void testRemoveCoordNull()
//    {
//        SkipList<String, Rect> s = new SkipList<String, Rect>();
//        Rect hm = new Rect(null, 1, 2, 3, 4);
//        s.removeByCoord(hm);
//        String output = systemOut().getHistory();
//        assertFuzzyEquals("Rectangle not removed: (1, 2, 3, 4)", output);
//    }
//
//    /**
//     * Tests remove by name when successful
//     */
//    @Test
//    public void testRemoveNameYasM()
//    {
//        SkipList<String, Rect> s = new SkipList<String, Rect>();
//        Rect y = new Rect("a", 0, 1, 5, 4);
//        KVPair<String, Rect> l = new KVPair<String, Rect>(y.getName(), y);
//        s.insert(l);
//
//        Rect re = new Rect("b", 1, 2, 3, 4);
//        KVPair<String, Rect> p = new KVPair<String, 
//                Rect>(re.getName(), re);        
//        s.insert(p);
//
//        Rect f = new Rect("f", 5, 200, 3, 4);
//        KVPair<String, Rect> g = new KVPair<String, Rect>(f.getName(), f);
//        s.insert(g);
//
//        s.removeByName("a");
//        String output = systemOut().getHistory();
//        assertFuzzyEquals("Rectangle removed: (a, 0, 1, 5, 4)", output);
//    }
//    
//    /**
//     * Tests remove by name when successful on first
//     * element
//     */
//    @Test
//    public void testRemoveNameYasF()
//    {
//        SkipList<String, Rect> s = new SkipList<String, Rect>();
//        Rect y = new Rect("a", 0, 1, 5, 4);
//        KVPair<String, Rect> l = new KVPair<String, Rect>(y.getName(), y);
//        s.insert(l);
//
//        Rect re = new Rect("b", 1, 2, 3, 4);
//        KVPair<String, Rect> p = new KVPair<String, 
//                Rect>(re.getName(), re);        
//        s.insert(p);
//
//        Rect f = new Rect("f", 5, 200, 3, 4);
//        KVPair<String, Rect> g = new KVPair<String, Rect>(f.getName(), f);
//        s.insert(g);
//
//        s.removeByName("b");
//        String output = systemOut().getHistory();
//        assertFuzzyEquals("Rectangle removed: (b, 1, 2, 3, 4)", output);
//    }
//    
//    /**
//     * Tests remove by name when successful on last 
//     * element
//     */
//    @Test
//    public void testRemoveNameYasL()
//    {
//        SkipList<String, Rect> s = new SkipList<String, Rect>();
//        Rect y = new Rect("a", 0, 1, 5, 4);
//        KVPair<String, Rect> l = new KVPair<String, Rect>(y.getName(), y);
//        s.insert(l);
//
//        Rect re = new Rect("b", 1, 2, 3, 4);
//        KVPair<String, Rect> p = new KVPair<String, 
//                Rect>(re.getName(), re);        
//        s.insert(p);
//
//        Rect f = new Rect("f", 5, 200, 3, 4);
//        KVPair<String, Rect> g = new KVPair<String, Rect>(f.getName(), f);
//        s.insert(g);
//
//        s.removeByName("f");
//        String output = systemOut().getHistory();
//        assertFuzzyEquals("Rectangle removed: (f, 5, 200, 3, 4)", output);
//    }
//
//    /**
//     * Tests when there is more than one instance
//     */
//    @Test
//    public void testRemoveNameMult()
//    {
//        SkipList<String, Rect> s = new SkipList<String, Rect>();
//
//        Rect i = new Rect("q", 0, 0, 1, 1);
//        KVPair<String, Rect> w = new KVPair<String, Rect>(i.getName(), i);
//        s.insert(w);
//
//        Rect re = new Rect("a", 1, 2, 3, 4);
//        KVPair<String, Rect> p = new KVPair<String, Rect>(re.getName(), re);
//
//        s.insert(p);
//
//        Rect nu = new Rect("a", 2, 4, 3, 4);
//        KVPair<String, Rect> m = new KVPair<String, Rect>(nu.getName(), nu);
//        s.insert(m);
//
//        Rect eh = new Rect("k", 6, 10, 7, 8);
//        KVPair<String, Rect> n = new KVPair<String, Rect>(eh.getName(), eh);
//        s.insert(n);
//
//        s.removeByName("a");
//        String output = systemOut().getHistory();
//        assertFuzzyEquals("Rectangle removed: (a, 2, 4, 3, 4)", output);
//    }
//
//    /**
//     * Tests when you cannot find something
//     * in remove
//     */
//    @Test
//    public void testRemoveNameNah()
//    {
//        Rect re = new Rect("a", 1, 2, 3, 4);
//        KVPair<String, Rect> p = new KVPair<String, Rect>(re.getName(), re);
//        SkipList<String, Rect> s = new SkipList<String, Rect>();
//        s.insert(p);
//
//        s.removeByName("b");
//        String outt = systemOut().getHistory();
//        assertFuzzyEquals("Rectangle not found: b", outt);
//    }
//    
//    /**
//     * Tests when you cannot find something
//     * in remove checking compareTo
//     */
//    @Test
//    public void testRemoveNameNah2()
//    {
//        Rect re = new Rect("b", 1, 2, 3, 4);
//        KVPair<String, Rect> p = new KVPair<String, Rect>(re.getName(), re);
//        SkipList<String, Rect> s = new SkipList<String, Rect>();
//        s.insert(p);
//        s.removeByName("a");
//        String outt = systemOut().getHistory();
//        assertFuzzyEquals("Rectangle not found: a", outt);
//    }
//
//    /**
//     * Tests when x.forward[i] is null
//     */
//    @Test
//    public void testRemoveNameNull()
//    {
//        SkipList<String, Rect> s = new SkipList<String, Rect>();
//        s.removeByName("roar");
//        String output = systemOut().getHistory();
//        assertFuzzyEquals("Rectangle not found: roar", output);
//    }
//    
//    /**
//     * tests when the element should be in middle but not
//     */
//    public void testSearch270()
//    {
//        Rect re = new Rect("a", 1, 2, 3, 4);
//        KVPair<String, Rect> p = new KVPair<String, Rect>(re.getName(), re);
//        SkipList<String, Rect> s = new SkipList<String, Rect>();
//        s.insert(p);
//
//        Rect nu = new Rect("c", 2, 2, 4, 4);
//        KVPair<String, Rect> n = new KVPair<String, Rect>(nu.getName(), nu);
//        s.insert(n);
//        
//        Rect or = new Rect("d", 2, 8, 4, 49);
//        KVPair<String, Rect> me = new KVPair<String, Rect>(or.getName(), or);
//        s.insert(me);
//        s.search("b");
//        String output = systemOut().getHistory();
//        assertFuzzyEquals("Rectangle not found: b", output);
//    }
//
//    /**
//     * Tests when multiple searches then other stuff
//     */
//    public void testSearchSame4()
//    {
//        Rect re = new Rect("a", 1, 2, 3, 4);
//        KVPair<String, Rect> p = new KVPair<String, Rect>(re.getName(), re);
//        SkipList<String, Rect> s = new SkipList<String, Rect>();
//        s.insert(p);
//
//        Rect nu = new Rect("a", 2, 2, 4, 4);
//        KVPair<String, Rect> n = new KVPair<String, Rect>(nu.getName(), nu);
//        s.insert(n);
//        
//        Rect or = new Rect("a", 2, 8, 4, 49);
//        KVPair<String, Rect> me = new KVPair<String, Rect>(or.getName(), or);
//        s.insert(me);
//        
//        Rect or1 = new Rect("b", 2, 8, 4, 49);
//        KVPair<String, Rect> me1 = new KVPair<String, Rect>(or1.getName(), or1);
//        s.insert(me1);
//        
//        s.search("a");
//        String output = systemOut().getHistory();
//        assertFuzzyEquals("(a, 2, 8, 4, 49)\n"
//                + "(a, 2, 2, 4, 4)\n"
//                + "(a, 1, 2, 3, 4)", output);
//    }
//    
//    /**
//     * Tests search when not found
//     */
//    @Test
//    public void testSearchNot()
//    {
//        Rect re = new Rect("a", 1, 2, 3, 4);
//        KVPair<String, Rect> p = new KVPair<String, Rect>(re.getName(), re);
//        SkipList<String, Rect> s = new SkipList<String, Rect>();
//        s.insert(p);
//        s.search("b");
//        String output = systemOut().getHistory();
//        assertFuzzyEquals("Rectangle not found: b", output);
//    }
//
//    /**
//     * Tests when x.forward[i] is null
//     */
//    @Test
//    public void testSearchNull()
//    {
//        SkipList<String, Rect> s = new SkipList<String, Rect>();
//        s.search("roar");
//        String output = systemOut().getHistory();
//        assertFuzzyEquals("Rectangle not found: roar", output);
//    }
//
//    /**
//     * Tests when search is successful
//     * and when you add another not found
//     */
//    @Test
//    public void testSearchYes()
//    {
//        Rect re = new Rect("a", 1, 2, 3, 4);
//        KVPair<String, Rect> p = new KVPair<String, Rect>(re.getName(), re);
//        SkipList<String, Rect> s = new SkipList<String, Rect>();
//        s.insert(p);
//        s.search("a");
//        String output = systemOut().getHistory();
//        assertFuzzyEquals("(a, 1, 2, 3, 4)", output);
//    }
//
//    /**
//     * Tests when search is successful
//     * and when you add another not found
//     */
//    @Test
//    public void testSearchNah()
//    {
//        Rect re = new Rect("a", 1, 2, 3, 4);
//        KVPair<String, Rect> p = new KVPair<String, Rect>(re.getName(), re);
//        SkipList<String, Rect> s = new SkipList<String, Rect>();
//        s.insert(p);
//
//        s.search("b");
//        String outt = systemOut().getHistory();
//        assertFuzzyEquals("Rectangle not found: b", outt);
//    }
//
//
//    /**
//     * Tests when search is the same
//     */
//    @Test
//    public void testSearchSame()
//    {
//        Rect re = new Rect("a", 1, 2, 3, 4);
//        KVPair<String, Rect> p = new KVPair<String, Rect>(re.getName(), re);
//        SkipList<String, Rect> s = new SkipList<String, Rect>();
//        s.insert(p);
//
//        Rect nu = new Rect("a", 2, 2, 4, 4);
//        KVPair<String, Rect> n = new KVPair<String, Rect>(nu.getName(), nu);
//        s.insert(n);
//        s.search("a");
//        String output = systemOut().getHistory();
//        assertFuzzyEquals("(a, 2, 2, 4, 4)\n"
//                + "(a, 1, 2, 3, 4)", output);
//    }
//
//    /**
//     * Tests when search is the and continues to search
//     */
//    @Test
//    public void testSearchSame3()
//    {
//        Rect re = new Rect("a", 1, 2, 3, 4);
//        KVPair<String, Rect> p = new KVPair<String, Rect>(re.getName(), re);
//        SkipList<String, Rect> s = new SkipList<String, Rect>();
//        s.insert(p);
//
//        Rect nu = new Rect("a", 2, 2, 4, 4);
//        KVPair<String, Rect> n = new KVPair<String, Rect>(nu.getName(), nu);
//        s.insert(n);
//
//        Rect or = new Rect("a", 2, 8, 4, 49);
//        KVPair<String, Rect> me = new KVPair<String, Rect>(or.getName(), or);
//        s.insert(me);
//
//        s.search("a");
//        String output = systemOut().getHistory();
//        assertFuzzyEquals("(a, 2, 8, 4, 49)\n"
//                + "(a, 2, 2, 4, 4)\n"
//                + "(a, 1, 2, 3, 4)", output);
//    }

    /**
     * Tests when there are no inserts
     * @throws IOException 
     * @throws ClassNotFoundException 
     */
    @Test
    public void testDumpNoInserts() throws ClassNotFoundException, IOException
    {
    	RectangleDisk.bufSize = 4096;
    	String st = "data.txt";
    	MemoryManager m = new MemoryManager(4096, st);
        TestableRandom.setNextInts(2, 2, 2);
        SkipList<String, Rect> s = new SkipList<String, Rect>(m);
        s.dump();
        String output = systemOut().getHistory();
        assertFuzzyEquals("SkipList dump: \n"
                + "Node has depth 1, Value (null)\n"
                + "SkipList size is: 0\n"
                + "Freelist Blocks: \n(0, 4096)", output);
    }

    /**
     * Test when there is one insert for dump
     * @throws IOException 
     * @throws ClassNotFoundException 
     */
    @Test
    public void testDumpWith1Insert() 
    		throws IOException, ClassNotFoundException
    {
    	RectangleDisk.bufSize = 512;
    	MemoryManager m = new MemoryManager(512,"a.txt");
        TestableRandom.setNextInts(3, 3, 3);
        Rect re = new Rect("a", 1, 2, 3, 4);
        KVPair<String, Rect> p = new KVPair<String, Rect>(re.getName(), re);
        SkipList<String, Rect> s = new SkipList<String, Rect>(m);
        //SkipList<String, Rect> s1 = new SkipList<String, Rect>(m);
        s.insert(p);
        //s1.controlledInsert(p, 1);
        s.dump();
        String output = systemOut().getHistory();
        assertFuzzyEquals("SkipList dump: \n"
                + "Node has depth 0, Value (null)\n"
                + "Node has depth 0, Value (a, 1, 2, 3, 4)\n"
                + "SkipList size is: 1\n"
                + "Freelist Blocks: \n(" + m.count + ", " + m.mm.length + ")", output);
    }

    /**
     * Tests when there are two inserts for dump
     * @throws IOException 
     * @throws ClassNotFoundException 
     */
    @Test
    public void testDumpWith2Inserts() throws ClassNotFoundException, IOException
    {
    	TestableRandom.setNextInts(2, 2, 2);
    	RectangleDisk.bufSize = 512;
    	MemoryManager m = new MemoryManager(512,"ta.txt");        
        Rect re = new Rect("a", 1, 2, 3, 4);
        KVPair<String, Rect> p = new KVPair<String, Rect>(re.getName(), re);
        SkipList<String, Rect> s = new SkipList<String, Rect>(m);
        //s.controlledInsert(p, 2);
        s.insert(p);
        
        Rect re1 = new Rect("hey", 1, 2, 12, 4);
        KVPair<String, Rect> p1 = new KVPair<String, Rect>(re1.getName(), re1);
        s.insert(p1);
        
        s.dump();
        String output = systemOut().getHistory();
        assertFuzzyEquals("SkipList dump: \n"
                + "Node has depth 0, Value (null)\n"
                + "Node has depth 0, Value (a, 1, 2, 3, 4)\n"
                + "Node has depth 1, Value (hey, 1, 2, 12, 4)\n"
                + "SkipList size is: 2\n"
                + "Freelist Blocks: \n(" + m.count + ", " + m.mm.length + ")\n"
                		+ "(" + m.count + ", " + ")", output);
    }    

//    /**
//     * Tests the controlled insert in SkipList
//     */
//    @Test
//    public void testInsertBool() {
//        Rect re = new Rect("a", 1, 2, 3, 4);
//        KVPair<String, Rect> p = new KVPair<String, Rect>(re.getName(), re);
//        SkipList<String, Rect> s = new SkipList<String, Rect>();
//        assertTrue(s.controlledInsert(p, 2));
//        assertEquals(null, s.head.element());
//    }
//
//    /**
//     * Tests when the controlled insert is not null
//     */
//    @Test
//    public void testInsertBoolMore()
//    {
//        Rect re = new Rect("a", 1, 2, 3, 4);
//        KVPair<String, Rect> p = new KVPair<String, Rect>(re.getName(), re);
//        SkipList<String, Rect> s = new SkipList<String, Rect>();
//        s.controlledInsert(p, 2);
//
//        Rect uh = new Rect("b", 1, 2, 3, 4);
//        KVPair<String, Rect> k = new KVPair<String, Rect>(uh.getName(), uh);
//        s.controlledInsert(k, 2);
//        assertEquals(null, s.head.element());
//    }
//
    /**
     * Tests all region search possibilites
     * @throws IOException 
     * @throws ClassNotFoundException 
     */
    @Test
    public void testRegionSearch() throws IOException, ClassNotFoundException
    {
    	RectangleDisk.bufSize = 512;
    	MemoryManager m = new MemoryManager(512,"ta.txt"); 
        SkipList<String, Rect> skip = new SkipList<String, Rect>(m);
        Rect r1 = new Rect("a", 1, 2, 3, 4);
        KVPair<String, Rect> p1 = new KVPair<String, Rect>(r1.getName(), r1);
        Rect r2 = new Rect("b", 1, 5, 3, 4);
        KVPair<String, Rect> p2 = new KVPair<String, Rect>(r2.getName(), r2);
        Rect r3 = new Rect("c", 4, 4, 2, 4);
        KVPair<String, Rect> p3 = new KVPair<String, Rect>(r3.getName(), r3);
        Rect r4 = new Rect("d", 6, 6, 2, 2);
        KVPair<String, Rect> p4 = new KVPair<String, Rect>(r4.getName(), r4);
        Rect r5 = new Rect("a", 11, 5, 7, 2);
        KVPair<String, Rect> p5 = new KVPair<String, Rect>(r5.getName(), r5);
        skip.insert(p1);
        skip.insert(p5);
        skip.insert(p3);
        skip.insert(p4);
        skip.insert(p2);
        skip.regionsearch(1, 2, 3, 4);
        String output = systemOut().getHistory();
        assertFuzzyEquals("Rectangles intersecting region (1, 2, 3, 4):\n"
                + "(a, 1, 2, 3, 4)\n" + "(b, 1, 5, 3, 4)\n", output);
        skip.regionsearch(6, 6, 2, 2);
    }

    /**
     * Tests intersections
     * @throws IOException 
     * @throws ClassNotFoundException 
     */
    @Test
    public void testIntersections() throws ClassNotFoundException, IOException
    {
    	RectangleDisk.bufSize = 512;
    	RectangleDisk.dfile = "test.txt";
    	MemoryManager m = new MemoryManager(512,"ta.txt"); 
        SkipList<String, Rect> skip = new SkipList<String, Rect>(m);
        Rect r1 = new Rect("a", 1, 2, 3, 4);
        KVPair<String, Rect> p1 = new KVPair<String, Rect>(r1.getName(), r1);
        Rect r2 = new Rect("b", 1, 5, 3, 4);
        KVPair<String, Rect> p2 = new KVPair<String, Rect>(r2.getName(), r2);
        Rect r3 = new Rect("c", 4, 4, 2, 4);
        KVPair<String, Rect> p3 = new KVPair<String, Rect>(r3.getName(), r3);
        Rect r4 = new Rect("d", 6, 6, 2, 2);
        KVPair<String, Rect> p4 = new KVPair<String, Rect>(r4.getName(), r4);
        Rect r5 = new Rect("a", 11, 5, 7, 2);
        KVPair<String, Rect> p5 = new KVPair<String, Rect>(r5.getName(), r5);
        skip.insert(p1);
        skip.insert(p5);
        skip.insert(p3);
        skip.insert(p4);
        skip.insert(p2);
        skip.intersections();
        String output = systemOut().getHistory();
        assertFuzzyEquals("(a, 1, 2, 3, 4 | b, 1, 5, 3, 4)\n"
                + "(b, 1, 5, 3, 4 | a, 1, 2, 3, 4)\n", output);
    }
    
//    /**
//     * Tests the controlled insert method
//     */
//    public void testControlledInsert() {
//        Rect re = new Rect("b", 1, 2, 3, 4);
//        KVPair<String, Rect> p = new KVPair<String, Rect>(re.getName(), re);
//        Rect re1 = new Rect("c", 2, 2, 3, 4);
//        KVPair<String, Rect> p1 = new KVPair<String, Rect>(re1.getName(), re1);
//        Rect re2 = new Rect("a", 2, 7, 3, 4);
//        KVPair<String, Rect> p2 = new KVPair<String, Rect>(re2.getName(), re2);
//        SkipList<String, Rect> s = new SkipList<String, Rect>();
//        s.controlledInsert(p, 4);
//        s.controlledInsert(p1, 12);
//        assertTrue(s.controlledInsert(p2, 6));
//    }
}
