import javax.swing.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

/**
 * Created by Darcie on 3/09/2016.
 * Methods that belong to the game.
 */

class SuperTrumpGame {
    public static SuperTrumpGame currentGame;
    public  int numberPlayers;
    int dealerNumber;
    Player[] players;
    public STDeck deck;
   // public GUI gui = new GUI();
    public String currentAttribute = "Hardness";
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
    public int passCounter;
    public ImageIcon cardimageplayed;
    public int firstTurn = 1;

    SuperTrumpGame(int numberPlayers) throws IOException {
        this.numberPlayers = numberPlayers;
        this.deck = new STDeck();
        currentGame = this;

    }

    //Randomly selects a player to be dealer.
    public int selectDealer(int NumberOfPlayers){
        int min = 1;
        Random generator = new Random();
        dealerNumber = generator.nextInt(NumberOfPlayers - min + 1) + min;
        System.out.println("The dealer has been chosen.");
        return dealerNumber;
    }

    //Assigns each player 8 cards from the shuffled deck.
    public void dealHand(int numberPlayers) {

        players = new Player[numberPlayers];
        ArrayList<Card> playerHand;

        for (int i = 0; i < numberPlayers; i++){
            Player player = new Player();
            players[i] = player;
            playerHand = deck.dealCards();
            player.setPlayerHand(playerHand);
            System.out.println("player " + i + " " + playerHand);
        }
        players[0].isHuman = true;
    }

    public int getBotNumberOfCards(int currentPlayer) {
        int numberOfBotCards;
        numberOfBotCards = players[currentPlayer].playerHand.size();
        return numberOfBotCards;
    }

    //Lets the player choose the first card to play and a category.
    public ImageIcon firstPlayerTurn(Card card){

        if (!deck.playedCards.isEmpty()){
            deck.checkEmptyDeck();
            deck.playedCards.clear();
        }

        int CardChoice = 0;

        for (int i = 0; i < players[0].playerHand.size(); i ++){
            if (card.cardTitle.equals(players[0].playerHand.get(i).cardTitle)){
                CardChoice = i;
            }
        }

        Card chosenCard = players[0].playerHand.get(CardChoice);
        System.out.println("Player 1 has played card: ");
        System.out.println(players[0].playerHand.get(CardChoice));

        //amend decks to played cards
        deck.playedCards.add(players[0].playerHand.get(CardChoice));
        //Card cardChoice = players[0].playerHand.get(CardChoice);
        players[0].playerHand.remove(CardChoice);


       if(chosenCard.getClass() == TrumpCard.class) {
           if (chosenCard.cardTitle.equals("The miner")) {
               currentAttribute = "Economic Value";
               System.out.println("The miner card has changed the category to Economic Value.");
               chosenCard.cardEconomicValue = "null";
               playedCardEconomicValue = "null";
               players[0].playerHand.remove(chosenCard);
           } else if (chosenCard.cardTitle.equals("The Petrologist")) {
               currentAttribute = "Crustal Abundance";
               System.out.println("The Petrologist card has changed the category to Crustal Abundance.");
               chosenCard.cardCrustalAbundance = "null";
               playedCardCrustalAbundance = "null";
               players[0].playerHand.remove(chosenCard);
           } else if (chosenCard.cardTitle.equals("The Gemmologist")) {
               currentAttribute = "Hardness";
               System.out.println("The Gemmologist card has changed the category to Hardness.");
               chosenCard.cardHardness = 0.0;
               playedCardHardness = 0.0;
               players[0].playerHand.remove(chosenCard);
           } else if (chosenCard.cardTitle.equals("The Mineralogist")) {
               currentAttribute = "Cleavage";
               System.out.println("The Mineralogist card has changed the category to Cleavage.");
               chosenCard.cardCleavage = "null";
               playedCardCleavage = "null";
               players[0].playerHand.remove(chosenCard);
           } else if (chosenCard.cardTitle.equals("The Geophysicist")) {
               currentAttribute = "Specific Gravity";
               System.out.println("The Geophysicist card has changed the category to Specific Gravity.");
               chosenCard.cardSpecificGravity = 0.0;
               playedCardSpecificGravity = 0.0;
               players[0].playerHand.remove(chosenCard);
           }
       }

       if (chosenCard.getClass() == MineralCard.class){

           chosenCard.cardSpecificGravity = 0.0;
           playedCardSpecificGravity = 0.0;

           chosenCard.cardHardness = 0.0;
           playedCardHardness = 0.0;

           chosenCard.cardCrustalAbundance = "null";
           playedCardCrustalAbundance= "null";

           chosenCard.cardCleavage = "null";
           playedCardCleavage = "null";

           chosenCard.cardEconomicValue = "null";
           playedCardEconomicValue = "null";



       }
                //    getPlayedCardAttributeValue(chosenCard);
              //  }
                 /**   System.out.println("The Geologist card lets you choose a playing category. Which category would you like to play by?");
                    System.out.println("1. Hardness" + "\n" + "2. Specific Gravity" + "\n" + "3. Cleavage" + "\n" + "4. Crustal Abundance" + "\n" + "5. Economic Value");
                    int attributeChoice = 1;
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
                            System.out.println("The chosen cards specific gravity is " + attributeSpecificGravity);
                            playedCardSpecificGravity = chosenCard.cardSpecificGravity;
                            break;
                        case 3:
                            currentAttribute = "Cleavage";
                            attributeCleavage = chosenCard.cardCleavage;
                            System.out.println("The Geologist has changed the category to Cleavage.");
                            System.out.println("The chosen cards cleavage is " + attributeCleavage);
                            playedCardCleavage = chosenCard.cardCleavage;
                            break;
                        case 4:
                            currentAttribute = "Crustal Abundance";
                            attributeCrustalAbundance = chosenCard.cardCrustalAbundance;
                            System.out.println("The Geologist has changed the category to Crustal Abundance.");
                            System.out.println("The chosen cards crustal abundance is " + attributeCrustalAbundance);
                            playedCardCrustalAbundance = chosenCard.cardCrustalAbundance;
                            break;
                        case 5:
                            currentAttribute = "Economic Value";
                            attributeEconomicValue = chosenCard.cardEconomicValue;
                            System.out.println("The Geologist has changed the category to Economic Value.");
                            System.out.println("The chosen cards economic value is " + attributeEconomicValue);
                            playedCardEconomicValue = chosenCard.cardEconomicValue;
                            break; */

              //      }
      //  }


       // else{
       //         getPlayedCardAttributeValue(chosenCard);
       //     }
                        /**
            System.out.println("Which category would you like to play by?");
            System.out.println("1. Hardness" + "\n" + "2. Specific Gravity" + "\n" + "3. Cleavage" + "\n" + "4. Crustal Abundance" + "\n" + "5. Economic Value");
            int attributeChoice = 1;
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
                    System.out.println("The chosen cards specific gravity is " + attributeSpecificGravity);
                    playedCardSpecificGravity = chosenCard.cardSpecificGravity;
                    break;
                case 3:
                    currentAttribute = "Cleavage";
                    attributeCleavage = chosenCard.cardCleavage;
                    System.out.println("You have chosen to play by the Cleavage category.");
                    System.out.println("The chosen cards cleavage is " + attributeCleavage);
                    playedCardCleavage = chosenCard.cardCleavage;
                    break;
                case 4:
                    currentAttribute = "Crustal Abundance";
                    attributeCrustalAbundance = chosenCard.cardCrustalAbundance;
                    System.out.println("You have chosen to play by the Crustal Abundance category.");
                    System.out.println("The chosen cards crustal abundance is " + attributeCrustalAbundance);
                    playedCardCrustalAbundance = chosenCard.cardCrustalAbundance;
                    break;
                case 5:
                    currentAttribute = "Economic Value";
                    attributeEconomicValue = chosenCard.cardEconomicValue;
                    System.out.println("You have chosen to play by the Economic Value category.");
                    System.out.println("The chosen cards economic value is " + attributeEconomicValue);
                    playedCardEconomicValue = chosenCard.cardEconomicValue;
                    break;
            }
            getPlayedCardAttributeValue();
            passCounter = 0;
        }
        return currentAttribute; */

        gameOver(currentGame.numberPlayers);

        cardimageplayed = getPlayedCardImage();
        return cardimageplayed;

    }

