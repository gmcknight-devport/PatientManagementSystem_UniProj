/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SystemServices;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Rule;
import org.junit.rules.ExpectedException;

/**
 *
 * @author Glenn McKnight
 */
public class ServicesFacadeTest {
    
    ServicesFacade instance = ServicesFacade.getInstance();
    Appointments appointInstance = new AppointImpl();
    MedicineStock medInstance = new MedImpl();
    
    @Before
    public void setUp() throws NoSuchFieldException, IllegalArgumentException, IllegalAccessException {
                
        Field field = ServicesFacade.class.getDeclaredField("appointments");
        field.setAccessible(true);
        
        Field field2 = ServicesFacade.class.getDeclaredField("medicines");
        field2.setAccessible(true);
        
        Field modField = Field.class.getDeclaredField("modifiers");
        modField.setAccessible(true);
        modField.setInt(field, field.getModifiers() & ~Modifier.FINAL);   
        
        field.set(instance, appointInstance);
        field2.set(instance, medInstance);
    }
    
    @After
    public void tearDown() {
        instance = null;
        appointInstance = null;
        medInstance = null;
        
        assertNull(instance);
        assertNull(appointInstance);
        assertNull(medInstance);
    }
    /**
     * Rule to test mocked class throws UpsupportedOperationException t prove method
     * call is successful.
     */
    @Rule
    public ExpectedException unsupportedException = ExpectedException.none();    
    /**
     * Test of getInstance method. Class method tested, ensure method is called
     * by checking UnsupportedOperationException is thrown. Of class ServicesFacade.
     */
    @Test
    public void testGetInstance() {
        System.out.println("getInstance");
        
        ServicesFacade result = ServicesFacade.getInstance();
        assertTrue(result instanceof ServicesFacade);
    }
    
    /**
     * Test of getCurrentAppointments method. Class method tested, ensure method is called
     * by checking UnsupportedOperationException is thrown. of class ServicesFacade.
     */
    @Test
    public void testGetCurrentAppointments() {
        System.out.println("getCurrentAppointments");
        
        unsupportedException.expect(UnsupportedOperationException.class);
        
        instance.getCurrentAppointments();
        assertNotNull(instance);
    }

    /**
     * Test of getUnapprovedAppointments method. Class method tested, ensure method is called
     * by checking UnsupportedOperationException is thrown. of class ServicesFacade.
     */
    @Test
    public void testGetUnapprovedAppointments() {
        System.out.println("getUnapprovedAppointments");
        
        unsupportedException.expect(UnsupportedOperationException.class);
        
        instance.getUnapprovedAppointments();
        assertNotNull(instance);
    }

    /**
     * Test of getPossibleTimes method. Class method tested, ensure method is called
     * by checking UnsupportedOperationException is thrown. of class ServicesFacade.
     */
    @Test
    public void testGetPossibleTimes() {
        System.out.println("getPossibleTimes");
        
        unsupportedException.expect(UnsupportedOperationException.class);
        
        instance.getPossibleTimes();
        assertNotNull(instance);
    }

    /**
     * Test of requestAppointment method. Class method tested, ensure method is called
     * by checking UnsupportedOperationException is thrown.  of class ServicesFacade.
     */
    @Test
    public void testRequestAppointment() {
        System.out.println("requestAppointment");
        String docId = "thing";
        String time = "thing";
        LocalDate date = null;
        String patId = "thing";
        
        unsupportedException.expect(UnsupportedOperationException.class);
        
        instance.requestAppointment(docId, time, date, patId);
        assertNotNull(instance);
    }

    /**
     * Test of addAppointment method. Class method tested, ensure method is called
     * by checking UnsupportedOperationException is thrown.  of class ServicesFacade.
     */
    @Test
    public void testAddAppointment() {
        System.out.println("addAppointment");
        String docId = "thing";
        String time = "thing";
        LocalDate date = LocalDate.of(2020, Month.MARCH, 3);
        String patId = "thing";
        
        unsupportedException.expect(UnsupportedOperationException.class);
        
        instance.addAppointment(docId, time, date, patId);
        assertNotNull(instance);
    }

    /**
     * Test of setApproved method. Class method tested, ensure method is called
     * by checking UnsupportedOperationException is thrown.  of class ServicesFacade.
     */
    @Test
    public void testSetApproved() {
        System.out.println("setApproved");
        int appointmentIndex = 0;
        
        unsupportedException.expect(UnsupportedOperationException.class);
        
        instance.setApproved(appointmentIndex);
        assertNotNull(instance);
    }

