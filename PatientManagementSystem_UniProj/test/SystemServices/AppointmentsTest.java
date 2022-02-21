/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SystemServices;

import SystemServices.Appointments;
import java.lang.reflect.Field;
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

/**
 *
 * @author Glenn McKnight
 */
public class AppointmentsTest {
    Appointments instance;
    ArrayList<Appointments> currentAppointments;
    ArrayList<Appointments> appointmentRequests;
    
    @AfterClass
    public static void tearDownClass() {
        
        Appointments appoint1 = new Appointments();
        
        appoint1.addAppointment("D1001", "08:00", LocalDate.of(2020, Month.MARCH, 13), "P1001");
        appoint1.addAppointment("D1001", "12:30", LocalDate.of(2020, Month.OCTOBER, 9), "P1001");
        appoint1.addAppointment("D1001", "16:30", LocalDate.of(2020, Month.APRIL, 20), "P1001");
        appoint1.addAppointment("D1001", "14:00", LocalDate.of(2020, Month.NOVEMBER, 20), "P1002");
        appoint1.addAppointment("D1001", "09:00", LocalDate.of(2020, Month.NOVEMBER, 25), "P1003");
        appoint1.addAppointment("D1002", "09:30", LocalDate.of(2020, Month.DECEMBER, 18), "P1003");
        appoint1.addAppointment("D1003", "15:00", LocalDate.of(2020, Month.OCTOBER, 5), "P1003");
        appoint1.addAppointment("D1003", "16:30", LocalDate.of(2021, Month.JANUARY, 5), "P1002");
        appoint1.addAppointment("D1003", "17:30", LocalDate.of(2020, Month.JANUARY, 29), "P1002");
        
        appoint1.save();
    }
    
    @Before
    public void setUp() throws NoSuchFieldException, IllegalArgumentException, IllegalAccessException {
        instance = new Appointments();
        
        Field field = instance.getClass().getDeclaredField("currentAppointments"); 
        field.setAccessible(true);
        currentAppointments = (ArrayList<Appointments>) field.get(instance);
        currentAppointments.clear();
              
        currentAppointments.add(new Appointments("D1002", "13:00", 
                LocalDate.of(2020, Month.OCTOBER, 23), "P1021", true));
                
        Field secondField = instance.getClass().getDeclaredField("appointmentRequests"); 
        secondField.setAccessible(true);
        appointmentRequests = (ArrayList<Appointments>) secondField.get(instance);
        appointmentRequests.clear();
        
        appointmentRequests.add(new Appointments("D1011", "16:30", 
                LocalDate.of(2020, Month.OCTOBER, 12), "P1211", false));
    }
    
    @After
    public void tearDown() {
        instance = null;
        currentAppointments = null;
        appointmentRequests = null;
        
        assertNull(instance);
        assertNull(currentAppointments);
        assertNull(appointmentRequests);
    }

    /**
     * Test of requestAppointment method, of class Appointments.
     */
    @Test
    public void testRequestAppointment() {
        System.out.println("requestAppointment");
        String doctorID = "D1001";
        String time = "08:00";
        LocalDate date = LocalDate.of(2020, Month.DECEMBER, 02);
        String patientID = "P1001";
                
        instance.requestAppointment(doctorID, time, date, patientID);
        
        String expResult = doctorID + ", " + time + ", " + date + ", " + patientID;
        String result = appointmentRequests.get(1).getDoctorID() + ", " +
                appointmentRequests.get(1).getAppointmentTime() + ", " +
                appointmentRequests.get(1).getAppointmentDate() + ", " +
                appointmentRequests.get(1).getPatientID();
        assertEquals(expResult, result);
    }

    /**
     * Test of addAppointment method, of class Appointments.
     */
    @Test
    public void testAddAppointment() {
        System.out.println("addAppointment");
        String doctorID = "D1001";
        String time = "08:00";
        LocalDate date = LocalDate.of(2020, Month.DECEMBER, 02);
        String patientID = "P1001";
        
        instance.addAppointment(doctorID, time, date, patientID);
        
        String expResult = doctorID + ", " + time + ", " + date + ", " + patientID;
        String result = currentAppointments.get(1).getDoctorID() + ", " +
                currentAppointments.get(1).getAppointmentTime() + ", " +
                currentAppointments.get(1).getAppointmentDate() + ", " +
                currentAppointments.get(1).getPatientID();
        assertEquals(expResult, result);
    }

