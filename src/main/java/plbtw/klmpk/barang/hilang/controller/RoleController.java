/*
 * To change this license header, choose License Headers in Project Properties. To change this
 * template file, choose Tools | Templates and open the template in the editor.
 */
package plbtw.klmpk.barang.hilang.controller;

import java.util.Collection;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import plbtw.klmpk.barang.hilang.entity.Role;
import plbtw.klmpk.barang.hilang.entity.User;
import plbtw.klmpk.barang.hilang.entity.form.request.RoleRequest;
import plbtw.klmpk.barang.hilang.entity.form.request.UserRequest;
import plbtw.klmpk.barang.hilang.message.CustomResponseMessage;
import plbtw.klmpk.barang.hilang.service.RoleService;

/**
 *
 * @author ALz
 */
@RestController
@RequestMapping(value = "/api/v1/roles")
public class RoleController {
  @Autowired
  private RoleService roleService;

  @RequestMapping(method = RequestMethod.GET, produces = "application/json")
  public Collection<Role> getAllUser() {
    return roleService.getAllRole();
  }

  @RequestMapping(value = "find/{id}", method = RequestMethod.GET, produces = "application/json")
  public Role find(@PathVariable("id") long id) {
    return roleService.getRole(id);
  }

  @RequestMapping(method = RequestMethod.POST, produces = "application/json")
  public CustomResponseMessage addRole(@RequestBody RoleRequest roleRequest) {
    try {
      Role role = new Role();
      role.setRole(roleRequest.getRole());
      roleService.addRole(role);
      return new CustomResponseMessage(200, "Role Has Been Created");
    } catch (Exception ex) {
      return new CustomResponseMessage(201, ex.toString());
    }
  }

  @RequestMapping(method = RequestMethod.PUT, produces = "application/json")
  public CustomResponseMessage updateRole(@RequestBody RoleRequest roleRequest) {
    try {
      Role role = roleService.getRole(roleRequest.getId());
      role.setRole(roleRequest.getRole());
      roleService.updateRole(role);
      return new CustomResponseMessage(200, "Update Role Succesfull");
    } catch (Exception ex) {
      return new CustomResponseMessage(201, ex.toString());
    }
  }

  @RequestMapping(method = RequestMethod.DELETE, produces = "application/json")
  public CustomResponseMessage deleteRole(@RequestBody RoleRequest roleRequest) {
    try {
      roleService.deleteRole(roleRequest.getId());
      return new CustomResponseMessage(200, "Detele Successfull");
    } catch (Exception ex) {
      return new CustomResponseMessage(201, ex.toString());
    }
  }
}
