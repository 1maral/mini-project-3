package edu.grinnell.csc207.blocks;

import java.util.Arrays;

/**
 * The vertical composition of blocks.
 *
 * @author Samuel A. Rebelsky
 * @author Your Name Here
 * @author Your Name Here
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

    if ((i < 0) || (i >= this.height())) {
      // Outside of normal bounds
      throw new Exception("Invalid row " + i);
    } else if (this.blocks[i].width() == this.width()) {
      madeline += this.blocks[i].row(0);
      return madeline;
    }


    if (null != this.align) switch (this.align) {
      case LEFT:
        madeline += this.blocks[i].row(0) + " ".repeat(this.width() - this.blocks[i].width());
        break;
      case CENTER:
        int margin = ((this.width() - this.blocks[i].width()) / 2);
        madeline += " ".repeat((int) Math.floor(margin)) + this.blocks[i].row(0) + " ".repeat((int) Math.ceil(margin));
        break;
      case RIGHT:
        madeline += " ".repeat(this.width() - this.blocks[i].width()) + this.blocks[i].row(0);
        break;
      default:
        madeline = "ERROR: WRONG ALIGNMENT";
        break;
      }

    return madeline;
  } // row(int)

  /**
   * Determine how many rows are in the block.
   *
   * @return the number of rows
   */
  public int height() {
    int len = 0;

    for (AsciiBlock block : this.blocks){
      len += block.height();
    }
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
    return (this.align == other.align) && (Arrays.equals(this.blocks, other.blocks));
  } // eqv (VComp)
} // class VComp
