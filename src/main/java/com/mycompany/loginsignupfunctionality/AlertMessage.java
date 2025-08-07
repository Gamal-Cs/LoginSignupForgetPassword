/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.loginsignupfunctionality;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

/**
 *
 * @author agamal
 */
public class AlertMessage {
    
    private Alert alert;
    
    public void errorMessage(String msg){
        alert = new Alert(AlertType.ERROR);
        alert.setHeaderText(null);
        alert.setTitle("error message");
        alert.setContentText(msg);
        alert.showAndWait();
    }
    
    public void successMessage(String msg){
        alert = new Alert(AlertType.INFORMATION);
        alert.setHeaderText(null);
        alert.setTitle("Information message");
        alert.setContentText(msg);
        alert.showAndWait();
    }
}
