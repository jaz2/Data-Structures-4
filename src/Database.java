
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
     */
    public Database()
    {
        skip = new SkipList<String, Rect>();
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
     */
    public void skipInsert(String id, int x, int y, int w, int h)
    {
        Rect r = new Rect(id, x, y, w, h);
        KVPair<String, Rect> p = new KVPair<String, Rect>(id, r);
        skip.insert(p); 
    }

    /**
     * Calls the skiplist remove by coord
     * @param x the x coord
     * @param y the y coord
     * @param w the width
     * @param h the height
     */
    public void skipRemoveCoord(int x, int y, int w, int h)
    {
        Rect r = new Rect(null, x, y, w, h);
        KVPair<String, Rect> p = new KVPair<String, Rect>(null, r);
        skip.removeByCoord(r);
    }
    
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
