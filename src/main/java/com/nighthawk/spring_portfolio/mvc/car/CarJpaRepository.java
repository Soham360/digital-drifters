package com.nighthawk.spring_portfolio.mvc.car;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/*
Extends the JpaRepository interface from Spring Data JPA.
-- Java Persistent API (JPA) - Hibernate: map, store, update and retrieve database
-- JpaRepository defines standard CRUD methods
-- Via JPA the developer can retrieve database from relational databases to Java objects and vice versa.
 */
public interface CarJpaRepository extends JpaRepository<Car, Long> {
    Car findByName(String name);
    List<Car> findAllByOrderByNameAsc();
    List<Car> findByNameIgnoreCase(String name);
    Car findByPrice(int price);
    List<Car> findAllByOrderByPriceAsc();
    
    @Query(
            value = "SELECT * FROM Car p WHERE p.name LIKE ?1",
            nativeQuery = true)
    List<Car> findByLikeTermNative(String term);


}