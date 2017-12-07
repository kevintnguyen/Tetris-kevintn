
package view;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.Timer;

import model.AbstractPiece;
import model.Block;
import model.Board;
import model.Piece;

/**
 * DrawingPanel is a class that sets up the drawing panel for Tetris.
 * 
 * @author Kevin Nguyen
 * @version 4.5.0 December 2015
 */
public class DrawingPanel extends JPanel implements Observer, PropertyChangeListener {
    /**
     * MOVE_DELAY is the delay the timer uses.
     */
    public static final int MOVE_DELAY = 500;
    /**
     * HUNDRED is used to replace a direct usage of int 100.
     */
    public static final int HUNDRED = 100;
    /**
     * SIXTY_HUNDRED is used to replace a direct usage of int 600.
     */
    public static final int SIXTY_HUNDRED = 600;
    /**
     * EIGHTY_FIVE is used to replace a direct usage of int 85.
     */
    public static final int EIGHTY_FIVE = 85;
    /**
     * THREEFIVE is used to replace a direct usage of int 315.
     */
    public static final int THREEFIVE = 315;
    /**
     * ONETWENTY is used to replace a direct usage of int 120.
     */
    public static final int ONETWENTY = 120;
    /**
     * TWOSEVENFIVE is used to replace a direct usage of int 275.
     */
    public static final int TWOSEVENFIVE = 275;
    /**
     * THIRTY is used to replace a direct usage of int 30.
     */
    public static final int THIRTY = 30;
    /**
     * TWENTY is used to replace a direct usage of int 30.
     */
    public static final int TWENTY = 20;
    /**
     * THREE is used to replace a direct usage of int 3.
     */
    public static final int THREE = 3;
    /**
     * SEVEN is used to replace a direct usage of int 7.
     */
    public static final int SEVEN = 7;
    /**
     * TEN is used to replace a direct usage of int 10.
     */
    public static final int TEN = 10;
    /**
     * I is used to replace a direct usage of "I".
     */
    public static final String I = "I";
    /**
     * J is used to replace a direct usage of "J".
     */
    public static final String J = "J";
    /**
     * L is used to replace a direct usage of "L".
     */
    public static final String L = "L";
    /**
     * O is used to replace a direct usage of "O".
     */
    public static final String O = "O";
    /**
     * S is used to replace a direct usage of "S".
     */
    public static final String S = "S";
    /**
     * T is used to replace a direct usage of "T".
     */
    public static final String T = "T";
    /**
     * Z is used to replace a direct usage of "Z".
     */
    public static final String Z = "Z";
    /**
     * CB is used to replace a direct usage of "sounds/CB.wav".
     */
    public static final String CB = "sounds/CB.wav";
    /**
     * INITIAL_DELAY is the initial delay the timer uses.
     */
    public static final int INITIAL_DELAY = 0;
    /**
     * serialVersionUID is generated to keep Checkstyle off.
     */
    private static final long serialVersionUID = 8051415093266520116L;
    /**
     * MY_GRID_SIZE is the size of the grid by every 30 pixels.
     */
    private static final int MY_GRID_SIZE = 30;
    /**
     * EIGHTY is used to replace a direct usage of int 80.
     */
    private static final int EIGHTY = 80;
    /**
     * myBlocks stores the shapes into an ArrayList. Not initialized yet.
     */
    private List<Shape> myBlocks;
    /**
     * myBoard is the current board used for Tetris.
     */

    private final Board myBoard;
    /**
     * myTimer is the timer for this class.
     */
    private final Timer myTimer;
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
     * myBlock1 is a shape or a block from a Tetris's piece.
     */
    private Shape myBlock1;
    /**
     * myBlock2 is a shape or a block from a Tetris's piece.
     */
    private Shape myBlock2;
    /**
     * myBlock3 is a shape or a block from a Tetris's piece.
     */
    private Shape myBlock3;
    /**
     * myBlock4 is a shape or a block from a Tetris's piece.
     */
    private Shape myBlock4;
    /**
     * myPC creates a PropertyChangeSupport for this class.
     */
    private final PropertyChangeSupport myPC = new PropertyChangeSupport(this);
    /**
     * myMoves is a class that determines the piece's moves.
     */
    private final Moves myMoves;
    /**
     * myGridBoolean determines whether or not the user has clicked on grid.
     */
    private boolean myGridBoolean;
    /**
     * mySounds stores the SoundPlayer, which is used to play sounds.
     */
    private SoundPlayer mySounds;

