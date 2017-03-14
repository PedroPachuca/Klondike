import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class KlondikeBoard {
	DrawPile fullDeck;
	PlayingPile firstPile;
	PlayingPile secondPile;
	PlayingPile thirdPile;
	PlayingPile fourthPile;
	PlayingPile fifthPile;
	PlayingPile sixthPile;
	PlayingPile seventhPile;
	LayPile lpile;
	FinalPile firstFinalPile;
	FinalPile secondFinalPile;
	FinalPile thirdFinalPile;
	FinalPile fourthFinalPile;
	ArrayList<PlayingPile> playingPiles = new ArrayList<PlayingPile>();
	ArrayList<FinalPile> finalPiles = new ArrayList<FinalPile>();
	public boolean first = true;
	public static List<Card> movingCards = new ArrayList();

	public void setUpBoard() {
		String[] emptysuit = new String[0];
		int[] emptyvalues = new int[0];

		// Initialize Piles
		fullDeck = new DrawPile(emptysuit, emptyvalues);
		lpile = new LayPile(emptysuit, emptyvalues);
		firstPile = new PlayingPile(emptysuit, emptyvalues);
		secondPile = new PlayingPile(emptysuit, emptyvalues);
		thirdPile = new PlayingPile(emptysuit, emptyvalues);
		fourthPile = new PlayingPile(emptysuit, emptyvalues);
		fifthPile = new PlayingPile(emptysuit, emptyvalues);
		sixthPile = new PlayingPile(emptysuit, emptyvalues);
		seventhPile = new PlayingPile(emptysuit, emptyvalues);
		playingPiles.addAll(Arrays.asList(firstPile, secondPile, thirdPile, fourthPile, fifthPile, sixthPile, seventhPile));
		firstFinalPile = new FinalPile(emptysuit, emptyvalues);
		secondFinalPile = new FinalPile(emptysuit, emptyvalues);
		thirdFinalPile = new FinalPile(emptysuit, emptyvalues);
		fourthFinalPile = new FinalPile(emptysuit, emptyvalues);
		finalPiles.addAll(Arrays.asList(firstFinalPile, secondFinalPile, thirdFinalPile, fourthFinalPile));
		
		fullDeck.setCoord((int)(fullDeck.getCardHeight()),(int)(fullDeck.getCardWidth()));
		for (int i = 0; i < playingPiles.size(); i++) {
			playingPiles.get(i).setCoord((int)(fullDeck.getCardHeight() + i * 1.25 * fullDeck.getCardWidth()),(int)(fullDeck.getCardWidth() + fullDeck.getCardHeight() + (0.7 * fullDeck.getCardHeight())));
		}
		for (int i = 0; i < finalPiles.size(); i++){
			finalPiles.get(i).setCoord((int)(fullDeck.getCardHeight() + (i+3) * 1.25 * fullDeck.getCardWidth()),(int)(fullDeck.getCardWidth()));
		}
		lpile.setCoord((int)(lpile.getCardHeight() + 1.25 * lpile.getCardWidth()),(int)(lpile.getCardWidth()));
		dealCards();
	}

	private void dealCards(){ 
		fullDeck.createDeck();
		for(int a = 0; a < playingPiles.size(); a++){
			for(int b = -1; b < a; b++){
				Card add = fullDeck.deal();
				add.setShouldBeDrawn(false);
				
				playingPiles.get(a).addCard(add);
			}
		}
	}
	
	public void draw(Graphics g) {
		lpile.fixLay();
		lpile.fixDraw();
		
		fullDeck.draw(g);
		lpile.draw(g);
		for (PlayingPile ppile : playingPiles) {
			ppile.fixDraw();
			ppile.fixPos();
			ppile.draw(g);
		}
		for (FinalPile fpile : finalPiles)	{
			fpile.fixDraw();
			fpile.fixPos();
			fpile.draw(g);
		}
	}

	public void clickedAt(MouseEvent arg0) {
		fullDeck.clicked(arg0, lpile);
		boolean hit = false;
		for(PlayingPile ppile: playingPiles) {
			if(!hit) {
			ppile.clickedAt(arg0, first, movingCards);
			}
			
		}
		if(!hit) {
			for(FinalPile fpile: finalPiles) {
				if(!hit) {
					//fpile.clickedAt(arg0);
				}
			}
		}
		

	}

}