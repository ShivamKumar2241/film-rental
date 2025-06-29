package com.filmrental.model.entity;

import jakarta.persistence.*;
import lombok.Data;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.Year;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "film")
@Data
public class Film {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "film_id")
    private Integer filmId;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "release_year")
    private Year releaseYear;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "language_id", nullable = false)
    private Language language;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "original_language_id")
    private Language originalLanguage;

    @Column(name = "rental_duration", nullable = false)
    private Integer rentalDuration;

    @Column(name = "rental_rate", nullable = false)
    private BigDecimal rentalRate;

    @Column(name = "length")
    private Integer length;

    @Column(name = "replacement_cost", nullable = false)
    private BigDecimal replacementCost;

    @Column(name = "rating")
    private String rating;

    @Column(name = "special_features")
    private String specialFeatures;

    @Column(name = "last_update", nullable = false)
    private LocalDateTime lastUpdate;

    @ManyToMany(mappedBy = "films", fetch = FetchType.LAZY)
    @JsonManagedReference
    private Set<Actor> actors = new HashSet<>();

    @OneToMany(mappedBy = "film", fetch = FetchType.LAZY)
    private List<FilmCategory> filmCategories;

    @OneToMany(mappedBy = "film", fetch = FetchType.LAZY)
    private List<Inventory> inventories;

    @PrePersist
    @PreUpdate
    private void updateTimestamp() {
        this.lastUpdate = LocalDateTime.now();
    }
}