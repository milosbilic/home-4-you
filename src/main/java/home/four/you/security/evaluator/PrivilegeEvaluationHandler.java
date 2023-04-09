package home.four.you.security.evaluator;


import home.four.you.security.UserPrincipal;
import home.four.you.security.auth.authorization.AuthorityPrivilege;

import java.io.Serializable;

/**
 * Interface representing the handler functionality for resolving resource privilege rights.
 */
@FunctionalInterface
public interface PrivilegeEvaluationHandler {

    /**
     * Evaluates the privilege rights over the provided resource ID and type.
     *
     * @param caller     User who claims to have a privilege on the resource.
     * @param resourceId Resource ID.
     * @param privilege  Required resource privilege.
     * @return true/false indicating the ownership
     */
    boolean handle(UserPrincipal caller, Serializable resourceId, AuthorityPrivilege privilege);
}
