package com.amwebexpert.hangman.controller;

import com.amwebexpert.hangman.domain.Category;
import com.amwebexpert.hangman.domain.Text;
import com.amwebexpert.hangman.service.CategoryService;
import com.amwebexpert.hangman.service.TextService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/categories")
public class HangmanController {

    @Autowired
    CategoryService categoryService;

    @Autowired
    TextService textService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<Page<Category>> findAll(Pageable pageable) {
        Page<Category> pagedResults = categoryService.findAll(pageable);

        if (pagedResults.isLast()) {
            return new ResponseEntity<>(pagedResults, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(pagedResults, HttpStatus.PARTIAL_CONTENT);
        }
    }

    @GetMapping(value = "/{uuid}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Category getCategory(@PathVariable("uuid") UUID uuid) {
        return categoryService.findByUuid(uuid);
    }

    @GetMapping(value = "/{uuid}/texts", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<Page<Text>> findCategoryElements(@PathVariable("uuid") UUID uuid, Pageable pageable) {
        Category category = categoryService.findByUuid(uuid);
        Page<Text> pagedResults = textService.findByCategory(category, pageable);

        if (pagedResults.isLast()) {
            return new ResponseEntity<>(pagedResults, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(pagedResults, HttpStatus.PARTIAL_CONTENT);
        }
    }

}
