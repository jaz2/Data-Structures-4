import java.lang.reflect.Array; 
import java.util.Random;

/**
 * The SkipList holds the SkipNode and 
 * is in charge of putting the rectangles where
 * they belong or deleting and searching 
 * 
 * @author Jazmine Zurita and Jessica McCready
 * @version Feb 01 2016
 * @param <K>  the key
 * @param <E>  the value
 *
 */
public class SkipList<K extends Comparable<K>, E> { 
    //module (KVPair is a sep. class) 6.3

    /**
     * @author Jazmine Zurita and Jessica McCready
     * @version Feb 1 2016
     *
     */
    public class SkipNode  {

        /**
         * forward array
         */
        public SkipNode[] forward;

        /**
         * the element 
         */
        public KVPair<K, E> element;

        /**
         * The constructor for the SkipNode
         * @param it  the element
         * @param level  the level
         */
        @SuppressWarnings("unchecked")
        public SkipNode(KVPair<K, E> it, int level)
        {
            element = it;
            forward = (SkipNode[]) Array.newInstance(
                    SkipList.SkipNode.class, level + 1);
        }

        /**
         * Returns the element
         * @return returns the element
         */
        public KVPair<K, E> element()
        {
            return element;
        }

        /**
         * Returns the level of the SkipNode
         * @return returns the level
         */
        public int getLevel()
        {
            return level;
        }
    }

    /**
     * the level of the skiplist
     */
    public int level;

    /**
     * The size 
     */
    public int size;

    /**
     * The head node
     */
    public SkipNode head;

    /**
     * For the random 
     */
    private Random rnd;

    /**
     * Constructor for the SkipList
     */
    public SkipList()
    { 
        head = new SkipNode(null, 0);    
        head.forward[0] = null; //check if this is correct
        level = 0;
        rnd = new Random();
        size = 0;
    }

    /** 
     * Pick a level using a geometric distribution 
     * @return the level
     */ 
    int randomLevel() {   
        int lev;   
        for (lev = 0; rnd.nextInt(2) == 0; lev++)
        {
            //Do nothing
        } 
        return lev; 
    }


    /** 
     * Insert a KVPair into the skiplist 
     * @param it the element
     * @return the values
     */ 
    public boolean insert(KVPair<K, E> it) {   
        int newLevel = randomLevel();   
        Comparable<K> k = it.key();  
        if (level < newLevel)     
            adjustHead(newLevel);
        @SuppressWarnings("unchecked")  //Generic array allocation   
        SkipNode[] update = (SkipNode[])Array.newInstance(
                SkipList.SkipNode.class, level + 1);   
        SkipNode x = head;        // Start at header node   
        for (int i = level; i >= 0; i--) { // Find insert position     
            while ((x.forward[i] != null) && 
                    (k.compareTo((x.forward[i]).element().key()) > 0))       
                x = x.forward[i];   
            update[i] = x;               // Track end at level i   
        }   
        x = new SkipNode(it, newLevel);   
        for (int i = 0; i <= newLevel; i++) {      // Splice into list     
            x.forward[i] = update[i].forward[i]; // Who x points to     
            update[i].forward[i] = x;            // Who y points to   
        }   
        size++;                       // Increment dictionary size
        return true; 
    }

