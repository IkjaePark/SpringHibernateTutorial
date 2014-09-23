package com.inpcreations.BlogEntry.aop;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.inpcreations.BlogEntry.utils.RestResponse;

/**
 * @author      Ikjae Park <park.ikjae@gmail.com>
 * @version     1.0.0
 * @since       2014-09-22
 */
@Aspect
public class JSONFilter {
	
	/**
	 * HttpServletRequest is required to fetch the URL parameter for the JSON filter
	 */
	@Autowired(required=true)
	private HttpServletRequest request;
	
	/**
	 * Utilizes Spring AOP AfterReturning to post-process the return Object to filter based on the URL parameter 
	 *
	 * @param RestResponse pre-processed JSON data
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@AfterReturning(value="execution(public com.inpcreations.BlogEntry.utils.RestResponse+ com.inpcreations.BlogEntry.controller.*.*(..))", returning="result")
	public void process(RestResponse result) throws IOException {
		// Utilizing jackson mapper to convert our object into a Map
		ObjectMapper mapper = new ObjectMapper();
		JsonNode rootNode = mapper.valueToTree(result);
		
		// Fetching all of the fields from the Map to create a remove list
		Iterator<String> nodeIterator = rootNode.get("data").fieldNames();
		List<String> unnessaryFields = new ArrayList<String>();
		
		// Fetching the URL parameter
		String urlParamFilter = request.getParameter("filterFields");
		
		if (urlParamFilter != null && urlParamFilter.length() > 0) {
			// Loop through the list of fields to find the none-matching ones to remove
			while(nodeIterator.hasNext()) {
				String currentField = nodeIterator.next();
				
				if (!urlParamFilter.contains(currentField)) {
					unnessaryFields.add(currentField);
				}
			}
		}
		
		// Removing all of the fields that were not included in the URL parameter
		((ObjectNode) rootNode.get("data")).remove(unnessaryFields);
		
		// Reset the return data
		result.setData((ObjectNode) rootNode.get("data"));
	}
}
