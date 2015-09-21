/**
 * 
 */
package cz.cbf.date.extractor;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

/**
 * @author jknetl
 *
 */
public class MatchParser {
	
	static final Logger logger = LoggerFactory.getLogger(MatchParser.class);
	
	//TODO: Use parser which can deal with invalid XML (such as JSoup)
	public List<Match> parseMatches(File file) throws XPathExpressionException, ParserConfigurationException, SAXException, IOException{
		
		List<Match> matches = new ArrayList<Match>();
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		Document doc = builder.parse(file);
		XPathFactory xPathfactory = XPathFactory.newInstance();
		XPath xpath = xPathfactory.newXPath();
		XPathExpression expr = xpath.compile("/html/body/xml/div/div/div/div/div/div/table/tbody");
		
		String exprResult = (String) expr.evaluate(doc, XPathConstants.STRING);
		
		logger.debug("Xpath expression result: {}", exprResult);
		
		return matches;
	}

}
