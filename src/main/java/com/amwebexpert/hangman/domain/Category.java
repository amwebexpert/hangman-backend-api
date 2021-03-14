package com.amwebexpert.hangman.domain;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "categories")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false, unique = true, nullable = false)
    Long id;

    @Column(name = "uuid", nullable = false)
    @NotNull
    UUID uuid;

    @Column(name = "lang_code", nullable = false)
    @NotBlank
    String langCode;

    @Column(name = "name", nullable = false)
    @NotBlank
    String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public String getLangCode() {
        return langCode;
    }

    public void setLangCode(String langCode) {
        this.langCode = langCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Category category1 = (Category) o;
        return id.equals(category1.id) && uuid.equals(category1.uuid) && langCode.equals(category1.langCode) && name.equals(category1.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, uuid, langCode, name);
    }

    @Override
    public String toString() {
        return "Category{" +
                "id=" + id +
                ", uuid=" + uuid +
                ", langCode='" + langCode + '\'' +
                ", category='" + name + '\'' +
                '}';
    }
}
