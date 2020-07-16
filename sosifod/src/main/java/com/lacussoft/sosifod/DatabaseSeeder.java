package com.lacussoft.sosifod;

import com.lacussoft.sosifod.model.User;
import com.lacussoft.sosifod.services.DaoFacade;
import javax.ejb.EJB;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

public class DatabaseSeeder extends HttpServlet {

    @EJB
    private DaoFacade dao;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);

        dao.save(new User("1", "Admin da Silva", "admin@email.com", "1", true));
        dao.save(new User("2", "Oficial da Silva", "oficial@email.com", "2", false));
    }
}
