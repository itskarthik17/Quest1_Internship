# Day 3 — Design Patterns

So on Day 3 I learned about design patterns.

Design patterns are reusable solutions to common software design problems. In this session we covered structural, creational, and behavioral patterns.

## Creational patterns

### Singleton

Only one instance of the class is created and used throughout the system (for example, a database connection manager). Singletons help avoid multiple instances that could cause synchronization or state consistency problems.

Java (thread-safe lazy singleton):

```java
public class Database {
    private static volatile Database instance;
    private Database() { }
    public static Database getInstance() {
        if (instance == null) {
            synchronized (Database.class) {
                if (instance == null) instance = new Database();
            }
        }
        return instance;
    }
}

```

### Factory

The Factory pattern hides object-creation logic from the client. A factory method or class returns different implementations of an interface or base class depending on input.

Java example:

```java
public interface Shape { void draw(); }
public class Circle implements Shape { public void draw() { System.out.println("Circle"); } }
public class Rectangle implements Shape { public void draw() { System.out.println("Rectangle"); } }
public class ShapeFactory {
    public static Shape create(String type) {
        switch (type.toLowerCase()) {
            case "circle": return new Circle();
            case "rectangle": return new Rectangle();
            default: throw new IllegalArgumentException("Unknown shape");
        }
    }
}
// Usage: Shape s = ShapeFactory.create("circle"); s.draw();
```

## Structural Patterns

Structural patterns help compose classes or objects into larger structures while keeping code flexible and maintainable. Below are concise explanations and examples for **Decorator** and **Proxy**.

### Decorator

Adds behavior to objects dynamically without changing their class. Useful for layering responsibilities (e.g., adding logging, validation, or UI features).

Java example:

```java
public interface Coffee { double cost(); String desc(); }

public class SimpleCoffee implements Coffee {
    public double cost() { return 2.0; }
    public String desc() { return "Simple coffee"; }
}

public abstract class CoffeeDecorator implements Coffee {
    protected Coffee coffee;
    public CoffeeDecorator(Coffee coffee) { this.coffee = coffee; }
    public double cost() { return coffee.cost(); }
    public String desc() { return coffee.desc(); }
}

public class Milk extends CoffeeDecorator {
    public Milk(Coffee coffee) { super(coffee); }
    public double cost() { return super.cost() + 0.5; }
    public String desc() { return super.desc() + " + milk"; }
}

// Usage:
// Coffee c = new Milk(new SimpleCoffee());
// System.out.println(c.desc() + " = " + c.cost());
```

### Proxy

Provides a surrogate or placeholder for another object to control access, add lazy initialization, caching, or access control.

Java example:

```java
public interface Image { void display(); }

public class RealImage implements Image {
    private String file;
    public RealImage(String file) { this.file = file; load(); }
    private void load() { System.out.println("Loading " + file); }
    public void display() { System.out.println("Displaying " + file); }
}

public class ProxyImage implements Image {
    private RealImage real;
    private String file;
    public ProxyImage(String file) { this.file = file; }
    public void display() {
        if (real == null) real = new RealImage(file);
        real.display();
    }
}

// Usage:
// Image img = new ProxyImage("photo.jpg");
// img.display();
```
