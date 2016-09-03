/**
 * Created by Darcie on 26/08/2016.
 */

public class Card {

    public String cardTitle;
    public String cardChemistry;
    public String cardClassification;
    public String cardCrystalSystem;
    public String cardOccurrence;
    public double cardHardness;
    public double cardSpecificGravity;
    public String cardCleavage;
    public String cardCrystalAbundance;
    public String cardEconomicValue;

    public Card(String title, String chemistry, String classification,
                String crystalSystem, String occurrence, double hardness,
                double specificGravity, String cleavage, String crystalAbundance,
                String economicValue){
        cardTitle = title;
        cardChemistry = chemistry;
        cardClassification = classification;
        cardCrystalSystem = crystalSystem;
        cardOccurrence = occurrence;
        cardHardness = hardness;
        cardSpecificGravity = specificGravity;
        cardCleavage = cleavage;
        cardCrystalAbundance = crystalAbundance;
        cardEconomicValue = economicValue;
    }

    public Card(String title, String economicValue){
        cardTitle = title;
        cardEconomicValue = economicValue;
    }

    public String toString(){
        return "cardTitle= " + cardTitle + " ," + cardChemistry;
    }

}

