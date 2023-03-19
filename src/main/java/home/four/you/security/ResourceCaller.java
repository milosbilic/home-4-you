package home.four.you.security;

import lombok.Builder;
import lombok.Getter;

/**
 * Model representing resource caller.
 */
@Builder
@Getter
public class ResourceCaller {

    private final Long id;
    private final boolean admin;
    private final Long callerId;
}
