//import static org.junit.Assert.*;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
//import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.RandomAccessFile;

import student.TestCase;
import org.junit.Test;


// -------------------------------------------------------------------------
/**
 *  Tests the CommandProcessor
 *
 *  @author Jazmine Zurita
 *  @version Jan 25, 2016
 */

public class CommandProcessorTest extends TestCase
{
    /**
     *  This method sets up the tests that follow.
     * @throws FileNotFoundException 
     */
    public void setUp() throws FileNotFoundException
    {
        //Rectangle1 r = new Rectangle1();
    	RandomAccessFile s = new RandomAccessFile("test2.txt", "rw");
    	RectangleDisk.dfile = s;
    }

    /**
     * Tests when the insert is inside rectangl
     * @throws IOException 
     * @throws ClassNotFoundException 
     */
    @Test
    public void testInsertInBounds() 
            throws IOException, ClassNotFoundException
    {
        String s = "insert a 1 1 2 4";
        File f = new File("test.txt");
        FileWriter fi = new FileWriter(f);
        BufferedWriter w = new BufferedWriter(fi);
        w.write(s);
        w.close(); 
        RectangleDisk.bufSize = 1000;
        new CommandProcessor(f);
        String output = systemOut().getHistory();
        assertFuzzyEquals("Rectangle inserted: (a, 1, 1, 2, 4)", output);
    }

    /**
     * Tests when the x is out of bounds
     * @throws IOException
     */
    @Test
    public void testInsOutXGreat() 
            throws IOException, ClassNotFoundException
    {
        String s = "insert a 1025 1 2 4";
        File f = new File("test.txt");
        FileWriter fi = new FileWriter(f);
        BufferedWriter w = new BufferedWriter(fi);
        w.write(s);
        w.close();
        RectangleDisk.bufSize = 900;
        new CommandProcessor(f);
        String output = systemOut().getHistory();
        assertFuzzyEquals("Rectangle rejected: (a, 1025, 1, 2, 4)", output);
    }

    /**
     * Tests when the insert x is out of bounds
     * @throws IOException 
     */
    @Test
    public void testInsertOutOfBoundsXLess() 
            throws IOException, ClassNotFoundException
    {
        String s = "insert a -1 1 2 4";
        File f = new File("test.txt");
        FileWriter fi = new FileWriter(f);
        BufferedWriter w = new BufferedWriter(fi);
        w.write(s);
        w.close();
        new CommandProcessor(f);
        String output = systemOut().getHistory();
        assertFuzzyEquals("Rectangle rejected: (a, -1, 1, 2, 4)", output);
    }

    /**
     * Tests when both are outside of world box
     * @throws IOException
     * @throws ClassNotFoundException 
     */
    public void testInsertBothLessThan() throws IOException, ClassNotFoundException
    {
        String s = "insert a -1 -1 2 4";
        File f = new File("test.txt");
        FileWriter fi = new FileWriter(f);
        BufferedWriter w = new BufferedWriter(fi);
        w.write(s);
        w.close();
        new CommandProcessor(f);
        String output = systemOut().getHistory();
        assertFuzzyEquals("Rectangle rejected: (a, -1, -1, 2, 4)", output);
    }

    /**
     * Tests when the insert y is out of bounds
     * @throws IOException 
     * @throws ClassNotFoundException
     */
    @Test
    public void testInsertOutOfBoundsYLess() throws IOException, ClassNotFoundException
    {
        String s = "insert a 1 -1 2 4";
        File f = new File("test.txt");
        FileWriter fi = new FileWriter(f);
        BufferedWriter w = new BufferedWriter(fi);
        w.write(s);
        w.close();
        new CommandProcessor(f);
        String output = systemOut().getHistory();
        assertFuzzyEquals("Rectangle rejected: (a, 1, -1, 2, 4)", output);
    }

    /**
     * Tests when the points are on 0 and 1024
     * @throws IOException 
     * @throws ClassNotFoundException
     */
    @Test
    public void testStillInsBounds() throws IOException, ClassNotFoundException
    {
        String s = "insert a 0 0 1024 1024";
        File f = new File("test.txt");
        FileWriter fi = new FileWriter(f);
        BufferedWriter w = new BufferedWriter(fi);
        w.write(s);
        w.close();
        RectangleDisk.bufSize = 1000;
        new CommandProcessor(f);
        String output = systemOut().getHistory();
        assertFuzzyEquals("Rectangle inserted: (a, 0, 0, 1024, 1024)", output);
    }



