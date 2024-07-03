package com.examen.modules.gender.adapter.out;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.examen.modules.gender.domain.Gender;
import com.examen.modules.gender.infrastructure.GenderRepository;

public class GenderMySQLRepository implements GenderRepository {
    private final String url;
    private final String user;
    private final String password;

    public GenderMySQLRepository(String url, String user, String password) {
        this.url = url;
        this.user = user;
        this.password = password;
    }

    @Override
    public void save(Gender gender){
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            String query = "INSERT INTO gender (type) VALUES (?)";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setString(1, gender.getType());
                statement.executeUpdate();
            } catch (Exception e) {
                e.getStackTrace();
            }
        } catch (SQLException e) {
            e.getStackTrace();
        }
    }

    @Override
    public void update(Gender gender){
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            String query = "UPDATE gender SET type = ? WHERE id = ?";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setString(1, gender.getType());
                statement.setInt(2, gender.getId());
                statement.executeUpdate();                
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    @Override
    public Optional<Gender> findById(int id){
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            String query = "SELECT id, type FROM gender WHERE id = ?";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setInt(1, id);
                try (ResultSet resultSet = statement.executeQuery()) {
                    if(resultSet.next()){
                        Gender gender = new Gender(
                            resultSet.getInt("id"),
                            resultSet.getString("type")
                        );
                        return Optional.of(gender);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    return Optional.empty();
    }
    
    @Override
    public void delete(int id){
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            String query = "DELETE FROM gender WHERE id = ?";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setInt(1, id);
                statement.executeUpdate();
            }  
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    @Override
    public List<Gender> findAll(){
        List<Gender> gender = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            String query = "SELECT id, type FROM gender";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                try (ResultSet resultSet = statement.executeQuery()) {
                    while(resultSet.next()){
                        Gender gender2 = new Gender(
                            resultSet.getInt("id"),
                            resultSet.getString("type")
                        );
                        gender.add(gender2);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return gender;
    }
}
