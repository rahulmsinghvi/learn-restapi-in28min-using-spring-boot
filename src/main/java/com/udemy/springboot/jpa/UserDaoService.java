package com.udemy.springboot.jpa;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@Repository
@Transactional
public class UserDaoService {

    @PersistenceContext
    private EntityManager entityManager;

    public long insertUser(User user)
    {
        entityManager.persist(user);
        long id = user.getId();

        return id;
    }
}