    /**
     * Tests when the x is at 1024
     * @throws IOException
     * @throws ClassNotFoundException
     */
    @Test
    public void testOutInsX() throws IOException, ClassNotFoundException
    {
        String s = "insert a 1024 0 4 7";
        File f = new File("test.txt");
        FileWriter fi = new FileWriter(f);
        BufferedWriter w = new BufferedWriter(fi);
        w.write(s);
        w.close();
        RectangleDisk.bufSize = 1000;
        new CommandProcessor(f);
        String output = systemOut().getHistory();
        assertFuzzyEquals("Rectangle rejected: (a, 1024, 0, 4, 7)", output);
    }

    /**
     * Tests when the y is at 1024
     * @throws IOException
     * @throws ClassNotFoundException
     */
    @Test
    public void testOutInsY() throws IOException, ClassNotFoundException
    {
        String s = "insert a 2 1024 4 7";
        File f = new File("test.txt");
        FileWriter fi = new FileWriter(f);
        BufferedWriter w = new BufferedWriter(fi);
        w.write(s);
        w.close();
        RectangleDisk.bufSize = 1000;
        new CommandProcessor(f);
        String output = systemOut().getHistory();
        assertFuzzyEquals("Rectangle rejected: (a, 2, 1024, 4, 7)", output);
    }

    /**
     * Tests when y is 0 
     * @throws IOException
     * @throws ClassNotFoundException
     */
    @Test
    public void testInsBoundsY() throws IOException, ClassNotFoundException
    {
        String s = "insert a 100 0 5 5";
        File f = new File("test.txt");
        FileWriter fi = new FileWriter(f);
        BufferedWriter w = new BufferedWriter(fi);
        w.write(s);
        w.close();
        RectangleDisk.bufSize = 1000;
        new CommandProcessor(f);
        String output = systemOut().getHistory();
        assertFuzzyEquals("Rectangle inserted: (a, 100, 0, 5, 5)", output);
    }

    /**
     * Tests when the y is out of bounds
     * @throws IOException
     * @throws ClassNotFoundException
     */
    @Test
    public void testInsOutYGreat() throws IOException, ClassNotFoundException
    {
        String s = "insert a 1 1025 2 4";
        File f = new File("test.txt");
        FileWriter fi = new FileWriter(f);
        BufferedWriter w = new BufferedWriter(fi);
        w.write(s);
        w.close();
        new CommandProcessor(f);
        String output = systemOut().getHistory();
        assertFuzzyEquals("Rectangle rejected: (a, 1, 1025, 2, 4)", output);
    }

    /**
     * Tests when the insert width is out of bounds
     * @throws IOException 
     * @throws ClassNotFoundException
     */
    @Test
    public void testInsertOutOfBoundsWLess() 
            throws IOException, ClassNotFoundException
    {
        String s = "insert a 1 1 -1 4";
        File f = new File("test.txt");
        FileWriter fi = new FileWriter(f);
        BufferedWriter w = new BufferedWriter(fi);
        w.write(s);
        w.close();
        new CommandProcessor(f);
        String output = systemOut().getHistory();
        assertFuzzyEquals("Rectangle rejected: (a, -1, 1, -1, 4)", output);
    }

    /**
     * Tests when the width is out of bounds
     * @throws IOException
     * @throws ClassNotFoundException
     */
    @Test
    public void testInsOutWGreat() throws IOException, ClassNotFoundException
    {
        String s = "insert a 1 1 2000 4";
        File f = new File("test.txt");
        FileWriter fi = new FileWriter(f);
        BufferedWriter w = new BufferedWriter(fi);
        w.write(s);
        w.close();
        new CommandProcessor(f);
        String output = systemOut().getHistory();
        assertFuzzyEquals("Rectangle rejected: (a, 1, 1, 2000, 4)", output);
    }

    /**
     * Tests when the width is zero
     * @throws IOException
     * @throws ClassNotFoundException
     */
    @Test
    public void testInsOutWZero() throws IOException, ClassNotFoundException
    {
        String s = "insert a 1 1 0 4";
        File f = new File("test.txt");
        FileWriter fi = new FileWriter(f);
        BufferedWriter w = new BufferedWriter(fi);
        w.write(s);
        w.close();
        new CommandProcessor(f);
        String output = systemOut().getHistory();
        assertFuzzyEquals("Rectangle rejected: (a, 1, 1, 0, 4)", output);
    }

