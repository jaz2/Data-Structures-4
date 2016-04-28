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
    public RandomAccessFile disk;

    /**
     * The bufferpool
     */
    public BufferPool bp;

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
        fb = new FreeBlock(size, 0);
        freeList.insert(fb);
        count = 0;
        found = false;
    }

    /**
     * Inserts into the memory manager array 
     * @param b the byte array given
     * @return the handle
     * @throws IOException 
     */
    public int insert(byte[] b) throws IOException
    {
        //int end = mm.length;
        int position = 0;
        if (b == null)
        {
            System.out.println("Error");
            return fly; // return null handle
        }
        int bytesNeeded = b.length + 2;
        /*if (count + bytesNeeded <= mm.length)*/
        //        {               
        //disk.write(b, b.length + 2, b.length);
        //where ever it ends, update those two bytes to be a new block
        for (int i = 0; i < freeList.length() && !found; i++)
        {
            if (bytesNeeded <= freeList.get(i).sz)
            {                    
                FreeBlock f1 = freeList.get(i);
                if (freeList.get(i).sz + freeList.get(i).p != mm.length)
                {
                    FreeBlock f2 = find(freeList.get(i).p + freeList.get(i).sz);
                    FreeBlock f3 = new FreeBlock((f1.sz - bytesNeeded) + f2.sz, count + bytesNeeded);
                    freeList.remove(f1);
                    freeList.remove(f2);
                    freeList.insert(f3);
                    found = true;
                }
                else 
                {
                    FreeBlock f4 = new FreeBlock(f1.sz - bytesNeeded, count + bytesNeeded);
                    freeList.remove(f1);
                    freeList.insert(f4);
                    found = true;
                }  
                //found = true;
            }
        }
        if (found)
        {
            found = false;
            //bp.write(disk, bytesNeeded, count, mm);
            ByteBuffer.wrap(mm).putShort(count, (short) b.length);
            System.arraycopy(b, 0, mm, count + 2, b.length);
            position = count + 2; 
            count += bytesNeeded;
        }
        //if u have 300, and take out 200,you get left with 100
        // }
        else
        { 
            int newSpace = 0;
            int leftover = mm.length - count;
            int spaceAdded = 0;
            if (leftover + ((bytesNeeded / sz)) * sz >= bytesNeeded) //we good
            { 
                newSpace = mm.length + ((bytesNeeded/sz))*sz;
                spaceAdded = ((bytesNeeded / sz)) * sz;
            }
            else 
            {
                newSpace = mm.length + ((bytesNeeded/sz)+1)*sz;
                spaceAdded = ((bytesNeeded / sz)) * sz;
            }
            FreeBlock old = find(count);
            FreeBlock fu = new FreeBlock(leftover + spaceAdded, count);
            freeList.remove(old);
            freeList.insert(fu);
            for (int i = 0; i < freeList.length() && !found; i++)
            {
                if (bytesNeeded <= freeList.get(i).sz)
                {

                    FreeBlock f1 = freeList.get(i);
                    if (freeList.get(i).sz + freeList.get(i).p != mm.length)
                    {
                        FreeBlock f2 = find(freeList.get(i).p + freeList.get(i).sz);
                        FreeBlock f3 = new FreeBlock((f1.sz - bytesNeeded) + f2.sz, count + bytesNeeded);
                        freeList.remove(f1);
                        freeList.remove(f2);
                        freeList.insert(f3);
                        found = true;
                    }
                    else 
                    {
                        FreeBlock f4 = new FreeBlock(f1.sz - bytesNeeded, count + bytesNeeded);
                        freeList.remove(f1);
                        freeList.insert(f4);
                        found = true;
                    } 
                    //found = true;
                }
            }
            //if (found)
            //{
                found = false;
                byte[] nu = new byte[mm.length + Math.max(sz, bytesNeeded)];
                System.arraycopy(mm, 0, nu, 0, mm.length);
                mm = nu;  
                ByteBuffer.wrap(mm).putShort(/*end*/count, (short) b.length);
                System.arraycopy(b, 0, mm, count/*end*/ + 2, b.length);
                //disk.write(b, b.length + 2, b.length);
                position = count/*end*/ + 2;
                //bp.write(disk, bytesNeeded, leftover, mm);
                count = count + bytesNeeded; 
            //}
        } //every time you add something you need to update the freeblock
        return position;
    } //look at the last free block if it's longer than what u need
    //grab what you need and then leave the rest -> pull off bytes from the front
    //if what you want is bigger than what you want take out small and give bigger
    //when you grow the header node, have to update -> have to give back the block 
    //that was using the header but never use it
    //can be done in adjust head, so take the handle for the old header 
    //and tell mm to delete it and then insert the new one

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
        ByteBuffer.wrap(mm).putShort(h - 2, (short) b.length);
        System.arraycopy(b, 0, mm, h, b.length);
        //bp.write(disk, b.length, h, b); //array of data to write in
    }


    /**
     * Find the free block
     * @param x the handle
     * @return the freeblock
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
     * To close the file
     * @throws IOException
     */
    public void quit() throws IOException
    {
        disk.close();
    }
} //don't need to connect it to BP for milestone 2
