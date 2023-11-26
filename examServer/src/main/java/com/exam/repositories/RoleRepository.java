package com.exam.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.exam.models.Roles;

@Repository
public interface RoleRepository extends JpaRepository<Roles, Long>{

}
