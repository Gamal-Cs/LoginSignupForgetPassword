/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.loginsignupfunctionality;

/**
 *
 * @author agamal
 */
public class Validation {
    
    public static boolean checkEmptyString(String... inputs) {
        if (inputs == null || inputs.length <= 0) return true;  // Consider empty input as "empty"

        for (String input : inputs) {
            if (input == null || input.trim().isEmpty()) {
                return true;  // Found an empty or null string
            }
        }
        return false;  // None are empty
    }
}
