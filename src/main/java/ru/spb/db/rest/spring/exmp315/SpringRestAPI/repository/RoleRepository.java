package ru.spb.db.rest.spring.exmp315.SpringRestAPI.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.spb.db.rest.spring.exmp315.SpringRestAPI.models.Role;

import java.util.List;

public interface RoleRepository extends JpaRepository<Role, Integer> {
    List<Role> findByName(String name);


}
