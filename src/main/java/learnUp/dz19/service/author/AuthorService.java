package learnUp.dz19.service.author;

import learnUp.dz19.entity.Author;
import learnUp.dz19.repository.AuthorRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorService {

    private final AuthorRepository authorRepository;

    public AuthorService(AuthorRepository authorRepository){
        this.authorRepository = authorRepository;
    }

    public List<Author> getAuthor(){
        return authorRepository.findAll();
    }
}
