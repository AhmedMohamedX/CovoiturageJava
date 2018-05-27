/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Iservices;

import Models.User;

/**
 *
 * @author adel
 */
public interface IUserService extends IService<User, Integer>{
    public User FindById(Integer id);
    public User findById(Integer id);
    public boolean Authentification(String login , String password);
    void setenabledtrue(int i);
    public void setConnected(int r);
    public void update(User r);
    public void setNotConnected(int r);
    public void Payer(int r,int s);
    public void GetPayer(int r,int s);
    public User FindByLogin(String id);
}
