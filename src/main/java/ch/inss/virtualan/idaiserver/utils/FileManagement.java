package ch.inss.virtualan.idaiserver.utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.stream.Stream;
import javax.validation.Valid;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;


/**
 * The type File management.
 */
public class FileManagement {

  /**
   * The constant lf.
   */
  public static final String lf = System.getProperty("line.separator");
  /**
   * The constant fs.
   */
  public static final String fs = FileSystems.getDefault().getSeparator();
  /**
   * The constant PROPERTIES.
   */
  public static final String PROPERTIES = "cucumblan.properties";
  /**
   * The constant NOERROR.
   */
  public final static String NOERROR = "";
  private static final Logger logger = LoggerFactory.getLogger(FileManagement.class);
  private static final String FEATURE = "virtualan-contract.feature";
  /**
   * Folder to store the Cucumber reports.
   */
  @Value("${ch.inss.idaiserver.reports.folder}")
  private String storagePath;

  /**
   * Save the cucumblan.properties file to the uuid/conf folder.
   *
   * @param confFolder the conf folder
   * @param content    the content
   * @return boolean boolean
   */
  public static boolean saveCucumblan(String confFolder, String content) {
    boolean ok = false;
    try {
      /* Create cucumblan.properties from mustache template. */
//        MustacheFactory mf = new DefaultMustacheFactory();
//        Mustache m = mf.compile("cucumblan.mustache");
//        StringWriter writer = new StringWriter();
//        m.execute(writer, cucumblan).flush();

//        String props = writer.toString();
//        logger.info("props: " + props);

//        writeString(content, path);



      /* Write new properties file. */
      writeString(confFolder + fs + PROPERTIES, content);

      /* Write postman collection. */
      //writeFilestream(path +"/"+cucumblan.getFILE(), cucumblan.getInputStream());
      ok = true;
    } catch (IOException ioe) {
      logger.error("Could not save " + PROPERTIES + " to folder  " + confFolder);
      return false;
    }
    return ok;
    //IOUtils.closeQuietly(initialStream);

  }

  /**
   * Read lines list.
   *
   * @param path the path
   * @return the list
   */
  /* Read existing file if no overwrite is set. */
  public static List<String> readLines(String path) {
    List<String> lines = new ArrayList<String>();
    BufferedReader in;
    try {
      in = new BufferedReader(new FileReader(path));
//        StringBuilder sb = new StringBuilder();

      while (in.readLine() != null) {
        lines.add(in.readLine());
//            sb.append(in.readLine()).append(lf);
      }
      in.close();
    } catch (IOException e) {
      // TODO Auto-generated catch block
      logger.error("Could not read file: " + path);
      return null;
    }
//        String content = sb.toString();

//        props = content + lf + props;
    return lines;
  }

  /**
   * Write filestream.
   *
   * @param filename the filename
   * @param in       the in
   * @throws IOException the io exception
   */
  /* Write files like postman collection. */
  public static void writeFilestream(String filename, InputStream in) throws IOException {
    File targetFile = new File(filename);
    InputStream initialStream = in;
    java.nio.file.Files.copy(
        initialStream,
        targetFile.toPath(),
        StandardCopyOption.REPLACE_EXISTING);

    initialStream.close();
  }

  /**
   * Add a line to the cucumblan.properties file.  @param path the path
   *
   * @param path     the path
   * @param value    the value
   * @param resource the resource
   * @return the string
   */
  public static String addLine(String path, String value, String resource) {
    String content;
    String key = "service.api" + "." + resource;
    Properties prop;
    try {
      prop = readCucumblanPropertiesFile(path);
      prop.put(key, value);
      writeProperty(prop, "adding line for " + resource);
//            content = line + lf + readToString(PROPERTIES);
//            writeString(content, PROPERTIES);
      content = prop.toString();
    } catch (IOException e) {
      logger.error("Could not add line: " + key + "=" + value, e);
      content = "Could not add line: " + key + "=" + value;
    }
    return content;
  }

  private static void writeProperty(Properties prop, String comment) throws IOException {
    FileOutputStream outputStrem = new FileOutputStream(PROPERTIES);
    //Storing the properties file
    prop.store(outputStrem, "Property file updated: "
        + comment); // at " + new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss z").format(new Date(System.currentTimeMillis())),"UTF-8");
    IOUtils.closeQuietly(outputStrem);
  }

  /**
   * Gets featurefile.
   *
   * @return the featurefile
   */
  /* Read the feature file. */
  public static String getFeaturefile() {
    try {
      return readToString(FEATURE);
    } catch (FileNotFoundException ffe) {
      logger.error("Could not find file: " + FEATURE, ffe);
      return "Error finding " + FEATURE;
    } catch (IOException ioe) {
      logger.error("Could not read file: " + FEATURE, ioe);
      return "Error reading " + FEATURE;
    }

  }

  /**
   * Read the Property file.  @return the propertyfile
   *
   * @return the propertyfile
   */
  public static String getPropertyfile() {
    try {
      return readToString(PROPERTIES);
    } catch (FileNotFoundException ffe) {
      logger.error("Could not find file: " + PROPERTIES, ffe);
      return "Error finding " + PROPERTIES;
    } catch (IOException ioe) {
      logger.error("Could not read file: " + PROPERTIES, ioe);
      return "Error reading " + PROPERTIES;
    }

  }


  /**
   * Read cucumblan properties file properties.
   *
   * @param path the path
   * @return properties properties
   */
  public static Properties readCucumblanPropertiesFile(String path) {
    FileInputStream fis = null;
    Properties prop = null;
    try {
      fis = new FileInputStream(path + fs + PROPERTIES);
      prop = new Properties();
      prop.load(fis);
    } catch (FileNotFoundException fnfe) {
      fnfe.printStackTrace();
    } catch (IOException ioe) {
      ioe.printStackTrace();
    } finally {
      try {
        fis.close();
      } catch (Exception e) {
      }
    }
    return prop;
  }


