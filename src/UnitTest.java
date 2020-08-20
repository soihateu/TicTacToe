import static org.junit.Assert.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

import org.junit.Test;

public class UnitTest {

	@Test
	public void printBoard_StartCase()
	{
		// Test board output for the start of the game
		
		// Setup
		String expected = "     |     |     \r\n" + 
						  "  1  |  2  |  3\r\n" + 
				          "_____|_____|_____\r\n" + 
				          "     |     |     \r\n" + 
				          "  4  |  5  |  6\r\n" + 
				          "_____|_____|_____\r\n" + 
				          "     |     |     \r\n" + 
				          "  7  |  8  |  9\r\n" + 
				          "     |     |     \r\n";
		
		ByteArrayOutputStream outSpy = new ByteArrayOutputStream();
		TicTacToe game = new TicTacToe(1, new PrintStream(outSpy));
		
		// Call function to test
		game.printBoard();
		
		// Assert
		assertEquals(expected, outSpy.toString());
	}
	
	@Test
	public void printBoard_XOCase()
	{
		// Test board output with player 1 (X) moving first
		// 1) Player 1 select 1
		// 2) Player 2 selects 7
		
		// Setup
		String expected = "     |     |     \r\n" + 
						  "  X  |  2  |  3\r\n" + 
				          "_____|_____|_____\r\n" + 
				          "     |     |     \r\n" + 
				          "  4  |  5  |  6\r\n" + 
				          "_____|_____|_____\r\n" + 
				          "     |     |     \r\n" + 
				          "  O  |  8  |  9\r\n" + 
				          "     |     |     \r\n";
		
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		InputStream inBackup = System.in;
		ByteArrayInputStream in = new ByteArrayInputStream(("1" + System.lineSeparator() + "7").getBytes());
		System.setIn(in);
		
		// Setup the game
		TicTacToe game = new TicTacToe(1, new PrintStream(out));
		game.getPlayerInput();
		game.getPlayerInput();
		out.reset();
		
		// Call function to test
		game.printBoard();
		
		// Assert
		assertEquals(expected, out.toString());
		
		// Reset back to normal
		System.setIn(inBackup);
	}
	
	@Test
	public void printBoard_OXCase()
	{
		// Test board output with player 2 (O) moving first
		// 1) Player 2 select 2
		// 2) Player 1 selects 6
		
		// Setup
		String expected = "     |     |     \r\n" + 
						  "  1  |  O  |  3\r\n" + 
				          "_____|_____|_____\r\n" + 
				          "     |     |     \r\n" + 
				          "  4  |  5  |  X\r\n" + 
				          "_____|_____|_____\r\n" + 
				          "     |     |     \r\n" + 
				          "  7  |  8  |  9\r\n" + 
				          "     |     |     \r\n";
		
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		InputStream inBackup = System.in;
		ByteArrayInputStream in = new ByteArrayInputStream(("2" + System.lineSeparator() + "6").getBytes());
		System.setIn(in);
		
		// Setup the game
		TicTacToe game = new TicTacToe(2, new PrintStream(out));
		game.getPlayerInput();
		game.getPlayerInput();
		out.reset();
		
		// Call function to test
		game.printBoard();
		
		// Assert
		assertEquals(expected, out.toString());
		
		// Reset back to normal
		System.setIn(inBackup);
	}

	@Test
	public void checkWinCondition_OngoingCase()
	{
		// Test win condition returns ongoing case (0)
		// 1) Player 1 select 1
		// 2) Player 2 selects 7
		// Game is still ongoing
		
		InputStream inBackup = System.in;
		ByteArrayInputStream in = new ByteArrayInputStream(("1" + System.lineSeparator() + "7").getBytes());
		System.setIn(in);
		
		// Setup the game
		TicTacToe game = new TicTacToe(1, System.out);
		game.getPlayerInput();
		game.getPlayerInput();
		
		// Assert
		assertEquals(0, game.checkWinCondition());
		
		// Reset back to normal
		System.setIn(inBackup);
	}
	
	@Test
	public void checkWinCondition_P1WinCase()
	{
		// Test win condition returns player 1 win case (1)
		// 1) Player 1 select 1
		// 2) Player 2 selects 4
		// 3) Player 1 selects 2
		// 4) Player 2 selects 5
		// 5) Player 1 selects 3
		// Player 1 wins with all X's in top row
		
		InputStream inBackup = System.in;
		ByteArrayInputStream in = new ByteArrayInputStream(("1" + System.lineSeparator() + "4" + System.lineSeparator() + "2"
				+ System.lineSeparator() + "5" + System.lineSeparator() + "3").getBytes());
		System.setIn(in);
		
		// Setup the game
		TicTacToe game = new TicTacToe(1, System.out);
		game.getPlayerInput();
		game.getPlayerInput();
		game.getPlayerInput();
		game.getPlayerInput();
		game.getPlayerInput();


		// Assert
		assertEquals(1, game.checkWinCondition());
		
		// Reset back to normal
		System.setIn(inBackup);
	}
	
