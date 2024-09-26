package edu.grinnell.csc207.blocks;

public class Ladder implements AsciiBlock {
    // +--------+------------------------------------------------------------
  // | Fields |
  // +--------+

  /**
   * The element in which the steps will be made of.
   */
  AsciiBlock stepElement;

  /**
   * The number of times the step will be repeated vertically.
   */
  int stepRepetitions;

  /**
   * What the sides of the ladder will look like.
   */
  char sideRail;

  // +--------------+------------------------------------------------------
  // | Constructors |
  // +--------------+

  /**
   * Build a new grid with the specified arrangement.
   *
   * @param stepElement
   *   What the ladder steps will be made of, must have height of 1.
   * @param stepRepitions
   *   The number of vertical repetitions in the grid.
   * @param sideRail
   *   The character of the side rail of the ladder.
   */
  public Ladder(AsciiBlock stepElement, int stepRepetitions,
      char sideRail) {
    this.stepElement = stepElement;
    this.stepRepetitions = stepRepetitions;
    this.sideRail = sideRail;
  } // Ladder(AsciiBlock, int, char)

  // +---------+-----------------------------------------------------------
  // | Methods |
  // +---------+

  /**
   * Get one row from the block.
   *
   * @param i the number of the row
   *
   * @return row i.
   *
   * @exception Exception
   *   If the row is invalid.
   */
  public String row(int i) throws Exception {
    String madeline = "";
    String sides = Character.toString(this.sideRail);

    if ((i < 0) || (i >= this.height())) {
      // Outside of normal bounds
      throw new Exception("Invalid row " + i);
    } // if

    if (i % 2 == 0) {
      madeline += sides + this.stepElement.row(0) + sides;
    } else {
      madeline += sides + " ".repeat(this.stepElement.width()) + sides;
    } // if/else

    return madeline;
  } // row(int)

  /**
   * Determine how many rows are in the block.
   *
   * @return the number of rows
   */
  public int height() {
    if (this.stepElement.width() == 0) {
      return 0;
    }
    return ((this.stepRepetitions - 1) * 2) + 1; 
  } // height()

  /**
   * Determine how many columns are in the block.
   *
   * @return the number of columns
   */
  public int width() {
    if (this.stepElement.width() == 0) {
      return 0;
    }
    return this.stepElement.width() + 2;   // STUB
  } // width()

  /**
   * Determine if another block is structurally equivalent to this block.
   *
   * @param other
   *   The block to compare to this block.
   *
   * @return true if the two blocks are structurally equivalent and
   *    false otherwise.
   */
  public boolean eqv(AsciiBlock other) {
    return ((other instanceof Ladder) && (this.eqv((Ladder) other)));
  } // eqv(AsciiBlock)

  /**
   * Determine if another grid is structurally equivalent to this grid.
   *
   * @param other
   *   The grid to compare to this grid.
   *
   * @return true if the two blocks are structurally equivalent and
   *    false otherwise.
   */
  public boolean eqv(Ladder other) {
    return (this.sideRail == other.sideRail) && (this.stepRepetitions == other.stepRepetitions)
        && (this.stepElement.eqv(other.stepElement));
  } // eqv(Grid)
}
