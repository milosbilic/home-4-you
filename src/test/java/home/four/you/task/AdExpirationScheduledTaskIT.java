package home.four.you.task;

import home.four.you.HttpBasedTest;
import home.four.you.model.entity.Ad;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.Instant;

/**
 * Integration tests for {@link AdExpirationScheduledTask}.
 */
@DisplayName("Ad expiration task")
public class AdExpirationScheduledTaskIT extends HttpBasedTest {

    @Autowired
    AdExpirationScheduledTask task;

    @Test
    @DisplayName("Mark outdated ads as expired")
    void markOutdatedAdsAsExpired() {
        Ad randomAd = createRandomAd();
        adRepository.save(randomAd.setExpirationDate(Instant.now()));

        task.markOutdatedTasksAsExpired();

        randomAd = adRepository.findById(randomAd.getId())
            .orElseThrow();

        Assertions.assertThat(randomAd.getExpired()).isTrue();
    }
}
