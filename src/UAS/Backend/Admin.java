package UAS.Backend;

/**
 *
 * @author Faris Ikhlasul H
 */
import UAS.Frontend.AdminMain;
import java.sql.ResultSet;
import java.util.ArrayList;

public class Admin extends User{

    public Admin(String nama, String username, String password, String jabatan) {
        super.nama = nama;
        super.username = username;
        super.password = password;
        super.jabatan = jabatan;
    }

    public Admin() {
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getJabatan() {
        return jabatan;
    }

    public void setJabatan(String jabatan) {
        this.jabatan = jabatan;
    }
    
    public Admin getById(int key){
        Admin admin = new Admin();
        ResultSet rs = DBHelper.selectQuery("SELECT * FROM user " +
                                            "WHERE id_user = '"+ key +"'");
        try {
            while (rs.next()) {
                admin = new Admin();
                admin.setId_user(rs.getInt("id_user"));
                admin.setNama(rs.getString("nama"));
                admin.setUsername(rs.getString("usernmae"));
                admin.setPassword(rs.getString("password"));
                admin.setJabatan(rs.getString("jabatan"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return admin;
    }
    
    public ArrayList<Admin> getAll(){
        ArrayList<Admin> listAdmin = new ArrayList();
        ResultSet rs = DBHelper.selectQuery("SELECT * FROM user");
        try {
            while (rs.next()) {
                Admin admin = new Admin();
                admin.setId_user(rs.getInt("id_user"));
                admin.setNama(rs.getString("nama"));
                admin.setUsername(rs.getString("usernmae"));
                admin.setPassword(rs.getString("password"));
                admin.setJabatan(rs.getString("jabatan"));
                listAdmin.add(admin);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listAdmin;
    }
    
    public ArrayList<Admin> search(String key){
        ArrayList<Admin> listAdmin = new ArrayList();
        ResultSet rs = DBHelper.selectQuery("SELECT * FROM user WHERE " +
                                            "nama like '%"+ key +"%' OR " +
                                            "username like '%"+ key +"%'");
        try {
            while (rs.next()) {
                Admin admin = new Admin();
                admin.setId_user(rs.getInt("id_user"));
                admin.setNama(rs.getString("nama"));
                admin.setUsername(rs.getString("usernmae"));
                admin.setPassword(rs.getString("password"));
                admin.setJabatan(rs.getString("jabatan"));
                listAdmin.add(admin);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listAdmin;
    }
    
    public void save(){
        if (getById(id_user).getId_user() == 0) {
            String SQL = "INSERT INTO user (nama, username, password, jabatan) VALUES ("
                        + " '"+this.nama+"', "
                        + " '"+this.username+"', "
                        + " '"+this.password+"', "
                        + " '"+this.jabatan+"' "
                        + " )";
            this.id_user = DBHelper.insertQueryGetId(SQL);
        }else{
            String SQL = "UPDATE user SET "
                        + " nama = '"+this.nama+"', "   
                        + " username = '"+this.username+"', "
                        + " password = '"+this.password+"', "
                        + " jabatan = '"+this.jabatan+"', "
                        + " WHERE id_user = '"+this.id_user+"' ";
            DBHelper.executeQuery(SQL);
        }
    }
    
    public void delete() {
        String SQL = "DELETE FROM user WHERE id_user = '"+this.id_user+"'";
        DBHelper.executeQuery(SQL);
    }
   

    @Override
    public void login(String uname, String pass) {
        String SQL = "SELECT * FROM user WHERE username = '"+ uname +"'" +
                     " AND password = '"+ pass +"'";
        ResultSet rs = DBHelper.selectQuery(SQL);
        try {
            while (rs.next()) { 
                setId_user(rs.getInt("id_user"));
                setJabatan(rs.getString("jabatan"));
                if (rs.getString("jabatan").equals("admin")) {
                    new AdminMain(getId_user(), getJabatan()).setVisible(true);
                }else{
                    System.out.println("halo");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public String getInfo(){
        return "Info Kasir : " + super.getInfo();
    }
    
}
