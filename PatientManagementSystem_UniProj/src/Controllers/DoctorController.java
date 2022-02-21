/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import SystemServices.ServicesFacade;
import LoginStrategiesModel.StrategySelect;
import SystemServices.MedicineStock;
import UserModel.UserFacade;
import UserModel.UserTypes;
import View.DoctorGUI;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import GUIUpdateObserver.GUIUpdateObserver;

/**
 * The controller for the DoctorGUI and it's interaction with UserFacade model.
 * Implements IController interface methods for setting up controller.
 * @author Glenn McKnight
 */
public class DoctorController implements IController, ISetLogoutStrategy, GUIUpdateObserver{

    private static volatile DoctorController doctorControllerInstance;
    private DoctorGUI view;
    private UserFacade userModel;  
    private ServicesFacade servicesModel;
    private final StrategySelect strategy;
    
    private DoctorController(){
        if (doctorControllerInstance != null){
            throw new RuntimeException("This class is a singleton, use getInstance() to access");
        }
        
        strategy = new StrategySelect();
    }
    
    /**
     * Returns a singleton instance of this controller. Creates a new instance
     * if one doesn't already exist.
     * @return doctorControllerInstance - an instance of this controller
     */
    public static DoctorController getInstance(){
        if(doctorControllerInstance == null){
            synchronized (DoctorController.class) {
                doctorControllerInstance = new DoctorController();
            }
        }
        
        return doctorControllerInstance;
    }
    
    /**
     * Assigns DoctorGUI to view variable
     * @param view DoctorGUI view
     */
    public void setView(DoctorGUI view) {
        this.view = view;
        setDoctorInfo();
        setDoctorMessages();
        setDoctorAppointments();
        setAppointmentDateFormat();
        setAppointmentTimes();
        setAppointmentPatientIDs();
        setPatientCombo();
        setPatientHistory();
        setPrescriptionHistory();
        setMedicinesJList();
        setLogoutHandler();
        addButtonHandler();
        addWindowCloseHandler();
    }
    
    @Override
    public void setModel() {
        this.userModel = UserFacade.getInstance();  
        this.servicesModel = ServicesFacade.getInstance();
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
        view.addCreateAppointmentButtonHandler(new CreateAppointmentButtonListener());
        view.addPatientJComboListener(new PatientHistoryJComboListener());
        view.addDeleteMessageButtonHandler(new DeleteMessageButtonListener());
        view.addNotesButtonHandler(new AddNotesButtonListener());
        view.addPrescribeButtonHandler(new PrescribeButtonListener());
        view.addCreateMedicineButtonHandler(new CreateMedicineButtonListener());
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
        String userType = "logout";
        strategy.selectStrategy(userType);
    }
        
    private void setDoctorInfo(){
        view.setDoctorInfo(userModel.getUserInfo());
    }
    
    private void setDoctorMessages(){
        try{
            view.setUserMessages(userModel.getUserMessages());
        }catch(NullPointerException ex){
            System.out.println("No user messages");
        }
    }
    
    private void setDoctorAppointments(){
        try{
            String[] appointments = new String[servicesModel.getCurrentAppointments().size()];

            for(int i = 0; i < servicesModel.getCurrentAppointments().size(); i++){
                appointments[i] = servicesModel.getCurrentAppointments().get(i);
            }
            view.setAppointmentsJList(appointments);
            
        }catch(NullPointerException ex){
            System.out.println("User has no booked appoitments");
        }
    }
    
    private void setAppointmentDateFormat(){
        view.setAppointmentDateSpinnerFormat();
    }
    
    private void setAppointmentTimes(){
        view.setAppointmentTimeCombo(servicesModel.getPossibleTimes());
    }
    
    private void setAppointmentPatientIDs(){
        view.setPatientCombos(userModel.getUserIDs(UserTypes.P.toString()));
    } 
    
    private void setPatientCombo(){
        try{
            ArrayList<String> tempPat = userModel.getUserInfo(UserTypes.P.toString());
            
            view.setPatientIDJCombos(tempPat);
        
        }catch(IllegalArgumentException ex){
            view.displayMessage("Failed to load patient data");
        }
    }
    
    private void setPatientHistory(){
        try{
            String[] patientNotes;
            patientNotes = new String[userModel.getPatientNotes(view.getPatientHistoryID().substring(0,5)).size()];
            
            for(int i = 0; i < patientNotes.length; i++){
                patientNotes[i] = userModel.getPatientNotes(view.getPatientHistoryID().substring(0,5)).get(i);
            }
            
            view.setPatientHistory(patientNotes);           
            
        }catch(NullPointerException ex){
            System.out.println("No patient history");
        }
    }
    
    private void setPrescriptionHistory(){
        try{
            String patientID = view.getPatientHistoryID().substring(0, 5); 
            String[] prescriptions = new String[userModel.getUserPrescription(patientID).size()];

            for(int i = 0; i < userModel.getUserPrescription(patientID).size(); i++){
                prescriptions[i] = userModel.getUserPrescription(patientID).get(i);
            }
            view.setPrescriptionHistory(prescriptions);
            
        }catch(NullPointerException ex){
            System.out.println("No patient prescription history");
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
    //ErrorReporting and Messaging Observer update methods
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
                setDoctorMessages();
            
            }catch(NullPointerException ex){
                view.displayMessage("Please select a message to delete");
            }
        }        
    }
    
    class CreateAppointmentButtonListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            try{
            servicesModel.addAppointment(userModel.getLoggedInUser(), 
                    view.getAppointmentTime(), view.getAppointmentDate(), 
                    view.getAppointmentPatient());
            
            setDoctorAppointments();
            
            }catch(IllegalArgumentException | NullPointerException ex){
                view.displayMessage("Couldn't create appointment");
                ex.printStackTrace();
            }
        }        
    }
    
    class PatientHistoryJComboListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            setPatientHistory();
            setPrescriptionHistory();
        }        
    }
    
    class AddNotesButtonListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            try{
                userModel.addPatientNotes(view.getPatientHistoryID().substring(0,5), 
                        view.getNotes());
                
                
                System.out.println("PatientID should be: " + view.getPatientHistoryID().substring(0,5));
                
                
                
                view.displayMessage("Notes added");
                setPatientHistory();
                view.clearNotesJText();
            
            }catch(NullPointerException ex){
                view.displayMessage("Please enter some notes");
            }
        }        
    }
    
    class PrescribeButtonListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            try{
                servicesModel.prescribeMedicine(view.getMedicinesIndex()); 
                                
                String message = userModel.getLoggedInUser() + 
                        ": Please give this medicine: " + 
                        servicesModel.getMedicineName(view.getMedicinesIndex()) 
                        + ", to this patient: " + view.getMedicinesPatientID();
                
                userModel.sendMessage(UserTypes.S.toString(), message);                
                view.displayMessage("Medicine prescribed");
                
            }catch(NullPointerException ex){
                view.displayMessage("Please select a medicine from list");
                ex.printStackTrace();
            }
        }        
    }
    
    class CreateMedicineButtonListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            try{
                servicesModel.addMedicine(view.getMedName(), view.getMedDosage(), 
                        view.getCommonUses());                  

                setMedicinesJList();
                view.clearCreateMedicineFields();
                view.displayMessage("Medicine added");
            
            }catch(NullPointerException ex){
                view.displayMessage("Please enter all medicine information");
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
            System.out.println("Should be saved");
        }
    }
}
