/*package repository.dbRepository;

import domain.Movie;
import domain.validators.SQLRepoException;
import domain.validators.Validator;
import domain.validators.ValidatorException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class MovieDBRepository implements SortingRepository<Integer, Movie> {
    @Autowired
    private Validator<Movie> validator;
    private Connection connection;

    public MovieDBRepository(Validator<Movie> validator, Connection connection) {
        this.validator = validator;
        this.connection = connection;
    }

    @Override
    public Optional<Movie> findOne(Integer o) {
        String sqlString = "select * from movie where id=?;";
        Movie movie = new Movie();
        try {
            PreparedStatement preparedStatement = this.connection.prepareStatement(sqlString);
            preparedStatement.setInt(1, o);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                movie.setId(resultSet.getInt("id"));
                movie.setTitle(resultSet.getString("title"));
                movie.setGenre(resultSet.getString("genre"));
                movie.setYear(resultSet.getInt("year"));
            }
            return Optional.of(movie);
        } catch (SQLException e) {
            throw new SQLRepoException(e.getMessage(), e);
        }
    }

    @Override
    public Iterable<Movie> findAll() {
        String sqlString = "select * from movie;";
        List<Movie> result = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = this.connection.prepareStatement(sqlString);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Movie movie = new Movie();
                movie.setId(resultSet.getInt("id"));
                movie.setTitle(resultSet.getString("title"));
                movie.setGenre(resultSet.getString("genre"));
                movie.setYear(resultSet.getInt("year"));
                result.add(movie);
            }
        } catch (SQLException e) {
            throw new SQLRepoException(e.getMessage(), e);
        }
        return result;
    }

    @Override
    public Optional<Movie> save(Movie entity) throws ValidatorException {
        Optional.ofNullable(entity).orElseThrow(() -> new IllegalArgumentException("Entity must not be null"));
        validator.validate(entity);
        String sqlString = "insert into movie(id, title, genre, year) values (?,?,?,?);";
        try {
            PreparedStatement preparedStatement = this.connection.prepareStatement(sqlString);
            preparedStatement.setInt(1, entity.getId());
            preparedStatement.setString(2, entity.getTitle());
            preparedStatement.setString(3, entity.getGenre());
            preparedStatement.setInt(4, entity.getYear());
            preparedStatement.executeUpdate();
            return Optional.empty();
        } catch (SQLException e) {
            throw new SQLRepoException(e.getMessage(), e);
        }
    }

    @Override
    public Optional<Movie> delete(Integer o) {
        String sqlString = "delete from movie where id=?;";
        String selectString = "select * from movie where id=?;";
        Movie movie = null;
        try {
            PreparedStatement selectStatement = this.connection.prepareStatement(selectString);
            selectStatement.setInt(1, o);
            ResultSet resultSet = selectStatement.executeQuery();
            while (resultSet.next()) {
                movie = new Movie();
                movie.setId(resultSet.getInt("id"));
                movie.setTitle(resultSet.getString("title"));
                movie.setGenre(resultSet.getString("genre"));
                movie.setYear(resultSet.getInt("year"));
            }
            PreparedStatement preparedStatement = this.connection.prepareStatement(sqlString);
            preparedStatement.setInt(1, o);
            preparedStatement.executeUpdate();
            return Optional.ofNullable(movie);
        } catch (SQLException e) {
            throw new SQLRepoException(e.getMessage(), e);
        }
    }

    @Override
    public Optional<Movie> update(Movie entity) throws ValidatorException {
        Optional.ofNullable(entity).orElseThrow(() -> new IllegalArgumentException("id must not be null and must exist"));
        validator.validate(entity);
        String sqlString = "update movie set title=?, genre=?, year=? where id=?;";
        try {
            PreparedStatement preparedStatement = this.connection.prepareStatement(sqlString);
            preparedStatement.setString(1, entity.getTitle());
            preparedStatement.setString(2, entity.getGenre());
            preparedStatement.setInt(3, entity.getYear());
            preparedStatement.setInt(4, entity.getId());
            int result = preparedStatement.executeUpdate();
            if (result == 0)
                return Optional.of(entity);
            else return Optional.empty();
        } catch (SQLException e) {
            throw new SQLRepoException(e.getMessage(), e);
        }
    }
}


 */