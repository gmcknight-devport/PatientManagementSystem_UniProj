/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LoginStrategies;

import Controllers.*;
import UserModel.UserFacade;
import UserModel.UserTypes;
import View.LoginGUI;

/**
 *
 * @author Glenn McKnight
 */
public final class LoggedOutStrategy implements ILoginStrategy {
   
    
    private LoginController controller;    
    private LoginGUI view;
    private UserFacade userModel;
    
    /**
     * Constructor calls loadUser()
     */
    public LoggedOutStrategy(){
        loadUser();
    }
    
    /**
     * Calls methods to construct the strategy algorithm
     */
    @Override
    public void loadUser(){
        setController();
        setModel();  
        setView();
    }
    
    @Override
    public void setController() {
        controller = LoginController.getInstance();
    }

    @Override
    public void setModel() {
        controller.setModel();        
    }
    
    @Override
    public void setView() {
        
        view = new LoginGUI();            
        view.setVisible(true);
        controller.setView(view);
        
        controller.addButtonHandler();
    }      
}
