package sparta.cafeteria.menu.infra.adapter;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import sparta.cafeteria.menu.application.out.PopularMenu;

import java.time.Duration;
import java.util.List;
import java.util.Set;

@Component
@RequiredArgsConstructor
public class PopularMenuRedisAdapter implements PopularMenu {

    private static final String KEY = "popular:menu";
    private final RedisTemplate<String, String> redisTemplate;

    @Override
    public void incrementMenuScore(Long menuId) {
        Boolean hasKey = redisTemplate.hasKey(KEY);
        redisTemplate.opsForZSet().incrementScore(KEY, menuId.toString(), 1);
        if (Boolean.FALSE.equals(hasKey)) {
            redisTemplate.expire(KEY, Duration.ofDays(7));
        }
    }

    @Override
    public List<Long> getTop3MenuIds() {
        Set<String> result = redisTemplate.opsForZSet().reverseRange(KEY, 0, 2);
        if (result == null || result.isEmpty()) return List.of();
        return result.stream().map(Long::parseLong).toList();
    }
}