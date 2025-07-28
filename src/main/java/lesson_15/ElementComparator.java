package lesson_15;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;

public class ElementComparator {
    public static void compareElements(WebElement elem1, WebElement elem2) {
        Point location1 = elem1.getLocation();
        Point location2 = elem2.getLocation();

        Dimension size1 = elem1.getSize();
        Dimension size2 = elem2.getSize();

        //vertical
        if (location1.getY() < location2.getY()) {
            System.out.println("Element 1 is positioned higher on the page than Element 2.");
        } else if (location1.getY() > location2.getY()) {
            System.out.println("Element 2 is positioned higher on the page than Element 1.");
        } else {
            System.out.println("Both elements are at the same vertical position.");
        }

        //horizontal
        if (location1.getX() < location2.getX()) {
            System.out.println("Element 1 is positioned more to the left than Element 2.");
        } else if (location1.getX() > location2.getX()) {
            System.out.println("Element 2 is positioned more to the left than Element 1.");
        } else {
            System.out.println("Both elements are at the same horizontal position.");
        }

        //area
        int area1 = size1.getHeight() * size1.getWidth();
        int area2 = size2.getHeight() * size2.getWidth();

        if (area1 > area2) {
            System.out.println("Element 1 takes up more space than Element 2.");
        } else if (area1 < area2) {
            System.out.println("Element 2 takes up more space than Element 1.");
        } else {
            System.out.println("Both elements take up the same amount of space.");
        }
    }
}