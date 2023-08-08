package compare;

import java.util.Arrays;
import java.util.Collections;

class Duck implements Comparable<Duck> { // 1. Arrays.sort(ducks)가 가능하도록 타입을 맞춰주는 일
    String name;
    int weight;

    public Duck(String name, int weight) {
        this.name = name;
        this.weight = weight;
    }

    public String toString() {
        return name + " weighs " + weight;
    }

    public int compareTo(Duck otherDuck) { // 2. sort()메서드에서 필요로 하는 것

        if (this.weight < otherDuck.weight) {
            return -1;
        } else if (this.weight == otherDuck.weight) {
            return 0;
        } else { // this.weight > otherDuck.weight
            return 1;
        }
    }
}

public class TestDriver {
    public static void main(String[] args) {
        Duck[] ducks = { 
                            new Duck("Daffy", 8), 
                            new Duck("Dewey", 2),
                            new Duck("Howard", 7), 
                            new Duck("Louie", 2),
                            new Duck("Donald", 10), 
                            new Duck("Huey", 2) 
        };

        System.out.println("Before sorting:");
        display(ducks);

        
        System.out.println("\nAfter sorting:");
        Arrays.sort(ducks); // 3. duck.compareTo()를 호출하면서 정렬
        display(ducks);
        Arrays.sort(ducks, Collections.reverseOrder()); // 내림차순
        display(ducks); 
    }

    public static void display(Duck[] ducks) {
        for (Duck d : ducks) {
            System.out.println(d);
        }
    }
    
}
