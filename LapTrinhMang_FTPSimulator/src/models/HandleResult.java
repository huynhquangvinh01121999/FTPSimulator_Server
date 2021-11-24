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
public class HandleResult implements Serializable {

    private boolean IsSuccessed;
    private String Message;
    private int Value;

    private Users User;
    private Folders Folder;
    private List<Folders> ListFolderChild;
    private List<Files> ListFile;
    private List<FileShares> ListFileShareses;
    private List<FolderShares> ListFolderShareses;
    private List<Permissions> ListPermissionses;

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Dùng cho authenticate">
    // 4 tham số: IsSuccessed - Message - User - Folder
    public HandleResult(boolean IsSuccessed, String Message, Users User,
            Folders Folder) {
        this.IsSuccessed = IsSuccessed;
        this.Message = Message;
        this.User = User;
        this.Folder = Folder;
    }

    // 2 tham số: ListFolderChild - ListFile
    public HandleResult(List<Folders> ListFolderChild, List<Files> ListFile) {
        this.ListFolderChild = ListFolderChild;
        this.ListFile = ListFile;
    }

    // 3 tham số: ListFileShareses - ListFolderShareses - ListPermissionses
    public HandleResult(List<FileShares> ListFileShareses, List<FolderShares> ListFolderShareses, List<Permissions> ListPermissionses) {
        this.ListFileShareses = ListFileShareses;
        this.ListFolderShareses = ListFolderShareses;
        this.ListPermissionses = ListPermissionses;
    }

    // </editor-fold>
    
    // successed: IsSuccessed - Message - Value
    public HandleResult(boolean IsSuccessed, String Message, int Value) {
        this.IsSuccessed = IsSuccessed;
        this.Message = Message;
        this.Value = Value;
    }

    // successed: IsSuccessed - Value
    public HandleResult(boolean IsSuccessed, int Value) {
        this.IsSuccessed = IsSuccessed;
        this.Value = Value;
    }

    // error: IsSuccessed - Message
    public HandleResult(boolean IsSuccessed, String Message) {
        this.IsSuccessed = IsSuccessed;
        this.Message = Message;
    }

    // success or error: IsSuccessed
    public HandleResult(boolean IsSuccessed) {
        this.IsSuccessed = IsSuccessed;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Set - Get methods">
    public List<FileShares> getListFileShareses() {
        return ListFileShareses;
    }

    public void setListFileShareses(List<FileShares> ListFileShareses) {
        this.ListFileShareses = ListFileShareses;
    }

    public List<FolderShares> getListFolderShareses() {
        return ListFolderShareses;
    }

    public void setListFolderShareses(List<FolderShares> ListFolderShareses) {
        this.ListFolderShareses = ListFolderShareses;
    }

    public List<Permissions> getListPermissionses() {
        return ListPermissionses;
    }

    public void setListPermissionses(List<Permissions> ListPermissionses) {
        this.ListPermissionses = ListPermissionses;
    }

    public List<Folders> getListFolderChild() {
        return ListFolderChild;
    }

    public void setListFolderChild(List<Folders> ListFolderChild) {
        this.ListFolderChild = ListFolderChild;
    }

    public Users getUser() {
        return User;
    }

    public void setUser(Users User) {
        this.User = User;
    }

    public Folders getFolder() {
        return Folder;
    }

    public void setFolder(Folders Folder) {
        this.Folder = Folder;
    }

    public List<Files> getListFile() {
        return ListFile;
    }

    public void setListFile(List<Files> ListFile) {
        this.ListFile = ListFile;
    }

    public int getValue() {
        return Value;
    }

    public void setValue(int Value) {
        this.Value = Value;
    }

    public boolean isSuccessed() {
        return IsSuccessed;
    }

    public void setIsSuccessed(boolean IsSuccessed) {
        this.IsSuccessed = IsSuccessed;
    }

    public String getMessage() {
        return Message;
    }

    public void setMessage(String Message) {
        this.Message = Message;
    }
    // </editor-fold>
    
}
