
/**
 * Created by Darcie on 3/09/2016.
 */
class STCard {


        private String cardTitle;
        private String cardChemistry;
        private String cardClassification;
        private String cardCrystalSystem;
        private String cardOccurrence;
        private double cardHardness;
        private double cardSpecificGravity;
        private String cardCleavage;
        private String cardCrystalAbundance;
        private String cardEconomicValue;

        public STCard(String title, String chemistry, String classification,
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

        public STCard(String title, String economicValue){
            cardTitle = title;
            cardEconomicValue = economicValue;
        }

    public String toString(){
        return "Card Name: " + cardTitle + " , Chemistry: " + cardChemistry + " , Classification: " + cardClassification + " , Crystal System: " + cardCrystalSystem +
                " , Occurrance: " + cardOccurrence + " , Hardness: " + cardHardness + " , Specific Gravity: " + cardSpecificGravity
                + " , Cleavage: " + cardCleavage + " , Crystal Abundance: " + cardCrystalAbundance + " , Economic Value: " + cardEconomicValue + "\n";

    }
}

