/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dal.Base;

import dal.Commons.GetConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author HUỲNH QUANG VINH
 */
public abstract class BaseServices {

    protected PreparedStatement ps;
    
    protected ResultSet rs;
    
    protected Connection dbContext;
}
