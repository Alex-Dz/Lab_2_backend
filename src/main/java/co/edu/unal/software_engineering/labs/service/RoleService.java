package co.edu.unal.software_engineering.labs.service;

import co.edu.unal.software_engineering.labs.model.Role;
import co.edu.unal.software_engineering.labs.model.User;
import co.edu.unal.software_engineering.labs.pojo.LoginUserPOJO;
import co.edu.unal.software_engineering.labs.pojo.RolePOJO;
import co.edu.unal.software_engineering.labs.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class RoleService {

    @Autowired
    UserService userService;

    @Autowired
    PasswordEncoder passwordEncoder;

    private final RoleRepository roleRepository;

    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }


    public Role findById(Integer id) {
        return roleRepository.findById(id).orElse(null);
    }

    public List<RolePOJO> getAll() {
        List<Role> dbRoles = roleRepository.findAll();
        List<RolePOJO> roles = new ArrayList<RolePOJO>();
        for (Role role : dbRoles) {
            roles.add(new RolePOJO(role));
        }
        return roles;
    }

    public String addRole(Integer idRole, String username, LoginUserPOJO userPOJO) {
        Role role = findById(idRole);
        User existingUser = userService.findByUsername(username);
        if (role == null || existingUser.hasRole(role)) {
            return "BAD_REQUEST";
        } else if (!passwordEncoder.matches(userPOJO.getPassword(), existingUser.getPassword())) {
            return "UNAUTHORIZED";
        }
        existingUser.addRole(role);
        userService.save(existingUser);
        return "CREATED";
    }

}
