
package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.OverlayLayout;

import model.Board;

/**
 * TetrisGUI is a class that sets up the Tetris game overall by adding
 * components together.
 * 
 * @author Kevin Nguyen
 * @version 4.5.0 December 2015
 */
public class TetrisGUI extends JPanel {
    /**
     * SIXTY_HUNDRED is used to replace a direct usage of int 600.
     */
    public static final int SIXTY_HUNDRED = 600;
    /**
     * THIRTY is used to replace a direct usage of int 30.
     */
    public static final int THIRTY = 30;
    /**
     * TEN is used to replace a direct usage of int 10.
     */
    public static final int TEN = 10;
    /**
     * serialVersionUID is generated to keep Checkstyle off.
     */
    private static final long serialVersionUID = -1241645069476373285L;
   
    /**
     * myFrame is the JFrame used for Tetris.
     */
    private final JFrame myFrame = new JFrame("Tetris");

    /**
     * myBoardPanel will be the main Panel used to display Tetris's GUI.
     */
    private DrawingPanel myBoardPanel;
    /**
     * start() runs the necessary componets to build the Tetris game.
     */
    public void start() {
        final Board board = new Board(10, 20);
        final DrawingPanel boardpanel = new DrawingPanel(300, 630, board);
        final ScoreTimes scoretime = new ScoreTimes(100, 200, boardpanel);
        myBoardPanel = boardpanel;
        board.addPropertyListener(scoretime);
        final Time time = new Time(100, 200, board, myBoardPanel);
        myBoardPanel.addPropertyListener(time);
        myBoardPanel.addPropertyListener(scoretime);
        final NextItem next = new NextItem(100, 100);
        myBoardPanel.setLayout(new BorderLayout());
        final JPanel itempanel = new JPanel();
        board.addObserver(next);
        myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        myFrame.addKeyListener(new KeyListener() {

            @Override
            public void keyTyped(final KeyEvent theEvent) {

            }

            @Override
            public void keyReleased(final KeyEvent theEvent) {
                myBoardPanel.keyaction(theEvent);

            }

            @Override
            public void keyPressed(final KeyEvent theEvent) {

            }
        });
        myFrame.setLocationByPlatform(true);
        myFrame.setVisible(true);
        myFrame.add(this);
        final JPanel backgroundPanel = new JPanel();
        backgroundPanel.setLayout(new OverlayLayout(backgroundPanel));
        myBoardPanel.setOpaque(false);
        backgroundPanel.add(myBoardPanel);
        backgroundPanel.add(new WallPaper(THIRTY * TEN, 
                                          SIXTY_HUNDRED + THIRTY));
        myFrame.add(backgroundPanel);
        final Dimension o = new Dimension(200, 630);
        itempanel.setPreferredSize(o);
        itempanel.add(next);
        itempanel.add(time);
        itempanel.add(scoretime);
        createlogo(itempanel);
        itempanel.setBackground(Color.black);
        myFrame.add(itempanel, BorderLayout.EAST);
        final JMenuBar menuBars = new JMenuBar();
        menuBars.setBackground(Color.CYAN);
        final BuildFileMenu fileMenu = new BuildFileMenu(myFrame, board, myBoardPanel);
        menuBars.add(fileMenu);
        menuBars.add(new BuildHelpMenu(myBoardPanel));

        myFrame.setJMenuBar(menuBars);

        final Dimension min =
                        new Dimension(myBoardPanel.getWidth() + itempanel.getWidth() - 10,
                                      myBoardPanel.getHeight());

        myFrame.setSize(min);
        myFrame.setResizable(false);
        myFrame.pack();

    }
    /**
     * createlogo creates a logo in the bottom left of the screen in Tetris.
     * @param theItemPanel is the panel sent over to add the image to.
     */
    private void createlogo(final JPanel theItemPanel) {
        BufferedImage imageUsed = null;
        try {
            imageUsed = ImageIO.read(new File("images/tetrislogo1.jpg"));
        } catch (final IOException exception) {
            // do nothing.
            add(new JPanel());
        }
        if (imageUsed != null) {
            final JLabel wallpaperUsed = new JLabel(new ImageIcon(imageUsed));

            theItemPanel.add(wallpaperUsed);
        }
    }

}
