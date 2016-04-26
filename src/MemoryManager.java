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
public class MemoryManager {

    //make a data file just open and close it

    //make a store null to make it a null handle
    //should have the null handle HERE
    //can set it to -1
    /**
     * The array to store freelist blocks
     */
    public byte[] mm;

    /**
     * FreeList for FreeBlocks
     */
    public List freeList;

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
    public RandomAccessFile disk;

    /**
     * The constructor
     * @param size the buffer size
     * @param s the string 
     * @throws IOException 
     */
    public MemoryManager(int size, String s) throws IOException
    {
        disk = new RandomAccessFile(s, "rw");
        disk.setLength(0);
        mm = new byte[size];
        sz = size;
        fly = -1;
        freeList = new List();
        FreeBlock fb = new FreeBlock(size, 0);
        freeList.insert(fb);
        count = 0;
    }

    /**
     * Inserts into the memory manager array 
     * @param b the byte array given
     * @return the handle
     * @throws IOException 
     */
    public int insert(byte[] b) throws IOException
    {
        int end = mm.length;
        int position = 0;
        if (b == null)
        {
            System.out.println("Error");
            return fly; // return null handle
        }
        int bytesNeeded = b.length + 2;
        if (count + bytesNeeded <= mm.length)
        {
            ByteBuffer.wrap(mm).putShort(count, (short) b.length);
            System.arraycopy(b, 0, mm, count + 2, b.length);
            //disk.write(b, b.length + 2, b.length);
            position = count + 2;
            count = count + bytesNeeded;            
        }
        else 
        {
            byte[] nu = new byte[mm.length + Math.max(sz, bytesNeeded)];
            System.arraycopy(mm, 0, nu, 0, mm.length);
            mm = nu;  

            ByteBuffer.wrap(mm).putShort(end/*count*/, (short) b.length);
            System.arraycopy(b, 0, mm, end/*count*/ + 2, b.length);
            //disk.write(b, b.length + 2, b.length);
            position = end/*count*/ + 2;
            count = count + bytesNeeded;            
        }
        return position;
    }

    /**
     * Gets the node at the position 
     * in the array
     * @param handle the handle given
     * @return the length of the message
     */
    public byte[] getNode(int handle)
    {
        if (handle == fly)
        {
            return null;
        }    
        short size = ByteBuffer.wrap(mm).getShort(handle - 2);
        byte[] b = new byte[size];
        System.arraycopy(mm, handle, b, 0, size);
        return b;
    }

    /**
     * Updates and puts into memory manager
     * @param n the handle
     * @param o the node 
     * @throws IOException 
     * @throws ClassNotFoundException 
     */
    public void update(int h, Object o) throws IOException, ClassNotFoundException
    {
    	if (h == fly)
    	{
    		System.out.println("Error!");
    	}
        byte[] b = Serializer.serialize(o);
        ByteBuffer.wrap(mm).putShort(h + 2/*- 2*/, (short) b.length);
        System.arraycopy(b, 0, mm, h, b.length);
        //disk.write(b, n, b.length);
    }

    /**
     * To close the file
     * @throws IOException
     */
    public void quit() throws IOException
    {
        disk.close();
    }
} //don't need to connect it to BP for milestone 2
