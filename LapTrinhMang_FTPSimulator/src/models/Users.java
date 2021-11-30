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
public class Users implements Serializable {

    private String Email, Password, FullName, Sex, Dob, Status,
            CreateAt, FileSizeUpload, FileSizeDownload, PermissionId, AnonymousPermission;

    public Users(String Email, String Password, String FullName, String Sex, String Dob, String Status, String CreateAt, String FileSizeUpload, String FileSizeDownload, String PermissionId, String AnonymousPermission) {
        this.Email = Email;
        this.Password = Password;
        this.FullName = FullName;
        this.Sex = Sex;
        this.Dob = Dob;
        this.Status = Status;
        this.CreateAt = CreateAt;
        this.FileSizeUpload = FileSizeUpload;
        this.FileSizeDownload = FileSizeDownload;
        this.PermissionId = PermissionId;
        this.AnonymousPermission = AnonymousPermission;
    }

    public Users() {
    }

    public String getAnonymousPermission() {
        return AnonymousPermission;
    }

    public void setAnonymousPermission(String AnonymousPermission) {
        this.AnonymousPermission = AnonymousPermission;
    }

    public String getPermissionId() {
        return PermissionId;
    }

    public void setPermissionId(String PermissionId) {
        this.PermissionId = PermissionId;
    }

    public String getFileSizeUpload() {
        return FileSizeUpload;
    }

    public void setFileSizeUpload(String FileSizeUpload) {
        this.FileSizeUpload = FileSizeUpload;
    }

    public String getFileSizeDownload() {
        return FileSizeDownload;
    }

    public void setFileSizeDownload(String FileSizeDownload) {
        this.FileSizeDownload = FileSizeDownload;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String Password) {
        this.Password = Password;
    }

    public String getFullName() {
        return FullName;
    }

    public void setFullName(String FullName) {
        this.FullName = FullName;
    }

    public String getSex() {
        return Sex;
    }

    public void setSex(String Sex) {
        this.Sex = Sex;
    }

    public String getDob() {
        return Dob;
    }

    public void setDob(String Dob) {
        this.Dob = Dob;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String Status) {
        this.Status = Status;
    }

    public String getCreateAt() {
        return CreateAt;
    }

    public void setCreateAt(String CreateAt) {
        this.CreateAt = CreateAt;
    }

}
