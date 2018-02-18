package com.example.laxtech.LIS;

import org.elasticsearch.client.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;

import java.util.List;
import java.util.Map;

@SpringBootApplication
public class LisApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(LisApplication.class, args);
	}

	@Autowired
	private ElasticsearchOperations es;

	@Autowired
	private BookService bookService;

	@Override
	public void run(String... args) throws Exception {

		printElasticSearchInfo();

		bookService.save(new Book("1001", "Elasticsearch Basics", "Sanjeev Tripathi", "21-FEB-2018"));
		bookService.save(new Book("1002", "Apache Lucene Basics", "Sanjeev Tripath", "15-MAR-2018"));
		bookService.save(new Book("1003", "Apache Solr Basics", "Sanjeev Tripath", "11-MAR-2018"));

		System.out.println("FindByAuther Books Result");
		Page<Book> books = bookService.findByAuthor("Sanjeev", new PageRequest(0, 10));
		books.forEach(x -> System.out.println(x));

		System.out.println("FindByTitle Books Result");
		List<Book> booksResult = bookService.findByTitle("Elasticsearch Basics");
		booksResult.forEach(x->System.out.println(x));
	}

	//useful for debug, print elastic search details
	private void printElasticSearchInfo() {

		System.out.println("--ElasticSearch--");
		Client client = es.getClient();
		Map<String, String> asMap = client.settings().getAsMap();

		asMap.forEach((k, v) -> {
			System.out.println(k + " = " + v);
		});
		System.out.println("--ElasticSearch--");
	}

}
