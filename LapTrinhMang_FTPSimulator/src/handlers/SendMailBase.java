/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package handlers;

import java.util.concurrent.ThreadLocalRandom;
import models.MailSender;

/**
 *
 * @author HUỲNH QUANG VINH
 */
public abstract class SendMailBase {
    protected int verifyCode;
    
    public void setVerifyCode(int code){
        this.verifyCode = code;
    }
    
    public abstract void SendEmail(MailSender mailSender);
}
