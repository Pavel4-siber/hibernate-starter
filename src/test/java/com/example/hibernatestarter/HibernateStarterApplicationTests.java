package com.example.hibernatestarter;

import com.example.hibernatestarter.model.*;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import lombok.Cleanup;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.time.LocalDate;
import java.util.List;

@SpringBootTest
class HibernateStarterApplicationTests {

    @Autowired
    private SessionFactory sessionFactory;

    @Test
    void check_HQL(){
        @Cleanup Session session = sessionFactory.openSession();
        session.beginTransaction();
        String name = "Pavel";
        String company = "Google";

        List<User> list = session.createNamedQuery("findUserByNameAndCompany")
                .setParameter("firstname", name)
                .setParameter("company", company)
                .list();

        System.out.println(list);

        session.getTransaction().commit();
    }

    @Test
    void check_profile(){
        @Cleanup Session session = sessionFactory.openSession();
        session.beginTransaction();

        User user = User.builder().username("Ivan2").build();
        Profile profile = Profile.builder().language("RU").street("Volkova 5").build();

        session.save(user);
        profile.setUser(user);
        session.save(profile);

        session.getTransaction().commit();
    }
    @Test
    void check_Orphal_Removal(){
        @Cleanup Session session = sessionFactory.openSession();
        session.beginTransaction();

        session.enableFetchProfile("withCompany");

//        var company = session.get(Company.class, 7L);
//        company.getUsers().removeIf(user -> user.getId().equals(4L));

        User user = session.get(User.class, 1L);
        System.out.println(user);

        session.getTransaction().commit();
    }

    @Test
    void add_new_company_and_user(){
//        Company company = Company.builder()
//                .name("Yandex")
//                .build();
        User user = User.builder()
                .username("Jjcash")
                .firstname("Olaf")
                .lastname("Peskov")
                .birthDate(new Birthday(LocalDate.now().minusYears(30)))
                .role(Role.USER)
                .build();
        //company.addUser(user);

        @Cleanup Session session = sessionFactory.openSession();
        session.beginTransaction();

        session.save(user);

        session.getTransaction().commit();
    }
}
