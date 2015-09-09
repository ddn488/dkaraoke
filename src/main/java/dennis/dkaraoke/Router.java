package dennis.dkaraoke;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

public class Router implements javax.servlet.Filter {

	private static final Pattern REWRITE_PATTERN = Pattern.compile("(^[1-9]\\d*)$");

	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain fc) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		
		if("/".equals(request.getPathInfo())) {
			RequestDispatcher dispatcher = request.getRequestDispatcher("index.htm");
			dispatcher.forward(req, res);
		}else {
	        fc.doFilter(req, res);
	    }
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub

	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}
}
