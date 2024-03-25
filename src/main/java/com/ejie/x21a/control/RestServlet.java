package com.ejie.x21a.control;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Enumeration;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Servlet implementation class RestServlet
 */
public class RestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	
	private static final Logger logger = LoggerFactory.getLogger(RestServlet.class);
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RestServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		logger.info(" ");
		logger.info("************************************************************");
		logger.info("****************** SERVLET REST GET ********************");
		logger.info("************************************************************");
//		
		this.traceRequest(request);
//		String streamToString = RestServlet.streamReaderToString(request);
//		
//		logger.info("Input Stream (Reader)");
//		logger.info(streamToString);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		logger.info(" ");
		logger.info("************************************************************");
		logger.info("****************** SERVLET REST POST ********************");
		logger.info("************************************************************");
		
		this.traceRequest(request);
//		String streamToString = RestServlet.streamToString(request.getInputStream());
//		
//		logger.info("Input Stream : ");
//		logger.info(streamToString);
		
	}
	
	@SuppressWarnings("rawtypes")
	public void traceRequest (HttpServletRequest request){
		
		logger.info("************************************************************");
		logger.info("******************INICIO REQUEST LOG********************");
		logger.info("************************************************************");
		
			
		logger.info("-----> InputStream content :");
		try {
			String streamToString = streamToString(request.getInputStream());
			
			logger.info(streamToString);
		} catch (IOException e) {
			logger.error("ERROR "+e.getMessage());
			logger.info("ERROR "+e.getMessage());
			e.printStackTrace();
		}
			
	
		logger.info("-----> Protocol : " + request.getProtocol());
		logger.info("-----> Method : " + request.getMethod());
		logger.info("-----> RequestURL : " + request.getRequestURL());
		
		logger.info("******** ATRIBUTES *************");
		
		Enumeration attributeNames = request.getAttributeNames();
		
		
		while(attributeNames.hasMoreElements()){
			String nextElement = (String) attributeNames.nextElement();
			logger.info("----->" +nextElement +" : " + request.getAttribute(nextElement).toString());
		}

		
		logger.info("******** PARAMETERS *************");
		
		Map parameterMap = request.getParameterMap();
		
		Set keySet = parameterMap.keySet();
		
		for (Object key : keySet) {
			
			logger.info("----->" +key +" : " + request.getParameter((String)key));
			
//			for (int i = 0; i < array.length; i++) {
//				logger.info("----->" +key +" : " + ((String[])request.getParameterValues(key).get(key))[0].toString());
//			}
//			
//			logger.info("----->" +key +" : " + ((String[])request.getParameterValues(name).get(key))[0].toString());
		}
		
		logger.info("******** HEADERS *************");
		Enumeration headerNames = request.getHeaderNames();
		
		while(headerNames.hasMoreElements()){
			String nextElement = (String) headerNames.nextElement();
			logger.info("----->" +nextElement +" : " + request.getHeader(nextElement));
		}

		logger.info("************************************************************");
		logger.info("******************END REQUEST LOG********************");
		logger.info("************************************************************");
	}
	
	public static String streamReaderToString(HttpServletRequest request) {

		try {
			BufferedReader reader;
			reader = request.getReader();
			StringBuilder sb = new StringBuilder();
			
			String line;
			while ((line = reader.readLine()) != null) {
				sb.append(line);
			}
			reader.close();
			
			return sb.toString();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.info("ERROR: "+e.getLocalizedMessage());
			return "ERROR";
		}
  }

	
	
    public static String streamToString(InputStream is) {

        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        StringBuilder sb = new StringBuilder();

        String line = null;
        try {
              while ((line = reader.readLine()) != null) {
                    sb.append(line + "\n");
              }
        } catch (IOException e) {
              e.printStackTrace();
              logger.info("ERROR: "+e.getLocalizedMessage());
              return "ERROR";
        } finally {
              try {
                    is.close();
              } catch (IOException e) {
            	  	logger.info("ERROR: "+e.getLocalizedMessage());
                    return "ERROR";
              }
        }

        return sb.toString();
  }


}
