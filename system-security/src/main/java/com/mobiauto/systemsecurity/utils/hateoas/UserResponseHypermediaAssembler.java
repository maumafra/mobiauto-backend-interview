package com.mobiauto.systemsecurity.utils.hateoas;

import com.mobiauto.systemsecurity.user.dtos.UserResponse;
import com.mobiauto.systemsecurity.user.controllers.UserController;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

public class UserResponseHypermediaAssembler {
    public static void addLinks(UserResponse userResponse) {
        userResponse.add(
                linkTo(
                        methodOn(UserController.class)
                                .getUserById(userResponse.getId())
                ).withSelfRel()
        );
    }
}
