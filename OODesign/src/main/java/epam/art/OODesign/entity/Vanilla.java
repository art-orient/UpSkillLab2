package epam.art.OODesign.entity;

public class Vanilla extends Condiment {
    private Beverage beverage;

    public Vanilla(Beverage beverage) {
        this.beverage = beverage;
    }

    @Override
    public String getDescription() {
        return beverage.getDescription() + ", vanilla";
    }

    @Override
    public double cost() {
        return beverage.cost() + 0.05;
    }
}