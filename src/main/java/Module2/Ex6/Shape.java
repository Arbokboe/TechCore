package Module2.Ex6;

import java.util.ArrayList;
import java.util.List;

abstract class Shape {
    public abstract double getArea();

    public static void main(String[] args) {
        List<Shape> shapes = new ArrayList<>();

        shapes.add(new Circle(5.0));
        shapes.add(new Rectangle(4.0, 6.0));
        shapes.add(new Circle(3.0));
        shapes.add(new Rectangle(2.0, 8.0));

        double totalArea = 0;
        for (Shape shape : shapes) {
            totalArea += shape.getArea();
        }

        System.out.println("Общая площадь всех фигур: " + totalArea);
    }
}
