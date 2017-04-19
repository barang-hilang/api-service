/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package plbtw.klmpk.barang.hilang.entity.form.request;

import plbtw.klmpk.barang.hilang.entity.Role;

/**
 *
 * @author CMDDJ
 */
public class DeveloperRequest {
    
  private long idDev;
  private long idrole;
  private String secretKey;
  private String token;
  private String email;
  private String password;

  
   public long getIdDeveloper() {
    return idDev;
  }

  public void setId(long idDev) {
    this.idDev = idDev;
  }
  
  public long getIdrole() {
    return idrole;
  }

  public void setIdrole(long idrole) {
    this.idrole = idrole;
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
