package com.hotelreview.user.Repositories;

import com.hotelreview.user.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {
}
