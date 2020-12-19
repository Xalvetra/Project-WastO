package UAS.Backend;

/**
 *
 * @author Faris Ikhlasul H
 */
abstract class User {
    protected int id_user;
    protected String nama, username, password, jabatan;
    
    abstract void login(String uname, String pass);
    
    public String getInfo(){
        return "\n\nNama     : " + nama +
               "\n\nJabatan     : " + jabatan +
               "\n\tUsername : " + username;
    }
}
