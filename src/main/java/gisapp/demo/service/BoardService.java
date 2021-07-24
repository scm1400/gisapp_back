package gisapp.demo.service;
import java.util.List;

import gisapp.demo.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import gisapp.demo.models.Board;
import gisapp.demo.repository.BoardRepository;

import javax.transaction.Transactional;

@Service
public class BoardService {
    private final BoardRepository boardRepository;

    @Autowired
    public BoardService(BoardRepository boardRepository){
        this.boardRepository = boardRepository;
    }


    // get boards data
    public List<Board> getAllBoard() {
        return boardRepository.findAll();
    }

    @Transactional
    public Board createBoard(Board board) {
        return boardRepository.save(board);
    }

    public ResponseEntity<Board> getBoard(Long no) {
        Board board = boardRepository.findById(no)
                .orElseThrow(() -> new ResourceNotFoundException("Not exist Board Data by no : ["+no+"]"));
        return ResponseEntity.ok(board);
    }
}
