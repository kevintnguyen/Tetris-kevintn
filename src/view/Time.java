
package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.JPanel;
import javax.swing.Timer;

import model.Board;

/**
 * Time sets up the time that is display on Tetris.
 * 
 * @author Kevin Nguyen
 * @version 4.5.0 December 2015
 */
@SuppressWarnings("unused")
public class Time extends JPanel implements PropertyChangeListener {
    /**
     * MOVE_DELAY is the delay the timer uses.
     */
    public static final int MOVE_DELAY = 500;
    /**
     * INITIAL_DELAY is the initial delay the timer uses.
     */
    public static final int INITIAL_DELAY = 0;
    /**
     * MINUTE is used to replace the direct usage of int 61.
     */
    public static final int MINUTE = 61;
    /**
     * SPREAD is the spread between each item the paint component uses.
     */
    public static final int SPREAD = 10;
    /**
     * serialVersionUID is generated to keep Checkstyle off.
     */
    private static final long serialVersionUID = -417147100271851362L;
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
     * myBoard is the current board used for Tetris.
     */
    private final Board myBoard;
    /**
     * myMinutes is an int that stores the number of minutes played.
     */
    private int myMinutes;
    /**
     * mySeconds is an int that stores the number of seconds played.
     */
    private int mySeconds;
    /**
     * myStopWatch is the timer for this class.
     */
    private final Timer myStopWatch;
    /**
     * myScore is an int that stores the current score.
     */
    private int myScore;
    /**
     * myPanel stores the main drawing panel so it could be used in this instance.
     */
    private final DrawingPanel myPanel;

    /**
     * Time sets up the Stop watch for Tetris.
     * 
     * @param theWidth is a int passed from another class.
     * @param theHeight is a int passed from another class.
     * @param theBoard is a Board passed from another class.
     * @param theMainBoardPanel is the main drawing panel used for Tetris.
     */
    public Time(final int theWidth, final int theHeight, final Board theBoard,
                final DrawingPanel theMainBoardPanel) {
        super();
        myWidth = theWidth;
        myHeight = theHeight;
        myMinSize = new Dimension(theWidth, theHeight);
        setBackground(Color.BLACK);
        myBoard = theBoard;
        myStopWatch = new Timer(MOVE_DELAY, new ActionListener() {

            @Override
            public void actionPerformed(final ActionEvent theAction) {
                mySeconds = mySeconds + 1;
                repaint();

            }
        });
        myPanel = theMainBoardPanel;
        myStopWatch.start();
        myStopWatch.setInitialDelay(INITIAL_DELAY);
    }

    @Override
    protected void paintComponent(final Graphics theGraphics) {
        super.paintComponent(theGraphics);
        final Graphics2D g2d = (Graphics2D) theGraphics;

        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                             RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setPaint(Color.CYAN);
        if (myPanel.watch()) {
            myStopWatch.start();
        }
        checktime();

        g2d.drawString("Time: ", SPREAD, SPREAD * 2);
        g2d.drawString(myMinutes + " Minutes", SPREAD, SPREAD * 2 * 2);
        g2d.drawString(mySeconds + " Seconds", SPREAD, SPREAD * ((2 * 2) + 2));
        
        g2d.drawString("W = Rotate", SPREAD, SPREAD * SPREAD);
        g2d.drawString("A = Left", SPREAD, SPREAD * (SPREAD + 2));
        g2d.drawString("S = Down", SPREAD, SPREAD * (SPREAD + 2 + 2));
        g2d.drawString("D = Right", SPREAD, SPREAD * (SPREAD + 2 + 2 + 2));
        g2d.drawString("Shift = Harddrop", SPREAD, SPREAD * (SPREAD + 2 + 2 + 2 + 2));

    }

    /**
     * checktime checks when there are 60 seconds and replaces it with 1
     * minutes.
     */
    private void checktime() {
        if (myBoard.isGameOver()) {
            myStopWatch.stop();

        }
        if (mySeconds == MINUTE) {
            myMinutes++;
            mySeconds = 0;
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
    public void propertyChange(final PropertyChangeEvent theEvent) {
        if ("NEWTIME".equals(theEvent.getPropertyName())) {
            myMinutes = 0;
            mySeconds = 0;
        }
    };

}
