package guru.springframework.spring5webapp.bootstrap;

import guru.springframework.spring5webapp.domain.Author;
import guru.springframework.spring5webapp.domain.Book;
import guru.springframework.spring5webapp.domain.Publisher;
import guru.springframework.spring5webapp.repositories.AuthorRepository;
import guru.springframework.spring5webapp.repositories.BookRepository;
import guru.springframework.spring5webapp.repositories.PublisherRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BootstrapData implements CommandLineRunner {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    private final PublisherRepository publisherRepository;

    public BootstrapData(AuthorRepository authorRepository, BookRepository bookRepository, PublisherRepository publisherRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        System.out.println("Started in the bootstrap");

        Publisher newPublisher = new Publisher();
        newPublisher.setName("Publisher 1");
        newPublisher.setCity("Catanzaro");
        newPublisher.setState("IT");

        publisherRepository.save(newPublisher);

        System.out.println("Publisher count: " + publisherRepository.count());

        Author eric = new Author("Eric", "Evans");
        Book ddd = new Book("Libro 1", "123123");
        eric.getBooks().add(ddd);
        ddd.getAuthors().add(eric);

        ddd.setPublisher(newPublisher);
        newPublisher.getBooks().add(ddd);

        authorRepository.save(eric);
        bookRepository.save(ddd);
        publisherRepository.save(newPublisher);

        Author rod = new Author("Rod", "Johnson");
        Book ccc = new Book("Libro 2", "456456");
        rod.getBooks().add(ccc);
        ccc.getAuthors().add(rod);

        ccc.setPublisher(newPublisher);
        newPublisher.getBooks().add(ccc);

        authorRepository.save(rod);
        bookRepository.save(ccc);
        publisherRepository.save(newPublisher);

        System.out.println("numbers of book " + bookRepository.count());

        System.out.println("Publisher Number of Book: " + newPublisher.getBooks().size());
    }

}
