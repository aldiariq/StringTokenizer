/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Algoritma;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.ListIterator;
import java.util.Map;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.stream.Collectors;
import javax.swing.JOptionPane;

/**
 *
 * @author aldiariq
 */
public class StringToken {

    private String teks;
    private String[] arrayKata;
    private String[] removalString;
    private Map<String, Long> hasilTokenizing;

    public String getTeks() {
        return teks;
    }

    public void setTeks(String teks) {
        this.teks = teks.toLowerCase();
    }

    public Map<String, Long> getHasilTokenizing() {
        return hasilTokenizing;
    }

    public void setHasilTokenizing(Map<String, Long> hasilTokenizing) {
        this.hasilTokenizing = hasilTokenizing;
    }

    public Map<String, Long> prosesTokenizing(String teks) throws FileNotFoundException {
        StringTokenizer strTokens = new StringTokenizer(this.getTeks(), " \t\n\r\f,.:;?![]'/+()");

        ArrayList<String> tampungKatamentah = new ArrayList<String>();

        this.bacaremovalString();

        while (strTokens.hasMoreElements()) {
            tampungKatamentah.add(strTokens.nextToken());
        }

        for (int i = 0; i < this.removalString.length; i++) {
            tampungKatamentah.remove(this.removalString[i]);
        }

        Collections.sort(tampungKatamentah);

        this.arrayKata = new String[tampungKatamentah.size()];
        this.arrayKata = tampungKatamentah.toArray(new String[0]);

        Map<String, Long> hitungkatasama = tampungKatamentah.stream().collect(Collectors.groupingBy(e -> e, Collectors.counting()));
        
        this.setHasilTokenizing(hitungkatasama);

        return this.getHasilTokenizing();
    }

    public void bacaremovalString() throws FileNotFoundException {
        Scanner bacaTxt = new Scanner(new File("Pelengkap/stopword.txt"));

        ArrayList<String> tampungRemoval = new ArrayList<String>();

        while (bacaTxt.hasNext()) {
            tampungRemoval.add(bacaTxt.nextLine());
        }

        this.removalString = tampungRemoval.toArray(new String[0]);

        bacaTxt.close();
    }

}