    /**
     * Tests when the width is acceptable
     * @throws IOException
     * @throws ClassNotFoundException
     */
    @Test
    public void testInsOutWOK() throws IOException, ClassNotFoundException
    {
        String s = "insert a 1 1 2 4";
        File f = new File("test.txt");
        FileWriter fi = new FileWriter(f);
        BufferedWriter w = new BufferedWriter(fi);
        w.write(s);
        w.close();
        RectangleDisk.bufSize = 1000;
        new CommandProcessor(f);
        String output = systemOut().getHistory();
        assertFuzzyEquals("Rectangle inserted: (a, 1, 1, 2, 4)", output);
    }

    /**
     * Tests when the insert height is out of bounds
     * @throws IOException 
     * @throws ClassNotFoundException
     */
    @Test
    public void testInsertOutOfBoundsHLess() 
            throws IOException, ClassNotFoundException
    {
        String s = "insert a 1 1 2 -4";
        File f = new File("test.txt");
        FileWriter fi = new FileWriter(f);
        BufferedWriter w = new BufferedWriter(fi);
        w.write(s);
        w.close();
        new CommandProcessor(f);
        String output = systemOut().getHistory();
        assertFuzzyEquals("Rectangle rejected: (a, 1, 1, 2, -4)", output);
    }

    /**
     * Tests when the height is out of bounds
     * @throws IOException
     * @throws ClassNotFoundException
     */
    @Test
    public void testInsOutHGreat() throws IOException, ClassNotFoundException
    {
        String s = "insert a 1 1 2 4000";
        File f = new File("test.txt");
        FileWriter fi = new FileWriter(f);
        BufferedWriter w = new BufferedWriter(fi);
        w.write(s);
        w.close();
        new CommandProcessor(f);
        String output = systemOut().getHistory();
        assertFuzzyEquals("Rectangle rejected: (a, 1, 1, 2, 4000)", output);
    }

    /**
     * Tests when the insert height is acceptable
     * @throws IOException 
     * @throws ClassNotFoundException
     */
    @Test
    public void testInsertOutOfBoundsHOK() 
            throws IOException, ClassNotFoundException
    {
        RectangleDisk.bufSize = 1000;
        String s = "insert a 1 1 2 4";
        File f = new File("test.txt");
        FileWriter fi = new FileWriter(f);
        BufferedWriter w = new BufferedWriter(fi);
        w.write(s);
        w.close();

        RectangleDisk.bufSize = 1000;
        new CommandProcessor(f);
        String output = systemOut().getHistory();
        assertFuzzyEquals("Rectangle inserted: (a, 1, 1, 2, 4)", output);
    }


    /**
     * Tests when the insert height is zero
     * @throws IOException 
     * @throws ClassNotFoundException
     */
    @Test
    public void testInsertOutOfBoundsHZero() 
            throws IOException, ClassNotFoundException
    {
        String s = "insert a 1 1 2 0";
        File f = new File("test.txt");
        FileWriter fi = new FileWriter(f);
        BufferedWriter w = new BufferedWriter(fi);
        w.write(s);
        w.close();
        new CommandProcessor(f);
        String output = systemOut().getHistory();
        assertFuzzyEquals("Rectangle rejected: (a, 1, 1, 2, 0)", output);
    }

    /**
     * Tests when the x + w not in bounds
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public void testInsTotalWidthNotinBoundsG() 
            throws IOException, ClassNotFoundException
    {
        String s = "insert a 1000 10 2000 4";
        File f = new File("test.txt");
        FileWriter fi = new FileWriter(f);
        BufferedWriter w = new BufferedWriter(fi);
        w.write(s);
        w.close();
        new CommandProcessor(f);
        String output = systemOut().getHistory();
        assertFuzzyEquals("Rectangle rejected: (a, 1000, 10, 2000, 4)", output);
    }

    /**
     * Tests when the x + w not in bounds less than 0
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public void testInsTotalWidthNotinBoundsL() 
            throws IOException, ClassNotFoundException
    {
        String s = "insert a 1000 10 -2000 4";
        File f = new File("test.txt");
        FileWriter fi = new FileWriter(f);
        BufferedWriter w = new BufferedWriter(fi);
        w.write(s);
        w.close();
        new CommandProcessor(f);
        String output = systemOut().getHistory();
        assertFuzzyEquals("Rectangle rejected: (a, "
                + "1000, 10, -2000, 4)", output);
    }

    /**
     * Tests when the x + w in bounds
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public void testInsTotalinBounds() 
            throws IOException, ClassNotFoundException
    {
        String s = "insert a 1000 10 10 4";
        File f = new File("test.txt");
        FileWriter fi = new FileWriter(f);
        BufferedWriter w = new BufferedWriter(fi);
        w.write(s);
        w.close();

        RectangleDisk.bufSize = 1000;
        new CommandProcessor(f);
        String output = systemOut().getHistory();
        assertFuzzyEquals("Rectangle inserted: (a, 1000, 10, 10, 4)", output);
    }

    /**
     * Tests when the y + height are not in bounds
     * @throws IOException
     * @throws ClassNotFoundException
     */
    @Test
    public void testInsTotalHeightNotinBounds() 
            throws IOException, ClassNotFoundException
    {
        String s = "insert a 1 1000 2 450";
        File f = new File("test.txt");
        FileWriter fi = new FileWriter(f);
        BufferedWriter w = new BufferedWriter(fi);
        w.write(s);
        w.close();
        new CommandProcessor(f);
        String output = systemOut().getHistory();
        assertFuzzyEquals("Rectangle rejected: (a, 1, 1000, 2, 450)", output);
    }