    /**
     * DrawingPanel() creates shapes for Tetris.
     * 
     * @param theWidth is a int passed from another class.
     * @param theHeight is a int passed from another class.
     * @param theBoard is a Board passed from another class.
     */
    public DrawingPanel(final int theWidth, final int theHeight, final Board theBoard) {
        super();
        myWidth = theWidth;
        myHeight = theHeight;
        myMinSize = new Dimension(theWidth, theHeight);
        myBoard = theBoard;

        myBlocks = new ArrayList<>();
        myMoves = new Moves(myBoard);
        myTimer = new Timer(MOVE_DELAY, new ActionListener() {

            @Override
            public void actionPerformed(final ActionEvent theAction) {
                makeshape();
                myBoard.step();
            }
        });
        setup();

    }

    /**
     * addPropertyListener() adds the Time panel to the list of property change
     * listeners.
     * 
     * @param theTime the change listener.
     */
    public void addPropertyListener(final Time theTime) {
        myPC.addPropertyChangeListener(theTime);
    }

    /**
     * addPropertyListener adds the ScoreTime panel to the list of property
     * change listeners.
     * 
     * @param theScoreTime the change listener.
     */
    public void addPropertyListener(final ScoreTimes theScoreTime) {
        myPC.addPropertyChangeListener(theScoreTime);
    }

    /**
     * watch() returns a boolean determining whether the watch is currently
     * running or not.
     * 
     * @return true is the Timer is running, else false.
     */
    public boolean watch() {
        return myTimer.isRunning();
    }

    /**
     * setup() sets up extra things for Tetris.
     */
    private void setup() {

        setBackground(Color.BLACK);
        setBorder(BorderFactory.createLineBorder(Color.black));
        myBoard.addObserver(this);
        myTimer.start();
        // myTimer.stop();
        myTimer.setInitialDelay(INITIAL_DELAY);
        myBoard.gamerOverNow();
        mySounds = new SoundPlayer();
    }

    @Override
    public int getWidth() {
        return myWidth;

    };

    @Override
    public int getHeight() {
        return myHeight;

    };

    @Override
    public Dimension getMinimumSize() {
        return myMinSize;
    }

    @Override
    public Dimension getPreferredSize() {
        return myMinSize;
    }

    @Override
    public void update(final Observable theObservable, final Object theObject) {
        repaint();
    }

    @Override
    protected void paintComponent(final Graphics theGraphics) {
        super.paintComponent(theGraphics);
        final Graphics2D g2d = (Graphics2D) theGraphics;

        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                             RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setPaint(Color.GREEN);

        if (myBoard.isGameOver()) {
            mySounds.stop(CB);
            myTimer.stop();
            g2d.setPaint(Color.BLACK);
            if (myBlock1 != null) {
                g2d.fill(myBlock1);
                g2d.fill(myBlock2);

                g2d.fill(myBlock3);
                g2d.fill(myBlock4);
            }
            for (final Shape s : myBlocks) {
                if (((Rectangle) s).y > 0) {
                    g2d.fill(s);
                }

            }
        } else {

            if (myBlock1 != null) {
                g2d.fill(myBlock1);
                g2d.fill(myBlock2);

                g2d.fill(myBlock3);
                g2d.fill(myBlock4);
            }
            g2d.setPaint(new GradientPaint(new Point(0, 0), Color.MAGENTA,
                                           new Point(HUNDRED * THREE, 0), Color.blue));

            makeshape();
            for (final Shape s : myBlocks) {
                if (((Rectangle) s).y > 0) {
                    g2d.fill(s);
                }

            }
            callrepaint();
            if (myGridBoolean) {
                drawGrid(g2d);
            }
        }
    }
    /**
     * grid sets the grid's boolean from the options menu.
     * 
     * @param theAnswer is the boolean sent over from the options menu.
     */
    public void grid(final boolean theAnswer) {
        myGridBoolean = theAnswer;
    }

    /**
     * callrepaint() calls repaint() outside from paint components.
     */
    private void callrepaint() {
        repaint();
    }

