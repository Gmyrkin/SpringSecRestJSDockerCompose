package ru.spb.db.rest.spring.exmp315.SpringRestAPI.service;


import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.spb.db.rest.spring.exmp315.SpringRestAPI.models.Role;
import ru.spb.db.rest.spring.exmp315.SpringRestAPI.models.User;
import ru.spb.db.rest.spring.exmp315.SpringRestAPI.repository.RoleRepository;
import ru.spb.db.rest.spring.exmp315.SpringRestAPI.repository.UserRepository;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Transactional
@Service
public class UserService implements UserServiceImpl {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;


    public UserService(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    @Override
    public User saveUser(User user){
        Set<Role> roles = new HashSet<>(roleRepository.findByName(user.getRole()));
        user.setRoles(roles);

        return userRepository.save(user);
    }

    @Override
    public User findUserById(Long id){
        Optional<User> userOptional = userRepository.findById(id);
        if (userOptional.isPresent()) {
            return userOptional.get();
        }
        return null;
    }

    @Override
    public List<User> findAllUsers(){
        return userRepository.findAll();
    }

    @Override
    public User updateUser(User user){
        Set<Role> roles = new HashSet<>(roleRepository.findByName(user.getRole()));
        user.setRoles(roles);

        return userRepository.save(user);
    }

    @Override
    public void deleteUser(Long id){
        userRepository.deleteById(id);
    }


    @Override
    public User findByUsername(String username) {
        return userRepository.findByFirstName(username);
    }


}
