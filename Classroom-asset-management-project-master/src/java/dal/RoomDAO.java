/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.util.ArrayList;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import models.Room;

public class RoomDAO extends DBContext {

    PreparedStatement st;
    ResultSet rs;
    
    public List<Room> getRoom(){
        List<Room> rooms=new ArrayList<>();
        try {
            
        } catch (Exception e) {
        }
    }

}
