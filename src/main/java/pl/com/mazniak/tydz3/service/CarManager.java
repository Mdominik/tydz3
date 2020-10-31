package pl.com.mazniak.tydz3.service;

import org.springframework.stereotype.Service;
import pl.com.mazniak.tydz3.model.Car;
import pl.com.mazniak.tydz3.model.CarDTO;
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

        carList.add(new Car("VW", "Qasd7", Color.RED));
        carList.add(new Car("Voyager", "iasd3", Color.BLUE));
        carList.add(new Car("Hoasda", "Ciasdvic", Color.MAGENTA));

        carList.add(new Car("Auasdai", "Qasd7", Color.MAGENTA));
        carList.add(new Car("BMasddasW", "iads3", Color.BLUE));
        carList.add(new Car("asdsaHonda", "Casdivic", Color.RED));
        carList.add(new Car("Auasdaddi", "Qasd7", Color.RED));
        carList.add(new Car("BasddaMW", "iasd3", Color.BLUE));
        carList.add(new Car("Hadsdanda", "Casdivic", Color.RED));

        carList.add(new Car("adAudi", "Qasd7", Color.MAGENTA));
        carList.add(new Car("BMasdW", "isad3", Color.BLUE));
        carList.add(new Car("Hoasdnda", "Casdivic", Color.RED));

        carList.add(new Car("Auasddi", "Qasd7", Color.BLACK));
        carList.add(new Car("BMasdW", "iasd3", Color.BLUE));
        carList.add(new Car("Hoasdasdnda", "Casdivic", Color.MAGENTA));
        carList.add(new Car("Audasdi", "Qasd7", Color.BLACK));
        carList.add(new Car("BMsadW", "iasd3", Color.RED));
        carList.add(new Car("Hoasdnda", "Casdvic", Color.MAGENTA));



    }

    public boolean addCarDTO(CarDTO carDTO) {
        return carList.add(new Car(carDTO));
    }
    public boolean addCar(Car car) {
        return carList.add(car);
    }
    public boolean removeCar(Car car) {
        return carList.remove(car);
    }

}
