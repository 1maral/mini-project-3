package edu.grinnell.csc207.main;

import edu.grinnell.csc207.blocks.AsciiBlock;
import edu.grinnell.csc207.blocks.Boxed;
import edu.grinnell.csc207.blocks.Grid;
import edu.grinnell.csc207.blocks.Lines;
import edu.grinnell.csc207.blocks.Rect;

import java.io.PrintWriter;

/**
 * Create and print an amazing 80x24 ASCII artwork.
 *
 * @author Maral Bat-Erdene
 * @author Jake Bell
 */
public class Art80x24 {
  /**
   * Create the artwork.
   *
   * @param args
   *   Command-line arguments (currently ignored).
   *
   * @exception Exception
   *   If something goes wrong with one of the underlying classes.
   */
  public static void main(String[] args) throws Exception {
    PrintWriter pen = new PrintWriter(System.out, true);
    AsciiBlock pattern1 = new Lines(new String[]{"Hello World!", "Nice to meet", "   YOU! :)", "- Maral & Jake"});
    AsciiBlock pattern2 = new Boxed(pattern1);
    AsciiBlock art = new Grid(pattern2,5,4);
    AsciiBlock.print(pen, art);
    pen.close();
  } // main(String[])
} // class Art80x24
