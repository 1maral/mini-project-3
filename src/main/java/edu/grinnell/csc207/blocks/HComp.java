package edu.grinnell.csc207.blocks;

import java.util.Arrays;

/**
 * The horizontal composition of blocks.
 *
 * @author Samuel A. Rebelsky
 * @author Your Name Here
 */
public class HComp implements AsciiBlock {
  // +--------+------------------------------------------------------------
  // | Fields |
  // +--------+

  /**
   * The blocks.
   */
  AsciiBlock[] blocks;

  /**
   * How the blocks are aligned.
   */
  VAlignment align;

  // +--------------+------------------------------------------------------
  // | Constructors |
  // +--------------+

  /**
   * Build a horizontal composition of two blocks.
   *
   * @param alignment
   *   The way in which the blocks should be aligned.
   * @param leftBlock
   *   The block on the left.
   * @param rightBlock
   *   The block on the right.
   */
  public HComp(VAlignment alignment, AsciiBlock leftBlock,
      AsciiBlock rightBlock) {
    this.align = alignment;
    this.blocks = new AsciiBlock[] {leftBlock, rightBlock};
  } // HComp(VAlignment, AsciiBlock, AsciiBlock)

  /**
   * Build a horizontal composition of multiple blocks.
   *
   * @param alignment
   *   The alignment of the blocks.
   * @param blocksToCompose
   *   The blocks we will be composing.
   */
  public HComp(VAlignment alignment, AsciiBlock[] blocksToCompose) {
    this.align = alignment;
    this.blocks = Arrays.copyOf(blocksToCompose, blocksToCompose.length);
  } // HComp(Alignment, AsciiBLOCK[])

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
   *   if i is outside the range of valid rows.
   */
  public String row(int i) throws Exception {
    String madeline = "";

    if ((i < 0) || (i >= this.height())) {
      // Outside of normal bounds
      throw new Exception("Invalid row " + i);
    } // if

    for (AsciiBlock block : this.blocks) {
      if (block.height() == this.height()) {
        madeline += block.row(i);
        continue;
      } // if

      if (VAlignment.TOP.equals(this.align)) {
        if (i < block.height()) {
          madeline += block.row(i);
        } else {
          madeline += " ".repeat(block.width());
        } // else
      } else if (VAlignment.CENTER.equals(this.align)) {
        if ((i >= Math.floor((this.height() - block.height()) / 2)
              && (i < block.height() + Math.ceil((this.height() - block.height()) / 2)))) {
          madeline += block.row(i - (int) Math.ceil((this.height() - block.height()) / 2));
        } else {
          madeline += " ".repeat(block.width());
        } // else
      } else if (VAlignment.BOTTOM.equals(this.align)) {
        if (i >= (this.height() - block.height())) {
          madeline += block.row(i - (this.height() - block.height()));
        } else {
          madeline += " ".repeat(block.width());
        } // else
      } // else if
    } // if

    return madeline;
  } // row(int)

  /**
   * Determine how many rows are in the block.
   *
   * @return the number of rows
   */
  public int height() {
    int max = 0;
    for (AsciiBlock block : this.blocks) {
      if (block.height() > max) {
        max = block.height();
      } // if
    } // for (block)
    return max;
  } // height()

  /**
   * Determine how many columns are in the block.
   *
   * @return the number of columns
   */
  public int width() {
    int wid = 0;

    for (AsciiBlock block : this.blocks) {
      wid += block.width();
    } // for
    return wid;
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
    return ((other instanceof HComp) && (this.eqv((HComp) other)));
  } // eqv(AsciiBlock)

  /**
   * Determine if another HComp is structurally equivalent to this HComp.
   *
   * @param other
   *   The HComp to compare to this HComp.
   *
   * @return true if the two blocks are structurally equivalent and
   *    false otherwise.
   */
  public boolean eqv(HComp other) {
    boolean arrEqv = true;
    for (int x = 0; x < blocks.length; x++) {
      System.out.println("x = " + x);
      arrEqv = arrEqv && blocks[x].eqv(other.blocks[x]);
      System.out.println(arrEqv);
    }
    return ((this.align == other.align) && arrEqv);
  } // eqv (HComp)
} // class HComp