    /**
     * makeshape() calls two methods to complete a shape and it's location on
     * Tetris.
     */
    public void makeshape() {

        addshape();
        makecoord();

    }

    /**
     * saves the piece when it is hard drop.
     * 
     * @param theOldPiece is the piece that was harddropped.
     */
    private void harddroppiece(final Piece theOldPiece) {

        final int[][] k = ((AbstractPiece) theOldPiece).getRotation();

        myBlock1 = new Rectangle((theOldPiece.getX() + (k[0][0])) * THIRTY,
                                 SIXTY_HUNDRED - ((theOldPiece.getY() + (k[0][1])) * THIRTY),
                                 THIRTY, THIRTY);
        myBlock2 = new Rectangle((theOldPiece.getX() + (k[1][0])) * THIRTY,
                                 SIXTY_HUNDRED - ((theOldPiece.getY() + (k[1][1])) * THIRTY),
                                 THIRTY, THIRTY);
        myBlock3 = new Rectangle((theOldPiece.getX() + (k[2][0])) * THIRTY,
                                 SIXTY_HUNDRED - ((theOldPiece.getY() + (k[2][1])) * THIRTY),
                                 THIRTY, THIRTY);
        myBlock4 = new Rectangle((theOldPiece.getX() + (k[THREE][0]))
                                 * THIRTY, SIXTY_HUNDRED
                                           - ((theOldPiece.getY() + (k[THREE][1])) * THIRTY),
                                 THIRTY, THIRTY);

        repaint();

    }

    /**
     * makecoord() sets the blocks to rectangle by giving it coordinates of the board's pieces.
     */
    private void makecoord() {
        final int[][] k = ((AbstractPiece) myBoard.getCurrentPiece()).getRotation();

        myBlock1 = new Rectangle((myBoard.getCurrentPiece().getX() + (k[0][0]))
                                 * THIRTY, SIXTY_HUNDRED
                                           - ((myBoard.getCurrentPiece().getY() + (k[0][1]))
                                              * THIRTY),
                                 THIRTY, THIRTY);
        myBlock2 = new Rectangle((myBoard.getCurrentPiece().getX() + (k[1][0]))
                                 * THIRTY, SIXTY_HUNDRED
                                           - ((myBoard.getCurrentPiece().getY() + (k[1][1]))
                                              * THIRTY),
                                 THIRTY, THIRTY);
        myBlock3 = new Rectangle((myBoard.getCurrentPiece().getX() + (k[2][0]))
                                 * THIRTY, SIXTY_HUNDRED
                                           - ((myBoard.getCurrentPiece().getY() + (k[2][1]))
                                              * THIRTY),
                                 THIRTY, THIRTY);
        myBlock4 = new Rectangle((myBoard.getCurrentPiece().getX() + (k[THREE][0])) * THIRTY,
                                 SIXTY_HUNDRED - ((myBoard.getCurrentPiece().getY()
                                                   + (k[THREE][1]))
                                                  * THIRTY),
                                 THIRTY, THIRTY);

    }

    /**
     * keyaction() receives a KeyEvent from another class and uses it for Tetris
     * to find the key event used.
     * 
     * @param theKeyEvent is the key event.
     */
    public void keyaction(final KeyEvent theKeyEvent) {
        if (!myBoard.isGameOver()) {
            if (theKeyEvent.getKeyCode() == (THIRTY * 2 + SEVEN - 2)) { // LEFT
                if (myBoard.moveleft() && myTimer.isRunning()) {
                    myMoves.checkleft();
                    makeshape();
                }
            } else if (theKeyEvent.getKeyCode() == (EIGHTY_FIVE + 2)) { // ROTATE
                if (myBoard.rotate2() && myTimer.isRunning()) {
                    myMoves.checkrotate();
                    makeshape();
                    mySounds.play("sounds/LS.wav");
                }

            } else {

                keyaction2(theKeyEvent);
            }

        }
    }

