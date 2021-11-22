/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dal.Commons;

/**
 *
 * @author HUỲNH QUANG VINH
 */
public class ConfigAuthen {

    private String user = "sa";
    private String password = "0112";

    private String forname = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
    private String url = "jdbc:sqlserver://localhost:1433;databasename=LapTrinhMang_FTPSimulator;";

    private static ConfigAuthen instance;

    public static ConfigAuthen getInstance() {
        if (instance == null) {
            instance = new ConfigAuthen();
        }
        return instance;
    }

    private ConfigAuthen() {
    }

    public String getUser() {
        return user;
    }

    public String getPassword() {
        return password;
    }

    public String getForname() {
        return forname;
    }

    public String getUrl() {
        return url;
    }
}
