/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import java.lang.Math;

/**
 *
 * @author wani
 */
public class CorrelationCoefficient {

    /**
     * This is array of the probability of every English char (X)
     */
    private final int[] probs = {3312, 573, 1568, 1602, 6192, 966, 769, 1869, 2943, 119, 206, 1579, 1500, 2982, 3261, 1074, 116, 2716, 3072, 4358, 1329, 512, 748, 123, 727, 16};
    /**
     * This is array of appearance percentage of every English char in our
     * decrypted text (Y)
     */
    private int[] appearances;
    /**
     * Value of correlation coefficient
     */
    private double corrCoeff;

    /**
     * constructor
     *
     * @param textAppearances
     */
    public CorrelationCoefficient(int[] textAppearances) {
        this.appearances = textAppearances;
    }

    /**
     * returns the correlation coefficient
     *
     * @return double correlation coefficient
     */
    public double getCorrCoeff() {
        return this.calculateCorrCoeff();
    }

    /**
     * calculates the correlation coefficient
     *
     * @return double
     */
    private double calculateCorrCoeff() {
        double top = ((caeserutils.Configs.AlPHABETS_LENGTH * this.getSegmaXY()) - (this.getSegmaX() * this.getSegmaY()));
        double bottom = Math.sqrt((caeserutils.Configs.AlPHABETS_LENGTH * this.getSegmaXSquered()) - Math.pow(this.getSegmaX(), 2)) * Math.sqrt((caeserutils.Configs.AlPHABETS_LENGTH * this.getSegmaYSquered()) - Math.pow(this.getSegmaY(), 2));
        this.corrCoeff = top / bottom;
        return this.corrCoeff;
    }

    private double getSegmaXY() {
        double segmaXY = 0.0;
        for (int i = 0; i < this.probs.length; i++) {
            segmaXY += (this.probs[i] * this.appearances[i]);
        }
        return segmaXY;
    }

    private double getSegmaX() {
        double segmaX = 0.0;
        for (int i = 0; i < this.probs.length; i++) {
            segmaX += this.probs[i];
        }
        return segmaX;
    }

    private double getSegmaY() {
        double segmaY = 0.0;
        for (int i = 0; i < this.probs.length; i++) {
            segmaY += this.appearances[i];
        }
        return segmaY;
    }

    private double getSegmaXSquered() {
        double segmaXSquered = 0.0;
        for (int i = 0; i < this.probs.length; i++) {
            segmaXSquered += Math.pow(this.probs[i], 2);
        }
        return segmaXSquered;
    }

    private double getSegmaYSquered() {
        double segmaYSquered = 0.0;
        for (int i = 0; i < this.probs.length; i++) {
            segmaYSquered += Math.pow(this.appearances[i], 2);
        }
        return segmaYSquered;
    }

}
