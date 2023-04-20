import java.util.Scanner;
import java.util.*;
import java.util.Arrays;
public class ThirdStage {
    static Scanner scanner = new Scanner(System.in);
    public static void printBoard(char[][] board) {
        System.out.println("---------");
        for (char[] cordinate : board) {
            for (int i = 0; i < cordinate.length; i++) {
                if (i == 0) {
                    System.out.print("| ");
                    System.out.print(cordinate[i] + " ");
                } else if (i == cordinate.length - 1) {
                    System.out.print(cordinate[i]);
                    System.out.print(" |");
                } else {
                    System.out.print(cordinate[i] + " ");
                }
            }
            System.out.println();
        }
        System.out.println("---------");
    }

    //-----------------------------------------------------
    public static String whoWins(char[][] board){
        String state = "";

        //checking if the board is fulled
        int xMoves = 0;
        int oMoves = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == 'X') {
                    xMoves++;
                }
                if (board[i][j] == 'O') {
                    oMoves++;
                }
            }
        }//for

        boolean isFullBoard = (xMoves + oMoves) == 9;

        boolean xWins = false;
        boolean oWins = false;

        for (int a = 0; a < 8; a++) {
            String line = null;
            switch (a) {
                case 0:
                    line = "" + board[0][0] + board[0][1] + board[0][2];
                    break;
                case 1:
                    line = "" + board[1][0] + board[1][1] + board[1][2];
                    break;
                case 2:
                    line = "" + board[2][0] + board[2][1] + board[2][2];
                    break;
                case 3:
                    line = "" + board[0][0] + board[1][0] + board[2][0];
                    break;
                case 4:
                    line = "" + board[0][1] + board[1][1] + board[2][1];
                    break;
                case 5:
                    line = "" + board[0][2] + board[1][2] + board[2][2];
                    break;
                case 6:
                    line = "" + board[0][0] + board[1][1] + board[2][2];
                    break;
                case 7:
                    line = "" + board[0][2] + board[1][1] + board[2][0];
                    break;
            }//switch

            if (line.equals("XXX")) {
                xWins = true;
            }
            if (line.equals("OOO")) {
                oWins = true;
            }

        }//for

        //Decisions
        if ((xWins && oWins) || (xMoves - oMoves >= 2 || oMoves - xMoves >= 2)) {
            return "Impossible";
        }
        if (!xWins && !oWins && !isFullBoard) {
            return "Game not finished";
        }

        if (!xWins && !oWins && isFullBoard) {
            return "Draw";
        }

        if (xWins && !oWins) {
            return "X wins";
        }

        if (!xWins && oWins) {
            return "O wins";
        }


        return state;


    }//getState
    //-------------------------------------------------------------------
    public static void main(String[] args){
        char[][] board = new char[3][3];
        String input = scanner.next();

        int l = 0; //for getting the character

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length && l < input.length(); j++, l++) {
                board[i][j] = input.charAt(l);
            }
        }

        String game = whoWins(board);
        printBoard(board);
        System.out.println(game);

    }

}
