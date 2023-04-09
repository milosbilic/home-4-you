package home.four.you.security.evaluator;


import home.four.you.exception.ResourceNotFoundException;
import home.four.you.security.UserPrincipal;
import home.four.you.security.auth.authorization.AuthorityPrivilege;

import java.io.Serializable;

/**
 * Base interface representing the resource privilege evaluator.
 */
public interface PrivilegeEvaluator {

    /**
     * Returns the set of privileges supported by the concrete evaluator implementation.
     *
     * @return Supported privileges.
     */
    AuthorityPrivilege supportedPrivilege();

    /**
     * Performs the ownership evaluation.
     *
     * @param caller     User who claims to have the privilege on the resource.
     * @param resourceId Resource ID.
     * @return true  if user is privileged to perform action on the resource, otherwise false.
     * @throws ResourceNotFoundException in case that resource can't be found.
     */
    boolean evaluate(UserPrincipal caller, Serializable resourceId) throws ResourceNotFoundException;
}
