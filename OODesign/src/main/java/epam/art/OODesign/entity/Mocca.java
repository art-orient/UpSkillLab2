package epam.art.OODesign.entity;

public class Mocca extends Condiment{
    private Beverage beverage;

    public Mocca(Beverage beverage) {
        this.beverage = beverage;
    }

    @Override
    public String getDescription() {
        return beverage.getDescription() + ", mocca";
    }

    @Override
    public double cost() {
        return beverage.cost() + 0.25;
    }
}