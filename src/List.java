

/**
 * 
 */

/**
 * @author Jazmine Zurita and Jessica McCready
 * @version Feb 22 2016
 *
 */
public class List {

	private int len;
	private int size;
	private FreeBlock[] l;

	/**
	 * Constructor for the List
	 */
	public List()
	{
		l = new FreeBlock[3];
		len = 3;
		size = 0;
	}

	/**
	 * Inserts and sorts p
	 * @param p the freeblock
	 */
	public void insert(FreeBlock p)
	{
		makeMoreMem();
		boolean found = false;
		if (size == 0)
		{
			found = true;
			l[size] = p;
			size++;
		}
		else 
		{
			for (int i = 0; i < size && !found; i++)
			{

				if (p.p < l[i].p)
				{
					found = true;
					FreeBlock next = l[i];
					l[i] = p;
					//size++;
					for (int j = size; j > i + 1; j--)
					{
						l[j] = l[j - 1];						
					}
					l[i + 1] = next;
					size++;
				}
			}
			if (!found)
			{
				l[size] = p;
				size++;
			}
		}
	}
	
	/**
	 * Makes more memory for insert
	 */
	public void makeMoreMem()
	{
		if (size != 0 && size == len)
		{
			len = len * 2; //change 
			FreeBlock[] n = new FreeBlock[len];
			for (int i = 0; i < size; i++)
			{
				n[i] = l[i];
			}
			l = n;
		}
	}

//	/**
//	 * Inserts into the list
//	 * @param p the FreeBlock
//	 */
//	private void insert1(FreeBlock p)
//	{
//		if (size != 0 && size == len)
//		{
//			len = len * 2; //change 
//			FreeBlock[] n = new FreeBlock[len];
//			for (int i = 0; i < len / 2; i++)
//			{
//				n[i] = l[i];
//			}
//			n[size] = p;
//			l = n;
//			size++;
//		}
//		else 
//		{
//			l[size] = p;
//			size++;
//		}
//	}

	/**
	 * Moves to this position
	 * @param i the position 
	 * @return the FreeBlock at the pos
	 */
	public FreeBlock get(int i)
	{
		return l[i];
	}

	//    /**
	//     * Finds the name of the FreeBlock
	//     * @param name the name 
	//     * @param p the FreeBlock
	//     * @return the string
	//     */
	//    public String getName(String name, FreeBlock p)
	//    {
	//        if (name == null) //remove by coord
	//        {
	//            String s = null;
	//            for (int i = 0; i < size; i++)
	//            {
	//                if (l[i].equals(p))
	//                {
	//                    //s = l[i].name;
	//                    remove(l[i]);
	//                    //System.out.println("removed");
	//                    return s;
	//                }
	//            }
	//            return null;
	//        }    
	//        else //remove by name
	//        {
	//            boolean removed = false;
	//            int i = 0;
	//            while (i < size && !removed) 
	//                //ft i=size and not removed -> not there 
	//                //ff i =size and removed -> last item removed
	//                //tt not at end and not removed -> not there
	//                //tf not at end and removed -> removed in middle!!!
	//            {        
	//                if (l[i].equals(p) /*&& l[i].getName().equals(name)*/)
	//                {
	//                    remove(l[i]); 
	//                    //System.out.println("removed");
	//                    removed = true;
	//                }
	//                i++;
	//            }
	//        }
	//        return null;
	//    }

	/**
	 * Removes a FreeBlock from the list
	 * @param p the FreeBlock
	 * @return true or false
	 */
	public boolean remove(FreeBlock p)
	{
		Boolean found = false;
		for (int i = 0; i < size && !found; i++)
		{
			if (l[i].equals(p) /*&& (l[i].getName() == p.getName() 
                    /*|| l[i].getName().equals(p.getName()))*/)
			{
				if (i == size - 1)
				{
					size--;
				}
				else 
				{
					for (int j = i; j < size - 1; j++)
					{
						l[j] = l[j + 1];
					}
					size--;
				}
				found = true;
			}
		}
		return found;
	}

	/**
	 * Dumps apparently
	 * @return string
	 */
	public String dump()
	{
		String s = "";
		for (int i = 0; i < l.length; i++)
		{
			System.out.println(l[i].p);
		}
		return s;
	}

	/**
	 * Returns the size
	 * @return the size
	 */
	public int length()
	{
		return size;
	}

	/**
	 * Returns true if size is 0,
	 * false otherwise
	 * @return t or f
	 */
	public boolean isEmpty()
	{
		return size == 0;
	}
}
