/*
 * To change this license header, choose License Headers in Project Properties. To change this
 * template file, choose Tools | Templates and open the template in the editor.
 */
package plbtw.klmpk.barang.hilang.repository;

import java.io.Serializable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Repository;
import plbtw.klmpk.barang.hilang.entity.Barang;

/**
 *
 * @author ALz
 */
@Repository
// @PreAuthorize("hasRole('ROLE_USER')")
public interface BarangRepository extends JpaRepository<Barang, Long> {

}
