import static org.junit.Assert.*;

import java.io.IOException;
import java.io.RandomAccessFile;

import org.junit.Test;
import student.TestCase;


// -------------------------------------------------------------------------
/**
 *  Tests the Database, SkipList
 *
 *  @author Jazmine Zurita
 *  @version Jan 25, 2016
 */

public class DatabaseTest extends TestCase
{
    /**
     * Tests the insert and calls SkipList
     * @throws IOException 
     * @throws ClassNotFoundException 
     */
    @Test
    public void testInsert() throws ClassNotFoundException, IOException
    {
        RandomAccessFile f = new RandomAccessFile("test", "rw");
        RectangleDisk.dfile = f;
        RectangleDisk.bufSize = 1024;
        RectangleDisk.numBuffs = 3;
        Database d = new Database();
        d.skipInsert("a", 1, 1, 3, 5);
        assertEquals(2, d.skip.head);
    }

    /**
     * Calls the skiplist remove by coord
     * 
     */
    @Test
    public void testSkipRemoveCoord() throws ClassNotFoundException, IOException
    {
        RandomAccessFile f = new RandomAccessFile("test", "rw");
        RectangleDisk.dfile = f;
        RectangleDisk.bufSize = 1024;
        RectangleDisk.numBuffs = 3;
        Database db = new Database();
        Rect r = new Rect(null, 1, 2, 3, 4);
        //KVPair<String, Rect> p = new KVPair<String, Rect>(null, r);
        db.skip.removeByCoord(r);
        assertNotNull(r.getX());
    }
}