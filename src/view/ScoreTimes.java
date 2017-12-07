
package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

import javax.swing.JPanel;

/**
 * Time sets up the time that is display on Tetris.
 * 
 * @author Kevin Nguyen
 * @version 4.5.0 December 2015
 */
public class ScoreTimes extends JPanel implements PropertyChangeListener {
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
     * TWENTY is used to replace a direct usage of int 20.
     */
    public static final int TWENTY = 20;
    /**
     * FIFTY is used to replace a direct usage of int 50.
     */
    public static final int FIFTY = 50;
    /**
     * SPREAD is the spread between each item the paint component uses.
     */
    public static final int SPREAD = 10;
    /**
     * serialVersionUID is generated to keep Checkstyle off.
     */
    private static final long serialVersionUID = -417147100271851362L;
    /**
     * myLevel is an int that describes the starting level of Tetris.
     */
    private  int myLevel = 1;
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
    private final PropertyChangeSupport myPC = new PropertyChangeSupport(this);
    /**
     * myScore is a int that holds the current score used in Tetris.
     */
    private int myScore;
    /**
     * myScore is a int that holds the amount of lines removed from Tetris.
     */
    private int myLines;
    /**
     * myNextLevel is a int that holds the next level score for Tetris.
     */
    private int myNextLevel;

    /**
     * Time sets up the Stop watch for Tetris.
     * 
     * @param theWidth is a int passed from another class.
     * @param theHeight is a int passed from another class.
     * @param thePanel is the main panel used from Tetris sent over.
     */
    public ScoreTimes(final int theWidth, final int theHeight,
                      final DrawingPanel thePanel) {
        super();
        myWidth = theWidth;
        myHeight = theHeight;
        myMinSize = new Dimension(theWidth, theHeight);
        setBackground(Color.BLACK);
        myPC.addPropertyChangeListener(thePanel);

    }

    @Override
    protected void paintComponent(final Graphics theGraphics) {
        super.paintComponent(theGraphics);
        final Graphics2D g2d = (Graphics2D) theGraphics;

        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                             RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setPaint(Color.CYAN);
        checklevel();
        g2d.drawString("Score: " + myScore, SPREAD, SPREAD * 2);
        g2d.drawString("Level: " + myLevel, SPREAD, SPREAD * 2 * 2);
        g2d.drawString("Lines: " + myLines, SPREAD, SPREAD * ((2 * 2) + 2));

        g2d.drawString("Next Level: " + myNextLevel, SPREAD, SPREAD * SPREAD);

        g2d.drawString("P = Pause", SPREAD, SPREAD * (SPREAD + 2 + 2));
        g2d.drawString("M = Mute", SPREAD, SPREAD * (SPREAD + 2 + 2 + 2));

    }
    /**
     * checklevel() checks if the score could move up a level.
     * Then increases the level and speed of Tetris.
     */
    private void checklevel() {
        if (myScore > ((TWENTY * SPREAD * SPREAD) * myLevel)) {
            myPC.firePropertyChange("CHANGE", 0, TWENTY * SPREAD);
            myLevel++;
        }
        myNextLevel = myLevel * (TWENTY * SPREAD * SPREAD);
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
        if ("Score".equals(theEvent.getPropertyName())) {
            final SoundPlayer local = new SoundPlayer();
            myScore += (int) theEvent.getNewValue();
            myLines += 1;
            repaint();
            local.play("sounds/L.wav");
        } else if ("NEWGAME".equals(theEvent.getPropertyName())) {
            myScore = (int) theEvent.getNewValue();
            myLines = (int) theEvent.getNewValue();
            myLevel = (int) theEvent.getNewValue() + 1;
            repaint();
        }

    }

}
