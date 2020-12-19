package UAS.Backend;

/**
 *
 * @author Faris Ikhlasul H
 */
public class Pelanggan {
    protected String namaPelanggan;

    public Pelanggan() {
    }

    public Pelanggan(String namaPelanggan) {
        this.namaPelanggan = namaPelanggan;
    }

    public String getNamaPelanggan() {
        return namaPelanggan;
    }

    public void setNamaPelanggan(String namaPelanggan) {
        this.namaPelanggan = namaPelanggan;
    }
}