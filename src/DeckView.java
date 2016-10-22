import javax.swing.*;
import java.awt.*;

/**
 * Created by Darcie on 22/10/2016.
 */
public class DeckView extends JPanel {
    Icon cardimage;

    public DeckView(ImageIcon cardimage) {
        this.cardimage = cardimage;

        addAllCards();
    }

    public void addAllCards() {

            ShowDeckCard deckView = new ShowDeckCard(cardimage);
            add(deckView, BorderLayout.CENTER);

        }
    }


