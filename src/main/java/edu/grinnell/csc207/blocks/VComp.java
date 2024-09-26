package edu.grinnell.csc207.blocks;

import java.util.Arrays;

/**
 * The vertical composition of blocks.
 *
 * @author Samuel A. Rebelsky
 * @author Maral Bat-Erdene
 * @author Jake Bell
 */
public class VComp implements AsciiBlock {
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
  HAlignment align;

  // +--------------+------------------------------------------------------
  // | Constructors |
  // +--------------+

  /**
   * Build a vertical composition of two blocks.
   *
   * @param alignment
   *   The way in which the blocks should be aligned.
   * @param topBlock
   *   The block on the top.
   * @param bottomBlock
   *   The block on the bottom.
   */
  public VComp(HAlignment alignment, AsciiBlock topBlock,
      AsciiBlock bottomBlock) {
    this.align = alignment;
    this.blocks = new AsciiBlock[] {topBlock, bottomBlock};
  } // VComp(HAlignment, AsciiBlock, AsciiBlock)

  /**
   * Build a vertical composition of multiple blocks.
   *
   * @param alignment
   *   The alignment of the blocks.
   * @param blocksToCompose
   *   The blocks we will be composing.
   */
  public VComp(HAlignment alignment, AsciiBlock[] blocksToCompose) {
    this.align = alignment;
    this.blocks = Arrays.copyOf(blocksToCompose, blocksToCompose.length);
  } // VComp(HAlignment, AsciiBLOCK[])

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
    int sum = 0;
    int index = 0;
    int count = 0;

    for (int j = 0; sum + this.blocks[j].height() <= i; j++) {
      sum += this.blocks[j].height();
      count++;
    }

    index = i - sum;

    if ((i < 0) || (i >= this.height())) {
      // Outside of normal bounds
      throw new Exception("Invalid row " + i);
    } else if (this.blocks[count].width() == this.width()) {
      madeline += this.blocks[count].row(index);
      return madeline;
    } // if

    
    switch (this.align) {
      case LEFT:
        madeline += this.blocks[count].row(index) + " ".repeat(this.width() - this.blocks[count].width());
        break;
      case CENTER:
        madeline += " ".repeat((int) Math.floor((this.width() - this.blocks[count].width()) / 2))
        + this.blocks[count].row(index) + " ".repeat((int) Math.ceil((double) (this.width() - this.blocks[count].width()) / 2));
        int checktop = ((int) Math.floor((this.width() - this.blocks[count].width()) / 2));
        int checkbottom = ((int) Math.ceil((this.width() - this.blocks[count].width()) / 2));
        break;
      case RIGHT:
        madeline += " ".repeat(this.width() - this.blocks[count].width()) + this.blocks[count].row(index);
        break;
      default:
        madeline = "ERROR: WRONG ALIGNMENT";
        break;
      } // switch

    return madeline;
  } // row(int)

  /**
   * Determine how many rows are in the block.
   *
   * @return the number of rows
   */
  public int height() {
    int len = 0;

    for (AsciiBlock block : this.blocks) {
      len += block.height();
    } // for
    return len;   // STUB
  } // height()

  /**
   * Determine how many columns are in the block.
   *
   * @return the number of columns
   */
  public int width() {
    int wid = 0;

    for (AsciiBlock block : this.blocks) {
      if (block.width() > wid) {
        wid = block.width();
      } // if
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
    return ((other instanceof VComp) && (this.eqv((VComp) other)));
  } // eqv(AsciiBlock)

  /**
   * Determine if another VComp is structurally equivalent to this VComp.
   *
   * @param other
   *   The VComp to compare to this VComp.
   *
   * @return true if the two blocks are structurally equivalent and
   *    false otherwise.
   */
  public boolean eqv(VComp other) {
    boolean arrEqv = true;
    for (int x = 0; x < Math.min(blocks.length, other.blocks.length); x++) {
      arrEqv = arrEqv && blocks[x].eqv(other.blocks[x]);
    }
    return ((this.align == other.align) && arrEqv);
  } // eqv (VComp)
} // class VComp
