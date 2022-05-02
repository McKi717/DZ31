package learnUp.dz19.service.user;

import learnUp.dz19.entity.Role;
import learnUp.dz19.entity.User;
import learnUp.dz19.repository.RoleRepository;
import learnUp.dz19.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityExistsException;
import javax.persistence.RollbackException;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, RoleRepository roleRepository, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if(user == null){
            throw new UsernameNotFoundException("Пользователь не найден");
        }

        return user;
    }

//    public void saveUser(User user){
//        User userFromDB = userRepository.findByUsername(user.getUsername());
//
//        if(userFromDB!=null){
//            throw new EntityExistsException("Данный логин " + user.getUsername() +" уже существует");
//
//        }
//        Set<String> roles = user.getRoles().stream()
//                .map(Role::getName)
//                .collect(Collectors.toSet());
//
//        Set<Role> existRoles = roleRepository.findRoleByNameIn(roles);
//        user.setRoles(existRoles);
//        existRoles.forEach(role -> role.setUsers(Set.of(user)));
//        user.setPassword(passwordEncoder.encode(user.getPassword()));
//
//        userRepository.save(user);
//
//    }

    public boolean saveUser(User user) {
        User userFromDB = userRepository.findByUsername(user.getUsername());

        if (userFromDB != null) {
            return false;
        }
        user.setRoles(user.getRoles());

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        return true;
    }

}
