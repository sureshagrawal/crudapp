package com.nsgacademy.crudapp.dao;

import com.nsgacademy.crudapp.exception.DAOException;
import com.nsgacademy.crudapp.model.Student;
import com.nsgacademy.crudapp.utils.JDBCUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StudentDAOImpl implements StudentDAO{
    private static final String INSERT_SQL = "INSERT INTO student(name,email,mobile) VALUES(?,?,?)";
    private static final String DELETE_SQL = "DELETE FROM student WHERE id=?";
    private static final String UPDATE_SQL = "UPDATE student SET name=?,email=?,mobile=? WHERE id=?";
    private static final String SELECT_BY_ID_SQL = "SELECT * FROM student WHERE id=?";
    private static final String SELECT_ALL_SQL = "SELECT * FROM student ORDER BY id";


    @Override
    public void insert(Student student) {
        try(Connection con = JDBCUtils.fetchConnection();
            PreparedStatement stmt = con.prepareStatement(INSERT_SQL)){

            stmt.setString(1,student.getName());
            stmt.setString(2,student.getEmail());
            stmt.setString(3,student.getMobile());

            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new DAOException("Unable to insert Student",e);
        }
    }

    @Override
    public void delete(int id) {
        try(Connection con = JDBCUtils.fetchConnection();
            PreparedStatement stmt = con.prepareStatement(DELETE_SQL)){

            stmt.setInt(1,id);
            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new DAOException("Unable to delete Student",e);
        }
    }

    @Override
    public Student getStudentById(int id) {
        Student student = null;
        try(Connection con = JDBCUtils.fetchConnection();
            PreparedStatement stmt = con.prepareStatement(SELECT_BY_ID_SQL)){

            stmt.setInt(1,id);

            try(ResultSet rs =  stmt.executeQuery()){
                if(rs.next()){
                    student = new Student();
                    student.setId(rs.getInt("id"));
                    student.setName(rs.getString("name"));
                    student.setEmail(rs.getString("email"));
                    student.setMobile(rs.getString("mobile"));
                }
            }
        } catch (SQLException e) {
            throw new DAOException("Unable to fetch Student",e);
        }
        return student;
    }

    @Override
    public void update(Student student) {
        try(Connection con = JDBCUtils.fetchConnection();
            PreparedStatement stmt = con.prepareStatement(UPDATE_SQL)){

            stmt.setString(1,student.getName());
            stmt.setString(2,student.getEmail());
            stmt.setString(3,student.getMobile());

            stmt.setInt(4,student.getId());

            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new DAOException("Unable to update Student",e);
        }
    }

    @Override
    public List<Student> getAllStudents() {
        List<Student> studentList = new ArrayList<>();
        try(Connection con = JDBCUtils.fetchConnection();
            PreparedStatement stmt = con.prepareStatement(SELECT_ALL_SQL);
            ResultSet rs = stmt.executeQuery()){

            while(rs.next()){
                Student student = new Student();
                student.setId(rs.getInt("id"));
                student.setName(rs.getString("name"));
                student.setEmail(rs.getString("email"));
                student.setMobile(rs.getString("mobile"));

                studentList.add(student);
            }
        } catch (SQLException e) {
            throw new DAOException("Unable to fetch Student",e);
        }

        return studentList;
    }
}
