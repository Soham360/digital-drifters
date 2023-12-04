package com.nighthawk.spring_portfolio.mvc.car;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;

@Data  // Annotations to simplify writing code (ie constructors, setters)
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    // email, password, roles are key attributes to login and authentication
    @Column(unique=true)
    private String name;

    private int topspeed;
    private int price;
    private int range;
    private int capacity;

    public Car(String name, int topspeed, int price, int range, int capacity) {
        this.name = name;
        this.topspeed = topspeed;
        this.price = price;
        this.range = range;
        this.capacity = capacity;
    }
    public String getName() {
        return name;
    }
    public void setName(String newName) {
        this.name = newName;
    }
    public int getTopspeed() {
        return topspeed;
    }
    public void setTopspeed(int newTopspeed) {
        this.topspeed = newTopspeed;
    }
    public int getPrice() {
        return price;
    }
    public void setPrice(int newPrice) {
        this.price = newPrice;
    }
    public int getRange() {
        return range;
    }
    public void setRange(int newRange) {
        this.range = newRange;
    }
    public int getCapacity() {
        return capacity;
    }
    public void setCapacity(int newCapacity) {
        this.capacity = newCapacity;
    }

    public String toString() {
        return "Cars [id=" + id + ", name=" + name + ", top speed=" + topspeed + ", price=" + price + ", range=" + range
                + ", capacity=" + capacity + "]";
    }

    public static Car[] init() {
        // basics of class construction
        Car p1 = new Car();
        p1.setName("BMW X7");
        p1.setTopspeed(130);
        p1.setPrice(567567);
        p1.setRange(567657);
        p1.setCapacity(567567);
        Car p2 = new Car();
        p2.setName("BMW 8");
        p2.setTopspeed(130);
        p2.setPrice(123123);
        p2.setRange(123123);
        p2.setCapacity(12312);
        Car p3 = new Car();
        p3.setName("BMW i7");
        p3.setTopspeed(130);
        p3.setPrice(123123);
        p3.setRange(123123);
        p3.setCapacity(12312);
        Car car[] = {p1, p2, p3};
        return(car);
    }
    public static void main(String[] args) {
        // obtain Car from initializer
        Car car[] = init();
        // iterate using "enhanced for loop"
        for( Car test : car) {
            System.out.println(test);  // print object
        }
    }
}