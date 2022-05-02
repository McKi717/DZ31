package learnUp.dz19.repository;

import learnUp.dz19.dao.book.BookFilter;
import learnUp.dz19.entity.Book;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.Predicate;

public class BookSpecifiacation {

    public static Specification<Book> byFilter(BookFilter filter){

        return ((root, query, criteriaBuilder) -> {
            Predicate predicate = criteriaBuilder.isNotNull(root.get("id"));

            if (filter.getNameBook() != null) {
                predicate = criteriaBuilder.and(predicate, criteriaBuilder.like(root.get("nameBook"), filter.getNameBook()));
            }
            return predicate;
        });
    }

}
