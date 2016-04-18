import static org.junit.Assert.*;

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
	public void testConstructor() {
		MemoryManager main = new MemoryManager(4096);
		 assertFuzzyEquals(
	                "Freelist Blocks: \n(0, 4096)",
	                systemOut().getHistory());
	}

}
