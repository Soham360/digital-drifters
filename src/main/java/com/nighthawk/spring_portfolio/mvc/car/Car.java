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
        p1.setTopspeed(155);
        p1.setPrice(78800);
        p1.setRange(482);
        p1.setCapacity(7);
        Car p2 = new Car();
        p2.setName("BMW 8");
        p2.setTopspeed(155);
        p2.setPrice(100500);
        p2.setRange(370);
        p2.setCapacity(4);
        Car p3 = new Car();
        p3.setName("BMW i7");
        p3.setTopspeed(155);
        p3.setPrice(105700);
        p3.setRange(321);
        p3.setCapacity(5);
        Car p4 = new Car();
        p4.setName("Tesla 3");
        p4.setTopspeed(140);
        p4.setPrice(38900);
        p4.setRange(333);
        p4.setCapacity(5);
        Car p5 = new Car();
        p5.setName("Tesla S");
        p5.setTopspeed(162);
        p5.setPrice(74900);
        p5.setRange(405);
        p5.setCapacity(5);
        Car p6 = new Car();
        p6.setName("Tesla X");
        p6.setTopspeed(155);
        p6.setPrice(81000);
        p6.setRange(348);
        p6.setCapacity(7);
        Car p7 = new Car();
        p7.setName("Toyota Camry");
        p7.setTopspeed(60);
        p7.setPrice(29800);
        p7.setRange(440);
        p7.setCapacity(5);
        Car p8 = new Car();
        p8.setName("Toyota Sienna");
        p8.setTopspeed(88);
        p8.setPrice(79800);
        p8.setRange(328);
        p8.setCapacity(7);
        Car p9 = new Car();
        p9.setName("Toyota Prius");
        p9.setTopspeed(90);
        p9.setPrice(79900);
        p9.setRange(369);
        p9.setCapacity(5);
        Car p10 = new Car();
        p10.setName("Honda CRV");
        p10.setTopspeed(127);
        p10.setPrice(29400);
        p10.setRange(420);
        p10.setCapacity(5);
        Car p11 = new Car();
        p11.setName("Honda Accord");
        p11.setTopspeed(88);
        p11.setPrice(32500);
        p11.setRange(450);
        p11.setCapacity(5);
        Car p12 = new Car();
        p12.setName("Honda Odyssey");
        p12.setTopspeed(90);
        p12.setPrice(39900);
        p12.setRange(369);
        p12.setCapacity(7);
        Car car[] = {p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11, p12};
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