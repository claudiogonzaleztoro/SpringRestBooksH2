package com.nisum.domain;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "Author")
public class Author {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name="id")
    private int authorId;
    @Column(name = "author_name")
    private String authorName;
    @Column(name = "last_name")
    private String lastName;

    public int getAuthorId() {return authorId;}

    public void setAuthorId(int authorId) {this.authorId = authorId;}

    public String getAuthorName() {return authorName;}

    public void setAuthorName(String authorName) {this.authorName = authorName;}

    public String getAuthorLastName() {return lastName;}

    public void setAuthorLastName(String lastName) {this.lastName = lastName;}
}
