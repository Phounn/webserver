package gov.la.webserver.user.controller;

import com.google.gson.Gson;
import gov.la.webserver.user.dto.UserDTO;
import gov.la.webserver.user.dto.UserRegisterDTO;
import gov.la.webserver.user.service.UserService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import static org.mockito.ArgumentMatchers.eq;

import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(UserController.class)
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @DisplayName("Testing user list lookup")
    @Test
    void getAllUser() throws Exception {

        List<UserDTO> mockUserList = List.of(
                new UserDTO(1L, "Tony Stark", 20, "Iron Man", "user"),
                new UserDTO(2L, "Thor Odinson", 21, "Thor", "user")
        );
        given(userService.findAllUser()).willReturn(mockUserList);

        mockMvc.perform(get("/api/v1/users"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.[0].id").value(1))
                .andExpect(jsonPath("$.[0].nickName").value("Iron Man"))
                .andExpect(jsonPath("$.[1].id").value(2))
                .andExpect(jsonPath("$.[1].nickName").value("Thor"))
        ;

    }

    @Test
    void getUser() throws Exception {
        UserDTO mockUserDTO = new UserDTO(1L, "Tony Stark", 20, "Iron Man", "hi");

        given(userService.getDetail(1L)).willReturn(mockUserDTO);

        mockMvc.perform(get("/api/v1/users/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.nickName").value("Iron Man"));
    }

    @DisplayName(value = "User-generated test")
    @Test
    void createUser() throws Exception {
        UserDTO mockUserDTO = new UserDTO(6L, "Peter Parker", 33, "Spider Man", "user");
        given(userService.registerUser(any(UserRegisterDTO.class))).willReturn(mockUserDTO);

        final String request = new Gson().toJson(new UserRegisterDTO("Peter Parker", 16, "Spider Man"));

        mockMvc.perform(
                        post("/api/v1/users")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(request)
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(6))
                .andExpect(jsonPath("$.nickName").value("Spider Man"))
        ;
    }

    @DisplayName(value = "Test modifying user nickname")
    @Test
    void updateUser() throws Exception {
        final Long mockUserId = 1L;
        UserDTO mockUserDTO = new UserDTO(mockUserId, "Tony Stark", 20, "Kirito", "user");
        given(userService.updateUser(any(Long.class), any(UserDTO.class))).willReturn(mockUserDTO);


        final String request = new Gson().toJson(mockUserDTO);

        mockMvc.perform(
                put("/api/v1/users/" + mockUserId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(request)
        )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.nickName").value("Kirito"));
    }

    @DisplayName("Test deleting users")
    @Test
    void deleteUser() throws Exception {
        mockMvc.perform(delete("/api/v1/users/6"))
                .andDo(print())
                .andExpect(status().isOk());

    }
}