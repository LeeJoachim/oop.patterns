package iterator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Iterator;

class MenuItem {
    String name;
    String description;
    boolean vegetarian;
    double price;

    public MenuItem (String name, String description, boolean vegetarian, double price) {
        this.name = name;
        this.description = description;
        this.vegetarian = vegetarian;
        this.price = price;
    }

    // 2. 메뉴 항목을 출력하는 메서드
    public String toString() {
        return (name + ", $" + price + "\n   " + description);
    }
}

class PancakeHouseMenu {
    List<MenuItem> menuItems;

    PancakeHouseMenu() {
        menuItems = new ArrayList<MenuItem>();
        menuItems.add(new MenuItem("K&B's Pancake Breakfast", "Pancakes with scrambled eggs, and toast", true, 2.99));
        menuItems.add(new MenuItem("Regular Pancake Breakfast", "Pancakes with fried eggs, sausage", false, 2.99));
        menuItems.add(new MenuItem("Blueberry Pancakes", "Pancakes made with fresh blueberries", true, 3.49));
        menuItems.add(new MenuItem("Waffles", "Waffles, with your choice of blueberries or strawberries", true, 3.59));

    }

    Iterator<MenuItem> createIterator() {
        return menuItems.iterator();
    }

}

class DinerMenu {
    static final int MAX_ITEMS = 6;
    int numberOfItems = 0;
    MenuItem[] menuItems;

    DinerMenu() {
        menuItems = new MenuItem[MAX_ITEMS];

        addItem("Vegetarian BLT", "(Fakin') Bacon with lettuce & tomato on whole wheat", true, 2.99);
        addItem("BLT", "Bacon with lettuce & tomato on whole wheat", false, 2.99);
        addItem("Soup of the day", "Soup of the day, with a side of potato salad", false, 3.29);
        addItem("Hotdog", "A hot dog, with saurkraut, relish, onions, topped with cheese", false, 3.05);
    }

    // 1. 메뉴 항목을 추가하는 메서드
    public void addItem(String name, String description, boolean vegetarian, double price) {
        MenuItem menuItem = new MenuItem(name, description, vegetarian, price);

        if (numberOfItems >= MAX_ITEMS) {
            System.out.println("Sorry, menu is full! Can't add item to menu");
        } else {
            menuItems[numberOfItems] = menuItem;
            numberOfItems++;
        }
    }

    Iterator<MenuItem> createIterator() {
        return Arrays.asList(menuItems).iterator();
    }
}

class Waitress {
    Iterator<MenuItem> pIter;
    Iterator<MenuItem> dIter;

    Waitress(Iterator<MenuItem> pIter, Iterator<MenuItem> dIter) {
        this.pIter = pIter;
        this.dIter = dIter;
    }

    public void printMenu() {

        System.out.println("MENU\n----\nBREAKFAST");
        iterating(pIter);

        System.out.println("\nLUNCH");
        iterating(dIter);
    }

    private void iterating(Iterator<MenuItem> iter) {
        while (iter.hasNext()) {
            MenuItem menuItem = iter.next();
            System.out.println(menuItem);
        }
    }

}

public class TestDriver {
    public static void main(String[] args) {

        PancakeHouseMenu pancakeHouseMenu = new PancakeHouseMenu();
        DinerMenu dinerMenu = new DinerMenu();
        Waitress waitress = new Waitress(pancakeHouseMenu.createIterator(), 
                                        dinerMenu.createIterator());
        waitress.printMenu();
        
    }
}
