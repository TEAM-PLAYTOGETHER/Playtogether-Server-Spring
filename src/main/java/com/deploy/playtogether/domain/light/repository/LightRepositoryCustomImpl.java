package com.deploy.playtogether.domain.light.repository;

import com.deploy.playtogether.domain.light.Light;
import com.deploy.playtogether.domain.light.QLight;
import com.deploy.playtogether.domain.user.QUser;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Objects;

import static com.deploy.playtogether.domain.light.QLight.light;

@RequiredArgsConstructor
public class LightRepositoryCustomImpl implements LightRepositoryCustom{
    private final JPAQueryFactory queryFactory;

    @Override
    public List<Light> findAllByUserIdUsingCursor(final Long userId, final int size, final Long lastLightId) {
        return queryFactory
                .select(light)
                .from(light)
                .where(
                        ltLightId(lastLightId),
                        light.user.id.eq(userId)
                )
                .orderBy(light.id.desc())
                .limit(size)
                .fetch();
    }

    private BooleanExpression ltLightId(Long lastLightId) {
        if (Objects.isNull(lastLightId)){
            return null;
        }
        return light.id.lt(lastLightId);
    }
}
