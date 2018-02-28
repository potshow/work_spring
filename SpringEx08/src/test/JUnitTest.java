package test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import java.util.HashSet;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.koitt.book.dao.BookDao;
import com.koitt.book.dao.UsersDao;
import com.koitt.book.model.Authority;
import com.koitt.book.model.AuthorityId;
import com.koitt.book.model.Book;
import com.koitt.book.model.BookException;
import com.koitt.book.model.Users;
import com.koitt.book.model.UsersException;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="/test/config/applicationContext.xml")
public class JUnitTest {
	
	@Autowired
	private ApplicationContext context;
	
	// fixture
	@Autowired
	private BookDao bookDao;
	
	@Autowired
	private UsersDao usersDao;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	private Users users1;
	private Users users2;
	private Users users3;
	
	private Authority admin;
	private Authority user;
	private Set<Authority> adminSet = new HashSet<>();
	private Set<Authority> userSet = new HashSet<>();
	private Set<Authority> bothSet = new HashSet<>();
	
	private Book book1;
	
	@Before
	public void setUp() {
		String password = passwordEncoder.encode("1234");
		
		this.users1 = new Users(null, "users1@koitt.com", password, "김영미", null);
		this.users2 = new Users(null, "users2@koitt.com", password, "이승훈", null);
		this.users3 = new Users(null, "users3@koitt.com", password, "윤성빈", null);
		
		// 권한 객체 생성
		this.admin = new Authority(AuthorityId.ADMIN.getAuthorityId(), AuthorityId.ADMIN.name());
		this.user = new Authority(AuthorityId.USER.getAuthorityId(), AuthorityId.USER.name());
		
		// 권한 객체를 Set 컬렉션에 추가
		adminSet.add(admin);
		userSet.add(user);
		bothSet.add(admin);
		bothSet.add(user);
		
		// 사용자에게 권한 저장
		this.users1.setAuthorities(adminSet);
		this.users2.setAuthorities(userSet);
		this.users3.setAuthorities(bothSet);
		
		this.book1 = 
				new Book(null, null, "책 제목", "책 내용", "책 작가", 30000, "책 설명", null);
	}
	
	
	// 사용자 추가 + 불러오기 메서드
	@Test
	public void addAndGetUsers() throws BookException, UsersException {
		
		// user 테이블 내용 삭제를 위해 board 삭제
		bookDao.deleteAll();
		assertThat(bookDao.getCount(), is(0));
		
		// user 테이블 내용 삭제를 위해 authority 삭제
		usersDao.deleteAllUsersAuthority();
		assertThat(usersDao.getCountUsersAuthority(), is(0));
		
		// user 테이블 내용 삭제 후 삭제됬는지 확인
		usersDao.deleteAll();
		assertThat(usersDao.getCount(), is(0));
		
		// user 테이블에 사용자 추가 후 추가됬는지 확인
		usersDao.insert(users1);
		users1.setNo(usersDao.selectLastInsertId());
		usersDao.insert(users2);
		users2.setNo(usersDao.selectLastInsertId());
		usersDao.insert(users3);
		users3.setNo(usersDao.selectLastInsertId());
		assertThat(usersDao.getCount(), is(3));
		
		// 입력된 사용자에게 권한 입력
		usersDao.insertAuthority(users1);
		usersDao.insertAuthority(users2);
		usersDao.insertAuthority(users3);
		assertThat(usersDao.getCountUsersAuthority(), is(4)); // users3 권한이 2개라서
		
		// 입력된 사용자가 불러와지는지 확인
		Users usersget1 = usersDao.selectByEmail(users1.getEmail());
		assertThat(usersget1.getName(), is(users1.getName()));
		assertTrue(passwordEncoder.matches("1234", usersget1.getPassword()));
		
		Users usersget2 = usersDao.selectByEmail(users2.getEmail());
		assertThat(usersget2.getName(), is(users2.getName()));
		assertTrue(passwordEncoder.matches("1234", usersget2.getPassword()));
		
		Users usersget3 = usersDao.selectByEmail(users3.getEmail());
		assertThat(usersget3.getName(), is(users3.getName()));
		assertTrue(passwordEncoder.matches("1234", usersget3.getPassword()));
		
	}
	
	@Test
	public void addAndGetBook() throws BookException, UsersException {
		// 테스트 전 테이블 삭제(board->user_authority->users)
		bookDao.deleteAll();
		assertThat(bookDao.getCount(), is(0));
		
		usersDao.deleteAllUsersAuthority();
		assertThat(usersDao.getCountUsersAuthority(), is(0));
		
		usersDao.deleteAll();
		assertThat(usersDao.getCount(), is(0));
		
		// 글 작성할 사용자 추가
		usersDao.insert(users1);
		Integer userNo = usersDao.selectLastInsertId();	//추가한 사용자의 번호 가져옴
		assertThat(usersDao.getCount(), is(1));
		
		book1.setUserNo(userNo);
		bookDao.insert(book1);
		Integer bookNo = bookDao.selectLastInsertId();
		assertThat(bookDao.getCount(), is(1));
		
		Book bookget1 = bookDao.select(bookNo.toString());
		assertThat(bookget1.getTitle(), is(book1.getTitle()));
		assertThat(bookget1.getAuthor(), is(book1.getAuthor()));
		assertThat(bookget1.getPublisher(), is(book1.getPublisher()));
		assertThat(bookget1.getPrice(), is(book1.getPrice()));
		assertThat(bookget1.getDescription(), is(book1.getDescription()));
		
	}

}

