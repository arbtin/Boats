package com.arbtin.boatapp.Boats.captain;

import com.arbtin.boatapp.Boats.ship.Ship;
import jakarta.persistence.*;

import java.util.List;

@Entity
public class Operator {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @OneToMany(mappedBy = "operator_id", fetch = FetchType.EAGER)
    private List<Ship> ship_id;


    public Operator() {
    }

    public Operator(String name) {
        this.name = name;
    }

    public Operator(Long id, String name, List<Ship> ship_id) {
        this.id = id;
        this.name = name;
        this.ship_id = ship_id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Ship> getShip() {
        return ship_id;
    }

    public void setShip(List<Ship> ship_id) {
        this.ship_id = ship_id;
    }

    @Override
    public String toString() {
        return "Operator{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", ship_id=" + ship_id +
                '}';
    }
}