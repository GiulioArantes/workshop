package com.giulioarantes.course.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.giulioarantes.course.entities.User;

public interface UserRepository extends JpaRepository<User, Long> {
}
