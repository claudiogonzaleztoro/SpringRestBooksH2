package com.nisum.bookDaoServices;

import com.nisum.domain.Author;
import com.nisum.domain.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

@Service
public interface BookDao extends JpaRepository<Book,Integer>{
    @Query("select b from Book b join b.authors c where c=?1")
    public Book containAuthor(Author author);

}
