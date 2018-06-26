package player;

import board.BoardInterface;
import board.BoardInterface.Piece;
import uiConsole.Position;

public abstract class Player
{
	/** Player's name */
	protected String name;
	
	protected UserInterface ui;
	
	Piece type;

	public Player(String name, Piece type, UserInterface ui) { this.name=name; this.type=type; this.ui=ui;}
	
	public String getName() { return name;} //okay because String is immutable
	
	public abstract Position getNextMove(BoardInterface currentState) throws NoMoveAvailableException;

	public Piece getPieceType() { return type;}

	public UserInterface getUserInterface() { return ui;}

	public class PlayerException extends Exception
	{
		private static final long serialVersionUID = 1L;

		public PlayerException(String msg)
		{
			super(msg);
		}
	}
	

	public class NoMoveAvailableException extends PlayerException
	{
		private static final long serialVersionUID = 1L;

		public NoMoveAvailableException()
		{
			super("No move available");
		}
	}
	
}
