//import static org.junit.Assert.*;
//import org.junit.Test;
//import student.TestCase;
//
//
//// -------------------------------------------------------------------------
///**
// *  Tests the Database, SkipList
// *
// *  @author Jazmine Zurita
// *  @version Jan 25, 2016
// */
//
//public class DatabaseTest extends TestCase
//{
//    /**
//     * Tests the insert and calls SkipList
//     */
//    @Test
//    public void testInsert()
//    {
//        Database d = new Database();
//        d.skipInsert("a", 1, 1, 3, 5);
//        assertEquals(, d.skip.head);
//    }
//
//    /**
//     * Calls the skiplist remove by coord
//     * @param x the x coord
//     * @param y the y coord
//     * @param w the width
//     * @param h the height
//     */
//    @Test
//    public void skipRemoveCoord(int x, int y, int w, int h)
//    {
//        Database db = new Database();
//        Rect r = new Rect(null, x, y, w, h);
//        KVPair<String, Rect> p = new KVPair<String, Rect>(null, r);
//        db.skip.removeByCoord(r);
//    }
//}