import javax.swing.*;

/**
 * Created by Darcie on 17/10/2016.
 */
public class ShowCard extends JPanel {
    Card card;
    JLabel container = new JLabel();
    ImageIcon cardImage;

    public ShowCard(Card card){
        this.card = card;
        cardImage = card.getCardImage();
        container.setIcon(cardImage);
        add(container);


    }

}
