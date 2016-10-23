import javax.swing.*;
import javax.swing.event.MouseInputAdapter;
import java.awt.*;
import java.awt.event.MouseEvent;

/**
 * Created by Darcie on 17/10/2016.
 */
public class ShowCard extends JPanel {
    Card card;
    public SuperTrumpGame game;

    JLabel container = new JLabel();
    JLabel deck = new JLabel();
    ImageIcon cardImage;
    public GUI gui = new GUI();
    JPanel deckPanel = new JPanel();
    JLabel deckLabel = new JLabel();
    public ShowDeckCard showDeck;
    public DeckView deckView;
    public static JLabel deckCard;


    public ShowCard(final Card card){
        this.card = card;
        cardImage = card.getCardImage();
        container.setIcon(cardImage);
        container.setBackground(new Color(52, 86, 55));
        add(container);
        final int turnCounter = 1;

        addMouseListener(new MouseInputAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);

                SuperTrumpGame.currentGame.firstPlayerTurn(card);

                ShowCard.this.removeAll();

                gui.addCard(card);
                repaint();
                revalidate();

                try {
                    gui.rungame(1);
                } catch (InterruptedException e1) {
                    e1.printStackTrace();
                }

            }
        });


    }

}
