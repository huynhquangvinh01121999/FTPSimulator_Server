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
 * @param <T>
 */
public class ObjectRequest<T> implements Serializable{

    private String message;
    private T Object;
    private T FileUpload;
    
    public ObjectRequest(String message) {
        this.message = message;
    }

    public ObjectRequest(String message, T Object) {
        this.message = message;
        this.Object = Object;
    }
    
    public ObjectRequest(String message, T Object, T FileUpload) {
        this.message = message;
        this.Object = Object;
        this.FileUpload = FileUpload;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getObject() {
        return Object;
    }

    public void setObject(T Object) {
        this.Object = Object;
    }

    public T getFileUpload() {
        return FileUpload;
    }

    public void setFileUpload(T FileUpload) {
        this.FileUpload = FileUpload;
    }

}
