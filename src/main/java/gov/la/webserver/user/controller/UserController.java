package gov.la.webserver.user.controller;


import gov.la.webserver.comon.response.dto.ApiResponseDTO;
import gov.la.webserver.user.dto.UserDTO;
import gov.la.webserver.user.dto.UserRegisterDTO;
import gov.la.webserver.user.entity.User;
import gov.la.webserver.user.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
@Slf4j
public class UserController {
    private final UserService userService;

    @Operation(
            summary = "Get All User List Der",
            description = "Query saved users in the form of a list.",
            security = @SecurityRequirement(name = "basicScheme")
    )

    @ApiResponse(
            responseCode = "200",
            description = "Success"
    )
    @Secured("ROLE_ADMIN")
    @GetMapping
    public Flux<ApiResponseDTO<Object>> getAllUser() {
        log.info("call getAllUser");
        List<UserDTO> userList = userService.findAllUser();
        return Flux.fromIterable(userList)
                .map(user ->
                        ApiResponseDTO.builder()
                                .code(200)
                                .message("User List found")
                                .body(user)
                                .build());
    }


    @Operation(
            summary = "Get User List",
            description = "Query saved users in the form of a list."
    )
    @Parameter(
            name = "id", description = "identifier", example = "1", required = true
    )
    @ApiResponse(
            responseCode = "200",
            description = "Success"
    )
    @GetMapping("/{id}")
    public Mono<ApiResponseDTO<Object>> getDetail(@PathVariable final Long id) {
        log.info("call getIser >>> {}", id);
        UserDTO user = userService.getDetail(id);
        return Mono.justOrEmpty(ApiResponseDTO.builder()
                        .code(200)
                        .message("get user")
                        .body(user)
                .build());

    }


    @Operation(
            summary = "Update user",
            description = "Update the user."
    )
    @Parameters({
            @Parameter(name = "id", description = "identifier", example = "1", required = true),
    })
    @ApiResponse(
            responseCode = "200",
            description = "Success",
            content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = User.class))
            }
    )
    @PutMapping("/{id}")
    public Mono<ApiResponseDTO<Object>> updateUser(@PathVariable final Long id, @RequestBody final UserDTO user) {
        log.info("call updateUser >>> id {} / name {} / age {} / nickname {}", id, user.getName(), user.getAge(), user.getNickName());
        UserDTO resUserDTO = userService.updateUser(id, user);
        return Mono.justOrEmpty(ApiResponseDTO.builder()
                .code(200)
                .message("updated user")
                .body(resUserDTO)
                .build()
        ).switchIfEmpty(
                Mono.just(
                        ApiResponseDTO.builder()
                                .code(404)
                                .message("User not found")
                                .build()
                )
        );
    }


    @Operation(
            summary = "Delete user",
            description = "Delete the user."
    )
    @Parameters({
            @Parameter(name = "id", description = "identifier", example = "1", required = true),
    })
    @ApiResponse(
            responseCode = "200",
            description = "Success"
    )
    @DeleteMapping("/{id}")
    public Mono<ApiResponseDTO<Object>> deleteUser(@PathVariable final Long id) {
        log.info("call deleteUser >>> id {}", id);
        Boolean isDeleted = userService.deleteUser(id);
        return Mono.just(
                ApiResponseDTO.builder()
                        .code(200)
                        .message("deleted user")
                        .body(isDeleted)
                        .build()
        );
    }


    @Operation(
            summary = "Create user",
            description = "Register the user."
    )
    @ApiResponse(
            responseCode = "200",
            description = "Success",
            content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = User.class))
            }
    )
    @PostMapping
    public Mono<ApiResponseDTO<Object>> createUser(@RequestBody final UserRegisterDTO user) {
        log.info("call createUser >>> name: {} / age: {} / nickname{}", user.getName(), user.getAge(), user.getNickName());
        UserDTO userDTO = userService.registerUser(user);
        return Mono.justOrEmpty(
                ApiResponseDTO.builder()
                        .code(200)
                        .message("Create user")
                        .build()
        );
    }


}
