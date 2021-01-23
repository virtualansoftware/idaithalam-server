package ch.inss.idaiserver.service;

import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.net.URLClassLoader;
import java.time.Duration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import ch.inss.idaiserver.model.Report;
import ch.inss.idaiserver.utils.FileManagement;
import io.virtualan.idaithalam.contract.IdaithalamExecutor;


@Service
public class TestService {

  private static final Logger logger = LoggerFactory.getLogger(TestService.class);
  
  /** Folder to store the Cucumber reports. */
  @Value("${ch.inss.idaiserver.reports.folder}")
  private String storagePath;
  
  @Value("${ch.inss.idaiserver.host}")
  private String serverHost;
  
  @Autowired private Cucumblan cucumblan;
  
  private static final String FEATURE = "virtualan-contract.feature";
  private static final String REPORTS = "cucumber-html-reports/";
  
  

  /* Main method to do the actual Maven tests. */
  public Report doTest()      {
	  
	logger.debug("Store reports in folder: " + this.addSlash(this.storagePath) + cucumblan.getFolder());
	logger.debug("Cucumblan: " + cucumblan.toString());
	Report links = new Report();
	try {
		
	    /* Initialize response. */
	    links.setError(FileManagement.NOERROR);
	    links.setCreationTime(FileManagement.whatTime());
	    links.setSkipResponseValidation(cucumblan.getSkipResponseValidation());
	    
	    final String testId = cucumblan.getUuid().toString();
	    links.setTestid(cucumblan.getUuid());
	    
	    /* Paths for the results in the filesystem and URLs. */
		final String reportFolder = this.storagePath + FileManagement.fs + cucumblan.getFolder() + FileManagement.fs;
//		final String confFolder = reportFolder + FileManagement.fs + "conf" + FileManagement.fs;
		final String skip = ".*=IGNORE";
		final String skipProp = reportFolder + "exclude-response.properties";
		final String reportURL = this.serverHost + "/" + cucumblan.getFolder();
		
		    
	    /* Take care of the folder where all the files and reports will be stored. */
	    if (!new File(reportFolder).exists()) {
	      new File(reportFolder).mkdir();
//	      new File(confFolder).mkdir();
	    }
	    if (!new File(reportFolder).exists()) {
	      logger.error("Cannot create folder " + reportFolder);
	      links.setError("Cannot create folder " + reportFolder);
	      return links;
	    }
	    /* Generate the cucumblan.properties and some other files.*/
	    boolean isSaved =  FileManagement.saveCucumblan(reportFolder, cucumblan.toString());
	    String postmanFile = reportFolder + cucumblan.getUploadFilename();
    	FileManagement.writeFilestream(postmanFile, cucumblan.getInputStream());
    	logger.info("skipResponseValidation: " + cucumblan.getSkipResponseValidation());
    	if ( cucumblan.getSkipResponseValidation()) {
    		FileManagement.writeString(skipProp, skip);
    		logger.info("Responses will not be validated.");
    	}
	    
	    /* Execute mvn test. */
	    if (cucumblan.getExecute()) {
	    	long startTime = System.nanoTime();
	    	links.setStartTime(FileManagement.whatTime());
	    	
	    	/* Here comes the actual man Maven test execution. 
	    	 **/
	    	String result =  mvnTest(reportFolder);
	    	
	    	/* Calcuate execution time. */
	    	long endTime = System.nanoTime();
	    	long duration = (endTime - startTime);
	    	Duration d = Duration.ofNanos(duration);
	    	links.setDurationSeconds(Long.valueOf(d.getSeconds()).toString());
	    	links.setEndTime(FileManagement.whatTime());
	      
	    	/* Check test result. */
	    	boolean isSuccess = Boolean.valueOf(result);
	    	links.setSuccess(isSuccess);
	    	if ( isSuccess == false && result.equalsIgnoreCase("false") == false) {
	    		links.setError(result);
	    		links.setTestExecuted(false);
	    		links.setMessage("An internal error occured.");
	    		return links;
	    	}else {
	    		links.setTestExecuted(true);
	    	}
	      
	      
		 } else {
		      /* Message */
		      links.setLinkToTeature("Not generated");
		      links.setLinkToReport("Not generated.");
		      links.setTestExecuted(false);
		      links.setMessage("Property file updated, no test executed (execute=false).");
		      if ( isSaved == false ) {
		    	  links.setError("Error: Could not save file.");
		      }
		      return links;
		 }
	
	    /* Copy generated feature file to the public folder. */
	//    String target = "http://35.193.57.60/"+testId + "/feature/virtualan-contract.0.feature";
	    String target = reportURL + "/feature/virtualan-contract.0.feature";
	    
	    links.setLinkToTeature(target);
	
	    /* Copy the cucumber report folder and send back the link
	     * to the main report html file: report-feature_1959214294.html.
	     * */
	//    String linkReport = "http://35.193.57.60/"+testId + "/cucumber-html-reports/overview-features.html";
	    String linkReport = reportURL + "/cucumber-html-reports/overview-features.html";
	    logger.info("Generated report html file: " + linkReport);
	    links.setLinkToReport(linkReport);
	    
	    links.setLinkToProperties(reportURL + "/" + FileManagement.PROPERTIES);
	    
	    /* Message */
	    links.setMessage("Report created.");
	    links.setError(FileManagement.NOERROR);
    
	}catch(Exception e) {
		links.setError(e.getLocalizedMessage());
		links.setMessage("No reports.");
		links.setSuccess(false);
		
	}

    return links;
  }

