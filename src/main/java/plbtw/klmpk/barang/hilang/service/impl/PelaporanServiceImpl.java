/*
 * To change this license header, choose License Headers in Project Properties. To change this
 * template file, choose Tools | Templates and open the template in the editor.
 */
package plbtw.klmpk.barang.hilang.service.impl;

import java.util.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import plbtw.klmpk.barang.hilang.entity.Pelaporan;
import plbtw.klmpk.barang.hilang.repository.PelaporanRepository;
import plbtw.klmpk.barang.hilang.service.PelaporanService;

/**
 *
 * @author ALz
 */
@Service
public class PelaporanServiceImpl implements PelaporanService {

  @Autowired
  private PelaporanRepository pelaporanRepository;

  @Override
  public Collection<Pelaporan> getAllPelaporan() {
    return pelaporanRepository.findAll();
  }

}
