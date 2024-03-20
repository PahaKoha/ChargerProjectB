package com.backend.PowerUp.services;

import com.backend.PowerUp.dto.MainUserData;
import com.backend.PowerUp.entities.Role;
import com.backend.PowerUp.entities.User;
import com.backend.PowerUp.entities.UserImage;
import com.backend.PowerUp.exceptions.UserAlreadyExistException;
import com.backend.PowerUp.repositories.RoleRepository;
import com.backend.PowerUp.repositories.UserImageRepository;
import com.backend.PowerUp.repositories.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.hibernate.Session;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final UserImageRepository userImageRepository;

    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = findByUsername(username).orElseThrow(() -> new UsernameNotFoundException(
                String.format("Пользователь '%s' не найден", username)
        ));
        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),
                user.getRoles().stream()
                        .map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList())
        );
    }

    public void createNewUser(User user) throws UserAlreadyExistException {
        if (findByUsername(user.getUsername()).isPresent()) {
            throw new UserAlreadyExistException(user.getUsername());
        }
        Role userRole = roleRepository.findByName("ROLE_USER")
                .orElseThrow(() -> new RuntimeException("Роль не найдена"));
        user.setRoles(List.of(userRole));
        userRepository.save(user);
    }

    @Transactional
    public MainUserData getMainUserData(String username) {
        User user = findByUsername(username).orElseThrow(() -> new UsernameNotFoundException(
                String.format("Пользователь '%s' не найден", username)
        ));
        if (user.getUserImage() != null) {
            UserImage userImage = userImageRepository.findById(user.getUserImage().getId()).orElseThrow(() -> new  RuntimeException("Не удалось найти изображение"));
            return new MainUserData(user.getUsername(), user.getEmail(), user.getFirstname(), user.getLastname(), userImage.getImageData());
        }
        return new MainUserData(user.getUsername(), user.getEmail(), user.getFirstname(), user.getLastname());
    }

    @Transactional
    public void updateUserData(MainUserData userData, String currentUsername, UserImage image) {
        Optional<User> existingUser = userRepository.findByUsername(currentUsername);
        if (existingUser.isPresent()) {
            User user = existingUser.get();

            user.setUsername(userData.getUsername());
            user.setEmail(userData.getEmail());
            user.setFirstname(userData.getFirstname());
            user.setLastname(userData.getLastname());
            user.setUserImage(image);

            userRepository.save(user);
        } else {
            throw new RuntimeException("Пользователя с таким именем не существует.");
        }
    }

}
