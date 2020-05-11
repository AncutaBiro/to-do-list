package org.fasttrackit.persistence;

import org.fasttrackit.transfer.CreateTaskRequest;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class TaskRepository {

    public void createTask(CreateTaskRequest request) throws IOException, SQLException, ClassNotFoundException {
// preventing SQL injection by using PreparedStatement
        String sql = "INSERT INTO task (description, deadline) VALUES (?,?)";

        // try-with-resources
        try (Connection connection =  DataBaseConfiguration.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

        preparedStatement.setString(1, request.getDescription());
        preparedStatement.setDate(2, Date.valueOf(request.getDeadline()));

        preparedStatement.executeUpdate();
        }

    }

}
