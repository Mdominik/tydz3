package pl.com.mazniak.tydz3.service;

import org.springframework.stereotype.Service;
import pl.com.mazniak.tydz3.model.Car;
import pl.com.mazniak.tydz3.model.Color;

import java.util.ArrayList;
import java.util.List;

@Service
public class CarManager {
    public List<Car> getCarList() {
        return carList;
    }

    public void setCarList(List<Car> carList) {
        this.carList = carList;
    }

    private List<Car> carList;


    public CarManager() {
        carList = new ArrayList<>();
        carList.add(new Car("Audi", "Q7", Color.BLACK));
        carList.add(new Car("BMW", "i3", Color.BLUE));
        carList.add(new Car("Honda", "Civic", Color.RED));
    }

    public boolean addCar(Car car) {
        return carList.add(car);
    }
    public boolean removeCar(Car car) {
        return carList.remove(car);
    }

}
