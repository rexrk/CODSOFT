package com.raman.dao;

import com.raman.entity.Students;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("stDao")
public class StudentManagementDao {
    @Autowired(required = false)
    private JdbcTemplate template;
    private RowMapper<Students> srm = (rs, rowMapper) -> {
      Students st = new Students();
      st.setStudentId(rs.getInt("student_id"));
      st.setFirstName(rs.getString("first_name"));
      st.setLastName(rs.getString("last_name"));
      st.setDateOfBirth(rs.getDate("date_of_Birth").toLocalDate());
      st.setContact(rs.getString("contact"));
      st.setAddress(rs.getString("address"));
      st.setCourse(rs.getString("course"));
      st.setOverallGrades(rs.getDouble("overall_grades"));
      return st;
    };

    public void insertStudent(Students s) throws DaoException {
        String sql = "insert into students values (?,?,?,?,?,?,?,?)";
        template.update(sql,s.getStudentId(), s.getFirstName(),s.getLastName(),s.getDateOfBirth(),
                s.getContact(),s.getAddress(), s.getCourse(), s.getOverallGrades());
    }

    public void updateStudent(Students s) throws DaoException {
        String sql = "update students " +
                "set first_name=?, last_name=?, date_of_Birth=?, contact=?, address=?," +
                " course=?, overall_grades=? where student_id=?";
        template.update(sql, s.getFirstName(),s.getLastName(),s.getDateOfBirth(),
                s.getContact(),s.getAddress(), s.getCourse(), s.getOverallGrades(), s.getStudentId());
    }

    public void deleteStudent(Integer studentId) throws DaoException{
        String sql = "delete from students where student_id=?";
        template.update(sql, studentId);
    }

    public Students getStudent(Integer studentId) throws DaoException {
        String sql = "select * from students where student_id=?";
        return template.queryForObject(sql, srm, studentId);
    }

    public long countStudents() throws DaoException {
        String sql = "select count(*) from students";
        return template.queryForObject(sql, Long.class);
    }
    public List<Students> getAllStudents() throws DaoException {
        String sql = "select * from students";
        return template.query(sql, srm);
    }
}
