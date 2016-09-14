import javax.smartcardio.*;
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
    public Card card;
    public String currentAttribute;
    public double attributeHardness;
    public double playedCardHardness;


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

    public String firstPlayerTurn(){

        Scanner playerChoice = new Scanner(System.in);
        System.out.println("Please enter the number of the card you would like to play:");
        int CardChoice = Integer.parseInt(playerChoice.next());
        Card chosenCard = players[0].playerHand.get(CardChoice);
        System.out.println("Player 1 has played card: ");
        System.out.println(players[0].playerHand.get(CardChoice));

        //amend decks to played cards
        deck.playedCards.add(players[0].playerHand.get(CardChoice));
        players[0].playerHand.remove(CardChoice);

        if(chosenCard.getClass() == TrumpCard.class){
            //// TODO: 12/09/2016 fix trump card to get attribute from card
            System.out.println("You have chosen to play by the " + attribute + " category.");
            return this.attribute = String.valueOf(chosenCard.cardHardness);
        }
        else {
            System.out.println("Which category would you like to play by?");
            System.out.println("1. Hardness" + "\n" + "2. Specific Gravity" + "\n" + "3. Cleavage" + "\n" + "4. Crystal Abundance" + "\n" + "5. Economic Value");
            int attributeChoice = playerChoice.nextInt();
            switch (attributeChoice){
                case 1:
                    currentAttribute = "Hardness";
                    attributeHardness = chosenCard.cardHardness;
                    System.out.println("The chosen cards hardness is " + attributeHardness);
                    System.out.println("You have chosen to play by the Hardness category.");
                    getPlayedCardHardness();
                    break;
                case 2:
                    currentAttribute = "Specific Gravity";
                    attribute = String.valueOf(chosenCard.cardSpecificGravity);
                    System.out.println("You have chosen to play by the Specific Gravity category.");
                    break;
                case 3:
                    currentAttribute = "Cleavage";
                    attribute = String.valueOf(chosenCard.cardCleavage);
                    System.out.println("You have chosen to play by the Cleavage category.");
                    break;
                case 4:
                    currentAttribute = "Crystal Abundance";
                    attribute = String.valueOf(chosenCard.cardCrystalAbundance);
                    System.out.println("You have chosen to play by the Crystal Abundance category.");
                    break;
                case 5:
                    currentAttribute = "Economic Value";
                    attribute = String.valueOf(chosenCard.cardEconomicValue);
                    System.out.println("You have chosen to play by the Economic Value category.");
                    break;
            }

        }
        return attribute;
    }

    private void getPlayedCardHardness(){
        int cardCount = deck.playedCards.size() - 1;
        Card cardHardness = deck.playedCards.get(cardCount);
        this.playedCardHardness = cardHardness.cardHardness;
    }

    public boolean compareCardsHardness(){
        boolean canPlayCard;
        if (this.attributeHardness > this.playedCardHardness){
            canPlayCard = true;
        }
        else {
            canPlayCard = false;
        }
        return canPlayCard;
    }

    public String playerTurn(){
        Scanner playerChoice = new Scanner(System.in);
        System.out.println("Please enter the number of the card you would like to play:");
        int CardChoice = Integer.parseInt(playerChoice.next());
        Card chosenCard = players[0].playerHand.get(CardChoice);
        attributeHardness = chosenCard.cardHardness;

        if (currentAttribute.equals("Hardness")){
            compareCardsHardness();
           if (compareCardsHardness() == true){
                deck.playedCards.add(players[0].playerHand.get(CardChoice));
                players[0].playerHand.remove(CardChoice);
                System.out.println("Player 1 has played card: " + players[0].playerHand.get(CardChoice));
            }


            while (compareCardsHardness() == false){
                System.out.println("Please play a card that is a higher hardness than " + playedCardHardness);

                System.out.println("Please enter the number of the card you would like to play:");
                CardChoice = Integer.parseInt(playerChoice.next());
                chosenCard = players[0].playerHand.get(CardChoice);
                compareCardsHardness();
            }
        }

        return attribute;
    }


    public String FirstBotTurn(int firstPlayer){
        //// TODO: 12/09/2016 fix to include attribute from trump card
        int cardChoice = 3;
        Card chosenCard = players[firstPlayer].playerHand.get(cardChoice);

        deck.playedCards.add(players[firstPlayer].playerHand.get(cardChoice));

        System.out.println("Player " + firstPlayer + " has played card: ");
        System.out.println(players[firstPlayer].playerHand.get(cardChoice));

        players[firstPlayer].playerHand.remove(cardChoice);

        if(chosenCard.getClass() == TrumpCard.class){
            //// TODO: 12/09/2016 fix trump card to get attribute from card
            System.out.println("Player " + firstPlayer + " has chosen to play by the " + attribute + " category.");
            return attribute = String.valueOf(chosenCard.cardHardness);
        }
        else {
            //Let bot pick random playing category

            Random randAttribute = new Random();

            int randNumber = randAttribute.nextInt(5) + 1;
            switch (randNumber) {
                    case 1: attribute = String.valueOf(chosenCard.cardHardness);
                        System.out.println("You have chosen to play by the " + attribute + " category.");
                        break;
                    case 2:
                        attribute = String.valueOf(chosenCard.cardSpecificGravity);
                        System.out.println("You have chosen to play by the " + attribute + " category.");
                        break;
                    case 3:
                        attribute = String.valueOf(chosenCard.cardCleavage);
                        System.out.println("You have chosen to play by the " + attribute + " category.");
                        break;
                    case 4:
                        attribute = String.valueOf(chosenCard.cardCrystalAbundance);
                        System.out.println("You have chosen to play by the " + attribute + " category.");
                        break;
                    case 5:
                        attribute = String.valueOf(chosenCard.cardEconomicValue);
                        System.out.println("You have chosen to play by the " + attribute + " category.");
                        break;
            }
        }
        return attribute;
    }

    public void printHand(){
        System.out.println("Here is your hand:");
        for (int i = 0; i < players[0].playerHand.size(); i++) {
            System.out.println((i) + ". " + players[0].playerHand.get(i));
        }
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
