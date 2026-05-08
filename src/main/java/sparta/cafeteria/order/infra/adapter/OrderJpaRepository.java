package sparta.cafeteria.order.infra.adapter;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;

public interface OrderJpaRepository extends JpaRepository<OrderJpaEntity, Long> {

    @Query("SELECT o.menuId, COUNT(o) FROM OrderJpaEntity o WHERE o.orderedAt >= :from GROUP BY o.menuId ORDER BY COUNT(o) DESC LIMIT 3")
    List<Object[]> findTop3MenuIdsSince(LocalDateTime from);
}