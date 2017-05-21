/*
 * To change this license header, choose License Headers in Project Properties. To change this
 * template file, choose Tools | Templates and open the template in the editor.
 */
package plbtw.klmpk.barang.hilang.entity;

import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import org.springframework.hateoas.ResourceSupport;

/**
 *
 * @author ALz
 */
@Entity
@Table(name = "barang_hilang_pelaporan")
public class Pelaporan extends ResourceSupport {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(cascade = CascadeType.MERGE)
    private Barang barang;

    @ManyToOne(cascade = CascadeType.MERGE)
    private User pelapor;

    @ManyToOne(cascade = CascadeType.MERGE)
    private User penemu;

    public Pelaporan() {
    }

    public Pelaporan(Barang barang, User pelapor, String keterangan, String tempatHilang, Date tanggalHilang) {
        this.barang = barang;
        this.pelapor = pelapor;
        this.keterangan = keterangan;
        this.tempatHilang = tempatHilang;
        this.tanggalHilang = tanggalHilang;
    }

    private String keterangan;
    private String tempatHilang;
    private Date tanggalHilang;
    private String tempatDitemukan;
    private Date tanggalDitemukan;

    public User getPelapor() {
        return pelapor;
    }

    public void setPelapor(User pelapor) {
        this.pelapor = pelapor;
    }

    public User getPenemu() {
        return penemu;
    }

    public void setPenemu(User penemu) {
        this.penemu = penemu;
    }

    public String getTempatDitemukan() {
        return tempatDitemukan;
    }

    public void setTempatDitemukan(String tempatDitemukan) {
        this.tempatDitemukan = tempatDitemukan;
    }

    public Date getTanggalDitemukan() {
        return tanggalDitemukan;
    }

    public void setTanggalDitemukan(Date tanggalDitemukan) {
        this.tanggalDitemukan = tanggalDitemukan;
    }

    public String getTempatHilang() {
        return tempatHilang;
    }

    public void setTempatHilang(String tempatHilang) {
        this.tempatHilang = tempatHilang;
    }

    public Date getTanggalHilang() {
        return tanggalHilang;
    }

    public void setTanggalHilang(Date tanggalHilang) {
        this.tanggalHilang = tanggalHilang;
    }

    public long getIdPelaporan() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Barang getBarang() {
        return barang;
    }

    public void setBarang(Barang barang) {
        this.barang = barang;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }

}
