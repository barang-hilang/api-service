/*
 * To change this license header, choose License Headers in Project Properties. To change this
 * template file, choose Tools | Templates and open the template in the editor.
 */
package plbtw.klmpk.barang.hilang.service.impl;

import java.util.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import plbtw.klmpk.barang.hilang.entity.KategoriBarang;
import plbtw.klmpk.barang.hilang.repository.KategoriBarangRepository;
import plbtw.klmpk.barang.hilang.service.KategoriBarangService;

/**
 *
 * @author ALz
 */
public class KategoriBarangServiceImpl implements KategoriBarangService {

  @Autowired
  private KategoriBarangRepository kategoriBarangRepository;

  @Override
  public Collection<KategoriBarang> getAllKategoriBarang() {
    return kategoriBarangRepository.findAll();
  }

}
