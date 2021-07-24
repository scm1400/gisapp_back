package gisapp.demo.service;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import gisapp.demo.exception.ResourceNotFoundException;
import gisapp.demo.util.PagingUtil;
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

    public int findAllCount() {
        return (int) boardRepository.count();
    }

    // get paging boards data
    public ResponseEntity<Map> getPagingBoard(Integer p_num) {
        Map result = null;

        PagingUtil pu = new PagingUtil(p_num, 5, 5); // ($1:표시할 현재 페이지, $2:한페이지에 표시할 글 수, $3:한 페이지에 표시할 페이지 버튼의 수 )
        List<Board> list = boardRepository.findFromTo(pu.getObjectStartNum(), pu.getObjectCountPerPage());
        pu.setObjectCountTotal(findAllCount());
        pu.setCalcForPaging();

        System.out.println("p_num : "+p_num);
        System.out.println(pu.toString());

        if (list == null || list.size() == 0) {
            return null;
        }

        result = new HashMap<>();
        result.put("pagingData", pu);
        result.put("list", list);

        return ResponseEntity.ok(result);
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
                .orElseThrow(() -> new ResourceNotFoundException("존재하지 않는 게시글입니다. : ["+no+"]"));
        return ResponseEntity.ok(board);
    }

    public ResponseEntity<Board> updateBoard(
            Long no, Board updatedBoard) {
        Board board = boardRepository.findById(no)
                .orElseThrow(() -> new ResourceNotFoundException("존재하지 않는 게시글입니다. : ["+no+"]"));
        board.setType(updatedBoard.getType());
        board.setTitle(updatedBoard.getTitle());
        board.setContents(updatedBoard.getContents());
        board.setUpdatedTime(new Date());

        Board endUpdatedBoard = boardRepository.save(board);
        return ResponseEntity.ok(endUpdatedBoard);
    }

    public ResponseEntity<Map<String, Boolean>> deleteBoard(
            Long no) {
        Board board = boardRepository.findById(no)
                .orElseThrow(() -> new ResourceNotFoundException("Not exist Board Data by no : ["+no+"]"));

        boardRepository.delete(board);
        Map<String, Boolean> response = new HashMap<>();
        response.put("Deleted Board Data by id : ["+no+"]", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }
}