  /**
   * Write string.
   *
   * @param filename the filename
   * @param content  the content
   * @throws IOException the io exception
   */
  public static void writeString(String filename, String content) throws IOException {
    /* Put String into writer. */
    BufferedWriter bwriter = new BufferedWriter(new FileWriter(filename));
    bwriter.write(content);
    bwriter.close();
  }


  /**
   * Read any file and give back the String content.  @param file the file
   *
   * @param file the file
   * @return the string
   * @throws FileNotFoundException the file not found exception
   * @throws IOException           the io exception
   */
  public static String readToString(String file) throws FileNotFoundException, IOException {
    logger.debug("File path: " + file);
    BufferedReader br = new BufferedReader(new FileReader(file));
    StringBuilder sb = new StringBuilder();
    String line = null;
    while ((line = br.readLine()) != null) {
      logger.info("LINE: " + line);
      sb.append(line).append(lf);
    }
    br.close();
    return sb.toString();
  }

  /**
   * Copy files string.
   *
   * @param source       the source
   * @param targetFolder the target folder
   * @return string string
   */
  public static String copyFiles(String source, String targetFolder) {
    Path sourcePath = Paths.get(source);
    String filename = sourcePath.getFileName().toString();
    Path newPath = Paths.get(targetFolder).resolve(filename);

    try {
      File file = new File(targetFolder);
      if (file.exists() == false) {
        file.mkdir();
      } else {
        logger.error("Folder cannot be created because it exists: " + targetFolder);
        return "Error";
      }
      Files.copy(sourcePath, newPath, StandardCopyOption.REPLACE_EXISTING);
      logger.info("Copied " + source + " to " + newPath.toAbsolutePath());
    } catch (IOException e) {
      logger.error("Cannot fopy feature file.", e);
    }

    return "ok";
  }

  /**
   * Copy folder.
   *
   * @param sourceFolder the source folder
   * @param targetFolder the target folder
   */
  public static void copyFolder(String sourceFolder, String targetFolder) {
//        String source = "C:/your/source";
    File srcDir = new File(sourceFolder);

//        String destination = "C:/your/destination";
    File destDir = new File(targetFolder);

    try {
      FileUtils.copyDirectory(srcDir, destDir);
    } catch (IOException e) {
      logger.error("Cannot copy report folder", e);
    }
  }

  /**
   * Gets reportfile.
   *
   * @param target the target
   * @return reportfile reportfile
   */
  public static String getReportfile(String target) {
    Path testPath = Paths.get(target);
    String result = "notfound";
    final String report = "report-feature_";
    Stream<Path> stream = null;
    try {
      //finding files containing 'items' in name
      stream =
          Files.find(testPath, 1,
              (path, basicFileAttributes) -> {
                File file = path.toFile();
                return !file.isDirectory() &&
                    file.getName().startsWith(report);
              });
      Object[] files = stream.toArray();
      if (files == null || files.length == 0) {
        logger.error("Did not find a file starting with " + report);

      } else {
        Path p = (Path) files[0];
        result = p.getFileName().toString();
      }
    } catch (IOException ioe) {
      logger.error("Could not find report html file.", ioe);
    } finally {
      stream.close();
    }
    return result;
  }

  /**
   * Write down the Postmancollection to the filesystem and add it to the list in
   * cucumblan.properties.
   *
   * @param path       the path
   * @param fileStream the file stream
   * @return boolean boolean
   */
  public static boolean addCollection(String path, @Valid MultipartFile fileStream) {
    try {
      writeFilestream(fileStream.getName(), fileStream.getInputStream());
      Properties prop = readCucumblanPropertiesFile(path);
      String value = prop.getProperty("virtualan.data.load");
      String file = fileStream.getName();
      if (file == null || file.equals("")) {
        return false;
      }
      value += ";" + file;
      prop.put("virtualan.data.load", value);
      writeProperty(prop, "adding collection: " + file);

    } catch (IOException e) {
      logger.error("Could not write file " + fileStream.getName());
      return false;
    }
    return true;
  }

  /**
   * Remove server boolean.
   *
   * @param path     the path
   * @param resource the resource
   * @return boolean boolean
   */
  public static boolean removeServer(String path, String resource) {
    String key = null;

    try {
      key = "service.api" + "." + resource;
      logger.debug("Removing " + key);
      Properties prop;
      prop = readCucumblanPropertiesFile(path);
      prop.remove(key);
      writeProperty(prop, "removing " + key);
    } catch (IOException ioe) {
      logger.error("Could not remove: " + key, ioe);
      return false;
    }
    return true;
  }

  /**
   * Give back a formated Date Time String: yyyy-MM-dd 'at' HH:mm:ss z
   *
   * @return the string
   */
  public static String whatTime() {
    return new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss z")
        .format(new Date(System.currentTimeMillis()));
  }

  /**
   * List folders list.
   *
   * @param path the path
   * @return the list
   */
  public static List<String> listFolders(String path) {
    List<String> folders = new ArrayList<String>();
    File main_dir = new File(path);
    if (main_dir.exists() && main_dir.isDirectory()) {
      File arr[] = main_dir.listFiles();
      for (File file : arr) {
        if (file.isDirectory()) {
          folders.add(file.getName());
        }
      }

    } else {
      logger.error("Folder cannot be read: " + path);
      return new ArrayList<String>();
    }

    return folders;
  }

  /**
   * Remove folder boolean.
   *
   * @param path the path
   * @return the boolean
   */
  public static boolean removeFolder(String path) {

    File folder = new File(path);
    return FileSystemUtils.deleteRecursively(folder);
  }

}
