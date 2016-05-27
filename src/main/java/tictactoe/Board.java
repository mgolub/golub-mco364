package tictactoe;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Board {

	public enum Piece {
		X, O, EMPTY;
		public Piece otherPlayer(){
		return this == Piece.X ? Piece.O : Piece.X;
		}
	}

	private int counter = 0;
	private Piece array[] = new Piece[9];
	private Piece activePlayer = Piece.X;

	public Board() {
		Arrays.fill(array, Piece.EMPTY);
	}

	public Board(Board other){
		array = other.array.clone();
		activePlayer = other.activePlayer.otherPlayer();
	}
	
	public Piece getActivePlayer(){
		return activePlayer;
	}

	public Piece getWinner() {
		if (isWinner(0,1,2))
			return array[0];
		if (isWinner(3,4,5))
			return array[3];
		if (isWinner(6,7,8))
			return array[6];
		if (isWinner(0,3,6))
			return array[6];
		if (isWinner(1,4,7))
			return array[1];
		if (isWinner(2,5,8))
			return array[2];
		if (isWinner(0,4,8))
			return array[0];
		if (isWinner(2,4,6))
			return array[2];
		return null;
	}
	
	public boolean isWinner(int a, int b, int c){
		return (array[a] != Piece.EMPTY && array[a] == array[b] && array[b] == array[c]);
	}

	public Piece get(int index) {
		return array[index];
	}

	/**
	 * @return a list of all possible moves
	 */
	public List<Integer> getMoves() {
		List<Integer> available = new ArrayList<Integer>();
		for (int i = 0; i < array.length; i++){
			if (array[i] == Piece.EMPTY){
				available.add(i);
			}
		}
		return available;
	}

	public void setActivePlayer(Piece activePlayer) {
		this.activePlayer = activePlayer;
	}

	public void set(int index, Piece piece) {
		array[index] = piece;
		counter++;
	}
	
	public boolean isFull(){
		return counter == 9;
	}

	public String toString() {
		return array[0].name() + array[1].name() + array[2].name() + "\n"
				+ array[3].name() + array[4].name() + array[5].name() + "\n"
				+ array[6].name() + array[7].name() + array[8].name();

	}
}