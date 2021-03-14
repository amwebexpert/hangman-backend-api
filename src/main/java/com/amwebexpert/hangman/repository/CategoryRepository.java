package com.amwebexpert.hangman.repository;

import com.amwebexpert.hangman.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

    Category findByUuid(UUID uuid);

}
