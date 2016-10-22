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
    public GUI gui;
    JPanel deckPanel = new JPanel();
    JLabel deckLabel = new JLabel();
    public ShowDeckCard showDeck;
    public DeckView deckView;


    public ShowCard(final Card card){
        this.card = card;
        cardImage = card.getCardImage();
        container.setIcon(cardImage);
        container.setBackground(new Color(52, 86, 55));
        add(container);

        addMouseListener(new MouseInputAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                SuperTrumpGame.currentGame.firstPlayerTurn(card);
                ShowCard.this.removeAll();
                repaint();
                revalidate();
            }
        });


    }

}
