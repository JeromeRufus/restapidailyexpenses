package com.dailyexpenses.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dailyexpenses.model.Users;

@Repository
public interface UserRepository extends JpaRepository<Users, String>{

}
