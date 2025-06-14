import React, { useState, useEffect } from 'react';
import {
  View,
  Text,
  ScrollView,
  StyleSheet,
  Image,
  TouchableOpacity,
  ActivityIndicator,
  Alert,
} from 'react-native';
import Icon from 'react-native-vector-icons/MaterialIcons';
import { mealService } from '../services/api';

const MealDetailScreen = ({ route, navigation }) => {
  const { mealId, mealName } = route.params;
  const [meal, setMeal] = useState(null);
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    loadMealDetails();
  }, [mealId]);

  const loadMealDetails = async () => {
    try {
      setLoading(true);
      const data = await mealService.getMealById(mealId);
      setMeal(data);
    } catch (error) {
      console.error('Error loading meal details:', error);
      Alert.alert('Error', 'Failed to load meal details');
    } finally {
      setLoading(false);
    }
  };

  const handleDelete = async () => {
    Alert.alert(
      'Delete Meal',
      'Are you sure you want to delete this meal?',
      [
        {
          text: 'Cancel',
          style: 'cancel',
        },
        {
          text: 'Delete',
          style: 'destructive',
          onPress: async () => {
            try {
              await mealService.deleteMeal(mealId);
              navigation.goBack();
            } catch (error) {
              console.error('Error deleting meal:', error);
              Alert.alert('Error', 'Failed to delete meal');
            }
          },
        },
      ]
    );
  };

  if (loading) {
    return (
      <View style={styles.loadingContainer}>
        <ActivityIndicator size="large" color="#FF6B6B" />
      </View>
    );
  }

  if (!meal) {
    return (
      <View style={styles.errorContainer}>
        <Text style={styles.errorText}>Meal not found</Text>
      </View>
    );
  }

  return (
    <ScrollView style={styles.container}>
      {meal.imageUrl ? (
        <Image source={{ uri: meal.imageUrl }} style={styles.image} />
      ) : (
        <View style={styles.imagePlaceholder}>
          <Icon name="restaurant" size={60} color="#666" />
        </View>
      )}

      <View style={styles.content}>
        <View style={styles.header}>
          <Text style={styles.title}>{meal.name}</Text>
          <View style={styles.categoryContainer}>
            <Text style={styles.category}>{meal.category}</Text>
            <Text style={styles.difficulty}>{meal.difficulty}</Text>
          </View>
        </View>

        <View style={styles.metaContainer}>
          <View style={styles.metaItem}>
            <Icon name="schedule" size={20} color="#666" />
            <Text style={styles.metaText}>
              Prep: {meal.preparationTime} min
            </Text>
          </View>
          <View style={styles.metaItem}>
            <Icon name="timer" size={20} color="#666" />
            <Text style={styles.metaText}>
              Cook: {meal.cookingTime} min
            </Text>
          </View>
          <View style={styles.metaItem}>
            <Icon name="restaurant" size={20} color="#666" />
            <Text style={styles.metaText}>
              {meal.servings} servings
            </Text>
          </View>
        </View>

        <Text style={styles.sectionTitle}>Description</Text>
        <Text style={styles.description}>{meal.description}</Text>

        <Text style={styles.sectionTitle}>Ingredients</Text>
        {meal.ingredients.map((ingredient, index) => (
          <View key={index} style={styles.ingredientItem}>
            <Icon name="fiber-manual-record" size={12} color="#FF6B6B" />
            <Text style={styles.ingredientText}>{ingredient}</Text>
          </View>
        ))}

        <Text style={styles.sectionTitle}>Instructions</Text>
        {meal.instructions.map((instruction, index) => (
          <View key={index} style={styles.instructionItem}>
            <Text style={styles.instructionNumber}>{index + 1}</Text>
            <Text style={styles.instructionText}>{instruction}</Text>
          </View>
        ))}

        {meal.nutritionalInfo && (
          <>
            <Text style={styles.sectionTitle}>Nutritional Information</Text>
            <Text style={styles.nutritionalInfo}>{meal.nutritionalInfo}</Text>
          </>
        )}

        <View style={styles.buttonContainer}>
          <TouchableOpacity
            style={[styles.button, styles.editButton]}
            onPress={() => navigation.navigate('EditMeal', { mealId: meal.id })}
          >
            <Icon name="edit" size={20} color="#fff" />
            <Text style={styles.buttonText}>Edit</Text>
          </TouchableOpacity>
          <TouchableOpacity
            style={[styles.button, styles.deleteButton]}
            onPress={handleDelete}
          >
            <Icon name="delete" size={20} color="#fff" />
            <Text style={styles.buttonText}>Delete</Text>
          </TouchableOpacity>
        </View>
      </View>
    </ScrollView>
  );
};

