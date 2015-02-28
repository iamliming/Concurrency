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
			System.out.println("cache info");
			for (BigInteger i : fac) {
				System.out.println(i);
			}
		}
		else{
			BigInteger[] newfac = new BigInteger[]{BigInteger.valueOf(1l), BigInteger.valueOf(2l)};
			cache = new OneValueCache(bid, newfac);
			System.out.println("not cache");
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
