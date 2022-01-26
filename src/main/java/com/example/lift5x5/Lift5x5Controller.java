package com.example.lift5x5;

import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
public class Lift5x5Controller {
    @Autowired
    LiftsRepository repository;

    //GET ALL LIFTS
    @CrossOrigin
    @GetMapping("/lifts/all")
    ResponseEntity<List<Lift>> getAllLifts(){
        return ResponseEntity.status(HttpStatus.OK).body(repository.findAll());
    }

    //GET BY AN ID
    @CrossOrigin
    @GetMapping("/lifts/{id}")
    ResponseEntity <Lift> getLiftById(@PathVariable String id) {
        return ResponseEntity.status(HttpStatus.OK).body(repository.findByid(Integer.parseInt(id)));
    }

    //ADD A NEW LIFT
    @CrossOrigin
    @PostMapping("/lifts/create")
    ResponseEntity<String> addNewLift(@RequestBody Lift newLift){
        repository.save(newLift);
        return ResponseEntity.status(HttpStatus.OK).body("New lift " + newLift.getLiftType() + " with an id of "
                + newLift.getId() + " added.");
    }

//  //DELETE AN ITEM ON ITS ID
    @CrossOrigin
    @RequestMapping(method=RequestMethod.DELETE, value="/lifts/{id}")
    public ResponseEntity<String> deletebyId(@PathVariable("id") String id) {
        repository.deleteByid(Integer.parseInt(id));
        return ResponseEntity.status(HttpStatus.OK).body("Lift with ID " + id + " has been deleted");
    }

    //UPDATE THE METHODS WITH THE SPECIFIED VALUES
    @CrossOrigin
    @RequestMapping(method=RequestMethod.PUT, value="/lifts/{id}")
    public ResponseEntity<String> updateSpecific(@RequestBody Lift lift, @PathVariable("id")String id){
        Lift original = repository.findByid(Integer.parseInt(id));
        Lift updated = LiftsService.getUpdatedLift(lift, original);
        repository.save(updated);
        return ResponseEntity.status(HttpStatus.OK).body("Lift with ID " + id + " has been updated");
    }
}
