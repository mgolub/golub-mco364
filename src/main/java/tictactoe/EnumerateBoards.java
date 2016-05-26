package tictactoe;

import java.util.List;
import java.util.Stack;

public class EnumerateBoards {

	/**
	 * Determine number of possible boards and which piece wins more often, X,
	 * O. Find how many times each piece wins.
	 */

//	private int numBoards = 0;
//	private int Xwins = 0;
//	private int Owins = 0;
//
//	public EnumerateBoards() {
//		Board empty = new Board();
//		enumerate(empty);
//	}
//
//	public void enumerate(Board b) {
//		// need to know current player, number of empty spaces...
//		Piece active = b.getActivePlayer();
//		int emptySpaces = b.getMoves().size();
//		for (int i = 0; i < emptySpaces; i++) {
//			if (b.getWinner() != null) {
//				increaseWins(b.getWinner());
//				return;
//			} else {
//
//			}
//
//			enumerate(new Board(b));
//			numBoards++;
//		}
//	}
//
//	private void increaseWins(Piece p) {
//		if (p == Piece.X)
//			Xwins++;
//		else
//			Owins++;
//	}

	public static void main(String[] args){
		Stack<Board> stack = new Stack<Board>();
		int numBoards = 0;
		int ties = 0;
		int Xwins = 0;
		int Owins = 0;
		
		Board empty = new Board();
		stack.push(empty);
		while(!stack.isEmpty()){
			Board b = stack.pop();
			numBoards++;
			
			Board.Piece winner = b.getWinner();
			if(winner == Board.Piece.X){
				Xwins++;
				continue;
			}
			else if(winner == Board.Piece.O){
				Owins++;
				continue;
			}
			
			Board.Piece active = b.getActivePlayer();
			List<Integer> moves = b.getMoves();
			
			if(moves.size() == 0){
				ties++;
			}
			
			for(int move: moves){
				Board child = new Board(b);
				child.set(move, active);
				stack.push(child);
			}
		}
		
		System.out.println(String.format("numBoards=%d ties=%d xWins=%d oWins=%d leaves=%d", 
				numBoards, ties, Xwins, Owins, ties+Xwins+Owins));
	}
}
