package learnUp.dz19.service.bookWareHouse;

import learnUp.dz19.entity.Book;
import learnUp.dz19.entity.BookWareHouse;
import learnUp.dz19.repository.BookWareHouseRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookWareHouseService {

    private final BookWareHouseRepository bookWareHouseRepository;

    public BookWareHouseService(BookWareHouseRepository bookWareHouseRepository) {
        this.bookWareHouseRepository = bookWareHouseRepository;
    }

    public List<BookWareHouse> getBookWareHouse() {return bookWareHouseRepository.findAll();}

    public BookWareHouse getBHWById(Long id){return bookWareHouseRepository.findBWHbyId(id);}


}
