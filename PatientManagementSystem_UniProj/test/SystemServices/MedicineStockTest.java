/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SystemServices;

import SystemServices.MedicineStock;
import java.lang.reflect.Field;
import java.util.ArrayList;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Glenn McKnight
 */
public class MedicineStockTest {
    
    MedicineStock instance = new MedicineStock();
    ArrayList<MedicineStock> medStock = new ArrayList<>();
    
    @AfterClass
    public static void tearDownClass() {
        
        MedicineStock medicine = new MedicineStock();
        
        medicine.addMedicine("Lithium", "300mg / twice per day", "Mania, Bipolar Disorder");
        medicine.updateStock(0, 60);
        medicine.addMedicine("Diazepam", "5mg / 3-4 times per day", "Anxiety disorders, alochol withdrawal symptoms, muscle spasms");
        medicine.updateStock(1, 100);
        medicine.addMedicine("Lorazepam", "2mg / 2-3times per day", "Anxiety disorders, seizures");
        medicine.updateStock(2, 90);
        medicine.addMedicine("Insulin", "4 units", "Diabetes");
        medicine.updateStock(3, 90);
        medicine.addMedicine("Zopaclone", "1mg before bed", "Insomnia");
        medicine.updateStock(4, 10);
        medicine.addMedicine("Amoxicillan", "250mg / twice per day", "Bacerial infections");
        medicine.updateStock(5, 10);
        medicine.addMedicine("Hydrocortisone", "1% / apply as needed", "Inflammation");
        medicine.updateStock(6, 15);
        medicine.addMedicine("Atorvastatin", "20mg / once per day", "High cholesterol, stroke and heart attack risk");
        medicine.updateStock(7, 22);
        medicine.addMedicine("Lisinopril", "5mg / once per day", "High blood pressure");
        medicine.updateStock(8, 10);
        medicine.addMedicine("Warfarin", "5mg / once per day", "Blood thinner to reduce clotting");
        medicine.updateStock(9, 40);
                
        medicine.save();
    }
    
    @Before
    public void setUp() throws IllegalAccessException, IllegalArgumentException, NoSuchFieldException {
        
        Field field = instance.getClass().getDeclaredField("medStock"); 
        field.setAccessible(true);
        medStock = (ArrayList<MedicineStock>) field.get(instance);        
        medStock.clear();
        
        instance.addMedicine("Lithium", "300mg / twice per day", "Mania, Bipolar Disorder");
        instance.updateStock(0, 60);
        instance.addMedicine("Diazepam", "5mg / 3-4 times per day", "Anxiety disorders, alochol withdrawal symptoms, muscle spasms");
        instance.updateStock(1, 100);
    }
    
    @After
    public void tearDown() {
        instance = null;
        assertNull(instance);
    }

    /**
     * Test of save method, of class MedicineStock.
     */
    @Test
    public void testSaveAndLoad() {
        System.out.println("save");
        
        instance.save();
        MedicineStock medicineSerialized = new MedicineStock();
        medicineSerialized.load();
                
        assertEquals(instance.getMedStock().get(1).getMedStock(), medicineSerialized.getMedStock().get(1).getMedStock());
    }

    /**
     * Test of addMedicine method, of class MedicineStock.
     */
    @Test
    public void testAddMedicine() {
        System.out.println("addMedicine");
        String medName = "Some Med";
        String medDosage = "Take some everyday";
        String commonUses = "For testing classes";
        
        instance.addMedicine(medName, medDosage, commonUses);
        
        String expResult = medName+medDosage+0+commonUses;
        String result = instance.getMedStock().get(2).getMedicineName() +
                instance.getMedStock().get(2).getMedicineDosage() + 
                instance.getMedStock().get(2).getQuantity() + 
                instance.getMedStock().get(2).getCommonUses();
        
        assertEquals(expResult, result);
    }

    /**
     * Test of updateStock method, of class MedicineStock.
     */
    @Test
    public void testUpdateStock() {
        System.out.println("updateStock");
        int medStockIndex = 1;
        int quantity = 20;
        
        instance.updateStock(medStockIndex, quantity);
        
        int expResult = 120;
        int result = instance.getMedStock().get(medStockIndex).getQuantity();
        
        assertEquals(expResult, result);
    }

    /**
     * Test of prescribeMedicine method, of class MedicineStock.
     */
    @Test
    public void testPrescribeMedicine() {
        System.out.println("prescribeMedicine");
        int medStockIndex = 1;
        
        String expResult = "Diazepam, Anxiety disorders, alochol withdrawal symptoms, muscle spasms, 5mg / 3-4 times per day";
        String result = instance.prescribeMedicine(medStockIndex);
        
        assertEquals(expResult, result);
    }
    