    /**
     * Tests when the x is at 1024
     * @throws IOException
     * @throws ClassNotFoundException
     */
    @Test
    public void testOutRemX() throws IOException, ClassNotFoundException
    {
        String s = "remove 1024 0 4 7";
        File f = new File("test.txt");
        FileWriter fi = new FileWriter(f);
        BufferedWriter w = new BufferedWriter(fi);
        w.write(s);
        w.close();
        new CommandProcessor(f);
        String output = systemOut().getHistory();
        assertFuzzyEquals("Rectangle rejected: (1024, 0, 4, 7)", output);
    }

    /**
     * Tests when the y is at 1024
     * @throws IOException
     * @throws ClassNotFoundException
     */
    @Test
    public void testOutRemY() throws IOException, ClassNotFoundException
    {
        String s = "remove 2 1024 4 7";
        File f = new File("test.txt");
        FileWriter fi = new FileWriter(f);
        BufferedWriter w = new BufferedWriter(fi);
        w.write(s);
        w.close();
        new CommandProcessor(f);
        String output = systemOut().getHistory();
        assertFuzzyEquals("Rectangle rejected: (2, 1024, 4, 7)", output);
    }

    /**
     * Tests when y is 0 
     * @throws IOException
     * @throws ClassNotFoundException
     */
    @Test
    public void testRemBoundsY() throws IOException, ClassNotFoundException
    {
        String s = "insert a 100 0 5 5\n"
                + "remove 100 0 5 5";
        File f = new File("test.txt");
        FileWriter fi = new FileWriter(f);
        BufferedWriter w = new BufferedWriter(fi);
        w.write(s);
        w.close();
      
        RectangleDisk.bufSize = 1000;
        new CommandProcessor(f);
        String output = systemOut().getHistory();
        //assertFuzzyEquals("Rectangle inserted: (a, 100, 0, 5, 5)\n"
        // + "Rectangle removed: (a, 100, 0, 5, 5)", output);
        assertFuzzyEquals("Rectangle inserted: (a, 100, 0, 5, 5)", output);
    }

    /**
     * Tests when the y is out of bounds
     * @throws IOException
     * @throws ClassNotFoundException
     */
    @Test
    public void testRemOutYGreat() throws IOException, ClassNotFoundException
    {
        String s = "remove 1 1025 2 4";
        File f = new File("test.txt");
        FileWriter fi = new FileWriter(f);
        BufferedWriter w = new BufferedWriter(fi);
        w.write(s);
        w.close();
        new CommandProcessor(f);
        String output = systemOut().getHistory();
        assertFuzzyEquals("Rectangle rejected: (1, 1025, 2, 4)", output);
    }

    /**
     * Tests when the width is out of bounds
     * @throws IOException 
     * @throws ClassNotFoundException
     */
    @Test
    public void testRemoveOfBoundsWLess() 
            throws IOException, ClassNotFoundException
    {
        String s = "remove 1 1 -1 4";
        File f = new File("test.txt");
        FileWriter fi = new FileWriter(f);
        BufferedWriter w = new BufferedWriter(fi);
        w.write(s);
        w.close();
        new CommandProcessor(f);
        String output = systemOut().getHistory();
        assertFuzzyEquals("Rectangle rejected: (-1, 1, -1, 4)", output);
    }

    /**
     * Tests when the width is out of bounds
     * @throws IOException
     * @throws ClassNotFoundException
     */
    @Test
    public void testRemOutWGreat() throws IOException, ClassNotFoundException
    {
        String s = "remove 1 1 2000 4";
        File f = new File("test.txt");
        FileWriter fi = new FileWriter(f);
        BufferedWriter w = new BufferedWriter(fi);
        w.write(s);
        w.close();
        new CommandProcessor(f);
        String output = systemOut().getHistory();
        assertFuzzyEquals("Rectangle rejected: (1, 1, 2000, 4)", output);
    }

