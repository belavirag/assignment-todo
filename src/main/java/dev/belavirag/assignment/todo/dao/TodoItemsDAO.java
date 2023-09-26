package dev.belavirag.assignment.todo.dao;

import dev.belavirag.assignment.todo.Person;
import dev.belavirag.assignment.todo.SQLConnectionManager;
import dev.belavirag.assignment.todo.Todo;
import dev.belavirag.assignment.todo.Utils;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class TodoItemsDAO implements TodoItems {
    @Override
    public Todo create(Todo todo) {
        try(Connection conn = SQLConnectionManager.getConnection();
            PreparedStatement stmt = conn.prepareStatement("INSERT INTO todo_item(title, description, deadline, done, assignee_id) VALUES(?, ?, ?, ?, ?)", PreparedStatement.RETURN_GENERATED_KEYS)
        ) {
            stmt.setString(1, todo.getTitle());
            stmt.setString(2, todo.getDescription());
            stmt.setDate(3, Utils.toSQLDate(todo.getDeadLine()));
            stmt.setBoolean(4, todo.isDone());

            if (todo.getAssignee() != null) {
                stmt.setInt(5, todo.getAssignee().getId());
            } else {
                stmt.setNull(5, Types.NULL);
            }

            int rowsInserted = stmt.executeUpdate();
            if (rowsInserted == 0) {
                throw new RuntimeException("Could not insert new todo into database!");
            }

            try (ResultSet result = stmt.getGeneratedKeys()) {
                if (result.next()) {
                    todo.setId(result.getInt(1));
                } else {
                    throw new RuntimeException("No generated keys returned from database");
                }
                return todo;
            }
        } catch (SQLException e) {
            System.err.println("Failed to insert todo into database");
            throw new RuntimeException(e);
        }
    }

    @Override
    public Collection<Todo> findAll() {
        List<Todo> todos = new ArrayList<>();

        try(
                Connection conn = SQLConnectionManager.getConnection();
                PreparedStatement stmt = conn.prepareStatement("SELECT * FROM todo_item t LEFT OUTER JOIN person p ON t.assignee_id = p.person_id")
        ) {
            try(ResultSet result = stmt.executeQuery()) {
                while (result.next()) {
                    Person p = Person.fromResultSet(result);
                    todos.add(Todo.fromResultSet(result, p));
                }
            }
        } catch (SQLException e) {
            System.out.println("Failed to fetch todos from database");
            throw new RuntimeException(e);
        }

        return todos;
    }

    @Override
    public Todo findById(int id) {
        try(
                Connection conn = SQLConnectionManager.getConnection();
                PreparedStatement stmt = conn.prepareStatement("SELECT * FROM todo_item t LEFT OUTER JOIN person p ON t.assignee_id = p.person_id WHERE t.todo_id = ?")
        ) {
            stmt.setInt(1, id);

            try(ResultSet result = stmt.executeQuery()) {
                if (result.next()) {
                    Person p = Person.fromResultSet(result);
                    return Todo.fromResultSet(result, p);
                }
            }
        } catch (SQLException e) {
            System.out.println("Failed to fetch todos from database");
            throw new RuntimeException(e);
        }

        return null;
    }

    @Override
    public Collection<Todo> findByAssignee(int id) {
        List<Todo> todos = new ArrayList<>();

        try(
                Connection conn = SQLConnectionManager.getConnection();
                PreparedStatement stmt = conn.prepareStatement("SELECT * FROM todo_item t LEFT OUTER JOIN person p ON t.assignee_id = p.person_id WHERE t.assignee_id = ?")
        ) {
            stmt.setInt(1, id);

            try(ResultSet result = stmt.executeQuery()) {
                while (result.next()) {
                    Person p = Person.fromResultSet(result);
                    todos.add(Todo.fromResultSet(result, p));
                }
            }
        } catch (SQLException e) {
            System.out.println("Failed to fetch todos from database");
            throw new RuntimeException(e);
        }

        return todos;
    }

    @Override
    public Collection<Todo> findByAssignee(Person person) {
        return findByAssignee(person.getId());
    }

    @Override
    public Collection<Todo> findByUnassignedTodoItems() {
        List<Todo> todos = new ArrayList<>();

        try(
                Connection conn = SQLConnectionManager.getConnection();
                PreparedStatement stmt = conn.prepareStatement("SELECT * FROM todo_item t WHERE t.assignee_id IS NULL")
        ) {
            try(ResultSet result = stmt.executeQuery()) {
                while (result.next()) {
                    todos.add(Todo.fromResultSet(result, null));
                }
            }
        } catch (SQLException e) {
            System.out.println("Failed to fetch todos from database");
            throw new RuntimeException(e);
        }

        return todos;
    }

    @Override
    public Todo update(Todo todo) {
        try(
                Connection conn = SQLConnectionManager.getConnection();
                PreparedStatement stmt = conn.prepareStatement("UPDATE todo_item SET title = ?, description = ?, deadline = ?, done = ?, assignee_id = ? WHERE todo_id = ?")
        ) {
            stmt.setString(1, todo.getTitle());
            stmt.setString(2, todo.getDescription());
            stmt.setDate(3, Utils.toSQLDate(todo.getDeadLine()));
            stmt.setBoolean(4, todo.isDone());
            if (todo.getAssignee() != null) {
                stmt.setInt(5, todo.getAssignee().getId());
            } else {
                stmt.setNull(5, Types.NULL);
            }
            stmt.setInt(6, todo.getId());

            int rowsChanged = stmt.executeUpdate();
            if (rowsChanged != 1) {
                throw new RuntimeException("Could not update todo with the id " + todo.getId());
            }

            return todo;
        } catch (SQLException e) {
            System.err.println("Failed to update person");
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean deleteById(int id) {
        try (Connection conn = SQLConnectionManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement("DELETE FROM todo_item WHERE todo_id = ?")
        ) {
            stmt.setInt(1, id);

            int rowsChanged = stmt.executeUpdate();
            return rowsChanged == 1;
        } catch (SQLException e) {
            System.err.println("Failed to delete by id");
        }

        return false;
    }
}
