/**
 * 
 */

/**
 * @author Jazmine Zurita and Jessica McCready
 * @version Feb 1 2016
 *
 */
public class SkipNode {

    /**
     * forward array
     */
    public int[] forward;

    /**
     * the element 
     */
    public int element;

    /**
     * The level var
     */
    public int lev;

    /**
     * The constructor for the SkipNode
     * @param it  the element
     * @param level  the level
     */
    @SuppressWarnings("unchecked")
    public SkipNode(int it, int level)
    {
        element = it;
        forward =  new int[level + 1];
        lev = level;
    }

    /**
     * Returns the element
     * @return returns the element
     */
    public int element()
    {
        return element;
    }

    /**
     * Returns the level of the SkipNode
     * @return returns the level
     */
    public int getLevel()
    {
        return lev;
    }
}
