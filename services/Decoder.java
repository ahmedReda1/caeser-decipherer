/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import caeserutils.Configs;
import java.awt.List;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author wani
 */
public class Decoder {

    public int decode(String filePath) throws FileNotFoundException, IOException {
        FileReader inputStream = null;
        FileWriter outputStream = null;
        int[] appearences = new int[caeserutils.Configs.AlPHABETS_LENGTH];

        try {

            inputStream = new FileReader(filePath);
//            outputStream = new FileWriter(System.getProperty("user.home") + "/Desktop/test.txt");
            int c;

            // intaiating a zero array of 26 element to count appearences of each cahr 
            while ((c = inputStream.read()) != -1) {
                appearences = DecoderHelper.countAppearences(c, appearences);
//                for (int i = 0; i < appearences.length; i++) {
//                    System.out.println(appearences[i]);
//
//                }
//                System.exit(0);
            }

        } finally {
            if (inputStream != null) {
                inputStream.close();
            }
            if (outputStream != null) {
                outputStream.close();
            }
        }
        return DecoderHelper.guessCipherShift(appearences);
    }
}
