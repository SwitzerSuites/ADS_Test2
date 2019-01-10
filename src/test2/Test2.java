/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test2;

import java.util.Scanner;

/**
 *
 * @author Lucas
 * 
 * 
 */
public class Test2 {
    public static xmlConvert converter;
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //Get xml file path and run through converter class
        System.out.println("Enter xml filepath to convert: ");
        Scanner sc = new Scanner(System.in);
        String filePath = sc.next();
        converter = new xmlConvert(filePath);
    }
    
}
