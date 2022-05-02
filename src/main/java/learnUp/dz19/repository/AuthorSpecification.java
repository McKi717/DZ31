package learnUp.dz19.repository;

import learnUp.dz19.dao.book.AuthorFilter;
import learnUp.dz19.entity.Author;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.Predicate;

public class AuthorSpecification {

    public static Specification<Author> byFilter(AuthorFilter filter){
        return ((root, query, criteriaBuilder) -> {
            Predicate predicate = criteriaBuilder.isNotNull(root.get("id"));

            if(filter.getFullName()!=null){
                predicate=criteriaBuilder.and(predicate, criteriaBuilder.like(root.get("fullName"), filter.getFullName()));
            }
            return predicate;
        });
    }
}
