public class DataBase {
    private String basename;

    DataBase(){
        basename = "test";
    }
    DataBase(String basename){
        this.basename = basename;
    }

    void createConnection(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("Connection was created!");
        }catch(ClassNotFoundException e){
            e.printStackTrace();
        }
    }
}
