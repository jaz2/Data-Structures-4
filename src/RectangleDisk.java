import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

/**
 * { your description of the project here }
 */

/**
 * The class containing the main method, the entry point
 * of the application.
 *
 * @author { your name here }
 * @version { put something here }
 */
public class RectangleDisk {
	/**
	 * To keep track of buffSize
	 */
	public static int bufSize;

    /**
     * The entry point for the application.
     *
     * @param args The command line arguments.
     * @throws IOException 
     */
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        if (args.length != 4) {
            System.out.println("Usage: RectangleDisk <commandfile> "
                + "<diskFile> <numBuffs> <buffSize>");
        }
        else {
            System.out.println("Found expected parameter list.");
            String input = args[0].trim();
            RandomAccessFile disk = new RandomAccessFile(args[1], "rw");
            disk.setLength(0);
            int numBuffs = Integer.parseInt(args[2]);
            
            File f = new File(input);
            bufSize = Integer.parseInt(args[3]);
            CommandProcessor cmd = new CommandProcessor(f);            
            MemoryManager m = new MemoryManager(bufSize);
           // disk.close();
        }
    }
} //SkipNode{ int Element, int[] found;}
