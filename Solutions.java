import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List; //control, shift and o adds these when it's a problem
import java.util.Map;
import java.util.Set;

public class Solutions {
	HashMap<Board, List<Pair>> done; //every possible output and way to solve
	HashMap<Board, List<Pair>> todo; //initially an empty list, waiting to be filled
	public Solutions() {
		this.done= new HashMap<Board, List<Pair>>(); //create a new solution in a hashmap
		this.todo= new HashMap<Board, List<Pair>>(); //create a new solution in a hashmap
		Board b=new Board(); //an all black board
		Board w=new Board(); //an all white board
		//initializes all w squares to white, black square are already false by default
		
		for (int yCount=0; yCount < w.height; yCount++) 
		{
			for (int xCount = 0; xCount < w.width; xCount++)
			{
				w.squares[xCount][yCount] = true; //to get the current board
			}
		}
		todo.put(b, new ArrayList<Pair>()); //create a new array list with the black squares
		todo.put(w, new ArrayList<Pair>()); //create a new array list with the white squares
		
		while(!todo.isEmpty()) { //stick the initial state into the array1
			//If there are items left to process
		System.out.println("Done: " + done.size());   //for debugging, to see what was in my Done array
		System.out.println("Todo: " + todo.size());   //for debugging, to see what was in my Todo array
			Set<Map.Entry<Board,List<Pair>>>currentTodos=new HashSet<Map.Entry<Board,List<Pair>>>(todo.entrySet()); //creates new hash set containing all the entries
			Iterator<Map.Entry<Board,List<Pair>>> it=currentTodos.iterator(); //iterates through the entries
			while (it.hasNext()) {
				Map.Entry<Board,List<Pair>>current=it.next(); //the actual iterator
				
				if(done.containsKey(current.getKey())) {
					//Solution has been added that would solve the puzzle
					//Which set of moves is the longest?
					
					//Have we found a solution for this key before? If yes, what is the list of moves?
					//returns list of pair, old moves
					List<Pair> oldMoves = done.get(current.getKey());
					List<Pair> newMoves = current.getValue();
					//returns list of moves to get back to a solved state
					if (newMoves.size() < oldMoves.size()) {
						//if the new list of moves is better, replace the old ones
						
						done.put(current.getKey(), current.getValue());
						//replace the newMoves with the oldMoves in done
					}
					
				}
				else {
					done.put(current.getKey(), current.getValue()); //if done doesn't have it, add it
					for (int yCount=0; yCount < w.height; yCount++) 
					{
						for (int xCount = 0; xCount < w.width; xCount++)
						{
							Board newB=current.getKey().flip(xCount, yCount); //adds map to done list
							Pair move=new Pair(xCount,yCount); //runs through every possible move on current board and records those moves
							List<Pair>newMoves=new ArrayList<Pair>(current.getValue()); //new move is an array list containing all the old moves.
							newMoves.add(move);  //add move to new array from above
							if (!todo.containsKey(newB)||todo.get(newB).size()>newMoves.size()) {
								todo.put(newB,newMoves);
							}
							
							//todo.put(newB, newMoves); //dump moves to todo list, the board and list of moves now can be looked at
						}
					}
				
				}
				//131 thousand solvable states versus 2^25 possible states
				 // .3% chance of randomly generating a solvable grid
				todo.remove(current.getKey()); //removes the information from todo to done, otherwise it runs out of memory
			}
		}
			}
	
	public List<Pair> moves(Board b){ //returns the list of moves that have been done
		return done.get(b);
	}

}
