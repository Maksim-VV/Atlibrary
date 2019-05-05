package com.vasiliska.Atlibrary.rest;


import com.vasiliska.Atlibrary.domain.Author;
import com.vasiliska.Atlibrary.domain.Book;
import com.vasiliska.Atlibrary.domain.Genre;
import lombok.Data;

@Data
public class BookDto {

    private Long bookId;
    private String bookName;
    private Author author;
    private Genre genre;
    private String authorName;
    private String genreName;

    public BookDto() {
        }

    public BookDto(long id, String bookName, Author author, Genre genre) {
        this.bookId = id;
        this.bookName = bookName;
        this.author = author;
        this.genre = genre;
    }

    public static BookDto toDto(Book book) {
        return new BookDto(book.getBookId(), book.getBookName(), book.getAuthor(), book.getGenre());
    }
}
