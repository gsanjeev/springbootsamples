package com.laxtech.example;

import com.laxtech.example.model.Address;
import com.laxtech.example.model.Author;
import com.laxtech.example.model.Book;
import com.laxtech.example.model.Library;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.util.AssertionErrors.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringDataRestApplication.class, webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class SpringDataRestApplicationTests {

    @Autowired
    private TestRestTemplate testRestTemplate;
    private static String BOOK_ENDPOINT = "http://localhost:8080/books";
    private static String AUTHOR_ENDPOINT = "http://localhost:8080/authors";
    private static String ADDRESS_ENDPOINT = "http://localhost:8080/addresses";
    private static String LIBRARY_ENDPOINT = "http://localhost:8080/libraries";
    private static String LIBRARY_NAME = "Irving Public Library";
    private static String AUTHOR_NAME = "Matt David";


    @Test
    public void whenSaveOneToOneRelationship_thenCorrect() {
        //add library
        Library library = new Library();
        library.setName(LIBRARY_NAME);
        testRestTemplate.postForEntity(LIBRARY_ENDPOINT, library, Library.class);
        System.out.println("Library:" + testRestTemplate.getForEntity(LIBRARY_ENDPOINT + "/1", Library.class).getBody());

        //add address
        Address address = new Address();
        address.setLocation("100 Camden drive");
        testRestTemplate.postForEntity(ADDRESS_ENDPOINT, address, Address.class);
        System.out.println("Address:" + testRestTemplate.getForEntity(ADDRESS_ENDPOINT + "/2", Address.class).getBody());

        //associate address to library
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Content-type", "text/uri-list");
        HttpEntity<String> httpEntity = new HttpEntity<>(ADDRESS_ENDPOINT + "/2", httpHeaders);
        testRestTemplate.exchange(LIBRARY_ENDPOINT + "/1/libraryAddress", HttpMethod.PUT, httpEntity, String.class);

        ResponseEntity<Library> libraryResponseEntity = testRestTemplate.getForEntity(ADDRESS_ENDPOINT + "/2/library", Library.class);
        assertEquals("library is incorrect", libraryResponseEntity.getBody().getName(), LIBRARY_NAME);
    }

    @Test
    public void whenSaveOneToManyRelationship_thenCorrect() throws JSONException {
        //add library
        Library library = new Library();
        library.setName(LIBRARY_NAME);
        testRestTemplate.postForEntity(LIBRARY_ENDPOINT, library, Library.class);
        System.out.println("Library:" + testRestTemplate.getForEntity(LIBRARY_ENDPOINT + "/1", Library.class).getBody());

        //add books
        Book book1 = new Book();
        book1.setTitle("Our Past");
        testRestTemplate.postForEntity(BOOK_ENDPOINT, book1, Book.class);
        Book book2 = new Book();
        book2.setTitle("Look Beyond");
        testRestTemplate.postForEntity(BOOK_ENDPOINT, book2, Book.class);
        System.out.println("Books:" +
                (new JSONObject(testRestTemplate.getForObject(BOOK_ENDPOINT, String.class)))
                        .getJSONObject("_embedded").getJSONArray("books"));

        //associate address to library
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Content-type", "text/uri-list");
        HttpEntity<String> httpEntity = new HttpEntity<>(LIBRARY_ENDPOINT + "/1", httpHeaders);
        testRestTemplate.exchange(BOOK_ENDPOINT + "/2/library", HttpMethod.PUT, httpEntity, String.class);
        testRestTemplate.exchange(BOOK_ENDPOINT + "/3/library", HttpMethod.PUT, httpEntity, String.class);

        ResponseEntity<Library> libraryResponseEntity = testRestTemplate.getForEntity(BOOK_ENDPOINT + "/2/library", Library.class);
        assertEquals("library is incorrect", libraryResponseEntity.getBody().getName(), LIBRARY_NAME);
    }

    @Test
    public void whenSavemANYToManyRelationship_thenCorrect() throws JSONException {
        //add library
        Author author1 = new Author();
        author1.setName(AUTHOR_NAME);
        testRestTemplate.postForEntity(AUTHOR_ENDPOINT, author1, Author.class);
        System.out.println("Author:" + testRestTemplate.getForEntity(AUTHOR_ENDPOINT + "/1", Author.class).getBody());

        //add books
        Book book1 = new Book();
        book1.setTitle("Look Upward");
        testRestTemplate.postForEntity(BOOK_ENDPOINT, book1, Book.class);
        Book book2 = new Book();
        book2.setTitle("Beyond Limit");
        testRestTemplate.postForEntity(BOOK_ENDPOINT, book2, Book.class);

/*        System.out.println("Books:" +
                (new JSONObject(testRestTemplate.getForObject(BOOK_ENDPOINT, String.class)))
                        .getJSONObject("_embedded").getJSONArray("books"));*/


        //associate address to library
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Content-type", "text/uri-list");
        HttpEntity<String> httpEntity = new HttpEntity<>(BOOK_ENDPOINT + "/2\n" + BOOK_ENDPOINT + "/3", httpHeaders);

        testRestTemplate.exchange(AUTHOR_ENDPOINT + "/1/books", HttpMethod.PUT, httpEntity, String.class); //POST will append the relatioship. PUT will replace it.

        //System.out.println("Author:" + testRestTemplate.getForEntity(AUTHOR_ENDPOINT + "/1", Author.class).getBody());
        //System.out.println("Book 1:" + testRestTemplate.getForEntity(BOOK_ENDPOINT + "/2", Book.class).getBody());
        //System.out.println("Book 2:" + testRestTemplate.getForEntity(BOOK_ENDPOINT + "/3", Book.class).getBody());

        // System.out.println("Books:" + (new JSONObject(testRestTemplate.getForObject(AUTHOR_ENDPOINT
        //         + "/1/books", String.class)).getJSONObject("_embedded")).getJSONArray("books"));

        assertEquals("book is incorrect", (new JSONObject(testRestTemplate.getForObject(AUTHOR_ENDPOINT
                + "/1/books", String.class)).getJSONObject("_embedded")).getJSONArray("books").getJSONObject(0)
                .getString("title"), "Look Upward");


        String jsonResponse = testRestTemplate.getForObject(BOOK_ENDPOINT + "/2/authors", String.class);
        JSONObject jsonObject = new JSONObject(jsonResponse).getJSONObject("_embedded");
        JSONArray jsonArray = jsonObject.getJSONArray("authors");
        //System.out.println("Authors:" + jsonArray);
        assertEquals("author is incorrect", jsonArray.getJSONObject(0).getString("name"), AUTHOR_NAME);

    }

}
