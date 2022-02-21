/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Serializer;

import java.beans.*;
import java.io.*;
import java.util.ArrayList;

/**
 * Serializer class to encode to and decode objects from XML format. 
 * @author Glenn McKnight
 */
public class Serializer {
       
    /**
     * Static Serializer method to store objects in file
     * @param object Object to be serialized
     * @param name Name of the finished serialized file
     */
    
    public static void serializeObject (Serializable object, String name)
    {
        try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(new File(name)))){
                        
            oos.writeObject(object);                        
            oos.close();            
            
        }catch(IOException ex){
            System.out.println(ex.toString());
        }
    }
    
    /**
     * Static Deserializer method to decode serialized objects 
     * @param name Name of the file to be deserialized.
     * @return
     */
    public static Object deserializeObject(String name){
        Object object = null;
        
        try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream(new File(name)))){
            object = ois.readObject();            
            ois.close();            
            
        }catch(IOException | ClassNotFoundException ex){  
            System.out.println(ex.toString());
        }       
        return object;
    }    
}
