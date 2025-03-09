package in.sp.main.QuestionRepo;

import in.sp.main.Entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Long> {
//    List<Question> findByTopicAndFetched(String topic, boolean b);
 
	

	boolean existsByQuestionTextAndTopic(String questionText, String topic);



	List<Question> findByTopicOrderByFetchCountAsc(String topic);

}
