package robertrajakone;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.ConsoleAppender;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

public class LoggerFilter implements Filter {
	private Logger logger = Logger.getLogger(this.getClass());
	
	public LoggerFilter(){
		logger.addAppender(new ConsoleAppender());
	}
	

	public void init(FilterConfig filterConfig) throws ServletException {
		logger.debug("init Filter");
		logger.setLevel(Level.DEBUG);
	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		
		logger.debug("before next filter: "+ httpRequest.getPathInfo());
		
		chain.doFilter(request, response);
		
		logger.debug("rückweg: after last filter");
	}

	public void destroy() {
		logger.debug("destroy Filter");
	}

}
