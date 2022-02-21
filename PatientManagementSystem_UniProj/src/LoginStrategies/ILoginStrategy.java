/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LoginStrategiesModel;

/**
 * Interface for login strategies. Ensures methods are implemented to load next 
 * user interface and underlying functionality
 * @author Glenn McKnight
 */
public interface ILoginStrategy {
      
    /**
     * Load required data for user
     */
    public void loadUser();
    /**
     * Set the controller for MVC subsystem
     */
    public void setController();

    /**
     * Set model for the specified user
     */
    public void setModel();    

    /**
     * Create and set view for specified user
     */
    public void setView();     
}
