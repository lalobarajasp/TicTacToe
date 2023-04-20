import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
public class FifthStage {
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

    //Regular Expressions
    // \\d - single digit from 0 - 9. In this example \d is the regular expression; the extra backslash is required for the code to compile. The test harness reads the expressions directly from the Console, however, so the extra backslash is unnecessary.
    // \\s - single whitespace
    public static boolean makeMove(String move, char[][] board, char symbol) {
        if (!move.matches("\\d\\s\\d")) {
            System.out.println("You should only use numbers!");
            return false;
        }

        //delete space to match with the switch condition
        move = move.replaceAll("\\s", "");

        for (char c : move.toCharArray()) {
            int n = Character.getNumericValue(c);
            if (n > 3 || n < 1) {
                System.out.println("Coordinates should be from 1 to 3");
                return false;
            }
        }

        int coordinates = Integer.parseInt(move);

        switch (coordinates) {
            case 11:
                return emptyCell(board, 0, 0, symbol);
            case 12:
                return emptyCell(board, 0, 1 , symbol);
            case 13:
                return emptyCell(board, 0, 2, symbol);
            case 21:
                return emptyCell(board, 1, 0, symbol);
            case 22:
                return emptyCell(board, 1, 1, symbol);
            case 23:
                return emptyCell(board, 1, 2, symbol);
            case 31:
                return emptyCell(board, 2, 0, symbol);
            case 32:
                return emptyCell(board, 2, 1, symbol);
            case 33:
                return emptyCell(board, 2, 2, symbol);
            default:
                return false;
        }
    }

    public static boolean emptyCell(char[][] board, int i1, int i2, char symbol) {
        if (board[i1][i2] == 'X' || board[i1][i2] == 'O') {
            System.out.println("This cell is occupied! Choose another one!");
            return false;
        }

        board[i1][i2] = symbol;
        return true;
    }

    public static String whoWins(char[][] board) {
        String state = "";

        //see if the board is completed
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
        }

        boolean isFullBoard = (xMoves + oMoves) == 9;

        //see if X or O wins
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
            }
            if (line.equals("XXX")) {
                xWins = true;
            }
            if (line.equals("OOO")) {
                oWins = true;
            }
        }

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
    }

    //-------------------------------------------------------------------
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        char[][] board = new char[3][3];

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = ' ';
            }
        }

        printBoard(board);
        whoPlays player = new whoPlays('U');
        boolean canMakeMove = false;
        boolean exit = false;
        String game = whoWins(board);

        do {
            player.changePlayer();
            do {
                char cPlayer = player.getSymbol();
                System.out.println(cPlayer);
                System.out.print("Enter the coordinates: ");
                String move = br.readLine();

                canMakeMove = makeMove(move, board, cPlayer);

            } while (!canMakeMove);
            printBoard(board);
            game = whoWins(board);

            if(!game.equals("Game not finished")){
                exit = true;
            }
        } while (!exit);
        System.out.println(game);

    }//main method
}//class

//-----------------------------------------------------

class whoPlays {
    //Attributes
    char symbol;
    //Constructor
    public whoPlays(char symbol) {
        this.symbol = symbol;
    }
    //Getters and Setters
    public char getSymbol() {
        return symbol;
    }
    public void setSymbol(char symbol) {
        this.symbol = symbol;
    }

    //Method to change player
    public void changePlayer(){
        switch (this.symbol){
            case 'U':
            case 'O':
                this.setSymbol('X');
                break;
            case 'X':
                this.setSymbol('O');
                break;
        }
    }


}
