package io.riza.xtramile.patients.adapter.persistence;

import io.riza.xtramile.patients.adapter.persistence.model.PatientEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PatientRepository extends JpaRepository<PatientEntity, Long> {

    @Query("select p from PatientEntity p where p.firstName like %?1% or p.lastName like %?1% order by p.pid desc")
    Page<PatientEntity> findAllByName(String name, Pageable pageable);

    Page<PatientEntity> findAllByPidEquals(Long query, Pageable pageable);


}
