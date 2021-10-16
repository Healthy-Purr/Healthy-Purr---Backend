package com.dawmecnagtrt.healthypurr.repository;

import com.dawmecnagtrt.healthypurr.entity.CatAllergic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CatAllergicRepository extends JpaRepository<CatAllergic,Integer> {
    List<CatAllergic> findAllByAllergicAllergicId(Integer allergicId);
    List<CatAllergic> findAllByCatCatId(Integer catId);

    @Query("SELECT u FROM CatAllergic u WHERE u.cat.catId = :catId AND u.allergic.allergicId = :allergicId")
    Optional<CatAllergic> findCatAllergic(@Param("catId") Integer catId, @Param("allergicId") Integer allergicId);
}
