/*
 * To change this license header, choose License Headers in Project Properties. To change this
 * template file, choose Tools | Templates and open the template in the editor.
 */
package plbtw.klmpk.barang.hilang.service;

import java.util.Collection;
import org.springframework.stereotype.Service;
import plbtw.klmpk.barang.hilang.entity.Pelaporan;

/**
 *
 * @author ALz
 */

public interface PelaporanService {
  Collection<Pelaporan> getAllPelaporan();

  Pelaporan getPelaporan(Long id);
  
  Pelaporan getPelaporanByPelapor(Long idUser);

  void addPelaporan(Pelaporan pelaporan);

  void updatePelaporan(Pelaporan pelaporanBaru);

  void deletePelaporan(long id);
}
