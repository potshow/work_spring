package com.koitt.java.tx;

public class TestDrive {
	
	public static void main(String[] args) {
		
		// 서비스 사용을 위해 서비스 객체 생성
		BoardService service = new BoardService();
		
		// 글 작성 (번호는 서비스를 통해서 입력되고 작성일은 SQL문을 통해 입력된다)
		Board board = new Board(null, "제목-1", "내용-1", "작성자-1", null);
		
		// 작성한 글을 데이터베이스에 저장하기 위해 service의 add 메서드 호출
		service.add(board);
		
	}

}
