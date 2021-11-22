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
public class FileShares implements Serializable {

    private String FileId, FromEmail, ToEmail, PermissionId, ShareAt;

    public FileShares(String FileId, String FromEmail, String ToEmail, String PermissionId, String ShareAt) {
        this.FileId = FileId;
        this.FromEmail = FromEmail;
        this.ToEmail = ToEmail;
        this.PermissionId = PermissionId;
        this.ShareAt = ShareAt;
    }

    public FileShares() {
    }

    public String getFileId() {
        return FileId;
    }

    public void setFileId(String FileId) {
        this.FileId = FileId;
    }

    public String getFromEmail() {
        return FromEmail;
    }

    public void setFromEmail(String FromEmail) {
        this.FromEmail = FromEmail;
    }

    public String getToEmail() {
        return ToEmail;
    }

    public void setToEmail(String ToEmail) {
        this.ToEmail = ToEmail;
    }

    public String getPermissionId() {
        return PermissionId;
    }

    public void setPermissionId(String PermissionId) {
        this.PermissionId = PermissionId;
    }

    public String getShareAt() {
        return ShareAt;
    }

    public void setShareAt(String ShareAt) {
        this.ShareAt = ShareAt;
    }

}
