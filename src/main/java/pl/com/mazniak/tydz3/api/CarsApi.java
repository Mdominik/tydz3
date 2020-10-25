package pl.com.mazniak.tydz3.api;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.com.mazniak.tydz3.model.Car;
import pl.com.mazniak.tydz3.model.Color;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/cars")
public class CarsApi {
    List<Car> listCars;

    public CarsApi() {
        listCars = new LinkedList<>();
        listCars.add(new Car(1L, "Audi", "Q7", Color.BLACK));
        listCars.add(new Car(2L, "BMW", "i3", Color.BLUE));
        listCars.add(new Car(3L, "Honda", "Civic", Color.RED));
        listCars.add(new Car(4L, "Polonez", "Caro", Color.BROWN));

    }

    @GetMapping("/all")
    public ResponseEntity<List<Car>> getAllCars() {
        return new ResponseEntity<>(listCars, HttpStatus.OK);
    }

    @GetMapping("/{id:[0-9]+}")
    public ResponseEntity<Car> getCarById(@PathVariable long id) {
        Optional<Car> first = listCars.stream().filter(car -> car.getId() == id).findFirst();
        if(first.isPresent()) {
            return new ResponseEntity<>(first.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/{color:[A-Za-z]+}")
    public ResponseEntity<List<Car>> getCarByColor(@PathVariable String color) {
        List<Car> list = listCars.stream().filter(car -> car.getColor().toString().equals(color.toUpperCase())).collect(
                Collectors.toList());
        if(!list.isEmpty() && list != null) {
            return new ResponseEntity<>(list, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    
}
