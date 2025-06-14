package com.mealplanner.api.repository;

import com.mealplanner.api.model.Meal;
import com.mealplanner.api.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MealRepository extends JpaRepository<Meal, Long> {
    List<Meal> findByUser(User user);
    List<Meal> findByUserAndCategory(User user, String category);
    List<Meal> findByUserAndDifficulty(User user, String difficulty);
    List<Meal> findByNameContainingIgnoreCaseAndUser(String name, User user);
} 