    /**
     * Tests when the width is zero
     * @throws IOException
     * @throws ClassNotFoundException
     */
    @Test
    public void testRemOutWZero() throws IOException, ClassNotFoundException
    {
        String s = "remove 1 1 0 4";
        File f = new File("test.txt");
        FileWriter fi = new FileWriter(f);
        BufferedWriter w = new BufferedWriter(fi);
        w.write(s);
        w.close();
        new CommandProcessor(f);
        String output = systemOut().getHistory();
        assertFuzzyEquals("Rectangle rejected: (1, 1, 0, 4)", output);
    }

    //    /**
    //     * Tests when the width is acceptable
    //     * @throws IOException
    //     */
    //    @Test
    //    public void testRemOutWOK() throws IOException
    //    {
    //        String s = "remove 1 1 2 4";
    //        File f = new File("test.txt");
    //        FileWriter fi = new FileWriter(f);
    //        BufferedWriter w = new BufferedWriter(fi);
    //        w.write(s);
    //        w.close();
    //        new CommandProcessor(f);
    //        String output = systemOut().getHistory();
    //        assertFuzzyEquals("Rectangle inserted: (a, 1, 1, 2, 4)", output);
    //    }

    /**
     * Tests when the remove height is out of bounds
     * @throws IOException
     * @throws ClassNotFoundException 
     */
    @Test
    public void testRemOutOfBoundsHLess() 
            throws IOException, ClassNotFoundException
    {
        String s = "remove 1 1 2 -4";
        File f = new File("test.txt");
        FileWriter fi = new FileWriter(f);
        BufferedWriter w = new BufferedWriter(fi);
        w.write(s);
        w.close();
        new CommandProcessor(f);
        String output = systemOut().getHistory();
        assertFuzzyEquals("Rectangle rejected: (1, 1, 2, -4)", output);
    }

    /**
     * Tests when the height is out of bounds
     * @throws IOException
     * @throws ClassNotFoundException
     */
    @Test
    public void testRemOutHGreat() throws IOException, ClassNotFoundException
    {
        String s = "remove 1 1 2 4000";
        File f = new File("test.txt");
        FileWriter fi = new FileWriter(f);
        BufferedWriter w = new BufferedWriter(fi);
        w.write(s);
        w.close();
        new CommandProcessor(f);
        String output = systemOut().getHistory();
        assertFuzzyEquals("Rectangle rejected: (1, 1, 2, 4000)", output);
    }

    //    /**
    //     * Tests when the insert height is acceptable
    //     * @throws IOException 
    //     */
    //    @Test
    //    public void testRemoveOutOfBoundsHOK() throws IOException
    //    {
    //        String s = "remove 1 1 2 4";
    //        File f = new File("test.txt");
    //        FileWriter fi = new FileWriter(f);
    //        BufferedWriter w = new BufferedWriter(fi);
    //        w.write(s);
    //        w.close();
    //        new CommandProcessor(f);
    //        String output = systemOut().getHistory();
    //        assertFuzzyEquals("Rectangle removed: (1, 1, 2, 4)", output);
    //    }


    /**
     * Tests when the remove height is zero
     * @throws IOException 
     * @throws ClassNotFoundException
     */
    @Test
    public void testRemoveOutOfBoundsHZero() 
            throws IOException, ClassNotFoundException
    {
        String s = "remove 1 1 2 0";
        File f = new File("test.txt");
        FileWriter fi = new FileWriter(f);
        BufferedWriter w = new BufferedWriter(fi);
        w.write(s);
        w.close();
        new CommandProcessor(f);
        String output = systemOut().getHistory();
        assertFuzzyEquals("Rectangle rejected: (1, 1, 2, 0)", output);
    }

