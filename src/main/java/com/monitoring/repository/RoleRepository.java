package com.monitoring.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.monitoring.dto.Role;

public interface RoleRepository extends MongoRepository<Role, String> {

	Role findByName(String name);

}
