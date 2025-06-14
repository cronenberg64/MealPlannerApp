# Meal Planner App

I developed a full-stack meal planning application with a Spring Boot backend and React Native mobile frontend as an extension to my java programming class assignment.

## Features

- User authentication and authorization
- Create, read, update, and delete meals
- Meal categorization and filtering
- User profile management
- Dietary preferences and restrictions
- Shopping list generation
- Nutritional information tracking
- Modern and intuitive mobile UI

## Tech Stack

### Backend
- Java 17
- Spring Boot 3.2.3
- Spring Security with JWT
- PostgreSQL
- JPA/Hibernate
- Maven

### Frontend
- React Native
- React Navigation
- Axios for API calls
- AsyncStorage for local storage
- React Native Vector Icons

## Prerequisites

- Java 17 or higher
- Node.js and npm
- PostgreSQL
- Android Studio (for Android development)
- Xcode (for iOS development, macOS only)
- Maven

## Getting Started

### Backend Setup

1. Clone the repository:
```bash
git clone <repository-url>
cd MealPlannerApp
```

2. Configure PostgreSQL:
   - Create a database named `mealplanner`
   - Update the database credentials in `src/backend/resources/application.yml`

3. Build and run the backend:
```bash
mvn clean install
mvn spring-boot:run
```

The backend server will start at `http://localhost:8080/api`

### Frontend Setup

1. Install dependencies:
```bash
cd src/frontend
npm install
```

2. Configure the API URL:
   - Open `src/frontend/services/api.js`
   - Update the `API_URL` constant if needed

3. Run the app:

For iOS (macOS only):
```bash
cd ios
pod install
cd ..
npx react-native run-ios
```

For Android:
```bash
npx react-native run-android
```

## Project Structure

```
MealPlannerApp/
├── src/
│   ├── backend/
│   │   ├── java/
│   │   │   └── com/mealplanner/api/
│   │   │       ├── model/
│   │   │       │   ├── Meal.java
│   │   │       │   └── User.java
│   │   │       ├── repository/
│   │   │       ├── service/
│   │   │       ├── controller/
│   │   │       └── MealPlannerApplication.java
│   │   └── resources/
│   │       └── application.yml
│   └── frontend/
│       ├── components/
│       ├── screens/
│       ├── navigation/
│       ├── services/
│       └── utils/
├── pom.xml
└── package.json
```

## API Documentation

Once the backend is running, you can access the API documentation at:
- Swagger UI: `http://localhost:8080/api/swagger-ui.html`
- OpenAPI JSON: `http://localhost:8080/api/api-docs`

## Contributing

1. Fork the repository
2. Create your feature branch (`git checkout -b feature/amazing-feature`)
3. Commit your changes (`git commit -m 'Add some amazing feature'`)
4. Push to the branch (`git push origin feature/amazing-feature`)
5. Open a Pull Request

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

## Acknowledgments

- Spring Boot team for the amazing framework
- React Native community for the mobile development tools
- All contributors who have helped shape this project


