import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
 * 
 */

/**
 * @author Jazz
 *
 */
public class SkipNodeTest {

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testNode() {
		SkipNode s = new SkipNode(3, 10);
		assertEquals(3, s.element());
		assertEquals(10, s.getLevel());
	}

}
