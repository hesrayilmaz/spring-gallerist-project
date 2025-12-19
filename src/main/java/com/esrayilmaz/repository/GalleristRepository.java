package com.esrayilmaz.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.esrayilmaz.model.Gallerist;

@Repository
public interface GalleristRepository extends JpaRepository<Gallerist, Long> {

}
