/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UserModel;

/**
 *
 * @author Glenn McKnight
 */
public interface ILoginCheck {
    
    abstract boolean checkLogin(String userID, String password);
}
