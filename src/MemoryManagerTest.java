import static org.junit.Assert.*;

import java.io.IOException;

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
public class MemoryManagerTest extends TestCase{

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testConstructor() throws IOException {
		String s = "data.txt";
		MemoryManager main = new MemoryManager(4096, s);
	}
	
	public void testInsert1() throws IOException
	{
		RectangleDisk.dfile = "dat.txt";
		String s = RectangleDisk.dfile;
		RectangleDisk.bufSize = 512;
		MemoryManager m = new MemoryManager(512, s);
		byte[] in = {76, 70, 86};
		//m.insert(in);
		assertEquals(2, m.insert(in));
	}

}
