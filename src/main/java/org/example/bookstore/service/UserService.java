package org.example.bookstore.service;

import org.example.bookstore.dto.request.CreateUserRequest;
import org.example.bookstore.dto.response.UserResponse;
import org.example.bookstore.entity.User;
import org.example.bookstore.exception.InvalidUsernameException;
import org.example.bookstore.exception.UserNotFoundException;
import org.example.bookstore.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final SepetService sepetService;
    private final BCryptPasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, SepetService sepetService, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.sepetService = sepetService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<User> user = userRepository.findByUsername(username);
        return user.orElseThrow(()->new InvalidUsernameException("Kullanici adi veya sifre yanlis"));
    }


    public UserResponse createUser(CreateUserRequest request) {
        User newUser = User.builder()
                .name(request.getName())
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .authorities(request.getAuthorities())
                .accountNonExpired(true)
                .credentialsNonExpired(true)
                .isEnabled(true)
                .accountNonLocked(true)
                .build();

        User user=userRepository.save(newUser);
        sepetService.createSepetByUserId(user);

        return mapToUserResponse(user);

    }

    public User getUserById(Long userId){
        return userRepository.findById(userId).orElseThrow(()->new UserNotFoundException("user not found this id:"+userId));
    }


    public UserResponse mapToUserResponse(User user) {
        UserResponse response = new UserResponse();
        response.setId(user.getId());
        response.setName(user.getName());
        response.setUserName(user.getUsername());

        return response;
    }
}
