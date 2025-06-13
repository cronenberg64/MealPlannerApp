package com.mealplanner.api.model;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
@Table(name = "meals")
@EntityListeners(AuditingEntityListener.class)
public class Meal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(length = 1000)
    private String description;

    @ElementCollection
    @CollectionTable(name = "meal_ingredients", joinColumns = @JoinColumn(name = "meal_id"))
    @Column(name = "ingredient")
    private List<String> ingredients;

    @ElementCollection
    @CollectionTable(name = "meal_instructions", joinColumns = @JoinColumn(name = "meal_id"))
    @Column(name = "instruction", length = 1000)
    private List<String> instructions;

    private Integer preparationTime; // in minutes
    private Integer cookingTime; // in minutes
    private Integer servings;
    private String difficulty; // EASY, MEDIUM, HARD
    private String category; // BREAKFAST, LUNCH, DINNER, SNACK

    @Column(name = "image_url")
    private String imageUrl;

    @Column(name = "nutritional_info", length = 1000)
    private String nutritionalInfo;

    @CreatedDate
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;
} 