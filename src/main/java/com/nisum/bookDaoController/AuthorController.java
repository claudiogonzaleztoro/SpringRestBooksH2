package com.nisum.bookDaoController;

import com.nisum.bookDaoServices.AuthorDao;
import com.nisum.domain.Author;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.http.HttpStatus.NOT_ACCEPTABLE;
import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
@RequestMapping("/author")
public class AuthorController {

    @Autowired
    private AuthorDao authorDao;

    @RequestMapping(value = "/get/{idAuthor}" , method = GET)
        public ResponseEntity<Author> getAuthor(@PathVariable("idAuthor") int idAuthor) {

        boolean authorFound = authorDao.exists(idAuthor);

        if (authorFound) {
            return new ResponseEntity<>(authorDao.findOne(idAuthor), OK);
        } else {
            return new ResponseEntity<>(NOT_FOUND);
        }
    }


    @RequestMapping(value = "/addAuthor/{name}/{lastname}" , method = POST)
    public ResponseEntity<Author> getAuthor(@PathVariable("name") String name , @PathVariable("lastname") String lastname) {

       Author authorExist = authorDao.getAuthorByNameAndLastName(name,lastname);

        if (authorExist!=null) {

            return new ResponseEntity<>(NOT_ACCEPTABLE);

        } else {
            Author newAuthor = new Author();
            newAuthor.setAuthorName(name);
            newAuthor.setAuthorLastName(lastname);
            authorDao.save(newAuthor);
            return new ResponseEntity<>(OK);
        }
    }

    @RequestMapping(value = "/list" , method = GET)
    public ResponseEntity<List<Author>>getAllAuthors(){
        return new ResponseEntity<>(authorDao.findAll(),OK);
    }

}
