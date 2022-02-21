
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UserModel;

import GUIUpdateObserver.GUIUpdate;
import java.util.ArrayList;
import java.util.LinkedList;

/**
 *
 * @author Glenn McKnight
 */
public class UserFactory {
    
    public UserFactory(){}
    
    /**
     * Switch statement method to decide which type of user should be created. Calls
     * appropriate method to add that type.
     * @param userType the user type to add
     * @param p patient reference
     * @param d doctor reference 
     * @param s secretary reference
     * @param a administrator reference
     * @param password to be set
     * @param title to be set
     * @param forename to be set
     * @param surname to be set
     * @param age to be set
     * @param address to be set
     */
    public void addUser(String userType, Patient p, Doctor d, Secretary s, 
            Administrator a, String password, String title, String forename, 
            String surname, int age, String address){
        
        try{        
            switch(UserTypes.valueOf(userType.substring(0, 1).toUpperCase())){            
                case P:
                    addPatient(p, password, title, forename, surname, age, address);
                    break;
                case D:
                    addDoctor(d, password, title, forename, surname, address);
                    break;
                case S:
                    addSecretary(s, password, title, forename, surname);
                    break;
                case A:
                    addAdministrator(a, password, title, forename, surname);
                    break;
                default:
                    System.out.println("Error");                
            }
        }catch(IllegalArgumentException ex){
            GUIUpdate.getInstance().notifyUpdateObserver("Couldn't create user");
        }
    }   
    /**
     * Create a new doctor object and add it to the list using the doctor reference.
     * @param doctor class reference.
     * @param password to be set.
     * @param title to be set.
     * @param forename to be set.
     * @param surname to be set.
     * @param address to be set.
     */
    private void addDoctor(Doctor doctor, String password, String title, String forename, 
            String surname, String address){
        
        Doctor tempDoc = new Doctor(doctor.generateID(doctor.getUserIDList(), UserTypes.D), 
                password, "Dr.", forename, surname, address, new ArrayList<>(), new ArrayList<>());
        
        doctor.addDoctorToList(tempDoc);  
        GUIUpdate.getInstance().notifyUpdateObserver("Doctor created");
    }
    /**
     * Create a new secretary object and add it to the list using the secretary reference.
     * @param sec class reference.
     * @param password to be set.
     * @param title to be set.
     * @param forename to be set.
     * @param surname to be set.
     */
    private void addSecretary(Secretary sec, String password, String title, 
            String forename, String surname){
        
        Secretary tempSec = new Secretary(sec.generateID(sec.getUserIDList(), UserTypes.S), 
                password, title, forename, surname, new ArrayList<>());
        
        sec.addSecToList(tempSec);  
        GUIUpdate.getInstance().notifyUpdateObserver("Secretary created");
    }
    /**
     * Create a new administrator object and add it to the list using the administrator reference.
     * @param admin class reference.
     * @param password to be set.
     * @param title to be set.
     * @param forename to be set.
     * @param surname to be set.
     */
    private void addAdministrator(Administrator admin, String password, 
            String title, String forename, String surname){
        
        Administrator tempAdmin = new Administrator(admin.generateID(admin.getUserIDList(), UserTypes.A), 
                password, title, forename, surname, new ArrayList<>());
        
        admin.addAdminToList(tempAdmin);      
        GUIUpdate.getInstance().notifyUpdateObserver("Administrator created");
    }
    /**
     * Create a new patient object and add it to the list using the patient reference.
     * @param patient class reference. 
     * @param password to be set.
     * @param title to be set.
     * @param forename to be set.
     * @param surname to be set.
     * @param age to be set.
     * @param address to be set.
     */
    private void addPatient(Patient patient, String password, String title, 
            String forename, String surname, int age, String address){
        
        
        Patient temp;        
        temp = new Patient(patient.generateID(patient.getUserIDList(), UserTypes.P), password, 
                title, forename, surname, age, address, determineGender(title), 
                new ArrayList<>(), new ArrayList<>(), new LinkedList<>(), new LinkedList<>());
        
        patient.addPatientToList(temp);
        GUIUpdate.getInstance().notifyUpdateObserver("Patient created");
    }
    /**
     * Method to determine the gender based on the title the has been inputted.
     * @param title user's title.
     * @return char denoting gender.
     */    
    private char determineGender(String title){
        
       switch (title) {
            case "Mr.":
                return 'M';
            case "Mrs.":
                return 'F';
            default:
                return 'O';
        }
    }    
}
