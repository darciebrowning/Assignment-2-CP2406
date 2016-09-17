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
    public double attributeSpecificGravity;
    public String attributeCleavage;
    public String attributeCrustalAbundance;
    public String attributeEconomicValue;

    public double playedCardHardness;
    public double playedCardSpecificGravity;
    public String playedCardCleavage;
    public String playedCardCrustalAbundance;
    public String playedCardEconomicValue;


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
                    attributeSpecificGravity = chosenCard.cardSpecificGravity;
                    System.out.println("You have chosen to play by the Specific Gravity category.");
                    getPlayedCardSpecificGravity();
                    break;
                case 3:
                    currentAttribute = "Cleavage";
                    attributeCleavage = chosenCard.cardCleavage;
                    System.out.println("You have chosen to play by the Cleavage category.");
                    getPlayedCardCleavage();
                    break;
                case 4:
                    currentAttribute = "Crustal Abundance";
                    attributeCrustalAbundance = chosenCard.cardCrystalAbundance;
                    System.out.println("You have chosen to play by the Crustal Abundance category.");
                    getPlayedCardCrustalAbundance();
                    break;
                case 5:
                    currentAttribute = "Economic Value";
                    attributeEconomicValue = chosenCard.cardEconomicValue;
                    System.out.println("You have chosen to play by the Economic Value category.");
                    getPlayedCardEconomicValue();
                    break;
            }

        }
        return attribute;
    }

    private void getPlayedCardEconomicValue() {
        int cardCount = deck.playedCards.size() - 1;
        Card cardCleavage = deck.playedCards.get(cardCount);
        this.playedCardEconomicValue = cardCleavage.cardEconomicValue;
    }

    private void getPlayedCardCrustalAbundance() {
        int cardCount = deck.playedCards.size() - 1;
        Card cardCleavage = deck.playedCards.get(cardCount);
        this.playedCardCrustalAbundance = cardCleavage.cardCrystalAbundance;
    }

    private void getPlayedCardCleavage() {
        int cardCount = deck.playedCards.size() - 1;
        Card cardCleavage = deck.playedCards.get(cardCount);
        this.playedCardCleavage = cardCleavage.cardCleavage;
    }

    private void getPlayedCardSpecificGravity() {
        int cardCount = deck.playedCards.size() - 1;
        Card cardSpecificGravity = deck.playedCards.get(cardCount);
        this.playedCardSpecificGravity = cardSpecificGravity.cardSpecificGravity;
    }

    private void getPlayedCardHardness(){
        int cardCount = deck.playedCards.size() - 1;
        Card cardHardness = deck.playedCards.get(cardCount);
        this.playedCardHardness = cardHardness.cardHardness;
    }

    public  boolean compareCardsSpecificGravity(){
        boolean canPlayCard;
        if (this.attributeSpecificGravity > this.playedCardSpecificGravity){
            canPlayCard = true;
        }
        else {
            canPlayCard = false;
        }
        return canPlayCard;
    }

    public boolean compareCardsEconomicValue() {
        int attributeCompare = 0;
        boolean canPlayCard;
        int playedAttributeCompare = 0;
        switch (attributeEconomicValue){
            case "I'm rich":
                attributeCompare = 6;
            case "very high":
                attributeCompare = 5;
                break;
            case "high":
                attributeCompare = 4;
                break;
            case "moderate":
                attributeCompare = 3;
                break;
            case "low":
                attributeCompare = 2;
                break;
            case "trivial":
                attributeCompare = 1;
                break;
        }

        switch (playedCardCrustalAbundance){
            case "I'm rich":
                playedAttributeCompare = 6;
                break;
            case "very high":
                playedAttributeCompare = 5;
                break;
            case "high":
                playedAttributeCompare = 4;
                break;
            case "moderate":
                playedAttributeCompare = 3;
                break;
            case "low":
                playedAttributeCompare = 2;
                break;
            case "trivial":
                playedAttributeCompare = 1;
                break;

        }if (attributeCompare > playedAttributeCompare){
            canPlayCard = true;
        }
        else {
            canPlayCard = false;
        }
        return canPlayCard;

    }

    public boolean compareCardsCrustalAbundance() {
        int attributeCompare = 0;
        boolean canPlayCard;
        int playedAttributeCompare = 0;
        switch (attributeCrustalAbundance){
            case "very high":
                attributeCompare = 6;
                break;
            case "high":
                attributeCompare = 5;
                break;
            case "moderate":
                attributeCompare = 4;
                break;
            case "low":
                attributeCompare = 3;
                break;
            case "trace":
                attributeCompare = 2;
                break;
            case "ultratrace":
                attributeCompare = 1;
                break;
        }

        switch (playedCardCrustalAbundance){
            case "very high":
                playedAttributeCompare = 6;
                break;
            case "high":
                playedAttributeCompare = 5;
                break;
            case "moderate":
                playedAttributeCompare = 4;
                break;
            case "low":
                playedAttributeCompare = 3;
                break;
            case "trace":
                playedAttributeCompare = 2;
                break;
            case "ultratrace":
                playedAttributeCompare = 1;
                break;

        }if (attributeCompare > playedAttributeCompare){
            canPlayCard = true;
        }
        else {
            canPlayCard = false;
        }
        return canPlayCard;

    }

    public boolean compareCardsCleavage(){
        int attributeCompare = 0;
        boolean canPlayCard;
        int playerAttributeCompare = 0;
        switch (attributeCleavage){
            case "6 perfect":
                attributeCompare = 14;
            case "5 perfect":
                attributeCompare = 13;
            case "4 perfect":
                attributeCompare = 12;
            case "3 perfect":
                attributeCompare = 11;
                break;
            case "2 perfect":
                attributeCompare = 10;
                break;
            case "1 perfect":
                attributeCompare = 9;
                break;
            case "1 perfect, 1 good":
                attributeCompare = 8;
                break;
            case "3 good":
                attributeCompare = 7;
                break;
            case "2 good":
                attributeCompare = 6;
                break;
            case "1 good":
                attributeCompare = 5;
                break;
            case "3 poor":
                attributeCompare = 4;
                break;
            case "2 poor":
                attributeCompare = 3;
                break;
            case "1 poor":
                attributeCompare = 2;
                break;
            case "poor/none":
                attributeCompare = 1;
                break;
            case "none":
                attributeCompare = 0;
                break;
        }

        switch (playedCardCleavage){
            case "6 perfect":
                playerAttributeCompare = 14;
            case "5 perfect":
                playerAttributeCompare = 13;
            case "4 perfect":
                playerAttributeCompare = 12;
            case "3 perfect":
                playerAttributeCompare = 11;
                break;
            case "2 perfect":
                playerAttributeCompare = 10;
                break;
            case "1 perfect":
                playerAttributeCompare = 9;
                break;
            case "1 perfect, 1 good":
                playerAttributeCompare = 8;
                break;
            case "3 good":
                playerAttributeCompare = 7;
                break;
            case "2 good":
                playerAttributeCompare = 6;
                break;
            case "1 good":
                playerAttributeCompare = 5;
                break;
            case "3 poor":
                playerAttributeCompare = 4;
                break;
            case "2 poor":
                playerAttributeCompare = 3;
                break;
            case "1 poor":
                playerAttributeCompare = 2;
                break;
            case "poor/none":
                playerAttributeCompare = 1;
                break;
            case "none":
                playerAttributeCompare = 0;
                break;
        }

        if (attributeCompare > playerAttributeCompare){
            canPlayCard = true;
        }
        else {
            canPlayCard = false;
        }
        return canPlayCard;

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

        getPlayedCardHardness();
        getPlayedCardEconomicValue();
        getPlayedCardCleavage();
        getPlayedCardSpecificGravity();
        getPlayedCardCrustalAbundance();

        Scanner playerChoice = new Scanner(System.in);
        System.out.println("Please enter the number of the card you would like to play:");
        int CardChoice = Integer.parseInt(playerChoice.next());
        Card chosenCard = players[0].playerHand.get(CardChoice);
        attributeHardness = chosenCard.cardHardness;
        attributeSpecificGravity = chosenCard.cardSpecificGravity;
        attributeCleavage = chosenCard.cardCleavage;
        attributeCrustalAbundance = chosenCard.cardCrystalAbundance;
        attributeEconomicValue = chosenCard.cardCrystalAbundance;

        if(chosenCard.getClass() == TrumpCard.class){
            if (chosenCard.cardTitle.equals("The miner")){
                currentAttribute = "Economic Value";
                System.out.println("The miner card has changed the category to Economic Value.");
            }
            else if (chosenCard.cardTitle.equals("The Petrologist")){
                currentAttribute = "Crustal Abundance";
                System.out.println("The Petrologist card has changed the category to Crustal Abundance.");
            }
            else if (chosenCard.cardTitle.equals("The Gemmologist")){
                currentAttribute = "Hardness";
                System.out.println("The Gemmologist card has changed the category to Hardness.");
            }
            else if (chosenCard.cardTitle.equals("The Mineralogist")){
                currentAttribute = "Cleavage";
                System.out.println("The Mineralogist card has changed the category to Cleavage.");
            }
            else if (chosenCard.cardTitle.equals("The Geophysicist")){
                currentAttribute = "Specific Gravity";
                System.out.println("The Geophysicist card has changed the category to Specific Gravity.");
            }
            else {
                System.out.println("Which category would you like to play by?");
                System.out.println("1. Hardness" + "\n" + "2. Specific Gravity" + "\n" + "3. Cleavage" + "\n" + "4. Crystal Abundance" + "\n" + "5. Economic Value");
                int attributeChoice = playerChoice.nextInt();
                switch (attributeChoice){
                    case 1:
                        currentAttribute = "Hardness";
                        break;
                    case 2:
                        currentAttribute = "Specific Gravity";
                        break;
                    case 3:
                        currentAttribute = "Cleavage";
                        break;
                    case 4:
                        currentAttribute = "Crustal Abundance";
                        break;
                    case 5:
                        currentAttribute = "Economic Value";
                        break;
                }
                System.out.println("The Geologist card has changed the category to " + currentAttribute);
            }
        }


        while (compareCardsHardness() == false){
            System.out.println(attributeHardness);
            System.out.println("Please play a card that is a higher hardness than " + playedCardHardness);

            System.out.println("Please enter the number of the card you would like to play:");
            CardChoice = Integer.parseInt(playerChoice.next());
            chosenCard = players[0].playerHand.get(CardChoice);
            attributeHardness = chosenCard.cardHardness;
            compareCardsHardness();
            }

        if (currentAttribute.equals("Hardness")){
            compareCardsHardness();
            if (compareCardsHardness() == true){
                deck.playedCards.add(players[0].playerHand.get(CardChoice));
                players[0].playerHand.remove(CardChoice);
                System.out.println("Player 1 has played card: " + players[0].playerHand.get(CardChoice));
            }
        }

        else if (currentAttribute.equals("Specific Gravity")){
            compareCardsSpecificGravity();
            if (compareCardsSpecificGravity() == true){
                deck.playedCards.add(players[0].playerHand.get(CardChoice));
                players[0].playerHand.remove(CardChoice);
                System.out.println("Player 1 has played card: " + players[0].playerHand.get(CardChoice));
            }


            while (compareCardsSpecificGravity()== false){
                System.out.println("Please play a card that is a higher Specific Gravity than " + playedCardSpecificGravity);

                System.out.println("Please enter the number of the card you would like to play:");
                CardChoice = Integer.parseInt(playerChoice.next());
                chosenCard = players[0].playerHand.get(CardChoice);
                attributeSpecificGravity = chosenCard.cardSpecificGravity;
                compareCardsSpecificGravity();
            }
        }

        else if (currentAttribute.equals("Cleavage")){
            compareCardsCleavage();
            if (compareCardsCleavage() == true){
                deck.playedCards.add(players[0].playerHand.get(CardChoice));
                players[0].playerHand.remove(CardChoice);
                System.out.println("Player 1 has played card: " + players[0].playerHand.get(CardChoice));
            }


            while (compareCardsCleavage()== false){
                System.out.println("Please play a card that is a higher Cleavage than " + playedCardCleavage);

                System.out.println("Please enter the number of the card you would like to play:");
                CardChoice = Integer.parseInt(playerChoice.next());
                chosenCard = players[0].playerHand.get(CardChoice);
                attributeCleavage = chosenCard.cardCleavage;
                compareCardsCleavage();
            }
        }

        else if (currentAttribute.equals("Crustal Abundance")){
            compareCardsCrustalAbundance();
            if (compareCardsCrustalAbundance() == true){
                deck.playedCards.add(players[0].playerHand.get(CardChoice));
                players[0].playerHand.remove(CardChoice);
                System.out.println("Player 1 has played card: " + players[0].playerHand.get(CardChoice));
            }


            while (compareCardsCrustalAbundance()== false){
                System.out.println("Please play a card that is a higher Cleavage than " + playedCardCrustalAbundance);

                System.out.println("Please enter the number of the card you would like to play:");
                CardChoice = Integer.parseInt(playerChoice.next());
                chosenCard = players[0].playerHand.get(CardChoice);
                attributeCrustalAbundance = chosenCard.cardCrystalAbundance;
                compareCardsCrustalAbundance();
            }
        }

        else if (currentAttribute.equals("Economic Value")){
            compareCardsEconomicValue();
            if (compareCardsEconomicValue() == true){
                deck.playedCards.add(players[0].playerHand.get(CardChoice));
                players[0].playerHand.remove(CardChoice);
                System.out.println("Player 1 has played card: " + players[0].playerHand.get(CardChoice));
            }


            while (compareCardsEconomicValue()== false){
                System.out.println("Please play a card that is a higher Economic Value than " + playedCardEconomicValue);

                System.out.println("Please enter the number of the card you would like to play:");
                CardChoice = Integer.parseInt(playerChoice.next());
                chosenCard = players[0].playerHand.get(CardChoice);
                attributeEconomicValue = chosenCard.cardEconomicValue;
                compareCardsEconomicValue();
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
            System.out.println("Player " + firstPlayer + " has chosen to play by the " + currentAttribute + " category.");

        }
        else {
            //Let bot pick random playing category

            Random randAttribute = new Random();

            int randNumber = randAttribute.nextInt(5) + 1;
            switch (randNumber) {
                case 1:
                    currentAttribute = "Hardness";
                    attributeHardness = chosenCard.cardHardness;
                    System.out.println("The chosen cards hardness is " + attributeHardness);
                    System.out.println("You have chosen to play by the Hardness category.");
                    getPlayedCardHardness();
                    break;
                case 2:
                    currentAttribute = "Specific Gravity";
                    attributeSpecificGravity = chosenCard.cardSpecificGravity;
                    System.out.println("You have chosen to play by the Specific Gravity category.");
                    getPlayedCardSpecificGravity();
                    break;
                case 3:
                    currentAttribute = "Cleavage";
                    attributeCleavage = chosenCard.cardCleavage;
                    System.out.println("You have chosen to play by the Cleavage category.");
                    getPlayedCardCleavage();
                    break;
                case 4:
                    currentAttribute = "Crustal Abundance";
                    attributeCrustalAbundance = chosenCard.cardCrystalAbundance;
                    System.out.println("You have chosen to play by the Crustal Abundance category.");
                    getPlayedCardCrustalAbundance();
                    break;
                case 5:
                    currentAttribute = "Economic Value";
                    attributeEconomicValue = chosenCard.cardEconomicValue;
                    System.out.println("You have chosen to play by the Economic Value category.");
                    getPlayedCardEconomicValue();
                    break;
            }
        }
        return currentAttribute;
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

    public void botTurn(){
        getPlayedCardHardness();
        getPlayedCardCleavage();
        getPlayedCardCrustalAbundance();
        getPlayedCardEconomicValue();
        getPlayedCardSpecificGravity();

        if (currentAttribute.equals("Hardness")){

        }


    }
}