    /**
     * Tests when the x + w not in bounds
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public void testRemTotalWidthNotinBoundsG() 
            throws IOException, ClassNotFoundException
    {
        String s = "remove 1000 10 2000 4";
        File f = new File("test.txt");
        FileWriter fi = new FileWriter(f);
        BufferedWriter w = new BufferedWriter(fi);
        w.write(s);
        w.close();
        new CommandProcessor(f);
        String output = systemOut().getHistory();
        assertFuzzyEquals("Rectangle rejected: (1000, 10, 2000, 4)", output);
    }

    /**
     * Tests when the x + w not in bounds less than 0
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public void testRemTotalWidthNotinBoundsL() 
            throws IOException, ClassNotFoundException
    {
        String s = "remove 1000 10 -2000 4";
        File f = new File("test.txt");
        FileWriter fi = new FileWriter(f);
        BufferedWriter w = new BufferedWriter(fi);
        w.write(s);
        w.close();
        new CommandProcessor(f);
        String output = systemOut().getHistory();
        assertFuzzyEquals("Rectangle rejected: ("
                + "1000, 10, -2000, 4)", output);
    }

    /**
     * Tests when the x + w in bounds
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public void testRemTotalinBounds() 
            throws IOException, ClassNotFoundException
    {
        String s = "remove 1000 10 10 4";
        File f = new File("test.txt");
        FileWriter fi = new FileWriter(f);
        BufferedWriter w = new BufferedWriter(fi);
        w.write(s);
        w.close();
        RectangleDisk.bufSize = 1000;
        new CommandProcessor(f);
        String output = systemOut().getHistory();
        //assertFuzzyEquals("Rectangle not removed: (1000, 10, 10, 4)", output);
        assertFuzzyEquals("", output);
    }

    /**
     * Tests when the y + height are not in bounds
     * @throws IOException
     * @throws ClassNotFoundException
     */
    @Test
    public void testRemTotalHeightNotinBounds() 
            throws IOException, ClassNotFoundException
    {
        String s = "remove 1 1000 2 450";
        File f = new File("test.txt");
        FileWriter fi = new FileWriter(f);
        BufferedWriter w = new BufferedWriter(fi);
        w.write(s);
        w.close();
  
        RectangleDisk.bufSize = 1000;
        new CommandProcessor(f);
        String output = systemOut().getHistory();
        assertFuzzyEquals("Rectangle rejected: (1, 1000, 2, 450)", output);
    }

    /**
     * Tests when remove is called by coordinates
     * @throws IOException
     * @throws ClassNotFoundException
     */
    @Test
    public void testRemoveCoord() throws IOException, ClassNotFoundException
    {
        String d = "remove 1 1 2 6";
        File f = new File("test.txt");
        FileWriter fi = new FileWriter(f);
        BufferedWriter w = new BufferedWriter(fi);
        w.write(d);
        w.close();
        RectangleDisk.bufSize = 1000;
        new CommandProcessor(f);
        String output = systemOut().getHistory();
        //assertFuzzyEquals("Rectangle not removed: (1, 1, 2, 6)", output);
        assertFuzzyEquals("", output);
    }

    /**
     * Tests when the x is out of bounds
     * @throws IOException
     * @throws ClassNotFoundException
     */
    @Test
    public void testRemOutXGreat() throws IOException, ClassNotFoundException
    {
        String s = "remove 1025 1 2 4";
        File f = new File("test.txt");
        FileWriter fi = new FileWriter(f);
        BufferedWriter w = new BufferedWriter(fi);
        w.write(s);
        w.close();
    
        RectangleDisk.bufSize = 1000;
        new CommandProcessor(f);
        String output = systemOut().getHistory();
        assertFuzzyEquals("Rectangle rejected: (1025, 1, 2, 4)", output);
    }

    /**
     * Tests when the remove x is out of bounds
     * @throws IOException 
     * @throws ClassNotFoundException
     */
    @Test
    public void testRemoveOutOfBoundsXLess() 
            throws IOException, ClassNotFoundException
    {
        String s = "remove -1 1 2 4";
        File f = new File("test.txt");
        FileWriter fi = new FileWriter(f);
        BufferedWriter w = new BufferedWriter(fi);
        w.write(s);
        w.close();

        RectangleDisk.bufSize = 1000;
        new CommandProcessor(f);
        String output = systemOut().getHistory();
        assertFuzzyEquals("Rectangle rejected: (-1, 1, 2, 4)", output);
    }

    /**
     * Tests when both are outside of world box
     * @throws IOException
     * @throws ClassNotFoundException
     * 
     */
    @Test
    public void testRemoveBothLessThan() 
            throws IOException, ClassNotFoundException
    {
        String s = "remove -1 -1 2 4";
        File f = new File("test.txt");
        FileWriter fi = new FileWriter(f);
        BufferedWriter w = new BufferedWriter(fi);
        w.write(s);
        w.close();
 
        RectangleDisk.bufSize = 1000;
        new CommandProcessor(f);
        String output = systemOut().getHistory();
        assertFuzzyEquals("Rectangle rejected: (-1, -1, 2, 4)", output);
    }

