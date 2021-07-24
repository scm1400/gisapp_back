package gisapp.demo.Controller;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import gisapp.demo.models.Board;
import gisapp.demo.service.BoardService;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api")
public class BoardController {

    private final BoardService boardService;

    @Autowired
    public BoardController(BoardService boardService){
        this.boardService = boardService;
    }

    // get all board
    @GetMapping("/board")
    public List<Board> getAllBoards() {

        return boardService.getAllBoard();
    }

    @PostMapping("/board")
    public Board createBoard(@RequestBody Board board) {
        return boardService.createBoard(board);
    }
    @GetMapping("/board/{no}")
    public ResponseEntity<Board> getBoardByNo(
            @PathVariable Long no) {

        return boardService.getBoard(no);
    }

}
