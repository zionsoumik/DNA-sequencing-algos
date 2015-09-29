package co.syntx.examples.cqengine;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.Random;

/**
 *
 * @author Soumik
 */
public class random {

    //...
public char[] randomgenerate(int length) {
    Random r = new Random();
    char[] s = null;
    String alphabet;
    alphabet = "ATGC";
    for (int i = 0; i < length; i++) {
        s[i]=alphabet.charAt(r.nextInt(alphabet.length()));
    } // prints 50 random characters from alphabet
    return s;
}
}
