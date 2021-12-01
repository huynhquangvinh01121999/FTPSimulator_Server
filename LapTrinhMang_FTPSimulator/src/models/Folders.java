/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.io.Serializable;

/**
 *
 * @author HUỲNH QUANG VINH
 */
public class Folders implements Serializable {

    private String FolderId, FolderName, FolderPath,
            Email, Size, RemainingSize, CreateAt, FolderParentId, FolderUserPermission;

    public Folders(String FolderId, String FolderName, String FolderPath,
            String Email, String Size, String RemainingSize, String CreateAt) {
        this.FolderId = FolderId;
        this.FolderName = FolderName;
        this.FolderPath = FolderPath;
        this.Email = Email;
        this.Size = Size;
        this.RemainingSize = RemainingSize;
        this.CreateAt = CreateAt;
    }

    public Folders(String FolderId) {
        this.FolderId = FolderId;
    }

    public Folders() {
    }

    public String getFolderUserPermission() {
        return FolderUserPermission;
    }

    public void setFolderUserPermission(String FolderUserPermission) {
        this.FolderUserPermission = FolderUserPermission;
    }

    public String getFolderParentId() {
        return FolderParentId;
    }

    public void setFolderParentId(String FolderParentId) {
        this.FolderParentId = FolderParentId;
    }

    public String getRemainingSize() {
        return RemainingSize;
    }

    public void setRemainingSize(String RemainingSize) {
        this.RemainingSize = RemainingSize;
    }

    public String getFolderId() {
        return FolderId;
    }

    public void setFolderId(String FolderId) {
        this.FolderId = FolderId;
    }

    public String getFolderName() {
        return FolderName;
    }

    public void setFolderName(String FolderName) {
        this.FolderName = FolderName;
    }

    public String getFolderPath() {
        return FolderPath;
    }

    public void setFolderPath(String FolderPath) {
        this.FolderPath = FolderPath;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    public String getSize() {
        return Size;
    }

    public void setSize(String Size) {
        this.Size = Size;
    }

    public String getCreateAt() {
        return CreateAt;
    }

    public void setCreateAt(String CreateAt) {
        this.CreateAt = CreateAt;
    }

}
