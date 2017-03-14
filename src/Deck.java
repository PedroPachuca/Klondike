import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

import java.util.ArrayList;
import java.util.Collections;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

/**
 * The Deck class represents a shuffled deck of cards. It provides several
 * operations including initialize, shuffle, deal, and check if empty.
 */
public class Deck {

	/**
	 * cards contains all the cards in the deck.
	 */
	protected List<Card> cards;

	/**
	 * size is the number of not-yet-dealt cards. Cards are dealt from the top
	 * (highest index) down. The next card to be dealt is at size - 1.
	 */
	protected int size;

	private int deckCoordX;
	private int amtReset;

	private int deckCoordY;
	protected int dealInd = 51;

	/** Width of a card. */
	private static final int CARD_WIDTH = 73;
	/** Height of a card. */
	private static final int CARD_HEIGHT = 97;
	private static final int CARD_OFFSET = 20;

	/**
	 * Creates a new <code>Deck</code> instance.<BR>
	 * It pairs each element of ranks with each element of suits, and produces
	 * one of the corresponding card.
	 * 
	 * @param ranks
	 *            is an array containing all of the card ranks.
	 * @param suits
	 *            is an array containing all of the card suits.
	 * @param values
	 *            is an array containing all of the card point values.
	 */
	public Deck(String[] suits, int[] values) {
		cards = new ArrayList<Card>();
		for (int j = 0; j < suits.length; j++) {
			for (String suitString : suits) {
				cards.add(new Card(suitString, values[j]));
			}
		}

		size = cards.size();
	}

	public void createDeck() {
		String[] suit = new String[] { "h", "c", "s", "d" };
		int[] values = new int[] { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13 };
		cards = new ArrayList<Card>();
		for (int i = 0; i < suit.length; i++) {
			for (int j = 0; j < values.length; j++) {
				Card add = new Card(suit[i], values[j]);
				add.setCoord(getx(), gety());
				add.setShouldBeDrawn(false);
				cards.add(add);
				// System.out.println(add);
			}
		}
		Collections.shuffle(cards);
	}

	/**
	 * Determines if this deck is empty (no undealt cards).
	 * 
	 * @return true if this deck is empty, false otherwise.
	 */

