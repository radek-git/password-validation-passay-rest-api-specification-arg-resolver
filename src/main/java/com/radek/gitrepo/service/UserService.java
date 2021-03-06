package com.radek.gitrepo.service;

import com.radek.gitrepo.service.util.FieldValueExists;
import com.radek.gitrepo.entity.User;
import com.radek.gitrepo.repository.UserRepository;
import com.radek.gitrepo.specification.UserByColumnNameAndValueSpecification;
import com.radek.gitrepo.specification.UserSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService implements FieldValueExists {

    private UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Page<User> findAll(UserSpecification userSpecification, Pageable pageable) {
        return userRepository.findAll(userSpecification, pageable);
    }

    public User add(User user) {
        return userRepository.save(user);
    }

    // "111111111" - o, "pesel" - fieldName
    @Override
    public boolean fieldValueExists(Object value, String fieldName) { //nazwa pola które chcemy sprawdzić
        List<String> uniqueFields = List.of("username", "pesel", "email");

        if (value == null) {
            // exception...
        }

        if (fieldName == null) {
            // exception...
        }

        if (uniqueFields.contains(fieldName)) {
            return userRepository.findOne(new UserByColumnNameAndValueSpecification(fieldName, value)).isPresent();
        } else {
            throw new UnsupportedOperationException("Field name not supported");
        }


//        wczesniejsza/gorsza wersja
//
//        if (fieldName.equals("username")) {
//            return userRepository.existsByUsername(value.toString());
//        } else if (fieldName.equals("pesel")) {
//            return userRepository.existsByPesel(value.toString());
//        } else if (fieldName.equals("email")) {
//            return userRepository.existsByEmail(value.toString());
//        } else {
//            throw new UnsupportedOperationException("Field name not supported");
//        }
    }
}
