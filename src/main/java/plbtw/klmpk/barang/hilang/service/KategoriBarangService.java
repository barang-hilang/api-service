/*
 * To change this license header, choose License Headers in Project Properties. To change this
 * template file, choose Tools | Templates and open the template in the editor.
 */
package plbtw.klmpk.barang.hilang.service;

import java.util.Collection;
import org.springframework.stereotype.Service;
import plbtw.klmpk.barang.hilang.entity.KategoriBarang;

/**
 *
 * @author ALz
 */

public interface KategoriBarangService {
  Collection<KategoriBarang> getAllKategoriBarang();

  KategoriBarang getKategoriBarang(long id);

  void addKategoriBarang(KategoriBarang kategoriBarang);

  void updateKategoriBarang(KategoriBarang baru);

  void deleteKategoriBarang(long id);

}
