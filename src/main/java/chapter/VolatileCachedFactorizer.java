package chapter;

import java.io.IOException;
import java.math.BigInteger;

import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * @author liming
 * @version 2.2.7
 * @date 15-2-28 下午5:27
 */
public class VolatileCachedFactorizer implements Servlet {

	private volatile OneValueCache cache = new OneValueCache(null, null);

	public void service(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException {
		Long id = Long.parseLong(servletRequest.getParameter("id"));
		BigInteger bid = BigInteger.valueOf(id);
		BigInteger[]  fac = cache.getLastFactors(bid);
		if(fac != null){

		}
	}
	public void init(ServletConfig servletConfig) throws ServletException {

	}

	public ServletConfig getServletConfig() {

		return null;
	}

	public String getServletInfo() {

		return null;
	}

	public void destroy() {

	}
}
