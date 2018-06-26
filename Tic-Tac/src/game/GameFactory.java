package game;

import java.util.Random;

import board.SimpleBoard;
import board.BoardInterface.Piece;
import player.LocalPlayer;
import player.Player;
import player.RandomLocalPlayer;
import player.UserInterface;






/**
 * Factory for building various types of Noughts and crosses games.
 *
 */
public class GameFactory 
{	
	public static Game makeSimpleGame(boolean useRandomCrossPlayer,boolean useRandomNoughtPlayer, UserInterface ui)
	{
		Player crossPlayer, noughtPlayer;
		if(useRandomCrossPlayer)
			crossPlayer= new RandomLocalPlayer("CrossRandom",Piece.X,ui);
		else	
			crossPlayer= new LocalPlayer("CrossInteractive",Piece.X,ui);
		
		if(useRandomNoughtPlayer)
			noughtPlayer= new RandomLocalPlayer("NoughtRandom",Piece.O,ui);
		else
			noughtPlayer= new LocalPlayer("NoughtInteractive",Piece.O,ui);
		
		//Randomly pick who starts
		Random rnd = new Random(System.nanoTime());
		
		Player first=null;
		if(rnd.nextInt() % 2 !=0 )
			first=crossPlayer;
		else
			first=noughtPlayer;
		
		return new Game(crossPlayer,noughtPlayer, new SimpleBoard(),first);

	}

}
