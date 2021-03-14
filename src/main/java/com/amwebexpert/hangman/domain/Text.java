package com.amwebexpert.hangman.domain;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "text")
public class Text {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="text_id", updatable = false, unique = true, nullable = false)
    Long id;

    @Column(name = "uuid", nullable = false)
    @NotNull
    UUID uuid;

    @Column(name = "original", nullable = false)
    @NotBlank
    String original;

    @Column(name = "normalized", nullable = false)
    @NotBlank
    String normalized;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", nullable = false)
    Category category;

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

    public String getOriginal() {
        return original;
    }

    public void setOriginal(String original) {
        this.original = original;
    }

    public String getNormalized() {
        return normalized;
    }

    public void setNormalized(String normalized) {
        this.normalized = normalized;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Text text = (Text) o;
        return id.equals(text.id) && uuid.equals(text.uuid) && original.equals(text.original) && normalized.equals(text.normalized);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, uuid, original, normalized);
    }

    @Override
    public String toString() {
        return "Text{" +
                "id=" + id +
                ", uuid=" + uuid +
                ", original='" + original + '\'' +
                ", normalized='" + normalized + '\'' +
                '}';
    }
}
