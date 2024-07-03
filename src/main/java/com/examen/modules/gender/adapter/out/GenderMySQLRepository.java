package com.examen.modules.gender.adapter.out;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.examen.modules.country.domain.Country;

public class GenderMySQLRepository {
    private final String url;
    private final String user;
    private final String password;

    public CountryMySQLRepository(String url, String user, String password) {
        this.url = url;
        this.user = user;
        this.password = password;
    }

    @Override
    public void save(Country country){
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            String query = "INSERT INTO country (name) VALUES (?)";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setString(1, country.getName());
                statement.executeUpdate();
            } catch (Exception e) {
                e.getStackTrace();
            }
        } catch (SQLException e) {
            e.getStackTrace();
        }
    }

    @Override
    public void update(Country country){
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            String query = "UPDATE country SET name = ? WHERE id = ?";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setString(1, country.getName());
                statement.setInt(2, country.getId());
                statement.executeUpdate();                
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    @Override
    public Optional<Country> findById(int id){
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            String query = "SELECT id, name FROM country WHERE id = ?";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setInt(1, id);
                try (ResultSet resultSet = statement.executeQuery()) {
                    if(resultSet.next()){
                        Country model = new Country(
                            resultSet.getInt("id"),
                            resultSet.getString("name")
                        );
                        return Optional.of(model);
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
            String query = "DELETE FROM country WHERE id = ?";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setInt(1, id);
                statement.executeUpdate();
            }  
        } catch (SQLException e) {
            e.printStackTrace();;
        }
    }
    
    @Override
    public List<Country> findAll(){
        List<Country> country = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            String query = "SELECT id, name FROM country";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                try (ResultSet resultSet = statement.executeQuery()) {
                    while(resultSet.next()){
                        Country country2 = new Country(
                            resultSet.getInt("id"),
                            resultSet.getString("name")
                        );
                        country.add(country2);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return country;
    }
}
