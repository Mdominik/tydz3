package pl.com.mazniak.tydz3.api;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
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
    }

    @GetMapping(value = "/all", produces = {MediaType.APPLICATION_XML_VALUE,
                                            MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<List<Car>> getAllCars() {
        return ResponseEntity.ok(listCars);
    }

    @GetMapping(value = "/{id:[0-9]+}", produces = {MediaType.APPLICATION_XML_VALUE,
            MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Car> getCarById(@PathVariable long id) {
        Optional<Car> first = listCars.stream().filter(car -> car.getId() == id).findFirst();
        if(first.isPresent()) {
            return ResponseEntity.ok(first.get());
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping(value = "/{color:[A-Za-z]+}", produces = {MediaType.APPLICATION_XML_VALUE,
            MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<List<Car>> getCarsByColor(@PathVariable String color) {
        List<Car> list = listCars.stream().filter(car -> car.getColor().toString().equals(color.toUpperCase())).collect(
                Collectors.toList());
        if(!list.isEmpty() && list != null) {
            return ResponseEntity.ok(list);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping(produces = {MediaType.APPLICATION_XML_VALUE,
            MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity addCar(@RequestBody Car car) {
        boolean added = listCars.add(car);
        return added ? new ResponseEntity(HttpStatus.CREATED) : new ResponseEntity(HttpStatus.BAD_REQUEST);
    }

    @PutMapping(produces = {MediaType.APPLICATION_XML_VALUE,
            MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity replaceCar(@RequestBody Car car) {
        Optional<Car> first = listCars.stream().filter(c -> c.getId() == car.getId()).findFirst();
        if(first.isPresent()) {
            listCars.remove(first.get());
            listCars.add(car);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

    @PatchMapping("/{id}")
    public ResponseEntity editCarById(@PathVariable long id, @RequestBody Car car) {
        Optional<Car> first = listCars.stream().filter(c -> c.getId() == id).findFirst();
        if(first.isPresent()) {
            if(car.getColor() != null) {
                first.get().setColor(car.getColor());
            }
            if(car.getMark() != null) {
                first.get().setMark(car.getMark());
            }

            if(car.getModel() != null) {
                first.get().setModel(car.getModel());
            }
            if(car.getId() != 0) {
                first.get().setId(car.getId());
            }
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteCarById(@PathVariable long id) {
        Optional<Car> first = listCars.stream().filter(c -> c.getId() == id).findFirst();
        if(first.isPresent()) {
            listCars.remove(first.get());
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}
