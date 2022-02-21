/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UserModel;

import java.util.ArrayList;
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
public class UserFactoryTest {
    
    @Rule
    public ExpectedException exceptionRule = ExpectedException.none();


    /**
     * Test of addUser method, of class UserFactory.
     */
    @Test
    public void testAddUserSucceeds() {
        System.out.println("addUser");
        String userType = "A";
        Patient p = new Patient();
        Doctor d = new Doctor();
        Secretary s = new Secretary();
        Administrator a = new Administrator();
        String password = "password";
        String title = "They";
        String forename = "Chair";
        String surname = "Leg";
        int age = 0;
        String address = "";
                
        
        UserFactory instance = new UserFactory();
        instance.addUser(userType, p, d, s, a, password, title, forename, surname, age, address);
                
        Administrator result = a.getUserInfo().get(0);
        
        assertTrue(result instanceof Administrator);
    }   
}
