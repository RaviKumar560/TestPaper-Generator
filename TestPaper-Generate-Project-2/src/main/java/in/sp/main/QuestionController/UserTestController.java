package in.sp.main.QuestionController;

import in.sp.main.QuestionServ.UserTestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController

public class UserTestController {

    @Autowired
    private UserTestService userTestService;

    @PostMapping("/submit")
    
    public String submitTest(
            @RequestParam String username,
            @RequestParam String email,
            @RequestParam String topic,
            @RequestParam String Date) {
        
        userTestService.saveUserTest(username, email, topic, Date);
        return "Test submitted successfully!";
    }
}
