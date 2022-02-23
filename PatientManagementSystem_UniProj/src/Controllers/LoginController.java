/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import LoginStrategies.StrategySelect;
import UserModel.UserFacade;
import View.LoginGUI;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 *
 * @author Glenn McKnight
 */
public class LoginController implements IController{

    private static volatile LoginController loginControllerInstance;
    private UserFacade userModel;
    private LoginGUI view;
    private final StrategySelect strategy;
    
    private LoginController(){
        if (loginControllerInstance != null){
            throw new RuntimeException("This class is a singleton, use getInstance() to access");
        }
        strategy = new StrategySelect();
    }
        
    /**
     * Returns a singleton instance of this controller. Creates a new instance
     * if one doesn't already exist.
     * @return loginControllerInstance - an instance of this controller
     */
    public static LoginController getInstance(){
        if(loginControllerInstance == null){
            synchronized (LoginController.class) {
                loginControllerInstance = new LoginController();
            }
        }
        
        return loginControllerInstance;
    }    
    
    /**
     * Assigns LoginGUI to the view variable
     * @param view LoginGUI view 
     */
    public void setView(LoginGUI view) {
       this.view = view;     
       addWindowCloseHandler();
    }    
        
    @Override
    public void setModel() {
        userModel = UserFacade.getInstance();    
    }
    
    @Override
    public void disposeView(){
        view.dispose();
    }
    
    @Override
    public boolean hasView() {
        if(view != null){
            return true;
        }else{
            return false;
        }
    }
    
    /**
     * Add listeners to the Login and Sign up buttons of the view by 
     * instantiating new inner classes: LoginListener, and SignupListener.
     */
    @Override
    public void addButtonHandler(){
        view.addLoginButtonHandler(new LoginListener());
        view.addSignupButtonHandler(new SignupListener());
    }  
    
    @Override
    public void addWindowCloseHandler(){
        view.addWindowListener(new WindowClosedListener());
    }
    /**
     * Get userType from the model and call selectStrategy method to set a new 
     * MVC subsystem for a specified user after login has been successful.
     */
    private void setStrategy() {
        String userType;
        userType = userModel.getLoggedInUser();
        
        strategy.selectStrategy(userType);
    }
    
    /**
     * Inner class to handle event in view for the Login Button. Implements 
     * action listener to check for button click. 
     */
    class LoginListener implements ActionListener{

        /**
         * Gets userID and password from view, ensures they aren't null and 
         * calls CheckLogin method. Calls view method displayMessage if
         * null check fails.
         * @param e actionEvent - login button click
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            
            String userID, password;
                      
            userID = view.getUserID();
            password = view.getLoginPassword();
             
            if(!"".equals(userID) && !"".equals(password)){
                checkDetails(userID, password); 
            }else{
                view.displayMessage("Enter a username and password");
            }
        }
        
        /**
         * Calls model method to check login details. Calls setStrategy() method 
         * if successful and calls view displayMessage() if it fails.
         * @param userID
         * @param password 
         */
        private void checkDetails(String userID, String password){
            
            if(userModel.checkLoginCredentials(userID, password)){                    
                    
                    setStrategy();
                    disposeView();
                    
            }else{
                view.displayMessage("Login details incorrect");
                view.clearFields();
            }
        }
    }
    
    /**
     * Inner class to handle event for the signup button of view. Implements
     * action listener to check for button click.
     */
    class SignupListener implements ActionListener{

        /**
         *
         * @param e actionEvent - signup button click
         */
        @Override
        public void actionPerformed(ActionEvent e) {
                        
            try{    
                userModel.signupRequest(view.getSignupPassword(), view.getUserTitle(),
                        view.getForename(), view.getSurname(), view.getAge(),
                        view.getAddress());
                
                view.displayMessage("Signup request received");                
                view.clearFields();
                
            }catch(NullPointerException ex){                
                view.displayMessage("Please enter a value in each field");
            }
        }        
    }    
    
    class WindowClosedListener extends WindowAdapter{
        
        @Override
        public void windowClosing(WindowEvent e) {
            System.out.println("Should be closed");
        }
    }
    
}