    public ImageIcon getPlayedCardImage(){
        Card card = deck.playedCards.get(deck.playedCards.size() - 1);
        return card.getCardImage();

    }


    public void getAttribute(String attribute){
        currentAttribute = attribute;
        //getPlayedCardAttributeValue();
        System.out.println("Card value is " + playedCardCleavage + playedCardHardness + playedCardCrustalAbundance + playedCardEconomicValue + playedCardSpecificGravity);
        System.out.println("The current attribute is " + currentAttribute);

    }

    //Gets the values of the previously played category.
    private void getPlayedCardAttributeValue(Card cardplayed) {


        int cardCount = deck.playedCards.size() - 1;
        Card cardPlayed = deck.playedCards.get(cardCount);

        if (cardPlayed.getClass() == TrumpCard.class) {
            if (cardPlayed.cardTitle.equals("The miner")) {
                currentAttribute = "Economic Value";
                playedCardEconomicValue = "low";

            } else if (cardPlayed.cardTitle.equals("The Petrologist")) {
                currentAttribute = "Crustal Abundance";
                playedCardCrustalAbundance = "low";

            } else if (cardPlayed.cardTitle.equals("The Gemmologist")) {
                currentAttribute = "Hardness";
                playedCardHardness = 0.0;

            } else if (cardPlayed.cardTitle.equals("The Mineralogist")) {
                currentAttribute = "Cleavage";
                playedCardCleavage = "poor";

            } else if (cardPlayed.cardTitle.equals("The Geophysicist")) {
                currentAttribute = "Specific Gravity";
                playedCardSpecificGravity = 0.0;

            } else {
                playedCardEconomicValue = "low";
                playedCardHardness = 0.0;
                playedCardSpecificGravity = 0.0;
                playedCardCrustalAbundance = "low";
                playedCardCleavage = "poor";
            }
        }
        else if (cardPlayed.getClass() == MineralCard.class) {

            if (currentGame.currentAttribute.equals("Hardness")) {
                playedCardHardness = cardPlayed.cardHardness;
            } else if (currentGame.currentAttribute.equals("Specific Gravity")) {
                playedCardSpecificGravity = cardPlayed.cardSpecificGravity;
            } else if (currentGame.currentAttribute.equals("Cleavage")) {
                playedCardCleavage = cardPlayed.cardCleavage;
            } else if (currentGame.currentAttribute.equals("Crustal Abundance")) {
                playedCardCrustalAbundance = cardPlayed.cardCrustalAbundance;
            } else if (currentGame.currentAttribute.equals("Economic Value")){
                playedCardEconomicValue = cardPlayed.cardEconomicValue;
            }
        }
    }

    //Compares the played card to the card that the player is trying to play based on Specific Gravity.
    private boolean compareCardsSpecificGravity(){
        boolean canPlayCard;
        canPlayCard = this.attributeSpecificGravity > this.playedCardSpecificGravity;
        return canPlayCard;
    }

