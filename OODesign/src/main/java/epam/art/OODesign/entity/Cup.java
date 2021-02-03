package epam.art.OODesign.entity;

public class Cup extends Condiment{
    private Beverage beverage;

    public Cup(Beverage beverage) {
        this.beverage = beverage;
    }

    @Override
    public double cost() {
        return beverage.cost();
    }

    @Override
    public String getDescription() {
        return beverage.description;
    }
}

