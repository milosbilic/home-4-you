package home.four.you.task;

import home.four.you.service.AdService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for {@link AdExpirationScheduledTask}.
 */
@ExtendWith(MockitoExtension.class)
class AdExpirationScheduledTaskTest {

    AdExpirationScheduledTask task;

    @Mock
    AdService adService;

    @BeforeEach
    void setUp() {
        task = new AdExpirationScheduledTask(adService);
    }

    @Test
    @DisplayName("Mark outdated tasks as expired")
    void markOutdatedTasksAsExpired() {
        task.markOutdatedTasksAsExpired();

        Mockito.verify(adService, Mockito.times(1)).markOutdatedAsExpired();
    }
}
