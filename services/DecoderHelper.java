/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

/**
 *
 * @author wani
 */
public class DecoderHelper {

    static int[] countAppearences(int c, int[] appearences) {
        // only small chars
        if ((97 <= c && c <= 122)) {
            appearences[c - caeserutils.Configs.SMALL_START_INDEX]++;
        } /**
         * only capital chars , counting all chars as small even capital ones so
         * shifting capital ASCII with 32
         */
        else if ((65 <= c && c <= 90)) {
            appearences[c + caeserutils.Configs.CAPITAL_SMALL_SHIFT - caeserutils.Configs.SMALL_START_INDEX]++;
        }
        return appearences;
    }

    static int guessCipherShift(int[] appearences) {
        // list of correlation coefficient values for 26 shifts
        double[] ccs = new double[caeserutils.Configs.AlPHABETS_LENGTH];
        for (int i = 0; i < caeserutils.Configs.AlPHABETS_LENGTH; i++) {
            ccs[i] = shiftAndCalculate(i, appearences);
        }
        for (int i = 0; i < caeserutils.Configs.AlPHABETS_LENGTH; i++) {
            System.out.println(ccs[i]);
        }

        return getBestCorrelation(ccs);
    }

    private static double shiftAndCalculate(int i, int[] appearences) {
        appearences = shiftArray(i, appearences);
        CorrelationCoefficient cc = new CorrelationCoefficient(appearences);
        return cc.getCorrCoeff();
    }

    private static int[] shiftArray(int shift, int[] appearences) {
        if (shift > 0) {
            for (int j = 1; j <= shift; j++) {
                int temp = appearences[0];
                for (int i = 0; i < caeserutils.Configs.AlPHABETS_LENGTH - 1; i++) {
                    appearences[i] = appearences[i + 1];
                }
                appearences[caeserutils.Configs.AlPHABETS_LENGTH - 1] = temp;
            }
        }
        return appearences;
    }

    private static int getBestCorrelation(double[] ccs) {
        int maxIndex = 0;
        for (int i = 1; i < ccs.length; i++) {
            double newnumber = ccs[i];
            if ((newnumber > ccs[maxIndex])) {
                maxIndex = i;
            }
        }
        return maxIndex;
    }

}