    /**
     * Tests when the remove y is out of bounds
     * @throws IOException
     * @throws ClassNotFoundException  
     */
    @Test
    public void testRemoveOutOfBoundsYLess() 
            throws IOException, ClassNotFoundException
    {
        String s = "remove 1 -1 2 4";
        File f = new File("test.txt");
        FileWriter fi = new FileWriter(f);
        BufferedWriter w = new BufferedWriter(fi);
        w.write(s);
        w.close();
  
        RectangleDisk.bufSize = 1000;
        new CommandProcessor(f);
        String output = systemOut().getHistory();
        assertFuzzyEquals("Rectangle rejected: (1, -1, 2, 4)", output);
    }

    //    /**
    //     * Tests when the points are on 0 and 1024
    //     * @throws IOException 
    //     */
    //    @Test
    //    public void testStillRemBounds() throws IOException
    //    {
    //        String s = "remove 0 0 1024 1024";
    //        File f = new File("test.txt");
    //        FileWriter fi = new FileWriter(f);
    //        BufferedWriter w = new BufferedWriter(fi);
    //        w.write(s);
    //        w.close();
    //        new CommandProcessor(f);
    //        String output = systemOut().getHistory();
    //        assertFuzzyEquals("Rectangle removed: (a, 0, 0, 1024, 1024)", output);
    //    }

    /**
     * Tests the case where the name is called to be removed
     * @throws ClassNotFoundException 
     */
    @Test
    public void testRemoveName() throws IOException, ClassNotFoundException
    {
        String d = "remove name";
        File f = new File("test.txt");
        FileWriter fi = new FileWriter(f);
        BufferedWriter w = new BufferedWriter(fi);
        w.write(d);
        w.close();
        new CommandProcessor(f);
        String output = systemOut().getHistory();
        //assertFuzzyEquals("Rectangle not found: name", output);
        assertFuzzyEquals("", output);
    }

    /**
     * Tests intersections
     * @throws ClassNotFoundException 
     */
    @Test
    public void testIntersections() throws IOException, ClassNotFoundException
    {
        String d = "intersections";
        File f = new File("test.txt");
        FileWriter fi = new FileWriter(f);
        BufferedWriter w = new BufferedWriter(fi);
        w.write(d);
        w.close();
        new CommandProcessor(f);
        String output = systemOut().getHistory();
        assertFuzzyEquals("Intersection pairs: ", output);
    }


    /**
     * Tests search
     * @throws ClassNotFoundException 
     */
    @Test
    public void testSearch() throws IOException, ClassNotFoundException
    {
        String d = "search me";
        File f = new File("test.txt");
        FileWriter fi = new FileWriter(f);
        BufferedWriter w = new BufferedWriter(fi);
        w.write(d);
        w.close();
        new CommandProcessor(f);
        String output = systemOut().getHistory();
        //assertFuzzyEquals("Rectangle not found: me", output);
        assertFuzzyEquals("", output);
    }

    /**
     * tests the region search method
     * height part
     * @throws ClassNotFoundException 
     */
    public void testRegionSearch10() throws IOException, ClassNotFoundException
    {
        String d = "regionsearch   2 2 0 0";
        File f = new File("test.txt");
        FileWriter fi = new FileWriter(f);
        BufferedWriter w = new BufferedWriter(fi);
        w.write(d);
        w.close();

        new CommandProcessor(f);
        String output = systemOut().getHistory();
        assertFuzzyEquals("Rectangle rejected: (2, 2, 0, 0)", output);
    }

    /**
     * tests the region search method
     * @throws ClassNotFoundException 
     */
    public void testRegionSearch11() throws IOException, ClassNotFoundException
    {
        String d = "regionsearch   2  2   1 0";
        File f = new File("test.txt");
        FileWriter fi = new FileWriter(f);
        BufferedWriter w = new BufferedWriter(fi);
        w.write(d);
        w.close();

        RectangleDisk.bufSize = 1000;
        new CommandProcessor(f);
        String output = systemOut().getHistory();
        assertFuzzyEquals("Rectangle rejected: (2, 2, 1, 0)", output);
    }

    /**
     * tests the region search method
     * @throws ClassNotFoundException 
     */
    public void testRegionSearch12() throws IOException, ClassNotFoundException
    {
        String d = "regionsearch   2 2    0 1";
        File f = new File("test.txt");
        FileWriter fi = new FileWriter(f);
        BufferedWriter w = new BufferedWriter(fi);
        w.write(d);
        w.close();

        new CommandProcessor(f);
        String output = systemOut().getHistory();
        assertFuzzyEquals("Rectangle rejected: (2, 2, 0, 1)", output);
    }

