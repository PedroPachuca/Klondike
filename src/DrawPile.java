import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DrawPile extends Deck{

	public DrawPile(String[] suits, int[] values) {
		super(suits, values);
	}
	public void clicked(MouseEvent click, LayPile lpile) {
		if(click.getX() >= this.getx() && click.getX() <= this.getx() + this.getCardWidth()) {
			if(click.getY() >= this.gety() && click.getY() <= this.gety() + this.getCardHeight()) {
				deal1Card(lpile);
			}
		}
	}
	private void deal1Card(LayPile lpile) {
		if(this.dealInd > 0){	
				System.out.println(this.dealInd);
				lpile.addCard(this.deal());
		}
		else {
			if(dealInd == 0) {
				this.cards.clear();
				this.dealInd--;
			}
			else{
			super.deckEmpty(lpile, this);
			}
		}
	
	}
	
}
