package com.example.hibernatestarter;

import com.example.hibernatestarter.dto.QPredicate;
import com.example.hibernatestarter.dto.UserFilter;
import com.example.hibernatestarter.model.QCompany;
import com.example.hibernatestarter.model.QProfile;
import com.example.hibernatestarter.model.QUser;
import com.example.hibernatestarter.model.User;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.Predicate;
import com.querydsl.jpa.impl.JPAQuery;
import lombok.Cleanup;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static com.example.hibernatestarter.model.QCompany.company;
import static com.example.hibernatestarter.model.QProfile.profile;
import static com.example.hibernatestarter.model.QUser.user;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author Zhurenkov Pavel 07.10.2023
 */

@SpringBootTest
public class UserDaoTest {
    @Autowired
    private SessionFactory sessionFactory;

    @Test
    void findAll(){
        @Cleanup Session session = sessionFactory.openSession();
        session.beginTransaction();

        List<User> list = new JPAQuery<>(session).select(user).from(user).fetch();

        assertEquals(list.size(), 5);
        session.getTransaction().commit();
    }

    @Test
    void findByFirstname(){
        @Cleanup Session session = sessionFactory.openSession();
        session.beginTransaction();
        String firstname = "Pavel";

        List<User> list = new JPAQuery<>(session).select(user).from(user).where(user.firstname.eq(firstname)).fetch();
        User user = new JPAQuery<>(session).select(QUser.user).from(QUser.user).where(QUser.user.firstname.eq(firstname)).fetchFirst();

        assertAll(()->{
        assertEquals(list.size(), 2);
        assertEquals(user.getFirstname(),  firstname);}
        );
        session.getTransaction().commit();
    }

    @Test
    void findLimitedUsersOrderedByBirthday(){
        @Cleanup Session session = sessionFactory.openSession();
        session.beginTransaction();
        int limit = 3;

        List<User> list = new JPAQuery<>(session).select(user).from(user)
                .orderBy(new OrderSpecifier(Order.DESC, user.birthDate))
                .limit(limit).fetch();

        list.stream().forEach(System.out::println);

        session.getTransaction().commit();
    }

    @Test
    void findAllByCompanyName(){
        @Cleanup Session session = sessionFactory.openSession();
        session.beginTransaction();
        String companyName = "Google";

        List<User> list = new JPAQuery<>(session).select(user).from(user)
                .join(user.company, company)
                .where(company.name.eq(companyName))
                .fetch();

        System.out.println(list.get(0).getCompany().getName());
        assertEquals(list.size(),1);

        session.getTransaction().commit();
    }

    @Test
    void findCompanyNameWithUserByLanguage(){
        @Cleanup Session session = sessionFactory.openSession();
        session.beginTransaction();

        List<Tuple> tuples = new JPAQuery<Tuple>(session).select(company.name, user.firstname, profile.street).from(company)
                .join(company.users, user)
                .join(user.profile, profile)
                .where(profile.street.eq("Slava 5"))
                .orderBy(company.name.asc(), user.firstname.asc())
                .fetch();

        assertThat(tuples.get(0).get(2, String.class)).contains("Slava 5");

        session.getTransaction().commit();
    }

    @Test
    void isItCompany(){
        @Cleanup Session session = sessionFactory.openSession();
        session.beginTransaction();

        List<Tuple> tuples = new JPAQuery<Tuple>(session).select(company.name.as("Company"), user.count()).from(company)
                .rightJoin(company.users, user)
                .groupBy(company.id)
                .fetch();

        tuples.stream().forEach(System.out::println);

        session.getTransaction().commit();
    }

    @Test
    void getAllUserAndCompany(){
        @Cleanup Session session = sessionFactory.openSession();
        session.beginTransaction();

        List<Tuple> list = new JPAQuery<>(session).select(company.name, user.profile.language).from(company)
                .join(company.users, user)
                .join(user.profile, profile)
                .fetch();

        list.stream().forEach(System.out::println);

        session.getTransaction().commit();
    }

    @Test
    void findUserByFirstnameAndLastname(){
        @Cleanup Session session = sessionFactory.openSession();
        session.beginTransaction();

        UserFilter filter = UserFilter.builder().firstname("Pavel").lastname("Frolov").build();

        Predicate predicates = QPredicate.builder()
                .add(filter.getFirstname(), user.firstname::eq)
                .add(filter.getLastname(), user.lastname::eq)
                .buildAnd();
//        List<Predicate> predicates = new ArrayList<>();
//        if (filter.getFirstname() != null){
//            predicates.add(user.firstname.eq(filter.getFirstname()));
//        }
//        if (filter.getLastname() != null){
//            predicates.add(user.lastname.eq(filter.getLastname()));
//        }

        List<User> list = new JPAQuery<>(session).select(user).from(user)
                .where(predicates)
                .fetch();
        list.forEach(System.out::println);

        session.getTransaction().commit();
    }
}
