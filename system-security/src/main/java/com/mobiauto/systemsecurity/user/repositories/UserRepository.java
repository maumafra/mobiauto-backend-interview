package com.mobiauto.systemsecurity.user.repositories;

import com.mobiauto.systemsecurity.user.entities.User;
import com.mobiauto.systemsecurity.utils.Constants;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByUsername(final String username);
    @Query(Constants.ASSIGNABLE_USERS_QUERY)
    Optional<List<String>> findUsersWithLeastOpportunities(final @Param("resaleId") Integer resaleId);
}
