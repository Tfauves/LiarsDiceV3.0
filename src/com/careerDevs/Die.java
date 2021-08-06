package com.careerDevs;

import java.util.Random;

public class Die {
    int numberOfSides;
    int faceUpValue;


    public Die() {
        numberOfSides = 6;
    }

    public void roll() {
        Random dieValue = new Random();
        int maxSideValue = 6;
        int minSideValue =1;
        faceUpValue = dieValue.nextInt(maxSideValue) + minSideValue;
        }
}






