import javax.swing.*;

/**
 * Created by Darcie on 19/10/2016.
 */
public class DeckPlayCard extends JPanel {
    Card card;
    JLabel playedCardContainer = new JLabel();
    ImageIcon cardImage;

    public DeckPlayCard(Card card){
        this.card = card;
        cardImage = card.getCardImage();
        playedCardContainer.setIcon(cardImage);
        add(playedCardContainer);
    }
}
