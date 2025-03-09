package in.sp.main.QuestionServ;

import in.sp.main.Entity.UserTest;
import in.sp.main.QuestionRepo.UserTestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserTestService {

    @Autowired
    private UserTestRepository userTestRepository;

    public void saveUserTest(String username, String email, String topic,String Date) {
        UserTest userTest = new UserTest(null, username, email, topic, Date);
        userTestRepository.save(userTest);
    }
}
