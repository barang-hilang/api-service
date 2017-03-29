/*
 * To change this license header, choose License Headers in Project Properties. To change this
 * template file, choose Tools | Templates and open the template in the editor.
 */
package plbtw.klmpk.barang.hilang.service.impl;

import java.util.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import plbtw.klmpk.barang.hilang.entity.Barang;
import plbtw.klmpk.barang.hilang.repository.BarangRepository;
import plbtw.klmpk.barang.hilang.service.BarangService;

/**
 *
 * @author ALz
 */
@Service
public class BarangServiceImpl implements BarangService {

  @Autowired
  private BarangRepository barangRepository;

  @Override
  public Collection<Barang> getAllBarang() {
    return barangRepository.findAll();
  }

  @Override
  public Barang getBarang(long id) {
    return barangRepository.findOne(id);
  }

  @Override
  public void addBarang(Barang barang) {
    barangRepository.save(barang);
  }

  @Override
  public void updateBarang(Barang barangBaru) {
    barangRepository.save(barangBaru);
  }

  @Override
  public void deleteBarang(long id) {
    barangRepository.delete(id);
  }

}
