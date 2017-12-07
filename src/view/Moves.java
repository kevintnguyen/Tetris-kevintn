
package view;

import model.AbstractPiece;
import model.Board;
/**
 * Moves is a class the determines the piece's moves.
 * @author Kevin Nguyen
 * @version 4.5.0 December 2015
 */
public class Moves {
    /**
     * NTWO is used to replace a direct usage of int -2.
     */
    public static final int NTWO = -2;
    /**
     * THREE is used to replace a direct usage of int 3.
     */
    public static final int THREE = 3;
    /**
     * FIVE is used to replace a direct usage of int 5.
     */
    public static final int FIVE = 5;
    /**
     * SEVEN is used to replace a direct usage of int 7.
     */
    public static final int SEVEN = 7;
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
     * myBoard is the current board used for Tetris.
     */

    private final Board myBoard;
    /**
     * Moves is initialized and sets the passed Board as an instance field.
     * @param theBoard is a board passed from another class.
     */
    public Moves(final Board theBoard) {
        myBoard = theBoard;

    }

    /**
     * checkleft checks what piece is in use to move left.
     */
    public void checkleft() {
        final String currentPiece =
                        ((AbstractPiece) myBoard.getCurrentPiece()).getBlock().toString();
        if (T.equals(currentPiece)) {
            checkleftT();

        } else if (I.equals(currentPiece)) {
            checkleftI();

        } else if (J.equals(currentPiece)) {
            checkleftJ();

        } else if (L.equals(currentPiece)) {
            checkleftL();

        } else if (O.equals(currentPiece)) {
            checkleftO();

        } else if (S.equals(currentPiece)) {
            checkleftS();

        } else if (Z.equals(currentPiece)) {
            checkleftZ();

        }

    }

    /**
     * checkleftZ checks left for Z.
     */
    private void checkleftZ() {
        final int[][] k = ((AbstractPiece) myBoard.getCurrentPiece()).getRotation();
        if (k[2][0] == 2 && k[2][1] == 1 && myBoard.getCurrentPiece().getX() > -1) {
            myBoard.getCurrentPiece().moveLeft();

        } else if (myBoard.getCurrentPiece().getX() > 0) {
            myBoard.getCurrentPiece().moveLeft();

        }

    }

    /**
     * checkleftS checks left for S.
     */
    private void checkleftS() {
        final int[][] k = ((AbstractPiece) myBoard.getCurrentPiece()).getRotation();
        if (k[1][0] == 1 && k[1][1] == 1 && myBoard.getCurrentPiece().getX() > -1) {
            myBoard.getCurrentPiece().moveLeft();

        } else if (myBoard.getCurrentPiece().getX() > 0) {
            myBoard.getCurrentPiece().moveLeft();

        }

    }

    /**
     * checkleftO checks left for O.
     */
    private void checkleftO() {
        if (myBoard.getCurrentPiece().getX() >= 0) {
            myBoard.getCurrentPiece().moveLeft();

        }

    }

    /**
     * checkleftL checks if L could move left.
     */
    private void checkleftL() {
        final int[][] k = ((AbstractPiece) myBoard.getCurrentPiece()).getRotation();
        if (k[0][0] == 1 && k[0][1] == 2 && myBoard.getCurrentPiece().getX() > -1) {
            myBoard.getCurrentPiece().moveLeft();

        } else if (myBoard.getCurrentPiece().getX() > 0) {
            myBoard.getCurrentPiece().moveLeft();

        }

    }

    /**
     * checkleftJ checks left for J.
     */
    private void checkleftJ() {
        final int[][] k = ((AbstractPiece) myBoard.getCurrentPiece()).getRotation();
        if (k[1][0] == 2 && k[1][1] == 2 && myBoard.getCurrentPiece().getX() > -1) {
            myBoard.getCurrentPiece().moveLeft();

        } else if (myBoard.getCurrentPiece().getX() > 0) {
            myBoard.getCurrentPiece().moveLeft();

        }
    }

    /**
     * checkleftI checks if I could move left.
     */
    private void checkleftI() {
        final int[][] k = ((AbstractPiece) myBoard.getCurrentPiece()).getRotation();
        if (k[0][0] == 1 && k[0][1] == THREE && myBoard.getCurrentPiece().getX() > -1) {

            myBoard.getCurrentPiece().moveLeft();

        } else if (k[0][0] == 2 && k[0][1] == THREE 
                        && myBoard.getCurrentPiece().getX() > NTWO) {
            myBoard.getCurrentPiece().moveLeft();

        } else if (myBoard.getCurrentPiece().getX() > 0) {
            myBoard.getCurrentPiece().moveLeft();

        }

    }

