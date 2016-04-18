import java.io.IOException;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.File;

// -------------------------------------------------------------------------
/**
 * This class processes the files given and parses them If the information in
 * the file works with the Rectangle, it goes through the Database and prints
 * out the results. Otherwise it prints out that it could not be found.
 *
 * @author Jazmine Zurita and Jessica McCready
 * @version Jan 24, 2016
 */

public class CommandProcessor {
    /**
     * The input file
     */
    private Scanner in;

    /**
     * The database
     */
    public Database db; 

    /**
     * The constructor which takes in the file
     * 
     * @param file
     *            the file name
     * @throws FileNotFoundException
     */
    public CommandProcessor(File file) throws FileNotFoundException {
        in = new Scanner(file);
        db = new Database();
        readInput();
    }


    /*(n1 < 0 || n1 > 1024) || (n2 < 0 || n2 > 1024) ||*/
    /**
     * Reads through the input and selects the keywords to then send to the
     * database
     * 
     * @throws IOException
     */
    public void readInput() { // x + h is greater than 1024 or y + w greater?
        while (in.hasNext()) {
            String str = in.next();
            if (str.contains("insert")) { // check that it is in bounds
                String id = in.next().toString();
                int n1 = in.nextInt();
                int n2 = in.nextInt();
                int n3 = in.nextInt();
                int n4 = in.nextInt();
                if (n1 < 0 || n2 < 0)
                {
                    System.out.println(
                            "Rectangle rejected: (" + id + ", " + n1 + ", " 
                                    + n2 + ", " + n3 + ", " + n4 + ")");
                }
                else if (((n3 <= 0 || n3 > 1024) /*|| (n4 <= 0 || n4 > 1024))
                        || (n1 + n3 > 1024 || n1 + n3 < 0 || n2 + n4 > 1024 
                                || n2 + n4 < 0)*/)) {
                    System.out.println(
                            "Rectangle rejected: (" + id + ", " + n1 + ", " 
                                    + n2 + ", " + n3 + ", " + n4 + ")");
                } 
                else if (n4 <= 0 || n4 > 1024)
                {
                    System.out.println(
                            "Rectangle rejected: (" + id + ", " + n1 + ", " 
                                    + n2 + ", " + n3 + ", " + n4 + ")");
                }
                else if (n1 + n3 > 1024 /*|| n1 + n3 < 0 */)
                {
                    System.out.println(
                            "Rectangle rejected: (" + id + ", " + n1 + ", " 
                                    + n2 + ", " + n3 + ", " + n4 + ")");
                }
                else if (n2 + n4 > 1024 /*|| n2 + n4 < 0*/)
                {
                    System.out.println(
                            "Rectangle rejected: (" + id + ", " + n1 + ", " 
                                    + n2 + ", " + n3 + ", " + n4 + ")");
                }
                else // if in bounds
                {
                    db.skipInsert(id, n1, n2, n3, n4);
                    System.out.println(
                            "Rectangle inserted: (" + id + ", " + n1 + ", " 
                                    + n2 + ", " + n3 + ", " + n4 + ")");
                }
            } 
            else if (str.contains("remove")) // two cases for remove
            { // check that it is in bounds

                if (in.hasNextInt() == true) // params
                {
                    int n1 = in.nextInt();
                    int n2 = in.nextInt();
                    int n3 = in.nextInt();
                    int n4 = in.nextInt();
                    if (n1 < 0 || n2 < 0)
                    {
                        System.out.println(
                                "Rectangle rejected: (" + n1 + ", " 
                                        + n2 + ", " + n3 + ", " + n4 + ")");
                    }
                    else if (((n3 <= 0 || n3 > 1024) 
                            /*|| (n4 <= 0 || n4 > 1024))
                            || (n1 + n3 > 1024 || n1 + n3 < 0 || n2 + n4 > 1024 
                                    || n2 + n4 < 0)*/)) {
                        System.out.println(
                                "Rectangle rejected: (" + n1 + ", " 
                                        + n2 + ", " + n3 + ", " + n4 + ")");
                    } 
                    else if (n4 <= 0 || n4 > 1024)
                    {
                        System.out.println(
                                "Rectangle rejected: (" + n1 + ", " 
                                        + n2 + ", " + n3 + ", " + n4 + ")");
                    }
                    else if (n1 + n3 > 1024 /*|| n1 + n3 < 0 */)
                    {
                        System.out.println(
                                "Rectangle rejected: (" + n1 + ", " 
                                        + n2 + ", " + n3 + ", " + n4 + ")");
                    }
                    else if (n2 + n4 > 1024 /*|| n2 + n4 < 0*/)
                    {
                        System.out.println(
                                "Rectangle rejected: (" + n1 + ", " 
                                        + n2 + ", " + n3 + ", " + n4 + ")");
                    }
                    else 
                    {
                        db.skipRemoveCoord(n1, n2, n3, n4);
                    }
                } 
                else // case where it's remove name
                {
                    String id = in.next().toString();
                    db.skip.removeByName(id);
                }
            } 
            else if (str.contains("regionsearch"))
            {  
                int n1 = in.nextInt();
                int n2 = in.nextInt();
                int n3 = in.nextInt();
                int n4 = in.nextInt();               
                if (n3 <= 0 || n4 <= 0)
                {
                    System.out.println("Rectangle rejected: (" + n1 + ", " + n2
                            + ", " + n3    + ", " + n4 + ")");               
                }
                else 
                {
                    db.skip.regionsearch(n1, n2, n3, n4); 
                }
            }
            else if (str.contains("intersections")) {
                System.out.println("Intersection pairs: ");
                db.skip.intersections();
            } 
            else if (str.contains("search")) {
                String id = in.next().toString();
                db.skip.search(id);
            } 
            else if (str.contains("dump")) {
                db.skip.dump();
            }
        }
    }
}
