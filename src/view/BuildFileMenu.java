
package view;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

import model.Board;

/**
 * BuildFileMenu is the class that sets up the File Menu.
 * 
 * @author Kevin Nguyen
 * @version 4.5.0 December 2015
 */
public class BuildFileMenu extends JMenu {
    /**
     * TEN is used to replace a direct usage of int 10.
     */
    public static final int TEN = 10;
    /**
     * serialVersionUID is generated to keep Checkstyle off.
     */
    private static final long serialVersionUID = 3494159208284016876L;
    /**
     * myFrame is the used to store the GUI's current overall frame.
     */
    private final JFrame myFrame;
  
    /**
     * myBoard is the current board used for Tetris.
     */
    private final Board myBoard;
    /**
     * myPanel stores the main drawing panel so it could be used in this instance.
     */
    private final DrawingPanel myPanel;

    /**
     * BuildFileMenu() sets up the File Menu by adding the necessary components
     * to it like JMenuItems.
     * 
     * @param theOldFrame uses the GUI's current frame, so it could access the
     *            exit function.
     * @param theBoard is the current board used on Tetris.
     * @param theDraw is the main drawing panel sent over.
     */
    public BuildFileMenu(final JFrame theOldFrame, final Board theBoard,
                         final DrawingPanel theDraw) {
        super("File");
        myFrame = theOldFrame;
        setForeground(Color.white);
        setMnemonic(KeyEvent.VK_F);
        setnewbutton();
        setendbutton();
        setexitbutton();
        myBoard = theBoard;
        myPanel = theDraw;
       

    }
    /**
     * setexitbutton() creates the exit button for Tetris.
     */
    private void setexitbutton() {
        final JMenuItem exit = new JMenuItem("Exit");

        exit.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(final ActionEvent theE) {
                myFrame.dispatchEvent(new WindowEvent(myFrame, WindowEvent.WINDOW_CLOSING));
            }
        });
        exit.setMnemonic(KeyEvent.VK_X);

        add(exit);
        
    }

    /**
     * setnewbutton() creates the new game button for Tetris.
     */
    private void setnewbutton() {
        final JMenuItem newGame = new JMenuItem("New Game");
        newGame.setMnemonic(KeyEvent.VK_N);

        newGame.addPropertyChangeListener(new PropertyChangeListener() {

            @Override
            public void propertyChange(final PropertyChangeEvent theEvent) {
                if (myBoard.isGameOver()) {
                    newGame.setEnabled(true);
                } else {
                    newGame.setEnabled(false);
                }

            }
        });

        newGame.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(final ActionEvent theAction) {
                myBoard.newGame(TEN, TEN * 2, null);
                myPanel.clear();
            }
        });
        add(newGame);
        addSeparator();

    }

    /**
     * setendbutton() creates the end game button for Tetris.
     */
    private void setendbutton() {
        final JMenuItem endGame = new JMenuItem("End Game");
        endGame.setMnemonic(KeyEvent.VK_E);

        endGame.addPropertyChangeListener(new PropertyChangeListener() {

            @Override
            public void propertyChange(final PropertyChangeEvent theEvent) {
                if (myBoard.isGameOver()) {
                    endGame.setEnabled(false);
                } else {
                    endGame.setEnabled(true);
                }

            }
        });

        endGame.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(final ActionEvent theAction) {
                myBoard.gamerOverNow();
            }
        });
        add(endGame);
        addSeparator();

    }

}
