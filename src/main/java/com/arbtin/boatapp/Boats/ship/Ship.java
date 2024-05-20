package com.arbtin.boatapp.Boats.ship;

import com.arbtin.boatapp.Boats.captain.Operator;
import jakarta.persistence.*;

@Entity
public class Ship {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String hull;
    private String captain;

    private Integer capacity;

    @Enumerated(EnumType.STRING)
    private SpecialConfiguration special_configuration;

    @ManyToOne()
    @JoinColumn(name= "operator_id")
    private Operator operator_id;

    public Ship() {
    }

    public Ship(String hull, String captain, Integer capacity, SpecialConfiguration specialConfiguration) {
        this.hull = hull;
        this.captain = captain;
        this.capacity = capacity;
        this.special_configuration = specialConfiguration;
    }

    public Ship(String hull, String captain, Integer capacity, SpecialConfiguration specialConfiguration, Operator operator) {
        this.hull = hull;
        this.captain = captain;
        this.capacity = capacity;
        this.special_configuration = specialConfiguration;
        this.operator_id = operator;
    }

    public Long getId() {
        return id;
    }

    public String getHull() {
        return hull;
    }

    public String getCaptain() {
        return captain;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setHull(String hull) {
        this.hull = hull;
    }

    public void setOperator(String captain) {
        this.captain = captain;
    }

    public Integer getCapacity() { return capacity; }

    public void setCapacity(Integer capacity) { this.capacity = capacity; }

    public SpecialConfiguration getSpecial_configuration() { return special_configuration; }

    public void setSpecial_configuration(SpecialConfiguration special_configuration) { this.special_configuration = special_configuration; }

    public Operator getOperator_id() {
        return operator_id;
    }

    public void setOperator_id(Operator operator_id) {
        this.operator_id = operator_id;
    }

    @Override
    public String toString() {
        return "Ship{" +
                "id=" + id +
                ", hull='" + hull + '\'' +
                ", captain='" + captain + '\'' +
                '}';
    }
}
