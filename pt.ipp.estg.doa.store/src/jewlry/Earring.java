package jewlry;

import utils.ValidationUtils;

public class Earring extends Jewelry {
    //  Type of clasp (e.g., "Stud", "Hook", "Leverback")
    private String claspType;

    public Earring(int jewelryId, String name, String material, double weight, double price, int stock, Category category, String claspType) {
        super(jewelryId, name, material, weight, price, stock, JewelryType.EARRING, category);
        ValidationUtils.requireNonBlank(claspType, "clasp type");
        this.claspType = claspType;
    }

    public String getClaspType() {
        return claspType;
    }

    public void setClaspType(String claspType) {
        ValidationUtils.requireNonBlank(claspType, "clasp type");
        this.claspType = claspType;
    }

    @Override
    public void fromCSV(String csvLine) {

    }

    @Override
    public String toCSV() {
        return getJewelryId() + ", EARRING, " +
                getName() + ", " +
                getMaterial() + ", " +
                getWeight() + ", " +
                getClaspType() + ", " +
                getPrice() + ", " +
                getStock() + ", " +
                getCategory();
    }
}
