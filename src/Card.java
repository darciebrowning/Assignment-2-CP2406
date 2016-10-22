import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Created by Darcie on 26/08/2016.
 * Creates the card object that Trump and Mineral are derived from.
 */

public class Card {

    public String cardTitle;
    String cardChemistry;
    String cardClassification;
    String cardCrystalSystem;
    String cardOccurrence;
    double cardHardness;
    double cardSpecificGravity;
    String cardCleavage;
    String cardCrustalAbundance;
    String cardEconomicValue;
    BufferedImage cardImage;

    Card(String title, String chemistry, String classification,
         String crystalSystem, String occurrence, double hardness,
         double specificGravity, String cleavage, String crystalAbundance,
         String economicValue, BufferedImage cardImg){
        cardTitle = title;
        cardChemistry = chemistry;
        cardClassification = classification;
        cardCrystalSystem = crystalSystem;
        cardOccurrence = occurrence;
        cardHardness = hardness;
        cardSpecificGravity = specificGravity;
        cardCleavage = cleavage;
        cardCrustalAbundance = crystalAbundance;
        cardEconomicValue = economicValue;
        cardImage = cardImg;
    }

    Card(String title, String economicValue, BufferedImage cardImg){
        cardTitle = title;
        cardEconomicValue = economicValue;
        cardImage = cardImg;
    }

    public ImageIcon getCardImage(){
        Image scaled = cardImage.getScaledInstance(100, 130, Image.SCALE_SMOOTH);
        return new ImageIcon(scaled);
    }

    public ImageIcon getCardBack() throws IOException {
        BufferedImage cardBack = ImageIO.read(new File("CardBack.jpg"));
        Image scaled = cardBack.getScaledInstance(40, 70, Image.SCALE_SMOOTH);
        return new ImageIcon(scaled);
    }

    public String getCardName() {
        return cardTitle;
    }

    public String toString(){

        return "Card Name: " + cardTitle + " , Chemistry: " + cardChemistry + " , Classification: " + cardClassification + " , Crystal System: " + cardCrystalSystem +
                " , Occurrance: " + cardOccurrence + " , Hardness: " + cardHardness + " , Specific Gravity: " + cardSpecificGravity
                + " , Cleavage: " + cardCleavage + " , Crustal Abundance: " + cardCrustalAbundance + " , Economic Value: " + cardEconomicValue + "\n";

    }


}

