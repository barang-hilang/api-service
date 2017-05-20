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
import plbtw.klmpk.barang.hilang.entity.Log;
import plbtw.klmpk.barang.hilang.repository.LogRepository;
import plbtw.klmpk.barang.hilang.service.LogService;

/**
 *
 * @author CMDDJ
 */
@Service
public class LogServiceImpl implements LogService {
  @Autowired
  private LogRepository logRepository;

  @Override
  public Log getLog(Long id) {
    return logRepository.findOne(id);
  }

  @Override
  public void addLog(Log log) {
    logRepository.save(log);
  }

  @Override
  public void deleteLog(long id) {
    logRepository.delete(id);
  }

    @Override
    public Long countByApiKey(String apiKey) {
        return logRepository.countByApiKey(apiKey);
    }

    

  
}
