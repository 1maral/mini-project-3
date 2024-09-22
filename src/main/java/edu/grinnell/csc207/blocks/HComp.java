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
    int max = this.blocks[0].height();
    int min = this.blocks[0].height();
    String madeline = "";

    for (int a = 1; a < this.blocks.length; a++) {
      if (this.blocks[a].height() > max) {
        max = this.blocks[a].height();
      } else if (this.blocks[a].height() < min) {
        min = (this.blocks[a].height());
      }
    }

    if (VAlignment.TOP.equals(this.align)){
      for (AsciiBlock block : this.blocks) {
        if ((i < 0) || (i >= this.height())) {
            // Outside of normal bounds
            throw new Exception("Invalid row " + i);
        } else if (i < block.height()) {
            madeline += block.row(i);
        } else if (i > block.height()) {
            madeline += " ".repeat(block.width());
        }
      }
    }

    if (VAlignment.CENTER.equals(this.align)){
      for (AsciiBlock block : this.blocks) {
        if ((i < 0) || (i >= this.height())) {
            // Outside of normal bounds
            throw new Exception("Invalid row " + i);
        } else if ((i >= Math.floor((max - this.height()) / 2) && (i < Math.ceil((max - this.height()) / 2)))){
            madeline += block.row(i);
        } else if (block.height() == max) {
            madeline += block.row(i);
        } else {
            madeline += " ".repeat(block.width());
        }
      }
    }

    if (VAlignment.BOTTOM.equals(this.align)){
      for (AsciiBlock block : this.blocks) {
        if ((i < 0) || (i >= this.height())) {
            // Outside of normal bounds
            throw new Exception("Invalid row " + i);
        } else if (block.height() == max) {
            madeline += block.row(i);
        } else if (i > (max - block.height())) {
            madeline += block.row(i);
        } else {
            madeline += " ".repeat(block.width());
        }
      }
    }
    return madeline;
  } // row(int)

  /**
   * Determine how many rows are in the block.
   *
   * @return the number of rows
   */
  public int height() {
    return Math.max(this.blocks[0].height(), this.blocks[1].height());
  } // height()

  /**
   * Determine how many columns are in the block.
   *
   * @return the number of columns
   */
  public int width() {
    return this.blocks[0].width() + this.blocks[1].width();
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
    return false;       // STUB
  } // eqv(AsciiBlock)
} // class HComp
