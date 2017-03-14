import java.awt.event.MouseEvent;
import java.util.List;

public class PlayingPile extends Deck{
	public PlayingPile(String[] suits, int[] values) {
		super(suits, values);
	}

	public void clickedAt(MouseEvent click, boolean first, List<Card> movingCards) {
		if(first) {
			firstClick(click, movingCards);
			first = false;
		}
		else {
			secondClick(click, movingCards);
			first = true;
		}
	
			
		
	}

	private void secondClick(MouseEvent click, List<Card> movingCards) {
		System.out.println(movingCards);
		Card checkingCard = movingCards.get(0);
		if(movePossible(checkingCard)) {
			for(Card add: movingCards) {
				cards.add(add);
			}
		}
		else {
			for(Card card: movingCards) {
				card.setSelected(false);
			}
			movingCards.clear();
			
		}
		
	}

	private void firstClick(MouseEvent click, List<Card> movingCards) {
		for(Card x: this.cards) {
			if(x.getShouldBeDrawn())
			if(click.getX() >=  x.getx() && click.getX() <= x.getx() + this.getCardWidth()) {
				if(click.getY() >= x.gety() && click.getY() <=  x.gety() + this.getCardHeight()){
					System.out.println("once");
					for(int i = this.cards.size() - 1; i >=  this.cards.indexOf(x); i--){
						movingCards.add(this.cards.get(i));
					}
					for(Card card: movingCards) {
						card.setSelected(true);
					}
					System.out.println(movingCards);
				}
				}
		}
		
	}

	private boolean movePossible(Card movedCard) {
		Card compareCard = this.cards.get(this.cards.size()-1);
		System.out.println("Comparing: " + compareCard + " to " + movedCard);
		
		boolean differentColor = movedCard.isRed() != compareCard.isRed();
		boolean rankUp = compareCard.pointValue()-1 == movedCard.pointValue();
		System.out.println(differentColor + "and" + rankUp);
		
		return differentColor && rankUp;
		}
		
	

}
