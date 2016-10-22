import javax.swing.*;
import javax.swing.event.MouseInputAdapter;
import java.awt.event.MouseEvent;

/**
 * Created by Darcie on 17/10/2016.
 */
public class ShowCard extends JPanel {
    Card card;
    public SuperTrumpGame game;
    JLabel container = new JLabel();
    ImageIcon cardImage;

    public ShowCard(final Card card){
        this.card = card;
        cardImage = card.getCardImage();
        container.setIcon(cardImage);
        add(container);

        addMouseListener(new MouseInputAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                SuperTrumpGame.currentGame.firstPlayerTurn(card);
            }
        });


    }

}
