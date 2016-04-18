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
    
    public void insert(byte x)
    {
    	
    }
} //don't need to connect it to BP for milestone 2
