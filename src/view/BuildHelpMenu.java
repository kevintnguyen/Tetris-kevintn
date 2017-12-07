
package view;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.JCheckBox;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

/**
 * BuildHelpMenu is the class that sets up the Help Menu.
 * 
 * @author Kevin Nguyen
 * @version 4.5.0 December 2015
 */
public class BuildHelpMenu extends JMenu {
    /**
     * serialVersionUID is generated to keep Checkstyle off.
     */
    private static final long serialVersionUID = -1911290848585409454L;
    /**
     * myPanel stores the main drawing panel so it could be used in this instance.
     */
    private final DrawingPanel myPanel;

    /**
     * BuildHelpMenu() sets up the Help Menu by adding the necessary components
     * to it like JMenuItems.
     * @param theDrawingPanel is the main drawing panel sent over to 
     * be used as an instance variable.
     */
    public BuildHelpMenu(final DrawingPanel theDrawingPanel) {

        super("Help");
        myPanel = theDrawingPanel;
        setForeground(Color.white);
        setMnemonic(KeyEvent.VK_H);
        setBackground(Color.GREEN);
        final JMenuItem about = new JMenuItem("Controls/Scoring");
        about.setMnemonic(KeyEvent.VK_A);
        add(about);
        about.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(final ActionEvent theE) {
                JOptionPane.showMessageDialog(null,
                                              "Scoring: 250 pts per line\n2000 pts per level\n"
                                              + "\n~BEWARE OF LEVEL 4~\n"
                                              + "\nControls:\nW  "
                                              + "= Rotate\n A = Left\n "
                                              + "D = Right\n S = "
                                              + "Down\nShift = Hard Drop");

            }
        });
        setupgridbutton();
    }
    /**
     * setupgridbutton() sets up the grid button for Tetris.
     */
    private void setupgridbutton() {
        final JCheckBox grid = new JCheckBox("     Grid");
        grid.setMnemonic(KeyEvent.VK_G);
        grid.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(final ActionEvent theAction) {
                if (grid.isSelected()) {
                    myPanel.grid(true);
                } else {
                    myPanel.grid(false);
                }

            }
        });
        add(grid);
        addSeparator();
        
    }
}
