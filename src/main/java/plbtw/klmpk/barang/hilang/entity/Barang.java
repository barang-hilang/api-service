/*
 * To change this license header, choose License Headers in Project Properties. To change this
 * template file, choose Tools | Templates and open the template in the editor.
 */
package plbtw.klmpk.barang.hilang.entity;

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
@Table(name = "barang_hilang_list_barang")
public class Barang {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  @ManyToOne(cascade = CascadeType.MERGE)
  private KategoriBarang kategoriBarang;

  @ManyToOne(cascade = CascadeType.MERGE)
  private User user;

  private String nama;
  private String status;
  private int jumlah;
  private String url_image;

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }

  public String getNama() {
    return nama;
  }

  public void setNama(String nama) {
    this.nama = nama;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public int getJumlah() {
    return jumlah;
  }

  public void setJumlah(int jumlah) {
    this.jumlah = jumlah;
  }

  public String getUrl_image() {
    return url_image;
  }

  public void setUrl_image(String url_image) {
    this.url_image = url_image;
  }

  public KategoriBarang getKategoriBarang() {
    return kategoriBarang;
  }

  public void setKategoriBarang(KategoriBarang kategoriBarang) {
    this.kategoriBarang = kategoriBarang;
  }



}
