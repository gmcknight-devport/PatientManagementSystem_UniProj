/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SystemServices;

import GUIUpdateObserver.GUIUpdate;
import Serializer.Serializer;
import java.io.InvalidObjectException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.ArrayList;

/**
 * A class to store, access, edit, delete, and add medicine objects. Implements 
 * serializable interface to allow saving of data to file.
 * @author Glenn McKnight
 */
public class MedicineStock implements Serializable {
    
    private String medName;
    private String medDosage;
    private String commonUses;
    private int quantity;
    
    private ArrayList<MedicineStock> medStock = new ArrayList<>(); 

    /**
     * Default constructor without parameters
     */
    public MedicineStock(){}
            
    /**
     * Constructor for class with all parameters required for an object.
     * @param medName name of medicine
     * @param medDosage dosage amount of medicine
     * @param quantity quantity of medicine 
     * @param commonUses common uses information
     */
    private MedicineStock(String medName, String medDosage, int quantity, String commonUses) {
        this.medName = medName;
        this.medDosage = medDosage;
        this.quantity = quantity;
        this.commonUses = commonUses;
    }      
    
   /**
    * Implements the abstract save method from super class. Saves objects to 
    * file using Serializer class static method.
    */
    public void save() {        
        Serializer.serializeObject(medStock, this.getClass().getName());        
    }

   /**
     * Implements abstract load method from super class. Loads objects from file
     * using Serializer class static method. 
     */
    public void load() {   
        try{            
            medStock = (ArrayList<MedicineStock>)Serializer.deserializeObject(this.getClass().getName());            
        }catch(IllegalArgumentException ex){
            GUIUpdate.getInstance().notifyUpdateObserver("Medicine stock load failed");
        }
    }
    /**
     * Creates a new medicine object and adds it to the stored list.
     * @param medName name to be set.
     * @param medDosage dosage amount and frequency.
     * @param commonUses to be set.
     */
    public void addMedicine(String medName, String medDosage, String commonUses){
        MedicineStock tempMed = new MedicineStock(medName, medDosage, 0, commonUses);
        medStock.add(tempMed);
    }
    /**
     * Deletes the medicine from the stock list specified by the index parameter.
     * @param medIndex specified medicine to be deleted.
     */
    public void deleteMedicine(int medIndex){
        try{
            medStock.remove(medStock.get(medIndex));
        
        }catch(NullPointerException | IndexOutOfBoundsException ex){
            GUIUpdate.getInstance().notifyUpdateObserver("Couldn't delete medicine");
        }
    }
    /**
     * Adds the quantity passed as parameter to the specified medicine. Use a 
     * negative number to reduce the medicine quantity.
     * @param medStockIndex specified medicine to update.
     * @param quantity of stock to add or remove.
     * @return true if successful or false for failure.
     */
    public boolean updateStock(int medStockIndex, int quantity){
                      
        int currQuantity = medStock.get(medStockIndex).getQuantity();
        
        if(currQuantity + quantity >= 0 ){
            medStock.get(medStockIndex).setQuantity(quantity);
            return true;
        }else{            
            return false;
        }        
    }
    /**
     * Prescribes a medicine to a patient. Returns a string of medicine information.
     * @param medStockIndex the specified medicine t prescribe.
     * @return String of patient prescription.
     */
    public String prescribeMedicine(int medStockIndex){
        String patientPrescription;
        
        try{        
            patientPrescription = medStock.get(medStockIndex).getMedicineName() + ", " 
                    + medStock.get(medStockIndex).getCommonUses() + ", " 
                    + medStock.get(medStockIndex).getMedicineDosage();

            return patientPrescription;
            
        }catch(NullPointerException | ArrayIndexOutOfBoundsException ex){
            GUIUpdate.getInstance().notifyUpdateObserver("Couldn't prescribe medicine");
            return null;
        }
    }
    ////////////////////////////////////////////////////////////////////////////
    // Accessors and mutators
    /**
     * Acessor to return specified medicine name.
     * @param medListIndex the specified medicine index.
     * @return medicine name.
     */
    public String getMedicineName(int medListIndex){
        try{            
            return medStock.get(medListIndex).getMedicineName();
        }catch(NullPointerException | ArrayIndexOutOfBoundsException ex){
            ex.printStackTrace();
            return null;
        }
    }
    /**
     * Accessor to return medicine name.
     * @return medicine name.
     */
    public String getMedicineName() {
        return medName;
    }
    /**
     * Mutator to set medicine name.
     * @param medName to be set.
     */
    public void setMedicineName(String medName) {
        this.medName = medName;
    }
    /**
     * Accessot to get medicine dosage.
     * @return dosage of medicine.
     */
    public String getMedicineDosage() {
        return medDosage;
    }
    /**
     * Mutator to set medicine dosage. 
     * @param medDosage dosage of medicine.
     */
    public void setMedicineDosage(String medDosage) {
        this.medDosage = medDosage;
    }
    /**
     * Accessor to get medicine quantity.
     * @return quantity of medicine.
     */
    public int getQuantity() {
        return quantity;
    }
    /**
     * Mutator to get medicine quantity.
     * @param quantity to be set.
     */
    public void setQuantity(int quantity) {
        this.quantity += quantity;
    }    
    /**
     * Accessor to get medicine's common uses.
     * @return common uses of medicine.
     */
    public String getCommonUses() {
        return commonUses;
    }
    /**
     * Mutator to set common uses for a medicine.
     * @param commonUses  to be set.
     */
    public void setCommonUses(String commonUses) {
        this.commonUses = commonUses;
    }
    /**
     * Acessor to get a list of medicines stored. 
     * @return list o medicine stock.
     */
    public ArrayList<MedicineStock> getMedStock() {        
        return medStock;
    }
    /**
     * Mutator to set list of medicine stock.
     * @param medStock list to be set.
     */
    public void setMedStock(ArrayList<MedicineStock> medStock) {
        this.medStock = medStock;
    }    
    ////////////////////////////////////////////////////////////////////////////
    // Serialisation Proxy
    /**
     * Blocks the readObject method to protect against serialzation attacks.
     * @param stream input stream from file.
     * @throws InvalidObjectException 
     */
    private void readObject(ObjectInputStream stream) throws InvalidObjectException{
        throw new InvalidObjectException("Proxy Required");
    }
    /**
     * Returns new MedicineStock proxy instead of the original class.
     * @return instance of MedicineStock class as proxy.
     */  
    private Object writeReplace(){       
        return new MedicineSerializationProxy(this);
    }
    /**
     * Static serialization proxy class used to protect against serialization attacks. 
     * A copy of the MedicineStock class constructor.
     */
    private static class MedicineSerializationProxy implements Serializable{
        
        private final String medName;
        private final String medDosage;
        private final int quantity;
        private final String commonUses;
        
        private MedicineSerializationProxy(MedicineStock m){
            
            this.medName = m.getMedicineName();
            this.medDosage = m.getMedicineDosage();
            this.quantity = m.getQuantity();
            this.commonUses = m.getCommonUses();                               
        }
        
        private static final long serialUID = 4653762542188888L;
        
        private Object readResolve(){
            return new MedicineStock(medName, medDosage, quantity, commonUses);
        }
    }    
}