	/** The actual main Maven test execution.
	 * @param reportFolder
	 * @return the result as String. "true" if success, "false" if test failed, or Exception message if an error occured.
	 */
	private String mvnTest(String reportFolder){
		int status = 0;
		String result = null;
		boolean isSuccess = false;
		try {
			addToClasspath(reportFolder);
			status = IdaithalamExecutor.validateContract("IDAI server test execution", reportFolder);
			logger.info("Execution status: " + status);
			if (status != 0) {
				isSuccess = false;
			} else {
				isSuccess = true;
			}
			removeFromClasspath(reportFolder);
			result = Boolean.valueOf(isSuccess).toString();
		} catch (MalformedURLException e) {
			result = e.getLocalizedMessage();
			logger.info("Maven execution MalformedURLException raised: " + result);
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			result = e.getLocalizedMessage();
			logger.info("Maven execution NoSuchMethodException raised: " + result);
			e.printStackTrace(); 
		} catch (InvocationTargetException e) {
			result = e.getLocalizedMessage();
			logger.info("Maven execution InvocationTargetException raised: " + result);
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			result = e.getLocalizedMessage();
			logger.info("Maven execution IllegalAccessException raised: " + result);
			e.printStackTrace();
		} catch (Exception e) {
			result = e.getLocalizedMessage();
			logger.info("Maven execution Execption raised: " + result);
			e.printStackTrace();
		}
//	      catch (Exception e) {
//	        logger.info("Execution execption raised: " + e.getLocalizedMessage());
//	      }
      finally {
    	  
      }
	return result;
	}

  /** Warning: This method does not work with Java 9 or greater. 
   *  Only Java 8 is working.
   *  
   *  https://stackoverflow.com/questions/52385610/java-class-cast-exception-spring-boot
   * 
   * 
 * @param s
 * @throws MalformedURLException
 * @throws NoSuchMethodException
 * @throws InvocationTargetException
 * @throws IllegalAccessException
 */
private void addToClasspath(String s)
      throws MalformedURLException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
    File f = new File(s);
    URI u = f.toURI();
    // class jdk.internal.loader.ClassLoaders$AppClassLoader cannot be cast to class java.net.URLClassLoader
    URLClassLoader urlClassLoader = (URLClassLoader) ClassLoader.getSystemClassLoader();
    Class<URLClassLoader> urlClass = URLClassLoader.class;
    Method method = urlClass.getDeclaredMethod("addURL", new Class[]{URL.class});
    method.setAccessible(true);
    method.invoke(urlClassLoader, new Object[]{u.toURL()});
  }
  

  /**
 * @param path
 * @throws Exception
 */
private static void removeFromClasspath(String path) throws Exception {
    URL url = new File(path).toURI().toURL();
    URLClassLoader urlClassLoader = (URLClassLoader)
        ClassLoader.getSystemClassLoader();
    Class<?> urlClass = URLClassLoader.class;
    Field ucpField = urlClass.getDeclaredField("ucp");
    ucpField.setAccessible(true);
//    URLClassPath ucp = (URLClassPath) ucpField.get(urlClassLoader);
//    Class<?> ucpClass = URLClassPath.class;
//    Field urlsField = ucpClass.getDeclaredField("urls");
//    urlsField.setAccessible(true);
//    Stack urls = (Stack) urlsField.get(ucp);
//    urls.remove(url);

//    urlsField = ucpClass.getDeclaredField("path");
//    urlsField.setAccessible(true);
//    List urlss = (List) urlsField.get(ucp);
//    urlss.remove(url);
  }
  
  private String addSlash(String url) {
	  if (url.endsWith("/")) {
		  return url;
	  }else {
		  return url + "/";
	  }
  }
  
  
}
