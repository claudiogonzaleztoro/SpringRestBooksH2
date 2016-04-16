package com.nisum.bookDaoController;


import static org.springframework.http.HttpStatus.NOT_ACCEPTABLE;
import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.web.bind.annotation.RequestMethod.DELETE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import com.nisum.bookDaoServices.BookDao;
import com.nisum.domain.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {

    @Autowired
    private BookDao bookDao;

    @RequestMapping(value = "/get/{idBook}" , method = GET)
    @ResponseBody
    public ResponseEntity<Book> findBook(@PathVariable(value = "idBook") int idBook){

        boolean bookFound = bookDao.exists(idBook);

        if(bookFound){
            return new ResponseEntity<>(bookDao.findOne(idBook),OK);
        }else {
            return new ResponseEntity<>(NOT_FOUND);
        }

    }

    @RequestMapping(value = "/create" , method = POST)
    @ResponseBody
    public ResponseEntity<Book> createBook(@RequestBody Book book){

        boolean bookFound = bookDao.exists(book.getId());

        if(bookFound){

            return new ResponseEntity<>(NOT_ACCEPTABLE);

        }else {

            bookDao.save(book);
            return new ResponseEntity<>(book,OK);
        }

    }


    @RequestMapping(value = "/list" , method = GET)
    @ResponseBody
    public ResponseEntity<List<Book>> getAllBooks(){
        return new ResponseEntity<>(bookDao.findAll(),OK);
    }

    @RequestMapping(value = "/delete/{idBook}" , method = DELETE)
    @ResponseBody
    public ResponseEntity<Book> deleteBook(@PathVariable(value = "idBook") int idBook){

        boolean bookFound = bookDao.exists(idBook);

        if(bookFound){

            Book book = bookDao.findOne(idBook);
            bookDao.delete(idBook);
            Book b = book;
            return new ResponseEntity<>(book,OK);

        }else{
            return new ResponseEntity<>(NOT_FOUND);
        }
    }



}
