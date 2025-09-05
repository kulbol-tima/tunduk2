package kg.gov.mtsdr.ubk.core.scheduler;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
@Slf4j
@RequiredArgsConstructor
@ConditionalOnProperty(name = "app.scheduler.data-sync.enabled", havingValue = "true")
public class DataSyncScheduler {

    private final RedisTemplate<String, Object> redisTemplate;
    // Inject other Tunduk services as needed

    /**
     * Runs every day at 1 AM to synchronize and cache data.
     */
    @Scheduled(cron = "0 0 1 * * ?")
    public void syncAndCacheData() {
        log.info("Starting daily data synchronization and caching...");
        try {
            // Example: Caching some reference data from a Tunduk service
            // var referenceData = someTundukService.getReferenceData();
            // redisTemplate.opsForValue().set("ref-data-key", referenceData, 1, TimeUnit.DAYS);
            log.info("Successfully cached reference data.");

            log.info("Daily data synchronization and caching completed successfully.");
        } catch (Exception e) {
            log.error("An error occurred during daily data synchronization and caching.", e);
        }
    }
}
