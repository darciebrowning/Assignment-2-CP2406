import javax.swing.*;

/**
 * Created by Darcie on 22/10/2016.
 */
public class ShowDeckCard extends JPanel {
    Icon card;
    public SuperTrumpGame game;
    JLabel container = new JLabel();
    ImageIcon cardImage;


    public ShowDeckCard(Icon card) {
        this.card = card;
       // cardImage = game.getPlayedCard().getCardImage();
        container.setIcon(cardImage);
        add(container);

    }
}
