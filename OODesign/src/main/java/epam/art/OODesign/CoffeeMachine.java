package epam.art.OODesign;

import epam.art.OODesign.entity.*;

public class CoffeeMachine {
    public static void main(String[] args) {
        Beverage myCoffee = new Arabica();
        myCoffee = new Milk(myCoffee);
        myCoffee = new Vanilla(myCoffee);
        myCoffee = new Sugar(myCoffee);
        System.out.printf("%s $%.2f", myCoffee.getDescription(), myCoffee.cost());
    }
}