const styles = StyleSheet.create({
  container: {
    flex: 1,
    backgroundColor: '#fff',
  },
  loadingContainer: {
    flex: 1,
    justifyContent: 'center',
    alignItems: 'center',
  },
  errorContainer: {
    flex: 1,
    justifyContent: 'center',
    alignItems: 'center',
  },
  errorText: {
    fontSize: 16,
    color: '#666',
  },
  image: {
    width: '100%',
    height: 250,
  },
  imagePlaceholder: {
    width: '100%',
    height: 250,
    backgroundColor: '#f5f5f5',
    justifyContent: 'center',
    alignItems: 'center',
  },
  content: {
    padding: 16,
  },
  header: {
    marginBottom: 16,
  },
  title: {
    fontSize: 24,
    fontWeight: 'bold',
    marginBottom: 8,
  },
  categoryContainer: {
    flexDirection: 'row',
    gap: 8,
  },
  category: {
    fontSize: 14,
    color: '#FF6B6B',
    backgroundColor: '#FFF0F0',
    paddingHorizontal: 12,
    paddingVertical: 4,
    borderRadius: 12,
  },
  difficulty: {
    fontSize: 14,
    color: '#666',
    backgroundColor: '#F5F5F5',
    paddingHorizontal: 12,
    paddingVertical: 4,
    borderRadius: 12,
  },
  metaContainer: {
    flexDirection: 'row',
    justifyContent: 'space-between',
    marginBottom: 24,
    paddingVertical: 16,
    borderTopWidth: 1,
    borderBottomWidth: 1,
    borderColor: '#eee',
  },
  metaItem: {
    flexDirection: 'row',
    alignItems: 'center',
    gap: 4,
  },
  metaText: {
    fontSize: 14,
    color: '#666',
  },
  sectionTitle: {
    fontSize: 18,
    fontWeight: 'bold',
    marginBottom: 12,
    marginTop: 24,
  },
  description: {
    fontSize: 16,
    color: '#333',
    lineHeight: 24,
  },
  ingredientItem: {
    flexDirection: 'row',
    alignItems: 'center',
    marginBottom: 8,
    gap: 8,
  },
  ingredientText: {
    fontSize: 16,
    color: '#333',
  },
  instructionItem: {
    flexDirection: 'row',
    marginBottom: 16,
    gap: 12,
  },
  instructionNumber: {
    width: 24,
    height: 24,
    backgroundColor: '#FF6B6B',
    color: '#fff',
    borderRadius: 12,
    textAlign: 'center',
    lineHeight: 24,
    fontSize: 14,
    fontWeight: 'bold',
  },
  instructionText: {
    flex: 1,
    fontSize: 16,
    color: '#333',
    lineHeight: 24,
  },
  nutritionalInfo: {
    fontSize: 16,
    color: '#333',
    lineHeight: 24,
  },
  buttonContainer: {
    flexDirection: 'row',
    justifyContent: 'space-between',
    marginTop: 32,
    gap: 16,
  },
  button: {
    flex: 1,
    flexDirection: 'row',
    alignItems: 'center',
    justifyContent: 'center',
    padding: 12,
    borderRadius: 8,
    gap: 8,
  },
  editButton: {
    backgroundColor: '#FF6B6B',
  },
  deleteButton: {
    backgroundColor: '#FF4444',
  },
  buttonText: {
    color: '#fff',
    fontSize: 16,
    fontWeight: 'bold',
  },
});

export default MealDetailScreen; 