    /**
     * Test of declineAppointment method. Class method tested, ensure method is called
     * by checking UnsupportedOperationException is thrown.  of class ServicesFacade.
     */
    @Test
    public void testDeclineAppointment() {
        System.out.println("declineAppointment");
        int appointmentIndex = 0;
        
        unsupportedException.expect(UnsupportedOperationException.class);
        
        instance.declineAppointment(appointmentIndex);
        assertNotNull(instance);
    }

    /**
     * Test of deleteAppointment method. Class method tested, ensure method is called
     * by checking UnsupportedOperationException is thrown.  of class ServicesFacade.
     */
    @Test
    public void testDeleteAppointment() {
        System.out.println("deleteAppointment");
        int appointmentIndex = 0;
        
        unsupportedException.expect(UnsupportedOperationException.class);
        
        instance.deleteAppointment(appointmentIndex);
        assertNotNull(instance);
    }

    /**
     * Test of getMedStock method. Class method tested, ensure method is called
     * by checking UnsupportedOperationException is thrown.  of class ServicesFacade.
     */
    @Test
    public void testGetMedStock() {
        System.out.println("getMedStock");
        
        unsupportedException.expect(UnsupportedOperationException.class);
        
        instance.getMedStock();
        assertNotNull(instance);
    }

    /**
     * Test of prescribeMedicine method. Class method tested, ensure method is called
     * by checking UnsupportedOperationException is thrown.  of class ServicesFacade.
     */
    @Test
    public void testPrescribeMedicine() {
        System.out.println("prescribeMedicine");
        int medicineIndex = 0;
        
        unsupportedException.expect(UnsupportedOperationException.class);
        
        instance.prescribeMedicine(medicineIndex);
        assertNotNull(instance);
    }

    /**
     * Test of getMedicineName method. Class method tested, ensure method is called
     * by checking UnsupportedOperationException is thrown.  of class ServicesFacade.
     */
    @Test
    public void testGetMedicineName() {
        System.out.println("getMedicineName");
        int medicineIndex = 0;
        
        unsupportedException.expect(UnsupportedOperationException.class);
        
        instance.getMedicineName(medicineIndex);
        assertNotNull(instance);
    }

    /**
     * Test of addMedicine method. Class method tested, ensure method is called
     * by checking UnsupportedOperationException is thrown.  of class ServicesFacade.
     */
    @Test
    public void testAddMedicine() {
        System.out.println("addMedicine");
        String medName = "thing";
        String dosage = "thing";
        String commonUses = "thing";
        
        unsupportedException.expect(UnsupportedOperationException.class);
        
        instance.addMedicine(medName, dosage, commonUses);
        assertNotNull(instance);
    }

    /**
     * Test of updateStock method. Class method tested, ensure method is called
     * by checking UnsupportedOperationException is thrown.  of class ServicesFacade.
     */
    @Test
    public void testUpdateStock() {
        System.out.println("updateStock");
        int medicineIndex = 0;
        int quantity = 0;
        
        unsupportedException.expect(UnsupportedOperationException.class);
        
        instance.updateStock(medicineIndex, quantity);
        assertNotNull(instance);
    }
    
    class AppointImpl extends Appointments{
       
        @Override
        public ArrayList<Appointments> getCurrentAppointments() {
            throw new UnsupportedOperationException();
        }        
        @Override
        public ArrayList<Appointments> getUnapprovedAppointments() {
            throw new UnsupportedOperationException();
        }    
        @Override
        public ArrayList<String> getPossibleTimes() {
            throw new UnsupportedOperationException();
        }    
        @Override
        public void requestAppointment(String doctorID, String time, LocalDate date, 
            String patientID) {
            throw new UnsupportedOperationException();
        }
        @Override
        public void declineAppointment(int requestsIndex) {
            throw new UnsupportedOperationException();
        }        
        @Override
        public void deleteAppointment(int requestsIndex) {
            throw new UnsupportedOperationException();
        }        
        @Override
        public void addAppointment(String doctorID, String time, LocalDate date, 
            String patientID) {
            throw new UnsupportedOperationException();
        }        
        @Override
        public void setApproved(int index) {
            throw new UnsupportedOperationException();
        }
    }    
    
    class MedImpl extends MedicineStock{
        
        @Override
        public ArrayList<MedicineStock> getMedStock() {
            throw new UnsupportedOperationException();
        }
        @Override
        public String prescribeMedicine(int index) {
            throw new UnsupportedOperationException();
        }
        @Override
        public String getMedicineName(int index) {
            throw new UnsupportedOperationException();
        }
        @Override
        public void addMedicine(String medName, String dosage, String patientID) {
            throw new UnsupportedOperationException();
        }
        @Override
        public boolean updateStock(int index, int quantity) {
            throw new UnsupportedOperationException();
        }
    }
}
