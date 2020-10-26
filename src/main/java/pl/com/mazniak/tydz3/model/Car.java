package pl.com.mazniak.tydz3.model;

public class Car {
    public static long getCarCounter() {
        return carCounter;
    }

    public static void setCarCounter(long carCounter) {
        Car.carCounter = carCounter;
    }

    private static long carCounter = 0;
    public Car(String mark, String model, Color color) {
        carCounter++;
        this.id = carCounter;
        this.mark = mark;
        this.model = model;
        this.color = color;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    private long id;
    private String mark;
    private String model;
    private Color color;
}
