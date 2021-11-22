/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bll.helpers;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author HUỲNH QUANG VINH
 */
public class DateHelper {

    public static String Now() {
        SimpleDateFormat timenow = new SimpleDateFormat("HH:mm:ss");
        SimpleDateFormat day = new SimpleDateFormat("dd");
        SimpleDateFormat month = new SimpleDateFormat("MM");
        SimpleDateFormat year = new SimpleDateFormat("yyyy");
        return day.format((new java.util.Date())) + "/"
                + month.format((new java.util.Date())) + "/"
                + year.format((new java.util.Date())) + " "
                + timenow.format((new java.util.Date()));
    }
    
    public static String formatDate(Date date){
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyy");
        return sdf.format(date);
    }
}
