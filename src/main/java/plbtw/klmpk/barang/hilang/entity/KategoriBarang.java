/*
 * To change this license header, choose License Headers in Project Properties. To change this
 * template file, choose Tools | Templates and open the template in the editor.
 */
package plbtw.klmpk.barang.hilang.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.hateoas.ResourceSupport;

/**
 *
 * @author ALz
 */
@Entity
@Table(name = "barang_hilang_kategori_barang")
public class KategoriBarang extends ResourceSupport {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String jenis;


    public KategoriBarang(Long id, String jenis) {
        this.id = id;
        this.jenis = jenis;
    }
    
  public KategoriBarang(String jenis) {
      this.jenis=jenis;
  }

    public KategoriBarang() {
    }
  
  

  public long getIdKategoriBarang() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getJenis() {
    return jenis;
  }

  public void setJenis(String jenis) {
    this.jenis = jenis;
  }


}
