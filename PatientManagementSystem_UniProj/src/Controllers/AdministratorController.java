/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import UserModel.UserFacade;
import View.AdminGUI;
import LoginStrategies.StrategySelect;
import UserModel.UserTypes;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import GUIUpdateObserver.GUIUpdateObserver;

/**
 * The controller for the AdminGUI and it's interaction with the UserFacade model.
 * Implements IController interface methods for setting up the controller.
 * @author Glenn McKnight
 */
public class AdministratorController implements IController,
        ISetLogoutStrategy, GUIUpdateObserver {

    private static volatile AdministratorController adminControllerInstance;
    private UserFacade userModel;
    private AdminGUI view;
    private final StrategySelect strategy;
    
    private AdministratorController(){
        if (adminControllerInstance != null){
            throw new RuntimeException("This class is a singleton, use getInstance() to access");
        }
        strategy = new StrategySelect();
    }
    
    /**
     * Returns a singleton instance of this controller. Creates a new instance
     * if one doesn't already exist.
     * @return adminControllerInstance - an instance of this controller
     */
    public static AdministratorController getInstance(){
        if(adminControllerInstance == null){
            synchronized (AdministratorController.class) {
                adminControllerInstance = new AdministratorController();
            }
        }
        
        return adminControllerInstance;
    }
    
    /**
     * Assigns AdminGUI to the view variable
     * @param view AdminGUI view 
     */
    public void setView(AdminGUI view) {
        this.view = view;
        setAdminInfo();
        setAdminMessages();
        setDoctorCombo();
        setSecCombo();
        setAdminCombo();
        addButtonHandler();
        addWindowCloseHandler();
        setLogoutHandler();
        setDocRatings();
    }
        
    @Override    
    public void setModel() {
        this.userModel = UserFacade.getInstance(); 
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

    @Override
    public void addButtonHandler() {
        view.addCreateUserButtonHandler(new CreateUserButtonListener());
        view.addRemoveDoctorButtonHandler(new DeleteDoctorButtonListener());
        view.addRemoveSecButtonHandler(new DeleteSecretaryButtonListener());
        view.addRemoveAdminButtonHandler(new DeleteAdminButtonListener());
        view.addDoctorRatingJComboHandler(new RatingJComboSelectionListener());
        view.addSendFeedbackJButtonHandler(new SendFeedbackButtonListener());
    }    
    
    @Override
    public void addWindowCloseHandler(){
        view.addWindowListener(new WindowClosedListener());
    }
    
    @Override
    public void setLogoutHandler() {               
        view.addLogoutButtonHandler(new LogoutButtonListener());
    }
        
    @Override 
    public void setStrategy(){
        String userType = "logout";
        strategy.selectStrategy(userType);
    }
    
    private void setAdminInfo(){
        view.setUserInfoJText(userModel.getUserInfo());
    }
    
    private void setAdminMessages(){
        try{
            view.setUserMessages(userModel.getUserMessages());
        }catch(NullPointerException ex){
            System.out.println("No user messages");
        }
    }
    
    private void setDoctorCombo(){
        try{
            ArrayList<String> tempDoc = userModel.getUserInfo(UserTypes.D.toString());
            
            view.setDocJCombos(tempDoc);
        
        }catch(IllegalArgumentException ex){
            view.displayMessage("Failed to load doctor data");
        }
    }

    private void setSecCombo(){
        try{
            ArrayList<String> tempSec = userModel.getUserInfo(UserTypes.S.toString());
            
            view.setRemoveSecJCombo(tempSec);
            
        }catch(IllegalArgumentException ex){
            view.displayMessage("Failed to load secretary data");
        }
    }
    
    private void setAdminCombo(){
        try{            
            view.setRemoveAdminJCombo(userModel.getUserInfo(UserTypes.A.toString()));
            
        }catch(IllegalArgumentException ex){
            view.displayMessage("Failed to load Admin data");
        }        
    }
    
    private void setDocRatings(){
        try{            
            String[] ratings = new String[userModel.getRating(view.getSelectedDoctor().substring(0,5)).size()];
            
            for( int i = 0; i < ratings.length; i++){
                ratings[i] = userModel.getRating(view.getSelectedDoctor().substring(0, 5)).get(i).toString();
            }
            
            view.setDoctorRatingsJText(ratings);
            
        }catch(NullPointerException ex){
            System.out.println("This doctor has no ratings");
        }
    }
    
    ////////////////////////////////////////////////////////////////////////////
    //ErrorReporting Observer update methods
    @Override
    public void update(String errorMessage) {
        view.displayMessage(errorMessage);
    }      
    ////////////////////////////////////////////////////////////////////////////
    //Inner listener classes
    class DeleteMessageButtonListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            try{
                userModel.deleteMessage(view.getMessageToDeleteIndex());
                setAdminMessages();
            
            }catch(NullPointerException ex){
                view.displayMessage("Please select a message to delete");
            }
        }        
    }
    
    class DeleteDoctorButtonListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            
            String userID;
                        
            userID = view.getRemoveDocJCombo().getSelectedItem().toString();
            if(userID != null){
                
                userID = userID.substring(0, 5);
                userModel.deleteUser(userID);
            }
            
            setDoctorCombo();
        }        
    }
    
    class DeleteSecretaryButtonListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            
            String userID;
                        
            userID = view.getRemoveSecJCombo().getSelectedItem().toString();
            if(userID != null){
                
                userID = userID.substring(0, 5);
                userModel.deleteUser(userID);
            }
            
            setSecCombo();
        }        
    }
    
    class DeleteAdminButtonListener implements ActionListener{
        
        @Override
        public void actionPerformed(ActionEvent e) {
            String userID;
                        
            userID = view.getRemoveAdminJCombo().getSelectedItem().toString();
            if(userID != null){
                
                userID = userID.substring(0, 5);
                userModel.deleteUser(userID);
            }
            
            setAdminCombo();
        }
    }
    
    class CreateUserButtonListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {   
            
            if(!view.getjPasswordField().isEmpty() && !view.getForenameJText().isEmpty()&&
                    !view.getSurnameJTextField().isEmpty()){
                            
                userModel.createUser(view.getUserTypeJCombo().getSelectedItem().toString(), 
                        view.getjPasswordField(), 
                        view.getTitleJCombo().getSelectedItem().toString(),
                        view.getForenameJText(), view.getSurnameJTextField(), 
                        view.getAddressJTextField());

                setDoctorCombo();
                setSecCombo();
                setAdminCombo();
                view.clearCreateUserFields();
            }else{
                view.displayMessage("Enter a value in each field");
            }
        }
        
    }
    
    class RatingJComboSelectionListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            setDocRatings();
        }        
    }
    
    class SendFeedbackButtonListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            String message = userModel.getLoggedInUser() + ": " + view.getDoctorFeedback() + "\n";
            userModel.sendMessage(view.getSelectedDoctor().substring(0, 5), message);
        }        
    }
    
    class LogoutButtonListener implements ActionListener{
        
        @Override
        public void actionPerformed(ActionEvent e) {
            setStrategy();
            disposeView();
            userModel.saveAll();
        }        
    }   
    
    class WindowClosedListener extends WindowAdapter{
        
        @Override
        public void windowClosing(WindowEvent e) {
            userModel.saveAll();
        }
    }
}
