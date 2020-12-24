package com.huipeng1982.spring.jpa.specification;

import com.google.common.collect.Lists;
import com.huipeng1982.spring.jpa.specification.symbols.*;
import com.huipeng1982.utils.collection.ArrayUtil;
import com.huipeng1982.utils.collection.CollectionUtil;
import org.apache.commons.lang3.reflect.FieldUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.validation.constraints.NotNull;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.function.Consumer;

public final class SpecificationBuilder {
    private static final String PERCENT = "%";
    private static final int MAX_IN_SIZE = 1000;
    private static final String SERIAL_VERSION_UID = "serialVersionUID";
    private static Logger logger = LoggerFactory.getLogger(SpecificationBuilder.class);

    private SpecificationBuilder() {
    }

    public static <T> Specification<T> buildSpecification(@NotNull Object condition) {
        Class<?> conditionClass = condition.getClass();
        List<Field> declaredFields = ArrayUtil.asList(FieldUtils.getAllFields(conditionClass));
        return (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>(declaredFields.size());
            CollectionUtil.of(declaredFields).ifPresent(fields -> fields.stream().filter(checkFiledAccess()).forEach(fieldParse(root, predicates, cb, condition)));
            return cb.and(predicates.toArray(new Predicate[predicates.size()]));
        };
    }


    private static java.util.function.Predicate<Field> checkFiledAccess() {
        return field -> !(field.isAnnotationPresent(Page.class) || field.isAnnotationPresent(Ignore.class) || SERIAL_VERSION_UID.equals(field.getName()));
    }

    private static <T> Consumer<Field> fieldParse(Root root, List<Predicate> predicates, CriteriaBuilder cb, T condition) {
        return field -> {
            try {
                field.setAccessible(true);
                String fieldName = field.isAnnotationPresent(Name.class) ? field.getAnnotation(Name.class).value() : field.getName();
                CollectionUtil.of(field.get(condition)).ifPresent(fieldObject -> appendPredicate(root, predicates, field, fieldName, fieldObject, cb));
            } catch (IllegalAccessException e) {
                logger.error("SpecificationBuilder buildSpecification fieldParse error", e);
            }
        };
    }

    private static void appendPredicate(Root root, List<Predicate> predicates, Field field, String fieldName, Object fieldObject, CriteriaBuilder cb) {
        if (Collection.class.isAssignableFrom(field.getType())) {
            collectionHandler(root, predicates, field, fieldName, (Collection) fieldObject, cb);
        } else {
            if (field.isAnnotationPresent(Like.class)) {
                likeHandler(root, predicates, field, fieldName, fieldObject, cb);
            } else if (field.isAnnotationPresent(GreaterThan.class)) {
                predicates.add(cb.greaterThan(root.get(fieldName), (Comparable) fieldObject));
            } else if (field.isAnnotationPresent(GreaterThanEqual.class)) {
                predicates.add(cb.greaterThanOrEqualTo(root.get(fieldName), (Comparable) fieldObject));
            } else if (field.isAnnotationPresent(LessThan.class)) {
                predicates.add(cb.lessThan(root.get(fieldName), (Comparable) fieldObject));
            } else if (field.isAnnotationPresent(LessThanEqual.class)) {
                predicates.add(cb.lessThanOrEqualTo(root.get(fieldName), (Comparable) fieldObject));
            } else if (field.isAnnotationPresent(SmartLike.class)) {
                smartLikeHandler(root, predicates, fieldName, fieldObject, cb);
            } else if (field.isAnnotationPresent(Join.class)) {
                predicates.add(cb.equal(root.get(field.getAnnotation(Join.class).value()).get(fieldName), fieldObject));
            } else if (field.isAnnotationPresent(NonNull.class)) {
                predicates.add(cb.isNotNull(root.get(fieldName)));
            } else if (field.isAnnotationPresent(IsNull.class)) {
                predicates.add(cb.isNull(root.get(fieldName)));
            } else {
                predicates.add(cb.equal(root.get(fieldName), fieldObject));
            }
        }
    }

    private static void collectionHandler(Root root, List<Predicate> predicates, Field field, String fieldName, Collection<?> fieldObject, CriteriaBuilder cb) {
        if (CollectionUtil.isNotEmpty(fieldObject)) {
            if (field.isAnnotationPresent(Join.class)) {
                Join join = field.getAnnotation(Join.class);
                predicates.add(root.get(join.value()).get(fieldName).in(fieldObject));
            } else {
                if (fieldObject.size() > MAX_IN_SIZE) {
                    Predicate[] inPredicates = Lists.partition(new ArrayList<>(fieldObject), MAX_IN_SIZE).stream()
                        .map(listParams -> root.get(fieldName).in(listParams)).toArray(Predicate[]::new);
                    predicates.add(cb.or(inPredicates));
                } else {
                    predicates.add(root.get(fieldName).in(fieldObject));
                }
            }
        }
    }

    private static void smartLikeHandler(Root root, List<Predicate> predicates, String fieldName, Object fieldObject, CriteriaBuilder cb) {
        if (fieldObject.toString().contains(PERCENT)) {
            predicates.add(cb.like(root.get(fieldName), fieldObject.toString()));
        } else {
            predicates.add(cb.equal(root.get(fieldName), fieldObject));
        }
    }

    private static void likeHandler(Root root, List<Predicate> predicates, Field field, String fieldName, Object fieldObject, CriteriaBuilder cb) {
        Like like = field.getAnnotation(Like.class);
        String location = like.location();
        if (Like.AROUND.equals(location)) {
            predicates.add(cb.like(root.get(fieldName), PERCENT + fieldObject + PERCENT));
        }
        if (Like.LEFT.equals(location)) {
            predicates.add(cb.like(root.get(fieldName), PERCENT + fieldObject));
        }
        if (Like.RIGHT.equals(location)) {
            predicates.add(cb.like(root.get(fieldName), fieldObject + PERCENT));
        }
    }
}
