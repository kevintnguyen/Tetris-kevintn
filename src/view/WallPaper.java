
package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * Wallpaper is a class that sets up the background wallpaper for Tetris.
 * 
 * @author Kevin Nguyen
 * @version 4.5.0 December 2015
 */
public class WallPaper extends JPanel {
    /**
     * serialVersionUID is generated to keep Checkstyle off.
     */
    private static final long serialVersionUID = 8051415093266520116L;
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

    /**
     * DrawingPanel() creates shapes for Tetris.
     * 
     * @param theWidth is a int passed from another class.
     * @param theHeight is a int passed from another class.
     */
    public WallPaper(final int theWidth, final int theHeight) {
        super();
        myWidth = theWidth;
        myHeight = theHeight;
        myMinSize = new Dimension(theWidth, theHeight);

        setup();

    }

    /**
     * setup() sets up extra things for Tetris.
     */
    private void setup() {

        setBackground(Color.BLACK);
        setBorder(BorderFactory.createLineBorder(Color.black));

        BufferedImage imageUsed = null;
        try {
            imageUsed = ImageIO.read(new File("images/text.jpg"));
        } catch (final IOException exception) {
            //do nothing.
            add(new JPanel());
        }
        if (imageUsed != null) {
            final JLabel wallpaperUsed = new JLabel(new ImageIcon(imageUsed));

            add(wallpaperUsed);
        }
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
    protected void paintComponent(final Graphics theGraphics) {
        super.paintComponent(theGraphics);
        final Graphics2D g2d = (Graphics2D) theGraphics;

        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                             RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setPaint(Color.GREEN);

    }

}
