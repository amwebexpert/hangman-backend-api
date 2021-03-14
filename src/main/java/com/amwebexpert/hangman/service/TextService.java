package com.amwebexpert.hangman.service;

import com.amwebexpert.hangman.domain.Category;
import com.amwebexpert.hangman.domain.Text;
import com.amwebexpert.hangman.repository.TextRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class TextService {

    @Autowired
    TextRepository textRepository;

    @Transactional(readOnly = true)
    public Page<Text> findAll(Pageable pageable) {
        return textRepository.findAll(pageable);
    }

    @Transactional(readOnly = true)
    public Page<Text> findByCategory(Category category, Pageable pageable) {
        return textRepository.findByCategory(category, pageable);
    }

}
