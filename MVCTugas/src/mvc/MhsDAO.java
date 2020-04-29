
package mvc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

public class MhsDAO {
    private int jmlData;
    private Connection koneksi;
    private Statement statement;
    
    public MhsDAO(){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://localhost/mahasiswa";
            koneksi = DriverManager.getConnection(url, "root", "");
            statement = koneksi.createStatement();
            JOptionPane.showMessageDialog(null, "Koneksi Berhasil");
        } catch (ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(null, "Class Not Found : " + ex);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "SQL Exception : " + ex);
        }
    }
    
    public void Insert(MhsModel Model){
        try{
            String query = "INSERT INTO data_mhs VALUES ('"+Model.getNama()+"','"+Model.getNim()+"','"+Model.getAlamat()+"')";
            statement.executeUpdate(query);
            JOptionPane.showMessageDialog(null, "Data Disimpan");
        } catch (SQLException sql) {
            JOptionPane.showMessageDialog(null, sql.getMessage());
        }
    }
    
    public void Update(MhsModel Model){
        try{
            String query = "UPDATE data_mhs SET nim = '"+Model.getNim()+"', nama = '"+Model.getNama()+"', alamat = '"+Model.getAlamat()+"' WHERE nim = '"+Model.getNim()+"'";
            statement.executeUpdate(query);
            JOptionPane.showMessageDialog(null, "Berhasil Terupdate");
        } catch (SQLException sql) {
            JOptionPane.showMessageDialog(null, sql.getMessage());
        }
    }
    
    public void Delete(MhsModel Model){
        try{
            String query = "DELETE FROM data_mhs WHERE nim = '"+Model.getNim()+"'";
            statement.executeUpdate(query);
            JOptionPane.showMessageDialog(null, "Data Berhasil Dihapus");
        } catch (SQLException sql) {
            JOptionPane.showMessageDialog(null, sql.getMessage());
        }
        
    }
    public String[][] readMahasiswa(){
            try{
                int jmlData = 0;
                String data[][] = new String[getJmldata()][3];
                String query = "SELECT * FROM data_mhs";
                ResultSet resultSet = statement.executeQuery(query);
                while(resultSet.next()){
                    data[jmlData][0] = resultSet.getString("nim");
                    data[jmlData][1] = resultSet.getString("nama");
                    data[jmlData][2] = resultSet.getString("alamat");
                    jmlData++;
                }
                return data;
            }catch(SQLException e){
                System.out.println(e.getMessage());
                System.out.println("SQL Error");
                return null;
            }
        }

    public int getJmldata() {
        int jmlData = 0;
        try{
            String query = "SELECT * FROM data_mhs";
                ResultSet resultSet = statement.executeQuery(query);
                while(resultSet.next()){
                    jmlData++;
                }
                return jmlData;
        }catch(SQLException e){
            System.out.println(e.getMessage());
            System.out.println("SQL Error");
            return 0;
        }
    }  
}
