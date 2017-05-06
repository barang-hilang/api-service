/*
 * To change this license header, choose License Headers in Project Properties. To change this
 * template file, choose Tools | Templates and open the template in the editor.
 */
package plbtw.klmpk.barang.hilang.service;

import java.util.Collection;
import org.springframework.stereotype.Service;
import plbtw.klmpk.barang.hilang.entity.Developer;

/**
 *
 * @author CMDDJ
 */
public interface DeveloperService {
  Collection<Developer> getAllDevelopers();

  Developer getDeveloper(long id);

  Developer getDeveloperByApiKey(String token);
  
  Developer getDeveloperByEmailAndPassword(String email,String password);

  void addDeveloper(Developer dev);

  void updateDeveloper(Developer devbaru);

  void deleteDeveloper(long id);
}
