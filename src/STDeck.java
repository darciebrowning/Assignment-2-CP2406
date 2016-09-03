import java.util.ArrayList;

/**
 * Created by Darcie on 3/09/2016.
 */
public class STDeck {
    private static final int NUMBER_OF_CARDS = 60;
    public ArrayList<STCard> cards;





    //// TODO: 3/09/2016 list of all cards

    public STDeck(){
        cards = new ArrayList<STCard>(NUMBER_OF_CARDS);


        for (int i = 0; i < NUMBER_OF_CARDS; i++) {
                 cards.add(new STCard());
            // google how to create random array of integers
            }
    }



    public ArrayList<STCard> dealCards(int i){

        return null;
    }
}
