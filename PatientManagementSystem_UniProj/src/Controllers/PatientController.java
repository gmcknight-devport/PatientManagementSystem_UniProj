/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import GUIUpdateObserver.GUIUpdateObserver;
import LoginStrategiesModel.StrategySelect;
import SystemServices.ServicesFacade;
import UserModel.UserFacade;
import UserModel.UserTypes;
import View.PatientGUI;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * The controller for the PatientGUI and it's interaction with the UserFacade model.
 * Implements IController interface methods for setting up the controller.
 * @author Glenn McKnight
 */
public class PatientController implements IController, ISetLogoutStrategy, 
        GUIUpdateObserver{

    private static volatile PatientController patientControllerInstance;
    private PatientGUI view;
    private UserFacade userModel;    
    private ServicesFacade servicesModel;
    private final ServicesFacade appointmentModel = ServicesFacade.getInstance();
    private final StrategySelect strategy = new StrategySelect();
    
    private PatientController(){
        if (patientControllerInstance != null){
            throw new RuntimeException("This class is a singleton, use getInstance() to access");
        }
    }
       
    /**
     * Returns a singleton instance of this controller. Creates a new instance
     * if one doesn't already exist.
     * @return patientControllerInstance - an instance of this controller
     */
    public static PatientController getInstance(){
        if(patientControllerInstance == null){
            synchronized (PatientController.class) {
                patientControllerInstance = new PatientController();
            }
        }
        
        return patientControllerInstance;
    }
    
    /**
     * Assigns AdminGUI to the view variable
     * @param view PatientGUI view
     */
    public void setView(PatientGUI view) {
        this.view = view;
        setLogoutHandler();
        setPatientInfo();
        setPatientMessages();
        setPatientCurrPrescription();
        setPatientAppointment();
        setAppointmentDateFormat();
        setAppointmentDoctorIDs();
        setAppointmentTimes();
        setRatedDoctors();
        setNotesHistory();
        setAppointmentHistory();
        setPrescriptionHistory();
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
        view.addDeleteMessageButtonHandler(new DeleteMessageButtonListener());
        view.addRateDoctorButtonHandler(new RateDoctorListener());
        view.addRequestAppointmentButtonHandler(new RequestAppointmentListener());
        view.addAccountTerminationButtonHandler(new RequestAccountTerminationListener());
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
    
    private void setPatientInfo(){
        view.setPatientInfo(userModel.getUserInfo());
    }
    
    private void setPatientMessages(){
        try{
            view.setUserMessages(userModel.getUserMessages());
        }catch(NullPointerException ex){
            System.out.println("No user messages");
        }
    }
    
    /**
     * Gets only the first element as this is current prescription of LIFO collection.
     */
    private void setPatientCurrPrescription(){
        try{
            view.setCurrentPrescription(userModel.getUserPrescription
                (userModel.getLoggedInUser()).get(0));
            
        }catch(NullPointerException ex){
            System.out.println("No current prescription");
        }catch(IndexOutOfBoundsException ex){
            System.out.println("Current prescription problem");
        }
    }
        
    private void setPatientAppointment(){
        try{
            String[] appointments = new String[appointmentModel.getCurrentAppointments().size()];

            for(int i = 0; i < appointmentModel.getCurrentAppointments().size(); i++){
                appointments[i] = appointmentModel.getCurrentAppointments().get(i);
            }
            view.setAppointmentsJList(appointments);
            
        }catch(IndexOutOfBoundsException ex){
            System.out.println("No appointments booked for this user");
        }catch(NullPointerException ex){
            System.out.println("No appointments booked");
        }
    }
    
    private void setAppointmentDateFormat(){
        view.setAppointmentDateSpinnerFormat();
    }
    
    private void setAppointmentTimes(){
        view.setAppointmentTimeCombo(servicesModel.getPossibleTimes());
    }
    
    private void setAppointmentDoctorIDs(){        
        view.setDoctorCombos(userModel.getUserIDs(UserTypes.D.toString()));        
    }
    
    private void setRatedDoctors(){
        String[] docRatings = new String[userModel.getDocsAndRatings().size()];
        
        try{
            for(int i = 0; i < userModel.getDocsAndRatings().size(); i++){
                docRatings[i] = userModel.getDocsAndRatings().get(i);
            }
        }catch(NullPointerException ex){
            System.out.println("No doctor ratings available");
        }
        view.setRatedDoctors(docRatings);
    }
    
    public void setAppointmentHistory(){
        try{
            String[] appointments = new String[userModel.getAppointmenthistory(userModel.getLoggedInUser()).size()];

            
            for(int i = 0; i < userModel.getAppointmenthistory(userModel.getLoggedInUser()).size(); i++){
                appointments[i] = userModel.getAppointmenthistory(userModel.getLoggedInUser()).get(i);
            }

            view.setAppointmentHistory(appointments);
            
        }catch(NullPointerException ex){
            System.out.println("User has no previous appointments");
        }
    }
    
    public void setPrescriptionHistory(){        
        try{
            String patientID = userModel.getLoggedInUser();
            String[] prescriptions = new String[userModel.getUserPrescription(patientID).size()];
           
            for(int i = 0; i < userModel.getUserPrescription(patientID).size(); i++){
                prescriptions[i] = userModel.getUserPrescription(patientID).get(i);
            }
            view.setPrescriptionHistory(prescriptions);            
            
        }catch(NullPointerException ex){
            System.out.println("User has no previous prescriptions");
        }
    }
    
    public void setNotesHistory(){
        
        try{
            String patientID = userModel.getLoggedInUser();
            String[] notes = new String[userModel.getPatientNotes(patientID).size()];
            
            for(int i = 0; i < notes.length; i++){
                notes[i] = userModel.getPatientNotes(patientID).get(i);
            }
            
            view.setNotesHistory(notes);
        }catch(NullPointerException ex){
            System.out.println("User has no notes history");
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
                userModel.deleteMessage(view.getSelectedMessageIndex());
                setPatientMessages();
            
            }catch(NullPointerException ex){
                view.displayMessage("Please select a message to delete");
            }
        }        
    }
    
    class RequestAccountTerminationListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            userModel.sendMessage(UserTypes.S.toString(), userModel.getLoggedInUser() 
                    + ": Please terminate my account");
        }        
    }
    
    class RequestAppointmentListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            try{
            servicesModel.requestAppointment(view.getAppointmentDoctor(), 
                    view.getAppointmentTime(), view.getAppointmentDate(), 
                    userModel.getLoggedInUser());
            
            }catch(NullPointerException ex){
                view.displayMessage("Please enter a value in each field");
                ex.printStackTrace();
            }
        }        
    }
    
    class RateDoctorListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            try{
                userModel.setRating(view.getRatedDoctor(), view.getDoctorRating());
                
                setRatedDoctors();
                view.displayMessage("Doctor rated");
                userModel.sendMessage(UserTypes.A.toString(), userModel.getLoggedInUser() 
                        + ": " + view.getFeedback() + ", for Doctor: " + view.getRatedDoctor());
                
                view.clearFeedback();
            
            }catch(NullPointerException ex){
                view.displayMessage("Select a rating for a doctor");
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