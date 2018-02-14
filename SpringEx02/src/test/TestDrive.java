package test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import dao.BoardDao;
import model.Board;

public class TestDrive {
	public static void main(String[] args) {
		ApplicationContext context = 
				new GenericXmlApplicationContext("/config/config.xml");
		
		BoardDao dao = context.getBean(BoardDao.class);
		Board board = dao.getBoard(2);	
		System.out.println(board);
		
		BoardDao dao2 = context.getBean(BoardDao.class);
		Board board2 = dao.getBoard(2);	
		System.out.println(board2);
	}

}
