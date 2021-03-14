package com.amwebexpert.hangman.controller;

import com.amwebexpert.hangman.domain.Category;
import com.amwebexpert.hangman.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/categories")
public class CategoryController {

    @Autowired
    CategoryService categoryService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<Page<Category>> findAll(Pageable pageable) {
        Page<Category> pagedResults = categoryService.findAll(pageable);

        if (pagedResults.isLast()) {
            return new ResponseEntity<>(pagedResults, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(pagedResults, HttpStatus.PARTIAL_CONTENT);
        }
    }

}
