package com.nisum.bookDaoServices;

import com.nisum.domain.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public interface BookDao extends JpaRepository<Book,Integer>{

}
