import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;

/**
 * 
 */

/**
 * @author Jazmine Zurita and Jessica McCready
 * @version April 14th
 *
 */
public class MemoryManager{

    //make a data file just open and close it

    /**
     * For insert
     */
    public boolean found;

    /**
     * The array to store freelist blocks
     */
    public byte[] mm;

    /**
     * FreeList for FreeBlocks
     */
    public List freeList;

    /**
     * The first free block
     */
    public FreeBlock fb;

    /**
     * Keeps track of size
     */
    public int sz;

    /**
     * Number of blocks used in array
     */
    public int count;

    /**
     * The null handle
     */
    public static int fly;

    /**
     * The file we will write into
     */
    // public RandomAccessFile disk;



    public byte[] m;



    public RandomAccessFile f;


    public int bufSize;

    public static BufferPool bp;


    /**
     * The constructor
     * @param size the buffer size
     * @param s the string 
     * @throws IOException 
     */
    public MemoryManager(int size, RandomAccessFile s) throws IOException
    {
        //disk = new RandomAccessFile(s, "rw");
        s.setLength(RectangleDisk.bufSize);
        fly = -1;
        count = 0;
        m = new byte[size];
        bufSize = size;
        freeList = new List();
        FreeBlock fb = new FreeBlock(size, 0);
        freeList.insert(fb);
        found = false;
        f = s;
        //f = new RandomAccessFile(RectangleDisk.dfile, "rw");
        bp = new BufferPool(RectangleDisk.numBuffs);
    }

    /**
     * Updates and puts into memory manager
     * @param h the handle
     * @param o the node 
     * @throws IOException 
     * @throws ClassNotFoundException 
     */
    public void update(int h, Object o) 
            throws IOException, ClassNotFoundException
    {
        if (h == fly)
        {
            System.out.println("Error!");
        }
        byte[] b = Serializer.serialize(o);
        byte[] a = new byte[2];
        ByteBuffer.wrap(a).putShort(0, (short) b.length);
        // System.arraycopy(b, 0, m, h, b.length);
        bp.write(f, 2, h - 2, a); //array of data to write in
        bp.write(f, b.length, h, b);
    }

    /**
     * turns used space to free space
     * @param h
     * @throws IOException 
     */
    public void makeFree(int h) throws IOException
    {
        byte[] a = new byte[2];
        bp.read(f, 2, h - 2, a);
        short size = ByteBuffer.wrap(a).getShort(0);
        FreeBlock f = new FreeBlock(size + 2, h - 2); 
        freeList.insert(f);        
    }

