package com.monitoring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.monitoring.dto.Role;
import com.monitoring.repository.RoleRepository;

@Service
public class RolesServiceImpl implements RolesService {

	@Autowired
	RoleRepository roleRepository;

	@Override
	public List<Role> getAllRoles() {
		return roleRepository.findAll();
	}

}
