package ch.fhnw.webfr.flashcard.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ch.fhnw.webfr.flashcard.domain.Questionnaire;
import ch.fhnw.webfr.flashcard.persistence.QuestionnaireRepository;
import ch.fhnw.webfr.flashcard.util.QuestionnaireInitializer;

@SuppressWarnings("serial")
public class BasicServlet extends HttpServlet {
	private Router router;
	
	private class Router{
		private String[] _path;
		public Router(String path){
			_path = path.split("/");
		}
		
		public String getControllerName(){
			if(_path.length>1) return _path[1];
			else return null;
		}
		
		public String getAction(){
			if(_path.length>2) return _path[2];
			else return null;
		}
	}
	

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=utf-8");
//		System.out.println(request.getPathInfo());
		
		router = new Router(request.getPathInfo());
//		System.out.println(router.getControllerName());
//		System.out.println(router.getAction());
		
		// Dispatching Process
		String[] pathElements = request.getRequestURI().split("/");
		if ("questionnaires".equals(router.getControllerName())) {
			handleQuestionnairesRequest(request, response);
		} else {
			handleIndexRequest(request, response);
		}
	}

	private boolean isLastPathElementQuestionnaires(String[] pathElements) {
		String last = pathElements[pathElements.length-1];
		return last.equals("questionnaires");
	}

	private void handleQuestionnairesRequest(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		if(router.getAction() == null || router.getAction().equals("")){			
			List<Questionnaire> questionnaires = QuestionnaireRepository.getInstance().findAll();
			PrintWriter writer = response.getWriter();
			writer.append("<html><head><title>Example</title></head><body>");
			writer.append("<h3>Fragebögen</h3>");
			
			// Todo: Handling empty result
			for (Questionnaire questionnaire : questionnaires) {
				String url = request.getContextPath()+request.getServletPath();
				url = url + "/questionnaires/" + questionnaire.getId().toString();
				writer.append("<p><a href='" + response.encodeURL(url) +"'>" + questionnaire.getTitle() + "</a></p>");
			}
			writer.append("</body></html>");
		}else{
 			Long id = Long.parseLong(router.getAction());
			Questionnaire q = QuestionnaireRepository.getInstance().findById(id);
			PrintWriter writer = response.getWriter();
			writer.append("<html><head><title>Example</title></head><body>");
			writer.append("<h3>"+q.getTitle() +"</h3>");
			writer.append("<p>" + q.getDescription() +"</p>");
			writer.append("</body></html>");
		}
	}

	private void handleIndexRequest(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		PrintWriter writer = response.getWriter();
		writer.append("<html><head><title>Example</title></head><body>");
		writer.append("<h3>Welcome</h3>");
		String url = request.getContextPath()+request.getServletPath();
		writer.append("<p><a href='" + response.encodeURL(url) + "/questionnaires'>All Questionnaires</a></p>");
		writer.append("</body></html>");
	}
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);		
	}

}
