

/**
 * The Rectangle class which acts an object for the KVPair
 * 
 * 
 * @author Jazmine Zurita and Jessica McCready
 * @version Feb 2 2016
 *
 */
public class Rect implements java.io.Serializable {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    /**
     * the name of the id
     */
    public String name;

    /**
     * the x coord
     */
    public int xx;

    /**
     * the y coord
     */
    public int yy;

    /**
     * the width
     */
    public int width;

    /**
     * the height
     */
    public int height;

    /**
     * Object class that handles Rectangles
     * @param id the name
     * @param x  the x coord
     * @param y  the y coord
     * @param w  the w coord
     * @param h  the h coord
     */
    public Rect(String id, int x, int y, int w, int h)
    {
        name = id;
        xx = x;
        yy = y;
        width = w;
        height = h;
    }

    /**
     * Returns the name of the Rectangle
     * @return the name
     */
    public String getName()
    {
        return name;
    }

    /**
     * Returns the x 
     * @return the y
     */
    public int getX()
    {
        return xx;
    }

    /**
     * Returns the y 
     * @return the y
     */
    public int getY()
    {
        return yy;
    }

    /**
     * Returns the width
     * @return the width
     */
    public int getWidth()
    {
        return width;
    }

    /**
     * Returns the height
     * @return the height
     */
    public int getHeight()
    {
        return height;
    }

    /**
     * Returns the toString of the four values
     * @return the string
     */
    public String toString()
    {
        String s = xx + ", " + yy + ", " + width + ", " + height;
        return s;
    }

    /**
     * Returns true if rectangles are equal
     * @param re the rectangle to compare to
     * @return true or false
     */
    @Override
    public boolean equals(Object re)
    {       
        if (xx == ((Rect) re).getX() && yy == ((Rect) re).getY() && 
                width == ((Rect) re).getWidth() && 
                    height == ((Rect) re).getHeight())
            return true;
        else return false;
    }

    /**
     * For the intersects method
     * @param r1 rect to check
     * @return the coords
     */
    public boolean intersects(Rect r1)
    {
        if (xx + width > r1.getX() && yy + height > r1.getY()) 
            return true;
        else return false;
    }
}