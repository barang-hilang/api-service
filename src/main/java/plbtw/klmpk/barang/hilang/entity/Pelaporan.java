/*
 * To change this license header, choose License Headers in Project Properties. To change this
 * template file, choose Tools | Templates and open the template in the editor.
 */
package plbtw.klmpk.barang.hilang.entity;

import java.sql.Date;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author ALz
 */
@Entity
@Table(name = "barang_hilang_pelaporan")
public class Pelaporan {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  @ManyToOne(cascade = CascadeType.MERGE)
  private Barang barang;

  @ManyToOne(cascade = CascadeType.MERGE)
  private User pelapor;

  private String email;
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



  public long getId() {
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

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getKeterangan() {
    return keterangan;
  }

  public void setKeterangan(String keterangan) {
    this.keterangan = keterangan;
  }


}
