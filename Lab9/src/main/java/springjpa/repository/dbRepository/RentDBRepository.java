/*package repository.dbRepository;

import domain.Rent;
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
public class RentDBRepository implements SortingRepository<Integer, Rent> {

    @Autowired
    private Validator<Rent> validator;

    private Connection connection;

    public RentDBRepository(Validator<Rent> validator, Connection connection) {
        this.validator = validator;
        this.connection = connection;
    }

    @Override
    public Optional<Rent> findOne(Integer o) {
        String sqlString = "select * from rent where id=" + o.toString();
        Rent rent = null;
        try {
            PreparedStatement preparedStatement = this.connection.prepareStatement(sqlString);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                rent = new Rent(resultSet.getInt("id"), resultSet.getInt("movie_id"), resultSet.getInt("client_id"));
            }
        } catch (SQLException e) {
            throw new SQLRepoException(e.getMessage(), e);
        }
        Optional.ofNullable(rent).orElseThrow(() -> new IllegalArgumentException("id must not be null and must exist"));
        return Optional.of(rent);
    }


    @Override
    public Iterable<Rent> findAll() {
        String sqlString = "select * from rent";
        List<Rent> result = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = this.connection.prepareStatement(sqlString);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Rent rent = new Rent(resultSet.getInt("id"), resultSet.getInt("movie_id"), resultSet.getInt("client_id"));
                result.add(rent);
            }
        } catch (SQLException e) {
            throw new SQLRepoException(e.getMessage(), e);
        }
        return result;
    }

    @Override
    public Optional<Rent> save(Rent entity) throws ValidatorException {
        Optional.ofNullable(entity).orElseThrow(() -> new IllegalArgumentException("Entity must not be null"));
        validator.validate(entity);
        String sqlString = "insert into rent(id, movie_id, client_id) values (?,?,?);";
        try {

            PreparedStatement preparedStatement = this.connection.prepareStatement(sqlString);
            preparedStatement.setInt(1, entity.getId());
            preparedStatement.setInt(2, entity.getMovieId());
            preparedStatement.setInt(3, entity.getClientId());
            preparedStatement.executeUpdate();
            return Optional.empty();
        } catch (SQLException e) {
            throw new SQLRepoException(e.getMessage(), e);
        }
    }

    @Override
    public Optional<Rent> delete(Integer o) {
        String sqlStringUpdate = "delete from rent where id=" + o.toString();
        String sqlStringSelect = "select * from rent where id=" + o.toString();
        Rent rent = null;
        try {
            PreparedStatement selectStatement = this.connection.prepareStatement(sqlStringSelect);
            ResultSet resultSet = selectStatement.executeQuery();
            if (resultSet.next()) {
                rent = new Rent(resultSet.getInt("id"), resultSet.getInt("movie_id"), resultSet.getInt("client_id"));
            }
            PreparedStatement updateStatement = this.connection.prepareStatement(sqlStringUpdate);
            updateStatement.executeUpdate();
            return Optional.ofNullable(rent);
        } catch (SQLException e) {
            throw new SQLRepoException(e.getMessage(), e);
        }
    }

    @Override
    public Optional<Rent> update(Rent entity) throws ValidatorException {
        Optional.ofNullable(entity).orElseThrow(() -> new IllegalArgumentException("id must not be null and must exist"));
        validator.validate(entity);
        String sqlString = "update rent set movie_id=?, client_id=? where id=?;";
        try {
            PreparedStatement preparedStatement = this.connection.prepareStatement(sqlString);
            preparedStatement.setInt(1, entity.getMovieId());
            preparedStatement.setInt(2, entity.getClientId());
            preparedStatement.setInt(3, entity.getId());
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