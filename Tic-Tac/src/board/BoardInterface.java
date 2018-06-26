package board;

import uiConsole.Position;

/**
 * Public Interface for game boards. The convention taken is that (0,0) refers
 * to the bottom left corner of the board.
 *
 */
public interface BoardInterface {
	// The length of one side of the board.
	static final int LENGTH = 3;

	void reset();

	void add(Piece type, Position p)
			throws InvalidBoardPositionException, BoardPositionOccupiedException, InvalidPieceException;

	Piece get(Position p) throws InvalidBoardPositionException;

	BoardInterface clone();

	public enum Piece {

		X, O, BLANK
	}

	public class BoardException extends Exception {

		private static final long serialVersionUID = 1L;

		public BoardException(String s) {
			super(s);
		}
	}

	public class InvalidBoardPositionException extends BoardException {
		private static final long serialVersionUID = 1L;

		public InvalidBoardPositionException(Position p) {
			super("Invalid Board Position " + p + ".");
		}
	}

	public class BoardPositionOccupiedException extends BoardException {
		private static final long serialVersionUID = 1L;

		public BoardPositionOccupiedException(Position p) {
			super("Board Position " + p + " is already occupied.");
		}
	}

	public class InvalidPieceException extends BoardException {
		private static final long serialVersionUID = 1L;

		public InvalidPieceException(Piece p) {
			super("Piece " + p.toString() + " is not allowed to be added to this board.");
		}
	}

}
