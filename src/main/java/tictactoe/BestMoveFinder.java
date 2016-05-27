package tictactoe;

import java.util.List;
import java.util.Queue;
import java.util.Random;

public class BestMoveFinder {

	private static final Random RAND = new Random();
	private Queue<Board> childBoards;

	/**
	 * @param board
	 * @return the best move for the active player as a number between 0 and 8
	 */
	public int getBestMove(Board parent) {
		Board.Piece activePlayer = parent.getActivePlayer();
		List<Integer> moves = parent.getMoves();
		for (int move : moves) {
			Board child = new Board(parent);

			child.set(move, activePlayer);
			if (child.getWinner() == activePlayer) {
				return move;
			}

			//child.setActivePlayer(activePlayer.otherPlayer());
			//childBoards.add(child);
		}
	//	return getBestMove(childBoards.poll());
		 return moves.get(RAND.nextInt(moves.size()));
	}

	/**
	 * 
	 * @param board
	 * @return 0-8
	 */
	private int computerMove(Board board, Board.Piece activePlayer, int move) {
		board.set(move, activePlayer);
		if (board.getWinner() == Board.Piece.X) {
			return Integer.MAX_VALUE;
		} else if (board.getWinner() == Board.Piece.O) {
			return Integer.MIN_VALUE;
		}

		return 0;
	}

	public static void main(String[] args) {

	}
}
