package com.nisum.bookDaoServices;

import com.nisum.domain.Author;
import com.nisum.domain.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface AuthorDao extends JpaRepository<Author,Integer>{
    @Query("select b from Author b where b.authorName=?1 and b.lastName=?2")
    public Author getAuthorByNameAndLastName(String name , String lastName);
}
