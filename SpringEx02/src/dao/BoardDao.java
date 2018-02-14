package dao;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import model.Board;

@Repository
public class BoardDao {

	@Autowired
	private SqlSessionFactory factory;
	
	@Autowired
	private SqlSession session01;
	
	public Board getBoard(Integer no) {
		SqlSession session = factory.openSession();
		Board board = session.selectOne("model.Board.select1", no);
	
		Board board2 = session01.selectOne("model.Board.select2", no);
		
		return board2;
	}
}