    /**
     * checkleftT checks if T could move left.
     */
    private void checkleftT() {
        final int[][] k = ((AbstractPiece) myBoard.getCurrentPiece()).getRotation();
        if (k[0][0] == 1 && k[0][1] == 2 && k[1][0] == 1 && k[1][1] == 1
            && myBoard.getCurrentPiece().getX() > -1) {
            myBoard.getCurrentPiece().moveLeft();

        } else if (myBoard.getCurrentPiece().getX() > 0) {
            myBoard.getCurrentPiece().moveLeft();

        }

    }

    /**
     * checkright checks what piece is currently in use.
     */
    public void checkright() {
        final String currentPiece =
                        ((AbstractPiece) myBoard.getCurrentPiece()).getBlock().toString();
        if (T.equals(currentPiece)) {
            checkrightT();

        } else if (I.equals(currentPiece)) {
            checkrightI();

        } else if (J.equals(currentPiece)) {
            checkrightJ();

        } else if (L.equals(currentPiece)) {
            checkrightL();

        } else if (O.equals(currentPiece)) {
            checkrightO();

        } else if (S.equals(currentPiece)) {
            checkrightS();
        } else if (Z.equals(currentPiece)) {
            checkrightZ();

        }

    }

    /**
     * checkrightZ checks right for Z.
     */
    private void checkrightZ() {
        final int[][] k = ((AbstractPiece) myBoard.getCurrentPiece()).getRotation();
        if (k[0][0] == 1 && k[0][1] == 2 && myBoard.getCurrentPiece().getX() < SEVEN + 1) {
            myBoard.getCurrentPiece().moveRight();

        } else if (myBoard.getCurrentPiece().getX() < SEVEN) {
            myBoard.getCurrentPiece().moveRight();

        }

    }

    /**
     * checkrightS checks right for S.
     */
    private void checkrightS() {
        final int[][] k = ((AbstractPiece) myBoard.getCurrentPiece()).getRotation();
        if (k[1][0] == 0 && k[1][1] == 1 && myBoard.getCurrentPiece().getX() < SEVEN + 1) {
            myBoard.getCurrentPiece().moveRight();

        } else if (myBoard.getCurrentPiece().getX() < SEVEN) {
            myBoard.getCurrentPiece().moveRight();

        }

    }

    /**
     * checkrightO checks right for O.
     */
    private void checkrightO() {
        if (myBoard.getCurrentPiece().getX() < SEVEN) {
            myBoard.getCurrentPiece().moveRight();

        }

    }

    /**
     * checkrightO checks right for O.
     */
    private void checkrightL() {
        final int[][] k = ((AbstractPiece) myBoard.getCurrentPiece()).getRotation();
        if (k[0][0] == 0 && k[0][1] == 2 && myBoard.getCurrentPiece().getX() < SEVEN + 1) {
            myBoard.getCurrentPiece().moveRight();

        } else if (myBoard.getCurrentPiece().getX() < SEVEN) {
            myBoard.getCurrentPiece().moveRight();

        }

    }

    /**
     * checkrightO checks right for O.
     */
    private void checkrightJ() {
        final int[][] k = ((AbstractPiece) myBoard.getCurrentPiece()).getRotation();
        if (k[2][0] == 0 && k[2][1] == 0 && myBoard.getCurrentPiece().getX() < SEVEN + 1) {
            myBoard.getCurrentPiece().moveRight();

        } else if (myBoard.getCurrentPiece().getX() < SEVEN) {
            myBoard.getCurrentPiece().moveRight();

        }

    }

    /**
     * checkrightO checks right for O.
     */
    private void checkrightI() {
        final int[][] k = ((AbstractPiece) myBoard.getCurrentPiece()).getRotation();
        if (k[0][0] == 1 && k[0][1] == THREE && myBoard.getCurrentPiece().getX() < SEVEN + 1) {

            myBoard.getCurrentPiece().moveRight();

        } else if (k[0][0] == 2 && k[0][1] == THREE
                 && myBoard.getCurrentPiece().getX() < SEVEN) {
            myBoard.getCurrentPiece().moveRight();

        } else if (myBoard.getCurrentPiece().getX() < SEVEN - 1) {
            myBoard.getCurrentPiece().moveRight();

        }

    }