	public void draw(Graphics g) {
		// System.out.println(cards);
		if (isEmpty()) {
			g.setColor(new Color(51, 102, 0));
			g.clearRect(deckCoordX, deckCoordY, CARD_WIDTH, CARD_HEIGHT);
			g.fillRect(deckCoordX, deckCoordY, CARD_WIDTH, CARD_HEIGHT);
		} else {
			for (Card drawCard : cards) {
				if (drawCard.getShouldBeDrawn()) {
					try {
						BufferedImage img = ImageIO.read(getClass()
								.getResourceAsStream(
										imageFileName(drawCard,
												drawCard.getSelected())));
						g.drawImage(img, drawCard.getx(), drawCard.gety(), null);
						// System.out.println(drawCard);
					} catch (IOException e) {
						e.printStackTrace();
					}
				} else {
					try {
						BufferedImage img = ImageIO.read(getClass()
								.getResourceAsStream(
										imageFileName(null,
												drawCard.getSelected())));
						g.drawImage(img, drawCard.getx(), drawCard.gety(), null);
						// System.out.println(drawCard);
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		}

	}

	private String imageFileName(Card c, boolean isSelected) {
		String str = "cards/";
		if (c == null) {
			return "cards/back.jpg";
		}

		// SettingRankName
		if (c.pointValue() < 11 && c.pointValue() != 1) {
			str += c.pointValue();
		} else {
			if (c.pointValue() == 1) {
				str += "ace";
			} else if (c.pointValue() == 11) {
				str += "jack";
			} else if (c.pointValue() == 12) {
				str += "queen";
			} else if (c.pointValue() == 13) {
				str += "king";
			}
		}

		// Suit
		if (c.suit().equals("h")) {
			str += "hearts";
		} else if (c.suit().equals("d")) {
			str += "diamonds";
		} else if (c.suit().equals("s")) {
			str += "spades";
		} else if (c.suit().equals("c")) {
			str += "clubs";
		}

		if (isSelected) {
			str += "S";
		}
		str += ".GIF";
		return str;
	}

	public void addCard(Card input) {
		cards.add(input);
		size = cards.size();
	}

	public void setAllVisible() {
		for (Card card : cards) {
			card.setShouldBeDrawn(true);
		}
	}

	public void removeCard(Card rem) {
		cards.remove(rem);
	}

	public Card getCard(int x) {
		Card Answer;
		Answer = cards.get(x);
		return Answer;
	}

	public boolean isEmpty() {
		size = cards.size();
		return size == 0;
	}

	public void setCoord(int x, int y) {
		deckCoordX = x;
		deckCoordY = y;
	}

	public int getx() {
		return deckCoordX;
	}

	public int gety() {
		return deckCoordY;
	}

	public int getCardWidth() {
		return CARD_WIDTH;
	}

	public int getCardHeight() {
		return CARD_HEIGHT;
	}

	/**
	 * Accesses the number of undealt cards in this deck.
	 * 
	 * @return the number of undealt cards in this deck.
	 */
	public int size() {
		size = cards.size();
		return size;
	}

	/**
	 * Deals a card from this deck.
	 * 
	 * @return the card just dealt, or null if all the cards have been
	 *         previously dealt.
	 */
	public Card deal() {
		if (isEmpty()) {
			return null;
		}

		Card c = cards.get(dealInd);
		dealInd--;
		return c;
	}

	public void fixDraw() {
		if (cards.size() > 0)
			cards.get(cards.size() - 1).setShouldBeDrawn(true);
	}

	public void fixPos() {
		for (Card card : cards) {
			if (!card.getShouldBeDrawn()) {
				card.setCoord(getx(), gety()
						+ (cards.indexOf(card) * CARD_OFFSET));
			} else {
				if (cards.size() > 1 && cards.size() < 8) {
					int modY = cards.get(cards.size() - 2).gety() + CARD_OFFSET;
					card.setCoord(getx(), modY);
				} else {
					if (cards.size() < 8) {
						card.setCoord(getx(), gety());
					}
				}
			}
		}
	}

	public void fixLay() {
		for (Card card : cards) {
			if (!card.getShouldBeDrawn()) {
				card.setCoord(getx(), gety());
			}
		}
	}

	/**
	 * Generates and returns a string representation of this deck.
	 * 
	 * @return a string representation of this deck.
	 */
	@Override
	public String toString() {
		String rtn = "size = " + size + "\nUndealt cards: \n";

		for (int k = size - 1; k >= 0; k--) {
			rtn = rtn + cards.get(k);
			if (k != 0) {
				rtn = rtn + ", ";
			}
			if ((size - k) % 2 == 0) {
				// Insert carriage returns so entire deck is visible on console.
				rtn = rtn + "\n";
			}
		}

		rtn = rtn + "\nDealt cards: \n";
		for (int k = cards.size() - 1; k >= size; k--) {
			rtn = rtn + cards.get(k);
			if (k != size) {
				rtn = rtn + ", ";
			}
			if ((k - cards.size()) % 2 == 0) {
				// Insert carriage returns so entire deck is visible on console.
				rtn = rtn + "\n";
			}
		}

		rtn = rtn + "\n";
		return rtn;
	}

	public void deckEmpty(LayPile full, DrawPile drawPile) {
		if (amtReset < 2) {
			int newInd = full.cards.size() - 1;
			for (Card card : full.cards) {
				drawPile.addCard(card);
			}
			full.cards.clear();
			drawPile.dealInd = newInd;
			drawPile.addCard(drawPile.deal());
			full.addCard(drawPile.deal());
			amtReset++;
		} else {
			drawPile.cards.clear();
		}

	}
}
