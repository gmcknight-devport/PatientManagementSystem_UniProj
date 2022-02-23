/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

/**
 * Interface for the controllers. Ensures standard methods are implemented to 
 * set up controller
 * @author Glenn McKnight
 */
public interface IController {
    
    /**
     * Set UserModel in the implementing controller class.
     */
    public void setModel();

    /**
     * Close the view in the implementing controller.
     */
    public void disposeView();

    /**
     * Return boolean to check if controller has initialised view
     * @return
     */
    public boolean hasView();
    
    void addButtonHandler();
    void addWindowCloseHandler();
}
