package com.koitt.java.tx;

import java.sql.Connection;
import java.sql.SQLException;

public class BoardService {
   
   private BoardDao dao = new BoardDao();
   
   /*
    * 롤백(Rollback) : SQL문 실행 전 상태로 되돌리는 것
    * 트랜젝션(Transaction) : 여러개의 sQL문을 하나의 작업단위로 묶는 것
    * 트랜젝션 처리 : 여러개의 SQL문 중 하나에서 문제가 발생했을 때 롤백을 호출하여 이전상태로 되돌리도록 하는 것을
    * 트랜젝션 처리라고 함.
    */
   
   public void add(Board board) {
      Connection conn = null;
      
      try {
         // 트랜젝션 처리영역 시작 부분
         conn = DBUtil.getInstance().getConnection();
         
         /*
          * 자동 commit을 막기위해 false 설정
          * Commit : 테이블에 최종적으로 SQL 실행한 결과를 반영하는 것
          * false로 설정했기 때문에 우리가 직접 커밋을 호출해야 한다.
          */
         
         conn.setAutoCommit(false);
         Integer no = dao.getBoardNo(conn);
         board.setNo(no);
         dao.insert(conn, board);
         
         // 마지막으로 SQL 실행한 결과를 반영
         conn.commit();
         
      } catch (Exception e) {
         e.printStackTrace();
         if (conn != null) {
            try {
               conn.rollback();
            } catch (SQLException e1) {
               e1.printStackTrace();
            }
         }
      } finally {
         if (conn != null) {
            try {
            	
            	// 원래 true로 되어있는 애를 false로 바꿔놨으니 마지막에 다시 true로 원래대로 돌린것 뿐. ㅎㅎ 
               conn.setAutoCommit(true);
               conn.close();	// 트랜젝션 처리 영역 끝부분
            } catch (SQLException e) {
               e.printStackTrace();
            }
         }
      }
   }

}