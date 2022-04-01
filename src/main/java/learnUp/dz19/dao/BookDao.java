package learnUp.dz19.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;



@Repository
public class BookDao {

    private static final String FIND_BY_NAME = "select * from public.book where name_book = :name_book";

    private static final String SAVE = "" +
            "insert into public.book (name_book, name_author, orders)" +
            "values(:name_book, :name_author, :orders)";

    private static final String BUY = "" +
            "insert into public.book (name_book, name_author, orders)" +
            "values(:name_book, :name_author, :orders)";

    private final NamedParameterJdbcTemplate template;


    public BookDao(NamedParameterJdbcTemplate template) {
        this.template = template;
    }

    public void save (Book book){
        template.update(SAVE, new MapSqlParameterSource()
                .addValue("name_book", book.getNameBook())
                .addValue("name_author", book.getNameAuthor())
                .addValue("orders", book.getOrders()));
    }


    public Book findByBookName(String book){
        return template.query(FIND_BY_NAME,
                new MapSqlParameterSource("name_book", book),
                (rs, rn) -> Book.builder()
                        .id(rs.getLong("id"))
                        .nameBook(rs.getString("name_book"))
                        .nameAuthor(rs.getString("name_author"))
                        .orders(rs.getInt("orders"))
                        .build()).stream().findAny().orElseThrow(()->new RuntimeException("Книга с названием " + book + " не найдена"));
    }

    public void buyBookForNameBook(String book){
        MapSqlParameterSource in = new MapSqlParameterSource();
        Book b = findByBookName(book);
        in.addValue("orders", b.getOrders() - 1);
        in.addValue("name_book", b.getNameBook());
        in.addValue("name_author", b.getNameAuthor());
        in.addValue("id", b.getId());
        String orderBuy = "update Book set orders = :orders, name_book = :name_book, name_author = :name_author, id = :id where id = :id";
        template.update(orderBuy, in);
    }


}
