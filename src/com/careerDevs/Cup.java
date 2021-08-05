package com.careerDevs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Cup {
    public List<Die> dice = new ArrayList<>();

    public Cup() {
        while (dice.size() < 5) {
            dice.add(new Die());
        }
    }

    public void roll() {

        for (Die die : dice) {
            die.roll();
        }
    }


    public String displayHand() {

        String hand = " ";
        for (Die die : dice) {
            hand += die.faceUpValue + " ";
        }
        return hand.trim();
    }



}
