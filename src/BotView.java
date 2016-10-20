import javax.swing.*;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Darcie on 17/10/2016.
 */
public class BotView extends JPanel {
    Player player;

    public BotView(Player botPlayer) throws IOException {
        this.player = botPlayer;

        addAllCards();
    }

    private void addAllCards() throws IOException {
        ArrayList<Card> hand = player.getPlayerHand();

        for (int i = 0; i < hand.size(); i++){
            Card card = hand.get(i);
            BotCard BotView = new BotCard(card);
            add(BotView);

        }
    }
}