package com.arbtin.boatapp.Boats.ship;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/ship")
public class ShipController {
    private final ShipRepository shipRepository;

    public ShipController(ShipRepository shipRepository) {
        this.shipRepository = shipRepository;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Ship createShip(@RequestBody Ship ship) {
        return shipRepository.save(ship);
    }

    @GetMapping
    public List<Ship> getAllShip() {
        return shipRepository.findAll();
    }

    @GetMapping("/{id}")
    public Ship getShipById(@PathVariable Long id) {
        return shipRepository.findById(id).orElseThrow();
    }
}