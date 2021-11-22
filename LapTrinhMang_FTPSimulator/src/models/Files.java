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
public class Files implements Serializable {

    private String FileId, FileName, SourcePath, FileSize,
            FileExtension, Status, FolderId, UploadAt, PrexEmail;

    public Files(String FileId, String FileName, String SourcePath, String FileSize, String FileExtension, String Status, String FolderId, String UploadAt) {
        this.FileId = FileId;
        this.FileName = FileName;
        this.SourcePath = SourcePath;
        this.FileSize = FileSize;
        this.FileExtension = FileExtension;
        this.Status = Status;
        this.FolderId = FolderId;
        this.UploadAt = UploadAt;
    }

    public Files() {
    }

    public String getFileId() {
        return FileId;
    }

    public void setFileId(String FileId) {
        this.FileId = FileId;
    }

    public String getFileName() {
        return FileName;
    }

    public void setFileName(String FileName) {
        this.FileName = FileName;
    }

    public String getSourcePath() {
        return SourcePath;
    }

    public void setSourcePath(String SourcePath) {
        this.SourcePath = SourcePath;
    }

    public String getFileSize() {
        return FileSize;
    }

    public void setFileSize(String FileSize) {
        this.FileSize = FileSize;
    }

    public String getFileExtension() {
        return FileExtension;
    }

    public void setFileExtension(String FileExtension) {
        this.FileExtension = FileExtension;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String Status) {
        this.Status = Status;
    }

    public String getFolderId() {
        return FolderId;
    }

    public void setFolderId(String FolderId) {
        this.FolderId = FolderId;
    }

    public String getUploadAt() {
        return UploadAt;
    }

    public void setUploadAt(String UploadAt) {
        this.UploadAt = UploadAt;
    }

    public String getPrexEmail() {
        return PrexEmail;
    }

    public void setPrexEmail(String PrexEmail) {
        this.PrexEmail = PrexEmail;
    }

}
