package com.mobiauto.systemsecurity.user.controllers;

import com.mobiauto.systemsecurity.user.dtos.UpdateResaleIdRequest;
import com.mobiauto.systemsecurity.user.dtos.UpdateRoleRequest;
import com.mobiauto.systemsecurity.user.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/system-security/user")
@AllArgsConstructor
public class UserController {

    @Autowired
    private final UserService userService;

    @GetMapping(
            path = "/{userId}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<?> getUserById(final @PathVariable("userId") Integer userId) {
        return userService.getUserById(userId);
    }

    @DeleteMapping(path = "/delete/{userId}")
    @PreAuthorize("hasAuthority('ADMINISTRATOR', 'OWNER')")
    public ResponseEntity<?> deleteUserById(final @PathVariable("userId") Integer userId) {
        return userService.deleteUserById(userId);
    }

    @PutMapping(
            path = "/update-role",
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    @PreAuthorize("hasAuthority('ADMINISTRATOR', 'OWNER')")
    public ResponseEntity<?> updateRole(final @RequestBody UpdateRoleRequest updateRoleRequest) {
        return userService.updateRole(updateRoleRequest);
    }

    @PutMapping(
            path = "/update-resale-id",
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    @PreAuthorize("hasAuthority('ADMINISTRATOR')")
    public ResponseEntity<?> updateResaleId(final @RequestBody UpdateResaleIdRequest updateResaleIdRequest) {
        return userService.updateResaleId(updateResaleIdRequest);
    }
}
