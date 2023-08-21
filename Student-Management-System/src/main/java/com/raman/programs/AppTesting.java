package com.raman.programs;

import com.raman.cfg.AppConfig;
import com.raman.dao.DaoException;
import com.raman.dao.StudentManagementTest;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class AppTesting {
    public static void main(String[] args) throws DaoException {
        AnnotationConfigApplicationContext ctx;
        ctx = new AnnotationConfigApplicationContext(AppConfig.class);

        StudentManagementTest dao = ctx.getBean("stDao", StudentManagementTest.class);
    }
}
