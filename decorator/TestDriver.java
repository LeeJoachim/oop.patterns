package decorator;

abstract class Beverage {
    String description = "";
    double cost;
    Condiment condiment;
    double cupSize;

    public void setCondiment(Condiment condiment) {
        this.condiment = condiment;
        cost += condiment.getCost();
        description += "\ncondiment: " + condiment.getName();
    }
    public String getDescription() {
        return description;
    }
    public double getCost() {
        return cost*cupSize;
    }
    public double getCupSize() {
        return cupSize;
    }

}

class Espresso extends Beverage {
    public Espresso(double cupSize) {
        description = "==Espresso==";
        cost = 1.99;
        this.cupSize = cupSize;
    }
}

abstract class Condiment {
    String name;
    double cost;
    Condiment next;
    
    public boolean hasNext() {
        return next != null;
    }
    public double getCost() {
        if(hasNext()) {
            return cost + next.getCost();
        }
        return cost;
    }
    public String getName() {
        if(hasNext()) {
            return name + " " + next.getName();
        }
        return name;
    }


}
class Milk extends Condiment {
    public Milk(Condiment next) {
        this.next = next;
        name = "Milk";
        cost = 1.00;
    }
}
class Mocha extends Condiment {
    public Mocha(Condiment next) {
        this.next = next;
        name = "Mocha";
        cost = 1.00;
    }
}

public class TestDriver {
    public static void main(String[] args) {
        Beverage espresso = new Espresso(1.2);
        espresso.setCondiment(new Milk(new Mocha(null)));
        System.out.println(espresso.getDescription());
        System.out.println("cost: " + espresso.getCost());
        System.out.println("cup: " + espresso.getCupSize());

    }

}
