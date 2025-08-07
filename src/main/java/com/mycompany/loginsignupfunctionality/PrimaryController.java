package com.mycompany.loginsignupfunctionality;

import com.mycompany.loginsignupfunctionality.dao.Conn;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class PrimaryController {
    
    @FXML
    private Button changeBack;

    @FXML
    private PasswordField changeConfirmPassword;

    @FXML
    private AnchorPane changeForm;

    @FXML
    private PasswordField changePassword;

    @FXML
    private Button changeProceed;

    @FXML
    private TextField forgetAnswer;

    @FXML
    private Button forgetBack;

    @FXML
    private AnchorPane forgetForm;

    @FXML
    private Button forgetProceed;

    @FXML
    private ComboBox<String> forgetSelectQuestion;

    @FXML
    private TextField forgetUsername;

    @FXML
    private Button loginBtn;

    @FXML
    private Button loginCreateAccount;

    @FXML
    private Hyperlink loginForgetPassword;

    @FXML
    private AnchorPane loginForm;

    @FXML
    private PasswordField loginPassword;
    
    @FXML
    private TextField loginShowPassword;


    @FXML
    private CheckBox loginSelectShowPassword;

    @FXML
    private TextField loginUsername;

    @FXML
    private AnchorPane mainForm;

    @FXML
    private TextField signupAnswer;

    @FXML
    private Button signupBtn;

    @FXML
    private PasswordField signupConfirmPassword;

    @FXML
    private TextField signupEmail;

    @FXML
    private AnchorPane signupForm;

    @FXML
    private Button signupLoginAccount;
    
    @FXML
    private Button loginBack;

    @FXML
    private PasswordField signupPassword;

    @FXML
    private ComboBox<String> signupSelectQuestion;

    @FXML
    private TextField signupUsername;
    
    // databas set
    private Connection conn;
    private PreparedStatement ps;
    private ResultSet rs;
    
    public void chanePassword(){
        AlertMessage alert = new AlertMessage();
        if(Validation.checkEmptyString(changePassword.getText(),changeConfirmPassword.getText())){
            alert.errorMessage("please,fill all blank fields");
        }else if(!changePassword.getText().equals(changeConfirmPassword.getText())){
            alert.errorMessage("password doesn't match");
        }else if(changePassword.getText().length() < 8){
            alert.errorMessage("Invalid password, at least 8 characters needed");
        }else{
            String sql = "update users set password=?,update_date=? where username=?";
            try {            
                LocalDateTime updateDate = LocalDateTime.now();
                conn = Conn.getConnection();
                ps = conn.prepareStatement(sql);
                ps.setString(1,changePassword.getText());
                ps.setTimestamp(2, Timestamp.valueOf(updateDate));
                ps.setString(3, forgetUsername.getText());
                int updatedRows = ps.executeUpdate();
                if(updatedRows > 0){
                    alert.successMessage("Password Updated Successfully");
                    signupForm.setVisible(false);
                    loginForm.setVisible(true);
                    forgetForm.setVisible(false);
                    changeForm.setVisible(false);
                    
                    changePassword.setText("");
                    changeConfirmPassword.setText("");
                }
            } catch (SQLException ex) {
                System.getLogger(PrimaryController.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
            }
        }
    }
    
    public void register() throws SQLException{
        AlertMessage alertMessage = new AlertMessage();
        
        if (Validation.checkEmptyString(
            signupEmail.getText(),
            signupPassword.getText(),
            String.valueOf(signupSelectQuestion.getSelectionModel().getSelectedItem()),
            signupUsername.getText(),
            signupAnswer.getText(),
            signupConfirmPassword.getText()
        )) {
            alertMessage.errorMessage("Please fill in all fields!");
        }else if(!signupPassword.getText().equals(signupConfirmPassword.getText())){
            alertMessage.errorMessage("password doesn't match");
        }else if(signupPassword.getText().length() < 8){
            alertMessage.errorMessage("Invalid password, at least 8 characters needed");
        }else{
//            String checkUsername = "select * from users where username = '"
//                    + signupUsername.getText() + "'";
            String checkUsername = "select * from users where username = ?";
            try {
                conn = Conn.getConnection();
                ps = conn.prepareStatement(checkUsername);
                ps.setString(1, signupUsername.getText());
                rs = ps.executeQuery();
                if(rs.next()){
                    alertMessage.errorMessage(String.format("%s is already exist", signupUsername.getText()));
                }else{
                    String insertData = "insert into users(email,username,password,question,answer,date)"
                            + "values(?,?,?,?,?,?)";
                    ps = conn.prepareStatement(insertData);
                    ps.setString(1, signupEmail.getText());
                    ps.setString(2, signupUsername.getText());
                    ps.setString(3, signupPassword.getText());
                    ps.setString(4, String.valueOf(signupSelectQuestion.getSelectionModel().getSelectedItem()));
                    ps.setString(5, signupAnswer.getText());                    
                    
                    java.sql.Date nowSqlDate = new java.sql.Date(System.currentTimeMillis());
                    ps.setDate(6, nowSqlDate);

                    int updatedRows = ps.executeUpdate();
                    if(updatedRows > 0){
                        alertMessage.successMessage("Registered successfully");
                        registerClearFields();
                    }
                }
            } catch (SQLException ex) {
                System.getLogger(PrimaryController.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
            }finally{
                Conn.closeConnection(conn);
                ps.close();
                rs.close();
            }
        }   
    }
    
    public void forgetPassword(){
        AlertMessage alert = new AlertMessage();
        if(Validation.checkEmptyString(forgetUsername.getText()
            ,(String)forgetSelectQuestion.getSelectionModel().getSelectedItem(),forgetAnswer.getText())){
            alert.errorMessage("please fill all blank fields");
        }else{
            String sql = "select username,question,answer from users where username=?"
                    + "and question=? and answer=?";
            try {
                conn = Conn.getConnection();
                ps = conn.prepareStatement(sql);
                ps.setString(1, forgetUsername.getText());
                ps.setString(2, (String)forgetSelectQuestion.getSelectionModel().getSelectedItem());
                ps.setString(3, forgetAnswer.getText());
                rs = ps.executeQuery();
                if(rs.next()){
                    signupForm.setVisible(false);
                    loginForm.setVisible(false);        
                    forgetForm.setVisible(false);
                    changeForm.setVisible(true);
                }else{
                    alert.errorMessage("Incorrect information!");
                }
            } catch (SQLException ex) {
                System.getLogger(PrimaryController.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
            }
        }
    }
    
    public void showPassword(){
        if (loginSelectShowPassword.isSelected()){
            loginShowPassword.setVisible(true);
            loginShowPassword.setText(loginPassword.getText());
        }
        else{
            loginShowPassword.setVisible(false);
            loginShowPassword.setText(loginPassword.getText());
        }
    }
    
    @FXML
    public void login(ActionEvent event) throws IOException{
        AlertMessage alert = new AlertMessage();
        if(Validation.checkEmptyString(loginUsername.getText(),loginPassword.getText())){
            alert.errorMessage("please fill all fields");
        }else{
            String sql = "select * from users where username=? and password=?";
            try {
                conn = Conn.getConnection();
                ps = conn.prepareStatement(sql);
                ps.setString(1, loginUsername.getText());
                ps.setString(2, loginPassword.getText());
                rs = ps.executeQuery();
                if(rs.next()){
                    try {
                        alert.successMessage("you are logged successfully");
//                        Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                        App.setRoot("welcomeMessage");
//                        loginBtn.getScene().getWindow().hide();
                        // close current window

//                        currentStage.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                        alert.errorMessage("FXML file could not be loaded!");
                    }
                }else{
                    alert.errorMessage("Incorrect username or password");
                }
            } catch (SQLException ex) {
                System.getLogger(PrimaryController.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
            }
            
        }
    }
    
    private String []questionList = {
            "What is your favourite Language?", 
            "What is your favourite pet?", 
            "What is your favourite color?"};
    
    private void questions() {
        List<String> items = new ArrayList<>();
        for(String data:questionList){
            items.add(data);
        }

        ObservableList<String> list = FXCollections.observableArrayList(items);
        signupSelectQuestion.setItems(list);  // pass the ObservableList here
    }

    private void forgetListQuestions() {
        List<String> items = new ArrayList<>();
        for(String data:questionList){
            items.add(data);
        }

        ObservableList<String> list = FXCollections.observableArrayList(items);
        forgetSelectQuestion.setItems(list);  // pass the ObservableList here
    }
    
    @FXML
    public void initialize() {
        questions();
        forgetListQuestions();
    }

    @FXML
    public void registerClearFields(){
        signupEmail.setText("");
        signupUsername.setText("");
        signupPassword.setText("");
        signupConfirmPassword.setText("");
        signupSelectQuestion.getSelectionModel().clearSelection();
        signupAnswer.setText("");
    }

    @FXML
    public void switchForm(ActionEvent event) throws IOException {
        if (event.getSource() == signupLoginAccount || event.getSource() == forgetBack) {
            signupForm.setVisible(false);
            loginForm.setVisible(true);
            forgetForm.setVisible(false);
            changeForm.setVisible(false);
        } else if (event.getSource() == loginCreateAccount) {
            signupForm.setVisible(true);
            loginForm.setVisible(false);        
            forgetForm.setVisible(false);
            changeForm.setVisible(false);
        } else if (event.getSource() == loginForgetPassword || event.getSource() == changeBack) {
            signupForm.setVisible(false);
            loginForm.setVisible(false);        
            forgetForm.setVisible(true);
            changeForm.setVisible(false);
            
            forgetListQuestions();
        }else if(event.getSource() == loginBack){
            App.setRoot("primary");
        }
    }

}
