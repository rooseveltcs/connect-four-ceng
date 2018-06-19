public class GameBoard{

   private int boardWidth;
   private int boardHeight;
   char[][] GameBoard;
   
   //Constructor for the GameBoard
   public GameBoard(int inputWidth, int inputHeight) {
      boardWidth = inputWidth;
      boardHeight = inputHeight;
      char[][] createBoard = new char[boardWidth][boardHeight];
      GameBoard = createBoard;
      for(int rowCount = 0; rowCount < boardHeight; rowCount++) {
         for(int columnCount = 0; columnCount < boardWidth; columnCount++) {
            GameBoard[columnCount][rowCount] = '_';
         }
      }
   }
   
   //Prints the GameBoard
   public String toString() {
      String board = "";
      for(int rowCount = 0; rowCount < boardHeight; rowCount++) {
         board += "|";
         for(int columnCount = 0; columnCount < boardWidth; columnCount++) {
            String valueInBoard = String.valueOf(GameBoard[columnCount][rowCount]);
            board += "_";
            board += valueInBoard;
            board += "_";
            board += "|";
         }
         board += "\n";
      }
      for(int numberCount = 1; numberCount <= boardWidth; numberCount++) {
         if(numberCount < 10) {
            board += " ";
         }
         board += " " + numberCount + " ";
      }
      board += "\n";
      return board;   
   }
   
   //Retrieves the value at a given space
   public char charAt(int column, int row) {
      return GameBoard[column][row];
   }
   
   //Adds a chip to a specified location
   public void addChip(char playerChip, int column, int row) {
      GameBoard[column][row] = playerChip;
   }
   
   //Find the location of a chip in the GameBoard
   public int[] findChipLocation(char playerChip) {
      int[] chipLocation = {-1, -1};
      for(int rowCount = 0; rowCount < boardHeight; rowCount++) {
         for(int columnCount = 0; columnCount < boardWidth; columnCount++) {
            if(GameBoard[columnCount][rowCount] == playerChip) {
               chipLocation[0] = columnCount;
               chipLocation[1] = rowCount;
               return chipLocation;            
            }
         }
      }
      return chipLocation;
   }
   
   //Checks a chip to see if there is four in a row horizontally
   public boolean horizontal(char playerChip) {
      int[] chipLocation = findChipLocation(playerChip);
      int columnLocation = chipLocation[0];
      int rowLocation = chipLocation[1];
      for(int checkForFour = 1; checkForFour < 4; checkForFour++) {
         if(columnLocation + checkForFour >= boardWidth) {
            return false;
         } else if(GameBoard[columnLocation + checkForFour][rowLocation] != playerChip) {
            return false;
         }
      }
      return true;
   }
   
   public boolean vertical(char playerChip) {
      int[] chipLocation = findChipLocation(playerChip);
      int columnLocation = chipLocation[0];
      int rowLocation = chipLocation[1];
      for(int checkForFour = 1; checkForFour < 4; checkForFour++) {
         if(rowLocation + checkForFour >= boardHeight) {
            return false;
         } else if(GameBoard[columnLocation][rowLocation + checkForFour] != playerChip) {
            return false;
         }
      }
      return true;
   }
   
   public boolean diagonalLeft(char playerChip) {
      int[] chipLocation = findChipLocation(playerChip);
      int columnLocation = chipLocation[0];
      int rowLocation = chipLocation[1];
      for(int checkForFour = 1; checkForFour < 4; checkForFour++) {
         if(rowLocation + checkForFour >= boardHeight || columnLocation - checkForFour < 0) {
            return false;
         } else if(GameBoard[columnLocation - checkForFour][rowLocation + checkForFour] != playerChip) {
            return false;
         }
      }
      return true;
   }
   
   public boolean diagonalRight(char playerChip) {
      int[] chipLocation = findChipLocation(playerChip);
      int columnLocation = chipLocation[0];
      int rowLocation = chipLocation[1];
      for(int checkForFour = 1; checkForFour < 4; checkForFour++) {
         if(rowLocation + checkForFour >= boardHeight || columnLocation + checkForFour >= boardWidth) {
            return false;
         } else if(GameBoard[columnLocation + checkForFour][rowLocation + checkForFour] != playerChip) {
            return false;
         }
      }
      return true;
   }
}