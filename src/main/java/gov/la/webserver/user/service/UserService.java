package gov.la.webserver.user.service;

import gov.la.webserver.user.dto.UserDTO;
import gov.la.webserver.user.dto.UserRegisterDTO;

import java.util.List;

public interface UserService {
    List<UserDTO> findAllUser();

    UserDTO registerUser(UserRegisterDTO registerDTO);

    UserDTO updateUser(Long id, UserDTO userDTO);

    Boolean deleteUser(Long id);

    UserDTO getDetail(Long id);

}
