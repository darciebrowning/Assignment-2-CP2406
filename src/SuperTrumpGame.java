import javax.smartcardio.*;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

/**
 * Created by Darcie on 3/09/2016.
 *
 */

public class SuperTrumpGame {
    private  int numberPlayers;
    public int dealerNumber;
    public Player[] players;
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
    public boolean gameIsover = false;


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
        players[0].isHuman = true;
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
                if (chosenCard.cardTitle.equals("The miner")) {
                    currentAttribute = "Economic Value";
                    System.out.println("The miner card has changed the category to Economic Value.");
                    chosenCard.cardEconomicValue = "null";
                    players[0].playerHand.remove(chosenCard);
                }
                else if (chosenCard.cardTitle.equals("The Petrologist")) {
                    currentAttribute = "Crustal Abundance";
                    System.out.println("The Petrologist card has changed the category to Crustal Abundance.");
                    chosenCard.cardCrystalAbundance = "null";
                    players[0].playerHand.remove(chosenCard);
                }
                else if (chosenCard.cardTitle.equals("The Gemmologist")) {
                    currentAttribute = "Hardness";
                    System.out.println("The Gemmologist card has changed the category to Hardness.");
                    chosenCard.cardHardness = 0.0;
                    players[0].playerHand.remove(chosenCard);
                }
                else if (chosenCard.cardTitle.equals("The Mineralogist")) {
                    currentAttribute = "Cleavage";
                    System.out.println("The Mineralogist card has changed the category to Cleavage.");
                    chosenCard.cardCleavage = "null";
                    players[0].playerHand.remove(chosenCard);
                }
                else if (chosenCard.cardTitle.equals("The Geophysicist")) {
                    currentAttribute = "Specific Gravity";
                    System.out.println("The Geophysicist card has changed the category to Specific Gravity.");
                    chosenCard.cardSpecificGravity = 0.0;
                    players[0].playerHand.remove(chosenCard);
                }
                else {
                    System.out.println("The Geologist card lets you choose a playing category. Which category would you like to play by?");
                    System.out.println("1. Hardness" + "\n" + "2. Specific Gravity" + "\n" + "3. Cleavage" + "\n" + "4. Crystal Abundance" + "\n" + "5. Economic Value");
                    int attributeChoice = playerChoice.nextInt();
                    switch (attributeChoice){
                        case 1:
                            currentAttribute = "Hardness";
                            attributeHardness = chosenCard.cardHardness;
                            System.out.println("The Geologist has changed the category to Hardness.");
                            System.out.println("The chosen cards hardness is " + attributeHardness);
                            playedCardHardness = chosenCard.cardHardness;
                            break;
                        case 2:
                            currentAttribute = "Specific Gravity";
                            attributeSpecificGravity = chosenCard.cardSpecificGravity;
                            System.out.println("The Geologist has changed the category to Specific Gravity.");
                            System.out.println("The chosen cards hardness is " + attributeSpecificGravity);
                            playedCardSpecificGravity = chosenCard.cardSpecificGravity;
                            break;
                        case 3:
                            currentAttribute = "Cleavage";
                            attributeCleavage = chosenCard.cardCleavage;
                            System.out.println("The Geologist has changed the category to Cleavage.");
                            System.out.println("The chosen cards hardness is " + attributeCleavage);
                            playedCardCleavage = chosenCard.cardCleavage;
                            break;
                        case 4:
                            currentAttribute = "Crustal Abundance";
                            attributeCrustalAbundance = chosenCard.cardCrystalAbundance;
                            System.out.println("The Geologist has changed the category to Crustal Abundance.");
                            System.out.println("The chosen cards hardness is " + attributeCrustalAbundance);
                            playedCardCrustalAbundance = chosenCard.cardCrystalAbundance;
                            break;
                        case 5:
                            currentAttribute = "Economic Value";
                            attributeEconomicValue = chosenCard.cardEconomicValue;
                            System.out.println("The Geologist has changed the category to Economic Value.");
                            System.out.println("The chosen cards hardness is " + attributeEconomicValue);
                            playedCardEconomicValue = chosenCard.cardEconomicValue;
                            break;
                    }
                }

        }
        else {
            System.out.println("Which category would you like to play by?");
            System.out.println("1. Hardness" + "\n" + "2. Specific Gravity" + "\n" + "3. Cleavage" + "\n" + "4. Crystal Abundance" + "\n" + "5. Economic Value");
            int attributeChoice = playerChoice.nextInt();
            switch (attributeChoice){
                case 1:
                    currentAttribute = "Hardness";
                    attributeHardness = chosenCard.cardHardness;
                    System.out.println("You have chosen to play by the Hardness category.");
                    System.out.println("The chosen cards hardness is " + attributeHardness);
                    playedCardHardness = chosenCard.cardHardness;
                    break;
                case 2:
                    currentAttribute = "Specific Gravity";
                    attributeSpecificGravity = chosenCard.cardSpecificGravity;
                    System.out.println("You have chosen to play by the Specific Gravity category.");
                    System.out.println("The chosen cards hardness is " + attributeSpecificGravity);
                    playedCardSpecificGravity = chosenCard.cardSpecificGravity;
                    break;
                case 3:
                    currentAttribute = "Cleavage";
                    attributeCleavage = chosenCard.cardCleavage;
                    System.out.println("You have chosen to play by the Cleavage category.");
                    System.out.println("The chosen cards hardness is " + attributeCleavage);
                    playedCardCleavage = chosenCard.cardCleavage;
                    break;
                case 4:
                    currentAttribute = "Crustal Abundance";
                    attributeCrustalAbundance = chosenCard.cardCrystalAbundance;
                    System.out.println("You have chosen to play by the Crustal Abundance category.");
                    System.out.println("The chosen cards hardness is " + attributeCrustalAbundance);
                    playedCardCrustalAbundance = chosenCard.cardCrystalAbundance;
                    break;
                case 5:
                    currentAttribute = "Economic Value";
                    attributeEconomicValue = chosenCard.cardEconomicValue;
                    System.out.println("You have chosen to play by the Economic Value category.");
                    System.out.println("The chosen cards hardness is " + attributeEconomicValue);
                    playedCardEconomicValue = chosenCard.cardEconomicValue;
                    break;
            }
            getPlayedCardAttributeValue();
        }
        return currentAttribute;
    }

    private void getPlayedCardAttributeValue() {
        int cardCount = deck.playedCards.size() - 1;
        Card cardPlayed = deck.playedCards.get(cardCount);
        if (currentAttribute.equals("Hardness")){
            playedCardHardness = cardPlayed.cardHardness;
        }
        else if (currentAttribute.equals("Specific Gravity")){
            playedCardSpecificGravity = cardPlayed.cardSpecificGravity;
        }
        else if (currentAttribute.equals("Cleavage")){
            playedCardCleavage = cardPlayed.cardCleavage;
        }
        else if (currentAttribute.equals("Crustal Abundance")){
            playedCardCrustalAbundance = cardPlayed.cardCrystalAbundance;
        }
        else {
            playedCardEconomicValue = cardPlayed.cardEconomicValue;
        }
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
            case "I'm rich!":
                attributeCompare = 6;
                break;
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
            case "null":
                attributeCompare = 0;
                break;
        }

        switch (playedCardEconomicValue){
            case "I'm rich!":
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
            case "null":
                playedAttributeCompare = 0;
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
            case "null":
                attributeCompare = 0;
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
            case "null":
                playedAttributeCompare = 0;
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
                attributeCompare = 15;
                break;
            case "5 perfect":
                attributeCompare = 14;
                break;
            case "4 perfect":
                attributeCompare = 13;
                break;
            case "3 perfect":
                attributeCompare = 12;
                break;
            case "2 perfect":
                attributeCompare = 11;
                break;
            case "1 perfect":
                attributeCompare = 10;
                break;
            case "1 perfect, 1 good":
                attributeCompare = 9;
                break;
            case "3 good":
                attributeCompare = 8;
                break;
            case "2 good":
                attributeCompare = 7;
                break;
            case "1 good":
                attributeCompare = 6;
                break;
            case "3 poor":
                attributeCompare = 5;
                break;
            case "2 poor":
                attributeCompare = 4;
                break;
            case "1 poor":
                attributeCompare = 3;
                break;
            case "poor/none":
                attributeCompare = 2;
                break;
            case "none":
                attributeCompare = 1;
                break;
            case "null":
                attributeCompare = 0;
                break;
        }

        switch (playedCardCleavage){
            case "6 perfect":
                playerAttributeCompare = 15;
                break;
            case "5 perfect":
                playerAttributeCompare = 14;
                break;
            case "4 perfect":
                playerAttributeCompare = 13;
                break;
            case "3 perfect":
                playerAttributeCompare = 12;
                break;
            case "2 perfect":
                playerAttributeCompare = 11;
                break;
            case "1 perfect":
                playerAttributeCompare = 10;
                break;
            case "1 perfect, 1 good":
                playerAttributeCompare = 9;
                break;
            case "3 good":
                playerAttributeCompare = 8;
                break;
            case "2 good":
                playerAttributeCompare = 7;
                break;
            case "1 good":
                playerAttributeCompare = 6;
                break;
            case "3 poor":
                playerAttributeCompare = 5;
                break;
            case "2 poor":
                playerAttributeCompare = 4;
                break;
            case "1 poor":
                playerAttributeCompare = 3;
                break;
            case "poor/none":
                playerAttributeCompare = 2;
                break;
            case "none":
                playerAttributeCompare = 1;
                break;
            case "null":
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

    public String playerTurn() {
        boolean x = false;
        gameOver(numberPlayers);

        getPlayedCardAttributeValue();

        if (currentAttribute.equals("Hardness")) {
            System.out.println("Please play a card that has a higher hardness than " + playedCardHardness);
        } else if (currentAttribute.equals("Cleavage")) {
            System.out.println("Please play a card that has a higher cleavage than " + playedCardCleavage);
        } else if (currentAttribute.equals("Specific Gravity")) {
            System.out.println("Please play a card that has a higher specific gravity than " + playedCardSpecificGravity);
        } else if (currentAttribute.equals("Crustal Abundance")) {
            System.out.println("Please play a card that has a higher crustal abundance than " + playedCardCrustalAbundance);
        } else {
            System.out.println("Please play a card that has a higher economic value than " + playedCardEconomicValue);
        }

        Scanner playerChoice = new Scanner(System.in);

        printHand();
        System.out.println("Enter the number of the card you would like to play or type 'pass' to pickup a card");
        String CardChoice = (playerChoice.nextLine());

        try {
            int cardChoice = Integer.parseInt(CardChoice);
            Card chosenCard = players[0].playerHand.get(cardChoice);
            attributeHardness = chosenCard.cardHardness;
            attributeSpecificGravity = chosenCard.cardSpecificGravity;
            attributeCleavage = chosenCard.cardCleavage;
            attributeCrustalAbundance = chosenCard.cardCrystalAbundance;
            attributeEconomicValue = chosenCard.cardCrystalAbundance;

            if (chosenCard.getClass() == TrumpCard.class) {
                trumpCardCategoryChangePlayer(chosenCard);
                players[0].playerHand.remove(chosenCard);

            } else {

                if (currentAttribute.equals("Hardness")) {
                    compareCardsHardness();

                    if (compareCardsHardness()) {
                        deck.playedCards.add(players[0].playerHand.get(cardChoice));
                        System.out.println("Player 1 has played card: " + players[0].playerHand.get(cardChoice));
                        players[0].playerHand.remove(cardChoice);
                    }

                    while (!compareCardsHardness() && !x) {
                        System.out.println(attributeHardness);
                        System.out.println("Please play a card that is a higher hardness than " + playedCardHardness + " or type 'pass' to pickup a card.");

                        //System.out.println("Please enter the number of the card you would like to play:");
                        CardChoice = (playerChoice.nextLine());

                        try {
                            cardChoice = Integer.parseInt(CardChoice);
                        }
                        catch (NumberFormatException e) {
                            if ("pass".equals(CardChoice)) {
                                //Pick up card if the user types pass.
                                System.out.println("You have picked up ");
                                Card passCard = deck.playerTurnPass();
                                players[0].playerHand.add(passCard);
                                System.out.println(passCard);
                                x = true;
                            }
                        }

                        chosenCard = players[0].playerHand.get(cardChoice);
                        if (chosenCard.getClass() == TrumpCard.class) {
                            trumpCardCategoryChangePlayer(chosenCard);
                            players[0].playerHand.remove(chosenCard);
                        }
                        attributeHardness = chosenCard.cardHardness;
                        compareCardsHardness();
                    }

                }
            }

        }
        catch (NumberFormatException e) {
            if ("pass".equals(CardChoice)) {
                //Pick up card if the user types pass.
                System.out.println("You have picked up ");
                Card passCard = deck.playerTurnPass();
                players[0].playerHand.add(passCard);
                System.out.println(passCard);
            }
        }
        return currentAttribute;
    }




/**
            if (chosenCard.getClass() == TrumpCard.class) {
                if (chosenCard.cardTitle.equals("The miner")) {
                    currentAttribute = "Economic Value";
                    System.out.println("The miner card has changed the category to Economic Value.");
                    players[0].playerHand.remove(CardChoice);
                } else if (chosenCard.cardTitle.equals("The Petrologist")) {
                    currentAttribute = "Crustal Abundance";
                    System.out.println("The Petrologist card has changed the category to Crustal Abundance.");
                    players[0].playerHand.remove(CardChoice);
                } else if (chosenCard.cardTitle.equals("The Gemmologist")) {
                    currentAttribute = "Hardness";
                    System.out.println("The Gemmologist card has changed the category to Hardness.");
                    players[0].playerHand.remove(CardChoice);
                } else if (chosenCard.cardTitle.equals("The Mineralogist")) {
                    currentAttribute = "Cleavage";
                    System.out.println("The Mineralogist card has changed the category to Cleavage.");
                    players[0].playerHand.remove(CardChoice);
                } else if (chosenCard.cardTitle.equals("The Geophysicist")) {
                    currentAttribute = "Specific Gravity";
                    System.out.println("The Geophysicist card has changed the category to Specific Gravity.");
                    players[0].playerHand.remove(CardChoice);
                } else {
                    System.out.println("Which category would you like to play by?");
                    System.out.println("1. Hardness" + "\n" + "2. Specific Gravity" + "\n" + "3. Cleavage" + "\n" + "4. Crystal Abundance" + "\n" + "5. Economic Value");
                    int attributeChoice = playerChoice.nextInt();
                    switch (attributeChoice) {
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
                    players[0].playerHand.remove(CardChoice);
                }
            } else if (chosenCard.getClass() == MineralCard.class) {

                if (currentAttribute.equals("Hardness")) {
                    compareCardsHardness();

                    if (compareCardsHardness()) {
                        deck.playedCards.add(players[0].playerHand.get(CardChoice));
                        System.out.println("Player 1 has played card: " + players[0].playerHand.get(CardChoice));
                        players[0].playerHand.remove(CardChoice);
                    }

                    while (!compareCardsHardness()) {
                        System.out.println(attributeHardness);
                        System.out.println("Please play a card that is a higher hardness than " + playedCardHardness);

                        System.out.println("Please enter the number of the card you would like to play:");
                        CardChoice = Integer.parseInt(playerChoice.next());
                        chosenCard = players[0].playerHand.get(CardChoice);
                        attributeHardness = chosenCard.cardHardness;
                        compareCardsHardness();
                    }

                } else if (currentAttribute.equals("Specific Gravity")) {
                    compareCardsSpecificGravity();
                    if (compareCardsSpecificGravity()) {
                        deck.playedCards.add(players[0].playerHand.get(CardChoice));
                        System.out.println("Player 1 has played card: " + players[0].playerHand.get(CardChoice));
                        players[0].playerHand.remove(CardChoice);
                    }


                    while (!compareCardsSpecificGravity()) {
                        System.out.println("Please play a card that is a higher Specific Gravity than " + playedCardSpecificGravity);

                        System.out.println("Please enter the number of the card you would like to play:");
                        CardChoice = Integer.parseInt(playerChoice.next());
                        chosenCard = players[0].playerHand.get(CardChoice);
                        attributeSpecificGravity = chosenCard.cardSpecificGravity;
                        compareCardsSpecificGravity();
                    }
                } else if (currentAttribute.equals("Cleavage")) {
                    compareCardsCleavage();
                    if (compareCardsCleavage()) {
                        deck.playedCards.add(players[0].playerHand.get(CardChoice));
                        System.out.println("Player 1 has played card: " + players[0].playerHand.get(CardChoice));
                        players[0].playerHand.remove(CardChoice);
                    }


                    while (!compareCardsCleavage()) {
                        System.out.println("Please play a card that is a higher Cleavage than " + playedCardCleavage);

                        System.out.println("Please enter the number of the card you would like to play:");
                        CardChoice = Integer.parseInt(playerChoice.next());
                        chosenCard = players[0].playerHand.get(CardChoice);
                        attributeCleavage = chosenCard.cardCleavage;
                        compareCardsCleavage();
                    }
                } else if (currentAttribute.equals("Crustal Abundance")) {
                    compareCardsCrustalAbundance();
                    if (compareCardsCrustalAbundance()) {
                        deck.playedCards.add(players[0].playerHand.get(CardChoice));
                        System.out.println("Player 1 has played card: " + players[0].playerHand.get(CardChoice));
                        players[0].playerHand.remove(CardChoice);
                    }


                    while (!compareCardsCrustalAbundance()) {
                        System.out.println("Please play a card that is a higher Cleavage than " + playedCardCrustalAbundance);

                        System.out.println("Please enter the number of the card you would like to play:");
                        CardChoice = Integer.parseInt(playerChoice.next());
                        chosenCard = players[0].playerHand.get(CardChoice);
                        attributeCrustalAbundance = chosenCard.cardCrystalAbundance;
                        compareCardsCrustalAbundance();
                    }
                } else if (currentAttribute.equals("Economic Value")) {
                    compareCardsEconomicValue();
                    if (compareCardsEconomicValue()) {
                        deck.playedCards.add(players[0].playerHand.get(CardChoice));
                        System.out.println("Player 1 has played card: " + players[0].playerHand.get(CardChoice));
                        players[0].playerHand.remove(CardChoice);
                    }


                    while (!compareCardsEconomicValue()) {
                        System.out.println("Please play a card that is a higher Economic Value than " + playedCardEconomicValue);

                        System.out.println("Please enter the number of the card you would like to play:");
                        CardChoice = Integer.parseInt(playerChoice.next());
                        chosenCard = players[0].playerHand.get(CardChoice);
                        attributeEconomicValue = chosenCard.cardEconomicValue;
                        compareCardsEconomicValue();
                    }
                }
            }
        }
        else {
            System.out.println("You have picked up ");
            Card passCard = deck.playerTurnPass();
            players[0].playerHand.add(passCard);
            System.out.println(passCard);
        }

        return attribute;
    }

*/

    public String firstBotTurn(int firstPlayer) {
        //// TODO: 12/09/2016 fix to include attribute from trump card
        int cardChoice = 3;
        Card chosenCard = players[firstPlayer].playerHand.get(cardChoice);
        System.out.println(chosenCard);

        deck.playedCards.add(players[firstPlayer].playerHand.get(cardChoice));

        if (chosenCard.getClass() == TrumpCard.class) {
            if (chosenCard.cardTitle.equals("The miner")) {
                currentAttribute = "Economic Value";
                System.out.println("The miner card has changed the category to Economic Value.");
                players[firstPlayer].playerHand.remove(chosenCard);
            } else if (chosenCard.cardTitle.equals("The Petrologist")) {
                currentAttribute = "Crustal Abundance";
                System.out.println("The Petrologist card has changed the category to Crustal Abundance.");
                players[firstPlayer].playerHand.remove(chosenCard);
            } else if (chosenCard.cardTitle.equals("The Gemmologist")) {
                currentAttribute = "Hardness";
                System.out.println("The Gemmologist card has changed the category to Hardness.");
                players[firstPlayer].playerHand.remove(chosenCard);
            } else if (chosenCard.cardTitle.equals("The Mineralogist")) {
                currentAttribute = "Cleavage";
                System.out.println("The Mineralogist card has changed the category to Cleavage.");
                players[firstPlayer].playerHand.remove(chosenCard);
            } else if (chosenCard.cardTitle.equals("The Geophysicist")) {
                currentAttribute = "Specific Gravity";
                System.out.println("The Geophysicist card has changed the category to Specific Gravity.");
                players[firstPlayer].playerHand.remove(chosenCard);
            } else {
                Random rand = new Random();
                int attributeChoice = rand.nextInt((5 - 1) + 1) + 1;
                switch (attributeChoice) {
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
        else{
                //Let bot pick random playing category

                Random randAttribute = new Random();

                int randNumber = randAttribute.nextInt(5) + 1;
                switch (randNumber) {
                    case 1:
                        currentAttribute = "Hardness";
                        attributeHardness = chosenCard.cardHardness;
                        System.out.println("Bot has chosen to play by the Hardness category.");
                        break;
                    case 2:
                        currentAttribute = "Specific Gravity";
                        attributeSpecificGravity = chosenCard.cardSpecificGravity;
                        System.out.println("Bot has chosen to play by the Specific Gravity category.");

                        break;
                    case 3:
                        currentAttribute = "Cleavage";
                        attributeCleavage = chosenCard.cardCleavage;
                        System.out.println("Bot has chosen to play by the Cleavage category.");

                        break;
                    case 4:
                        currentAttribute = "Crustal Abundance";
                        attributeCrustalAbundance = chosenCard.cardCrystalAbundance;
                        System.out.println("Bot has chosen to play by the Crustal Abundance category.");

                        break;
                    case 5:
                        currentAttribute = "Economic Value";
                        attributeEconomicValue = chosenCard.cardEconomicValue;
                        System.out.println("Bot has chosen to play by the Economic Value category.");

                        break;
                }
                getPlayedCardAttributeValue();
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

    public void botTurn(int currentPosition) {

        System.out.println(players[currentPosition].playerHand);
        getPlayedCardAttributeValue();
        gameOver(numberPlayers);
        int i;
        Card chosenCard;


        if (currentAttribute.equals("Hardness")) {

            for (i = 0; i < players[currentPosition].playerHand.size() + 1; i++) {
                if (i < players[currentPosition].playerHand.size()) {
                    chosenCard = players[currentPosition].playerHand.get(i);

                    if (chosenCard.getClass() == MineralCard.class) {

                        attributeHardness = chosenCard.cardHardness;
                        if (compareCardsHardness()) {
                            players[currentPosition].playerHand.remove(chosenCard);
                            deck.playedCards.add(chosenCard);
                            System.out.println(chosenCard);
                            playedCardHardness = chosenCard.cardHardness;
                            i = players[currentPosition].playerHand.size() + 2;
                        }

                    }
                }
            }

            if (i == players[currentPosition].playerHand.size() + 1) {

                for (i = 0; i < players[currentPosition].playerHand.size() + 1; i++) {
                    if (i < players[currentPosition].playerHand.size()) {
                        chosenCard = players[currentPosition].playerHand.get(i);
                        if (chosenCard.getClass() == TrumpCard.class) {

                            trumpCardCategoryChange(chosenCard);
                            players[currentPosition].playerHand.remove(chosenCard);
                            deck.playedCards.add(chosenCard);
                            players[currentPosition].playerHand.remove(chosenCard);
                            deck.playedCards.add(chosenCard);
                            playedCardHardness = 0.0;
                            i = players[currentPosition].playerHand.size() + 2;
                        }

                    }
                }
            }

            if (i == players[currentPosition].playerHand.size() + 1) {
                System.out.println("This player has picked up a card.");
                Card passCard = deck.playerTurnPass();
                players[currentPosition].playerHand.add(passCard);
            }
        }

        if (currentAttribute.equals("Cleavage")) {

            for (i = 0; i < players[currentPosition].playerHand.size() + 1; i++) {
                if (i < players[currentPosition].playerHand.size()) {
                    chosenCard = players[currentPosition].playerHand.get(i);

                    if (chosenCard.getClass() == MineralCard.class) {

                        attributeCleavage = chosenCard.cardCleavage;
                        if (compareCardsCleavage()) {
                            players[currentPosition].playerHand.remove(chosenCard);
                            deck.playedCards.add(chosenCard);
                            System.out.println(chosenCard);
                            playedCardCleavage = chosenCard.cardCleavage;
                            i = players[currentPosition].playerHand.size() + 2;
                        }

                    }
                }
            }

            if (i == players[currentPosition].playerHand.size() + 1) {

                for (i = 0; i < players[currentPosition].playerHand.size() + 1; i++) {
                    if (i < players[currentPosition].playerHand.size()) {
                        chosenCard = players[currentPosition].playerHand.get(i);
                        if (chosenCard.getClass() == TrumpCard.class) {

                            trumpCardCategoryChange(chosenCard);
                            players[currentPosition].playerHand.remove(chosenCard);
                            deck.playedCards.add(chosenCard);
                            players[currentPosition].playerHand.remove(chosenCard);
                            deck.playedCards.add(chosenCard);
                            playedCardCleavage = "none";
                            i = players[currentPosition].playerHand.size() + 2;
                        }

                    }
                }
            }

            if (i == players[currentPosition].playerHand.size() + 1) {
                System.out.println("This player has picked up a card.");
                Card passCard = deck.playerTurnPass();
                players[currentPosition].playerHand.add(passCard);
            }
        }

        if (currentAttribute.equals("Specific Gravity")) {

            for (i = 0; i < players[currentPosition].playerHand.size() + 1; i++) {
                if (i < players[currentPosition].playerHand.size()) {
                    chosenCard = players[currentPosition].playerHand.get(i);

                    if (chosenCard.getClass() == MineralCard.class) {

                        attributeSpecificGravity = chosenCard.cardSpecificGravity;
                        if (compareCardsSpecificGravity()) {
                            players[currentPosition].playerHand.remove(chosenCard);
                            deck.playedCards.add(chosenCard);
                            System.out.println(chosenCard);
                            playedCardSpecificGravity = chosenCard.cardSpecificGravity;
                            i = players[currentPosition].playerHand.size() + 2;
                        }

                    }
                }
            }

            if (i == players[currentPosition].playerHand.size() + 1) {

                for (i = 0; i < players[currentPosition].playerHand.size() + 1; i++) {
                    if (i < players[currentPosition].playerHand.size()) {
                        chosenCard = players[currentPosition].playerHand.get(i);
                        if (chosenCard.getClass() == TrumpCard.class) {

                            trumpCardCategoryChange(chosenCard);
                            players[currentPosition].playerHand.remove(chosenCard);
                            deck.playedCards.add(chosenCard);
                            players[currentPosition].playerHand.remove(chosenCard);
                            deck.playedCards.add(chosenCard);
                            playedCardSpecificGravity = 0.0;
                            i = players[currentPosition].playerHand.size() + 2;
                        }

                    }
                }
            }

            if (i == players[currentPosition].playerHand.size() + 1) {
                System.out.println("This player has picked up a card.");
                Card passCard = deck.playerTurnPass();
                players[currentPosition].playerHand.add(passCard);
            }
        }

        if (currentAttribute.equals("Crustal Abundance")) {

            for (i = 0; i < players[currentPosition].playerHand.size() + 1; i++) {
                if (i < players[currentPosition].playerHand.size()) {
                    chosenCard = players[currentPosition].playerHand.get(i);

                    if (chosenCard.getClass() == MineralCard.class) {

                        attributeCrustalAbundance = chosenCard.cardCrystalAbundance;
                        if (compareCardsCrustalAbundance()) {
                            players[currentPosition].playerHand.remove(chosenCard);
                            deck.playedCards.add(chosenCard);
                            System.out.println(chosenCard);
                            playedCardCrustalAbundance = chosenCard.cardCrystalAbundance;
                            i = players[currentPosition].playerHand.size() + 2;
                        }

                    }
                }
            }

            if (i == players[currentPosition].playerHand.size() + 1) {

                for (i = 0; i < players[currentPosition].playerHand.size() + 1; i++) {
                    if (i < players[currentPosition].playerHand.size()) {
                        chosenCard = players[currentPosition].playerHand.get(i);
                        if (chosenCard.getClass() == TrumpCard.class) {

                            trumpCardCategoryChange(chosenCard);
                            players[currentPosition].playerHand.remove(chosenCard);
                            deck.playedCards.add(chosenCard);
                            players[currentPosition].playerHand.remove(chosenCard);
                            deck.playedCards.add(chosenCard);
                            playedCardCrustalAbundance = "null";
                            i = players[currentPosition].playerHand.size() + 2;
                        }

                    }
                }
            }

            if (i == players[currentPosition].playerHand.size() + 1) {
                System.out.println("This player has picked up a card.");
                Card passCard = deck.playerTurnPass();
                players[currentPosition].playerHand.add(passCard);
            }
        }

        if (currentAttribute.equals("Economic Value")) {

            for (i = 0; i < players[currentPosition].playerHand.size() + 1; i++) {
                if (i < players[currentPosition].playerHand.size()) {
                    chosenCard = players[currentPosition].playerHand.get(i);

                    if (chosenCard.getClass() == MineralCard.class) {

                        attributeEconomicValue = chosenCard.cardEconomicValue;
                        if (compareCardsEconomicValue()) {
                            players[currentPosition].playerHand.remove(chosenCard);
                            deck.playedCards.add(chosenCard);
                            System.out.println(chosenCard);
                            playedCardEconomicValue = chosenCard.cardEconomicValue;
                            i = players[currentPosition].playerHand.size() + 2;
                        }

                    }
                }
            }

            if (i == players[currentPosition].playerHand.size() + 1) {

                for (i = 0; i < players[currentPosition].playerHand.size() + 1; i++) {
                    if (i < players[currentPosition].playerHand.size()) {
                        chosenCard = players[currentPosition].playerHand.get(i);
                        if (chosenCard.getClass() == TrumpCard.class) {

                            trumpCardCategoryChange(chosenCard);
                            players[currentPosition].playerHand.remove(chosenCard);
                            deck.playedCards.add(chosenCard);
                            players[currentPosition].playerHand.remove(chosenCard);
                            deck.playedCards.add(chosenCard);
                            playedCardEconomicValue = "null";
                            i = players[currentPosition].playerHand.size() + 2;
                        }

                    }
                }
            }

            if (i == players[currentPosition].playerHand.size() + 1) {
                System.out.println("This player has picked up a card.");
                Card passCard = deck.playerTurnPass();
                players[currentPosition].playerHand.add(passCard);
            }
        }
    }

    private void trumpCardCategoryChange(Card chosenCard) {
        if (chosenCard.cardTitle.equals("The miner")) {
            currentAttribute = "Economic Value";
            System.out.println("The miner card has changed the category to Economic Value.");

        } else if (chosenCard.cardTitle.equals("The Petrologist")) {
            currentAttribute = "Crustal Abundance";
            System.out.println("The Petrologist card has changed the category to Crustal Abundance.");

        } else if (chosenCard.cardTitle.equals("The Gemmologist")) {
            currentAttribute = "Hardness";
            System.out.println("The Gemmologist card has changed the category to Hardness.");

        } else if (chosenCard.cardTitle.equals("The Mineralogist")) {
            currentAttribute = "Cleavage";
            System.out.println("The Mineralogist card has changed the category to Cleavage.");

        } else if (chosenCard.cardTitle.equals("The Geophysicist")) {
            currentAttribute = "Specific Gravity";
            System.out.println("The Geophysicist card has changed the category to Specific Gravity.");

        } else {
            Random rand = new Random();
            int attributeChoice = rand.nextInt((5 - 1) + 1) + 1;
            switch (attributeChoice) {
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

    public void trumpCardCategoryChangePlayer(Card chosenCard){

        if (chosenCard.getClass() == TrumpCard.class) {
            if (chosenCard.cardTitle.equals("The miner")) {
                currentAttribute = "Economic Value";
                System.out.println("The miner card has changed the category to Economic Value.");

            } else if (chosenCard.cardTitle.equals("The Petrologist")) {
                currentAttribute = "Crustal Abundance";
                System.out.println("The Petrologist card has changed the category to Crustal Abundance.");

            } else if (chosenCard.cardTitle.equals("The Gemmologist")) {
                currentAttribute = "Hardness";
                System.out.println("The Gemmologist card has changed the category to Hardness.");

            } else if (chosenCard.cardTitle.equals("The Mineralogist")) {
                currentAttribute = "Cleavage";
                System.out.println("The Mineralogist card has changed the category to Cleavage.");

            } else if (chosenCard.cardTitle.equals("The Geophysicist")) {
                currentAttribute = "Specific Gravity";
                System.out.println("The Geophysicist card has changed the category to Specific Gravity.");

            } else {
                Scanner playerChoice = new Scanner(System.in);
                System.out.println("Which category would you like to play by?");
                System.out.println("1. Hardness" + "\n" + "2. Specific Gravity" + "\n" + "3. Cleavage" + "\n" + "4. Crystal Abundance" + "\n" + "5. Economic Value");
                int attributeChoice = playerChoice.nextInt();
                switch (attributeChoice) {
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
    }

    public boolean gameOver(int numberPlayers) {

        for (int i = 0; i < numberPlayers; i++) {
            if (players[i].playerHand.size() < 1){
                System.out.print("Player " + (i + 1) + " has won the game!");
                gameIsover = true;
            }
            else gameIsover = false;


            if (!deck.checkEmptyDeck()) {
                gameIsover = true;
            }
        }
        return gameIsover;
    }
}


