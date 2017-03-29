/*
 * To change this license header, choose License Headers in Project Properties. To change this
 * template file, choose Tools | Templates and open the template in the editor.
 */
package plbtw.klmpk.barang.hilang.service.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import plbtw.klmpk.barang.hilang.entity.Role;
import plbtw.klmpk.barang.hilang.repository.RoleRepository;
import plbtw.klmpk.barang.hilang.service.RoleService;

/**
 *
 * @author ALz
 */
@Service
public class RoleServiceImpl implements RoleService {

  @Autowired
  private RoleRepository roleRepository;

  @Override
  public Collection<Role> getAllRole() {
    List<Role> data = new ArrayList<Role>();
    data = roleRepository.findAll();
    return data;
  }

  @Override
  public Role getRole(long id) {
    return roleRepository.findOne(id);
  }

  @Override
  public void addRole(Role input) {
    roleRepository.save(input);
  }

  @Override
  public void updateRole(Role roleBaru) {
    roleRepository.save(roleBaru);
  }

  @Override
  public void deleteRole(long id) {
    roleRepository.delete(id);
  }

}