    /**
     * tests the region search method
     * @throws ClassNotFoundException 
     */
    public void testRegionSearc13() throws IOException, ClassNotFoundException
    {
        String d = "regionsearch   2 2 1 -1";
        File f = new File("test.txt");
        FileWriter fi = new FileWriter(f);
        BufferedWriter w = new BufferedWriter(fi);
        w.write(d);
        w.close();

        new CommandProcessor(f);
        String output = systemOut().getHistory();
        assertFuzzyEquals("Rectangle rejected: (2, 2, 1, -1)", output);
    }

    /**
     * tests the region search method
     * @throws ClassNotFoundException 
     */
    public void testRegionSearc14() throws IOException, ClassNotFoundException
    {
        String d = "regionsearch   2 2 -1 1";
        File f = new File("test.txt");
        FileWriter fi = new FileWriter(f);
        BufferedWriter w = new BufferedWriter(fi);
        w.write(d);
        w.close();

        new CommandProcessor(f);
        String output = systemOut().getHistory();
        assertFuzzyEquals("Rectangle rejected: (2, 2, -1, 1)", output);
    }

    /**
     * tests the region search method
     * @throws ClassNotFoundException 
     */
    public void testRegionSearc15() throws IOException, ClassNotFoundException
    {
        String d = "regionsearch   2 2 -1 -1";
        File f = new File("test.txt");
        FileWriter fi = new FileWriter(f);
        BufferedWriter w = new BufferedWriter(fi);
        w.write(d);
        w.close();

        new CommandProcessor(f);
        String output = systemOut().getHistory();
        assertFuzzyEquals("Rectangle rejected: (2, 2, -1, -1)", output);
    }

    /**
     * tests the region search method
     * @throws ClassNotFoundException 
     */
    public void testRegionSearc16() throws IOException, ClassNotFoundException
    {
        RectangleDisk.bufSize = 1000;
        String d = "regionsearch   2 2 1 1";
        File f = new File("test.txt");
        FileWriter fi = new FileWriter(f);
        BufferedWriter w = new BufferedWriter(fi);
        w.write(d);
        w.close();

        new CommandProcessor(f);
        String output = systemOut().getHistory();
        //assertFuzzyEquals("Rectangles intersecting "
        // + "region (2, 2, 1, 1):", output);
        assertFuzzyEquals("", output);
    }


    /**
     * tests the region search method
     * @throws ClassNotFoundException 
     */
    public void testRegionSearc17() throws IOException, ClassNotFoundException
    {
        String d = "regionsearch   2 2 0 -1";
        File f = new File("test.txt");
        FileWriter fi = new FileWriter(f);
        BufferedWriter w = new BufferedWriter(fi);
        w.write(d);
        w.close();

        new CommandProcessor(f);
        String output = systemOut().getHistory();
        assertFuzzyEquals("Rectangle rejected: (2, 2, 0, -1)", output);
    }

    /**
     * tests the region search method
     * @throws ClassNotFoundException 
     */
    public void testRegionSearc18() throws IOException, ClassNotFoundException
    {
        String d = "regionsearch   2 2 -1 0";
        File f = new File("test.txt");
        FileWriter fi = new FileWriter(f);
        BufferedWriter w = new BufferedWriter(fi);
        w.write(d);
        w.close();

        new CommandProcessor(f);
        String output = systemOut().getHistory();
        assertFuzzyEquals("Rectangle rejected: (2, 2, -1, 0)", output);
    }


    /**
     * Tests the dump
     * @throws IOException 
     * @throws ClassNotFoundException 
     */
    @Test 
    public void testDump() throws IOException, ClassNotFoundException
    {       
        String d = "dump";
        File f = new File("test.txt");
        FileWriter fi = new FileWriter(f);
        BufferedWriter w = new BufferedWriter(fi);
        w.write(d);
        w.close();
        RectangleDisk.bufSize = 1000;
        new CommandProcessor(f);
        String output = systemOut().getHistory();
        assertFuzzyEquals("SkipList dump:\n Node has "
                + "depth 1, Value (null)\n SkipList size is: 0\n"
                + "Freelist Blocks: \n(0, 1000)", output);
    }

    /**
     * Tests when it is not a dump or anything else
     * @throws IOException
     * @throws ClassNotFoundException 
     */
    @Test
    public void testNotDump() throws IOException, ClassNotFoundException
    {

        RectangleDisk.bufSize = 1000;
        String x = "test should not work";
        File f = new File("test.txt");
        FileWriter fi = new FileWriter(f);
        BufferedWriter w = new BufferedWriter(fi);
        w.write(x);
        w.close();
        new CommandProcessor(f);
        String output = systemOut().getHistory();
        assertFuzzyEquals("", output);
    }
}
