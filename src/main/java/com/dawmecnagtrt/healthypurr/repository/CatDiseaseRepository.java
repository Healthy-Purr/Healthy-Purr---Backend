package com.dawmecnagtrt.healthypurr.repository;

import com.dawmecnagtrt.healthypurr.entity.CatDisease;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CatDiseaseRepository extends JpaRepository<CatDisease,Integer> {
    List<CatDisease> findAllByDiseaseDiseaseId(Integer diseaseId);
    List<CatDisease> findAllByCatCatId(Integer catId);

    @Query("SELECT u FROM CatDisease u WHERE u.cat.catId = :catId AND u.disease.diseaseId = :diseaseId")
    Optional<CatDisease> findCatDisease(@Param("catId") Integer catId, @Param("diseaseId") Integer diseaseId);
}