    /**
     * inserts byte array into memory array
     * @param b bytes representing array
     * @return the handle of object
     * @throws IOException 
     */
    public int insert(byte[] b) throws IOException
    {
        //look at last free block, if its longer then what you need, grab what you need and leave the rest ->pull off bytes from front
        found = false;
        int end = ((int)f.length());
        if(b == null) 
        {
            return fly;
        }
        int bytesNeeded = b.length + 2;
        int pos = 0;
        for (int i = 0; i < freeList.length() && !found; i++)
        {
            if (bytesNeeded <= freeList.get(i).sz)
            {
                found = true;
                FreeBlock f1 = freeList.get(i);
//                if (freeList.get(i).p + freeList.get(i).sz != end) //not at end
//                {
                    pos = freeList.get(i).p + 2;
                    FreeBlock f3 = new FreeBlock((f1.sz - bytesNeeded), f1.p + bytesNeeded);
                    freeList.remove(f1);
                    freeList.insert(f3);

                    byte[] a = new byte[2];                
                    ByteBuffer.wrap(a).putShort(0, (short) b.length);

                    bp.write(f, 2, pos - 2, a);
                    bp.write(f, b.length, pos, b);
//                }
//                else //at end
//                {
//                	pos = freeList.get(i).p + 2;
//                    FreeBlock f4 = new FreeBlock((f1.sz - bytesNeeded), f1.p + bytesNeeded);
//                    freeList.remove(f1);
//                    freeList.insert(f4);                    
//
//                    byte[] a = new byte[2];                
//                    ByteBuffer.wrap(a).putShort(0, (short) b.length);
//
//                    bp.write(f, 2, pos - 2, a);
//                    bp.write(f, b.length, pos, b);
//                    pos = count + 2;
//                    count = count + b.length + 2;
//                }              
            }
        }
        if (!found)  //make more mem 
        {
            int spaceAdded = 0;
            FreeBlock last = findLast();
           // int leftover = end - count;
//            if(leftover + ((bytesNeeded/bufSize)) * bufSize >= bytesNeeded) //round buff number down
//            { 
//                spaceAdded = ((bytesNeeded/bufSize) * bufSize);
//            }
//            else
//            {
                spaceAdded = (((bytesNeeded/bufSize) + 1) * bufSize);
//            }
            //FreeBlock fold = find(count);
            FreeBlock fnew = new FreeBlock((end - last.p) + spaceAdded, last.p);
            freeList.remove(last);
            freeList.insert(fnew);
            end = end + spaceAdded;
            f.setLength(end);
            //inserts 
//            for (int i = 0; i < freeList.length() && !found; i++)
//            {
//                if (bytesNeeded <= freeList.get(i).sz)
//                {
//                    found = true;
//                    FreeBlock f1 = freeList.get(i);
//                    if (freeList.get(i).p + freeList.get(i).sz != end) //not at end
//                    {
//
//                        FreeBlock f2 = find(f1.p + f1.sz);
//                        if (f2 == null) System.out.println("kill");
//                        FreeBlock f3 = new FreeBlock((f1.sz - bytesNeeded) + f2.sz, count + bytesNeeded);
//                        freeList.remove(f1);
//                        freeList.remove(f2);
//                        freeList.insert(f3);
//                    }
//                    else //at end
//                    {
//                        FreeBlock f4 = new FreeBlock((f1.sz - bytesNeeded), count + bytesNeeded);
//                        freeList.remove(f1);
//                        freeList.insert(f4);
//                    }                
//                }
//            }
//            byte[] a = new byte[2];                
//            ByteBuffer.wrap(a).putShort(0, (short) b.length);

////            bp.write(f, 2, count, a);
//            bp.write(f, b.length, count + 2, b);
//            pos = count + 2;
//            count = count + b.length + 2;
            return insert(b);
        }
        return pos;
    }

    /**
     * Finds the handle
     * @param x the handle
     * @return as a freeblock
     */
    public FreeBlock find(int x)
    {
        for (int i = 0; i < freeList.length(); i++)
        {
            if (freeList.get(i).p == x)
            {
                return freeList.get(i);
            }
        }
        return null;
    }
    
    /**
     * Find the last thing in the list
     * @param x compares if it is last thing
     * @return free block
     */
    public FreeBlock findLast()
    {
    	FreeBlock max = freeList.get(0);
    	for (int i = 0; i < freeList.length(); i++)
    	{
    		if (freeList.get(i).p > max.p)
    		{
    			max = freeList.get(i);
    		}
    	}
    	return max;
    }

    /**
     * Gets the node
     * @param x the object handle 
     * @return the byte array that describes object
     * @throws IOException 
     */
    public byte[] getNode(int x) throws IOException
    {
        if(x == fly) 
            return null;
        else 
        {
            byte[] a = new byte[2];        
            bp.read(f, 2, x - 2, a);
            //ByteBuffer.wrap(a).putShort(0, (short) b.length);        

            short size = ByteBuffer.wrap(a).getShort(0);
            byte[] b = new byte[size];
            //System.arraycopy(m, x, b, 0, size);
            bp.read(f, size, x, b);
            //bp.write(f, b.length, x + 2, b);
            return b;
        }
    }
}
