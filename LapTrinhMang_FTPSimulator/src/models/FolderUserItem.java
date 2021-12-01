/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.util.List;

/**
 *
 * @author HUỲNH QUANG VINH
 */
public class FolderUserItem {

    private String Email;
    private List<Folders> folderChild;

    public FolderUserItem(String Email, List<Folders> folderChild) {
        this.Email = Email;
        this.folderChild = folderChild;
    }

    public FolderUserItem() {
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    public List<Folders> getFolderChild() {
        return folderChild;
    }

    public void setFolderChild(List<Folders> folderChild) {
        this.folderChild = folderChild;
    }

}
