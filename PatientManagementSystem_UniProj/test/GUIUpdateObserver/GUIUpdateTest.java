/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUIUpdateObserver;

import java.lang.reflect.Field;
import java.lang.reflect.Type;
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
public class GUIUpdateTest {
    GUIUpdate updateInstance;
    List<GUIUpdateObserver> observers;
    ObserverTestImpl testImplInstance;
    
    @Before
    public void setUp() throws NoSuchFieldException, IllegalArgumentException, IllegalAccessException {         
        updateInstance = GUIUpdate.getInstance();
        testImplInstance = new ObserverTestImpl();
        
        Field field = updateInstance.getClass().getDeclaredField("errorObservers"); 
        field.setAccessible(true);
        observers = (ArrayList<GUIUpdateObserver>) field.get(updateInstance);
        observers.clear();
    }
    
    @After
    public void tearDown() {
        exception.equals(ExpectedException.none());
        updateInstance = null;
        observers = null;
                
        assertNull(updateInstance);
        assertNull(observers);
    }
    
    @Rule
    public ExpectedException exception = ExpectedException.none();
    /**
     * Test of getInstance method, of class GUIUpdate.
     */
    @Test
    public void testGetInstance() {
        System.out.println("getInstance");
        
        GUIUpdate result = GUIUpdate.getInstance();
        assertTrue(result instanceof GUIUpdate);
    }
    /**
     * Tests adding an observer to the list of stored observers
     */
    @Test
    public void updateObserverTest(){
        
        updateInstance.updateObserver(testImplInstance);
        
        ObserverTestImpl expResult = testImplInstance;        
        Object result = observers.get(0);
        
        assertEquals(expResult, result);
    }
    /**
     * Checks for exception to ensure update method in ObserverTestingImpl is being
     * called.
     */
    @Test
    public void updateDisplayMessageTest(){
        
        exception.expect(UnsupportedOperationException.class);
        
        updateInstance.updateObserver(testImplInstance);
        updateInstance.notifyUpdateObserver("Some message");
    }
    /**
     * Tests to ensure observer is removed from stored list.
     */  
    @Test
    public void removeUpdateObserverTest(){ 
        
        observers.add(testImplInstance);
        updateInstance.removeUpdateObserver(testImplInstance);
       
        assertTrue(observers.isEmpty());
    }
    /**
     * Checks for exception to ensure update method in ObserverTestingImpl is being
     * called.
     */
    @Test
    public void notifyUpdateObserverTest(){
        
        exception.expect(UnsupportedOperationException.class);
        
        updateInstance.updateObserver(testImplInstance);
        updateInstance.notifyUpdateObserver("Some message");
    }
    
    public class ObserverTestImpl implements  GUIUpdateObserver{
        
        public ObserverTestImpl(){
            
        }

        @Override
        public void update(String errorMessage) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }
    }
}
