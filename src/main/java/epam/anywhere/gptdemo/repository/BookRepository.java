package epam.anywhere.gptdemo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import epam.anywhere.gptdemo.entity.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

}
