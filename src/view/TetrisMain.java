
package view;

import java.awt.EventQueue;

/**
 * TetrisMain is a class that starts the GUI.
 * 
 * @author Kevin Nguyen
 * @version 4.5.0 December 2015
 */
public final class TetrisMain {
    /**
     * Private constructor is used so it could not be instantiated.
     */
    private TetrisMain() {
        throw new IllegalStateException();
    }

    /**
     * The main method, starts the GUI by calling start on DrawingPanel.
     * 
     * @param theArgs Command line arguments.
     */
    public static void main(final String[] theArgs) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new TetrisGUI().start();
            }
        });
    }
}
