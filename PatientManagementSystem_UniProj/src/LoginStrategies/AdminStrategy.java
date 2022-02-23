/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LoginStrategies;

import Controllers.AdministratorController;
import Controllers.LoginController;
import GUIUpdateObserver.GUIUpdate;
import UserModel.UserFacade;
import UserModel.UserTypes;
import View.AdminGUI;



/**
 * Strategy to load the AdminUser MVC. Implements ILoginStrategy methods.
 * @author Glenn McKnight
 */
public final class AdminStrategy implements ILoginStrategy, IRegisterErrorHandling{

    private AdministratorController controller;
    private AdminGUI view;
    
    /**
     * Constructor calls loadUser()
     */
    public AdminStrategy(){
        loadUser();
    }
    
    /**
     * Calls methods to construct the strategy algorithm
     */
    @Override
    public void loadUser(){
         try{
            setController();
            setModel();
            setView();
            addUpdateObserver();
        }catch(NullPointerException ex ){
            System.out.println(ex);
        }
    }
    
    @Override
    public void setController() {
        controller = AdministratorController.getInstance();
    }

    @Override
    public void setModel() {
        controller.setModel();        
    }
    
    @Override
    public void setView() {
        view = new AdminGUI();
        view.setVisible(true);
        controller.setView(view);
    }
    
    @Override
    public void addUpdateObserver() {
         GUIUpdate.getInstance().updateObserver(controller);
    }
}
