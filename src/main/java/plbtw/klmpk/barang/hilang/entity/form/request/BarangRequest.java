/*
 * To change this license header, choose License Headers in Project Properties. To change this
 * template file, choose Tools | Templates and open the template in the editor.
 */
package plbtw.klmpk.barang.hilang.entity.form.request;

/**
 *
 * @author ALz
 */
public class BarangRequest {
  private Long id;
  private Long idKategoriBarang;
  private Long idUserPemilik;

  private String nama, status, url_image;
  private int jumlahBarang;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Long getIdKategoriBarang() {
    return idKategoriBarang;
  }

  public void setIdKategoriBarang(Long idKategoriBarang) {
    this.idKategoriBarang = idKategoriBarang;
  }

  public Long getIdUserPemilik() {
    return idUserPemilik;
  }

  public void setIdUserPemilik(Long idUserPemilik) {
    this.idUserPemilik = idUserPemilik;
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

  public String getUrl_image() {
    return url_image;
  }

  public void setUrl_image(String url_image) {
    this.url_image = url_image;
  }

  public int getJumlahBarang() {
    return jumlahBarang;
  }

  public void setJumlahBarang(int jumlahBarang) {
    this.jumlahBarang = jumlahBarang;
  }


}
