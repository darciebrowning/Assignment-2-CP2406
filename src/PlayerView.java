import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * Created by Darcie on 17/10/2016.
 */
public class PlayerView extends JPanel {
    Player player;

    public PlayerView(Player humanPlayer) {
        this.player = humanPlayer;

        addAllCards();
    }

    private void addAllCards() {
        ArrayList<Card> hand = player.getPlayerHand();

        for (int i = 0; i < hand.size(); i++){
            Card card = hand.get(i);
            ShowCard cardView = new ShowCard(card);
            cardView.setBackground(new Color(52, 86, 55));
            add(cardView);

        }
    }

}
