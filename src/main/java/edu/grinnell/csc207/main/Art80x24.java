package edu.grinnell.csc207.main;

import edu.grinnell.csc207.blocks.AsciiBlock;
import edu.grinnell.csc207.blocks.Boxed;
import edu.grinnell.csc207.blocks.Grid;
import edu.grinnell.csc207.blocks.HAlignment;
import edu.grinnell.csc207.blocks.HComp;
import edu.grinnell.csc207.blocks.Line;
import edu.grinnell.csc207.blocks.Lines;
import edu.grinnell.csc207.blocks.Rect;
import edu.grinnell.csc207.blocks.VAlignment;
import edu.grinnell.csc207.blocks.VComp;

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
    AsciiBlock example = new Grid(new Line("."),80,24);
    AsciiBlock pattern1 = new Rect('#', 10, 3);
    AsciiBlock pattern2 = new Boxed(new Rect(' ', 8, 3));
    AsciiBlock hair1 = new Grid(pattern1,8,1);
    AsciiBlock forehead1 = new Grid(pattern2,6,1);
    AsciiBlock hair2 = new HComp(VAlignment.CENTER, new AsciiBlock[] {pattern1, forehead1, pattern1});
    AsciiBlock forehead2 = new Grid(pattern2,8,1);
    AsciiBlock foreheadFull = new VComp(HAlignment.CENTER, new AsciiBlock[] {hair1, hair2, forehead2});
    AsciiBlock art = new Grid(new Line("."), 80, 24);
    AsciiBlock.print(pen, example);
    AsciiBlock.print(pen, foreheadFull);
    pen.close();
  } // main(String[])
} // class Art80x24