    //Compares the played card to the card that the player is trying to play based on Economic Value
    private boolean compareCardsEconomicValue() {
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

        }
        canPlayCard = attributeCompare > playedAttributeCompare;
        return canPlayCard;

    }

    //Compares the played card to the card that the player is trying to play based on Crustal Abundance.
    private boolean compareCardsCrustalAbundance() {
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

        }
        canPlayCard = attributeCompare > playedAttributeCompare;
        return canPlayCard;

    }

    //Compares the played card to the card that the player is trying to play based on Cleavage.
    private boolean compareCardsCleavage(){
        int attributeCompare = 0;
        boolean canPlayCard;
        int playerAttributeCompare = 0;
        switch (attributeCleavage){
            case "6 perfect":
                attributeCompare = 15;
                break;
            case "4 perfect":
                attributeCompare = 14;
                break;
            case "3 perfect":
                attributeCompare = 13;
                break;
            case "2 perfect, 1 good":
                attributeCompare = 12;
                break;
            case "1 perfect, 2 good":
                attributeCompare = 11;
                break;
            case "1 perfect, 1 good":
                attributeCompare = 10;
                break;
            case "1 perfect":
                attributeCompare = 9;
                break;
            case "3 good":
                attributeCompare = 8;
                break;
            case "2 good":
                attributeCompare = 7;
                break;
            case "1 good, 1 poor":
                attributeCompare = 6;
                break;
            case "1 good":
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
            case "4 perfect":
                playerAttributeCompare = 14;
                break;
            case "3 perfect":
                playerAttributeCompare = 13;
                break;
            case "2 perfect, 1 good":
                playerAttributeCompare = 12;
                break;
            case "1 perfect, 2 good":
                playerAttributeCompare = 11;
                break;
            case "1 perfect, 1 good":
                playerAttributeCompare = 10;
                break;
            case "1 perfect":
                playerAttributeCompare = 9;
                break;
            case "3 good":
                playerAttributeCompare = 8;
                break;
            case "2 good":
                playerAttributeCompare = 7;
                break;
            case "1 good, 1 poor":
                playerAttributeCompare = 6;
                break;
            case "1 good":
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

        canPlayCard = attributeCompare > playerAttributeCompare;
        return canPlayCard;

    }

    //Compares the played card to the card that the player is trying to play based on Hardness.
    private boolean compareCardsHardness(){
        boolean canPlayCard;

        canPlayCard = this.attributeHardness > this.playedCardHardness;
        return canPlayCard;
    }

    //Controls the players turn, letting them play a trump or mineral card and checking for validity.
    public String playerTurn() {
        boolean x = false;
        players[0].hasPassed = false;
        checkHandPass(0);
      //  getPlayedCardAttributeValue();

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
                attributeCrustalAbundance = chosenCard.cardCrustalAbundance;
                attributeEconomicValue = chosenCard.cardEconomicValue;

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
                            System.out.println("This card has a Hardness of " + attributeHardness + ".");
                            System.out.println("Please play a card that is a higher hardness than " + playedCardHardness + " or type 'pass' to pickup a card.");
                            CardChoice = (playerChoice.nextLine());

                            try {
                                cardChoice = Integer.parseInt(CardChoice);
                            } catch (NumberFormatException e) {
                                if ("pass".equals(CardChoice)) {
                                    //Pick up card if the user types pass.
                                    System.out.println("You have picked up ");
                                    Card passCard = deck.playerTurnPass();
                                    players[0].playerHand.add(passCard);
                                    System.out.println(passCard);
                                    players[0].hasPassed = true;
                                    x = true;
                                    passCounter = passCounter + 1;
                                }
                            }

                            chosenCard = players[0].playerHand.get(cardChoice);
                            if (chosenCard.getClass() == TrumpCard.class) {
                                trumpCardCategoryChangePlayer(chosenCard);
                                x = true;
                                players[0].playerHand.remove(chosenCard);
                            }
                            attributeHardness = chosenCard.cardHardness;
                            compareCardsHardness();
                        }

                    }

                    //crustal abundance
                    if (currentAttribute.equals("Crustal Abundance")) {
                        compareCardsCrustalAbundance();

                        if (compareCardsCrustalAbundance()) {
                            deck.playedCards.add(players[0].playerHand.get(cardChoice));
                            System.out.println("Player 1 has played card: " + players[0].playerHand.get(cardChoice));
                            players[0].playerHand.remove(cardChoice);
                        }

                        while (!compareCardsCrustalAbundance() && !x) {
                            System.out.println("This card has a Crustal Abundance of " + attributeCrustalAbundance + ".");
                            System.out.println("Please play a card that is a higher crustal abundance than " + playedCardCrustalAbundance + " or type 'pass' to pickup a card.");

                            CardChoice = (playerChoice.nextLine());

                            try {
                                cardChoice = Integer.parseInt(CardChoice);
                            } catch (NumberFormatException e) {
                                if ("pass".equals(CardChoice)) {
                                    //Pick up card if the user types pass.
                                    System.out.println("You have picked up ");
                                    Card passCard = deck.playerTurnPass();
                                    players[0].playerHand.add(passCard);
                                    System.out.println(passCard);
                                    players[0].hasPassed = true;
                                    x = true;
                                    passCounter = passCounter + 1;
                                }
                            }

                            chosenCard = players[0].playerHand.get(cardChoice);

                            if (chosenCard.getClass() == TrumpCard.class) {
                                trumpCardCategoryChangePlayer(chosenCard);
                                players[0].playerHand.remove(chosenCard);
                                x = true;
                            }
                            attributeCrustalAbundance = chosenCard.cardCrustalAbundance;
                            compareCardsCrustalAbundance();
                        }

                    }

                    //economic value
                    if (currentAttribute.equals("Economic Value")) {
                        compareCardsEconomicValue();

                        if (compareCardsEconomicValue()) {
                            deck.playedCards.add(players[0].playerHand.get(cardChoice));
                            System.out.println("Player 1 has played card: " + players[0].playerHand.get(cardChoice));
                            players[0].playerHand.remove(cardChoice);
                        }

                        while (!compareCardsEconomicValue() && !x) {
                            System.out.println("This card has a Economic Value of " + attributeEconomicValue + ".");
                            System.out.println("Please play a card that is a higher economic value than " + playedCardEconomicValue + " or type 'pass' to pickup a card.");

                            CardChoice = (playerChoice.nextLine());

                            try {
                                cardChoice = Integer.parseInt(CardChoice);
                            } catch (NumberFormatException e) {
                                if ("pass".equals(CardChoice)) {
                                    //Pick up card if the user types pass.
                                    System.out.println("You have picked up ");
                                    Card passCard = deck.playerTurnPass();
                                    players[0].playerHand.add(passCard);
                                    System.out.println(passCard);
                                    players[0].hasPassed = true;
                                    x = true;
                                    passCounter = passCounter + 1;
                                }
                            }

                            chosenCard = players[0].playerHand.get(cardChoice);

                            if (chosenCard.getClass() == TrumpCard.class) {
                                trumpCardCategoryChangePlayer(chosenCard);
                                players[0].playerHand.remove(chosenCard);
                                x = true;
                            }
                            attributeEconomicValue = chosenCard.cardEconomicValue;
                            compareCardsEconomicValue();
                        }

                    }
                    // specific gravity
                    if (currentAttribute.equals("Specific Gravity")) {
                        compareCardsSpecificGravity();

                        if (compareCardsSpecificGravity()) {
                            deck.playedCards.add(players[0].playerHand.get(cardChoice));
                            System.out.println("Player 1 has played card: " + players[0].playerHand.get(cardChoice));
                            players[0].playerHand.remove(cardChoice);
                        }

                        while (!compareCardsSpecificGravity() && !x) {
                            System.out.println("This card has a Specific Gravity of " + attributeSpecificGravity + ".");
                            System.out.println("Please play a card that is a higher specific gravity than " + playedCardSpecificGravity + " or type 'pass' to pickup a card.");

                            //System.out.println("Please enter the number of the card you would like to play:");
                            CardChoice = (playerChoice.nextLine());

                            try {
                                cardChoice = Integer.parseInt(CardChoice);
                            } catch (NumberFormatException e) {
                                if ("pass".equals(CardChoice)) {
                                    //Pick up card if the user types pass.
                                    System.out.println("You have picked up ");
                                    Card passCard = deck.playerTurnPass();
                                    players[0].playerHand.add(passCard);
                                    System.out.println(passCard);
                                    x = true;
                                    passCounter = passCounter + 1;
                                }
                            }

                            chosenCard = players[0].playerHand.get(cardChoice);
                            if (chosenCard.getClass() == TrumpCard.class) {
                                trumpCardCategoryChangePlayer(chosenCard);
                                players[0].playerHand.remove(chosenCard);
                                x = true;
                            }
                            attributeSpecificGravity = chosenCard.cardSpecificGravity;
                            compareCardsSpecificGravity();
                        }

                    }

                    //cleavage
                    if (currentAttribute.equals("Cleavage")) {
                        compareCardsCleavage();

                        if (compareCardsCleavage()) {
                            deck.playedCards.add(players[0].playerHand.get(cardChoice));
                            System.out.println("Player 1 has played card: " + players[0].playerHand.get(cardChoice));
                            players[0].playerHand.remove(cardChoice);
                        }

                        while (!compareCardsCleavage() && !x) {
                            System.out.println("This card has a cleavage of " + attributeSpecificGravity + ".");
                            System.out.println("Please play a card that is a higher cleavage than " + playedCardCleavage + " or type 'pass' to pickup a card.");

                            //System.out.println("Please enter the number of the card you would like to play:");
                            CardChoice = (playerChoice.nextLine());

                            try {
                                cardChoice = Integer.parseInt(CardChoice);
                            } catch (NumberFormatException e) {
                                if ("pass".equals(CardChoice)) {
                                    //Pick up card if the user types pass.
                                    System.out.println("You have picked up ");
                                    Card passCard = deck.playerTurnPass();
                                    players[0].playerHand.add(passCard);
                                    System.out.println(passCard);
                                    players[0].hasPassed = true;
                                    passCounter = passCounter + 1;
                                    x = true;
                                }
                            }

                            chosenCard = players[0].playerHand.get(cardChoice);
                            if (chosenCard.getClass() == TrumpCard.class) {
                                trumpCardCategoryChangePlayer(chosenCard);
                                players[0].playerHand.remove(chosenCard);
                                x = true;
                            }
                            attributeCleavage = chosenCard.cardCleavage;
                            compareCardsCleavage();
                        }

                    }
                }

                    if (players[0].wonHand) {
                        System.out.println("Which category would you like to play by?");
                        System.out.println("1. Hardness" + "\n" + "2. Specific Gravity" + "\n" + "3. Cleavage" + "\n" + "4. Crustal Abundance" + "\n" + "5. Economic Value");
                        int attributeChoice = playerChoice.nextInt();
                        switch (attributeChoice){
                            case 1:
                                currentAttribute = "Hardness";
                                attributeHardness = chosenCard.cardHardness;
                                System.out.println("You have changed the category to Hardness.");
                                System.out.println("The chosen cards hardness is " + attributeHardness);
                                playedCardHardness = chosenCard.cardHardness;
                                break;
                            case 2:
                                currentAttribute = "Specific Gravity";
                                attributeSpecificGravity = chosenCard.cardSpecificGravity;
                                System.out.println("You have changed the category to Specific Gravity.");
                                System.out.println("The chosen cards specific gravity is " + attributeSpecificGravity);
                                playedCardSpecificGravity = chosenCard.cardSpecificGravity;
                                break;
                            case 3:
                                currentAttribute = "Cleavage";
                                attributeCleavage = chosenCard.cardCleavage;
                                System.out.println("You have changed the category to Cleavage.");
                                System.out.println("The chosen cards cleavage is " + attributeCleavage);
                                playedCardCleavage = chosenCard.cardCleavage;
                                break;
                            case 4:
                                currentAttribute = "Crustal Abundance";
                                attributeCrustalAbundance = chosenCard.cardCrustalAbundance;
                                System.out.println("You have changed the category to Crustal Abundance.");
                                System.out.println("The chosen cards crustal abundance is " + attributeCrustalAbundance);
                                playedCardCrustalAbundance = chosenCard.cardCrustalAbundance;
                                break;
                            case 5:
                                currentAttribute = "Economic Value";
                                attributeEconomicValue = chosenCard.cardEconomicValue;
                                System.out.println("You have changed the category to Economic Value.");
                                System.out.println("The chosen cards economic value is " + attributeEconomicValue);
                                playedCardEconomicValue = chosenCard.cardEconomicValue;
                                break;
                        }
                       passCounter = 0;
                    }


            } catch (NumberFormatException e) {
                if ("pass".equals(CardChoice)) {
                    //Pick up card if the user types pass.
                    System.out.println("You have picked up ");
                    Card passCard = deck.playerTurnPass();
                    players[0].playerHand.add(passCard);
                    System.out.println(passCard);
                    players[0].hasPassed = true;
                    x = true;
                    passCounter = passCounter + 1;
                }
            }
        players[0].wonHand = false;
        gameOver(numberPlayers);
        return currentAttribute;
    }

    //Plays card number three (cards have already been randomised)for the bot and chooses initial playing category.
    public String firstBotTurn(int firstPlayer) {

        int cardsPlayed = 0;

            int cardChoice = players[firstPlayer].playerHand.size();

            Random random = new Random();
            cardChoice = random.nextInt((cardChoice - 1) + 1);

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
                cardsPlayed = 1;
            } else {
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
                        attributeCrustalAbundance = chosenCard.cardCrustalAbundance;
                        System.out.println("Bot has chosen to play by the Crustal Abundance category.");

                        break;
                    case 5:
                        currentAttribute = "Economic Value";
                        attributeEconomicValue = chosenCard.cardEconomicValue;
                        System.out.println("Bot has chosen to play by the Economic Value category.");

                        break;
                }
            }

            //getPlayedCardAttributeValue();

            if (!players[0].hasPassed){
                passCounter = 0;
            }

            return currentAttribute;
        }

    //Prints the player hand and the number the player has to enter to access that card.
    public void printHand(){
        System.out.println("Here is your hand:");
        for (int i = 0; i < players[0].playerHand.size(); i++) {
            System.out.println((i) + ". " + players[0].playerHand.get(i));
        }
    }

    //Determines where the play starts. (Left of the dealer)
    public int determineFirstPlayer(int dealerNumber, int numberPlayers){
        int startingPlayer = dealerNumber + 1;

        if (startingPlayer >= numberPlayers){
            startingPlayer = 1;
        }

        else { return startingPlayer; }

        System.out.println("The first player is " + startingPlayer);
        return startingPlayer;
    }

    //Looks for a card in the hand that is higher in the current category, if none found it looks to play trump.
    public Card botTurn(int currentPosition) {
       // getPlayedCardAttributeValue();
        checkHandPass(currentPosition);
        int playedTrump = 0;
        int i;
        Card chosenCard = null;
        players[currentPosition].hasPassed = false;

            if (SuperTrumpGame.currentGame.currentAttribute.equals("Hardness") && playedTrump == 0) {

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
                                trumpCardCategoryChange(chosenCard, currentPosition);
                                players[currentPosition].playerHand.remove(chosenCard);
                                deck.playedCards.add(chosenCard);
                                players[currentPosition].playerHand.remove(chosenCard);
                                deck.playedCards.add(chosenCard);
                                playedCardHardness = 0.0;
                                i = players[currentPosition].playerHand.size() + 2;
                                playedTrump = 1;
                            }

                        }
                    }
                }
                    if (i == players[currentPosition].playerHand.size() + 1) {
                        System.out.println("This player has picked up a card." + "\n");
                        Card passCard = deck.playerTurnPass();
                        players[currentPosition].playerHand.add(passCard);
                        players[currentPosition].hasPassed = true;
                        passCounter = passCounter + 1;
                    }

            }

            if (SuperTrumpGame.currentGame.currentAttribute.equals("Cleavage") && playedTrump == 0) {

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

                                trumpCardCategoryChange(chosenCard, currentPosition);
                                players[currentPosition].playerHand.remove(chosenCard);
                                deck.playedCards.add(chosenCard);
                                players[currentPosition].playerHand.remove(chosenCard);
                                deck.playedCards.add(chosenCard);
                                playedCardCleavage = "ultratrace";
                                i = players[currentPosition].playerHand.size() + 2;
                                playedTrump = 1;
                            }

                        }
                    }
                }

                    if (i == players[currentPosition].playerHand.size() + 1) {
                        System.out.println("This player has picked up a card." + "\n");
                        Card passCard = deck.playerTurnPass();
                        players[currentPosition].playerHand.add(passCard);
                        players[currentPosition].hasPassed = true;
                        passCounter = passCounter + 1;
                    }

            }

            if (SuperTrumpGame.currentGame.currentAttribute.equals("Specific Gravity") && playedTrump == 0) {

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
                                trumpCardCategoryChange(chosenCard, currentPosition);
                                players[currentPosition].playerHand.remove(chosenCard);
                                deck.playedCards.add(chosenCard);
                                players[currentPosition].playerHand.remove(chosenCard);
                                deck.playedCards.add(chosenCard);
                                playedCardSpecificGravity = 0.0;
                                i = players[currentPosition].playerHand.size() + 2;
                                playedTrump = 1;
                            }

                        }
                    }
                }

                    if (i == players[currentPosition].playerHand.size() + 1) {
                        System.out.println("This player has picked up a card." + "\n");
                        Card passCard = deck.playerTurnPass();
                        players[currentPosition].playerHand.add(passCard);
                        players[currentPosition].hasPassed = true;
                        passCounter = passCounter + 1;
                    }

            }

            if (SuperTrumpGame.currentGame.currentAttribute.equals("Crustal Abundance") && playedTrump == 0) {

                for (i = 0; i < players[currentPosition].playerHand.size() + 1; i++) {
                    if (i < players[currentPosition].playerHand.size()) {
                        chosenCard = players[currentPosition].playerHand.get(i);

                        if (chosenCard.getClass() == MineralCard.class) {
                            attributeCrustalAbundance = chosenCard.cardCrustalAbundance;
                            if (compareCardsCrustalAbundance()) {
                                players[currentPosition].playerHand.remove(chosenCard);
                                deck.playedCards.add(chosenCard);
                                System.out.println(chosenCard);
                                playedCardCrustalAbundance = chosenCard.cardCrustalAbundance;
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
                                trumpCardCategoryChange(chosenCard, currentPosition);
                                players[currentPosition].playerHand.remove(chosenCard);
                                deck.playedCards.add(chosenCard);
                                players[currentPosition].playerHand.remove(chosenCard);
                                deck.playedCards.add(chosenCard);
                                playedCardCrustalAbundance = "none";
                                i = players[currentPosition].playerHand.size() + 2;
                                playedTrump = 1;
                            }

                        }
                    }
                }
                    if (i == players[currentPosition].playerHand.size() + 1) {
                        System.out.println("This player has picked up a card." + "\n");
                        Card passCard = deck.playerTurnPass();
                        players[currentPosition].playerHand.add(passCard);
                        players[currentPosition].hasPassed = true;
                        passCounter = passCounter + 1;
                    }
            }

            if (SuperTrumpGame.currentGame.currentAttribute.equals("Economic Value") && playedTrump == 0) {

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
                                trumpCardCategoryChange(chosenCard, currentPosition);
                                players[currentPosition].playerHand.remove(chosenCard);
                                deck.playedCards.add(chosenCard);
                                players[currentPosition].playerHand.remove(chosenCard);
                                deck.playedCards.add(chosenCard);
                                playedCardEconomicValue = "low";
                                i = players[currentPosition].playerHand.size() + 2;
                                playedTrump = 1;
                            }

                        }
                    }
                }

                    if (i == players[currentPosition].playerHand.size() + 1) {
                        System.out.println("This player has picked up a card." + "\n");
                        Card passCard = deck.playerTurnPass();
                        players[currentPosition].playerHand.add(passCard);
                        players[currentPosition].hasPassed = true;
                        passCounter = passCounter + 1;
                    }

            }

            gameOver(currentGame.numberPlayers);
        return chosenCard;
    }

    //Changes the current category when a bot plays a turn.
    private void trumpCardCategoryChange(Card chosenCard, int currentPlayer) {
        if (chosenCard.cardTitle.equals("The Miner")) {
            currentAttribute = "Economic Value";
            playedCardEconomicValue = "low";
            System.out.println("The miner card has changed the category to Economic Value.");

        } else if (chosenCard.cardTitle.equals("The Petrologist")) {
            currentAttribute = "Crustal Abundance";
            playedCardCrustalAbundance = "low";
            System.out.println("The Petrologist card has changed the category to Crustal Abundance.");

        } else if (chosenCard.cardTitle.equals("The Gemmologist")) {
            currentAttribute = "Hardness";
            playedCardHardness = 0.0;
            System.out.println("The Gemmologist card has changed the category to Hardness.");

        } else if (chosenCard.cardTitle.equals("The Mineralogist")) {
            currentAttribute = "Cleavage";
            playedCardCleavage = "none";
            System.out.println("The Mineralogist card has changed the category to Cleavage.");

        } else if (chosenCard.cardTitle.equals("The Geophysicist")) {
            for (int i = 0; i < players[currentPlayer].playerHand.size(); i++) {
                Card Mag = players[currentPlayer].playerHand.get(i);
                if (Mag.cardTitle.equals("Magnetite")){
                    deck.playedCards.add(Mag);
                    players[currentPlayer].playerHand.remove(Mag);
                    System.out.println("Player " + currentPlayer + " has won the game!");
                    System.exit(0);
                }
            }
            currentAttribute = "Specific Gravity";
            playedCardSpecificGravity = 0.0;
            System.out.println("The Geophysicist card has changed the category to Specific Gravity.");

        } else {
            Random rand = new Random();
            int attributeChoice = rand.nextInt((5 - 1) + 1) + 1;
            switch (attributeChoice) {
                case 1:
                    currentAttribute = "Hardness";
                    playedCardHardness = 0.0;
                    break;
                case 2:
                    currentAttribute = "Specific Gravity";
                    playedCardSpecificGravity = 0.0;
                    break;
                case 3:
                    currentAttribute = "Cleavage";
                    playedCardCleavage = "none";
                    break;
                case 4:
                    currentAttribute = "Crustal Abundance";
                    playedCardCrustalAbundance = "ultratrace";
                    break;
                case 5:
                    currentAttribute = "Economic Value";
                    playedCardEconomicValue = "low";
                    break;
            }
            System.out.println("The Geologist card has changed the category to " + currentAttribute);
        }
    }

    //Changes the current category when a player plays their turn.
    private void trumpCardCategoryChangePlayer(Card chosenCard){

        if (chosenCard.getClass() == TrumpCard.class) {
            if (chosenCard.cardTitle.equals("The Miner")) {
                currentAttribute = "Economic Value";
                playedCardEconomicValue = "low";
                System.out.println("The miner card has changed the category to Economic Value." + "\n");

            } else if (chosenCard.cardTitle.equals("The Petrologist")) {
                currentAttribute = "Crustal Abundance";
                playedCardCrustalAbundance = "low";
                System.out.println("The Petrologist card has changed the category to Crustal Abundance." + "\n");

            } else if (chosenCard.cardTitle.equals("The Gemmologist")) {
                currentAttribute = "Hardness";
                playedCardHardness = 0.0;
                System.out.println("The Gemmologist card has changed the category to Hardness." + "\n");

            } else if (chosenCard.cardTitle.equals("The Mineralogist")) {
                currentAttribute = "Cleavage";
                playedCardCleavage = "none";
                System.out.println("The Mineralogist card has changed the category to Cleavage." + "\n");

            } else if (chosenCard.cardTitle.equals("The Geophysicist")) {
                for (int i = 0; i < players[0].playerHand.size(); i++) {
                    Card Mag = players[0].playerHand.get(i);
                    if (Mag.cardTitle.equals("Magnetite")){

                        System.out.println("Would you like to play your Magnetite card to win the hand?");
                        Scanner reader = new Scanner(System.in);
                        System.out.println("1. Yes" + "\n" + "2. No");
                        int answer = reader.nextInt();

                        if (answer == 1) {
                            deck.playedCards.add(Mag);
                            players[0].playerHand.remove(Mag);
                            System.out.println("Player 1 has won the game!");
                            System.exit(0);
                        } else {break;}
                    }
                }
                currentAttribute = "Specific Gravity";
                playedCardSpecificGravity = 0.0;
                System.out.println("The Geophysicist card has changed the category to Specific Gravity." + "\n");

            } else {
                Scanner playerChoice = new Scanner(System.in);
                System.out.println("Which category would you like to play by?");
                System.out.println("1. Hardness" + "\n" + "2. Specific Gravity" + "\n" + "3. Cleavage" + "\n" + "4. Crustal Abundance" + "\n" + "5. Economic Value");
                int attributeChoice = playerChoice.nextInt();
                switch (attributeChoice) {
                    case 1:
                        currentAttribute = "Hardness";
                        playedCardHardness = 0.0;
                        break;
                    case 2:
                        currentAttribute = "Specific Gravity";
                        playedCardSpecificGravity = 0.0;
                        break;
                    case 3:
                        currentAttribute = "Cleavage";
                        playedCardCleavage = "none";
                        break;
                    case 4:
                        currentAttribute = "Crustal Abundance";
                        playedCardCrustalAbundance = "ultratrace";
                        break;
                    case 5:
                        currentAttribute = "Economic Value";
                        playedCardEconomicValue = "low";
                        break;
                }
                System.out.println("The Geologist card has changed the category to " + currentAttribute);
            }
        }
    }

    private void checkHandPass(int currentPosition){

        for (int i = currentPosition; i < players.length; i++){

            if (passCounter == (numberPlayers - 1)){

                if (currentPosition == 0){
                    players[0].wonHand = true;


                } else {
                    players[currentPosition].wonHand = true;

                }
            }
        }

        if (players[0].wonHand){
            System.out.println("You have won the hand!");
            passCounter = 0;
            players[0].wonHand = false;
        }

        if (players[currentPosition].wonHand){
            System.out.println("Player has won the hand!");
            passCounter = 0;
            players[currentPosition].wonHand = false;
        }
    }

    public void beginPlayerTurns(int firstPlayer) {
        if (firstPlayer == 1) {
            printHand();
       //     firstPlayerTurn();
        } else {
            firstBotTurn(firstPlayer);

        }

        //ensure the play starts at the right person.
        if (firstPlayer == 1) {
            for (int i = 1; i < players.length; i++) {
                if (players[i].isHuman) {
                    playerTurn();
                } else {
                    System.out.println("Player " + (i + 1) + " has played a turn:");
                    botTurn(i);
                }
            }
        } else {
            for (int i = firstPlayer; i < players.length; i++) {
                if (players[i].isHuman) {
                    playerTurn();
                } else {
                    System.out.println("Player " + (i + 1) + " has played a turn:");
                    botTurn(i);
                }
            }
        }

        //controls the game until game over
        while (!gameIsover) {

            for (int i = 0; i < players.length; i++) {
                if (players[i].isHuman) {
                    playerTurn();
                } else {
                    System.out.println("Player " + (i + 1) + " has played a turn:");
                    botTurn(i);
                }

            }
        }
    }


    //Queries for the game winning conditions and ends the game when they become true.
    private boolean gameOver(int numberPlayers) {

        for (int i = 0; i < numberPlayers; i++) {
            if (players[i].playerHand.size() == 0){
                System.out.print("Player has won the game!");
                gameIsover = true;
                System.exit(0);
            }
            else gameIsover = false;
        }

        deck.checkEmptyDeck();

        if (deck.emptyDeck()){
            System.out.println("The deck is empty! There are too many cards in the players hands!");
            System.exit(0);
        }

        return gameIsover;
    }

    public void playerHasPassed(){
        System.out.println("You have picked up ");
        Card passCard = deck.playerTurnPass();
        players[0].playerHand.add(passCard);
        System.out.println(passCard);
        players[0].hasPassed = true;
        passCounter = passCounter + 1;

    }

    public static void runGame(){
        for (int i = 0; i < currentGame.players.length; i++) {
            if (currentGame.players[i].isHuman) {
                currentGame.playerTurn();
            } else {
                System.out.println("Player " + (i + 1) + " has played a turn:");
                currentGame.botTurn(i);
            }
        }
    }


    //Lets the player choose the first card to play and a category.
    public ImageIcon playCard(Card card){

            if (!deck.playedCards.isEmpty()) {
                deck.checkEmptyDeck();
                deck.playedCards.clear();
            }

            int CardChoice = 0;

            for (int i = 0; i < players[0].playerHand.size(); i++) {
                if (card.cardTitle.equals(players[0].playerHand.get(i).cardTitle)) {
                    CardChoice = i;
                }
            }

            Card chosenCard = players[0].playerHand.get(CardChoice);

        if (firstTurn == 1) {

            System.out.println("Player 1 has played card: ");
            System.out.println(players[0].playerHand.get(CardChoice));

            //amend decks to played cards
            deck.playedCards.add(players[0].playerHand.get(CardChoice));
            //Card cardChoice = players[0].playerHand.get(CardChoice);
            players[0].playerHand.remove(CardChoice);


            if (chosenCard.getClass() == TrumpCard.class) {
                if (chosenCard.cardTitle.equals("The miner")) {
                    currentAttribute = "Economic Value";
                    System.out.println("The miner card has changed the category to Economic Value.");
                    chosenCard.cardEconomicValue = "null";
                    playedCardEconomicValue = "null";
                    players[0].playerHand.remove(chosenCard);
                } else if (chosenCard.cardTitle.equals("The Petrologist")) {
                    currentAttribute = "Crustal Abundance";
                    System.out.println("The Petrologist card has changed the category to Crustal Abundance.");
                    chosenCard.cardCrustalAbundance = "null";
                    playedCardCrustalAbundance = "null";
                    players[0].playerHand.remove(chosenCard);
                } else if (chosenCard.cardTitle.equals("The Gemmologist")) {
                    currentAttribute = "Hardness";
                    System.out.println("The Gemmologist card has changed the category to Hardness.");
                    chosenCard.cardHardness = 0.0;
                    playedCardHardness = 0.0;
                    players[0].playerHand.remove(chosenCard);
                } else if (chosenCard.cardTitle.equals("The Mineralogist")) {
                    currentAttribute = "Cleavage";
                    System.out.println("The Mineralogist card has changed the category to Cleavage.");
                    chosenCard.cardCleavage = "null";
                    playedCardCleavage = "null";
                    players[0].playerHand.remove(chosenCard);
                } else if (chosenCard.cardTitle.equals("The Geophysicist")) {
                    currentAttribute = "Specific Gravity";
                    System.out.println("The Geophysicist card has changed the category to Specific Gravity.");
                    chosenCard.cardSpecificGravity = 0.0;
                    playedCardSpecificGravity = 0.0;
                    players[0].playerHand.remove(chosenCard);
                }
            }

            if (chosenCard.getClass() == MineralCard.class) {

                chosenCard.cardSpecificGravity = 0.0;
                playedCardSpecificGravity = 0.0;

                chosenCard.cardHardness = 0.0;
                playedCardHardness = 0.0;

                chosenCard.cardCrustalAbundance = "null";
                playedCardCrustalAbundance = "null";

                chosenCard.cardCleavage = "null";
                playedCardCleavage = "null";

                chosenCard.cardEconomicValue = "null";
                playedCardEconomicValue = "null";


            }

            firstTurn = firstTurn + 1;
        }

        else {

            boolean x = false;
            players[0].hasPassed = false;
            checkHandPass(0);
            //  getPlayedCardAttributeValue();

            if (SuperTrumpGame.currentGame.currentAttribute.equals("Hardness")) {
                System.out.println("Please play a card that has a higher hardness than " + playedCardHardness);
            } else if (SuperTrumpGame.currentGame.currentAttribute.equals("Cleavage")) {
                System.out.println("Please play a card that has a higher cleavage than " + playedCardCleavage);
            } else if (SuperTrumpGame.currentGame.currentAttribute.equals("Specific Gravity")) {
                System.out.println("Please play a card that has a higher specific gravity than " + playedCardSpecificGravity);
            } else if (SuperTrumpGame.currentGame.currentAttribute.equals("Crustal Abundance")) {
                System.out.println("Please play a card that has a higher crustal abundance than " + playedCardCrustalAbundance);
            } else {
                System.out.println("Please play a card that has a higher economic value than " + playedCardEconomicValue);
            }


            printHand();
            System.out.println("Enter the number of the card you would like to play or type 'pass' to pickup a card");
           // String CardChoice = (playerChoice.nextLine());

            try {
               // int cardChoice = Integer.parseInt(CardChoice);
                chosenCard = players[0].playerHand.get(CardChoice);
                attributeHardness = chosenCard.cardHardness;
                attributeSpecificGravity = chosenCard.cardSpecificGravity;
                attributeCleavage = chosenCard.cardCleavage;
                attributeCrustalAbundance = chosenCard.cardCrustalAbundance;
                attributeEconomicValue = chosenCard.cardEconomicValue;

                if (chosenCard.getClass() == TrumpCard.class) {
                    trumpCardCategoryChangePlayer(chosenCard);
                    players[0].playerHand.remove(chosenCard);

                } else {

                    if (currentAttribute.equals("Hardness")) {
                        compareCardsHardness();

                        if (compareCardsHardness()) {
                            deck.playedCards.add(players[0].playerHand.get(CardChoice));
                            System.out.println("Player 1 has played card: " + players[0].playerHand.get(CardChoice));
                            players[0].playerHand.remove(CardChoice);
                        }

                        while (!compareCardsHardness() && !x) {
                            System.out.println("This card has a Hardness of " + attributeHardness + ".");
                            System.out.println("Please play a card that is a higher hardness than " + playedCardHardness + " or type 'pass' to pickup a card.");
                           // CardChoice = (playerChoice.nextLine());

                            try {

                            } catch (NumberFormatException e) {
                                if ("pass".equals(CardChoice)) {
                                    //Pick up card if the user types pass.
                                    System.out.println("You have picked up ");
                                    Card passCard = deck.playerTurnPass();
                                    players[0].playerHand.add(passCard);
                                    System.out.println(passCard);
                                    players[0].hasPassed = true;
                                    x = true;
                                    passCounter = passCounter + 1;
                                }
                            }

                            chosenCard = players[0].playerHand.get(CardChoice);
                            if (chosenCard.getClass() == TrumpCard.class) {
                                trumpCardCategoryChangePlayer(chosenCard);
                                x = true;
                                players[0].playerHand.remove(chosenCard);
                            }
                            attributeHardness = chosenCard.cardHardness;
                            compareCardsHardness();
                        }

                    }

                    //crustal abundance
                    if (currentAttribute.equals("Crustal Abundance")) {
                        compareCardsCrustalAbundance();

                        if (compareCardsCrustalAbundance()) {
                            deck.playedCards.add(players[0].playerHand.get(CardChoice));
                            System.out.println("Player 1 has played card: " + players[0].playerHand.get(CardChoice));
                            players[0].playerHand.remove(CardChoice);
                        }

                        while (!compareCardsCrustalAbundance() && !x) {
                            System.out.println("This card has a Crustal Abundance of " + attributeCrustalAbundance + ".");
                            System.out.println("Please play a card that is a higher crustal abundance than " + playedCardCrustalAbundance + " or type 'pass' to pickup a card.");

                          //  CardChoice = (playerChoice.nextLine());

                            try {
                              //  cardChoice = Integer.parseInt(CardChoice);
                            } catch (NumberFormatException e) {
                                if ("pass".equals(CardChoice)) {
                                    //Pick up card if the user types pass.
                                    System.out.println("You have picked up ");
                                    Card passCard = deck.playerTurnPass();
                                    players[0].playerHand.add(passCard);
                                    System.out.println(passCard);
                                    players[0].hasPassed = true;
                                    x = true;
                                    passCounter = passCounter + 1;
                                }
                            }

                            chosenCard = players[0].playerHand.get(CardChoice);

                            if (chosenCard.getClass() == TrumpCard.class) {
                                trumpCardCategoryChangePlayer(chosenCard);
                                players[0].playerHand.remove(chosenCard);
                                x = true;
                            }
                            attributeCrustalAbundance = chosenCard.cardCrustalAbundance;
                            compareCardsCrustalAbundance();
                        }

                    }

                    //economic value
                    if (currentAttribute.equals("Economic Value")) {
                        compareCardsEconomicValue();

                        if (compareCardsEconomicValue()) {
                            deck.playedCards.add(players[0].playerHand.get(CardChoice));
                            System.out.println("Player 1 has played card: " + players[0].playerHand.get(CardChoice));
                            players[0].playerHand.remove(CardChoice);
                        }

                        while (!compareCardsEconomicValue() && !x) {
                            System.out.println("This card has a Economic Value of " + attributeEconomicValue + ".");
                            System.out.println("Please play a card that is a higher economic value than " + playedCardEconomicValue + " or type 'pass' to pickup a card.");

                       //     CardChoice = (playerChoice.nextLine());

                            try {
                              //  cardChoice = Integer.parseInt(CardChoice);
                            } catch (NumberFormatException e) {
                                if ("pass".equals(CardChoice)) {
                                    //Pick up card if the user types pass.
                                    System.out.println("You have picked up ");
                                    Card passCard = deck.playerTurnPass();
                                    players[0].playerHand.add(passCard);
                                    System.out.println(passCard);
                                    players[0].hasPassed = true;
                                    x = true;
                                    passCounter = passCounter + 1;
                                }
                            }

                            chosenCard = players[0].playerHand.get(CardChoice);

                            if (chosenCard.getClass() == TrumpCard.class) {
                                trumpCardCategoryChangePlayer(chosenCard);
                                players[0].playerHand.remove(chosenCard);
                                x = true;
                            }
                            attributeEconomicValue = chosenCard.cardEconomicValue;
                            compareCardsEconomicValue();
                        }

                    }
                    // specific gravity
                    if (currentAttribute.equals("Specific Gravity")) {
                        compareCardsSpecificGravity();

                        if (compareCardsSpecificGravity()) {
                            deck.playedCards.add(players[0].playerHand.get(CardChoice));
                            System.out.println("Player 1 has played card: " + players[0].playerHand.get(CardChoice));
                            players[0].playerHand.remove(CardChoice);
                        }

                        while (!compareCardsSpecificGravity() && !x) {
                            System.out.println("This card has a Specific Gravity of " + attributeSpecificGravity + ".");
                            System.out.println("Please play a card that is a higher specific gravity than " + playedCardSpecificGravity + " or type 'pass' to pickup a card.");

                            //System.out.println("Please enter the number of the card you would like to play:");
                           // CardChoice = (playerChoice.nextLine());

                            try {
                            //    cardChoice = Integer.parseInt(CardChoice);
                            } catch (NumberFormatException e) {
                                if ("pass".equals(CardChoice)) {
                                    //Pick up card if the user types pass.
                                    System.out.println("You have picked up ");
                                    Card passCard = deck.playerTurnPass();
                                    players[0].playerHand.add(passCard);
                                    System.out.println(passCard);
                                    x = true;
                                    passCounter = passCounter + 1;
                                }
                            }

                            chosenCard = players[0].playerHand.get(CardChoice);
                            if (chosenCard.getClass() == TrumpCard.class) {
                                trumpCardCategoryChangePlayer(chosenCard);
                                players[0].playerHand.remove(chosenCard);
                                x = true;
                            }
                            attributeSpecificGravity = chosenCard.cardSpecificGravity;
                            compareCardsSpecificGravity();
                        }

                    }

                    //cleavage
                    if (currentAttribute.equals("Cleavage")) {
                        compareCardsCleavage();

                        if (compareCardsCleavage()) {
                            deck.playedCards.add(players[0].playerHand.get(CardChoice));
                            System.out.println("Player 1 has played card: " + players[0].playerHand.get(CardChoice));
                            players[0].playerHand.remove(CardChoice);
                        }

                        while (!compareCardsCleavage() && !x) {
                            System.out.println("This card has a cleavage of " + attributeSpecificGravity + ".");
                            System.out.println("Please play a card that is a higher cleavage than " + playedCardCleavage + " or type 'pass' to pickup a card.");

                            //System.out.println("Please enter the number of the card you would like to play:");
                           // CardChoice = (playerChoice.nextLine());

                            try {
                              //  cardChoice = Integer.parseInt(CardChoice);
                            } catch (NumberFormatException e) {
                                if ("pass".equals(CardChoice)) {
                                    //Pick up card if the user types pass.
                                    System.out.println("You have picked up ");
                                    Card passCard = deck.playerTurnPass();
                                    players[0].playerHand.add(passCard);
                                    System.out.println(passCard);
                                    players[0].hasPassed = true;
                                    passCounter = passCounter + 1;
                                    x = true;
                                }
                            }

                            chosenCard = players[0].playerHand.get(CardChoice);
                            if (chosenCard.getClass() == TrumpCard.class) {
                                trumpCardCategoryChangePlayer(chosenCard);
                                players[0].playerHand.remove(chosenCard);
                                x = true;
                            }
                            attributeCleavage = chosenCard.cardCleavage;
                            compareCardsCleavage();
                        }

                    }
                }

               /* if (players[0].wonHand) {
                    System.out.println("Which category would you like to play by?");
                    System.out.println("1. Hardness" + "\n" + "2. Specific Gravity" + "\n" + "3. Cleavage" + "\n" + "4. Crustal Abundance" + "\n" + "5. Economic Value");
                    int attributeChoice = playerChoice.nextInt();
                    switch (attributeChoice){
                        case 1:
                            currentAttribute = "Hardness";
                            attributeHardness = chosenCard.cardHardness;
                            System.out.println("You have changed the category to Hardness.");
                            System.out.println("The chosen cards hardness is " + attributeHardness);
                            playedCardHardness = chosenCard.cardHardness;
                            break;
                        case 2:
                            currentAttribute = "Specific Gravity";
                            attributeSpecificGravity = chosenCard.cardSpecificGravity;
                            System.out.println("You have changed the category to Specific Gravity.");
                            System.out.println("The chosen cards specific gravity is " + attributeSpecificGravity);
                            playedCardSpecificGravity = chosenCard.cardSpecificGravity;
                            break;
                        case 3:
                            currentAttribute = "Cleavage";
                            attributeCleavage = chosenCard.cardCleavage;
                            System.out.println("You have changed the category to Cleavage.");
                            System.out.println("The chosen cards cleavage is " + attributeCleavage);
                            playedCardCleavage = chosenCard.cardCleavage;
                            break;
                        case 4:
                            currentAttribute = "Crustal Abundance";
                            attributeCrustalAbundance = chosenCard.cardCrustalAbundance;
                            System.out.println("You have changed the category to Crustal Abundance.");
                            System.out.println("The chosen cards crustal abundance is " + attributeCrustalAbundance);
                            playedCardCrustalAbundance = chosenCard.cardCrustalAbundance;
                            break;
                        case 5:
                            currentAttribute = "Economic Value";
                            attributeEconomicValue = chosenCard.cardEconomicValue;
                            System.out.println("You have changed the category to Economic Value.");
                            System.out.println("The chosen cards economic value is " + attributeEconomicValue);
                            playedCardEconomicValue = chosenCard.cardEconomicValue;
                            break;
                    }*/
                    passCounter = 0;



            } catch (NumberFormatException e) {
                if ("pass".equals(CardChoice)) {
                    //Pick up card if the user types pass.
                    System.out.println("You have picked up ");
                    Card passCard = deck.playerTurnPass();
                    players[0].playerHand.add(passCard);
                    System.out.println(passCard);
                    players[0].hasPassed = true;
                    x = true;
                    passCounter = passCounter + 1;
                }
            }
            players[0].wonHand = false;
            gameOver(numberPlayers);

        }

        cardimageplayed = getPlayedCardImage();
        return cardimageplayed;

    }

}


