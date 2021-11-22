/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package handlers;

import java.io.File;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 *
 * @author HUỲNH QUANG VINH
 */
public interface FileBase {

    public abstract void ClientSender(ObjectOutputStream oos, File file, String destinationPath);

    public abstract void ClientSaver(ObjectInputStream ois);
    
    public abstract void ServerSaver(ObjectInputStream ois);
}
