// Riya Affrin
//4/15/24
//CSE 123 BG
//C1: Abstract Strategy Games:ConnectFour

//This is Connect Four. This is a game where two players try getting four of their tokens in a row
//The tokens need to either vertical, horizontal, or diagonal in order to win. 
import java.util.*;

public class ConnectFour extends AbstractStrategyGame{
    private String yellow;
    private String red;
    private String [][]board;
    private boolean yellowTurn;

    //in this method there is noting returned or passed in, we are simply setting our board size
    // and setting our strings as their tokens. 
    public ConnectFour(){
        board = new String [6][7];
        this.red = "❤️";
        this.yellow = "💛";
        this.yellowTurn = true;
    }

    //This is the instructions method. Here we return a string which is the set of
    //instructions for the game. When this method is called it will return the instructions.
    // To print the instructions we call the method in a print statement

    public String instructions(){
        return "Connect four is a two player game. Each player will take turns putting" +
        " in one token into their chosen slot. The first player who has four of their tokens" +
        " of their tokens in a row either veritically, horizontally, or" +
        " diagonally wins.If no player reaches four in a row, the game is tied 😬";
    }
    
    
    //In make move we are not returning anything but passing in a scanner. The purpose of this
    //method is to let the player place their token in a chosen column. If the chosen spot is not
    //on the board then an IllegalArgumentException will be thrown letting the user know they
    //can't place their token there.
    public void makeMove(Scanner console){
        //here I am asking for the area which the user wants to place their heart
        System.out.println("Which column? ");
        
        int col = console.nextInt()-1;
        
       //This where the hearts get dropped into each column
        if(yellowTurn ==  true){
            helperMakeMove(col, this.yellow);

        }else{
            helperMakeMove(col, this.red);
        }
        
        

    }


    //This is the makeMove helper method. This method passes in int col and String color
    //We pass this in so we know who is trying to go where in the 2D array
    //to make sure the user isn't putting in their token in a spot not in the board 
    //we throw an IllegalArgumentException

    private void helperMakeMove(int col, String color){
        //This is where we throw the IllegalArgumentException. If the player 
        //chooses a column less than 0 or 
        //greater than 7 throw an IllegalArgumentException because its not in the board
        if(col < 0 || col > 7){
            throw new IllegalArgumentException();

        }

        int rows = 5;
        //here we are checking where the token will land in each row
        while(rows < board.length && board[rows][col] != null){
            rows --;
        }
        //if the row is less than 0 we throw an IllegalArgumentException because it cannot 
        //be negative
        if(rows <0){
            throw new IllegalArgumentException();

        }

        board[rows][col] = color;
        //this is showing that it is no longer yellows turn
        this.yellowTurn = !this.yellowTurn;
    }
    
    /*This is the getWinner method. Here we run the tokens placed in the board by difference 
    scenarios to check if there is a winner. Notice how here the method is not void and returns
    an int value and passes in nothing. We do this because depending on which number is returned
    we will have player one or player two as the winner. If the number 1 is returned then player
    one wins, if the number two is returned then player 2 wins, if no one wins the number -1 will
    be returned as the game is a tie.The different tests that we do to check if there is a
     winner are vertical,horizontal, and diagonal.These are the ways a person can win
    the game. The first player to get four in a row like this wins the game*/
    
    public int getWinner(){
       

        //This is checking if a player has won horizontally
        for(int i = 0; i < board.length; i++){
            for(int j = 0; j < 4; j++){
                if(board[i][j] != null && board[i][j] == board[i][j+1] && board[i][j+1] == 
                board[i][j+2] && board[i][j+2] == board[i][j+3]){
                    if(board[i][j].equals(this.red)){
                        return 2;
                    }else{
                        return 1;
                    }
                }
            }
        }
       
        
        //this is checking if a player won veritically
        for(int i = 0; i< board[0].length;i++){
            for(int j = 0; j < 3; j++){
                if(board[j][i] != null && board[j][i] == board[j+1][i] && board[j+1][i] == 
                board[j+2][i] && board[j+2][i] == board[j+3][i]){
                    if(board[j][i].equals(this.red)){
                        return 2;
                    }else{
                        return 1;
                    }
                }
            }
        }
        
        //checking for diagonal win in this direction \

        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 4; j++){
                if(board[i][j] != null && board[i][j] == board[i+1][j+1] && board[i+1][j+1] == 
                board[i+2][j+2] && board[i+2][j+2] == board[i+3][j+3]){
                    if(board[i][j].equals(this.red)){
                    return 2;
                    }else{
                        return 1;
                    }
                }
            }
        }
       
        //checks for the diagonal win in this direction /
        for(int i = board.length -1; i> 2; i--){
            for(int j = 0; j<4; j++){
                if(board[i][j] != null && board[i][j] == board[i-1][j+1] && board[i-1][j+1] == 
                board[i-2][j+2] && board[i-2][j+2] == board[i-3][j+3]){
                    if(board[i][j].equals(this.red)){
                        return 2;
                    }else{
                        return 1;
                    }
                }
            }
        }
        return -1;
    

    }

    
    //in this method we are returning a boolean, and not passing in any paramaters.
    // This method determines the current state of the game.
    // It returns true if a player has won, false if the game is a draw, 
    // or indicates that the game is still ongoing.
 
    public boolean isGameOver() {
        int winner = getWinner();

        if(winner != -1){
            return true;
        }
        
        for(int row = 0; row<6; row++){
            for(int col = 0; col <7; col++){
                if(board[row][col] == null){
                    return false;
                }

            }
        }
        return true;
    }


    //This is the get player method we use this to check if it is a new players turn. We return
    //an int because that tells us if it is red or yellows turn. If it is
    // 1 its red's turn 0 is yellow's turn 
    public int  getNextPlayer(){
        if(yellowTurn == false){
            return 2;
        }
        return 1;
    }

    //this method returns a string and passes in no paramaters. The purpose of this method is
    //to create a representation of the board and fill the empty spaces with white hearts. 
    public String toString(){
        String end = "";
        for(int i = 0; i < board.length; i++){
            for(int j = 0; j < board[0].length; j++){
                if(board[i][j] != null){
                    end += board[i][j];

                }else{
                    end += "🤍";

                }
                end += " ";
            }
            end += "\n";
        }
    return end;
    }
}
