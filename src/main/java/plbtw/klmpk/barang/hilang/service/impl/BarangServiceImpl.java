/*
 * To change this license header, choose License Headers in Project Properties. To change this
 * template file, choose Tools | Templates and open the template in the editor.
 */
package plbtw.klmpk.barang.hilang.service.impl;

import java.util.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import plbtw.klmpk.barang.hilang.entity.Barang;
import plbtw.klmpk.barang.hilang.repository.BarangRepository;
import plbtw.klmpk.barang.hilang.service.BarangService;

/**
 *
 * @author ALz
 */
public class BarangServiceImpl implements BarangService {

  @Autowired
  private BarangRepository barangRepository;

  @Override
  public Collection<Barang> getAllBarang() {
    return barangRepository.findAll();
  }

}
