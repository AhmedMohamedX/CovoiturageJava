/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 *//*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Iservices;

import java.util.ArrayList;
import Models.Annonce;
import java.time.LocalDate;
import java.util.Date;
import org.joda.time.DateTime;
/**
 *
 * @author AH Info
 */
public interface Iannonce {
    public ArrayList<Annonce> selectByUser(int id);
    public Annonce selectOne(int id);
    public void insertAnnonce(Annonce e);
    public void editAnnonce(Annonce e);
    public void deleteAnnonce(Annonce e);
    public ArrayList<Annonce> selectAll();
    public    ArrayList<Annonce> findByPrix(double prix,int id);
    public ArrayList<Annonce> recherche(String vd,String va,LocalDate d);
    public ArrayList<Annonce> last(String d1,String d2,String location);
    public ArrayList<Annonce> findByLogin(String d1);
    
}
