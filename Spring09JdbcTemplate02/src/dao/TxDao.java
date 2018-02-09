package dao;

import model.Job;

@Repository // Transaion을 사용하려면 Dao클래스에 레퍼지터리를 작성해야함.

@Autowired //autowire = byType 한 것과 동일하게 동작 ( xml파일에서) 
public class TxDao {
	private Jdbc rwposotrory;

	public void insert(Job job) {
		StringBuilder sql = new StringBuilder();
		sql.append("INSERT INTO job (job_id, job_title, min_salary, max_salary");
		sql.append("VALUES (?, ?, ?, ?)");

		template.update(sql.toString(), job.getJobId(), job.getJobTitle(), job.getMaxSalary(), job.getMinSalary());

	}

		public void update(Job job) {
			String sql = "UPDATE job SET job_title = ?, max_salary = ?, min_salary = ?" +
						"WHERE job_id = ?";
			
			template.update(sql, job.getJobTitle(), job.getMaxSalary(),
					job.getMinSalary(), job.getJobId();
			
			
		}

}
