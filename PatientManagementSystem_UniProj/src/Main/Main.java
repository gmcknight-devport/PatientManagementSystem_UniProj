/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import Controllers.LoginController;
import SystemServices.ServicesFacade;
import UserModel.UserFacade;
import View.LoginGUI;

/**
 *
 * @author Glenn McKnight
 */
public class Main {

    public static void main(String[] args) {
        
        runSerializedProject();
    }
    
    /**
     * Creates and initialises all required parts of the loginMVC subsystem
     */
    private static void runSerializedProject(){
        
        LoginGUI view = new LoginGUI();
        LoginController controller = LoginController.getInstance();
        
        controller.setModel();
        controller.setView(view);
        controller.addButtonHandler();
      
        view.setVisible(true);      
    }    
}
