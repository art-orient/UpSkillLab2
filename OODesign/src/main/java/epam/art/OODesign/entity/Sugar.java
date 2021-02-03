package epam.art.OODesign.entity;

public class Sugar extends Condiment{
    private Beverage beverage;

    public Sugar(Beverage beverage) {
        this.beverage = beverage;
    }

    @Override
    public String getDescription() {
        return beverage.getDescription() + ", sugar";
    }

    @Override
    public double cost() {
        return beverage.cost() + 0.10;
    }
}
