/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SystemServices;

import UserModel.UserFacade;
import UserModel.UserTypes;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Glenn McKnight
 */
public class ServicesFacade {
    
    private static volatile ServicesFacade servicesFacadeInstance;
        
    private final UserFacade userModel = UserFacade.getInstance();
    private final Appointments appointments = new Appointments();
    private final MedicineStock medicines = new MedicineStock();
    
    /**
     * Private constructor throws exception if a second instance instantiation is 
     * attempted. Calls Appointments methods to load and sort in preparation for use. 
     */
    private ServicesFacade(){
        if (servicesFacadeInstance != null){
            throw new RuntimeException("This class is a singleton, use getInstance() to access");
        }

        appointments.load();
        medicines.load();
        sortAppointments();
    }
    /**
     * Static access method to return singleton of this class. Creates new instance
     * if one doesn't already exist.
     * @return instance of this class
     */
    public static ServicesFacade getInstance(){
        if(servicesFacadeInstance == null){
            synchronized (ServicesFacade.class) {
                servicesFacadeInstance = new ServicesFacade();
            }
        }
        return servicesFacadeInstance;
    }    
    /**
     * Save appointments to file using Serializer class.
     */
    public void save(){
        appointments.save();
    }
    ///////////////////////////////////////////////////////////////////////////
     //Appointments    
    /**
     * Call sortAppointments method of appointments class and store a list of past 
     * appointments to send to the userFacade. Catch array index out of bounds exception.
     */
    private void sortAppointments(){
        String appointment;
        List<Appointments> pastAppointments = appointments.sortAppointments();
        
        try{
            for(Appointments a : pastAppointments){
                
                appointment = a.getDoctorID() + ", " + a.getAppointmentDate().toString()
                        + ", " + a.getAppointmentTime();            
                userModel.addAppointmentToHistory(appointment, a.getPatientID());
            }
        }catch(ArrayIndexOutOfBoundsException ex){
            ex.printStackTrace();
        }
    }
    /**
     * Call getCurrentAppointments method of appointments class and return a list
     * of appointments for the current logged in user registered in UserFacade if 
     * they're a patient or doctor. Get a list of all appointments if the user is 
     * a Secretary.
     * @return list of appointments dependent on logged in user type.
     */
    public ArrayList<String> getCurrentAppointments(){
        ArrayList<String> userAppointments = new ArrayList<>();
        
        for(Appointments a : appointments.getCurrentAppointments()){
            
            if(a.getDoctorID().equals(userModel.getLoggedInUser()) 
                    || a.getPatientID().equals(userModel.getLoggedInUser())){
                
                userAppointments.add(a.getDoctorID() + ", " + a.getAppointmentDate().toString() 
                        + ", " + a.getAppointmentTime() + ", " + a.getPatientID());
                
            }else if(userModel.getLoggedInUser().substring(0, 1).equals(UserTypes.S.toString())){
                
                userAppointments.add(a.getDoctorID() + ", " + a.getAppointmentDate().toString() 
                        + ", " + a.getAppointmentTime() + ", " + a.getPatientID());
            }
        }                  
        return userAppointments;
    }
    /**
     * Calls getUnapprovedAppointments method of Appointments class and returns a
     * String array of results.
     * @return String array of unapproved appointments.
     */
    public String[] getUnapprovedAppointments(){  
        String[] appoint = new String[appointments.getUnapprovedAppointments().size()];
        
        for(int i = 0; i < appointments.getUnapprovedAppointments().size(); i++){
            appoint[i] = appointments.getUnapprovedAppointments().get(i).getDoctorID() + ", "
                    + appointments.getUnapprovedAppointments().get(i).getAppointmentDate() + ", "
                    + appointments.getUnapprovedAppointments().get(i).getAppointmentTime() + ", "
                    + appointments.getUnapprovedAppointments().get(i).getPatientID();
        }
        return appoint;
    }
    /**
     * Accessor to return possible appointment times frmo appointments class.
     * @return list of possible appointment times
     */
    public List<String> getPossibleTimes(){
        return appointments.getPossibleTimes();
    }
    /**
     * Calls requestAppointment method from appointments class - stores details of
     * requested appointment time, notifies user if request fails due to invalid day
     * or already booked.
     * @param docId to make appointment with.
     * @param time requested time
     * @param date requested date.
     * @param patId to make appointment for.
     */
    public void requestAppointment(String docId, String time, LocalDate date, String patId){
        appointments.requestAppointment(docId, time, date, patId);
    }
    /**
     * Calls addAppointment method of Appointments class to create an 
     * appointment for specified values if available, otherwise notifies user.
     * @param docId to make appointment with.
     * @param time requested time
     * @param date requested date.
     * @param patId to make appointment for.
     */
    public void addAppointment(String docId , String time, LocalDate date, String patId){
        appointments.addAppointment(docId, time, date, patId);
    }
    /**
     * Calls method setApproved of appointments class and passes index to specify 
     * the one to be approved. 
     * @param appointmentIndex specified appointment to approve.
     */
    public void setApproved(int appointmentIndex){
        appointments.setApproved(appointmentIndex);
    }
    /**
    * Calls method declineAppointment in appointments class  passes index to specify 
    * the one to be declined.
    * @param appointmentIndex specified appointment to decline.
    */
    public void declineAppointment(int appointmentIndex){
        appointments.declineAppointment(appointmentIndex);
    }
    /**
    * Calls method deleteAppointment in appointments class  passes index to specify 
    * the one to be deleted.
    * @param appointmentIndex specified appointment to delete.
    */
    public void deleteAppointment(int appointmentIndex){
        appointments.deleteAppointment(appointmentIndex);
    }    
    ////////////////////////////////////////////////////////////////////////////
    /// MedicineStock  
    /**
     * Calls method getMedStock in medicineStock class to get a list of all medicines
     * held in stock and the information stored about them.
     * @return a list of stored medicines.
     */
    public ArrayList<MedicineStock> getMedStock(){
        return medicines.getMedStock();
    }
    /**
     * Calls method getMedStock in medicineStock class to prescribe a medicine 
     * specified by the index.
     * @param medicineIndex specified medicine to be prescribed.
     */
    public void prescribeMedicine(int medicineIndex){
        medicines.prescribeMedicine(medicineIndex);
    }
    /**
     * Accessor to get name of a medicine specified by the index.
     * @param medicineIndex of specified medicine.
     * @return name of specified medicine.
     */
    public String getMedicineName(int medicineIndex){
        return medicines.getMedicineName(medicineIndex);
    }
    /**
     * Calls method addMedicine in MedicineStock class to create a new medcine 
     * from inputted parameters.
     * @param medName of medicine.
     * @param dosage of medicine.
     * @param commonUses of medicine.
     */
    public void addMedicine(String medName, String dosage, String commonUses){
        medicines.addMedicine(medName, dosage, commonUses);
    }
    /**
     * Calls method updateStock of medicineStock class to update the quantity of
     * a specified medicine. Inputted a negative number with decrease the stock level.
     * @param medicineIndex of medicine to be updated.
     * @param quantity of stock to update.
     */
    public void updateStock(int medicineIndex, int quantity){
        medicines.updateStock(medicineIndex, quantity);
    }
    /**
     * Calls method deleteMedicine from the medicineStock class to delete the 
     * medicine specified by the parameter.
     * @param medicineIndex Specified medicine to be deleted.
     */
    public void deleteMedicine(int medicineIndex){
        medicines.deleteMedicine(medicineIndex);
    }
}

