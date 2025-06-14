package com.mealplanner.api.service;

import com.mealplanner.api.model.Meal;
import com.mealplanner.api.model.User;
import com.mealplanner.api.repository.MealRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MealService {
    private final MealRepository mealRepository;

    @Transactional(readOnly = true)
    public List<Meal> getAllMealsByUser(User user) {
        return mealRepository.findByUser(user);
    }

    @Transactional(readOnly = true)
    public List<Meal> getMealsByCategory(User user, String category) {
        return mealRepository.findByUserAndCategory(user, category);
    }

    @Transactional(readOnly = true)
    public List<Meal> getMealsByDifficulty(User user, String difficulty) {
        return mealRepository.findByUserAndDifficulty(user, difficulty);
    }

    @Transactional(readOnly = true)
    public List<Meal> searchMeals(User user, String query) {
        return mealRepository.findByNameContainingIgnoreCaseAndUser(query, user);
    }

    @Transactional
    public Meal createMeal(Meal meal) {
        return mealRepository.save(meal);
    }

    @Transactional
    public Meal updateMeal(Long id, Meal mealDetails) {
        Meal meal = mealRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Meal not found"));
        
        meal.setName(mealDetails.getName());
        meal.setDescription(mealDetails.getDescription());
        meal.setIngredients(mealDetails.getIngredients());
        meal.setInstructions(mealDetails.getInstructions());
        meal.setPreparationTime(mealDetails.getPreparationTime());
        meal.setCookingTime(mealDetails.getCookingTime());
        meal.setServings(mealDetails.getServings());
        meal.setDifficulty(mealDetails.getDifficulty());
        meal.setCategory(mealDetails.getCategory());
        meal.setImageUrl(mealDetails.getImageUrl());
        meal.setNutritionalInfo(mealDetails.getNutritionalInfo());

        return mealRepository.save(meal);
    }

    @Transactional
    public void deleteMeal(Long id) {
        Meal meal = mealRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Meal not found"));
        mealRepository.delete(meal);
    }
} 