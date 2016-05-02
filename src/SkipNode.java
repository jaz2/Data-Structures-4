/**
 * 
 */

/**
 * @author Jazmine Zurita and Jessica McCready
 * @version Feb 1 2016
 *
 */
public class SkipNode implements java.io.Serializable {
	
    private static final long serialVersionUID = 1L;

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

    /**
     * To check if same
     * @param s the skipnode
     * @return t or f
     */
    public boolean equalss(SkipNode s)
    {
        return (s.element() == element);
    }
}
