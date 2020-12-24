package UAS.Backend;

/**
 *
 * @author Faris Ikhlasul H
 */
import UAS.Frontend.*;
import java.sql.ResultSet;
import java.util.ArrayList;

public class Kasir extends User{
    private int gaji;

    public Kasir(String nama, String username, String password, String jabatan, 
            int gaji) {
        super.nama = nama;
        super.username = username;
        super.password = password;
        super.jabatan = jabatan;
        this.gaji = gaji;
    }

    public Kasir() {
    }
    
    public int getGaji() {
        return gaji;
    }

    public void setGaji(int gaji) {
        this.gaji = gaji;
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

    public String jabatan() {
        return jabatan;
    }

    public void setJabatan(String jabatan) {
        this.jabatan = jabatan;
    }

    public String getJabatan() {
        return jabatan;
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
    
    public Kasir getById(int key){
        Kasir kasir = new Kasir();
        ResultSet rs = DBHelper.selectQuery("SELECT * FROM user " +
                                            "WHERE id_user = '"+ key +"'");
        try {
            while (rs.next()) {
                kasir = new Kasir();
                kasir.setId_user(rs.getInt("id_user"));
                kasir.setNama(rs.getString("nama"));
                kasir.setGaji(rs.getInt("gaji"));
                kasir.setUsername(rs.getString("username"));
                kasir.setPassword(rs.getString("password"));
                kasir.setJabatan(rs.getString("jabatan"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return kasir;
    }
    
    public ArrayList<Kasir> getAll(){
        ArrayList<Kasir> listKasir = new ArrayList();
        ResultSet rs = DBHelper.selectQuery("SELECT * FROM user");
        try {
            while (rs.next()) {
                Kasir kasir = new Kasir();
                kasir.setId_user(rs.getInt("id_user"));
                kasir.setNama(rs.getString("nama"));
                kasir.setGaji(rs.getInt("gaji"));
                kasir.setUsername(rs.getString("username"));
                kasir.setPassword(rs.getString("password"));
                kasir.setJabatan(rs.getString("jabatan"));
                listKasir.add(kasir);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listKasir;
    }
    
    public ArrayList<Kasir> search(String key){
        ArrayList<Kasir> listKasir = new ArrayList();
        ResultSet rs = DBHelper.selectQuery("SELECT * FROM user WHERE " +
                                            "nama like '%"+ key +"%' OR " +
                                            "username like '%"+ key +"%'");
        try {
            while (rs.next()) {
                Kasir kasir = new Kasir();
                kasir.setId_user(rs.getInt("id_user"));
                kasir.setNama(rs.getString("nama"));
                kasir.setGaji(rs.getInt("gaji"));
                kasir.setUsername(rs.getString("username"));
                kasir.setPassword(rs.getString("password"));
                kasir.setJabatan(rs.getString("jabatan"));
                listKasir.add(kasir);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listKasir;
    }
    
    public void save(){
        if (getById(id_user).getId_user() == 0) {
            String SQL = "INSERT INTO user (nama, gaji, username, password, jabatan) VALUES ("
                        + " '"+this.nama+"', "
                        + " '"+this.gaji+"',"
                        + " '"+this.username+"', "
                        + " '"+this.password+"', "
                        + " '"+this.jabatan+"' "
                        + " )";
            this.id_user = DBHelper.insertQueryGetId(SQL);
        }else{
            String SQL = "UPDATE user SET "
                        + " nama = '"+this.nama+"', "
                        + " gaji = '"+this.gaji+"', "
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
                if (rs.getString("jabatan").equals("kasir")) {
                    new KasirMain(getId_user(), getJabatan()).setVisible(true);
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