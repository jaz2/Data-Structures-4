import java.io.IOException;
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
    public int head; //could be a skipnode but should be an int

    /**
     * For the random 
     */
    private Random rnd;

    /**
     * To keep track of memory
     */
    public MemoryManager mm;

    /**
     * Constructor for the SkipList
     * @param m the memory manager
     * @throws IOException 
     * @throws ClassNotFoundException 
     */
    public SkipList(MemoryManager m) throws IOException, ClassNotFoundException
    {
        mm = m;
        //head = 0;    
        SkipNode skip = new SkipNode(MemoryManager.fly, 0); 
        skip.forward[0] = MemoryManager.fly; //forward array of 1 null element
        byte[] message = Serializer.serialize(skip);
        //serialize it and when you get the handle set that to head
        int handle = mm.insert(message);
        head = handle; //pass byte array to Mem, set handle to head
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
     * Insert a KVPair into the skipList 
     * @param it the element
     * @return the values
     * @throws IOException 
     * @throws ClassNotFoundException 
     */ 
    public boolean insert(KVPair<K, E> it) 
        throws IOException, ClassNotFoundException {  
        if (it == null)
        {
            return false;
        }
        int newLevel = randomLevel();   
        Comparable<K> k = it.key();  
        if (level < newLevel) 
        {
            adjustHead(newLevel);
        }
        @SuppressWarnings("unchecked")  //Generic array allocation   
        int[] update = new int[level + 1];   
        int x = head;        // Start at header node    
        for (int i = level; i >= 0; i--) { // Find insert position   
            while (((getNode(x)).forward[i] != MemoryManager.fly) &&
                    (k.compareTo(((getKV((getNode((getNode(x)).
                            forward[i])).element())).key())) > 0))
            {
                x = (getNode(x)).forward[i];
            } 
            update[i] = x;               // Track end at level i   
        }   
        int kv = insertObject(it);
        x = insertObject(new SkipNode(kv, newLevel));   
        for (int i = 0; i <= newLevel; i++)  
        {      // Splice into list 
            SkipNode save = getNode(x);
            save.forward[i] = getNode(update[i]).forward[i]; // Who x points to
            mm.update(x, save);
            SkipNode pred = getNode(update[i]);
            pred.forward[i] = x;
            mm.update(update[i], pred);            // Who y points to  

        }    
        size++;                       // Increment dictionary size
        return true; 
    }

    /**
     * Searches by name and if found, 
     * removes the rectangle
     * @param key the key
     * @throws IOException 
     * @throws ClassNotFoundException 
     */
    public void removeByName(Comparable<K> key) 
        throws ClassNotFoundException, IOException
    {
        boolean found = false;   
        int x = head;                     // Dummy header node   
        @SuppressWarnings("unchecked")
        int[] store = new int[level + 1];
        for (int i = level; i >= 0; i--)  
        { // For each level...     
            while ((getNode(x).forward[i] != MemoryManager.fly) &&            
                    (key.compareTo(((getKV((getNode((getNode(x))
                            .forward[i])).element()))
                                .key())) > 0)) // go forward
            {
                x = getNode(x).forward[i];              // Go one last step 
            }
            store[i] = x; 
        }
        // now the store is populated
        x = getNode(x).forward[0];  // Move to actual record, if it exists
        if ((x != MemoryManager.fly) && 
                (key.compareTo(getKV(getNode(x).element()).key()) == 0))    
        {
            found = true; //found an instance of key
            //now remove key
            //make a for loop going over store to 
            //update the nodes before and after
            for (int i = getNode(x).forward.length - 1; i >= 0; i--)
            {
                //getNode(store[i]).forward[i] = getNode(x).forward[i];

                SkipNode pred = getNode(store[i]);
                pred.forward[i] = getNode(x).forward[i];
                mm.update(store[i], pred);  
            }
            size--;
            System.out.println("Rectangle removed: ("
                    + getKV(getNode(x).element).toString() + ")");
            mm.remove(getNode(x).element);
            mm.remove(x);
        }
        if (!found)
        {
            System.out.println("Rectangle not found: " + key); 
        }
    }

    /**
     * Removes the coordinates if found
     * @param val the value to search for
     * @throws IOException 
     * @throws ClassNotFoundException 
     */
    public void removeByCoord(E val) 
        throws ClassNotFoundException, IOException
    { //and we haven't gotten to the end go at level 0, 
        boolean found = false;   
        int x = head;                     // Dummy header node   
        @SuppressWarnings("unchecked")
        int[] store = new int[level + 1];
        for (int i = level; i >= 0; i--)
        {
            store[i] = x;
        } //and not at the end
        while (getNode(x).forward[0] != MemoryManager.fly && 
                !((Rect) getKV(getNode(getNode(x).forward[0]).element)
                        .value()).equals(val))
        {
            for (int i = getNode(x).forward.length - 1; i >= 0; i--)
            { /*each level in the current node*/
                store[i] = getNode(x).forward[i]; //currentNode
            }
            //advance currentNode
            x = getNode(x).forward[0];
        }
        int nodeToRemove = getNode(x).forward[0];
        if (getNode(x).forward[0] != MemoryManager.fly && 
                ((Rect) getKV(getNode(getNode(x).forward[0])
                        .element).value()).equals(val))
        {
            found = true;
        }
        if (found)    
        {
            //now remove key
            for (int i = 0; i <= getNode(nodeToRemove).forward.length - 1; i++)
            {
                SkipNode pred = getNode(store[i]);
                pred.forward[i] = getNode(nodeToRemove).forward[i];
                mm.update(store[i], pred); 
            }
            size--;
            System.out.println("Rectangle removed: ("
                    + getKV(getNode(nodeToRemove).element).toString() + ")");
            mm.remove(getNode(nodeToRemove).element);
            mm.remove(nodeToRemove);
        }
        if (!found)

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
    public void search(Comparable<K> key) 
        throws ClassNotFoundException, IOException {   
        boolean found = false;   
        int x = head;                     // Dummy header node   
        for (int i = level; i >= 0; i--) // For each level...                 
        {
            while ((getNode(x).forward[i] != MemoryManager.fly) &&            
                    (key.compareTo(getKV(getNode(getNode(x)
                            .forward[i]).element)
                            .key()) > 0)) // go forward 
            {
                x = getNode(x).forward[i];              // Go one last step   
            }
        }
        x = getNode(x).forward[0];  // Move to actual record, if it exists
        if ((x != MemoryManager.fly) && 
                (key.compareTo(getKV(getNode(x).element).key()) == 0))    
        {
            found = true;
            System.out.println("(" + getKV(getNode(x)
                    .element).toString() + ")");
            //look ahead at level 0 printing as long as it is an equal key
            if (getNode(x).forward[0] != MemoryManager.fly)
            {
                x = getNode(x).forward[0];
                while (x != MemoryManager.fly && key.
                        equals(getKV(getNode(x).element).key()))
                {
                    System.out.println("(" + getKV(getNode(x)
                            .element).toString() + ")");
                    x = getNode(x).forward[0];
                }
            }
        }
        if (!found)  //transfer tests
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
     * @throws IOException 
     * @throws ClassNotFoundException 
     */
    public void adjustHead(int lev) throws IOException, ClassNotFoundException
    {
        @SuppressWarnings("unchecked")
        int[] nu = new int[lev + 1];
        for (int i = 0; i < (getNode(head)).forward.length; i++)
        {
            nu[i] = (getNode(head)).forward[i];
        }
        for (int i = (getNode(head)).forward.length; i <= lev; i++)
        {
            nu[i] = MemoryManager.fly;
        }
        SkipNode oldHead = getNode(head);
        oldHead.forward = nu;
        oldHead.lev = lev;
        //release head
        mm.remove(head);
        head = insertObject(oldHead);
        level = lev;
    }

    /**
     * Prints out each SkipNode from left to right
     * and returns the value and number of pointers
     * @throws IOException 
     * @throws ClassNotFoundException 
     */
    public void dump() throws IOException, ClassNotFoundException
    {
        if (size == 0)
        {
            System.out.println("SkipList dump: ");
            System.out.println("Node has depth 1, Value (null)");
            System.out.println("SkipList size is: 0");
            System.out.println("Freelist Blocks: \n(0, " 
                    + RectangleDisk.bufSize + ")");
        }
        else 
        {
            System.out.println("SkipList dump: ");
            System.out.println("Node has depth " + getNode(head).getLevel() +
                    ", Value (null)");

            int node = head;
            for (int i = 1; i <= size + 0; i++)
            {
                System.out.println("Node has depth " + (
                        getNode(node)).getLevel() +
                        ", Value (" + getKV(getNode(getNode(node)
                                .forward[0]).element).key() + ", "
                                + (getKV(getNode(getNode(node).forward[0])
                                        .element)).value().toString() + ")");
                node = getNode(node).forward[0];
            } 
            System.out.println("SkipList size is: " + size);
            System.out.println("Freelist Blocks: \n" + dumpFreeBlocks());
        }
    }

    /**
     * Dumps all free blocks
     * @return the string
     */
    public String dumpFreeBlocks()
    {
        String s = "";
        for (int i = 0; i < mm.freeList.length(); i++)
        {
            s += mm.freeList.get(i).dump();
        }
        return s;
    }

    /**
     * To make an int a skipNode
     * @param n the handle
     * @return the skipnode
     * @throws ClassNotFoundException
     * @throws IOException
     */
    public SkipNode getNode(int n) throws IOException, ClassNotFoundException
    {
        if (n == MemoryManager.fly)
        {
            return null;
        }
        return ((SkipNode)getObject(n));
    }

    /**
     * To make an int a kvpair
     * @param n the handle
     * @return the kvpair that is from the handle
     * @throws ClassNotFoundException
     * @throws IOException
     */
    @SuppressWarnings("unchecked")
    public KVPair<K, E> getKV(int n) throws IOException, ClassNotFoundException
    {
        if (n == MemoryManager.fly)
        {
            return null;
        }
        return ((KVPair<K, E>)Serializer.deserialize(mm.getNode(n)));
    }

    /**
     * Uses deserializer to convert handle into object
     * @param n the handle
     * @return the object
     * @throws IOException 
     * @throws ClassNotFoundException 
     */
    public Object getObject(int n) throws IOException, ClassNotFoundException
    {
        if (n == MemoryManager.fly)
        {
            return null;
        }
        return Serializer.deserialize(mm.getNode(n));
    }

    /**
     * Inserts object into mem manager
     * @param o the object
     * @return that it inserted into mm
     * @throws IOException 
     */
    public int insertObject(Object o) throws IOException
    {
        return mm.insert(Serializer.serialize(o));
    }


    //    /**
    //     * For testing purposes
    //     * @param it the element
    //     * @param n the level
    //     * @return the thing
    //     */
    //    public boolean controlledInsert(int it, int n)
    //    {
    //        int newLevel = n;
    //        Comparable<K> k = it.key();
    //        //        System.exit(0);
    //        if (level < newLevel) //adjust levels
    //            adjustHead(newLevel);
    //        @SuppressWarnings("unchecked") // Generic array allocation
    //        int[] update = new int[level + 1];
    // SkipNode x = ((SkipNode)getObject(head));   // Start at header node
    //        for (int i = level; i >= 0; i--) { // Find insert position
    //            while ((x.forward[i] != null) &&
    //                    (k.compareTo((x.forward[i]).element().key()) > 0))
    //                x = x.forward[i];
    //            update[i] = x;               // Track end at level i
    //        }    
    //        x = new SkipNode(it, newLevel);
    //        for (int i = 0; i <= newLevel; i++) {      // Splice into list
    //            x.forward[i] = update[i].forward[i]; // Who x points to
    //            //    System.exit(0);
    //            update[i].forward[i] = x;            // Who y points to
    //        }
    //        size++;                       // Increment dictionary size
    //        return true;
    //    }

    /**
     * The RegionSearch method
     * @param x x coordinate
     * @param y y coordinate 
     * @param h height
     * @param w width 
     */
    public void regionsearch(int x, int y, int w, int h) 
        throws ClassNotFoundException, IOException
    {
        System.out.println("Rectangles intersecting region (" + x
                + ", " + y + ", " + w + ", " + h + "):");
        int node = head;
        Rect r;
        Rect rect = new Rect("rs", x, y, w, h);
        //        int rh = insertObject(rect);
        for (int i = 1; i <= size + 0; i++)
        { 
            r = (Rect) getKV(getNode(getNode(node).forward[0]).element).value();
            if (r.intersects(rect))
            {
                if (rect.intersects(r))
                {
                    System.out.println("(" 
                            + (getKV(getNode(getNode(node).forward[0])
                                    .element)).key() + ", "
                                    + (getKV(getNode(getNode(node).forward[0])
                                            .element)).value()
                                    .toString() + ")");
                }
            }
            node = getNode(node).forward[0];
            //node = node.forward[0];
        } 
    }


    /**
     * Intersections method
     *  
     */
    public void intersections() throws ClassNotFoundException, IOException
    {
        int node1 = head;
        int node2;
        Rect r1;
        Rect r2;
        for (int i = 1; i <= size; i++)
        {
            r1 = (Rect) getKV(getNode(getNode(node1)
                    .forward[0]).element).value();
            //    r1 = (Rect) node1.forward[0].element.value();
            node2 = head;
            for (int j = 1; j <= size; j++)
            {
                r2 = (Rect) getKV(getNode(getNode(node2)
                        .forward[0]).element).value();
                if (getNode(node1).forward[0] != getNode(node2).forward[0])
                {
                    if (r1.intersects(r2) && r2.intersects(r1))
                    {
                        System.out.println("(" 
                                + getKV(getNode(getNode(node1)
                                        .forward[0]).element).key()
                                + ", "    + getKV(getNode(getNode(node1)
                                        .forward[0]).element).value().toString()
                                + " | " 
                                + getKV(getNode(getNode(node2).forward[0])
                                        .element).key() + ", "
                                        + getKV(getNode(getNode(node2)
                                                .forward[0])
                                                .element).value()
                                        .toString() + ")");
                    }
                }
                node2 = getNode(node2).forward[0];
                //node2 = node2.forward[0];
            }
            node1 = getNode(node1).forward[0];
            //node1 = node1.forward[0];
        } 
    }
}