    /**
     * Test of getMedicineName method, of class MedicineStock.
     */
    @Test
    public void testGetMedicineName_int() {
        System.out.println("getMedicineName");
        int medListIndex = 1;
        String medicineName = "Diazepam";
        
        String expResult = medicineName;
        String result = instance.getMedicineName(medListIndex);
        
        assertEquals(expResult, result);
    }

    /**
     * Test of getMedicineName method, of class MedicineStock.
     */
    @Test
    public void testGetMedicineName_0args() {
        System.out.println("getMedicineName");
        int medListIndex = 0;
        String medicineName = "Lithium";
        
        String expResult = medicineName;
        String result = medStock.get(medListIndex).getMedicineName();
        
        assertEquals(expResult, result);
    }

    /**
     * Test of setMedicineName method, of class MedicineStock.
     */
    @Test
    public void testSetMedicineName() {
        System.out.println("setMedicineName");
        String medName = "testName";
        
        medStock.get(1).setMedicineName(medName);

        String expResult = medName;
        String result = medStock.get(1).getMedicineName();
        
        assertEquals(expResult, result);
    }

    /**
     * Test of getMedicineDosage method, of class MedicineStock.
     */
    @Test
    public void testGetMedicineDosage() {
        System.out.println("getMedicineDosage");
        
        String expResult = "300mg / twice per day";
        String result = medStock.get(0).getMedicineDosage();
        
        assertEquals(expResult, result);
    }

    /**
     * Test of setMedicineDosage method, of class MedicineStock.
     */
    @Test
    public void testSetMedicineDosage() {
        System.out.println("setMedicineDosage");
        String medDosage = "500mg / thrice per day";
        
        medStock.get(0).setMedicineDosage(medDosage);
        
        String expResult = medDosage;
        String result = medStock.get(0).getMedicineDosage();
        
        assertEquals(expResult, result);
    }

    /**
     * Test of getQuantity method, of class MedicineStock.
     */
    @Test
    public void testGetQuantity() {
        System.out.println("getQuantity");
        
        int expResult = 60;
        int result = medStock.get(0).getQuantity();
        
        assertEquals(expResult, result);
    }

    /**
     * Test of setQuantity method, of class MedicineStock.
     */
    @Test
    public void testSetQuantity() {
        System.out.println("setQuantity");
        int quantity = 40;
        
        medStock.get(1).setQuantity(quantity);
        
        int expResult = 100 + quantity;
        int result = medStock.get(1).getQuantity();
        
        assertEquals(expResult, result);
    }

    /**
     * Test of getCommonUses method, of class MedicineStock.
     */
    @Test
    public void testGetCommonUses() {
        System.out.println("getCommonUses");
        
        String expResult = "Anxiety disorders, alochol withdrawal symptoms, muscle spasms";
        String result = medStock.get(1).getCommonUses();
        
        assertEquals(expResult, result);
    }

    /**
     * Test of setCommonUses method, of class MedicineStock.
     */
    @Test
    public void testSetCommonUses() {
        System.out.println("setCommonUses");
        String commonUses = "some test uses";
        
        medStock.get(0).setCommonUses(commonUses);
        
        String expResult = commonUses;
        String result = medStock.get(0).getCommonUses();
        
        assertEquals(expResult, result);
    }

    /**
     * Test of getMedStock method, of class MedicineStock.
     */
    @Test
    public void testGetMedStock() {
        System.out.println("getMedStock");
                
        String expResult = "Lithium, 300mg / twice per day, 60, Mania, Bipolar Disorder";
        String result = (instance.getMedStock().get(0).getMedicineName() + ", " +
                instance.getMedStock().get(0).getMedicineDosage() + ", " +
                Integer.toString(instance.getMedStock().get(0).getQuantity()) + ", " +
                instance.getMedStock().get(0).getCommonUses());
        
        assertEquals(expResult, result);
    }

    /**
     * Test of setMedStock method, of class MedicineStock.
     */
    @Test
    public void testSetMedStock() {
        System.out.println("setMedStock");
        ArrayList<MedicineStock> medicines = new ArrayList<>();
        MedicineStock med = new MedicineStock();
        
        medStock.clear();
        
        med.setMedicineName("Zopaclone");
        med.setMedicineDosage("1mg before bed");
        med.setQuantity(10);
        med.setCommonUses("Insomnia");
        medicines.add(med);
        
        instance.setMedStock(medicines);
                
        String expResult = "Zopaclone, 1mg before bed, 10, Insomnia";
        String result = (instance.getMedStock().get(0).getMedicineName() + ", " +
                instance.getMedStock().get(0).getMedicineDosage() + ", " +
                Integer.toString(instance.getMedStock().get(0).getQuantity()) + ", " +
                instance.getMedStock().get(0).getCommonUses());
        
        assertEquals(expResult, result);
    }
}
