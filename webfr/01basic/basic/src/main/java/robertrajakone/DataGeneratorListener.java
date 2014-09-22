package robertrajakone;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import ch.fhnw.webfr.flashcard.util.QuestionnaireInitializer;

public class DataGeneratorListener implements ServletContextListener {

	public void contextInitialized(ServletContextEvent sce) {
		
		if("test".equals(sce.getServletContext().getInitParameter("mode").toLowerCase() ))
			QuestionnaireInitializer.createQuestionnaires();
	}

	public void contextDestroyed(ServletContextEvent sce) {
		// not necessary because it's an inmemory database
	}

}
