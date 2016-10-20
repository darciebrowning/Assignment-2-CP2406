import javax.swing.*;
import java.io.IOException;

/**
 * Created by Darcie on 19/10/2016.
 */
public class BotCard extends JPanel {
    Card card;
    JLabel container = new JLabel();
    ImageIcon cardImage;

    public BotCard(Card card) throws IOException {
        this.card = card;
        cardImage = card.getCardBack();
        container.setIcon(cardImage);
        add(container);


    }

}