    /**
     * checkrightO checks right for O.
     */
    private void checkrightT() {
        final int[][] k = ((AbstractPiece) myBoard.getCurrentPiece()).getRotation();
        if (k[2][0] == 1 && k[2][1] == 1 && k[THREE][0] == 1 && k[THREE][1] == 0
            && myBoard.getCurrentPiece().getX() < SEVEN + 1) {
            myBoard.getCurrentPiece().moveRight();

        } else if (myBoard.getCurrentPiece().getX() < SEVEN) {
            myBoard.getCurrentPiece().moveRight();

        }

    }

    /**
     * checkrotate checks what piece is currently use, and checks if it could
     * rotate.
     */
    public void checkrotate() {
        final String currentPiece =
                        ((AbstractPiece) myBoard.getCurrentPiece()).getBlock().toString();
        if (T.equals(currentPiece)) {
            checkrotateT();
        } else if (I.equals(currentPiece)) {
            checkrotateI();
        } else if (J.equals(currentPiece)) {
            checkrotateJ();
        } else if (L.equals(currentPiece)) {
            checkrotateL();
        } else if (O.equals(currentPiece)) {
            myBoard.getCurrentPiece().rotate();

        } else if (S.equals(currentPiece)) {
            checkrotateS();
        } else if (Z.equals(currentPiece)) {
            checkrotateZ();
        }

    }

    /**
     * checkrotateZ checks if Z piece could rotate at current position.
     */
    private void checkrotateZ() {
        // 2
        final int[][] k = ((AbstractPiece) myBoard.getCurrentPiece()).getRotation();
        if (k[0][0] == 2 && k[0][1] == 2 || k[0][0] == 0 && k[0][1] == 2) {
            checkZextended();
            // 4
        } else if (k[0][0] == 1 && k[0][1] == 2 || k[0][0] == 0 && k[0][1] == 1) {
            checkZ2extended();
            // 1
        }

    }
    /**
     * checkZextended is a extend of checkrotateZ.
     */
    private void checkZextended() {
        if (myBoard.getCurrentPiece().getX() >= 0 && myBoard.getCurrentPiece().getY() > 0) {

            myBoard.getCurrentPiece().rotate();

        }
    }
    /**
     * checkZ2extended is a extend of checkrotateZ.
     */
    private void checkZ2extended() {
        if (myBoard.getCurrentPiece().getX() <= SEVEN
            && myBoard.getCurrentPiece().getY() > 0) {
            myBoard.getCurrentPiece().rotate();

        }
    }

    /**
     * checkrotateS checks if S piece could rotate at current position.
     */
    private void checkrotateS() {

        // 2
        final int[][] k = ((AbstractPiece) myBoard.getCurrentPiece()).getRotation();
        if (k[0][1] == 2 && k[1][0] == 1 || k[0][1] == 2 && k[1][0] == 2) {
            checkSextended();
            // 4
        } else if (k[0][1] == 2 && k[1][0] == 0 || k[0][1] == 1 && k[1][0] == 2) {
            checkS2extended();
            // 1
        }

    }
    /**
     * checkSextended is a extend of checkrotateS.
     */
    private void checkSextended() {
        if (myBoard.getCurrentPiece().getX() >= 0 && myBoard.getCurrentPiece().getY() > 0) {

            myBoard.getCurrentPiece().rotate();

        }
    }
    /**
     * checkS2extended is a extend of checkrotateS.
     */
    private void checkS2extended() {
        if (myBoard.getCurrentPiece().getX() <= SEVEN
            && myBoard.getCurrentPiece().getY() > 0) {
            myBoard.getCurrentPiece().rotate();

        }
    }

    /**
     * checkrotateL checks if L piece could rotate at current position.
     */
    private void checkrotateL() {
        // 2
        final int[][] k = ((AbstractPiece) myBoard.getCurrentPiece()).getRotation();
        if (k[0][0] == 2 && k[0][1] == 2 || k[0][0] == 1 && k[0][1] == 2) {
            checkLextended();

            // 4
        } else if (k[0][0] == 0 && k[0][1] == 2 || k[0][0] == 0 && k[0][1] == 1) {
            checkL2extended();

        }
    }

