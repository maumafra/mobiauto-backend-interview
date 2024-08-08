package com.mobiauto.systemsecurity.utils;

public class Constants {
    public static final String BLANK_FIELD_ERROR_MESSAGE = " cannot be null/blank.";
    public static final String STARTUP_USER_EMAIL = "admin@admin.com";
    public static final String STARTUP_USER_PASSWORD = "password";
    public static final String USER_DELETED_SUCCESSFULLY = "user deleted successfully.";
    public static final String ASSIGNABLE_USERS_QUERY =
            """
                    select a.username\s
                    from User a\s
                    where coalesce(a.qttOpportunitiesAttended, 0) =\s
                    (select min(coalesce(b.qttOpportunitiesAttended,0)) from User b where b.resaleId = :resaleId)
                    and a.resaleId = :resaleId\s
                    order by a.lastOpportunityReceived desc
            """;
}
