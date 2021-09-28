package com.example.springtinemvc.repository;

import com.example.springtinemvc.controller.MovieController;

import java.sql.*;
import java.util.ArrayList;

public class MovieRepository {

    private Connection connection = DBManager.getConnection();

    public String sqlCon(String sql) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();


            if (resultSet.next()) {
                return resultSet.getString(1);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return "ERROR";
        } finally {
            //sqlnontransientconnectionexception
            //closeConnectionToSQL();
        }
        return null;
    }

    public int sqlConInt(String sql) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();


            if (resultSet.next()) {
                return resultSet.getInt(1);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return -1;
        } finally {
            //sqlnontransientconnectionexception
            //closeConnectionToSQL();
        }
        return 0;
    }

    public void closeConnectionToSQL() {
        try {
            connection.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public String getRandomMovie() {
        return sqlCon("SELECT movies.title FROM movies order by RAND() LIMIT 3");
    }

    public String getFirstMovie() {
        return sqlCon("select movies.title from movies where ID=1");
    }

    public String getTenSortByPopularity() {

        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM movies order by popularity DESC LIMIT 10");
            ResultSet resultSet = preparedStatement.executeQuery();

            ArrayList<Movie> movies = new ArrayList<>();
            while (resultSet.next()) {

                Movie movie = new Movie(
                        resultSet.getInt("ID"),
                        resultSet.getString("title"),
                        resultSet.getInt("year"),
                        resultSet.getInt("length"),
                        resultSet.getString("subject"),
                        resultSet.getInt("popularity"),
                        resultSet.getString("awards"));

                movies.add(movie);
            }

            StringBuilder stringBuilder = new StringBuilder();

            for (Movie m : movies) {
                stringBuilder.append(m.toString()).append("\n");
            }

            return stringBuilder.toString();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return "ERROR";
        } finally {
            //sqlnontransientconnectionexception
            //closeConnectionToSQL();
        }
    }

    public int howManyWonAnAward() {
        return sqlConInt("SELECT COUNT(awards) FROM movies");
    }

    public void advanced(Movie m) {
        try {

            PreparedStatement stmt = connection.prepareStatement("INSERT INTO movies(title, year, length, subject, popularity, awards) VALUE (?,?,?,?,?,?)");

            stmt.setString(1, m.getTitle());
            stmt.setInt(2, m.getYear());
            stmt.setInt(3, m.getLength());
            stmt.setString(4, m.getSubject());
            stmt.setInt(5, m.getPopularity());
            stmt.setString(6, m.getAwards());
            stmt.executeUpdate();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void test() {
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate("insert into movies(title, year, length, subject, popularity, awards) VALUE ('hallo', 1999, 100, 'drama', 10, 'yes')");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

/*
    public String sqlConInsert(String sql) {
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate(sql);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return "ERROR";
        } finally {
            //sqlnontransientconnectionexception
            //closeConnectionToSQL();
        }
        return null;
    }

 */
}
