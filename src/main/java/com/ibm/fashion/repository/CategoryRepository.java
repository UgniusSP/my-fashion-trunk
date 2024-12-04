package com.ibm.fashion.repository;

import com.ibm.fashion.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    @Query("SELECT c.name FROM Category c WHERE c.isAllowed = :isAllowed")
    List<String> findAllByIsAllowed(@Param("isAllowed") boolean isAllowed);
}
