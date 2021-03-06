/**
 * 
 */

/**
 * @author Jazmine Zurita and Jessica McCready
 * @version April 18 2016
 *
 */
public class FreeBlock {
    
    /**
     * The size of message
     */
    public int sz;
    
    /**
     * The position
     */
    public int p;
    
    /**
     * Constructor
     * @param size the size of the block
     * @param pos where it is
     */
    public FreeBlock(int size, int pos)
    {
        sz = size;
        p = pos;
    }
    
    /**
     * Dumps a free block
     * For SkipList dump
     * @return the string
     */
    public String dump()
    {    
        return "(" + p + ", " + sz + ")\n";
    }
}
