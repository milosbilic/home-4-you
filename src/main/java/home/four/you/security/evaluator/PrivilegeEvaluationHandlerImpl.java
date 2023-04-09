package home.four.you.security.evaluator;

import home.four.you.security.ResourceCaller;
import home.four.you.security.auth.authorization.AuthorityPrivilege;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Map;

import static java.util.Optional.ofNullable;
import static java.util.function.Function.identity;
import static java.util.stream.Collectors.toUnmodifiableMap;

/**
 * Default implementation of {@link PrivilegeEvaluationHandler}.
 */
@Slf4j
@Component
public class PrivilegeEvaluationHandlerImpl implements PrivilegeEvaluationHandler, ApplicationContextAware {

    private Map<AuthorityPrivilege, PrivilegeEvaluator> evaluators;

    @Override
    public boolean handle(ResourceCaller caller, Serializable resourceId,
                          AuthorityPrivilege privilege) {
        log.info("Evaluating user [{}] privilege [{}] on resource ID [{}]", caller.getId(), privilege,
                resourceId);

        return ofNullable(evaluators.get(privilege))
                .map(evaluator -> evaluator.evaluate(caller, resourceId))
                .orElse(false);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        log.info("Initializing ownership evaluators");

        evaluators = applicationContext.getBeansOfType(PrivilegeEvaluator.class)
                .values().stream()
                .collect(toUnmodifiableMap(PrivilegeEvaluator::supportedPrivilege, identity()));
    }

}