    /**
     * Test of deleteAppointment method, of class Appointments.
     */
    @Test
    public void testDeleteAppointment() {
        System.out.println("deleteAppointment");
        int currentAppointmentIndex = 0;
        
        instance.deleteAppointment(currentAppointmentIndex);
                
        ArrayList<String> expResult = new ArrayList<>();
        ArrayList<Appointments> result = currentAppointments;
        
        assertEquals(expResult, result);
    }

    /**
     * Test of declineAppointment method, of class Appointments.
     */
    @Test
    public void testDeclineAppointment() {
        System.out.println("declineAppointment");
        int requestsIndex = 0;
        
        instance.declineAppointment(requestsIndex);
        
        ArrayList<String> expResult = new ArrayList<>();
        ArrayList<Appointments> result = appointmentRequests;
        
        assertEquals(expResult, result);
    }

    /**
     * Test of save and load methods, of class Appointments.
     */
    @Test
    public void testSaveAndLoad() {
        System.out.println("save");
        
        instance.save();
        Appointments appointSerialized = new Appointments();
        appointSerialized.load();
        
        String expResult = "13:00";
        String result = appointSerialized.getCurrentAppointments().get(0).getAppointmentTime();
        
        assertEquals(expResult, result);
    }

    /**
     * Test of sortAppointments method, of class Appointments.
     */
    @Test
    public void testSortAppointments() {
        System.out.println("sortAppointments");
        
        String docID = "D1001";
        String time = "11:30";
        LocalDate date = LocalDate.of(2020, Month.MARCH, 07);
        String patID = "P1001";
        boolean approved = true;
                
        Appointments appoint1 = new Appointments(docID, time, date, patID, approved);
        instance.addAppointment(docID, time, date, patID);
        instance.sortAppointments();
        
        List<Appointments> expResult = new ArrayList<>();
        expResult.add(appoint1);
        List<Appointments> result = instance.getCurrentAppointments();
        
        assertNotEquals(expResult, result);
    }

    /**
     * Test of getPossibleTimes method, of class Appointments.
     */
    @Test
    public void testGetPossibleTimes() {
        System.out.println("getPossibleTimes");
        
        ArrayList<String> expResult = new ArrayList<>();
        expResult.add("08:00"); expResult.add("08:30"); expResult.add("09:00");
        expResult.add("09:30"); expResult.add("10:00"); expResult.add("10:30"); 
        expResult.add("11:00"); expResult.add("11:30"); expResult.add("12:00");
        expResult.add("12:30"); expResult.add("13:00"); expResult.add("13:30");
        expResult.add("14:00"); expResult.add("14:30"); expResult.add("15:00");
        expResult.add("15:30"); expResult.add("16:00"); expResult.add("16:30");
        expResult.add("17:00"); expResult.add("17:30"); expResult.add("18:00");
        expResult.add("18:30");
        
        ArrayList<String> result = instance.getPossibleTimes();
        assertEquals(expResult, result);
    }

    /**
     * Test of setApproved method, of class Appointments.
     */
    @Test
    public void testSetApproved() {
        System.out.println("setApproved");
        
        String docID = "D1001";
        String time = "13:00";
        LocalDate date = LocalDate.of(2020, Month.NOVEMBER, 29);
        String patID = "P1001";
        boolean approved = false;
        
        Appointments appoint1 = new Appointments(docID, time, date, patID, approved);
        appointmentRequests.add(appoint1);
        
        instance.setApproved(1);
        
        String expResult = docID + ", " + time + ", " + date + ", " + patID;
        String result = currentAppointments.get(1).getDoctorID() + ", " +
                currentAppointments.get(1).getAppointmentTime() + ", " +
                currentAppointments.get(1).getAppointmentDate() + ", " +
                currentAppointments.get(1).getPatientID();
        
        assertEquals(expResult, result);
    }

    /**
     * Test of getUnapprovedAppointments method, of class Appointments.
     */
    @Test
    public void testGetUnapprovedAppointments() {
        System.out.println("getUnapprovedAppointments");
        
        
        String expResult = "D1011" + ", " + "16:30" + ", " + 
                LocalDate.of(2020, Month.OCTOBER, 12).toString() + ", " + "P1211";
        String result = appointmentRequests.get(0).getDoctorID() + ", " +
                appointmentRequests.get(0).getAppointmentTime() + ", " +
                appointmentRequests.get(0).getAppointmentDate() + ", " +
                appointmentRequests.get(0).getPatientID();
        
        assertEquals(expResult, result);
    }

