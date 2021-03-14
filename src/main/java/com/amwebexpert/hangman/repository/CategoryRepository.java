package com.amwebexpert.hangman.repository;

import com.amwebexpert.hangman.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

    Category findByNameAndLangCode(String name, String langCode);

    Category findByUuid(String uuid);

}
