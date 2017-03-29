/*
 * To change this license header, choose License Headers in Project Properties. To change this
 * template file, choose Tools | Templates and open the template in the editor.
 */
package plbtw.klmpk.barang.hilang.service;

import java.util.Collection;
import plbtw.klmpk.barang.hilang.entity.Role;

/**
 *
 * @author ALz
 */

public interface RoleService {
  Collection<Role> getAllRole();

  Role getRole(long id);

  void addRole(Role input);

  void updateRole(Role roleBaru);

  void deleteRole(long id);
}
