package in.sp.main.QuestionServ;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.sp.main.Entity.Question;
import in.sp.main.QuestionRepo.QuestionRepository;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class QuestionService {
	
   
	@Autowired
    private QuestionRepository questionRepository;

    public void fetchAndStoreQuestions(String topic, String url, String selector) {
        try {
            Document doc = Jsoup.connect(url).get();//use to get the question from javapoint
            
            Elements questionElements = doc.select(selector);//select the h3 tag element from java point

            for (Element question : questionElements) {
                String questionText = question.text();//select only text by from  tag

                // Save only if not already present in DB
                if (!questionRepository.existsByQuestionTextAndTopic(questionText, topic)) {
                    Question q = new Question(null, topic, questionText,0);
                    questionRepository.save(q);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
  //this is for fetch by topic
    public List<Question> getUniqueQuestionsByTopic(String topic,int count) {
        // Fetch questions ordered by fetchCount in ascending order
        List<Question> allQuestions = questionRepository.findByTopicOrderByFetchCountAsc(topic);
        Collections.shuffle(allQuestions);
        // Select count number of questions
        List<Question> selectedQuestions = allQuestions.stream().limit(count).collect(Collectors.toList());

        // Increase fetchCount for selected questions 1++
        selectedQuestions.forEach(q -> {
            q.setFetchCount(q.getFetchCount() + 1);
            questionRepository.save(q); // Update in DB
        });

        return  selectedQuestions;
    }
    
    //This is for fetch all
    
    public List<Question> getAllQuestion(int count) {
        // Fetch questions ordered by fetchCount in ascending order
        List<Question> allQuestions = questionRepository.findAll();
        Collections.shuffle(allQuestions);
        // Select count number of questions
        List<Question> selectedQuestions = allQuestions.stream().limit(count).collect(Collectors.toList());

        // Increase fetchCount for selected questions 1++
        selectedQuestions.forEach(q -> {
            q.setFetchCount(q.getFetchCount() + 1);
            questionRepository.save(q); // Update in DB
        });

        return  selectedQuestions;
    }
   }
    

