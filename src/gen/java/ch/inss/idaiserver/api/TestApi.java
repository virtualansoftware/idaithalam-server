/**
 * NOTE: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech) (5.0.0).
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */
package ch.inss.idaiserver.api;

import ch.inss.idaiserver.model.Report;
import ch.inss.idaiserver.model.Test;
import java.util.UUID;
import io.swagger.annotations.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.multipart.MultipartFile;
import springfox.documentation.annotations.ApiIgnore;

import javax.validation.Valid;
import javax.validation.constraints.*;
import java.util.List;
import java.util.Map;
import java.util.Optional;
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2021-01-23T19:37:28.644598+01:00[Europe/Zurich]")
@Validated
@Api(value = "test", description = "the test API")
public interface TestApi {

    default Optional<NativeWebRequest> getRequest() {
        return Optional.empty();
    }

    /**
     * PUT /test/{testid}/conf : Update the configuration.
     * Update the configuration.
     *
     * @param testid testid for that serverurl (required)
     * @param serverurl Serverurl to add to the test configuration. (required)
     * @return Gives back the entire test configuration as a cucumblan.properties file. (status code 201)
     *         or Bad request. (status code 400)
     *         or unexpected error (status code 200)
     */
    @ApiOperation(value = "Update the configuration.", nickname = "addserver", notes = "Update the configuration.", response = String.class, tags={ "Configuration", })
    @ApiResponses(value = { 
        @ApiResponse(code = 201, message = "Gives back the entire test configuration as a cucumblan.properties file.", response = String.class),
        @ApiResponse(code = 400, message = "Bad request."),
        @ApiResponse(code = 200, message = "unexpected error") })
    @PutMapping(
        value = "/test/{testid}/conf",
        produces = { "text/plain" }
    )
    default ResponseEntity<String> addserver(@ApiParam(value = "testid for that serverurl",required=true) @PathVariable("testid") String testid,@NotNull @ApiParam(value = "Serverurl to add to the test configuration.", required = true) @Valid @RequestParam(value = "serverurl", required = true) String serverurl) {
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }


    /**
     * GET /test/{testid}/feature : Get the content of the generated Gherkin feature file.
     * Get the content of the generated Gherkin feature file.
     *
     * @param testid testid for that Gherkin file. (required)
     * @return Content of the generated Gherkin feature file. (status code 200)
     *         or Internal server error. (status code 500)
     *         or unexpected error (status code 200)
     */
    @ApiOperation(value = "Get the content of the generated Gherkin feature file.", nickname = "getgherkin", notes = "Get the content of the generated Gherkin feature file.", response = String.class, tags={ "Results", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Content of the generated Gherkin feature file.", response = String.class),
        @ApiResponse(code = 500, message = "Internal server error."),
        @ApiResponse(code = 200, message = "unexpected error") })
    @GetMapping(
        value = "/test/{testid}/feature",
        produces = { "text/plain" }
    )
    default ResponseEntity<String> getgherkin(@ApiParam(value = "testid for that Gherkin file.",required=true) @PathVariable("testid") String testid) {
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }


