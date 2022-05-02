package learnUp.dz19.controller;

import learnUp.dz19.dao.book.AuthorFilter;
import learnUp.dz19.entity.Author;
import learnUp.dz19.entity.Book;
import learnUp.dz19.service.author.AuthorService;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityExistsException;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class authorController {

    private AuthorService authorService;

    public authorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @PostMapping("/author")
    @Transactional
    public Author addAuthor(@RequestBody Author author) {
        if (author.getId() != null) {
            throw new EntityExistsException(
                    String.format("Post with id = %s already exist", author.getId())
            );
        }
        authorService.addAuthor(author);
        return author;
    }

    @GetMapping("/author")
    @Transactional
    public List<Author> getAuthors(@RequestParam(value = "fullName", required = false) String fullName
    ) {
        return authorService.getAuthorsBy(new AuthorFilter(fullName))
                .stream()
                .collect(Collectors.toList());
    }

    @GetMapping("/author/{id}")
    @Transactional
    public Author getAuthorById (@PathVariable Long id){
        Author author = authorService.findAuthorById(id);
        if(author==null){
            throw new EntityExistsException("Не найден id " + id);
        }
        return author;
    }

    @PutMapping("/author/{id}")
    @Transactional
    public Author updateAuthor(@PathVariable("id") Long id, @RequestBody Author author){
        Author author1 = getAuthorById(id);
        if(author1==null){
            throw new EntityExistsException("Не найден id " + id);
        }
        authorService.updateAuthor(author1);
        return author;
    }

    @DeleteMapping("/author/{id}")
    @Transactional
    public String deleteAuthor(@PathVariable Long id){
        Author author1 = getAuthorById(id);
        if(author1==null){
            throw new EntityExistsException("Не найден id " + id);
        }
        authorService.delete(id);
        return "Автор с id =  " + id + " удален";
    }

}
