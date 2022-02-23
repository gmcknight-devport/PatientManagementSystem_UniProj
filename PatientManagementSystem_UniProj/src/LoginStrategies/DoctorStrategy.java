/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LoginStrategies;

import Controllers.DoctorController;
import Controllers.LoginController;
import GUIUpdateObserver.GUIUpdate;
import UserModel.UserFacade;
import View.DoctorGUI;

/**
 * Strategy to load the DoctorUser MVC. Implements ILoginStrategy methods.
 * @author Glenn McKnight
 */
public final class DoctorStrategy implements ILoginStrategy, IRegisterErrorHandling {
    
    private DoctorController controller;
    private DoctorGUI view;
    private UserFacade userModel;

    /**
     * Constructor calls loadUser()
     */
    public DoctorStrategy(){
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
        controller = DoctorController.getInstance();
    }

    @Override
    public void setModel() {
        controller.setModel();        
    }

    @Override
    public void setView() {
        view = new DoctorGUI();
        view.setVisible(true);        
        controller.setView(view);
    }   
    
    @Override
    public void addUpdateObserver() {
         GUIUpdate.getInstance().updateObserver(controller);
    }
}
