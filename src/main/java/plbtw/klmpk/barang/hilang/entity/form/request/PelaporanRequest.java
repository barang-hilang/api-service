/*
 * To change this license header, choose License Headers in Project Properties. To change this
 * template file, choose Tools | Templates and open the template in the editor.
 */
package plbtw.klmpk.barang.hilang.entity.form.request;

import java.sql.Date;

/**
 *
 * @author ALz
 */
public class PelaporanRequest {
  private Long idPelaporan;
  private Long idBarang;
  private Long idPelapor;
  private String email, keterangan, tempatHilang, tempatDitemukan;
  private Date tanggalHilang, tanggalDitemukan;

  public Long getIdPelaporan() {
    return idPelaporan;
  }

  public void setIdPelaporan(Long idPelaporan) {
    this.idPelaporan = idPelaporan;
  }

  public Long getIdBarang() {
    return idBarang;
  }

  public void setIdBarang(Long idBarang) {
    this.idBarang = idBarang;
  }

  public Long getIdPelapor() {
    return idPelapor;
  }

  public void setIdPelapor(Long idPelapor) {
    this.idPelapor = idPelapor;
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

  public String getTempatHilang() {
    return tempatHilang;
  }

  public void setTempatHilang(String tempatHilang) {
    this.tempatHilang = tempatHilang;
  }

  public String getTempatDitemukan() {
    return tempatDitemukan;
  }

  public void setTempatDitemukan(String tempatDitemukan) {
    this.tempatDitemukan = tempatDitemukan;
  }

  public Date getTanggalHilang() {
    return tanggalHilang;
  }

  public void setTanggalHilang(Date tanggalHilang) {
    this.tanggalHilang = tanggalHilang;
  }

  public Date getTanggalDitemukan() {
    return tanggalDitemukan;
  }

  public void setTanggalDitemukan(Date tanggalDitemukan) {
    this.tanggalDitemukan = tanggalDitemukan;
  }


}
