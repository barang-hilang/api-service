/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package plbtw.klmpk.barang.hilang.repository;
import java.io.Serializable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import plbtw.klmpk.barang.hilang.entity.Log;
/**
 *
 * @author CMDDJ
 */
@Repository
public interface LogRepository extends JpaRepository<Log, Long> {  
}