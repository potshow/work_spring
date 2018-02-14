package service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dao.TxDao;
import model.Job;

@Service		// service 클래스를 bean 객체로 만들고 싶을 때
				// @Service 애노테이션을 사용
public class TxService {

	@Autowired
	// @Qualifier("") 를 안쓴 Autowired ->
	// TxDao 라는 Bean 객체를 찾음. dao TxDao dao = new TxDao();
	private TxDao dao;
	
	@Transactional	// 해당 메서드는 Transaction 처리를 하겠다는 뜻
	public void save(Job job) {
		dao.update(job);
		//job.setJobId(job.getJobId() + "_"); // 첫번째 실행시 활성화
		job.setJobId(job.getJobId());		// 두번째 실행시 활성화
		dao.insert(job);
		
	}
	
	
}
