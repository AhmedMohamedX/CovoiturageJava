/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Iservices;
import Models.comment;
import java.util.ArrayList;

/**
 *
 * @author AH Info
 */
public interface Icomment {
     public comment selectOne(int id);
    public void insertComment(comment e);
    public void deleteComment(comment e);
    public ArrayList<comment> selectAll();
}
