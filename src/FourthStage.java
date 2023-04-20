import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.Integer;
public class FourthStage {
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
    //Regular Expressions
    // \\d - single digit from 0 - 9. In this example \d is the regular expression; the extra backslash is required for the code to compile. The test harness reads the expressions directly from the Console, however, so the extra backslash is unnecessary.
    // \\s - single whitespace
    public static boolean makeMove(String move, char[][] board, char symbol) {

        if (!move.matches("\\d\\s\\d")) {
            System.out.println("You should enter numbers!");
            return false;
        }

        //delete space to match with the switch condition
        move = move.replaceAll("\\s", "");

        for (char c : move.toCharArray()) {
            int n = Character.getNumericValue(c);
            if (n > 3 || n < 1) {
                System.out.println("Coordinates should be from 1 to 3!");
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
        String result = "";
        if(xMoves + oMoves == 9){
            //Is it completed?
            result = "true";
        }else {
            result = "false";
        }
        return result;
    }

    //----------------------------------------------------
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedReader brr = new BufferedReader(new InputStreamReader(System.in));
        char[][] board = new char[3][3];
        String input = br.readLine();

        int l = 0; //for getting the character

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length && l < input.length(); j++, l++) {
                board[i][j] = input.charAt(l);

            }
        }

        printBoard(board);
        alonePlayer aloneplayer = new alonePlayer('U');
        boolean canMakeMove = false;
        boolean exit = false;

        do {

            aloneplayer.changePlayer();

            do {
                char cPlayer = aloneplayer.getSymbol();
                String move = brr.readLine();
                canMakeMove = makeMove(move, board, cPlayer);
                //Is it comply every condition?
            } while (!canMakeMove);

            printBoard(board);
            String game = whoWins(board);
            if(!game.equals("false")){
                exit = true;
            }

            //Is there cells to fill?
        } while (!exit);


    }//main method
}//class

//-----------------------------------------
class alonePlayer {
    //Attributes
    char symbol;
    //Constructor
    public alonePlayer(char symbol) {
        this.symbol = symbol;
    }
    //Getters and Setters
    public char getSymbol() {
        return symbol;
    }
    public void setSymbol(char symbol) {
        this.symbol = symbol;
    }

    //Method to select own player
    public void changePlayer(){
        if(this.symbol == 'U'){
            this.setSymbol('X');
        }
    }

}
//-----------------------------------------


