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
public class DataShare implements Serializable {

    private List<String> listIdFileShare;
    private List<String> listUserShare;
    private String idFolderShare;
    private String permissionId;
    private String fromEmail;

    // share files
    public DataShare(List<String> listIdFileShare, List<String> listUserShare, String permissionId, String fromEmail) {
        this.listIdFileShare = listIdFileShare;
        this.listUserShare = listUserShare;
        this.permissionId = permissionId;
        this.fromEmail = fromEmail;
    }
    
    // share folder
    public DataShare(String idFolderShare, List<String> listUserShare, String permissionId, String fromEmail) {
        this.listUserShare = listUserShare;
        this.idFolderShare = idFolderShare;
        this.permissionId = permissionId;
        this.fromEmail = fromEmail;
    }

    public List<String> getListIdFileShare() {
        return listIdFileShare;
    }

    public void setListIdFileShare(List<String> listIdFileShare) {
        this.listIdFileShare = listIdFileShare;
    }

    public List<String> getListUserShare() {
        return listUserShare;
    }

    public void setListUserShare(List<String> listUserShare) {
        this.listUserShare = listUserShare;
    }

    public String getIdFolderShare() {
        return idFolderShare;
    }

    public void setIdFolderShare(String idFolderShare) {
        this.idFolderShare = idFolderShare;
    }

    public String getPermissionId() {
        return permissionId;
    }

    public void setPermissionId(String permissionId) {
        this.permissionId = permissionId;
    }

    public String getFromEmail() {
        return fromEmail;
    }

    public void setFromEmail(String fromEmail) {
        this.fromEmail = fromEmail;
    }

}
