package app;

import java.sql.Connection;

public class Registration {
    String Name;
    String Password;
    boolean IsManager;

    public Registration(Connection conn,String Name, String Password) {
        this.Name = Name;
        this.Password = Password;
           searchUser(conn);
    }

    public void searchUser(Connection conn){
        DbFunctions db = new DbFunctions();
        db.search_by_user(conn,Name,Password);
    }
}
