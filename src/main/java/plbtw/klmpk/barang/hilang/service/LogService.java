/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package plbtw.klmpk.barang.hilang.service;
import java.util.Date;
import java.util.List;
import plbtw.klmpk.barang.hilang.entity.Log;

/**
 *
 * @author CMDDJ
 */
public interface LogService {
  List<Log> getAll();  
    
  Log getLog(Long id);
  
  Long rateLimit(String apiKey);

  void addLog(Log log);

  Long countByApiKey(String apiKey);
  
  void deleteLog(long id);
}