	@Test
	public void checkWinCondition_P2WinCase()
	{
		// Test win condition returns player 2 win case (2)
		// 1) Player 2 select 1
		// 2) Player 1 selects 4
		// 3) Player 2 selects 2
		// 4) Player 1 selects 5
		// 5) Player 2 selects 3
		// Player 2 wins with all X's in top row
		
		InputStream inBackup = System.in;
		ByteArrayInputStream in = new ByteArrayInputStream(("1" + System.lineSeparator() + "4" + System.lineSeparator() + "2"
				+ System.lineSeparator() + "5" + System.lineSeparator() + "3").getBytes());
		System.setIn(in);
		
		// Setup the game
		TicTacToe game = new TicTacToe(2, System.out);
		game.getPlayerInput();
		game.getPlayerInput();
		game.getPlayerInput();
		game.getPlayerInput();
		game.getPlayerInput();


		// Assert
		assertEquals(2, game.checkWinCondition());
		
		// Reset back to normal
		System.setIn(inBackup);
	}
	
	@Test
	public void checkWinCondition_TieCase()
	{
		// Test win condition returns tie case (3)
		// 1) Player 1 select 1
		// 2) Player 2 selects 5
		// 3) Player 1 selects 7
		// 4) Player 2 selects 4
		// 5) Player 1 selects 6
		// 6) Player 2 selects 2
		// 7) Player 1 selects 8
		// 8) Player 2 selects 9
		// 9) Player 1 selects 3
		// Tie case.
		
		InputStream inBackup = System.in;
		ByteArrayInputStream in = new ByteArrayInputStream(("1" + System.lineSeparator() + "5" + System.lineSeparator() + "7"
				+ System.lineSeparator() + "4" + System.lineSeparator() + "6" + System.lineSeparator() + "2" + System.lineSeparator()
				+ "8" + System.lineSeparator() + "9" + System.lineSeparator() + "3").getBytes());
		System.setIn(in);
		
		// Setup the game
		TicTacToe game = new TicTacToe(2, System.out);
		game.getPlayerInput();
		game.getPlayerInput();
		game.getPlayerInput();
		game.getPlayerInput();
		game.getPlayerInput();
		game.getPlayerInput();
		game.getPlayerInput();
		game.getPlayerInput();
		game.getPlayerInput();

		// Assert
		assertEquals(3, game.checkWinCondition());
		
		// Reset back to normal
		System.setIn(inBackup);
	}

	@Test
	public void checkPlayerInput_InvalidInputOutOfBounds()
	{
		// Setup
		String expected = "Player 1 (X), Enter a number: \r\n" + 
				"Invalid input please enter a number between 1 to 9 and a square that is not already marked\r\n" +
				"Player 1 (X), Enter a number: \r\n";
		
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		InputStream inBackup = System.in;
		ByteArrayInputStream in = new ByteArrayInputStream(("11" + System.lineSeparator() + "5").getBytes());
		System.setIn(in);
		
		// Setup the game
		TicTacToe game = new TicTacToe(1, new PrintStream(out));
		
		game.getPlayerInput();

		// Assert
		assertEquals(expected, out.toString());
		
		// Reset back to normal
		System.setIn(inBackup);
	}
	
	@Test
	public void checkPlayerInput_InvalidInputMarked()
	{
		// Setup
		String expected = "Player 1 (X), Enter a number: \r\n" +
				"Player 2 (O), Enter a number: \r\n" +
				"Invalid input please enter a number between 1 to 9 and a square that is not already marked\r\n" +
				"Player 2 (O), Enter a number: \r\n";
		
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		InputStream inBackup = System.in;
		ByteArrayInputStream in = new ByteArrayInputStream(("1" + System.lineSeparator() + "1" + System.lineSeparator() + "5").getBytes());
		System.setIn(in);
		
		// Setup the game
		TicTacToe game = new TicTacToe(1, new PrintStream(out));
		
		game.getPlayerInput();
		game.getPlayerInput();

		// Assert
		assertEquals(expected, out.toString());
		
		// Reset back to normal
		System.setIn(inBackup);
	}
	
	@Test
	public void checkPlayerInput_ValidInput()
	{
		// Setup
		String expected = "Player 1 (X), Enter a number: \r\n" +
				"Player 2 (O), Enter a number: \r\n";
		
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		InputStream inBackup = System.in;
		ByteArrayInputStream in = new ByteArrayInputStream(("1" + System.lineSeparator() + "5").getBytes());
		System.setIn(in);
		
		// Setup the game
		TicTacToe game = new TicTacToe(1, new PrintStream(out));
		
		game.getPlayerInput();
		game.getPlayerInput();

		// Assert
		assertEquals(expected, out.toString());
		
		// Reset back to normal
		System.setIn(inBackup);
	}
}
