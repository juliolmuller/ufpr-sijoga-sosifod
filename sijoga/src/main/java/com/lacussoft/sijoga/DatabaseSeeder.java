 package com.lacussoft.sijoga;

import com.lacussoft.sijoga.model.Address;
import com.lacussoft.sijoga.model.Advogado;
import com.lacussoft.sijoga.model.Juiz;
import com.lacussoft.sijoga.model.Parte;
import com.lacussoft.sijoga.model.Process;
import com.lacussoft.sijoga.services.Dao;
import com.thedeanda.lorem.Lorem;
import com.thedeanda.lorem.LoremIpsum;
import java.util.Date;
import java.util.stream.Stream;
import javax.ejb.EJB;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

public class DatabaseSeeder extends HttpServlet {

    private Juiz[] judges;
    private Advogado[] lawyers;
    private Parte[] parties;
    private Process[] processes;

    private Lorem lorem = LoremIpsum.getInstance();

    @EJB
    private Dao dao;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);

        System.out.println("SIJOGA: seeding database...");

        seedUsers();
        seedProcesses();

        System.out.println("SIJOGA: seeding complete.");
    }

    private <T> T random(T[] array) {
        int len = array.length;
        int index = (int) Math.round(Math.random() * len) % len;
        return array[index];
    }

    private void seedUsers() {
        System.out.println("SIJOGA:     Seeding users...");

        Stream.of(judges = new Juiz[] {
            new Juiz("1", "1", "Juiz da Silva", new Date(), "juiz@email.com", new Address("80610150", "Rua Morretes", 753, null, "Curitiba",  "PR")),
            new Juiz("09434959208", "09434959208", "Júlio Müller",   new Date(), "julio@email.com",  new Address("69029285", "Rua do Lago Nessy",     400, "Apt 34-B", "Curitiba",  "PR")),
            new Juiz("63837430073", "63837430073", "Marcos Nespolo", new Date(), "marcos@email.com", new Address("69029285", "Rua Matsunaga",         200, null,       "Curitiba",  "PR")),
            new Juiz("22649526798", "22649526798", "André Shizuo",   new Date(), "andre@email.com",  new Address("13015180", "Praça Anita Garibaldi", 486, null,       "São Paulo", "SP"))
        }).forEach(dao::create);

        Stream.of(lawyers = new Advogado[] {
            new Advogado("2", "2", "Advogado da Silva", new Date(), "advogado@email.com", new Address("80610150", "Rua Morretes", 753, null, "Curitiba",  "PR"), "12345"),
            new Advogado("59851539594", "59851539594", "Wesley Caetano", new Date(), "vvesley@email.com", new Address("69029285", "Rua Le Parkour",        500, "Bloco A",  "Curitiba",  "PR"), "1234"),
            new Advogado("46513906091", "46513906091", "Ana Nicole",     new Date(), "ana@email.com",     new Address("69029285", "Rua Gustavo Oliveira",  100, null,       "Curitiba",  "PR"), "1001"),
            new Advogado("90498121209", "90498121209", "Aurélio Vidal",  new Date(), "aurelio@email.com", new Address("13015180", "Praça Anita Garibaldi", 288, "apto 109", "São Paulo", "SP"), "4321"),
            new Advogado("20165379812", "20165379812", "David Machado",  new Date(), "david@email.com",   new Address("13015180", "Praça Anita Garibaldi", 992, "Apt 86C",  "Curitiba",  "PR"), "7053"),
            new Advogado("81160961506", "81160961506", "Lucas Moreira",  new Date(), "lucas@email.com",   new Address("57055265", "Rua Anita Cajado",      715, "Casa 3",   "Curitiba",  "PR"), "6050")
        }).forEach(dao::create);

        Stream.of(parties = new Parte[] {
            new Parte("3", "3", "Parte da Silva", new Date(), "parte@email.com", new Address("80610150", "Rua Morretes", 753, null, "Curitiba",  "PR"), random(lawyers)),
            new Parte("22649526793", "22649526793", "Lucas Henrique",   new Date(), "lucas@email.com",    new Address("69029285", "Rua da Banda",            300, "Apt 65",    "Curitiba",       "PR"), random(lawyers)),
            new Parte("83128933006", "83128933006", "Matheus Teixeira", new Date(), "matheus@email.com",  new Address("69029285", "Rua Anita Garibald",      722, null,        "Rio de Janeiro", "RJ"), random(lawyers)),
            new Parte("90534089241", "90534089241", "Cassiano Antunes", new Date(), "cassiano@email.com", new Address("40725074", "Rua Anita Cajado",        898, null,        "Curitiba",       "PR"), random(lawyers)),
            new Parte("08054962622", "08054962622", "Mariana Oliveira", new Date(), "mariana@email.com",  new Address("31210440", "Rua Anfibólios",          399, "apt. 88",   "São Paulo",      "SP"), random(lawyers)),
            new Parte("73812059479", "73812059479", "Gabriela Amaral",  new Date(), "gabriela@email.com", new Address("13405247", "Travessa Ângelo Valler",  670, null,        "Rio de Janeiro", "RJ"), random(lawyers)),
            new Parte("75932242108", "75932242108", "Marina Marins",    new Date(), "marina@email.com",   new Address("13405247", "Travessa Ângelo Valler",  298, "bloco 3",   "Porto Alegre",   "RS"), random(lawyers)),
            new Parte("90534089240", "90534089240", "Alice Muxfeldt",   new Date(), "alice@email.com",    new Address("70342500", "Quadra CLS 103",          650, "apt 71",    "São Paulo",      "SP"), random(lawyers)),
            new Parte("54466773076", "54466773076", "Gabriel Borgo",    new Date(), "gabriel@email.com",  new Address("57055265", "Rua Antenor de Oliveira", 239, null,        "São Paulo",      "SP"), random(lawyers)),
            new Parte("90498121208", "90498121208", "Giovana Cardoso",  new Date(), "giovana@email.com",  new Address("13015180", "Praça Anita Garibaldi",   997, "casa 6",    "Rio de Janeiro", "RJ"), random(lawyers)),
            new Parte("09434959209", "09434959209", "Pedro Krepsky",    new Date(), "pedro@email.com",    new Address("80610150", "Rua Morretes",            753, null,        "Porto Alegre",   "RS"), random(lawyers)),
            new Parte("88931040083", "88931040083", "Mariano Costa",    new Date(), "mariano@email.com",  new Address("13015180", "Praça Anita Garibaldi",   611, "Apto 80",   "Rio de Janeiro", "RJ"), random(lawyers))
        }).forEach(dao::create);
    }

    private void seedProcesses() {
        System.out.println("SIJOGA:     Seeding processes...");

        Stream.of(processes = new Process[] {
            factory(), factory(), factory(), factory(), factory(), factory(), factory(), factory(),
            factory(), factory(), factory(), factory(), factory(), factory(), factory(), factory(),
            factory(), factory(), factory(), factory(), factory(), factory(), factory(), factory(),
            factory(), factory(), factory(), factory(), factory(), factory(), factory(), factory(),
            factory(), factory(), factory(), factory(), factory(), factory(), factory(), factory(),
            factory(), factory(), factory(), factory(), factory(), factory(), factory(), factory(),
            factory(), factory(), factory(), factory(), factory(), factory(), factory(), factory(),
            factory(), factory(), factory(), factory(), factory(), factory(), factory(), factory()
        }).forEach(dao::create);
    }

    private Process factory() {
        Parte promoter, promoted;
        do {
            promoter = random(parties);
            promoted = random(parties);
        } while (promoter.getId().equals(promoted.getId()));
        return new Process(
            lorem.getWords(5, 30),
            random(judges),
            promoter,
            promoted,
            promoter.getLawyer(),
            promoted.getLawyer()
        );
    }
}
