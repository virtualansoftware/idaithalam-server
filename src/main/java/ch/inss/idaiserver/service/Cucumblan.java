package ch.inss.idaiserver.service;

import java.io.InputStream;
import java.util.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import ch.inss.idaiserver.model.Report;
import ch.inss.idaiserver.model.Serviceapi;
import ch.inss.idaiserver.model.TestDao;
import ch.inss.idaiserver.utils.FileManagement;

@Component
public class Cucumblan {
    
    private String TYPE;
    private InputStream inputStream;
    private String folder;
    private String uploadFilename;
    private Integer sessionNr;
    private Boolean execute;
    private Boolean skipResponseValidation;
    private UUID uuid;
    
    /** Store keys as key=service.api. 
     * Add resourcees with key=service.api.nextresource. */
    private HashMap<String, String> URL;
    /* List of all postman collection files. */
    private LinkedHashSet<String> postmanCollections;  //TODO: check if always only one.
    
    private static final String dataload = "virtualan.data.load";
    private static final String datatype = "virtualan.data.type";
    private static final String serviceapi = "service.api";

    public Cucumblan() {

        
    }
    
    private static final Logger logger = LoggerFactory.getLogger(Cucumblan.class);
    
    public void init() {
        this.uuid = UUID.randomUUID();
        this.iniAll();
    }
    public void init(UUID uuid) {
    	this.uuid = uuid;
    	this.iniAll();
    }
    
    /**
     * uuid must be set already.
     */
    private void iniAll() {
    	if ( this.uuid == null ) {
    		logger.error("Wrong initialization. UUID must be set.");
    		return;
    	}
    	this.execute = new Boolean(true);
//        this.overwrite = new Boolean(true);
        this.folder = this.uuid.toString();
        this.postmanCollections = new LinkedHashSet<String>();
        this.URL = new HashMap<String, String>();
        this.sessionNr = new Integer(0);
    }
    
    
	public String getFolder() {
        return folder;
    }

    public String getUploadFilename() {
		return uploadFilename;
	}

	public void setUploadFilename(String uploadFilename) {
		this.uploadFilename = uploadFilename;
	}

	public UUID getUuid() {
        return uuid;
    }

    public Integer getSessionNr() {
		return sessionNr;
	}




	public void setSessionNr(Integer sessionNr) {
		this.sessionNr = sessionNr;
	}




	public InputStream getInputStream() {
        return inputStream;
    }

    public void setInputStream(InputStream inputStream) {
        this.inputStream = inputStream;
    }


    public String getTYPE() {
        return TYPE;
    }
    public void setTYPE(String tYPE) {
        TYPE = tYPE;
    }

    public Boolean getExecute() {
        return execute;
    }

    public void setExecute(Boolean execute) {
        this.execute = execute;
    }

    
    public Boolean getSkipResponseValidation() {
		return skipResponseValidation;
	}


	public void setSkipResponseValidation(Boolean skipResponseValidation) {
		this.skipResponseValidation = skipResponseValidation;
	}




	/** Input is only the resource. service.api is added. */
    public void addURL(String resource, String value) {
        if ( resource == null || "".equals(resource)) {
            resource = "service.api"; 
        }else {
            resource = "service.api." + resource ;
        }
        this.URL.put(resource, value);
                
    }
    
    /** Add a postman collection file name for the property virtualan.data.load. */
    public void addFILE(String dataload) {
        if (dataload == null) return;
        this.postmanCollections.add(dataload);
    }
    
    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer("#Generated " + FileManagement.whatTime());
//        final String dataload = "virtualan.data.load";
//        final String datatype = "virtualan.data.type";
        sb.append(FileManagement.lf);
        for ( String key : this.URL.keySet()) {
            sb.append(key).append(" = ").append(this.URL.get(key)).append(FileManagement.lf);    
        }
        sb.append(dataload).append(" = ");
        for ( String filename : this.postmanCollections) {
            sb.append(filename).append(";");
        }
        sb.append(FileManagement.lf);
        sb.append(datatype).append(" = ").append(this.TYPE).append(FileManagement.lf);
        
        return sb.toString();
    }
    
    /**
     * @param prpüertyCpmtemt
     * @return
     * 
     * Read the property file.
     * service.api=http://localhost:8080
     * virtualan.data.load=idaiserver.postman_collection.json
     * virtualan.data.type=POSTMAN
     */
    public void fromProperty() {
    	
    	
//        StringBuffer sb = new StringBuffer("#Generated " + FileManagement.whatTime());
        
        Properties p = FileManagement.readCucumblanPropertiesFile();
        
        /* POSTMAN, VIRTUALAN or EXCEL. */
        this.TYPE = p.getProperty(datatype);
        
        /* Semicolon separated list of postman collections. */
        String post = p.getProperty(dataload);
        String[] posts = post.split(";");
        this.postmanCollections = new LinkedHashSet(Arrays.asList(posts));
        
        /* List of URLs with resources */
        for ( Object key : p.keySet()) {
        	if ( key.toString().startsWith(serviceapi)) {
        		this.URL.put(key.toString(), p.get(key).toString());
        	}
        }
        
    }
    
    /** Get a single file. */
    public String getOneFILE() {
        if ( this.postmanCollections.size() > 1 ) {
            logger.warn("There are more files in this configuration. This method should be only called for a single file configuration like instan execution for a test. ");
        }
        if ( this.postmanCollections == null || this.postmanCollections.isEmpty()) {
            logger.error("There was no postman collection file added.");
            return null;
        }
        Iterator<String> iterator = this.postmanCollections.iterator();
        if(iterator.hasNext()){
            return iterator.next(); 
        }else{
            return "";
        }
    }

    public Report reportFactory() {
    	Report reportLinks = new Report();
	   reportLinks.setError(FileManagement.NOERROR);
       reportLinks.setCreationTime(FileManagement.whatTime());
       reportLinks.setTestid( this.uuid );
       reportLinks.setSessionNr(this.sessionNr);
       return reportLinks;
    }


    public HashMap<String, String> getURL() {
        return URL;
    }

    public LinkedHashSet<String> getFILE() {
        return postmanCollections;
    }




	public TestDao testFactory() {
		TestDao dao = new TestDao();
		dao.setTestid(this.uuid);
		dao.setCreationtime(FileManagement.whatTime());
		dao.setError(FileManagement.NOERROR);
		dao.setLoad(this.getFILE());
		dao.setServiceapi(this.generateServiceapi(this.getURL()));
		
		return dao;
	}
	
	private List<Serviceapi> generateServiceapi(HashMap<String, String> map){
		List<Serviceapi> list = new ArrayList<>();
		for ( String key : map.keySet()) {
			String value = map.get(key);
			Serviceapi api = new Serviceapi();
			api.setResource(key);
			api.setUrl(value);
			list.add(api);
		}
		return list;
	}


    
}
