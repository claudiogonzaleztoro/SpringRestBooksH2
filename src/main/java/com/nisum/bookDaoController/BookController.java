package com.nisum.bookDaoController;


import static org.springframework.http.HttpStatus.NOT_ACCEPTABLE;
import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.web.bind.annotation.RequestMethod.DELETE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import com.nisum.bookDaoServices.AuthorDao;
import com.nisum.bookDaoServices.BookDao;
import com.nisum.domain.Author;
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

    @Autowired
    private AuthorDao authorDao;

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

            Book newBook = new Book();
            newBook.setName(book.getName());
            newBook.setEditorial(book.getEditorial());
            newBook.setAuthor(book.getAuthors());
            bookDao.save(newBook);
            return new ResponseEntity<>(OK);
        }

    }


    @RequestMapping(value = "/list" , method = GET)
    @ResponseBody
    public ResponseEntity<List<Book>> getAllBooks(){
        List<Book>b = bookDao.findAll();
        return new ResponseEntity<>(bookDao.findAll(),OK);
    }

    @RequestMapping(value = "/delete/{idBook}" , method = DELETE)
    @ResponseBody
    public ResponseEntity<Book> deleteBook(@PathVariable(value = "idBook") int idBook){

        boolean bookFound = bookDao.exists(idBook);

        if(bookFound){

            Book book = bookDao.findOne(idBook);
            bookDao.delete(idBook);
            return new ResponseEntity<>(book,OK);

        }else{
            return new ResponseEntity<>(NOT_FOUND);
        }
    }

    @RequestMapping(value = "/addAuthorToBook/{idBook}", method = POST)
    @ResponseBody
    public ResponseEntity<Book> addAuthorToBook(@PathVariable(value = "idBook") int idBook, @RequestBody Author author) {

        boolean bookFound = bookDao.exists(idBook);

        if(bookFound){

            boolean authorFound = authorDao.exists(author.getAuthorId());

            if(authorFound){
                Book book = bookDao.findOne(idBook);
                book.getAuthors().add(author);
                bookDao.save(book);

                return new ResponseEntity<>(book,OK);
            }else{
                return new ResponseEntity<>(NOT_FOUND);
            }

        }else{
            return new ResponseEntity<>(NOT_FOUND);
        }

    }
    @RequestMapping(value = "/removeAuthorBook/{idBook}/{idAuthor}", method = DELETE)
    @ResponseBody
    public ResponseEntity<Book>removeAuthorBook(@PathVariable("idBook") int idBook, @PathVariable("idAuthor") int idAuthor){

        boolean bookFound = bookDao.exists(idBook);

        if(bookFound){

            Book book = bookDao.findOne(idBook);
            List<Author> authors = book.getAuthors();
            for (int i = 0; i <authors.size() ; i++) {
                if(authors.get(i).getAuthorId()==idAuthor){
                    authors.remove(i);
                }else{
                    return new ResponseEntity<>(NOT_FOUND);
                }
            }

            book.setAuthor(authors);
            bookDao.save(book);
            return new ResponseEntity<>(book,OK);

        }else{
            return new ResponseEntity<>(NOT_FOUND);
        }
    }

}
