/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import SystemServices.ServicesFacade;
import LoginStrategies.StrategySelect;
import SystemServices.MedicineStock;
import UserModel.UserFacade;
import UserModel.UserTypes;
import View.SecretaryGUI;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;
import GUIUpdateObserver.GUIUpdateObserver;

/**
 * The controller for the SecretaryGUI and it's interaction with the UserFacade 
 * model. Implements IController interface methods for setting up the controller.
 * @author Glenn McKnight
 */
public class SecretaryController implements IController, 
        ISetLogoutStrategy, GUIUpdateObserver{

    private static volatile SecretaryController secretaryControllerInstance;
    private SecretaryGUI view;
    private UserFacade userModel;
    private ServicesFacade servicesModel;
    private final StrategySelect strategy = new StrategySelect();
        
    private SecretaryController(){
        if (secretaryControllerInstance != null){
            throw new RuntimeException("This class is a singleton, use getInstance() to access");
        }
    }
    
    /**
     * Returns a singleton instance of this controller. Creates a new instance
     * if one doesn't already exist.
     * @return patientControllerInstance - an instance of this controller
     */
    public static SecretaryController getInstance(){
        if(secretaryControllerInstance == null){
            synchronized (SecretaryController.class) {
                secretaryControllerInstance = new SecretaryController();
            }
        }
        return secretaryControllerInstance;
    }
    
    /**
     * Assigns AdminGUI to the view variable
     * @param view SecretaryGUI view 
     */
    public void setView(SecretaryGUI view) {
        this.view = view;
        addWindowCloseHandler();
        setLogoutHandler();
        setCreationRequests();
        setSecInfo();
        setSecMessages();
        setDeleteCombo();
        setAppointmentApprovalRequests();
        setDeleteAppointmentList();
        setAppointmentDateFormat();
        setAppointmentDoctorIDs();
        setAppointmentPatientIDs();
        setAppointmentTimes();
        setMedicinesJList();
        addButtonHandler();
    }
    
    @Override
    public void setModel() {
        this.userModel = UserFacade.getInstance(); 
        this.servicesModel = ServicesFacade.getInstance();
    }
    
    /**
     *
     */
    @Override
    public void disposeView(){
        view.dispose();
    }

    /**
     *
     * @return
     */
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
        view.addDeleteMessageButtonHandler(new DeleteMessageButtonListener());
        view.addApproveAccountButtonHandler(new ApproveAccountListener());
        view.addDeclineAccountButtonHandler(new DeclineAccountListener());
        view.addRemoveAccountButtonHandler(new DeletePatientListener());
        view.addApproveAppointmentButtonHandler(new ApproveAppointmentListener());
        view.addDeclineAppointmentButtonHandler(new DeclineAppointmentListener());
        view.addDeleteAppointmentButtonHandler(new DeleteAppointmentButtonListener());
        view.addCreateAppointmentButtonHandler(new CreateAppointmentListener());
        view.addPrescribeMedicineButtonHandler(new GiveMedicineButtonListener());
        view.addOrderMedicineButtonHandler(new OrderMedicineButtonListener());
        view.addDeleteMedicineButtonHandler(new DeleteMedicineButtonListener());
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
    public void setStrategy() {
        String userType = "Logout";
        strategy.selectStrategy(userType);
    }
            
    private void setCreationRequests(){
        
        try{
            String[] creation = new String[userModel.getAccountRequests().size()];
            
            for(int i = 0; i < userModel.getAccountRequests().size(); i++){
                
                creation[i] = userModel.getAccountRequests().get(i).getTitle() + ", "
                        + userModel.getAccountRequests().get(i).getFirstName() + ", "
                        + userModel.getAccountRequests().get(i).getSurname() + ", "
                        + userModel.getAccountRequests().get(i).getAge() + ", "
                        + userModel.getAccountRequests().get(i).getAddress();                              
            }            
            view.setCreationJList(creation);  
            
        }catch(NullPointerException ex){
            System.out.println("No creation requests");
            ex.printStackTrace();
        }
    }
      
    private void setDeleteCombo(){
        try{
            ArrayList<String> tempPat = userModel.getUserInfo(UserTypes.P.toString());            
            view.setRemoveJCombo(tempPat);
            
        }catch(IllegalArgumentException ex){
            view.displayMessage("Failed to load patient data");
        }
    }
    
    private void setSecInfo(){
        view.setSecInfoText(userModel.getUserInfo());
    }
    
    private void setSecMessages(){
        try{
            view.setUserMessages(userModel.getUserMessages());
        }catch(NullPointerException ex){
            System.out.println("No user messages");
        }
    }
    
    private void setAppointmentApprovalRequests(){  
        try{
            view.setAppointmentApprovalRequests(servicesModel.getUnapprovedAppointments());            
        }catch(NullPointerException ex){
            System.out.println("No unapproved appointments");
        }
    }
    
    private void setAppointmentDateFormat(){
        view.setAppointmentDateSpinnerFormat();
    }
    
    private void setAppointmentTimes(){
        view.setAppointmentTimeCombo(servicesModel.getPossibleTimes());
    }
    
    private void setAppointmentDoctorIDs(){        
        view.setDoctorCombo(userModel.getUserIDs(UserTypes.D.toString()));        
    }
    
    private void setAppointmentPatientIDs(){
        view.setPatientCombos(userModel.getUserIDs(UserTypes.P.toString()));
    } 
    
    private void setDeleteAppointmentList(){
        List<String> allAppointments = servicesModel.getCurrentAppointments();
        String[] appointments = new String[servicesModel.getCurrentAppointments().size()];
        
        try{
            for(int i = 0; i < allAppointments.size(); i++){
                appointments[i] = allAppointments.get(i);
            }

            view.setDeleteAppointmentsList(appointments);
        
        }catch(NullPointerException ex){
            ex.printStackTrace();
        }
    }
    
    private void setMedicinesJList(){
        ArrayList<MedicineStock> medicineStock = servicesModel.getMedStock();
        String[] medicines = new String[medicineStock.size()];
        
        for(int i = 0; i < medicineStock.size(); i++){
            
            medicines[i] = medicineStock.get(i).getMedicineName() + ", " +                     
                    medicineStock.get(i).getMedicineDosage() + ", " +                    
                    medicineStock.get(i).getCommonUses() + ", " +                    
                    medicineStock.get(i).getQuantity();
        }
        
        view.setMedicinesJList(medicines);        
    }
        
    ////////////////////////////////////////////////////////////////////////////
    //ErrorReporting observer update methods
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
                setSecMessages();
            
            }catch(NullPointerException ex){
                view.displayMessage("Please select a message to delete");
            }
        }        
    }    
    
    class ApproveAccountListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            try{                
                userModel.signupApproved(view.getCreationSelectedIndex());
                
                setDeleteCombo();
                setCreationRequests();
                
            } catch(IllegalArgumentException ex){
                System.out.println(ex);
            } catch(NullPointerException ex){
                view.displayMessage("No user selected");
            }
        }        
    }
    
    class DeclineAccountListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            try{                
                userModel.signupDeclined(view.getCreationSelectedIndex());
                setCreationRequests();
            
            } catch(NullPointerException ex){
                view.displayMessage("No user selected");
                ex.printStackTrace();
            }
        }        
    }    
    
    class DeletePatientListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            try{                
                userModel.deleteUser(view.getDeletePatientValue().substring(0, 5));
                setDeleteCombo();
                
            }catch(NullPointerException ex){
                view.displayMessage("No user selected");
            }
        }        
    }
        
    class ApproveAppointmentListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            try{
                servicesModel.setApproved(view.getAppointmentRequestIndex());
            
                setAppointmentApprovalRequests();
                setDeleteAppointmentList();
                view.displayMessage("Appointment approved");
                
            }catch(NullPointerException ex){
                view.displayMessage("No appointment selected");
            }
        }
        
    }
    
    class DeclineAppointmentListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            try{
                servicesModel.declineAppointment(view.getAppointmentRequestIndex());
                
                setAppointmentApprovalRequests();
                view.displayMessage("Appointment declined");
                
            }catch(NullPointerException ex){
                view.displayMessage("No appointment Selected");
            }
        }
        
    }
    
    class DeleteAppointmentButtonListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            try{
                servicesModel.deleteAppointment(view.getDeleteAppointmentIndex());
                
                setDeleteAppointmentList();
                view.displayMessage("Appointment deleted");
            }catch(NullPointerException ex){
                view.displayMessage("Please select an appointmnet to delete");
                ex.printStackTrace();
            }
        }    
    }
    
    class CreateAppointmentListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            try{
            servicesModel.addAppointment(view.getAppointmentDoctor(), 
                    view.getAppointmentTime(), view.getAppointmentDate(), 
                    view.getAppointmentPatient());
            
            setDeleteAppointmentList();
            
            }catch(IllegalArgumentException ex){
                view.displayMessage("Couldn't create appointment");
            }
        }
        
    }
    
    class OrderMedicineButtonListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            
            if(view.getMedicineQuantity() != 0){
                servicesModel.updateStock(view.getMedicineListIndex(), view.getMedicineQuantity());
                setMedicinesJList();
            }else{
                view.displayMessage("Please enter a medicine quantity");
            }
        }
    }
    
    class DeleteMedicineButtonListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            
            try{
                servicesModel.deleteMedicine(view.getMedicineListIndex());
                setMedicinesJList();
                
            }catch(NullPointerException ex){
                view.displayMessage("Select a medicine");
            }
        }
    
    }
    
    class GiveMedicineButtonListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            
            try{
                if(view.getMedicineQuantity() != 0){

                    String message;
                    message = userModel.getLoggedInUser() + ": Your doctor has prescribed" + "\n"
                            + view.getPrescribedMedicine().substring(0, view.getPrescribedMedicine().length()-2) 
                            +"." + "\n" + " I have sent this much of it to you: " + "\n"
                            + view.getMedicineQuantity();

                    userModel.giveMedicine(view.getMedicineListIndex(), 
                            view.getMedicineQuantity(), view.getPrescriptionPatientID(), 
                            message);

                    setMedicinesJList();
                }else{
                    view.displayMessage("Please enter a medicine quantity");
                }
            }catch(NullPointerException ex){
                view.displayMessage("Select a medicine");
            }
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
