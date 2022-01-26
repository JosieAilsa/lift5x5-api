package com.example.lift5x5;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface LiftsRepository extends JpaRepository <Lift,String> {
    //Where we would write custom methods
//Naming in the method must match the parameter
    Lift findByid(int id);

    @Transactional
    String deleteByid(int id);

}
