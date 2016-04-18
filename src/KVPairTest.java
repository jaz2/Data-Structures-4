//import static org.junit.Assert.*;
import student.TestCase;
import org.junit.Before;
import org.junit.Test;

/**
 * @author Jazmine Zurita and Jessica McCready    
 * @version Feb 3 2016
 *
 */
public class KVPairTest extends TestCase {

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
    }

    /**
     * Tests the KVPair
     */
    @Test
    public void testKV() {
        Rect rec = new Rect("a", 1, 2, 3, 4);
        KVPair<String, Rect> p = new KVPair<String, Rect>(rec.getName(), rec);
        
        assertEquals("a", p.key());
        assertEquals(rec, p.value());
        assertEquals("a, 1, 2, 3, 4", p.toString());
    }
    
    
    /**
     * Tests when two pairs are the same
     */
    @Test
    public void testCompareToPairs()
    {
        Rect rec = new Rect("a", 1, 2, 3, 4);
        KVPair<String, Rect> p = new KVPair<String, Rect>(rec.getName(), rec);
        
        Rect other = new Rect("a", 1, 2, 3, 4);
        KVPair<String, Rect> b = new KVPair<
                String, Rect>(other.getName(), other);
        
        assertEquals(0, p.compareTo(b));
    }
    
    /**
     * Tests when two pairs are not the same
     */
    @Test
    public void testCompareToNotPairs()
    {
        Rect rec = new Rect("a", 1, 2, 3, 4);
        KVPair<String, Rect> p = new KVPair<String, Rect>(rec.getName(), rec);
        
        Rect nu = new Rect("k", 1, 2, 3, 5);
        KVPair<String, Rect> n = new KVPair<String, Rect>(nu.getName(), rec);
        
        assertEquals(-10, p.compareTo(n));
    }
    
    
    /**
     * Tests when two names match
     */
    @Test
    public void testCompareToName()
    {
        Rect rec = new Rect("a", 1, 2, 3, 4);
        KVPair<String, Rect> p = new KVPair<String, Rect>(rec.getName(), rec);
        
        Rect idk = new Rect("a", 1, 1, 2, 4);
        
        assertEquals(0, p.compareTo(idk.getName()));
    }

    /**
     * Tests when two names do not match
     */
    @Test
    public void testCompareToNoName()
    {
        Rect rec = new Rect("a", 1, 2, 3, 4);
        KVPair<String, Rect> p = new KVPair<String, Rect>(rec.getName(), rec);
        
        Rect no = new Rect("b", 1, 2, 3, 4);
        
        assertEquals(-1, p.compareTo(no.getName()));
    }
}
