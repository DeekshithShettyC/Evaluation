package com.robosoft.PhotosAndVideosApp.repository;


import com.robosoft.PhotosAndVideosApp.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository("roleRepository")
public interface RoleRepository extends JpaRepository<Role, Integer> {

    Optional<Role> findByRole(final String role);

    @Query("FROM Role r WHERE r.role IN (:roles)")
    List<Role> getAllRoles(@Param("roles") final List<String> roles);
}