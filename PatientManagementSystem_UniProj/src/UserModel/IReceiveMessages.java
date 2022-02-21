/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UserModel;

import java.util.List;

/**
 *
 * @author Glenn McKnight
 */
public interface IReceiveMessages {    
    abstract void receiveMessage(String userID, String message);
    abstract List<String> getUserMessages(String userID);
    abstract void deleteMessage(String userID, int messageIndex);
}
