/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author wani
 */
public class Encoder {

    /**
     * This method is meant to read user input and encode alphabetical
     * characters and ignore symbols using ASCII codes
     *
     * @param filePath path of user input
     * @param shiftValue cipher shift key
     * @throws FileNotFoundException
     * @throws IOException
     */
    public void encode(String filePath, int shiftValue) throws FileNotFoundException, IOException {
        FileReader inputStream = null;
        FileWriter outputStream = null;

        try {
            inputStream = new FileReader(filePath);
            outputStream = new FileWriter(System.getProperty("user.home") + "/Documents/0/output.txt");
            int c;
            while ((c = inputStream.read()) != -1) {
                // skipping all but chars
                if ((caeserutils.Configs.CAPITAL_START_INDEX <= c && c <= caeserutils.Configs.CAPITAL_END_INDEX)
                        || (caeserutils.Configs.SMALL_START_INDEX <= c && c <= caeserutils.Configs.SMALL_END_INDEX)) {
                    c = this.cShifter(c, shiftValue);
                }
                outputStream.write(c);
            }
        } finally {
            if (inputStream != null) {
                inputStream.close();
            }
            if (outputStream != null) {
                outputStream.close();
            }
        }
    }

    /**
     * This method is meant to do the shifting process whenever the character is
     * CAPITAL or small case
     *
     * @param c ASCII of a char
     * @param shiftValue cipher shift key
     * @return
     */
    private int cShifter(int c, int shiftValue) {
        // dummy capital limits
        int end = caeserutils.Configs.CAPITAL_END_INDEX;

        if (c > caeserutils.Configs.CAPITAL_END_INDEX) {
            end = caeserutils.Configs.SMALL_END_INDEX;
        }
        if (c + shiftValue > end) {
            c -= (caeserutils.Configs.AlPHABETS_LENGTH - shiftValue);
        } else {
            c += shiftValue;
        }
        return c;
    }

}
