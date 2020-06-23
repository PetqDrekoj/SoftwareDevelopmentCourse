/*package repository.dbRepository;

import domain.Client;
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
public class ClientDBRepository implements SortingRepository<Integer, Client> {

    @Autowired
    private Validator<Client> validator;
    private Connection connection;

    public ClientDBRepository(Validator<Client> validator, Connection connection) {
        this.validator = validator;
        this.connection = connection;
    }

    @Override
    public Optional<Client> findOne(Integer o) {
        String sqlString = "select * from client where id=?;";
        Client client = null;
        try {
            PreparedStatement preparedStatement = this.connection.prepareStatement(sqlString);
            preparedStatement.setInt(1, o);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                client = new Client();
                client.setId(resultSet.getInt("id"));
                client.setName(resultSet.getString("name"));
                client.setAge(resultSet.getInt("age"));
            }
            Optional.ofNullable(client).orElseThrow(() -> new IllegalArgumentException("id must not be null and must exist"));
            return Optional.of(client);
        } catch (SQLException e) {
            throw new SQLRepoException(e.getMessage(), e);
        }
    }

    @Override
    public Iterable<Client> findAll() {
        String sqlString = "select * from client;";
        List<Client> result = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = this.connection.prepareStatement(sqlString);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Client client = new Client();
                client.setId(resultSet.getInt("id"));
                client.setName(resultSet.getString("name"));
                client.setAge(resultSet.getInt("age"));
                result.add(client);
            }
        } catch (SQLException e) {
            throw new SQLRepoException(e.getMessage(), e);
        }
        return result;
    }

    @Override
    public Optional<Client> save(Client entity) throws ValidatorException {
        Optional.ofNullable(entity).orElseThrow(() -> new IllegalArgumentException("Entity must not be null"));
        validator.validate(entity);
        String sqlString = "insert into client(id, name, age) values (?,?,?);";
        try {
            PreparedStatement preparedStatement = this.connection.prepareStatement(sqlString);
            preparedStatement.setInt(1, entity.getId());
            preparedStatement.setString(2, entity.getName());
            preparedStatement.setInt(3, entity.getAge());
            preparedStatement.executeUpdate();
            return Optional.empty();
        } catch (SQLException e) {
            throw new SQLRepoException(e.getMessage(), e);
        }
    }

    @Override
    public Optional<Client> delete(Integer o) {
        String sqlString = "delete from client where id=?;";
        String selectString = "Select * from client where id=?";
        Client client = null;
        try {
            PreparedStatement selectStatement = this.connection.prepareStatement(selectString);
            selectStatement.setInt(1, o);
            ResultSet resultSet = selectStatement.executeQuery();
            if (resultSet.next()) {
                client = new Client();
                client.setId(resultSet.getInt("id"));
                client.setName(resultSet.getString("name"));
                client.setAge(resultSet.getInt("age"));
            }
            PreparedStatement preparedStatement = this.connection.prepareStatement(sqlString);
            preparedStatement.setInt(1, o);
            preparedStatement.executeUpdate();
            return Optional.ofNullable(client);
        } catch (SQLException e) {
            throw new SQLRepoException(e.getMessage(), e);
        }
    }

    @Override
    public Optional<Client> update(Client entity) throws ValidatorException {
        Optional.ofNullable(entity).orElseThrow(() -> new IllegalArgumentException("id must not be null and must exist"));
        validator.validate(entity);
        String sqlString = "update client set name=?,  age=? where id=?;";
        try {
            PreparedStatement preparedStatement = this.connection.prepareStatement(sqlString);
            preparedStatement.setString(1, entity.getName());
            preparedStatement.setInt(2, entity.getAge());
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