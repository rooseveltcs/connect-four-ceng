import java.util.Scanner;

public class GameMain {
   
   public static final char PLAYER_1_CHIP = 'x';
   public static final char PLAYER_2_CHIP = 'o';
   public static final int BOARD_WIDTH = 7;
   public static final int BOARD_HEIGHT = 6;
   
   public static void main(String[] args) {
      boolean playAgain = true;
      System.out.println("Welcome to Connect Four");
      while(playAgain == true) {
         playMultiplayer();
         System.out.println("Would you like to play again?");
         Scanner console = new Scanner(System.in);
         String answer = console.next();
         answer.toLowerCase();
         if(!(answer.substring(0, 1).equals("y"))) {
            playAgain = false;
            System.out.println("Thanks for playing Connect Four!");
         }
      }
   }
   
   //Executes a game of Connect Four
   public static void playMultiplayer() {
      GameBoard board = new GameBoard(BOARD_WIDTH, BOARD_HEIGHT);
      Scanner console = new Scanner(System.in);
      boolean gameOver = false;
      int turnCounter = 0;
      char[] playerChips = {PLAYER_1_CHIP, PLAYER_2_CHIP};
      System.out.println("Player 1 starts");
      while(gameOver == false) {
         int currentPlayer = (turnCounter) % 2;
         System.out.println();
         boolean doneWithTurn = false;
         boolean hasPlacedChip = false;
         while(doneWithTurn == false) {
            System.out.println("Player " + (currentPlayer + 1) + "'s turn : " + playerChips[currentPlayer]);
            System.out.println(board.toString());
            System.out.println("Where would you like to place your chip?");
            int chosenXCoord = console.nextInt() - 1;
            if(chosenXCoord >= BOARD_WIDTH) {
               System.out.println("Please input a vaild number");
            } else if(board.charAt(chosenXCoord, 0) != '_') {
               System.out.println();
               System.out.println("This row is full. Please choose a different row");
            } else if(board.charAt(chosenXCoord, 0) == '_') {
               int currentRow = BOARD_HEIGHT - 1;
               while(hasPlacedChip == false) {
                  if(board.charAt(chosenXCoord, currentRow) == '_') {
                     board.addChip(playerChips[currentPlayer], chosenXCoord, currentRow);
                     hasPlacedChip = true;
                     doneWithTurn = true;
                     turnCounter++;
                  }
                  currentRow--;  
               }
            }
            if(turnCounter != 0) {
               boolean vertical = board.vertical(playerChips[currentPlayer]);
               boolean horizontal = board.horizontal(playerChips[currentPlayer]);
               boolean diagonalLeft = board.diagonalLeft(playerChips[currentPlayer]);
               boolean diagonalRight = board.diagonalRight(playerChips[currentPlayer]);
               if(horizontal || vertical || diagonalLeft || diagonalRight) {
                  System.out.println(board.toString());
                  System.out.println("Player " + (currentPlayer + 1) + " wins!");
                  gameOver = true;
               }
            }
        } 
      }
  } 
}
