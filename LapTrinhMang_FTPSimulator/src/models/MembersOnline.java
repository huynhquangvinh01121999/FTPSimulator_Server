/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import components.Server.ListenThread;

/**
 *
 * @author HUỲNH QUANG VINH
 */
public class MembersOnline {

    private ListenThread listenThread;
    private Users users;

    public MembersOnline(ListenThread listenThread, Users users) {
        this.listenThread = listenThread;
        this.users = users;
    }

    public MembersOnline() {
    }

    public ListenThread getListenThread() {
        return listenThread;
    }

    public void setListenThread(ListenThread listenThread) {
        this.listenThread = listenThread;
    }

    public Users getUsers() {
        return users;
    }

    public void setUsers(Users users) {
        this.users = users;
    }

}