    /**
     * Test of getCurrentAppointments method, of class Appointments.
     */
    @Test
    public void testGetCurrentAppointments() {
        System.out.println("getCurrentAppointments");
        
        String expResult = "D1002" + ", " + "13:00" + ", " +
                LocalDate.of(2020, Month.OCTOBER, 23).toString() + ", " + "P1021";
        String result = currentAppointments.get(0).getDoctorID() + ", " +
                currentAppointments.get(0).getAppointmentTime() + ", " +
                currentAppointments.get(0).getAppointmentDate() + ", " +
                currentAppointments.get(0).getPatientID();
        
        assertEquals(expResult, result);
    }

    /**
     * Test of getAppointmentDate method, of class Appointments.
     */
    @Test
    public void testGetAppointmentDate() {
        System.out.println("getAppointmentDate");
        
        LocalDate expResult = LocalDate.of(2020, Month.OCTOBER, 23);
        LocalDate result = instance.getCurrentAppointments().get(0).getAppointmentDate();
        
        assertEquals(expResult, result);
    }

    /**
     * Test of setAppointmentDate method, of class Appointments.
     */
    @Test
    public void testSetAppointmentDate() {
        System.out.println("setAppointmentDate");
        LocalDate appointmentDate = LocalDate.of(2020, Month.AUGUST, 24);
        
        instance.getCurrentAppointments().get(0).setAppointmentDate(appointmentDate);
        
        LocalDate expResult = appointmentDate;
        LocalDate result = instance.getCurrentAppointments().get(0).getAppointmentDate();
        
        assertEquals(expResult, result);
    }

    /**
     * Test of getDoctorID method, of class Appointments.
     */
    @Test
    public void testGetDoctorID() {
        System.out.println("getDoctorID");
                
        String expResult = "D1002";        
        String result = instance.getCurrentAppointments().get(0).getDoctorID();
        
        assertEquals(expResult, result);
    }

    /**
     * Test of setDoctorID method, of class Appointments.
     */
    @Test
    public void testSetDoctorID() {
        System.out.println("setDoctorID");
        
        String doctorID = "D1787";
        
        instance.getCurrentAppointments().get(0).setDoctorID(doctorID);
        
        String expResult = doctorID;        
        String result = instance.getCurrentAppointments().get(0).getDoctorID();
        
        assertEquals(expResult, result);
    }

    /**
     * Test of getPatientID method, of class Appointments.
     */
    @Test
    public void testGetPatientID() {
        System.out.println("getPatientID");
        
        String expResult = "P1021";        
        String result = instance.getCurrentAppointments().get(0).getPatientID();
        
        assertEquals(expResult, result);
    }

    /**
     * Test of setPatientID method, of class Appointments.
     */
    @Test
    public void testSetPatientID() {
        System.out.println("setPatientID");
        
        String patID = "P1990";
        
        instance.getCurrentAppointments().get(0).setDoctorID(patID);
        
        String expResult = patID;        
        String result = instance.getCurrentAppointments().get(0).getDoctorID();
        
        assertEquals(expResult, result);
    }

    /**
     * Test of getAppointmentTime method, of class Appointments.
     */
    @Test
    public void testGetAppointmentTime() {
        System.out.println("getAppointmentTime");
        
        String expResult = "13:00";        
        String result = instance.getCurrentAppointments().get(0).getAppointmentTime();
        
        assertEquals(expResult, result);
    }

    /**
     * Test of setAppointmentTime method, of class Appointments.
     */
    @Test
    public void testSetAppointmentTime() {
        System.out.println("setAppointmentTime");
        
        String time = "P1990";
        
        instance.getCurrentAppointments().get(0).setAppointmentTime(time);
        
        String expResult = time;        
        String result = instance.getCurrentAppointments().get(0).getAppointmentTime();
        
        assertEquals(expResult, result);
    }

    /**
     * Test of isAppointmentApproved method, of class Appointments.
     */
    @Test
    public void testIsAppointmentApproved() {
        System.out.println("isAppointmentApproved");
        
        boolean expResult = true;
        boolean result = instance.getCurrentAppointments().get(0).isAppointmentApproved();
        
        assertEquals(expResult, result);
    }

    /**
     * Test of setAppointmentApproved method, of class Appointments.
     */
    @Test
    public void testSetAppointmentApproved() {
        System.out.println("setAppointmentApproved");
        
        appointmentRequests.get(0).setAppointmentApproved(true);
        
        boolean result = appointmentRequests.get(0).isAppointmentApproved();
        
        assertTrue(result);
    }
    
    /**
     * Test of getSaveFileName method, of class Appointments.
     */
    @Test
    public void testGetSaveFileName() {
        System.out.println("getSaveFileName");
        
        String expResult = instance.getClass().getName();        
        String result = instance.getSaveFileName();
        
        assertEquals(expResult, result);
    }
    
}
