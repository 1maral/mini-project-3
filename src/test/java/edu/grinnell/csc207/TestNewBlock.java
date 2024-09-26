package edu.grinnell.csc207;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

import edu.grinnell.csc207.blocks.AsciiBlock;
import edu.grinnell.csc207.blocks.Empty;
import edu.grinnell.csc207.blocks.Ladder;
import edu.grinnell.csc207.blocks.Line;

/**
 * Tests of the new block.
 */
public class TestNewBlock {
  // +-------+-------------------------------------------------------
  // | Tests |
  // +-------+

    /**
   * Test a regular ladder.
   */
  @Test
  public void testLadderBasic () {
    AsciiBlock ladderMade = new Ladder(new Line("====="), 3, '|');

    assertEquals(7, ladderMade.width(),
        "E: Correct width after making line narrower");
    assertEquals(5, ladderMade.height(),
        "E: Correct height after making line narrower");
    assertEquals("""
                 |=====|
                 |     |
                 |=====|
                 |     |
                 |=====|
                 """,
                 TestUtils.toString(ladderMade));
  } // testLadderBasic ()

  /**
   * Test a ladder line.
   */
  @Test
  public void testLadderLine () {
    Line line = new Line("=====");
    AsciiBlock ladderMade = new Ladder(line, 3, '|');

    assertEquals(7, ladderMade.width(),
        "E: Correct width after making line narrower");
    assertEquals(5, ladderMade.height(),
        "E: Correct height after making line narrower");
    assertEquals("""
                 |=====|
                 |     |
                 |=====|
                 |     |
                 |=====|
                 """,
                 TestUtils.toString(ladderMade));
  } // testLadderLine ()

  /**
   * Test a ladder where the line width changes.
   */
  @Test
  public void testLadderLineChange () {
    Line line = new Line("=====");
    AsciiBlock ladderMade = new Ladder(line, 3, '|');

    assertEquals(7, ladderMade.width(),
        "E: Correct width");
    assertEquals(5, ladderMade.height(),
        "E: Correct height");
    assertEquals("""
                 |=====|
                 |     |
                 |=====|
                 |     |
                 |=====|
                 """,
                 TestUtils.toString(ladderMade));

    line.update("===");

    assertEquals(5, ladderMade.width(),
        "E: Correct width after making line narrower");
    assertEquals(5, ladderMade.height(),
        "E: Correct height after making line narrower");
    assertEquals("""
                 |===|
                 |   |
                 |===|
                 |   |
                 |===|
                 """,
                 TestUtils.toString(ladderMade));

    line.update("=======");

    assertEquals(9, ladderMade.width(),
        "E: Correct width after making line wider");
    assertEquals(5, ladderMade.height(),
        "E: Correct height after making line wider");
    assertEquals("""
                 |=======|
                 |       |
                 |=======|
                 |       |
                 |=======|
                 """,
                 TestUtils.toString(ladderMade));
                 
  } // testLadderLineChange ()

  /**
   * Test a ladder that changes in height.
   */
  @Test
  public void testLadderHeightChange () {
    AsciiBlock ladderMade = new Ladder(new Line("====="), 3, '|');

    assertEquals(7, ladderMade.width(),
        "E: Correct width after making line narrower");
    assertEquals(5, ladderMade.height(),
        "E: Correct height after making line narrower");
    assertEquals("""
                 |=====|
                 |     |
                 |=====|
                 |     |
                 |=====|
                 """,
                 TestUtils.toString(ladderMade));

    ladderMade = new Ladder(new Line("====="), 5, '|');

    assertEquals(7, ladderMade.width(),
        "E: Correct width after making height larger");
    assertEquals(9, ladderMade.height(),
        "E: Correct height after making height larger");
    assertEquals("""
                 |=====|
                 |     |
                 |=====|
                 |     |
                 |=====|
                 |     |
                 |=====|
                 |     |
                 |=====|
                 """,
                 TestUtils.toString(ladderMade));
                 
    ladderMade = new Ladder(new Line("====="), 1, '|');

    assertEquals(7, ladderMade.width(),
        "E: Correct width after making steps less");
    assertEquals(1, ladderMade.height(),
        "E: Correct height after making steps less");
    assertEquals("""
                 |=====|
                 """,
                 TestUtils.toString(ladderMade));
  } // testLadderHeightChange ()

  /**
   * Test a ladder with different sides.
   */
  @Test
  public void testLadderDiffSides () {
    AsciiBlock ladderMade = new Ladder(new Line("====="), 3, 'I');

    assertEquals(7, ladderMade.width(),
        "E: Correct width after making line narrower");
    assertEquals(5, ladderMade.height(),
        "E: Correct height after making line narrower");
    assertEquals("""
                 I=====I
                 I     I
                 I=====I
                 I     I
                 I=====I
                 """,
                 TestUtils.toString(ladderMade));

    ladderMade = new Ladder(new Line("====="), 3, 'l');

    assertEquals(7, ladderMade.width(),
        "E: Correct width after changing sides");
    assertEquals(5, ladderMade.height(),
        "E: Correct height after changing sides");
    assertEquals("""
                 l=====l
                 l     l
                 l=====l
                 l     l
                 l=====l
                 """,
                 TestUtils.toString(ladderMade));
  } // testLadderDiffSides ()

      /**
   * Test a regular ladder.
   */
  @Test
  public void testLadderEmpty () {
    AsciiBlock ladderMade = new Ladder(new Empty(), 3, '|');

    assertEquals(0, ladderMade.width(),
        "E: Correct width");
    assertEquals(0, ladderMade.height(),
        "E: Correct height");
  } // testLadderBasic ()
} // class TestNewBlock
