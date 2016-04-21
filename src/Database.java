import java.io.IOException;

// -------------------------------------------------------------------------
/**
 *  This class contains the SkipList and works with the CommandProcessor.
 *
 *  @author Jazmine Zurita and Jessica McCready
 *  @version Jan 24, 2016
 */

public class Database
{
    /**
     * The SkipList
     */
    public SkipList<String, Rect> skip; 

    /**
     * The Database constructor which creates a SkipList
     * @throws IOException 
     */
    public Database() throws IOException
    {
    	MemoryManager mem = new MemoryManager(RectangleDisk.bufSize);
        skip = new SkipList<String, Rect>(mem);
    }

    /**
     * Inserts using the SkipList
     * 
     * @param id the key
     * @param x the x coord
     * @param y the y coord
     * @param w the width
     * @param h the height
     * @return
     * @throws IOException 
     * @throws ClassNotFoundException 
     */
    public void skipInsert(String id, int x, int y, int w, int h) throws IOException, ClassNotFoundException
    {
        Rect r = new Rect(id, x, y, w, h);
        KVPair<String, Rect> p = new KVPair<String, Rect>(id, r);
        skip.insert(p); 
    }

//    /**
//     * Calls the skiplist remove by coord
//     * @param x the x coord
//     * @param y the y coord
//     * @param w the width
//     * @param h the height
//     */
//    public void skipRemoveCoord(int x, int y, int w, int h)
//    {
//        Rect r = new Rect(null, x, y, w, h);
//        KVPair<String, Rect> p = new KVPair<String, Rect>(null, r);
//        skip.removeByCoord(r);
//    }
    
    //    /**
    //     * Searches for the rectangle through name
    //     * 
    //     * @param id
    //     */
    //    public void skipSearch(String id)
    //    {
    //        KVPair p = new KVPair()
    //        skip.search(id);
    //    }
}
