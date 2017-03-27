/*
 * To change this license header, choose License Headers in Project Properties. To change this
 * template file, choose Tools | Templates and open the template in the editor.
 */
package plbtw.klmpk.barang.hilang.controller;

import java.util.Collection;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import plbtw.klmpk.barang.hilang.entity.Role;
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
}
