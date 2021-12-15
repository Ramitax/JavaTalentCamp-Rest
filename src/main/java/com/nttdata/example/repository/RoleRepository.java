package com.nttdata.example.repository;

import java.util.Optional;

import com.nttdata.example.models.ERole;
import com.nttdata.example.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
	Optional<Role> findByName(ERole name);
}
