package th.co.dptf.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import th.co.dptf.entity.User;


@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	
	
	@Query(value= "SELECT * FROM users", nativeQuery=true)
	List<User> usersList();
	
//	เงื่อนไข
	@Query(value= "SELECT * FROM users WHERE username =?1", nativeQuery=true)
	User usersExits(String username);

//	เพิ่มข้อมูล 
	@Modifying(clearAutomatically=true)
	@Query(value= "INSERT INTO users (id, username, password) VALUES (?1, ?2, ?3)", nativeQuery=true)
	int addUser(int id, String username, String password);
	
//	แก้ไข Update
	@Modifying(clearAutomatically=true)
	@Query(value= "UPDATE users SET password = ?1 WHERE id = ?2", nativeQuery=true)
	int resetPassword(String password,int id);

//	Page
	@Query(value= "SELECT * FROM (SELECT ROW_NUMBER() OVER (ORDER BY username) AS seq, * FROM users) AS SUB WHERE SUB.seq >=?1 AND SUB.seq <=?2",nativeQuery=true)
	List<User> userLists(int start, int end);
	
	@Query(value= "SELECT COUNT(username) FROM users",nativeQuery = true)
	int countRows();
	
	@Modifying(clearAutomatically=true)
	@Query(value= "DELETE FROM users WHERE id=?1", nativeQuery=true)
	int deleteuser(int id);
	
	
	

}
