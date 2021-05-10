/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.vanier.carbonemissionsapp.controllers;
import edu.vanier.carbonemissionsapp.helpers.ConnectionProvider;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Logger;
import java.util.logging.Level;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

/**
 *
 * @author Kevin
 */
public class UsersController {
    
    public static String currentUser = "";
    
    public UsersController() {
        
    }
    public boolean setUsers(TextField txtName, PasswordField pwfPassword, PasswordField pwfConfirm) {
        Connection dbConnection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        boolean flag = false;
        //HashMap<Integer, userModel> userList = new HashMap<>();

        String username = txtName.getText();
        String password = pwfPassword.getText();
        String confirmedPassword = pwfConfirm.getText();
        if(confirmedPassword.equals(password)){
        try{
            dbConnection = ConnectionProvider.getConnection("users.db");
            statement = dbConnection.createStatement();
            
            String sql = "INSERT INTO Users (username,password)" + "values('" + txtName.getText()
                    + "','" + pwfPassword.getText() + "')";
            int status = statement.executeUpdate(sql);
            if(status > 0)
                flag = true;
            else
                flag = false;
            
            
        } catch (SQLException e) {
            Logger.getLogger(UsersController.class.getName()).log(Level.SEVERE, null, e);
        }
        }
        else{
            flag = false;
        }
        return flag;
    }
    public boolean login(TextField username, PasswordField password){
        Connection dbConnection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        boolean flag = false;
        try{
            dbConnection = ConnectionProvider.getConnection("users.db");
            statement = dbConnection.createStatement();
            String query = "select * from Users where username = '" + username.getText()
                 + "'and password = '" + password.getText() + "'";
            resultSet = statement.executeQuery(query);
            if(resultSet.next()) {
                flag = true;
                UsersController.currentUser = username.getText();
            } else
                flag = false;
        }catch(SQLException e){
            Logger.getLogger(UsersController.class.getName()).log(Level.SEVERE, null, e);
        }
        
        return flag;
    }

}
