package gisapp.demo.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import gisapp.demo.models.Board;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BoardRepository extends JpaRepository<Board, Long> {

    public final static String SELECT_BOARD_LIST_PAGED = ""
            + "SELECT "
            + "no,"
            + "type,"
            + "title,"
            + "contents,"
            + "member_no,"
            + "created_time,"
            + "updated_time,"
            + "likes,"
            + "counts"
            + " FROM board WHERE 0 < no "
            + "ORDER BY no DESC LIMIT ?1, ?2";

    @Query(value = SELECT_BOARD_LIST_PAGED, nativeQuery = true)
    List<Board> findFromTo(Integer objectStartNum, Integer objectCountPerPage);
}
