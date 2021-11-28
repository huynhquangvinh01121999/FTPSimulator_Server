/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package handlers;

import components.ListenThread;
import models.ObjectRequest;

/**
 *
 * @author HUỲNH QUANG VINH
 */
public abstract class HandleBase {

    protected ListenThread listenThread;

    public void setListenThread(ListenThread listenThread) {
        this.listenThread = listenThread;
    }

//    public abstract void handleRequest(ObjectRequest message);
//    public abstract void handleRequest(String message);
    public abstract void handleRequest(String[] data);

}
