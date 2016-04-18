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
	 * FreeList for FreeBlocks
	 */
	public List freeList;

	/**
	 * Keeps track of size
	 */
	public int sz;

	/**
	 * The constructor
	 * @param size the buffer size
	 */
	public MemoryManager(int size)
	{
		mm = new byte[size];
		sz = size;
		freeList = new List();
		FreeBlock fb = new FreeBlock(size, 0);
		freeList.insert(fb);
	} //for second milestone just create an array

	/**
	 * Inserts into the memory manager array 
	 * @param b the byte array given
	 */
	public int insert(byte[] b)
	{
		boolean found = false;
		int position = 0;
		for (int i = 0; i <= freeList.length() && found == false; i++)
		{
			if (b.length <= freeList.get(i).sz)
			{
				found = true;
				position = freeList.get(i).p;
				ByteBuffer.wrap(mm).putShort(freeList.get(i).p, (short) b.length);
				System.arraycopy(b, 0, mm, freeList.get(i).p + 2, b.length);

				FreeBlock f = new FreeBlock(freeList.get(i).sz - (b.length - 2), 
						freeList.get(i).p + b.length + 2);				
				freeList.remove(freeList.get(i));
				freeList.insert(f);
			}
		}
		if (found == false)
		{
			int endPos = freeList.get(freeList.length() - 1).p;
			int endSize = freeList.get(freeList.length() - 1).sz;
			//make more mem
			if (endPos + endSize == mm.length)
			{
				FreeBlock nu = new FreeBlock(sz + endSize, endPos);
				freeList.remove(freeList.get(freeList.length() - 1));
				freeList.insert(nu);
			}
			else
			{
				FreeBlock n2 = new FreeBlock(sz, mm.length);				
				freeList.insert(n2);
			}
			byte[] m2 = new byte[mm.length + sz];
			System.arraycopy(mm, 0, m2, 0, mm.length);
			mm = m2; 
			
			ByteBuffer.wrap(mm).putShort(freeList.get(freeList.length() - 1).p, (short) b.length);
			System.arraycopy(b, 0, mm, freeList.get(freeList.length() - 1).p + 2, b.length);
			position = freeList.get(freeList.length() - 1).p;
		}
		return position;
		//find the one that is greater than or equal to
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
