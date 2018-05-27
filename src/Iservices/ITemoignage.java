/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Iservices;

import Models.temoignage;
import java.util.ArrayList;

/**
 *
 * @author Hard-System-Info
 */
public interface ITemoignage{
        public void add(temoignage t);
        public void delete(Integer id);
        public void update(temoignage t);
    public ArrayList<temoignage> selectAll();
    public temoignage FindById(Integer id) ;
    
}
