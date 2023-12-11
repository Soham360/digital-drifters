package com.nighthawk.spring_portfolio.mvc.car;

import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.security.core.authority.SimpleGrantedAuthority;
// import org.springframework.security.core.userdetails.UserDetailsService;
// import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
// import org.springframework.security.crypto.password.PasswordEncoder;
// import java.util.ArrayList;
// import java.util.Collection;
import java.util.List;

/*
This class has an instance of Java Persistence API (JPA)
-- @Autowired annotation. Allows Spring to resolve and inject collaborating beans into our bean.
-- Spring Data JPA will generate a proxy instance
-- Below are some CRUD methods that we can use with our database
*/
@Service
@Transactional
public class CarDetailsService {  // "implements" ties ModelRepo to Spring Security
    // Encapsulate many object into a single Bean (Activities, Roles, and Scrum)
    @Autowired  // Inject ActivitiesJpaRepository
    public CarJpaRepository carJpaRepository;

    /* UserDetailsService Overrides and maps Activities & Roles POJO into Spring Security */

    /* Activities Section */

    public  List<Car>listAll() {
        return carJpaRepository.findAllByOrderByNameAsc();
    }

    // custom query to find match to name or email
    public  List<Car>list(String name) {
        return carJpaRepository.findByNameIgnoreCase(name);
    }

    // custom query to find anything containing term in name or email ignoring case
    public  List<Car>listLike(String term) {
        return carJpaRepository.findByNameIgnoreCase(term);
    }

    public void save(Car car) {
        carJpaRepository.save(car);
    }

    public Car get(long id) {
        return (carJpaRepository.findById(id).isPresent())
        ? carJpaRepository.findById(id).get()
        : null;
    }

    public Car getByName(String name) {
        return (carJpaRepository.findByName(name));
    }

    public Car getByPrice(int price) {
        return (carJpaRepository.findByPrice(price));
    }
    
}