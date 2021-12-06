/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.io.Serializable;
import java.util.List;

/**
 *
 * @author HUỲNH QUANG VINH
 */
public class UpdateResultFolderUserPermission implements Serializable {

    private String FolderIdSelected;
    private List<Folders> listFolderChild;
    private String Permission;
    private String Message;

    public UpdateResultFolderUserPermission(String FolderIdSelected, List<Folders> listFolderChild, String Permission, String Message) {
        this.FolderIdSelected = FolderIdSelected;
        this.listFolderChild = listFolderChild;
        this.Permission = Permission;
        this.Message = Message;
    }

    public UpdateResultFolderUserPermission() {
    }

    public String getFolderIdSelected() {
        return FolderIdSelected;
    }

    public void setFolderIdSelected(String FolderIdSelected) {
        this.FolderIdSelected = FolderIdSelected;
    }

    public List<Folders> getListFolderChild() {
        return listFolderChild;
    }

    public void setListFolderChild(List<Folders> listFolderChild) {
        this.listFolderChild = listFolderChild;
    }

    public String getPermission() {
        return Permission;
    }

    public void setPermission(String Permission) {
        this.Permission = Permission;
    }

    public String getMessage() {
        return Message;
    }

    public void setMessage(String Message) {
        this.Message = Message;
    }

}
