import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Scanner;

public class App { //essentially my main function

	public static void main(String[] args) throws FileNotFoundException { //necessary for the output file
		
		Scanner scanner = new Scanner(System.in); //takes user input
		System.out.println("Do you want a (1) solved board or a (2) unsolved board?");
		int command = scanner.nextInt();
		
		if (command != 1 && command != 2) { //end the game if someone enters a number other than 1 or 2
			return;
		}
		
		Board board = new Board(); //create new board
		
		if (command == 1 ) {
			board = new Board(false); //output a solved board
			
		}
		
		if (command == 2) {
			board = new Board(true); //output an unsolved board
		}
		
		
		System.out.println(board); //display new board
		Solutions s=new Solutions(); //the solutions to the board
		Board board2 = new Board(); //the new board being generated
		System.out.println(s.moves(board2)); //output the new board
		while(true) {
			System.out.println("Enter X: "); //enter the x coordinate per user input
			int x = scanner.nextInt();  //store input as x
			System.out.println("Enter Y: "); //enter the y coordinate
			int y = scanner.nextInt(); //store input as y
			board = board.flip(x,y); //flip that row and column
			System.out.println(board); //display cloned board with flips
			List<Pair> moves=s.moves(board); //the list of moves to be done on the board to solve it
			
			
			if (moves == null) {
				System.out.println("No solution available. "); //moves returns null if there are no possible solutions for the board
				try (PrintWriter out = new PrintWriter("solutions.txt")) { //output the "solutions" to text file
				    out.println("No solutions available.");
				    out.close();
				}
			}
			
			else {
			System.out.println(moves); //moves returns the moves it takes to solve the puzzle
			try (PrintWriter out = new PrintWriter("solutions.txt")) { //outputs the solutions to text file
			    out.println(moves);
			    out.close();
			}
			}
			
		}
		
	}

}
