/*
 * To change this license header, choose License Headers in Project Properties. To change this
 * template file, choose Tools | Templates and open the template in the editor.
 */
package plbtw.klmpk.barang.hilang.service;

import java.util.Collection;
import org.springframework.stereotype.Service;
import plbtw.klmpk.barang.hilang.entity.Barang;

/**
 *
 * @author ALz
 */
public interface BarangService {
  Collection<Barang> getAllBarang();

  Barang getBarang(long id);

  void addBarang(Barang barang);
}