    /**
     * GET /test/{testid}/conf : Get the test configuration content from the cucumblan.properties.
     * Get the content of the configured cucumblan.properties.
     *
     * @param testid testid for that test (required)
     * @return Configuration of cucumblan.properties file. (status code 200)
     *         or Test not found. (status code 404)
     *         or Internal server error. (status code 500)
     *         or unexpected error (status code 200)
     */
    @ApiOperation(value = "Get the test configuration content from the cucumblan.properties.", nickname = "getproperty", notes = "Get the content of the configured cucumblan.properties.", response = Test.class, tags={ "Configuration", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Configuration of cucumblan.properties file.", response = Test.class),
        @ApiResponse(code = 404, message = "Test not found."),
        @ApiResponse(code = 500, message = "Internal server error."),
        @ApiResponse(code = 200, message = "unexpected error") })
    @GetMapping(
        value = "/test/{testid}/conf",
        produces = { "application/json" }
    )
    default ResponseEntity<Test> getproperty(@ApiParam(value = "testid for that test",required=true) @PathVariable("testid") String testid) {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType: MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "{ \"linktofeature\" : \"http://localhost:8080/result.feature\", \"linktoreport\" : \"http://localhost:8080/result.html\", \"message\" : \"Test created.\", \"error\" : \"no error occured.\" }";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
            }
        });
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }


    /**
     * GET /test/{testid} : Get the test result links as in PUT.
     * Get the test result links as in PUT.
     *
     * @param testid testid for that test (required)
     * @return Configuration of cucumblan.properties file. (status code 200)
     *         or Test not found. (status code 404)
     *         or Internal server error. (status code 500)
     *         or unexpected error (status code 200)
     */
    @ApiOperation(value = "Get the test result links as in PUT.", nickname = "getreport", notes = "Get the test result links as in PUT.", response = Test.class, tags={ "API test execution", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Configuration of cucumblan.properties file.", response = Test.class),
        @ApiResponse(code = 404, message = "Test not found."),
        @ApiResponse(code = 500, message = "Internal server error."),
        @ApiResponse(code = 200, message = "unexpected error") })
    @GetMapping(
        value = "/test/{testid}",
        produces = { "application/json" }
    )
    default ResponseEntity<Test> getreport(@ApiParam(value = "testid for that test",required=true) @PathVariable("testid") String testid) {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType: MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "{ \"linktofeature\" : \"http://localhost:8080/result.feature\", \"linktoreport\" : \"http://localhost:8080/result.html\", \"message\" : \"Test created.\", \"error\" : \"no error occured.\" }";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
            }
        });
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }


    /**
     * DELETE /test/{testid}/conf : Remove the entire test configuration.
     * Remove the test.
     *
     * @param testid testid for that test (required)
     * @return Test deleted. (status code 204)
     */
    @ApiOperation(value = "Remove the entire test configuration.", nickname = "removetest", notes = "Remove the test.", tags={ "Configuration", })
    @ApiResponses(value = { 
        @ApiResponse(code = 204, message = "Test deleted.") })
    @DeleteMapping(
        value = "/test/{testid}/conf"
    )
    default ResponseEntity<Void> removetest(@ApiParam(value = "testid for that test",required=true) @PathVariable("testid") String testid) {
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }


    /**
     * GET /test/{testid}/report : Get the report for this uuid.
     * Get the report of a previously executed test.
     *
     * @param testid testid for that test (required)
     * @return Configuration of cucumblan.properties file. (status code 200)
     *         or Internal server error. (status code 500)
     *         or unexpected error (status code 200)
     */
    @ApiOperation(value = "Get the report for this uuid.", nickname = "report", notes = "Get the report of a previously executed test.", response = Report.class, tags={ "Results", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Configuration of cucumblan.properties file.", response = Report.class),
        @ApiResponse(code = 500, message = "Internal server error."),
        @ApiResponse(code = 200, message = "unexpected error") })
    @GetMapping(
        value = "/test/{testid}/report",
        produces = { "application/json" }
    )
    default ResponseEntity<Report> report(@ApiParam(value = "testid for that test",required=true) @PathVariable("testid") String testid) {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType: MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "{ \"linktofeature\" : \"http://localhost:8080/result.feature\", \"linktoreport\" : \"http://localhost:8080/result.html\", \"message\" : \"Test created.\", \"error\" : \"no error occured.\" }";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
            }
        });
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }


    /**
     * PUT /test/{testid} : Run test.
     * Run test
     *
     * @param testid testid for that test (required)
     * @return Gives back the entire cucumblan.properties file and generated testid. (status code 201)
     */
    @ApiOperation(value = "Run test.", nickname = "runtest", notes = "Run test", response = Report.class, tags={ "API test execution", })
    @ApiResponses(value = { 
        @ApiResponse(code = 201, message = "Gives back the entire cucumblan.properties file and generated testid.", response = Report.class) })
    @PutMapping(
        value = "/test/{testid}",
        produces = { "application/json" }
    )
    default ResponseEntity<Report> runtest(@ApiParam(value = "testid for that test",required=true) @PathVariable("testid") UUID testid) {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType: MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "{ \"linktofeature\" : \"http://localhost:8080/result.feature\", \"linktoreport\" : \"http://localhost:8080/result.html\", \"message\" : \"Test created.\", \"error\" : \"no error occured.\" }";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
            }
        });
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }


    /**
     * POST /test : Create and run the test with the uploaded Postman collection.
     * Create a new test and run against an API. Generates the Cucumber report and a Gherkin file.
     *
     * @param filestream  (required)
     * @param serverurl The server url to be tested. (required)
     * @param dataload filename (optional, default to &quot;idaithalan.postman_collection.json&quot;)
     * @param execute Execute test immediately. If false, only the property file will be updated (append). (optional, default to &quot;true&quot;)
     * @param skipResponseValidation Skip the respone validation in tests. (optional, default to &quot;true&quot;)
     * @param datatype Type of data is POSTMAN, VIRTUALAN OR EXCEL. (optional, default to POSTMAN)
     * @return Test created. (status code 201)
     *         or Bad request. (status code 400)
     *         or I am a teapot. (status code 418)
     *         or unexpected error (status code 200)
     */
    @ApiOperation(value = "Create and run the test with the uploaded Postman collection.", nickname = "testrun", notes = "Create a new test and run against an API. Generates the Cucumber report and a Gherkin file.", response = Report.class, tags={ "API test execution", })
    @ApiResponses(value = { 
        @ApiResponse(code = 201, message = "Test created.", response = Report.class),
        @ApiResponse(code = 400, message = "Bad request."),
        @ApiResponse(code = 418, message = "I am a teapot."),
        @ApiResponse(code = 200, message = "unexpected error") })
    @PostMapping(
        value = "/test",
        produces = { "application/json" },
        consumes = { "multipart/form-data" }
    )
    default ResponseEntity<Report> testrun(@ApiParam(value = "") @Valid @RequestPart(value = "filestream", required = true) MultipartFile filestream,@ApiParam(value = "The server url to be tested.", required=true, defaultValue="http://localhost:8080") @Valid @RequestPart(value = "serverurl", required = true)  String serverurl,@ApiParam(value = "filename", defaultValue="idaithalan.postman_collection.json") @Valid @RequestPart(value = "dataload", required = false)  String dataload,@ApiParam(value = "Execute test immediately. If false, only the property file will be updated (append).", defaultValue="true") @Valid @RequestPart(value = "execute", required = false)  String execute,@ApiParam(value = "Skip the respone validation in tests.", defaultValue="true") @Valid @RequestPart(value = "skipResponseValidation", required = false)  String skipResponseValidation,@ApiParam(value = "Type of data is POSTMAN, VIRTUALAN OR EXCEL.", allowableValues="POSTMAN, VIRTUALAN, EXCEL", defaultValue="POSTMAN") @Valid @RequestPart(value = "datatype", required = false)  String datatype) {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType: MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "{ \"linktofeature\" : \"http://localhost:8080/result.feature\", \"linktoreport\" : \"http://localhost:8080/result.html\", \"message\" : \"Test created.\", \"error\" : \"no error occured.\" }";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
            }
        });
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }

}
