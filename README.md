So, this is my first attempt of constructing a full-stack web application!

- Small brief
Masterpeakers is an e-shop that sells games. Here, users, are allowed to view available games, add them to their cart and make purchases! 
Also, it includes about and contact page. 
Administrators can manage users and games with all necessary CRUD functions.

- Features
 * User authentication and authorization,
 * View availbale games
 * Add games to cart
 * Remove games from cart	    
 * Checkout games and purchase
 * Admin management for games and users
 * Send back an email confirmation to user, about successful purchase

- The technologies i used
 Backend
 * Java EE
 * Spring Boot 3.3.0
 * Hibernate
 * MySql (Workbench)
 Frontend
 * HTML/CSS
 * Thymeleaf
 * Bootstrap

- About set up
1. Clone the repository: git clone https://github.com/gerasimidisk/masterpeakers.git
2. Go to the project directory: cd masterpeakers
3. Configure the database in: src/main/resources/application.properties
4. Run the SQL script provided
5. Build the project: ./gradlew build
6. Run the application: ./gradlew bootRun