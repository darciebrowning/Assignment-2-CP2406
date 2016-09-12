/**
 * Created by Darcie on 26/08/2016.
*/
public class TrumpCard extends Card{

    public TrumpCard(String title, String economicValue){
        super(title, economicValue);
    }

    public String toString(){

        return "Card Name: " + cardTitle + " , Chemistry: " + cardChemistry + " , Classification: " + cardClassification + " , Crystal System: " + cardCrystalSystem +
                " , Occurrance: " + cardOccurrence + " , Hardness: " + cardHardness + " , Specific Gravity: " + cardSpecificGravity
                + " , Cleavage: " + cardCleavage + " , Crystal Abundance: " + cardCrystalAbundance + " , Economic Value: " + cardEconomicValue + "\n";

    }
}
