/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Iservices;

import Models.threadComment;
import java.util.ArrayList;

/**
 *
 * @author AH Info
 */
public interface IthreadComment {
    public threadComment selectOne(int id);
    public void insertThreadComent(threadComment e);
    public void deleteThreadComment(threadComment e);
    public ArrayList<threadComment> selectAll();
}
