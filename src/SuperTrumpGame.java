import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

/**
 * Created by Darcie on 3/09/2016.
 */

public class SuperTrumpGame {
    private  int numberPlayers;
    public int dealerNumber;
    public String attribute;
    private Player[] players;
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

    public void dealHand(int numberPlayers) {

        players = new Player[numberPlayers];
        ArrayList<Card> playerHand;

        for (int i = 0; i < numberPlayers; i++){
            Player player = new Player();
            players[i] = player;
            playerHand = deck.dealCards();
            player.setPlayerHand(playerHand);
            //System.out.println("player " + i + " " + playerHand);
        }
    }

    public String firstPlayerTurn(String attribute){
        System.out.println("Here is your hand:");
        System.out.println(players[0].playerHand);
        Scanner playerChoice = new Scanner(System.in);
        System.out.println("Please enter the number of the card you would like to play:");
        int CardChoice = Integer.parseInt(playerChoice.next());
        Card chosenCard = players[0].playerHand.get(CardChoice);
        System.out.println("Player 1 has played card: " + players[0].playerHand.get(CardChoice));

        //amend decks to played cards
        deck.playedCards.add(players[0].playerHand.get(CardChoice));
        players[0].playerHand.remove(CardChoice);

        if(chosenCard.getClass() == TrumpCard.class){
            attribute = "Hardness";
            System.out.println("You have chosen to play by the" + attribute + " category.");
            return attribute;
        }
        else {
            System.out.println("Which category would you like to play by?");
            System.out.println("1. Hardness" + "\n" + "2. Specific Gravity" + "\n" + "3. Clevage" + "\n" + "4. Crystal Abundance" + "\n" + "5. Economic Value");
            int attributeChoice = playerChoice.nextInt();
            switch (attributeChoice){
                case 1: attribute = "Hardness";
                    System.out.println("You have chosen to play by the " + attribute + " category.");
                    return attribute;
                case 2: attribute = "Specific Gravity";
                    System.out.println("You have chosen to play by the " + attribute + " category.");
                    return attribute;
                case 3: attribute = "Clevage";
                    System.out.println("You have chosen to play by the " + attribute + " category.");
                    return attribute;
                case 4: attribute = "Crystal Abundance";
                    System.out.println("You have chosen to play by the " + attribute + " category.");
                    return attribute;
                case 5: attribute = "Economic Value";
                    System.out.println("You have chosen to play by the " + attribute + " category.");
                    return attribute;
            }

        }
        return null;
    }



    public String FirstBotTurn(int firstPlayer, String attribute){

        int cardChoice = 3;

        deck.playedCards.add(players[firstPlayer].playerHand.get(cardChoice));

        System.out.println("Player " + firstPlayer + " has played card: " + players[firstPlayer].playerHand.get(cardChoice));
        System.out.println(deck.playedCards);

        players[firstPlayer].playerHand.remove(cardChoice);

        //Let bot pick random playing category

        Random randAttribute = new Random();

        int randNumber = randAttribute.nextInt(5) + 1;
        switch (randNumber){
            case 1: attribute = "Hardness";
                System.out.println("You have chosen to play by the " + attribute + " category.");
                return attribute;
            case 2: attribute = "Specific Gravity";
                System.out.println("You have chosen to play by the " + attribute + " category.");
                return attribute;
            case 3: attribute = "Clevage";
                System.out.println("You have chosen to play by the " + attribute + " category.");
                return attribute;
            case 4: attribute = "Crystal Abundance";
                System.out.println("You have chosen to play by the " + attribute + " category.");
                return attribute;
            case 5: attribute = "Economic Value";
                System.out.println("You have chosen to play by the " + attribute + " category.");
                return attribute;
        }
        return null;
    }



    public int determineFirstPlayer(int dealerNumber, int numberPlayers){
        int startingPlayer = dealerNumber + 1;

        if (startingPlayer >= numberPlayers){
            startingPlayer = 1;
        }

        else { return startingPlayer; }

        return startingPlayer;
    }
}