    /**
     * Searches by name and if found, 
     * removes the rectangle
     * @param key the key
     */
    public void removeByName(Comparable<K> key)
    {
        boolean found = false;   
        SkipNode x = head;                     // Dummy header node   
        @SuppressWarnings("unchecked")
        SkipNode[] store = (SkipNode[]) Array.newInstance(
                SkipList.SkipNode.class, level + 1);
        for (int i = level; i >= 0; i--)  
        { // For each level...     
            while ((x.forward[i] != null) &&            
                    (key.compareTo(x.forward[i].
                            element().key()) > 0)) // go forward
            {
                x = x.forward[i];              // Go one last step 
            }
            store[i] = x;
        }
        // now the store is populated
        x = x.forward[0];  // Move to actual record, if it exists
        if ((x != null) && 
                (key.compareTo(x.element().key()) == 0))    
        {
            found = true; //found an instance of key
            //now remove key
            //make a for loop going over store to 
            //update the nodes before and after
            for (int i = x.forward.length - 1; i >= 0; i--)
            {
                store[i].forward[i] = x.forward[i];
            }
            size--;
            System.out.println("Rectangle removed: ("
                    + x.element.toString() + ")");
        }
        if (found == false)
        {
            System.out.println("Rectangle not found: " + key); 
        }
    }
    
    /**
     * Removes the coordinates if found
     * @param val the value to search for
     */
    public void removeByCoord(E val) //while we haven't found it
    { //and we haven't gotten to the end go at level 0, 
        boolean found = false;   
        SkipNode x = head;                     // Dummy header node   
        @SuppressWarnings("unchecked")
        SkipNode[] store = (SkipNode[]) Array.newInstance(
                SkipList.SkipNode.class, level + 1);
        for (int i = level; i >= 0; i--)
        {
            store[i] = x;
        } //and not at the end
        while (x.forward[0] != null && 
                ((Rect) x.forward[0].element.value()).equals(val) == false)
        {
            for (int i = x.forward.length - 1; i >= 0; i--)
            { /*each level in the current node*/
                store[i] = x.forward[i]; //currentNode
            }
            //advance currentNode
            x = x.forward[0];
        }
        SkipNode nodeToRemove = x.forward[0];
        if (x.forward[0] != null && 
                ((Rect) x.forward[0].element.value()).equals(val) == true)
        {
            found = true;
        }
        if (found == true)    
        {
            //now remove key
            for (int i = 0; i <= nodeToRemove.forward.length - 1; i++)
            {
                store[i].forward[i] = nodeToRemove.forward[i];
            }
            size--;
            System.out.println("Rectangle removed: ("
                    + nodeToRemove.element.toString() + ")");
        }
        if (found == false)

        {
            System.out.println("Rectangle not removed: (" 
                    + val.toString() + ")");
        }
    }

    /**
     * Return the (first) matching matching element 
     * if one exists, null otherwise
     *  
     * @param key the key
     */
    public void search(Comparable<K> key) {   
        boolean found = false;   
        SkipNode x = head;                     // Dummy header node   
        for (int i = level; i >= 0; i--)           // For each level...     
            while ((x.forward[i] != null) &&            
                    (key.compareTo(x.forward[i].
                            element().key()) > 0)) // go forward       
                x = x.forward[i];              // Go one last step   
        x = x.forward[0];  // Move to actual record, if it exists
        if ((x != null) && 
                (key.compareTo(x.element().key()) == 0))    
        {
            found = true;
            System.out.println("(" + x.element.toString() + ")");
            //look ahead at level 0 printing as long as it is an equal key
            if (x.forward[0] != null)
            {
                x = x.forward[0];
                while (x != null && key.equals(x.element.key()))
                {
                    System.out.println("(" + x.element.toString() + ")");
                    x = x.forward[0];
                }
            }
        }
        if (found == false)  //transfer tests
        {
            System.out.println("Rectangle not found: " + key); 
        }
    }

    /**
     * Adjusts the head 
     * Take the SkipNode head's forward array
     * allocate a new array of newlevel(or level + 1) size
     * copy all data from previous forward into new one 
     * and make new one equal to forward
     * ex: head.forward = newArray
     * @param lev  the new level
     */
    public void adjustHead(int lev)
    {
        @SuppressWarnings("unchecked")
        SkipNode[] nu = (SkipNode[]) Array.newInstance(
                SkipList.SkipNode.class, lev + 1);
        for (int i = 0; i < head.forward.length; i++)
        {
            nu[i] = head.forward[i];
        }
        for (int i = head.forward.length; i < lev; i++)
        {
            nu[i] = null;
        }
        head.forward = nu;
        level = lev;
    }

