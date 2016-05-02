import static org.junit.Assert.*;

import java.io.IOException;
import java.io.RandomAccessFile;

import org.junit.Before;
import org.junit.Test;
import student.TestCase;

/**
 * 
 */

/**
 * @author Jazmine Zurita and Jessica McCready
 * @version May 2 2016
 *
 */
public class MemoryManagerTest extends 
    TestCase implements java.io.Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    /**
     * to keep track of skip list
     */
    SkipList<String, Rect> skip;

    /**
     * to keep track of node
     */
    SkipNode node;

    /**
     * To keep track of mm
     */
    MemoryManager m;

    /**
     * To keep track of kv
     */
    KVPair kv;
    /**
     * @throws java.lang.Exception
     */
    @SuppressWarnings("unchecked")
    @Before
    public void setUp() throws Exception {
        RandomAccessFile s = new RandomAccessFile("test.txt", "rw");
        RectangleDisk.dfile = s;
        RectangleDisk.numBuffs = 3;

        RectangleDisk.bufSize = 4096;
        m = new MemoryManager(RectangleDisk.bufSize, RectangleDisk.dfile); 
        Rect re = new Rect("a", 1, 2, 3, 4);
        kv = new KVPair<String, Rect>(re.getName(), re);
    }

    /**
     * Tests when one thing is inserted into 
     * Memory Manager
     * @throws IOException
     */
    @Test
    public void testInsert1() throws IOException
    {
        RectangleDisk.bufSize = 512;
        //m = new MemoryManager(512, RectangleDisk.dfile);
        byte[] in = {76, 70, 86};
        assertEquals(2, m.insert(in));
        //assertEquals(510, m.fb);
    }

    /**
     * Tests when two things are inserted
     * @throws IOException
     */
    @Test
    public void testInsert2() throws IOException
    {
        RectangleDisk.bufSize = 512;
        //m = new MemoryManager(512, RectangleDisk.dfile);
        byte[] in = {76, 70, 86};
        m.insert(in);
        byte[] nu = {89, 68, 67, 72};
        assertEquals(7, m.insert(nu));
    }

    /**
     * Tests when at end of memory manager
     * @throws IOException 
     */
    @Test
    public void testInsertClose() throws IOException
    {
        RectangleDisk.bufSize = 5;
        //m = new MemoryManager(5, RectangleDisk.dfile);
        //System.out.println("mm length " + m.mm.length);

        byte[] in = {76, 70, 86};
        m.insert(in);
        byte[] nu = {82, 85, 77, 65};
        System.out.println(nu.length + " max size " + m.sz);
        assertEquals(7, m.insert(nu));

        //assertEquals(11, m.mm.length);
    }

    /**
     * Tests insertion
     * @throws IOException
     * @throws ClassNotFoundException
     */
    @Test
    public void testInsert() throws IOException, ClassNotFoundException
    {
        //node = new SkipNode(kv, 0);
        byte[] b = Serializer.serialize(kv);
        int kvh = m.insert(b);
        node = new SkipNode(kvh, 0); 
        b = Serializer.serialize(node);
        int nh = m.insert(b);
        assertEquals(Serializer.deserialize(m.getNode(kvh))
                .toString(), kv.toString());    
        assertTrue(((SkipNode) (Serializer.
                deserialize(m.getNode(nh)))).equalss(node));    
    }

    /**
     * Makes more memory
     * @throws IOException
     * @throws ClassNotFoundException
     */
    @Test
    public void testMakeMoreMem() throws IOException, ClassNotFoundException
    {
        //node = new SkipNode(kv, 0);
        byte[] b = Serializer.serialize(kv);
        int kvh = m.insert(b);
        node = new SkipNode(kvh, 0);
        b = Serializer.serialize(node);
        int nh = m.insert(b);
        //        int nh2 = m.insert(b); 
        //        int nh3 = m.insert(b);
        //        int nh4 = m.insert(b); 
        b = Serializer.serialize(kv);
        int kv2 = m.insert(b); //debug!!! 
        assertEquals(Serializer.deserialize(m.
                getNode(kvh)).toString(), kv.toString());    
        assertTrue(((SkipNode) (Serializer.
                deserialize(m.getNode(nh)))).equalss(node));        
        assertEquals(Serializer.deserialize(m.
                getNode(kv2)).toString(), kv.toString());    
    }

    /**
     * Tests remove
     * @throws IOException
     */
    @Test
    public void testRemove() throws IOException
    {
        byte[] b = Serializer.serialize(kv);
        int kvh = m.insert(b);
        Rect re1 = new Rect("a", 1, 2, 3, 4);
        int re1h = m.insert(Serializer.serialize(re1));
        Rect re2 = new Rect("b", 1, 2, 3, 4);
        int re2h = m.insert(Serializer.serialize(re2));
        Rect re3 = new Rect("a", 1, 2, 3, 4);
        int re3h = m.insert(Serializer.serialize(re3));
        //Rect re4 = new Rect("a", 1, 2, 3, 4);
        //Rect re5 = new Rect("a", 1, 2, 3, 4);
        m.remove(kvh);
        assertEquals(m.freeList.length(), 2);
        m.remove(re2h);
        assertEquals(m.freeList.length(), 3);
        m.remove(re1h);
        assertEquals(m.freeList.length(), 2);
        assertEquals(m.freeList.get(0).p, 0);
        assertEquals(m.freeList.get(0).sz, re3h - 2);
        int ref = m.insert(Serializer.serialize(re1));
        assertEquals(ref, 2);
    }

    //    /**
    //     * Tests remove with best fit
    //     * @throws IOException
    //     */
    //    @Test
    //    public void testRemove2() throws IOException
    //    {
    //        byte[] b = Serializer.serialize(kv);
    //        int kvh = m.insert(b);
    //        Rect re1 = new Rect("a", 1, 2, 3, 4);
    //        int re1h = m.insert(Serializer.serialize(re1));
    //        
    //        Rect re2 = new Rect("b", 1, 2, 3, 4);
    //        int re2h = m.insert(Serializer.serialize(re2));
    //        
    //        Rect re3 = new Rect("a", 1, 2, 3, 4);
    //        int re3h = m.insert(Serializer.serialize(re3));
    //        
    ////        Rect re4 = new Rect("a", 1, 2, 3, 4);
    ////        Rect re5 = new Rect("a", 1, 2, 3, 4);
    //        m.remove(kvh);
    //        assertEquals(m.freeList.length(), 2);
    //        m.remove(re2h);
    //        assertEquals(m.freeList.length(), 3);
    //        m.remove(re1h);
    //        assertEquals(m.freeList.length(), 2);
    //        assertEquals(m.freeList.get(0).p, 0);
    //        assertEquals(m.freeList.get(0).sz, re3h - 2);
    //        assertEquals(500, m.freeList.get(2).p);
    //        int ref = m.insert(Serializer.serialize(re1));
    //        assertEquals(2, ref);
    //    }

    /**
     * Tests update method
     * @throws IOException
     * @throws ClassNotFoundException
     */
    @Test
    public void testUpdate() throws IOException, ClassNotFoundException
    {
        byte[] b = Serializer.serialize(kv);
        int kvh = m.insert(b);
        node = new SkipNode(kvh, 0);
        b = Serializer.serialize(node);
        m.update(kvh, node);
        //kvh should be node
        assertEquals( (Serializer.deserialize(m.
                getNode(kvh))).getClass(), SkipNode.class);
        assertTrue(((SkipNode) (Serializer.
                deserialize(m.getNode(kvh)))).equalss(node));    
    }

    /**
     * Tests cases when null
     * @throws IOException 
     * @throws ClassNotFoundException 
     */
    @Test
    public void testNull() throws ClassNotFoundException, IOException
    {
        byte[] c = null;
        assertEquals(MemoryManager.fly, m.insert(c));

        byte[] b = Serializer.serialize(kv);
        int hand = MemoryManager.fly;
        assertNull(m.getNode(hand));

        int kvh = m.insert(b);


        node = new SkipNode(kvh, 0);
        //        m.update(hand, node);
        //         String output = systemOut().getHistory();
        //            assertFuzzyEquals("Error!", output);
    }

}
