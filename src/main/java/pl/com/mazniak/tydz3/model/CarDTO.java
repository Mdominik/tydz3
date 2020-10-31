package pl.com.mazniak.tydz3.model;

public class CarDTO {
    public CarDTO(String mark, String model, Color color) {
        this.mark = mark;
        this.model = model;
        this.color = color;
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

    String mark;
    String model;
    Color color;
    public CarDTO(Car car) {
        this.mark = car.getMark();
        this.model = car.getModel();
        this.color = car.getColor();
    }

}
