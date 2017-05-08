/*
 * To change this license header, choose License Headers in Project Properties. To change this
 * template file, choose Tools | Templates and open the template in the editor.
 */
package plbtw.klmpk.barang.hilang.entity.form.request;

/**
 *
 * @author ALz
 */
public class RoleRequest {
  private Long idRole;
  private String role;

  public RoleRequest() {}

  public String getRole() {
    return role;
  }

  public void setRole(String role) {
    this.role = role;
  }

  public Long getId() {
    return idRole;
  }

  public void setId(Long id) {
    this.idRole = id;
  }



}
