package com.lacussoft.sijoga;

import com.lacussoft.sijoga.model.Advogado;
import com.lacussoft.sijoga.model.Juiz;
import com.lacussoft.sijoga.model.Parte;
import com.lacussoft.sijoga.services.Dao;
import javax.ejb.EJB;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

public class DatabaseSeeder extends HttpServlet {

    @EJB
    private Dao dao;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);

        System.out.println("Populando banco de dados...");

        seedUsers();

        System.out.println("Populamento de banco de dados completo.");
    }

    private void seedUsers() {
        System.out.println("    Populando usuários...");
        dao.create(new Juiz("1", "1"));
        dao.create(new Advogado("2", "2"));
        dao.create(new Parte("3", "3"));
        System.out.println("    Populamento de usuários completo.");
    }
}
