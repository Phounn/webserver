package gov.la.webserver.user.service.impl;

import gov.la.webserver.user.dto.UserDTO;
import gov.la.webserver.user.dto.UserRegisterDTO;
import gov.la.webserver.user.entity.User;
import gov.la.webserver.user.event.UserChangeLogEvent;
import gov.la.webserver.user.repository.UserChangeLogRepository;
import gov.la.webserver.user.repository.UserRepository;
import gov.la.webserver.user.service.UserChangeLogService;
import gov.la.webserver.user.service.UserService;
import gov.la.webserver.user.service.exception.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private  final UserRepository userRepository;
    private final UserChangeLogRepository userChangeLogRepository;
    private final UserChangeLogService userChangeLogService;
    private final PasswordEncoder passwordEncoder;
    private final ApplicationEventPublisher publisher;

    @Override
    public List<UserDTO> findAllUser() {
        return userRepository.findAll()
                .stream()
                .map(UserDTO::new)
                .collect(Collectors.toList());
    }

    @Override
    public UserDTO getDetail(Long id) {
        return userRepository.findById(id)
                .map(UserDTO::new)
                .orElseThrow(UserNotFoundException::new);
    }


    @Override
    public UserDTO registerUser(UserRegisterDTO registerDTO) {
        User user = User.createUserWithAccount(
                registerDTO.getName(),
                registerDTO.getAge(),
                registerDTO.getNickName(),
                registerDTO.getUsername(),
                passwordEncoder.encode(registerDTO.getPassword())
        );
        User savedUser = userRepository.save(user);
        return new UserDTO(savedUser);
    }

    @Override
    public UserDTO updateUser(Long id, UserDTO userDTO) {

        User savedUser = userRepository.findById(id).orElseThrow(UserNotFoundException::new);


        publisher.publishEvent(new UserChangeLogEvent(userDTO, savedUser));


        savedUser.changeNickName(userDTO.getNickName());
        savedUser.changeName(userDTO.getName());
        savedUser.changeAge(userDTO.getAge());
        User updateUser = userRepository.save(savedUser);
        return new UserDTO(updateUser);

    }
    @Override
    public Boolean deleteUser(Long id) {
        final User user = userRepository.findById(id).orElseThrow(UserNotFoundException::new);
        userRepository.delete(user);
        return Boolean.TRUE;
    }
}
