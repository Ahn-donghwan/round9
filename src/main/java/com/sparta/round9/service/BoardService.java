package com.sparta.round9.service;

import com.sparta.round9.dto.*;
import com.sparta.round9.entity.Board;
import com.sparta.round9.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class BoardService {

    private final BoardRepository boardRepository;

    @Transactional
    public BoardSaveResponseDto saveBoard(BoardSaveRequestDto boardSaveRequestDto) {
        Board newBoard = new Board(boardSaveRequestDto.getTitle(), boardSaveRequestDto.getContents());

        Board savedBoard = boardRepository.save(newBoard);

        return new BoardSaveResponseDto(savedBoard.getId(), savedBoard.getTitle(), savedBoard.getContents());
    }

    public List<BoardSimpleResponseDto> getBoards() {
        List<Board> boardList = boardRepository.findAll();

        List<BoardSimpleResponseDto> dtoList = new ArrayList<>();

        for (Board board : boardList) {
            BoardSimpleResponseDto dto = new BoardSimpleResponseDto(board.getTitle());
            dtoList.add(dto);
        }
        return dtoList;
    }

    public BoardDetailResponseDto getBoard(Long boardId) {
        Board board = boardRepository.findById(boardId).orElseThrow(() -> new NullPointerException("ㅈㅅ 그런 보드 없음 !!"));

        return new BoardDetailResponseDto(board.getId(), board.getTitle(), board.getContents());
    }

    @Transactional
    public BoardUpdateTitleResponseDto updateBoardTitle(Long boardId, BoardUpdateTitleRequestDto boardUpdateTitleRequestDto) {
        Board board = boardRepository.findById(boardId).orElseThrow(() -> new NullPointerException("ㅈㅅ 그런 보드 업서여!"));

        board.updateTitle(boardUpdateTitleRequestDto.getTitle());

        return new BoardUpdateTitleResponseDto(board.getId(), board.getTitle(), board.getContents());
    }

    @Transactional
    public BoardUpdateContentsResponseDto updateBoardContents(Long boardId, BoardUpdateContentsRequestDto boardUpdateContentsRequestDto) {
        Board board = boardRepository.findById(boardId).orElseThrow(() -> new NullPointerException("ㅈㅅ 그런 보드 없어여!"));

        board.updateContents(boardUpdateContentsRequestDto.getContents());

        return new BoardUpdateContentsResponseDto(board.getId(), board.getTitle(), board.getContents());
    }

    @Transactional
    public void deleteBoard(Long boardId) { boardRepository.deleteById(boardId);
    }
}
