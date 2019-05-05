package com.vasiliska.Atlibrary.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vasiliska.Atlibrary.domain.Author;
import com.vasiliska.Atlibrary.domain.Book;
import com.vasiliska.Atlibrary.domain.Genre;
import com.vasiliska.Atlibrary.service.BookServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;

import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.*;
import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@RunWith(SpringRunner.class)
@WebMvcTest(BookController.class)
public class BookControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private BookServiceImpl bookService;

    private Author author;
    private Genre genre;
    private Book testBook;

    private final String TEST_BOOK_NAME = "Му-му";
    private final String TEST_AUTHOR = "Тургенев";
    private final String TEST_GENRE = "Драма";
    static final String PAGE = "index";

    @Before
    public void setUp() throws Exception {
        createTestBook();
    }


    @Test
    public void addNewBook() throws Exception {
        BookDto bookDto = new BookDto(1L, TEST_BOOK_NAME, null, null);
        ObjectMapper objectMapper = new ObjectMapper();
        mvc.perform(post("/api/v1/books")
                .contentType(APPLICATION_JSON_UTF8)
                .content(objectMapper.writeValueAsString(testBook)))
                .andExpect(status().isOk());
        verify(bookService, times(1))
                .addNewBook(bookDto.getBookName(), bookDto.getAuthorName(), bookDto.getGenreName());
    }

    @Test
    public void getBookById() throws Exception {
       when(bookService.bookByName(bookService.getBookByBookId(1L))).thenReturn(testBook);
        mvc.perform(get("/api/v1/books/" + "?id=1")).andExpect(status().isOk());
        verify(bookService, times(1)).getBookByBookId(1L);
    }

    @Test
    public void deleteById() throws Exception {
        this.mvc.perform(delete("/api/v1/books/1"))
                .andExpect(status().isOk());
        verify(bookService, times(1))
                .delBook(bookService.getBookByBookId(1L));
    }

    private void createTestBook() {

        author = new Author(TEST_AUTHOR);
        author.setAuthorId(1L);

        genre = new Genre(TEST_GENRE);
        genre.setGenreId(1L);

        testBook = new Book(TEST_BOOK_NAME, author, genre);
        testBook.setBookId(1L);
    }


}