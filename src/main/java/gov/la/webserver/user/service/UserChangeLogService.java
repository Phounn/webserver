package gov.la.webserver.user.service;

import gov.la.webserver.user.dto.UserChangeLogDTO;
import gov.la.webserver.user.dto.UserDTO;
import gov.la.webserver.user.entity.UserChangeLog;

import java.util.List;

public interface UserChangeLogService {
    List <UserChangeLog> findAllUserLog();
    Boolean createLogUserNickName(UserDTO userDTO, String nickName);
    Boolean createLogUserName(UserDTO userDTO, String name);
    Boolean createLogUserAge(UserDTO userDTO, Integer age);


    List<UserChangeLogDTO> getAllUserChangelog();
    Boolean clearAllUserLogs();
}
