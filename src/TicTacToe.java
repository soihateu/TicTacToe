import java.io.PrintStream;
import java.util.Scanner;

public class TicTacToe 
{
	
	private static final char[] mark = { 'X', 'O' };
	
	private char[] board;
	private int player;
	private Scanner scanner;
	private PrintStream stream;
	
	TicTacToe(int player, PrintStream stream)
	{
		this.player = player;
		this.stream = stream;
		
		scanner = new Scanner(System.in);
		
		board = new char[10];
		
		for (int i = 0; i < 10; i++)
		{
			board[i] = (char) (i + '0');
		}
	}
	
	/***********************************************************************/
	/* Displays the board, the numbers on each block and the current player */
	/***********************************************************************/
	public void printBoard()
	{
	    stream.println("     |     |     ");
	    stream.println("  " + board[1] + "  |  " + board[2] + "  |  " + board[3]);
	    stream.println( "_____|_____|_____");
	    stream.println("     |     |     ");
	    stream.println("  " + board[4] + "  |  " + board[5] + "  |  " + board[6]);
	    stream.println("_____|_____|_____");
	    stream.println("     |     |     " );
	    stream.println( "  " + board[7] + "  |  " + board[8] + "  |  " + board[9] );
	    stream.println( "     |     |     " );
		
		int winCondition = checkWinCondition();
		
		if (winCondition > 0)
		{
			if (winCondition == 3)
			{
				stream.println("Tie!");
			}
			else
			{
			    // Once the game has finished, print the final board and declare winner
			    player--;      // Fixes error when displaying winner.
			    stream.println( "Player " + player + " (" + mark[player-1] + ")" + " has won the game!");
			}
		
			scanner.close();
		}	
	}

	// 0 = game still ongoing, 1 = player 1 won, 2 = player 2 won, 3 = tie
	public int checkWinCondition()
	{		
	    if (board[1] == board[2] && board[2] == board[3])
	    {
	    	if (board[1] == mark[0])
	    	{
	    		return 1;
	    	}
	    	else
	    	{
	    		return 2;
	    	}
	    }
	    
	    if (board[4] == board[5] && board[5] == board[6])
	    {
	    	if (board[4] == mark[0])
	    	{
	    		return 1;
	    	}
	    	else
	    	{
	    		return 2;
	    	}
	    }
	    
	    if (board[7] == board[8] && board[8] == board[9])
	    {
	    	if (board[7] == mark[0])
	    	{
	    		return 1;
	    	}
	    	else
	    	{
	    		return 2;
	    	}
	    }
	    
	    if (board[1] == board[4] && board[4] == board[7])
	    {
	    	if (board[1] == mark[0])
	    	{
	    		return 1;
	    	}
	    	else
	    	{
	    		return 2;
	    	}
	    }
	    
	    if (board[2] == board[5] && board[5] == board[8])
	    {
	    	if (board[2] == mark[0])
	    	{
	    		return 1;
	    	}
	    	else
	    	{
	    		return 2;
	    	}
	    }
	    
	    if (board[3] == board[6] && board[6] == board[9])
	    {
	    	if (board[3] == mark[0])
	    	{
	    		return 1;
	    	}
	    	else
	    	{
	    		return 2;
	    	}
	    }
	    
	    if (board[1] == board[5] && board[5] == board[9])
	    {
	    	if (board[1] == mark[0])
	    	{
	    		return 1;
	    	}
	    	else
	    	{
	    		return 2;
	    	}
	    }
	    
	    if (board[3] == board[5] && board[5] == board[7])
	    {
	    	if (board[3] == mark[0])
	    	{
	    		return 1;
	    	}
	    	else
	    	{
	    		return 2;
	    	}
	    }
	    
	    for (int i = 1; i < 10; i++)
	    {
	    	if (board[i] != mark[0] && board[i] != mark[1])
	    	{
	    		return 0;
	    	}
	    }
	    
	    return 3;
	}
	
	public void getPlayerInput()
	{
		String input;
		
        //Check to see if player is 1 or 2. Uses modulo and increments the player counter by 1 every time a turn ends.
        if (player % 2 == 1)
        {
            player = 1;
        }
        else
        {
            player = 2;
        }

        // Take user input and ensures player turn is not skipped if invalid input.
        do
        {
            stream.println("Player " + player + " (" + mark[player-1] + ")" + ", Enter a number: ");
            input = scanner.next();
        } while (!isPlayerInputValid(input));

        player++;

	}
	
	
	/***********************************************************************************************/
	/* Ensures that the input is within 0 to 9 and prevents any input that has already been marked */
	/***********************************************************************************************/
	private boolean isPlayerInputValid(String stringInput)
	{
		int input;
		
		// Verify if input is an integer
		try
		{
			input = Integer.parseInt(stringInput);
		}
		catch (NumberFormatException e)
		{
			stream.println("Invalid input. Please enter a number between 1 to 9 and a square that is not already marked!");
			return false;
		}
		
		// Verify input is between 1-9 and not on a marked box
	    if (input > 9 || input < 0 || board[input] == mark[0] || board[input] == mark[1])
	    {  
	    	stream.println("Invalid input. Please enter a number between 1 to 9 and a square that is not already marked!");
	        return false;
	    }
	    else
	    {
	    	// Replaces the index in the board with the respective mark.
	        board[input] = mark[player-1];
	        return true;
	    }
	}
	
	public static void main(String[] args) 
	{
	    boolean isGameOver = false;

		TicTacToe game = new TicTacToe(1, System.out);

	    while (!isGameOver)
	    {
	        game.printBoard();
	        game.getPlayerInput();
	        
	        isGameOver = (game.checkWinCondition() > 0);
	    }
	    
	    game.printBoard();
	}
}