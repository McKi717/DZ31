package learnUp.dz19.service.author;

import learnUp.dz19.dao.book.AuthorFilter;
import learnUp.dz19.entity.Author;
import learnUp.dz19.repository.AuthorRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityExistsException;
import javax.persistence.LockModeType;
import javax.persistence.OptimisticLockException;
import java.util.List;

import static learnUp.dz19.repository.AuthorSpecification.byFilter;
import static org.springframework.data.jpa.domain.Specification.where;

@Service
@Slf4j
public class AuthorService {

    private final AuthorRepository authorRepository;

    public AuthorService(AuthorRepository authorRepository){
        this.authorRepository = authorRepository;
    }

    public List<Author> getAllAuthors(){
        return authorRepository.findAll();
    }

    public Author findAuthorById(Long id){
        return authorRepository.findAuthorById(id);
    }

    @Transactional
    public Author addAuthor(Author author){
        for (Author a: getAllAuthors()
             ) {if(a.getFullName().equals(author.getFullName())){
                 throw new EntityExistsException("Автор с таким именем уже есть");
        }
        }
      return  authorRepository.save(author);
    }

    public List<Author> getAuthorsBy(AuthorFilter filter){
        Specification<Author> specification = where(byFilter(filter)) ;
        return authorRepository.findAll(specification);
    }

    @Transactional
    @Lock(value = LockModeType.OPTIMISTIC)
    public void updateAuthor(Author author){
        try {
            authorRepository.save(author);
        }
        catch (OptimisticLockException e){
            log.warn("Optimistic lock exception");
            throw e;
        }
    }

    @Transactional
    @Lock(value= LockModeType.OPTIMISTIC)
    public Boolean delete(Long id) {
        try{authorRepository.delete(authorRepository.findAuthorById(id));
            return true;}
        catch (OptimisticLockException e){
            log.warn("Optimistick lock for author delete with id " + id);
            throw e;
        }
    }
}
