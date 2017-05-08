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
import org.springframework.hateoas.ResourceSupport;

/**
 *
 * @author CMDDJ
 */
@Entity
@Table(name = "barang_hilang_developer")
public class Developer extends ResourceSupport {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long idDeveloper;

  @ManyToOne(cascade = CascadeType.MERGE)
  private Role role;

  private String secretKey;
  private String token;
  private String email;
  private String password;

  public Developer() {}



  public Developer(Role role, String secretKey, String token, String email, String password) {
    this.role = role;
    this.secretKey = secretKey;
    this.token = token;
    this.email = email;
    this.password = password;
  }

  public Long getIdDeveloper() {
    return idDeveloper;
  }

  public void setId(Long id) {
    this.idDeveloper = id;
  }

  public Role getRole() {
    return role;
  }

  public void setRole(Role role) {
    this.role = role;
  }

  public String getSecretKey() {
    return secretKey;
  }

  public void setSecretKey(String secretKey) {
    this.secretKey = secretKey;
  }

  public String getToken() {
    return token;
  }

  public void setToken(String token) {
    this.token = token;
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
}
