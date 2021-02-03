package epam.art.OODesign.entity;

public class Ginger extends Condiment{
    private Beverage beverage;

    public Ginger(Beverage beverage) {
        this.beverage = beverage;
    }

    @Override
    public String getDescription() {
        return beverage.getDescription() + ", ginger";
    }

    @Override
    public double cost() {
        return beverage.cost() + 0.10;
    }
}
