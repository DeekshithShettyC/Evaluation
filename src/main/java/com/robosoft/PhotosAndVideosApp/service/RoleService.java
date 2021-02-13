package com.robosoft.PhotosAndVideosApp.service;


import com.robosoft.PhotosAndVideosApp.entity.Role;
import com.robosoft.PhotosAndVideosApp.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class RoleService {

    @Autowired
    RoleRepository roleRepository;

    public Role getByRole(final String role) {
        return this.getByRole(roleRepository.findByRole(role));
    }

    public List<Role> getAllRoles(final List<String> role) {
        return roleRepository.getAllRoles(role);
    }


    private Role getByRole(Optional<Role> findByRole) {
        if(!findByRole.isPresent()) {
            return null;
        }
        return findByRole.get();
    }


}
