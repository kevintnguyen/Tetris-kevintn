
package view;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.util.Observable;
import java.util.Observer;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

import model.AbstractPiece;
import model.Board;

/**
 * NextItem is a class that sets up the display for Tetris, the Next
 * Piece display.
 * 
 * @author Kevin Nguyen
 * @version 4.5.0 December 2015
 */
public class NextItem extends JPanel implements Observer {
    /**
     * serialVersionUID is generated to keep Checkstyle off.
     */
    private static final long serialVersionUID = 4496266730617922507L;
    /**
     * THRITY is used to replace a direct usage of int 30.
     */
    private static final int THRITY = 30;
    /**
     * TEN is used to replace a direct usage of int 10.
     */
    private static final int TEN = 10;
    /**
     * MY_GRID_SIZE is the size of the grid by every 30 pixels.
     */
    private static final int MY_GRID_SIZE = 10;
    /**
     * myWidth is the width of this JPanel.
     */
    private final int myWidth;
    /**
     * myHeight is the height of this JPanel.
     */
    private final int myHeight;
    /**
     * myMinSize is the minimum Dimension size of this JPanel.
     */
    private final Dimension myMinSize;
    /**
     * myBlock is a rectangle used to display a piece.
     */
    private Rectangle myBlock;
    /**
     * myBlock2 is a rectangle used to display a piece.
     */
    private Rectangle myBlock2;
    /**
     * myBlock3 is a rectangle used to display a piece.
     */


    /**
     * NextItem() sets up the display for Tetris's next piece.
     * @param theWidth is a int passed from another class.
     * @param theHeight is a int passed from another class.
     */
    public NextItem(final int theWidth, final int theHeight) {
        super();
        myWidth = theWidth;
        myHeight = theHeight;
        myMinSize = new Dimension(theWidth, theHeight);
        setBackground(Color.BLACK);
        setBorder(BorderFactory.createLineBorder(Color.green));
        myBlock = new Rectangle(0, 0);
        myBlock2 = new Rectangle(0, 0);


    }

    @Override
    protected void paintComponent(final Graphics theGraphics) {
        super.paintComponent(theGraphics);
        final Graphics2D g2d = (Graphics2D) theGraphics;

        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                             RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setPaint(Color.CYAN);
        g2d.drawString("Next:", TEN, TEN * 2);
        g2d.fill(myBlock);
        g2d.fill(myBlock2);
        drawGrid(g2d);
    }
    /**
     * findshape() finds the board's next shape and sends it to myBlock.
     * @param thePiece 
     */
    public void findshape(final String thePiece) {
        if ("I".equals(thePiece)) {
            myBlock = new Rectangle(TEN + TEN * 2, TEN + THRITY, TEN + THRITY, TEN);
            myBlock2 = new Rectangle(0, 0);
        } else if ("T".equals(thePiece)) {
            myBlock = new Rectangle(TEN + THRITY, TEN + THRITY, THRITY, TEN);
            myBlock2 = new Rectangle(TEN * 2 + THRITY,  THRITY, TEN, TEN);

        } else if ("J".equals(thePiece)) {
            myBlock = new Rectangle(TEN + THRITY, TEN + THRITY, THRITY, TEN);
            myBlock2 = new Rectangle(TEN + THRITY,  +THRITY, TEN, TEN);
        } else if ("L".equals(thePiece)) {
            myBlock = new Rectangle(TEN + TEN * 2, TEN + THRITY, THRITY, TEN);
            myBlock2 = new Rectangle(THRITY + TEN * 2 ,  THRITY, TEN, TEN);
        } else if ("O".equals(thePiece)) {
            myBlock = new Rectangle(TEN + THRITY, TEN + THRITY, TEN * 2, TEN);
            myBlock2 = new Rectangle(TEN + THRITY,  THRITY, TEN * 2, TEN);
        } else if ("S".equals(thePiece)) {
            myBlock = new Rectangle(THRITY + TEN * 2, TEN + THRITY, TEN * 2, TEN);
            myBlock2 = new Rectangle(TEN * 2 + TEN * 2, TEN * 2 + THRITY, TEN * 2, TEN);

        } else if ("Z".equals(thePiece)) {
            myBlock = new Rectangle(TEN + THRITY, TEN + THRITY, TEN * 2, TEN);
            myBlock2 = new Rectangle(TEN * 2 + THRITY, TEN * 2 + THRITY, TEN * 2, TEN);
        }

        repaint();
    }
    /**
     * drawGrid draws a grid to the panel.
     * 
     * @param theGraphicsPanel is the graphics passed from JPanel.
     */
    private void drawGrid(final Graphics2D theGraphicsPanel) {
        theGraphicsPanel.setPaint(Color.BLACK);
        theGraphicsPanel.setStroke(new BasicStroke(1));

        for (int i = 0; i < getWidth(); i = i + MY_GRID_SIZE) {
            for (int r = 0; r < getHeight(); r = r + MY_GRID_SIZE) {
                theGraphicsPanel.draw(new Rectangle(i, r, MY_GRID_SIZE, MY_GRID_SIZE));

            }
            repaint();
        }
    }

    @Override
    public Dimension getMinimumSize() {
        return myMinSize;
    }

    @Override
    public Dimension getPreferredSize() {
        return myMinSize;
    }

    @Override
    public int getWidth() {
        return myWidth;

    };

    @Override
    public int getHeight() {
        return myHeight;

    }

    @Override
    public void update(final Observable theObservable , final Object theObject) {
        final Board board = (Board) theObservable;
        final String next = ((AbstractPiece) board.getNextPiece()).getBlock().toString();
        findshape(next);
    };


}
