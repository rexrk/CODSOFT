package com.raman.dao;

import com.raman.cfg.AppConfig;
import com.raman.entity.Students;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository("jdDao")
public class StudentManagementTest {
    static JdbcTemplate template;

    public static void main(String[] args) throws DaoException {
        AnnotationConfigApplicationContext ctx;
        ctx = new AnnotationConfigApplicationContext(AppConfig.class);

        template = ctx.getBean(JdbcTemplate.class);
        Students st = new Students();
        st.setFirstName("Raman");
        st.setLastName("Kumar");
        insertStudent(st);

        ctx.close();
    }

    static void insertStudent(Students s) throws DaoException {
        String sql = "insert into students values (?,?,?,?,?,?,?,?)";
        template.update(sql,s.getStudentId(), s.getFirstName(),s.getLastName(),s.getDateOfBirth(),
        s.getContact(),s.getAddress(), s.getCourse(), s.getOverallGrades());
    }
}
