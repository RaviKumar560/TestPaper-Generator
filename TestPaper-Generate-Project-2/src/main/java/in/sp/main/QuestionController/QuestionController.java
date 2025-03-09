package in.sp.main.QuestionController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import in.sp.main.Entity.Question;
import in.sp.main.QuestionServ.QuestionService;

import java.util.Collections;
import java.util.List;

@Controller

public class QuestionController {

    @Autowired
    private QuestionService service;
    
    @GetMapping("/")
    public String display() {
		return "Question";
    	
    }

    @PostMapping("/fetch")
    @ResponseBody
    public String fetchQuestions() {
        service.fetchAndStoreQuestions("Core Java", "https://www.tpointtech.com/corejava-interview-questions", "h3.h3");
        return "Questions fetched and stored successfully!";
    }
//this is for get question by topic 
    @GetMapping("/generate")
    @ResponseBody
    public List<Question> generateTestPaper(@RequestParam String topic) {
    	
    	if(topic.equals("All Question")) {
    		List<Question> allQuestions1= service.getAllQuestion(10);
    		return allQuestions1;
    	}
    	else {
    		
    		 List<Question> allQuestions= service.getUniqueQuestionsByTopic(topic,10);
    	        return allQuestions;
    	}
    }
    
} 