    /**
     * keyaction2() receives a KeyEvent from another method and uses it for
     * Tetris to find the key event used.
     * 
     * @param theKeyEvent is the key event.
     */
    private void keyaction2(final KeyEvent theKeyEvent) {
        if (theKeyEvent.getKeyCode() == (EIGHTY_FIVE - 2) && myTimer.isRunning()) { // DOWN

            if (myBoard.getCurrentPiece().getY() > 0
                && myBoard.getCurrentPiece().getY() != TWENTY) {
                myTimer.stop();
                myBoard.moveDown();
                makeshape();
                myTimer.start();
            }
        } else if (theKeyEvent.getKeyCode() == (TEN + SEVEN - 1) 
                        && myTimer.isRunning()) { // HARDDROP

            final Piece old = myBoard.getCurrentPiece();
            myBoard.hardDrop();
            harddroppiece(old);
            addshape();

        } else {

            keyaction3(theKeyEvent);

        }

    }

    /**
     * keyaction3() receives a KeyEvent from another method and uses it for
     * Tetris to find the key event used.
     * 
     * @param theKeyEvent is the key event.
     */
    private void keyaction3(final KeyEvent theKeyEvent) {
        if (theKeyEvent.getKeyCode() == (THIRTY * 2 + SEVEN + 1)) { // RIGHT
            if (myBoard.moveright() && myTimer.isRunning()) {
                myMoves.checkright();
                makeshape();
            }
        } else if (theKeyEvent.getKeyCode() == EIGHTY) { // PAUSE

            if (myTimer.isRunning()) {
                myTimer.stop();
                if (mySounds.isSongPlaying()) {
                    mySounds.pause(CB);
                }

            } else {
                myTimer.start();
                if (!mySounds.isSongPlaying()) {
                    mySounds.play(CB);
                }
            }
        } else {
            keyaction4(theKeyEvent);
        }

    }

    /**
     * keyaction4() receives a KeyEvent from another method and uses it for
     * Tetris to find the key event used.
     * 
     * @param theKeyEvent is the key event.
     */
    private void keyaction4(final KeyEvent theKeyEvent) {

        if (theKeyEvent.getKeyCode() == ((SEVEN * TEN) + SEVEN)) { // MUTE

            if (mySounds.isSongPlaying()) {
                mySounds.pause(CB);

            } else if (!mySounds.isSongPlaying()) {
                mySounds.play(CB);

            }
        }

    }

    /**
     * addshape() adds a shape to a frozen arraylist off of the orginal board's block list.
     */
    private void addshape() {

        final List<Block[]> frozenPieces = myBoard.getFrozenBlocks();
        myBlocks.clear();
        for (int row = 0; row < frozenPieces.size(); row++) {
            for (int section = 0; section < frozenPieces.get(row).length; section++) {
                if (frozenPieces.get(row)[section] != Block.EMPTY) {
                    myBlocks.add(new Rectangle(section * THIRTY, SIXTY_HUNDRED - row * THIRTY,
                                               THIRTY, THIRTY));
                }
            }
        }
    }

    /**
     * clear() clears everything from the board as if it was a new game.
     */
    public void clear() {

        myBlocks = new ArrayList<>();
        myTimer.setDelay(MOVE_DELAY);
        myTimer.restart();
        myPC.firePropertyChange("NEWGAME", HUNDRED * TEN, 0);
        myPC.firePropertyChange("NEWTIME", 1, 0);
        mySounds.play(CB);

    }

    /**
     * drawGrid draws a grid to the panel.
     * 
     * @param theGraphicsPanel is the graphics passed from JPanel.
     */
    private void drawGrid(final Graphics2D theGraphicsPanel) {
        theGraphicsPanel.setPaint(Color.CYAN);
        theGraphicsPanel.setStroke(new BasicStroke(1));

        for (int i = 0; i < getWidth(); i = i + MY_GRID_SIZE) {
            for (int r = 0; r < getHeight(); r = r + MY_GRID_SIZE) {
                theGraphicsPanel.draw(new Rectangle(i, r, MY_GRID_SIZE, MY_GRID_SIZE));

            }
            repaint();
        }
    }

    @Override
    public void propertyChange(final PropertyChangeEvent theEvent) {
        if ("CHANGE".equals(theEvent.getPropertyName())) {
            final int delay = myTimer.getDelay();
            if (delay != HUNDRED) {
                myTimer.setDelay(delay - ((int) theEvent.getNewValue()));

            }
        }

    }

}
