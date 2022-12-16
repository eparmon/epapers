package com.iapps.epapers.persistence.predicate;

import com.iapps.epapers.persistence.domain.QEpaperRequest;
import com.querydsl.core.types.ExpressionUtils;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.Expressions;

import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

public class EpaperRequestPredicateBuilder {

    private Predicate newspaperNameExpression;

    private Predicate screenWidthExpression;

    private Predicate screenHeightExpression;

    private Predicate screenDpiExpression;

    public EpaperRequestPredicateBuilder withNewspaperName(String newspaperName) {
        if (newspaperName != null) {
            newspaperNameExpression = QEpaperRequest.epaperRequest.newspaperName.eq(newspaperName);
        }
        return this;
    }

    public EpaperRequestPredicateBuilder withScreenWidth(Integer screenWidth) {
        if (screenWidth != null) {
            screenWidthExpression = QEpaperRequest.epaperRequest.screenWidth.eq(screenWidth);
        }
        return this;
    }

    public EpaperRequestPredicateBuilder withScreenHeight(Integer screenHeight) {
        if (screenHeight != null) {
            //noinspection SuspiciousNameCombination
            screenHeightExpression = QEpaperRequest.epaperRequest.screenHeight.eq(screenHeight);
        }
        return this;
    }

    public EpaperRequestPredicateBuilder withScreenDpi(Integer screenDpi) {
        if (screenDpi != null) {
            screenDpiExpression = QEpaperRequest.epaperRequest.screenDpi.eq(screenDpi);
        }
        return this;
    }

    public Predicate build() {
        List<Predicate> notNullPredicates = Stream.of(
                        newspaperNameExpression,
                        screenWidthExpression,
                        screenHeightExpression,
                        screenDpiExpression)
                .filter(Objects::nonNull)
                .toList();
        if (notNullPredicates.isEmpty()) {
            return Expressions.asBoolean(true).isTrue();
        }
        return ExpressionUtils.allOf(notNullPredicates);
    }

}
