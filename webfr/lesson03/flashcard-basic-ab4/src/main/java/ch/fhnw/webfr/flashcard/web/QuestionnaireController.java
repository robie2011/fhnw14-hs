package ch.fhnw.webfr.flashcard.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import ch.fhnw.webfr.flashcard.domain.Questionnaire;
import ch.fhnw.webfr.flashcard.persistence.QuestionnaireRepository;

@Controller
@RequestMapping("/questionnaires")
public class QuestionnaireController {
	@Autowired
	QuestionnaireRepository questionnaireRepository;
	
	@RequestMapping(method = RequestMethod.GET)
	public String List(Model model){
		System.out.println("auflisten");
		java.util.List<Questionnaire> q = questionnaireRepository.findAll();
		model.addAttribute("questionnaire", "somevalue");
		model.addAttribute("questionnaires", q );
		return "/questionnaires";
	}

	
	@RequestMapping(method = RequestMethod.POST)
	public String Create(String title, String description, Model model){
		System.out.println(title);
		System.out.println(description);
		model.addAttribute("questionnaire", "value");
		return "redirect:/questionnaires";
	}

}
