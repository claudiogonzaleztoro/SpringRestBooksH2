package com.nisum.domain;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "Book")
public class Book {
    @Id
    @Column(name="ID")
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int bookId;
    @Column
    private String name;
    @Column
    private String editorial;
    @Column
    @ManyToMany(fetch=FetchType.EAGER)
    @JoinTable(
            name="Book_Author",
            joinColumns=@JoinColumn(name="book_id", referencedColumnName="ID"),
            inverseJoinColumns=@JoinColumn(name="author_id", referencedColumnName="ID"))
    private List<Author> authors;

    public int getId() {
        return bookId;
    }

    public void setId(int id) {
        this.bookId = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Author> getAuthor() {
        return authors;
    }

    public String getEditorial() {return editorial;
    }

    public void setEditorial(String editorial) {this.editorial = editorial;
    }

    public void setAuthor(List<Author> authors) {
        this.authors = authors;
    }
}
