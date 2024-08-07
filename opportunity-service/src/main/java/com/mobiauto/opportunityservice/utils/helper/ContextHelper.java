package com.mobiauto.opportunityservice.utils.helper;

import com.mobiauto.systemsecurity.user.entities.User;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class ContextHelper {

    public User getContextUser() {
        return  (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
}
