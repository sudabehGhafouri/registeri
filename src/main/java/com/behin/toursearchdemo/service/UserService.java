package com.behin.toursearchdemo.service;

import Model.UserSignin;
import org.springframework.data.domain.Page;

import java.awt.print.Pageable;
import java.util.Optional;

public interface UserService {
    UserSignin save(UserSignin article);

    Optional<UserSignin> findOne(String id);

    Iterable<UserSignin> findAll();

    Page<UserSignin> findByAuthorName(String name, Pageable pageable);
}
