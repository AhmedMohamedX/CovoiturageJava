/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Iservices;

import java.util.List;
import Models.Message;

/**
 *
 * @author Mortadhafff
 */
public interface IMessageService extends IService<Message, Integer>{
    List<Message> getMessageAll();
}
