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
public class Permissions implements Serializable {

    private String PermissionId, PermissionName;

    public Permissions() {
    }

    public Permissions(String PermissionId, String PermissionName) {
        this.PermissionId = PermissionId;
        this.PermissionName = PermissionName;
    }

    public String getPermissionId() {
        return PermissionId;
    }

    public void setPermissionId(String PermissionId) {
        this.PermissionId = PermissionId;
    }

    public String getPermissionName() {
        return PermissionName;
    }

    public void setPermissionName(String PermissionName) {
        this.PermissionName = PermissionName;
    }

}
