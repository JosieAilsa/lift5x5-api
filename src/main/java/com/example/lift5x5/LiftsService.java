package com.example.lift5x5;

import org.springframework.stereotype.Service;

@Service
public class LiftsService {
   public static Lift getUpdatedLift(Lift update, Lift original) {
       original.setLiftType(update.getLiftType());
       original.setWeight(update.getWeight());
       original.setDifficulty(update.getDifficulty());
       return original;
   }
}
