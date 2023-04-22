package com.example.FirstSpring.Script;

import com.github.javafaker.Faker;
import java.sql.*;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.concurrent.TimeUnit;

public class FakerScript {
    public static void main(String[] args)  throws SQLException {

        // Establish database connection
        Connection connection = DriverManager.getConnection(
                "jdbc:postgresql://localhost:5432/employees",
                "postgres",
                "password"
        );

        // Initialize Faker library
        Faker faker = new Faker();


//        // PROJECT TABLE
//        for (int i = 1; i <= 1000000; i++) {
//            PreparedStatement preparedStatement = connection.prepareStatement(
//                    "INSERT INTO project (client_name, deadline, language, name) VALUES (?, ?, ?, ?)"
//            );
//            preparedStatement.setString(1, faker.name().fullName());
//            java.util.Date birthday = faker.date().birthday();
//            java.sql.Date sqlDate = new java.sql.Date(birthday.getTime());
//            preparedStatement.setDate(2, sqlDate);
//            preparedStatement.setString(3, faker.programmingLanguage().name());
//            preparedStatement.setString(4, faker.commerce().productName());
//            preparedStatement.executeUpdate();
//            preparedStatement.close();
//        }


//        // SPOUSE TABLE
//        for (int i = 1; i <= 1000000; i++) {
//            PreparedStatement preparedStatement = connection.prepareStatement(
//                    "INSERT INTO spouse (age, name, phone, working) VALUES (?, ?, ?, ?)"
//            );
//            preparedStatement.setInt(1, faker.number().numberBetween(18,90));
//            preparedStatement.setString(2, faker.name().fullName());
//            preparedStatement.setString(3, faker.phoneNumber().phoneNumber());
//            preparedStatement.setBoolean(4, faker.bool().bool());
//            preparedStatement.executeUpdate();
//            preparedStatement.close();
//        }

//        // EMPLOYEE TABLE
//        for (int i = 1; i <= 1000000; i++) {
//            PreparedStatement preparedStatement = connection.prepareStatement(
//                    "INSERT INTO employee (age, city, name, fk_spouse) VALUES (?, ?, ?, ?)"
//            );
//            preparedStatement.setInt(1, faker.number().numberBetween(18, 70));
//            preparedStatement.setString(2, faker.address().city());
//            preparedStatement.setString(3, faker.name().fullName());
//            preparedStatement.setInt(4, i);
//            preparedStatement.executeUpdate();
//            preparedStatement.close();
//        }

//        // ADDRESS TABLE
//        for (int i = 1; i <= 1000000; i++) {
//            PreparedStatement preparedStatement = connection.prepareStatement(
//                    "INSERT INTO address (city, country, line1, line2, state, zip_code, employee_employee_id) VALUES (?, ?, ?, ?, ?, ?, ?)"
//            );
//            preparedStatement.setString(1, faker.address().city());
//            preparedStatement.setString(2, faker.address().country());
//            preparedStatement.setString(3, faker.address().streetAddress());
//            preparedStatement.setString(4, faker.address().buildingNumber());
//            preparedStatement.setString(5, faker.address().state());
//            preparedStatement.setString(6, faker.address().zipCode());
//            preparedStatement.setInt(7, faker.number().numberBetween(1000000, 2000000));
//            preparedStatement.executeUpdate();
//            preparedStatement.close();
//        }


        // Close database connection
        connection.close();
    }
}
