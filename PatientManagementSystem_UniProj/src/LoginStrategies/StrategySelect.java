/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LoginStrategiesModel;

import LoginStrategiesModel.*;
import UserModel.UserTypes;

/**
 * Class to select the strategy to load the next MVC subsystem for specified user.
 * @author Glenn McKnight
 */
public class StrategySelect {
    
    /**
     * Selects the required strategy using the parameter. 
     * @param userType String name of selected user class
     * @return the selected login strategy
     */
    public ILoginStrategy selectStrategy(String userType){
        
        switch(UserTypes.valueOf(userType.substring(0, 1).toUpperCase())){
            case P:                
                return new PatientStrategy();
            case D:
                return new DoctorStrategy();
            case A:
                return new AdminStrategy();
            case S: 
                return new SecretaryStrategy();
            default:
                 return new LoggedOutStrategy();
        }        
    }
}
