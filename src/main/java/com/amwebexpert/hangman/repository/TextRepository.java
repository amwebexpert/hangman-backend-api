package com.amwebexpert.hangman.repository;

import com.amwebexpert.hangman.domain.Category;
import com.amwebexpert.hangman.domain.Text;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TextRepository extends JpaRepository<Text, Long> {

    Page<Text> findByCategory(Category category, Pageable pageable);

}
