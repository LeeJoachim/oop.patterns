package strategy;

interface FlyBehavior {
    void fly();
}
class FlyWithWings implements FlyBehavior {
    @Override
    public void fly() {
        System.out.println("Fly with wings");
    }
}
class FlyNoWay implements FlyBehavior {
    @Override
    public void fly() {
        System.out.println("Can't fly");
    }
}
class FlyRocketPowered implements FlyBehavior {
    @Override
    public void fly() {
        System.out.println("Fly with rocket");
    }
}

interface QuackBehavior {
    void quack();
}

class Quack implements QuackBehavior {
    @Override
    public void quack() {
        System.out.println("Quack");
    }
}

class Squeak implements QuackBehavior {
    @Override
    public void quack() {
        System.out.println("Squeak");
    }
}


abstract class Duck {
    protected FlyBehavior flyBehavior;
    protected QuackBehavior quackBehavior;

    void fly() {
        flyBehavior.fly();
    }
    void quack() {
        quackBehavior.quack();
    }
    public void setFlyBehavior(FlyBehavior flyBehavior) {
        this.flyBehavior = flyBehavior;
    }
    
}

class MallardDuck extends Duck{
    
    public MallardDuck() {
        flyBehavior = new FlyWithWings();
        quackBehavior = new Quack();
    }
}

class RubberDuck extends Duck{

    public RubberDuck() {
        flyBehavior = new FlyNoWay();
        quackBehavior = new Squeak();
    }
}

class TurkeyAdapter extends Duck {
    Turkey turkey;
    public TurkeyAdapter(Turkey turkey) {
        this.turkey = turkey;
    }
    @Override
    void fly() {
        turkey.fly();
    }
    @Override
    void quack() {
        turkey.gobble();
    }
}

interface Turkey {
    void gobble();
    void fly();
}

class WildTurkey implements Turkey {
    @Override
    public void gobble() {
        System.out.println("Gobble gobble");
    }
    @Override
    public void fly() {
        System.out.println("Fly short distance");
    }
}

public class TestDriver {
    public static void main(String[] args) {
        Duck duck = new MallardDuck();
        duck.setFlyBehavior(() -> System.out.println("Fly with rocket"));
        duck.fly();
        duck.quack();
        duck = new RubberDuck();
        duck.fly();
        duck.quack();

        Turkey turkey = new WildTurkey();
        turkey.fly();
        turkey.gobble();
        Duck turkeyAdapter = new TurkeyAdapter(turkey);
        turkeyAdapter.fly();
        turkeyAdapter.quack();
    }
}