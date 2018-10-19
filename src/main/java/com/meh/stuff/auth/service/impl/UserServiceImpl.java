package com.meh.stuff.auth.service.impl;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.meh.stuff.auth.db.entity.User;
import com.meh.stuff.auth.db.exception.EntityNotFoundException;
import com.meh.stuff.auth.db.repository.UserRepository;
import com.meh.stuff.auth.service.UserService;

@Service
public class UserServiceImpl implements UserService {

    private EntityManager entityManager;
    private UserRepository userRepository;
    
    private static final String UPDATE_PASSWORD_QUERY = " UPDATE the_user"
            + " SET password = :password, salt = :salt "
            + " WHERE id = :id ";

    @Autowired
    public UserServiceImpl(EntityManager entityManager, UserRepository userRepository) {
        this.entityManager = entityManager;
        this.userRepository = userRepository;
    }

    @Override
    public User findUserByUsername(String username) throws EntityNotFoundException {
        return userRepository
                .findUserByUsername(username)
                .orElseThrow(() -> new EntityNotFoundException("Unable to find username: " + username));
    }

    @Override
    @Transactional
    public User saveUser(User user,
                         String password,
                         String salt) throws EntityNotFoundException {
        User savedUser = userRepository.save(user);
        if (savedUser.getId() == null) {
            throw new EntityNotFoundException("Unable to see id of the record. User was not saved.");
        }
        
        entityManager.joinTransaction();
        
        Query query = entityManager.createNativeQuery(UPDATE_PASSWORD_QUERY);
        query.setParameter("password", password);
        query.setParameter("salt", salt);
        query.setParameter("id", savedUser.getId());
        
        int updateCount = query.executeUpdate();
        assert(updateCount == 1);
        
        entityManager.flush();
        
        return savedUser;
    }

}
