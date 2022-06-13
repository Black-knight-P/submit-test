package com.kakao.cafe.menu.repository;

import com.kakao.cafe.menu.dto.PopularMenuItemDto;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.core.types.dsl.NumberPath;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import static com.kakao.cafe.menu.domain.QMenuItem.menuItem;
import static com.kakao.cafe.order.domain.QOrder.order;

@RequiredArgsConstructor
@Repository
public class MenuCustomQueryRepository {

    private final JPAQueryFactory jpaQueryFactory;

    /**
     * 7일간 인기 상품 추출 Query
     * @return List<PopularMenuItemDto>
     */
    @Transactional
    public List<PopularMenuItemDto> getPopularMenus() {

        NumberPath<Long> count = Expressions.numberPath(Long.class, "saleCount");
        return jpaQueryFactory
                .select(Projections.fields(PopularMenuItemDto.class,
                        menuItem.id.as("menuItemId"),
                        menuItem.name.as("menuName"),
                        menuItem.count().as(count)))
                .from(order)
                .leftJoin(menuItem).on(order.menuItem.id.eq(menuItem.id))
                .where(order.orderDateTime.between(LocalDateTime.of(LocalDate.now().minusDays(7), LocalTime.MIN), LocalDateTime.of(LocalDate.now(), LocalTime.MAX)))
                .groupBy(menuItem.id)
                .orderBy(menuItem.count().desc())
                .limit(3L)
                .fetch();
    }
}
