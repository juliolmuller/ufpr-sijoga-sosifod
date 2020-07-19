 package com.lacussoft.sosifod;

import com.lacussoft.sosifod.model.User;
import com.lacussoft.sosifod.model.Intimacao;
import com.lacussoft.sosifod.services.DaoFacade;
import com.lacussoft.utils.IO;
import com.thedeanda.lorem.Lorem;
import com.thedeanda.lorem.LoremIpsum;
import java.io.IOException;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Stream;
import javax.ejb.EJB;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

public class DatabaseSeeder extends HttpServlet {

    private User[] users;
    private Intimacao[] intimacoes;

    private final Lorem lorem = LoremIpsum.getInstance();

    @EJB
    private DaoFacade dao;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);

        System.out.println("SOSIFOD: seeding database...");

        seedUsers();
        seedIntimacoes();
        
        System.out.println("SOSIFOD: seeding completed.");
    }

    private <T> T random(T[] array) {
        int len = array.length;
        int index = (int) Math.round(Math.random() * len) % len;

        return array[index];
    }

    private void seedUsers() {
        System.out.println("SOSIFOD:     Seeding users...");

        Stream.of(users = new User[] {
            new User("1", "Ana", "ana@email.com", "1", true),
            new User("2", "Julio", "julio@email.com", "2", false),
            new User("3", "Marcos", "marcos@email.com", "3", false),
            new User("4", "VVesley", "vvesley@email.com", "4", false)
        }).forEach(dao::save);
    }


    private void seedIntimacoes() {
        System.out.println("SOSIFOD:     Seeding intimacoes...");

        Stream.of(intimacoes = new Intimacao[] {
            new Intimacao(new Date(), lorem.getZipCode(), "Rua das laranjeiras", null, "não executada", random(users), "12414"),
            new Intimacao(new Date(), lorem.getZipCode(), "Rua do macaco", new Date(), "Executada", random(users), "3123"),
            new Intimacao(new Date(), lorem.getZipCode(), "Rua das lagartas", null, "não executada", random(users), "1231312")
        }).forEach(dao::save);
    }
}
