import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DataBase {
    private String basename;
    private Connection connection;

    DataBase(){
        basename = "JavaJoinTest";

    }
    DataBase(String basename){
        this.basename = basename;
    }
    String getBasename(){
        return basename;
    }

    void createConnection(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("Connection was created!");
            String url = "jdbc:mysql://localhost/mysql";
            String user = "root";
            String password = "rjnjgtc";
            try{
                connection = DriverManager.getConnection(url,user,password);
                System.out.println("Connection created");
            }catch(SQLException s){
                s.printStackTrace();
            }
        }catch(ClassNotFoundException e){
            e.printStackTrace();
        }
    }
    void closeConnection(){
        try {
            connection.close();
            System.out.println("Connection closed");
        }catch(SQLException s){
            s.printStackTrace();
        }
    }
    void joinBase(String basename){
        try{
            Statement st = connection.createStatement();
            st.executeUpdate("USE " + basename);
            st.close();
        }catch(SQLException s){
            s.printStackTrace();
        }
    }
    void joinBase(){
        try{
            Statement st = connection.createStatement();
            st.executeUpdate("USE " + this.basename);
            st.close();
        }catch(SQLException s){
            s.printStackTrace();
        }
        System.out.println("Joined to " + this.basename);
    }
    void createTable(String tableName){
        try {
            Statement st = connection.createStatement();
            st.executeUpdate("CREATE TABLE IF NOT EXISTS " + tableName);
            st.close();
        }catch(SQLException s){
            s.printStackTrace();
        }
        System.out.println("Created table " + tableName);
    }
    void getCustomers(String tableName, String conditions){
    }
}
