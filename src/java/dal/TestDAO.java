
package dal;


public class TestDAO extends DBContext{

    public TestDAO() {
    }
    public String TestDBConnection(){
        if (connection!=null) {
            return connection.toString();
        }else{
            return "Khong the ket not CSDL";
        }
    }
    
}
