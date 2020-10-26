package pl.com.mazniak.tydz3.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.com.mazniak.tydz3.model.Car;
import pl.com.mazniak.tydz3.model.Color;
import pl.com.mazniak.tydz3.service.CarManager;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/cars")
public class CarsApi {
    CarManager carManager;

    @Autowired
    public CarsApi(CarManager carManager) {
        this.carManager = carManager;
    }

    @GetMapping(value = "/all", produces = {MediaType.APPLICATION_XML_VALUE,
                                            MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<List<Car>> getAllCars() {
        return ResponseEntity.ok(carManager.getCarList());
    }

    @GetMapping(value = "/{id:[0-9]+}", produces = {MediaType.APPLICATION_XML_VALUE,
            MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Car> getCarById(@PathVariable long id) {
        Optional<Car> first = carManager.getCarList().stream().filter(car -> car.getId() == id).findFirst();
        if(first.isPresent()) {
            return ResponseEntity.ok(first.get());
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping(value = "/{color:[A-Za-z]+}", produces = {MediaType.APPLICATION_XML_VALUE,
            MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<List<Car>> getCarsByColor(@PathVariable String color) {
        List<Car> list = carManager.getCarList().stream().filter(car -> car.getColor().toString().equals(color.toUpperCase())).collect(
                Collectors.toList());
        if(!list.isEmpty() && list != null) {
            return ResponseEntity.ok(list);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping(produces = {MediaType.APPLICATION_XML_VALUE,
            MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity addCar(@RequestBody Car car) {
        boolean added = carManager.addCar(car);
        return added ? new ResponseEntity(HttpStatus.CREATED) : new ResponseEntity(HttpStatus.BAD_REQUEST);
    }

    @PutMapping(produces = {MediaType.APPLICATION_XML_VALUE,
            MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity replaceCar(@RequestBody Car car) {
        Optional<Car> first = carManager.getCarList().stream().filter(c -> c.getId() == car.getId()).findFirst();
        if(first.isPresent()) {
            carManager.removeCar(first.get());
            carManager.addCar(car);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

    @PatchMapping("/{id}")
    public ResponseEntity editCarById(@PathVariable long id, @RequestBody Car car) {
        Optional<Car> first = carManager.getCarList().stream().filter(c -> c.getId() == id).findFirst();
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
        Optional<Car> first = carManager.getCarList().stream().filter(c -> c.getId() == id).findFirst();
        if(first.isPresent()) {
            carManager.removeCar(first.get());
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}