    /**
     * Prints out each SkipNode from left to right
     * and returns the value and number of pointers
     */
    public void dump()
    {
        if (size == 0)
        {
            System.out.println("SkipList dump: ");
            System.out.println("Node has depth 1, Value (null)");
            System.out.println("SkipList size is: 0");
        }
        else 
        {
            System.out.println("SkipList dump: ");
            System.out.println("Node has depth " + head.getLevel() +
                    ", Value (null)");

            SkipNode node = head;
            for (int i = 1; i <= size + 0; i++)
            {
                System.out.println("Node has depth " + node.getLevel() +
                        ", Value (" + node.forward[0].element.key() + ", "
                        + node.forward[0].element.value().toString() + ")");
                node = node.forward[0];
            } 
            System.out.println("SkipList size is: " + size);
        }
    }

    /**
     * For testing purposes
     * @param it the element
     * @param n the level
     * @return the thing
     */
    public boolean controlledInsert(KVPair<K, E> it, int n)
    {
        int newLevel = n;
        Comparable<K> k = it.key();
        //        System.exit(0);
        if (level < newLevel) //adjust levels
            adjustHead(newLevel);
        @SuppressWarnings("unchecked") // Generic array allocation
        SkipNode[] update = (SkipNode[])Array.
        newInstance(SkipList.SkipNode.class, level + 1);
        SkipNode x = head;        // Start at header node
        for (int i = level; i >= 0; i--) { // Find insert position
            while ((x.forward[i] != null) &&
                    (k.compareTo((x.forward[i]).element().key()) > 0))
                x = x.forward[i];
            update[i] = x;               // Track end at level i
        }    
        x = new SkipNode(it, newLevel);
        for (int i = 0; i <= newLevel; i++) {      // Splice into list
            x.forward[i] = update[i].forward[i]; // Who x points to
            //    System.exit(0);
            update[i].forward[i] = x;            // Who y points to
        }
        size++;                       // Increment dictionary size
        return true;
    }
    
    /**
     * The RegionSearch method
     * @param x x coordinate
     * @param y y coordinate 
     * @param h height
     * @param w width 
     */
    public void regionsearch(int x, int y, int w, int h)
    {
        System.out.println("Rectangles intersecting region (" + x
                + ", " + y + ", " + w + ", " + h + "):");
        SkipNode node = head;
        Rect r;
        Rect rect = new Rect("rs", x, y, w, h);
        for (int i = 1; i <= size + 0; i++)
        {
            r = (Rect) node.forward[0].element.value();
            if (r.intersects(rect))
            {
                if (rect.intersects(r))
                {
                    System.out.println("(" 
                            + node.forward[0].element.key() + ", "
                            + node.forward[0].element.value().toString() + ")");
                }
            }
            node = node.forward[0];
        } 
    }

    
    /**
     * Intersections method
     *  
     */
    public void intersections()
    {
        SkipNode node1 = head;
        SkipNode node2;
        Rect r1;
        Rect r2;
        for (int i = 1; i <= size; i++)
        {
            r1 = (Rect) node1.forward[0].element.value();
            node2 = head;
            for (int j = 1; j <= size; j++)
            {
                r2 = (Rect) node2.forward[0].element.value();
                if (node1.forward[0] != node2.forward[0])
                {
                    if (r1.intersects(r2) && r2.intersects(r1))
                    {
                        System.out.println("(" + node1.forward[0].
                                element.key() + ", " + 
                                    node1.forward[0].element.value()
                                        .toString() + " | " + node2.
                                            forward[0].element.key() + 
                                                ", " + node2.forward[0].element.
                                                    value().toString() + ")");
                    }
                }

                node2 = node2.forward[0];
            }

            node1 = node1.forward[0];
        } 
    }
    
}