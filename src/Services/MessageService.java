/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Iservices.IMessageService;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import Models.Message;
import Models.User;
import Technique.MyConnection;

/**
 *
 * @author Mortadhafff
 */
public class MessageService implements IMessageService {

    private Connection connection;
    private PreparedStatement ps;
    UserServices userv = new UserServices();

    public MessageService() {
        connection = MyConnection.getInstance().getConnection();
    }

    @Override
    public void add(Message message) {
        String req = "insert into message (contenu,idEmetteur) values (?,?)";
        try {
            ps = connection.prepareStatement(req);
            ps.setString(1, message.getContenu());
            ps.setInt(2, message.getEmetteur().getId()); 
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


   
    @Override
    public List<Message> getAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }


    @Override
    public List<Message> getMessageAll() {
        List<Message> messages = new ArrayList<>();
        try {
            String req = "select * FROM message ";
            ps = connection.prepareStatement(req);
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                Message message = new Message();
                message.setIdMessage(resultSet.getInt(1));
                message.setContenu(resultSet.getString(2));
                User u = userv.FindById(resultSet.getInt(3));
                message.setEmetteur(u);
                message.setDateEnvoie(resultSet.getDate(4));
                messages.add(message);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return messages;
    }

    @Override
    public void delete(Integer r) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Message FindById(Integer r) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

 
  

}
