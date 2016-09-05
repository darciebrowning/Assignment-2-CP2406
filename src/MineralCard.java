/**
 * Created by Darcie on 26/08/2016.
 **/
public class MineralCard extends Card {

    public MineralCard(String title, String chemistry, String classification,
                       String crystalSystem, String occurrence, double hardness,
                       double specificGravity, String cleavage, String crystalAbundance,
                       String economicValue) {
        super(title, chemistry, classification, crystalSystem, occurrence, hardness, specificGravity,
                cleavage, crystalAbundance, economicValue);
    }

    public String toString() {
        return "cardTitle= " + cardTitle + " ," + cardChemistry;
    }

}