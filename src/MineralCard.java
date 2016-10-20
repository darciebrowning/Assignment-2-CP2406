import java.awt.image.BufferedImage;

/**
 * Created by Darcie on 26/08/2016.
 **/
public class MineralCard extends Card {

    public MineralCard(String title, String chemistry, String classification,
                       String crystalSystem, String occurrence, double hardness,
                       double specificGravity, String cleavage, String crystalAbundance,
                       String economicValue, BufferedImage cardImage) {
        super(title, chemistry, classification, crystalSystem, occurrence, hardness, specificGravity,
                cleavage, crystalAbundance, economicValue, cardImage);
    }

    public String toString(){

        return "Card Name: " + cardTitle + " , Chemistry: " + cardChemistry + " , Classification: " + cardClassification + " , Crystal System: " + cardCrystalSystem +
                " , Occurrance: " + cardOccurrence + " , Hardness: " + cardHardness + " , Specific Gravity: " + cardSpecificGravity
                + " , Cleavage: " + cardCleavage + " , Crustal Abundance: " + cardCrustalAbundance + " , Economic Value: " + cardEconomicValue + "\n";

    }

}