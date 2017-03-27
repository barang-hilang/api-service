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
import org.springframework.hateoas.ResourceSupport;

/**
 *
 * @author ALz
 */
@Entity
@Table(name = "barang_hilang_user")
public class User extends ResourceSupport {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long idUser;

  @ManyToOne(cascade = CascadeType.MERGE)
  private Role role;

  public Role getRole() {
    return role;
  }

  public void setRole(Role role) {
    this.role = role;
  }

  private String email, password, alamat, noHp;

  private String apiKkey;

  public long getIdUser() {
    return idUser;
  }

  public void setId(long id) {
    this.idUser = id;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getApiKkey() {
    return apiKkey;
  }

  public void setApiKkey(String key) {
    this.apiKkey = key;
  }

  public String getAlamat() {
    return alamat;
  }

  public void setAlamat(String alamat) {
    this.alamat = alamat;
  }

  public String getNoHp() {
    return noHp;
  }

  public void setNoHp(String noHp) {
    this.noHp = noHp;
  }


}
