import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
//On my honor: 
// 
// - I have not used source code obtained from another student, 
// or any other unauthorized source, either modified or 
// unmodified. 
// 
// - All source code and documentation used in my program is 
// either my original work, or was derived by me from the 
// source code published in the textbook for this course. 
// 
// - I have not discussed coding details about this project with 
// anyone other than my partner (in the case of a joint 
// submission), instructor, ACM/UPE tutors or the TAs assigned 
// to this course. I understand that I may discuss the concepts 
// of this program with other students, and that another student 
// may help me debug my program so long as neither of us writes 
// anything during the discussion or modifies any computer file 
// during the discussion. I have violated neither the spirit nor 
// letter of this restriction.
//
// Jazmine Zurita and Jessica McCready

/**
 * A cumulative program
 */

/**
 * The class containing the main method, the entry point
 * of the application.
 *
 * @author Jazmine Zurita and Jessica McCready
 * @version April 13, 2016
 */
public class RectangleDisk {
	/**
	 * To keep track of buffSize
	 */
	public static int bufSize;
	 
	/**
	 * The data file
	 */
	public static RandomAccessFile dfile;
	
	/**
	 * To keep track of the number of buffers
	 */
	public static int numBuffs;

    /**
     * The entry point for the application.
     *
     * @param args The command line arguments.
     * @throws IOException 
     */
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        if (args.length != 4) 
        {
            System.out.println("Usage: RectangleDisk <commandfile> "
                + "<diskFile> <numBuffs> <buffSize>");
        }
        else 
        {
            System.out.println("Found expected parameter list.");
            
            dfile = new RandomAccessFile(args[1], "rw");
            numBuffs = Integer.parseInt(args[2]);              
            bufSize = Integer.parseInt(args[3]);
            String input = args[0].trim(); 
            File f = new File(input);
            CommandProcessor cmd = new CommandProcessor(f);
            dfile.close();
        }
    }
}
