/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LoginStrategiesModel;

import Controllers.LoginController;
import Controllers.SecretaryController;
import GUIUpdateObserver.GUIUpdate;
import UserModel.UserFacade;
import View.SecretaryGUI;

/**
 * Strategy to load the SecretaryUser MVC. Implements ILoginStrategy methods.
 * @author Glenn McKnight
 */
public final class SecretaryStrategy implements ILoginStrategy, IRegisterErrorHandling{
    
    private SecretaryController controller;
    private SecretaryGUI view;
    private UserFacade userModel;
    
    /**
     * Constructor calls loadUser()
     */
    public SecretaryStrategy(){
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
        controller = SecretaryController.getInstance();
    }

    @Override
    public void setModel() {
        controller.setModel();        
    }
    
    @Override
    public void setView() {
        view = new SecretaryGUI();
        view.setVisible(true);
        controller.setView(view);       
    }       
        
    @Override
    public void addUpdateObserver() {
         GUIUpdate.getInstance().updateObserver(controller);
    }
}
