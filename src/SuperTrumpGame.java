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
    private STDeck deck;

    public SuperTrumpGame(int numberPlayers){
        this.numberPlayers = numberPlayers;
    }

    public int selectDealer(int NumberOfPlayers){
        int min = 1;
        Random generator = new Random();
        dealerNumber = generator.nextInt(NumberOfPlayers - min + 1) + min;
        //// TODO: 3/09/2016  get random int value in java
        return dealerNumber;
    }

    public void dealCards(){

        players = new Player[numberPlayers];

        for (Player player : players){
            ArrayList<STCard> card = deck.dealCards(NUMBER_CARDS_DEAL);


        }
    }
}
