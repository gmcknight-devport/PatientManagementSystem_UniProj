/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SystemServices;

import GUIUpdateObserver.GUIUpdate;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import Serializer.Serializer;
import java.io.InvalidObjectException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.ConcurrentModificationException;
import java.util.List;

/**
 *
 * @author Glenn McKnight
 */
public class Appointments implements Serializable{
    
    private final String[] days = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday"};
    private ArrayList<String> possibleTimes = new ArrayList<>();
    
    private LocalDate appointmentDate;
    private String doctorID;
    private String patientID;
    private String appointmentTime;
    private boolean appointmentApproved;
    
    private ArrayList<Appointments> currentAppointments = new ArrayList<>();
    private ArrayList<Appointments> appointmentRequests = new ArrayList<>();
    
    private final String saveFileName = this.getClass().getName();
    /**
     * Default constructor. Adds all possible appointment times to list.
     */
    public Appointments(){
        possibleTimes.add("08:00"); possibleTimes.add("08:30"); possibleTimes.add("09:00");
        possibleTimes.add("09:30"); possibleTimes.add("10:00"); possibleTimes.add("10:30"); 
        possibleTimes.add("11:00"); possibleTimes.add("11:30"); possibleTimes.add("12:00");
        possibleTimes.add("12:30"); possibleTimes.add("13:00"); possibleTimes.add("13:30");
        possibleTimes.add("14:00"); possibleTimes.add("14:30"); possibleTimes.add("15:00");
        possibleTimes.add("15:30"); possibleTimes.add("16:00"); possibleTimes.add("16:30");
        possibleTimes.add("17:00"); possibleTimes.add("17:30"); possibleTimes.add("18:00");
        possibleTimes.add("18:30");
    }
    /**
     * Constructor for a full Appointments object.
     * @param doctorID to be set
     * @param time to be set
     * @param date to be set.
     * @param patientID to be set. 
     * @param approved Whether appointment has been approved by appropriate user.
     */
    public Appointments(String doctorID, String time, LocalDate date, String patientID, boolean approved){
        this.currentAppointments = new ArrayList();
        this.doctorID = doctorID;
        this.appointmentTime = time;
        this.appointmentDate = date;
        this.patientID = patientID;
        this.appointmentApproved = approved;
    }
    /**
     * Checks to see if appointment with particular doctor is available on time 
     * and date. Ensures the requested day is not a weekend. Adds appointment to 
     * requests list if it is valid. Notifies observer that request has been 
     * accepted or that it has been refused.
     * @param doctorID the ID of the doctor requested for the appointment.
     * @param time the requested appointment time.
     * @param date the requested appointment day.
     * @param patientID the patient who requested the appointment.
     */
    public void requestAppointment(String doctorID, String time, LocalDate date, 
            String patientID){
        
        if(!checkForAppointment(doctorID, date, time, appointmentRequests) && checkDay(date)){
            
            Appointments appointment = new Appointments(doctorID, time, date, patientID, false);            
            appointmentRequests.add(appointment);

            GUIUpdate.getInstance().notifyUpdateObserver("Appointment request received");
        }else{
            GUIUpdate.getInstance().notifyUpdateObserver("Appointment time or "
                    + "date unavailable");
        }
    }
    /**
     * Adds an appointment to the appointments list if the specified doctor is
     * available on the specified date and at the specified time. Checks the 
     * day is not on a weekend. Reports to the observer that appointment has
     * been created or that it has failed.
     * @param doctorID
     * @param time
     * @param date
     * @param patientID 
     */
    public void addAppointment(String doctorID, String time, LocalDate date, 
            String patientID){
        
        if(!checkForAppointment(doctorID, date, time,  currentAppointments) && checkDay(date)){
            
            Appointments appointment = new Appointments(doctorID, time, date, patientID, true);
            currentAppointments.add(appointment);

            GUIUpdate.getInstance().notifyUpdateObserver("Appointment created");
            
        }else{
            GUIUpdate.getInstance().notifyUpdateObserver("Appointment time or "
                    + "date unavailable");
        }
    }    
    /**
     * Checks the day the parameter falls on is not a weekend.
     * @param date
     * @return 
     */
    private boolean checkDay(LocalDate date){        
        String day = date.getDayOfWeek().toString();
        
        for(String s : days){
            if(s.equalsIgnoreCase(day)){
                return true;
            }
        }
        return false;        
    }
    /**
     * Deletes the appointment from the currentAppointments list specified by the
     * index.
     * @param currentAppointmentIndex of the appointment to be deleted.
     */
    public void deleteAppointment(int currentAppointmentIndex){
        if(checkForAppointment(currentAppointments.get(currentAppointmentIndex).getDoctorID(), 
                currentAppointments.get(currentAppointmentIndex).getAppointmentDate(), 
                currentAppointments.get(currentAppointmentIndex).getAppointmentTime(), currentAppointments)){
            
            currentAppointments.remove(currentAppointmentIndex);
        
        }else{
            GUIUpdate.getInstance().notifyUpdateObserver("Couldn't delete appointment");
        }
    }
    /**
     * Declines the appointment specified by the index and removes it from the
     * request list.
     * @param requestsIndex 
     */
    public void declineAppointment(int requestsIndex){
        if(checkForAppointment(appointmentRequests.get(requestsIndex).getDoctorID(), 
                appointmentRequests.get(requestsIndex).getAppointmentDate(), 
                appointmentRequests.get(requestsIndex).getAppointmentTime(), appointmentRequests)){
            
            appointmentRequests.remove(requestsIndex);
        }else{
            GUIUpdate.getInstance().notifyUpdateObserver("Couln't decline appointment");
        }
    }
    /**
     * Checks to see if the specified doctor is available on that day and time. 
     * @param doctorID the doctor to make appointment with.
     * @param date requested for appointment.
     * @param time requested for appointment.
     * @param appointmentList The type of appointment list to perform check on.
     * @return 
     */
    private boolean checkForAppointment(String doctorID, LocalDate date, String time,  
            ArrayList<Appointments> appointmentList){
        
        if(appointmentList == null){
            return false;
        }else{
            for(Appointments a : appointmentList){
                if(a.doctorID.equals(doctorID) && date.equals(a.appointmentDate) 
                        && a.appointmentTime.equals(time)){
                   
                    return true;
                }
            }
            return false;
        }
    }
    /**
     * Saves the appointments from all appointment lists to file using Serializer class.
     */
    public void save(){
        try{
            ArrayList<Object> allAppointments = new ArrayList<>();

            for(int i = 0; i < currentAppointments.size(); i++){
                allAppointments.add(currentAppointments.get(i));
            }

            for(int i = 0; i < appointmentRequests.size(); i++){
                allAppointments.add(appointmentRequests.get(i));
            }
            Serializer.serializeObject(allAppointments, this.getClass().getName());   
            
        }catch(NullPointerException ex){
            System.out.print("No appointments to save to file");
        }
    }
    /**
     * Loads appointments from file. Initialises the lists for them to be stored in.
     */
    public void load(){
        try{
            currentAppointments = (ArrayList<Appointments>)Serializer.deserializeObject(this.getClass().getName());
            
        }catch(NullPointerException ex){
            currentAppointments = new ArrayList<>();
            appointmentRequests = new ArrayList<>();
            System.out.println("No appointments loaded");
        }        
    }
    /**
     * Sorts appointments loaded from file into their appropriate lists depending on 
     * whether they have been approved or not. Returns a list of the appointments
     * that have occurred in the past.
     * @return list of previous appointments.
     */
    public List<Appointments> sortAppointments(){
        List<Appointments> pastAppointments = new ArrayList<>();
        
        try{           
            for(int i = 0; i < currentAppointments.size(); i++){
                if(currentAppointments.get(i).getAppointmentDate().isBefore(LocalDate.now())){
                                        
                    pastAppointments.add(currentAppointments.get(i));
                    currentAppointments.remove(i);                    

                }else if(!currentAppointments.get(i).isAppointmentApproved()){
                    
                    appointmentRequests.add(currentAppointments.get(i));
                    currentAppointments.remove(i);
                }
            }       
            return pastAppointments;
            
        }catch(ConcurrentModificationException ex){
            ex.printStackTrace();
            GUIUpdate.getInstance().notifyUpdateObserver("Appointmnets sorting failed, "
                    + "some appointments from previous days may still be displayed"
                    + " and requests may not appear.");
            return pastAppointments;
        }
    }
    ////////////////////////////////////////////////////////////////////////////
    // Accessors and Mutators
    /**
     * Accessor for possible appointment times.
     * @return list of possible appointment times
     */
    public ArrayList<String> getPossibleTimes(){
        return possibleTimes;
    }
    /**
     * Sets appointment approved boolean to true. Moves appointment to 
     * currentAppointments list and removes it from requests list.
     * @param index 
     */
    public void setApproved(int index){
        appointmentRequests.get(index).appointmentApproved = true;
        
        currentAppointments.add(appointmentRequests.get(index));
        appointmentRequests.remove(index);              
    }       
    /**
     * Accessor to return list of unapproved appointments.
     * @return list of unapproved appointments.
     */
    public ArrayList<Appointments> getUnapprovedAppointments(){
       return appointmentRequests;
    }
    /**
     * Accessor to return list of approved appointments.
     * @return list of approved appointments.
     */
    public ArrayList<Appointments> getCurrentAppointments(){
        return currentAppointments;
    }
    /**
     * Accessor to return appointment date.
     * @return appointment date.
     */
    public LocalDate getAppointmentDate() {
        return appointmentDate;
    }
    /**
     * Mutator to set the date of the appointment.
     * @param appointmentDate to be set.
     */
    public void setAppointmentDate(LocalDate appointmentDate) {
        this.appointmentDate = appointmentDate;
    }
    /**
     * Accessor for doctorID.
     * @return doctorID.
     */
    public String getDoctorID() {
        return doctorID;
    }
    /**
     * Mutator to set doctorID.
     * @param doctorID to be set.
     */
    public void setDoctorID(String doctorID) {
        this.doctorID = doctorID;
    }
    /**
     * Accessor for patientID.
     * @return patientID.
     */
    public String getPatientID() {
        return patientID;
    }
    /**
     * Mutator to set patientID.
     * @param patientID to be set.
     */
    public void setPatientID(String patientID) {
        this.patientID = patientID;
    }
    /**
     * Accessor for appointmentTime.
     * @return appointmentTime.
     */
    public String getAppointmentTime() {
        return appointmentTime;
    }
    /**
     * Mutator to set appoitmentTime.
     * @param appointmentTime to be set.
     */
    public void setAppointmentTime(String appointmentTime) {
        this.appointmentTime = appointmentTime;
    }
    /**
     * Boolean to indicate is appointment has been approved.
     * @return boolean of appointment approval.
     */
    public boolean isAppointmentApproved() {
        return appointmentApproved;
    }
    /**
     * Mutator to set the appointment to approved.
     * @param appointmentApproved to be set.
     */
    public void setAppointmentApproved(boolean appointmentApproved) {
        this.appointmentApproved = appointmentApproved;
    }
    /**
     * Accessor to get the save file name for this class. 
     * @return the save file name.
     */
    public String getSaveFileName() {
        return saveFileName;
    }
    ////////////////////////////////////////////////////////////////////////////
    // Serialization Proxy
    /**
     * Blocks the readObject method to protect against serialzation attacks.
     * @param stream input stream from file.
     * @throws InvalidObjectException 
     */
    private void readObject(ObjectInputStream stream) throws InvalidObjectException{
        throw new InvalidObjectException("Proxy Required");
    }
    /**
     * Returns new Appointments proxy instead of the original class.
     * @return instance of Appointments class as proxy.
     */ 
    private Object writeReplace(){
        return new AppointmentsSerializationProxy(this);
    }
    /**
     * Static serialization proxy class used to protect against serialization attacks. 
     * A copy of the Appointments class constructor.
     */
    private static class AppointmentsSerializationProxy implements Serializable{
                
        private final String doctorID;
        private final String appointmentTime;
        private final LocalDate appointmentDate;
        private final String patientID;
        private final boolean appointmentApproved;
        
        private AppointmentsSerializationProxy(Appointments a){
            this.doctorID = a.getDoctorID();
            this.appointmentTime = a.getAppointmentTime();
            this.appointmentDate = a.getAppointmentDate();
            this.patientID = a.getPatientID();
            this.appointmentApproved = a.isAppointmentApproved();
        }
        
        private Object readResolve(){
            return new Appointments(doctorID, appointmentTime, appointmentDate, 
                    patientID, appointmentApproved);
        }
    }
}
