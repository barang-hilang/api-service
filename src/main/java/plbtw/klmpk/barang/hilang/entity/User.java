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
  private Long idUser;

  @ManyToOne(cascade = CascadeType.MERGE)
  private Developer dev;

  private String email, username, password, alamat, noHp;

  private String apiKey;

    public User() {
    }

    public User(Long idUser, Developer dev, String email, String username, String password, String alamat, String noHp, String apiKey) {
        this.idUser = idUser;
        this.dev = dev;
        this.email = email;
        this.username = username;
        this.password = password;
        this.alamat = alamat;
        this.noHp = noHp;
        this.apiKey = apiKey;
    }
  
  public User(Developer dev, String email, String username, String password, String alamat, String noHp, String apiKey) {
        this.dev = dev;
        this.email = email;
        this.username = username;
        this.password = password;
        this.alamat = alamat;
        this.noHp = noHp;
        this.apiKey = apiKey;
    }
  

  public Developer dev() {
    return dev;
  }

  public void setDev(Developer dev) {
    this.dev = dev;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getApiKey() {
    return apiKey;
  }

  public void setApiKey(String apiKey) {
    this.apiKey = apiKey;
  }

  public Long getIdUser() {
    return idUser;
  }

  public void setId(Long id) {
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
    return apiKey;
  }

  public void setApiKkey(String key) {
    this.apiKey = key;
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
