package gisapp.demo.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import gisapp.demo.models.Board;

public interface BoardRepository extends JpaRepository<Board, Long> {

}
