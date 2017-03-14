import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class KlondikePanel extends JPanel {

	// /** The card displays. */
	// private JLabel[] displayCards;
	// /** The coordinates of the card displays. */
	// private Point[] cardCoords;

	Dimension dim = new Dimension(1000, 700);
	Color backGround = new Color(20, 150, 110);
	KlondikeBoard board = new KlondikeBoard();

	public KlondikePanel() {
		this.setPreferredSize(dim);
		this.setBackground(backGround);
		setUpMouseListeners();
		board.setUpBoard();
	}

	/**
	 * Draw the display (cards and messages).
	 */
	// public void repaint() {
	// for (int k = 0; k < board.size(); k++) {
	// String cardImageFileName =
	// imageFileName(board.cardAt(k), selections[k]);
	// URL imageURL = getClass().getResource(cardImageFileName);
	// if (imageURL != null) {
	// ImageIcon icon = new ImageIcon(imageURL);
	// displayCards[k].setIcon(icon);
	// displayCards[k].setVisible(true);
	// } else {
	// throw new RuntimeException(
	// "Card image not found: \"" + cardImageFileName + "\"");
	// }
	//
	// displayCards = new JLabel[board.size()];
	// for (int k = 0; k < board.size(); k++) {
	// displayCards[k] = new JLabel();
	// panel.add(displayCards[k]);
	// displayCards[k].setBounds(cardCoords[k].x, cardCoords[k].y,
	// CARD_WIDTH, CARD_HEIGHT);
	// displayCards[k].addMouseListener(new MyMouseListener());
	// selections[k] = false;

	private void setUpMouseListeners() {
		setUpML();
		setUPMML();
	}

	private void setUPMML() {
		// MouseMotionListener allows for listening for dragging

	}

	private void setUpML() {
		// MouseListeners allow for listening for clicks
		this.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mousePressed(MouseEvent arg0) {
				System.out.println("you just clicked " + arg0);

			
				board.clickedAt(arg0);
				repaint();
			}

			@Override
			public void mouseReleased(MouseEvent arg0) {
				// TODO Auto-generated method stub

			}

		});
		this.requestFocusInWindow();
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		board.draw(g);
	}

}
