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

    public BufferPool bp;


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
    //    {
    //    
    //        mm = new byte[size];
    //        sz = size;
    //        fly = -1;
    //        freeList = new List();
    //        fb = new FreeBlock(size, 0);
    //        freeList.insert(fb);
    //        count = 0;
    //        //found = false;
    //    }
    //
    //    /**
    //     * Inserts into the memory manager array 
    //     * @param b the byte array given
    //     * @return the handle
    //     * @throws IOException 
    //     */
    //    public int insert(byte[] b) throws IOException
    //    {
    //        found = false;
    //        //int end = mm.length;
    //        int position = 0;
    //        if (b == null)
    //        {
    //            System.out.println("Error");
    //            return fly; // return null handle
    //        }
    //        int bytesNeeded = b.length + 2;
    //        /*if (count + bytesNeeded <= mm.length)*/
    //        //        {               
    //        //disk.write(b, b.length + 2, b.length);
    //        //where ever it ends, update those two bytes to be a new block
    //        for (int i = 0; i < freeList.length() && !found; i++)
    //        {
    //            if (bytesNeeded <= freeList.get(i).sz)
    //            {                    
    //                FreeBlock f1 = freeList.get(i);
    //                found = true;
    //                if (freeList.get(i).sz + freeList.get(i).p != mm.length)
    //                {
    //                    //FreeBlock f2 = find(freeList.get(i).p + freeList.get(i).sz);
    //                    position = freeList.get(i).p + 2;
    //                    FreeBlock f3 = new FreeBlock((f1.sz - bytesNeeded), f1.p + bytesNeeded);
    //                    freeList.remove(f1);
    //                    //freeList.remove(f2);
    //                    freeList.insert(f3);
    //                    //found = true;
    //                    
    //                   // found = false;
    //                    //bp.write(disk, bytesNeeded, count, mm);
    //                    ByteBuffer.wrap(mm).putShort(count, (short) b.length);
    //                    System.arraycopy(b, 0, mm, position, b.length);
    //                    //position = count + 2; 
    //                    //count += bytesNeeded;
    //                }
    //                else 
    //                {
    //                    FreeBlock f4 = new FreeBlock(f1.sz - bytesNeeded, count + bytesNeeded);
    //                    freeList.remove(f1);
    //                    freeList.insert(f4);
    //                    //found = true;
    //                    //found = false;
    //                    //bp.write(disk, bytesNeeded, count, mm);
    //                    ByteBuffer.wrap(mm).putShort(count, (short) b.length);
    //                    System.arraycopy(b, 0, mm, count + 2, b.length);
    //                    position = count + 2; 
    //                    count += bytesNeeded;
    //                }  
    //                //found = true;
    //            }
    //        }
    ////        if (found)
    ////        {
    ////            found = false;
    ////            //bp.write(disk, bytesNeeded, count, mm);
    ////            ByteBuffer.wrap(mm).putShort(count, (short) b.length);
    ////            System.arraycopy(b, 0, mm, count + 2, b.length);
    ////            position = count + 2; 
    ////            count += bytesNeeded;
    ////        }
    //        //if u have 300, and take out 200,you get left with 100
    //        // }
    //        if (!found)
    //        { 
    //            int newSpace = 0;
    //            int leftover = mm.length - count;
    //            int spaceAdded = 0;
    //            if (leftover + ((bytesNeeded / sz)) * sz >= bytesNeeded) //we good
    //            { 
    //                newSpace = mm.length + ((bytesNeeded/sz))*sz;
    //                spaceAdded = ((bytesNeeded / sz)) * sz;
    //            }
    //            else 
    //            {
    //                newSpace = mm.length + ((bytesNeeded/sz)+1)*sz;
    //                spaceAdded = ((bytesNeeded / sz)) * sz;
    //            }
    //            byte[] nu = new byte[mm.length + Math.max(sz, bytesNeeded)];
    //            System.arraycopy(mm, 0, nu, 0, mm.length);
    //            mm = nu;  
    //            
    //            FreeBlock old = find(count);
    //            FreeBlock fu = new FreeBlock(leftover + spaceAdded, count);
    //            freeList.remove(old);
    //            freeList.insert(fu);
    //            for (int i = 0; i < freeList.length() && !found; i++)
    //            {
    //                if (bytesNeeded <= freeList.get(i).sz)
    //                {
    //
    //                    FreeBlock f1 = freeList.get(i);
    //                    if (freeList.get(i).sz + freeList.get(i).p != mm.length)
    //                    {
    //                        FreeBlock f2 = find(freeList.get(i).p + freeList.get(i).sz);
    //                        FreeBlock f3 = new FreeBlock((f1.sz - bytesNeeded) + f2.sz, count + bytesNeeded);
    //                        freeList.remove(f1);
    //                        freeList.remove(f2);
    //                        freeList.insert(f3);
    //                        found = true;
    //                    }
    //                    else 
    //                    {
    //                        FreeBlock f4 = new FreeBlock(f1.sz - bytesNeeded, count + bytesNeeded);
    //                        freeList.remove(f1);
    //                        freeList.insert(f4);
    //                       // found = true;
    //                    } 
    //                    //found = true;
    //                }
    //            }
    //            //if (found)
    //            //{
    //              //  found = false;
    //                
    //                ByteBuffer.wrap(mm).putShort(count, (short) b.length);
    //                System.arraycopy(b, 0, mm, count + 2, b.length);
    //                //disk.write(b, b.length + 2, b.length);
    //                position = count + 2;
    //                //bp.write(disk, bytesNeeded, leftover, mm);
    //                count = count + bytesNeeded; 
    //            //}
    //        } //every time you add something you need to update the freeblock
    //        return position;
    //    } //look at the last free block if it's longer than what u need
    //    //grab what you need and then leave the rest -> pull off bytes from the front
    //    //if what you want is bigger than what you want take out small and give bigger
    //    //when you grow the header node, have to update -> have to give back the block 
    //    //that was using the header but never use it
    //    //can be done in adjust head, so take the handle for the old header 
    //    //and tell mm to delete it and then insert the new one
    //
    //    /**
    //     * Gets the node at the position 
    //     * in the array
    //     * @param handle the handle given
    //     * @return the length of the message
    //     */
    //    public byte[] getNode(int handle)
    //    {
    //        if (handle == fly)
    //        {
    //            return null;
    //        }    
    //        short size = ByteBuffer.wrap(mm).getShort(handle - 2);
    //        byte[] b = new byte[size];
    //        System.arraycopy(mm, handle, b, 0, size);
    //        return b;
    //    }

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
                if (freeList.get(i).p + freeList.get(i).sz != end) //not at end
                {
                    pos = freeList.get(i).p + 2;
                    FreeBlock f3 = new FreeBlock((f1.sz - bytesNeeded), f1.p + bytesNeeded);
                    freeList.remove(f1);
                    //    freeList.remove(f2);
                    freeList.insert(f3);

                    byte[] a = new byte[2];                
                    ByteBuffer.wrap(a).putShort(0, (short) b.length);

                    bp.write(f, 2, pos - 2, a);
                    bp.write(f, b.length, pos, b);
                }
                else //at end
                {
                    FreeBlock f4 = new FreeBlock((f1.sz - bytesNeeded), count + bytesNeeded);
                    freeList.remove(f1);
                    freeList.insert(f4);                    

                    byte[] a = new byte[2];                
                    ByteBuffer.wrap(a).putShort(0, (short) b.length);

                    bp.write(f, 2, count, a);
                    bp.write(f, b.length, count + 2, b);
                    pos = count + 2;
                    count = count + b.length + 2;
                }              
            }
        }
        if (found == false)  //make more mem 
        {
            //int newSpace = 0;
            int spaceAdded = 0;
            int leftover = end - count;
            if(leftover + ((bytesNeeded/bufSize))*bufSize >= bytesNeeded) //round buff number down
            { 
              //  newSpace = end + ((bytesNeeded/bufSize)*bufSize);
                spaceAdded = ((bytesNeeded/bufSize)*bufSize);
            }
            else
            {
               // newSpace = end + (((bytesNeeded/bufSize)+1)*bufSize);
                spaceAdded = (((bytesNeeded/bufSize)+1)*bufSize);

            }
            //            byte[] newm = new byte[newSpace];
            //            System.arraycopy(m, 0, newm, 0, end);
            //            
            //            m = newm;
            FreeBlock fold = find(count);
            FreeBlock fnew = new FreeBlock(leftover + spaceAdded, count);
            freeList.remove(fold);
            freeList.insert(fnew);
            end = end + spaceAdded;
            //inserts 
            for (int i = 0; i < freeList.length() && !found; i++)
            {
                if (bytesNeeded <= freeList.get(i).sz)
                {
                    found = true;
                    FreeBlock f1 = freeList.get(i);
                    if (freeList.get(i).p + freeList.get(i).sz != end) //not at end
                    {

                        FreeBlock f2 = find(f1.p + f1.sz);
                        if (f2 == null) System.out.println("kill");
                        FreeBlock f3 = new FreeBlock((f1.sz - bytesNeeded) + f2.sz, count + bytesNeeded);
                        freeList.remove(f1);
                        freeList.remove(f2);
                        freeList.insert(f3);
                    }
                    else //at end
                    {
                        FreeBlock f4 = new FreeBlock((f1.sz - bytesNeeded), count + bytesNeeded);
                        freeList.remove(f1);
                        freeList.insert(f4);
                    }                
                }
            }
            byte[] a = new byte[2];                
            ByteBuffer.wrap(a).putShort(0, (short) b.length);

            bp.write(f, 2, count, a);
            bp.write(f, b.length, count + 2, b);
            pos = count + 2;
            count = count + b.length + 2;
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
        for(int i = 0; i < freeList.length(); i++)
        {
            if (freeList.get(i).p == x)
            {
                return freeList.get(i);
            }
        }
        return null;
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
//
//
//    /**
//     * Find the free block
//     * @param x the handle
//     * @return the freeblock
//     */
//    public FreeBlock find(int x)
//    {
//        for (int i = 0; i < freeList.length(); i++)
//        {
//            if (freeList.get(i).p == x)
//            {
//                return freeList.get(i);
//            }
//        }
//        return null;
//    }
//
//    /**
//     * To close the file
//     * @throws IOException
//     */
//    public void quit() throws IOException
//    {
//        disk.close();
//    }
//} //don't need to connect it to BP for milestone 2
