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
	
	/**
	 * The array to store freelist blocks
	 */
	public byte[] mm;

    /**
     * The constructor
     * @param size the buffer size
     */
    public MemoryManager(int size)
    {
    	mm = new byte[size];      
    } //for second milestone just create an array
    
    /**
     * Inserts into the memory manager array 
     * @param b the byte array given
     */
    public void insert(byte[] b)
    {
    	if () //find the one that is greater than or equal to
    }//extend memory to buffersize 
    //so at first just have 1 huge block
    //can start at the beginning
    //once you allocate, split the block to get the free block
    //can have a list that stores ints (or where it is and its size)
    //make a free block class
    
    /**
     * Gets the node at the position 
     * in the array
     * @param handle the handle given
     * @return the length of the message
     */
    public byte[] getNode(int handle)
    {
    	if (handle == SkipList.fly)
    	{
    		return null;
    	}	
    	short size = ByteBuffer.wrap(mm).getShort(handle - 2);
    	byte[] b = new byte[size];
    	System.arraycopy(mm, handle, b, 0, size);
    	return b;
    }
} //don't need to connect it to BP for milestone 2
