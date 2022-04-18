package learnUp.dz19.controller;

import learnUp.dz19.repository.AuthorRepository;
import learnUp.dz19.repository.BookRepository;
import learnUp.dz19.repository.BookWareHouseRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/books")
public class booksController {

    private final BookRepository bookRepository;
    private final BookWareHouseRepository bookWareHouseRepository;
    private final AuthorRepository authorRepository;

    public booksController(BookRepository bookRepository, BookWareHouseRepository bookWareHouseRepository, AuthorRepository authorRepository) {
        this.bookRepository = bookRepository;
        this.bookWareHouseRepository = bookWareHouseRepository;
        this.authorRepository = authorRepository;
    }

    @GetMapping("/showAll")
    public String toString(Model model){
        model.addAttribute("books", bookRepository.findAll());
        return "book/showAllBooks";
    }


}
