import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Darcie on 3/09/2016.
 */
public class SuperTrumpGame {
    private static final int NUMBER_CARDS_DEAL = 8;
    private  int numberPlayers;
    public int dealerNumber;
    private Player[] players;
    public STDeck cards;
    public STDeck deck;

    public SuperTrumpGame(int numberPlayers){
        this.numberPlayers = numberPlayers;
        this.deck = new STDeck();

    }

    public int selectDealer(int NumberOfPlayers){
        int min = 1;
        Random generator = new Random();
        dealerNumber = generator.nextInt(NumberOfPlayers - min + 1) + min;

        return dealerNumber;
    }

    public void dealCards(){

        players = new Player[numberPlayers];

        for (Player player : players){
            ArrayList<STCard> card = cards.dealCards(NUMBER_CARDS_DEAL);


        }
    }
}
