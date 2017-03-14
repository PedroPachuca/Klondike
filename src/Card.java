import java.awt.Point;

import javax.swing.JLabel;

public class Card {

	private String suit;

	private int pointValue;

	private boolean red;

	private boolean faceUp;

	private boolean selected;

	private int cardCoordX;

	private int cardCoordY;

	private boolean shouldBeDrawn;

	public Card(String cardSuit, int cardPointValue) {
		// initializes a new Card with the given rank, suit, and point value
		suit = cardSuit;
		pointValue = cardPointValue;
		this.faceUp = false;
		if (suit == "h" || suit == "d") {
			red = true;
		} else {
			red = false;
		}
		shouldBeDrawn = false;
		selected = false;
	}

	public void setShouldBeDrawn(boolean yes) {
		shouldBeDrawn = yes;
	}

	public boolean getShouldBeDrawn() {
		return shouldBeDrawn;
	}

	public void setSelected(boolean input) {
		selected = input;
	}

	public boolean faceUp() {
	return faceUp;
	}

	public boolean getSelected() {
		return selected;
	}

	public String suit() {
		return suit;
	}

	public void setCoord(int x, int y) {
		cardCoordX = x;
		cardCoordY = y;
	}
	public void setFace(boolean b) {
			faceUp = b;
	}

	public int getx() {
		return cardCoordX;
	}

	public int gety() {
		return cardCoordY;
	}

	public int pointValue() {
		return pointValue;
	}

	/**
	 * Compare this card with the argument.
	 * 
	 * @param otherCard
	 *            the other card to compare to this
	 * @return true if the rank, suit, and point value of this card are equal to
	 *         those of the argument; false otherwise.
	 */
	public boolean matches(Card otherCard) {
		return otherCard.suit().equals(this.suit()) && otherCard.pointValue() == this.pointValue();
	}

	/**
	 * Converts the rank, suit, and point value into a string in the format
	 * "[Rank] of [Suit] (point value = [PointValue])". This provides a useful
	 * way of printing the contents of a <code>Deck</code> in an easily readable
	 * format or performing other similar functions.
	 *
	 * @return a <code>String</code> containing the rank, suit, and point value
	 *         of the card.
	 */
	@Override
	public String toString() {
		return pointValue + " of " + suit;
	}

	public boolean isRed() {
		return red;
	}
}
