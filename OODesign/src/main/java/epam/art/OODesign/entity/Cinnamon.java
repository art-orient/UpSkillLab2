package epam.art.OODesign.entity;

public class Cinnamon extends Condiment{
    private Beverage beverage;

    public Cinnamon(Beverage beverage) {
        this.beverage = beverage;
    }

    @Override
    public String getDescription() {
        return beverage.getDescription() + ", cinnamon";
    }

    @Override
    public double cost() {
        return beverage.cost() + 0.15;
    }
}