    /**
     * checkLextended is a extend of checkrotateL.
     */
    private void checkLextended() {
        if (myBoard.getCurrentPiece().getX() >= 0 && myBoard.getCurrentPiece().getY() > 0) {
            myBoard.getCurrentPiece().rotate();

        }

    }

    /**
     * checkL2extended is a extend of checkrotateL.
     */
    private void checkL2extended() {
        if (myBoard.getCurrentPiece().getX() <= SEVEN
            && myBoard.getCurrentPiece().getY() > 0) {
            myBoard.getCurrentPiece().rotate();

        }
        // 1
    }

    /**
     * checkrotateJ checks if J piece could rotate at current position.
     */
    private void checkrotateJ() {
        // 2
        final int[][] k = ((AbstractPiece) myBoard.getCurrentPiece()).getRotation();
        if (k[0][1] == 2 && k[1][0] == 0 || k[0][1] == 2 && k[1][0] == 2) {
            checkJextended();
            // 4
        } else if (k[0][1] == 1 && k[1][0] == 1 || k[0][1] == 2 && k[1][0] == 1) {
            checkJ2extended();
            // 1
        }

    }

    /**
     * checkJextended is a extend of checkrotateJ.
     */
    private void checkJextended() {
        if (myBoard.getCurrentPiece().getX() >= 0 && myBoard.getCurrentPiece().getY() > 0) {

            myBoard.getCurrentPiece().rotate();

        }
    }

    /**
     * checkJ2extended is a extend of checkrotateJ.
     */
    private void checkJ2extended() {
        if (myBoard.getCurrentPiece().getX() <= SEVEN
            && myBoard.getCurrentPiece().getY() > 0) {
            myBoard.getCurrentPiece().rotate();

        }
    }
    /**
     * checkrotateI checks rotation for I.
     */
    private void checkrotateI() {
        // 2
        final int[][] k = ((AbstractPiece) myBoard.getCurrentPiece()).getRotation();
        if (k[0][0] == 0 && k[0][1] == 2 || k[0][0] == 2 && k[0][1] == THREE) {
            checkIextended();
            // 4
        } else if (k[0][0] == 1 && k[0][1] == THREE || k[0][0] == 0 && k[0][1] == 1) {
            checkI2extended();
            // 1
        }

    }
    /**
     * checkI2extended is a extend of checkrotateI.
     */
    private void checkIextended() {
        if (myBoard.getCurrentPiece().getX() >= 0 && myBoard.getCurrentPiece().getY() > 0) {

            myBoard.getCurrentPiece().rotate();

        }
    }
    /**
     * checkI2extended is a extend of checkrotateI.
     */
    private void checkI2extended() {
        if (myBoard.getCurrentPiece().getX() <= FIVE && myBoard.getCurrentPiece().getY() > 0) {
            myBoard.getCurrentPiece().rotate();

        }
    }
    /**
     * checkrotateT checks rotation for T.
     */
    private void checkrotateT() {
        // 2
        final int[][] k = ((AbstractPiece) myBoard.getCurrentPiece()).getRotation();
        if (k[2][0] == 1 && k[2][1] == 1 && k[THREE][0] == 2 && k[THREE][1] == 1
            || k[2][0] == 2 && k[2][1] == 1 && k[THREE][0] == 1 && k[THREE][1] == 0) {
            checkTextended();
            // 4
        } else {
            checkT2extended(k);
        }

    }
    /**
     * checkTextended is a extend of checkrotateT.
     */
    private void checkTextended() {
        if (myBoard.getCurrentPiece().getX() >= 0 && myBoard.getCurrentPiece().getY() > 0) {

            myBoard.getCurrentPiece().rotate();

        }
    }
    /**
     * checkT2extended is a extend of checkrotateT.
     * @param theRotation continues checking for T, theRotation is also passed.
     */
    private void checkT2extended(final int[][] theRotation) {
        if (theRotation[0][0] == 0 && theRotation[0][1] == 1
            || theRotation[2][0] == 1 && theRotation[2][1] == 1 && theRotation[THREE][0] == 1 
            && theRotation[THREE][1] == 0) {
            checkT3extended();
           
            // 1
        }
    }
    /**
     * checkT3extended is a extend of checkrotateT.
     */
    private void checkT3extended() {
        if (myBoard.getCurrentPiece().getX() <= SEVEN
                        && myBoard.getCurrentPiece().getY() > 0) {
            myBoard.getCurrentPiece().rotate();

        }
        
    }

}
