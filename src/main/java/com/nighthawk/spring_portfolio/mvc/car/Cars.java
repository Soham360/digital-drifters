package com.nighthawk.spring_portfolio.mvc.car;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Cars {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int steeringwheel;

    public String getCarInfo() {
        return "Car with ID: " + id + ", Steering Wheel: " + steeringwheel;
    }
}

@Entity
class BMW extends Cars {
    private int wheels;

    public String getBMWInfo() {
        return "BMW with ID: " + getId() + ", Wheels: " + wheels;
    }
}

@Entity
class BMW_X7 extends BMW {
    private String name = "BMW X7";
    private int price = 81900;
    private int capacity = 7;
    private double range = 547.5;
    private int topSpeed = 130;

    public String getBMW_X7Info() {
        return "BMW X7: " + getBMWInfo() + ", Name: " + name + ", Price: " + price + ", Capacity: " + capacity + ", Range: " + range + ", Top Speed: " + topSpeed;
    }
}

@Entity
class BMW_8 extends BMW {
    private String name = "BMW 8";
    private int price = 90800;
    private int capacity = 347;
    private double range = 5427.5;
    private int topSpeed = 130;

    public String getBMW_8Info() {
        return "BMW 8: " + getBMWInfo() + ", Name: " + name + ", Price: " + price + ", Capacity: " + capacity + ", Range: " + range + ", Top Speed: " + topSpeed;
    }
}

@Entity
class BMW_i7 extends BMW {
    private String name = "BMW i7";
    private int price = 105700;
    private int capacity = 23427;
    private double range = 542342347.5;
    private int topSpeed = 130;

    public String getBMW_i7Info() {
        return "BMW i7: " + getBMWInfo() + ", Name: " + name + ", Price: " + price + ", Capacity: " + capacity + ", Range: " + range + ", Top Speed: " + topSpeed;
    }
}
