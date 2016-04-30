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
 * @author Jazz
 *
 */
public class MemoryManagerTest extends TestCase implements java.io.Serializable{

	SkipList skip;
	SkipNode node;
	MemoryManager m;
	KVPair kv;
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		RandomAccessFile s = new RandomAccessFile("test2.txt", "rw");
    	RectangleDisk.dfile = s;
		m = new MemoryManager(512, RectangleDisk.dfile); 
		RectangleDisk.bufSize = 4096;
		RectangleDisk.numBuffs = 3;
	    Rect re = new Rect("a", 1, 2, 3, 4);
	    kv = new KVPair(re.getName(), re);
	}

	/**
	 * Tests 
	 * @throws IOException
	 */
	@Test
	public void testConstructor() throws IOException {
		String s = "data.txt";
		MemoryManager main = new MemoryManager(4096, RectangleDisk.dfile);
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
		MemoryManager m = new MemoryManager(512, RectangleDisk.dfile);
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
		MemoryManager m = new MemoryManager(512, RectangleDisk.dfile);
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
		MemoryManager m = new MemoryManager(5, RectangleDisk.dfile);
		//System.out.println("mm length " + m.mm.length);
		
		byte[] in = {76, 70, 86};
		m.insert(in);
		byte[] nu = {82, 85, 77, 65};
		System.out.println(nu.length + " max size " + m.sz);
		assertEquals(7, m.insert(nu));
				
		//assertEquals(11, m.mm.length);
	}
	
	@Test
	public void testInsert() throws IOException, ClassNotFoundException
	{
		//node = new SkipNode(kv, 0);
		byte[] b = Serializer.serialize(kv);
		int kvh = m.insert(b);
		node = new SkipNode(kvh, 0); 
		b = Serializer.serialize(node);
		int nh = m.insert(b);
		assertEquals(Serializer.deserialize(m.getNode(kvh)).toString(), kv.toString());	
		assertTrue(((SkipNode) (Serializer.deserialize(m.getNode(nh)))).equalss(node));		
	}
	
	@Test
	public void testMakeMoreMem() throws IOException, ClassNotFoundException
	{
		//node = new SkipNode(kv, 0);
		byte[] b = Serializer.serialize(kv);
		int kvh = m.insert(b);
		node = new SkipNode(kvh, 0);
		b = Serializer.serialize(node);
		int nh = m.insert(b);
		int nh2 = m.insert(b); 
		int nh3 = m.insert(b);
		int nh4 = m.insert(b); 
		b = Serializer.serialize(kv);
		int kv2 = m.insert(b); //debug!!! 
		assertEquals(Serializer.deserialize(m.getNode(kvh)).toString(), kv.toString());	
		assertTrue(((SkipNode) (Serializer.deserialize(m.getNode(nh)))).equalss(node));		
		assertEquals(Serializer.deserialize(m.getNode(kv2)).toString(), kv.toString());	
	}
	
	@Test
	public void testUpdate() throws IOException, ClassNotFoundException
	{
		byte[] b = Serializer.serialize(kv);
		int kvh = m.insert(b);
		node = new SkipNode(kvh, 0);
		b = Serializer.serialize(node);
		m.update(kvh, node);
		//kvh should be node
		assertEquals( (Serializer.deserialize(m.getNode(kvh))).getClass(), SkipNode.class);
		assertTrue(((SkipNode) (Serializer.deserialize(m.getNode(kvh)))).equalss(node));	
	}

}
