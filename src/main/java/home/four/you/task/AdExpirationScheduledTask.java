package home.four.you.task;

import home.four.you.service.AdService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * Scheduled task used to ensure that outdated ads are invalidated and set to expired state.
 */
@Component
@RequiredArgsConstructor
@Slf4j
public class AdExpirationScheduledTask {

    private final AdService adService;

    @Scheduled(cron = "0 0 * * * ?") // Run every day at 00:00
    public void markOutdatedTasksAsExpired() {
        log.debug("Running scheduled ad expiration task");

        adService.markOutdatedAsExpired();
    }
}
