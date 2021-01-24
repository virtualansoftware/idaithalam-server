/**
 * NOTE: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech) (5.0.0).
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */
package ch.inss.idaiserver.api;

import ch.inss.idaiserver.model.Conf;
import ch.inss.idaiserver.model.Report;
import ch.inss.idaiserver.model.Testidlist;
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
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2021-01-24T13:26:32.313525+01:00[Europe/Zurich]")
@Validated
@Api(value = "test", description = "the test API")
public interface TestApi {

    default Optional<NativeWebRequest> getRequest() {
        return Optional.empty();
    }

    /**
     * GET /test/{testId}/conf : Get the test configuration content from the cucumblan.properties.
     * Get the content of the configured cucumblan.properties.
     *
     * @param testId  (required)
     * @return Configuration of cucumblan.properties file. (status code 200)
     *         or Test not found. (status code 404)
     *         or Internal server error. (status code 500)
     *         or unexpected error (status code 200)
     */
    @ApiOperation(value = "Get the test configuration content from the cucumblan.properties.", nickname = "getConfProperty", notes = "Get the content of the configured cucumblan.properties.", response = String.class, tags={ "Configuration", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Configuration of cucumblan.properties file.", response = String.class),
        @ApiResponse(code = 404, message = "Test not found."),
        @ApiResponse(code = 500, message = "Internal server error."),
        @ApiResponse(code = 200, message = "unexpected error") })
    @GetMapping(
        value = "/test/{testId}/conf",
        produces = { "text/plain" }
    )
    default ResponseEntity<String> getConfProperty(@ApiParam(value = "",required=true) @PathVariable("testId") String testId) {
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }


    /**
     * GET /test/{testId} : Get the test result links.
     * Get the test result links.
     *
     * @param testId testId for that test (required)
     * @return Configuration of cucumblan.properties file. (status code 200)
     *         or Test not found. (status code 404)
     *         or Internal server error. (status code 500)
     *         or unexpected error (status code 200)
     */
    @ApiOperation(value = "Get the test result links.", nickname = "getReport", notes = "Get the test result links.", response = Report.class, tags={ "Results", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Configuration of cucumblan.properties file.", response = Report.class),
        @ApiResponse(code = 404, message = "Test not found."),
        @ApiResponse(code = 500, message = "Internal server error."),
        @ApiResponse(code = 200, message = "unexpected error") })
    @GetMapping(
        value = "/test/{testId}",
        produces = { "application/json" }
    )
    default ResponseEntity<Report> getReport(@ApiParam(value = "testId for that test",required=true) @PathVariable("testId") String testId) {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType: MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "{ \"sessionNr\" : 0, \"linkToProperties\" : \"linkToProperties\", \"creationTime\" : \"creationTime\", \"durationSeconds\" : 6, \"linkToReport\" : \"linkToReport\", \"linkToSessions\" : \"linkToSessions\", \"message\" : \"message\", \"error\" : \"error\", \"linkToFeature\" : \"linkToFeature\", \"skipResponseValidation\" : true, \"success\" : true, \"testExecuted\" : true, \"testId\" : \"046b6c7f-0b8a-43b9-b35d-6489e6daee91\", \"startTime\" : \"startTime\", \"endTime\" : \"endTime\" }";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
            }
        });
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }


    /**
     * GET /test/{testId}/feature : Get the content of the generated Gherkin feature file.
     * Get the content of the generated Gherkin feature file.
     *
     * @param testId testid for that Gherkin file. (required)
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
        value = "/test/{testId}/feature",
        produces = { "text/plain" }
    )
    default ResponseEntity<String> getgherkin(@ApiParam(value = "testid for that Gherkin file.",required=true) @PathVariable("testId") String testId) {
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }


    /**
     * GET /test : Get all test IDs as a list.
     * Get all test IDs as a list.
     *
     * @return List of all test IDs. (status code 200)
     *         or No test found. (status code 404)
     *         or Internal server error. (status code 500)
     *         or unexpected error (status code 200)
     */
    @ApiOperation(value = "Get all test IDs as a list.", nickname = "listTest", notes = "Get all test IDs as a list.", response = Testidlist.class, tags={ "Results", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "List of all test IDs.", response = Testidlist.class),
        @ApiResponse(code = 404, message = "No test found."),
        @ApiResponse(code = 500, message = "Internal server error."),
        @ApiResponse(code = 200, message = "unexpected error") })
    @GetMapping(
        value = "/test",
        produces = { "application/json" }
    )
    default ResponseEntity<Testidlist> listTest() {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType: MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "{ \"serverUrl\" : [ \"serverUrl\", \"serverUrl\" ], \"idList\" : [ \"idList\", \"idList\" ] }";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
            }
        });
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }


    /**
     * DELETE /test/{testId}/conf : Remove the single config configuration.
     * Remove the test.
     *
     * @param configKey remove given key configuration. (required)
     * @param testId  (required)
     * @return delete key Configuration of cucumblan.properties file. (status code 200)
     */
    @ApiOperation(value = "Remove the single config configuration.", nickname = "removeConf", notes = "Remove the test.", response = String.class, tags={ "Configuration", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "delete key Configuration of cucumblan.properties file.", response = String.class) })
    @DeleteMapping(
        value = "/test/{testId}/conf",
        produces = { "text/plain" }
    )
    default ResponseEntity<String> removeConf(@NotNull @ApiParam(value = "remove given key configuration.", required = true) @Valid @RequestParam(value = "configKey", required = true) String configKey,@ApiParam(value = "",required=true) @PathVariable("testId") String testId) {
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }


    /**
     * GET /test/{testId}/report : Get the report for this uuid.
     * Get the report of a previously executed test.
     *
     * @param testId testid for that test (required)
     * @return Configuration of cucumblan.properties file. (status code 200)
     *         or Internal server error. (status code 500)
     *         or unexpected error (status code 200)
     */
    @ApiOperation(value = "Get the report for this uuid.", nickname = "report", notes = "Get the report of a previously executed test.", response = Report.class, responseContainer = "List", tags={ "Results", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Configuration of cucumblan.properties file.", response = Report.class, responseContainer = "List"),
        @ApiResponse(code = 500, message = "Internal server error."),
        @ApiResponse(code = 200, message = "unexpected error") })
    @GetMapping(
        value = "/test/{testId}/report",
        produces = { "application/json" }
    )
    default ResponseEntity<List<Report>> report(@ApiParam(value = "testid for that test",required=true) @PathVariable("testId") String testId) {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType: MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "{ \"sessionNr\" : 0, \"linkToProperties\" : \"linkToProperties\", \"creationTime\" : \"creationTime\", \"durationSeconds\" : 6, \"linkToReport\" : \"linkToReport\", \"linkToSessions\" : \"linkToSessions\", \"message\" : \"message\", \"error\" : \"error\", \"linkToFeature\" : \"linkToFeature\", \"skipResponseValidation\" : true, \"success\" : true, \"testExecuted\" : true, \"testId\" : \"046b6c7f-0b8a-43b9-b35d-6489e6daee91\", \"startTime\" : \"startTime\", \"endTime\" : \"endTime\" }";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
            }
        });
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }


    /**
     * PUT /test/{testId} : Run test.
     * Run test
     *
     * @param testId testid for that test (required)
     * @return Gives back the entire cucumblan.properties file and generated testid. (status code 201)
     */
    @ApiOperation(value = "Run test.", nickname = "runTest", notes = "Run test", response = Report.class, tags={ "API test execution", })
    @ApiResponses(value = { 
        @ApiResponse(code = 201, message = "Gives back the entire cucumblan.properties file and generated testid.", response = Report.class) })
    @PutMapping(
        value = "/test/{testId}",
        produces = { "application/json" }
    )
    default ResponseEntity<Report> runTest(@ApiParam(value = "testid for that test",required=true) @PathVariable("testId") UUID testId) {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType: MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "{ \"sessionNr\" : 0, \"linkToProperties\" : \"linkToProperties\", \"creationTime\" : \"creationTime\", \"durationSeconds\" : 6, \"linkToReport\" : \"linkToReport\", \"linkToSessions\" : \"linkToSessions\", \"message\" : \"message\", \"error\" : \"error\", \"linkToFeature\" : \"linkToFeature\", \"skipResponseValidation\" : true, \"success\" : true, \"testExecuted\" : true, \"testId\" : \"046b6c7f-0b8a-43b9-b35d-6489e6daee91\", \"startTime\" : \"startTime\", \"endTime\" : \"endTime\" }";
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
     * @param execute Execute test immediately. If false, only the property file will be updated (append). (optional, default to &quot;true&quot;)
     * @param skipResponseValidation Skip the respone validation in tests. (optional, default to &quot;true&quot;)
     * @return Test created. (status code 201)
     *         or Bad request. (status code 400)
     *         or I am a teapot. (status code 418)
     *         or unexpected error (status code 200)
     */
    @ApiOperation(value = "Create and run the test with the uploaded Postman collection.", nickname = "testRun", notes = "Create a new test and run against an API. Generates the Cucumber report and a Gherkin file.", response = Report.class, tags={ "API test execution", })
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
    default ResponseEntity<Report> testRun(@ApiParam(value = "") @Valid @RequestPart(value = "filestream", required = true) MultipartFile filestream,@ApiParam(value = "The server url to be tested.", required=true) @Valid @RequestPart(value = "serverurl", required = true)  String serverurl,@ApiParam(value = "Execute test immediately. If false, only the property file will be updated (append).", defaultValue="true") @Valid @RequestPart(value = "execute", required = false)  String execute,@ApiParam(value = "Skip the respone validation in tests.", defaultValue="true") @Valid @RequestPart(value = "skipResponseValidation", required = false)  String skipResponseValidation) {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType: MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "{ \"sessionNr\" : 0, \"linkToProperties\" : \"linkToProperties\", \"creationTime\" : \"creationTime\", \"durationSeconds\" : 6, \"linkToReport\" : \"linkToReport\", \"linkToSessions\" : \"linkToSessions\", \"message\" : \"message\", \"error\" : \"error\", \"linkToFeature\" : \"linkToFeature\", \"skipResponseValidation\" : true, \"success\" : true, \"testExecuted\" : true, \"testId\" : \"046b6c7f-0b8a-43b9-b35d-6489e6daee91\", \"startTime\" : \"startTime\", \"endTime\" : \"endTime\" }";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
            }
        });
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }


    /**
     * PUT /test/{testId}/conf : Update the configuration.
     * Update the configuration.
     *
     * @param testId  (required)
     * @param conf  (required)
     * @return Gives back the entire test configuration as a cucumblan.properties file. (status code 200)
     *         or Bad request. (status code 400)
     *         or unexpected error (status code 200)
     */
    @ApiOperation(value = "Update the configuration.", nickname = "updateConf", notes = "Update the configuration.", response = String.class, tags={ "Configuration", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Gives back the entire test configuration as a cucumblan.properties file.", response = String.class),
        @ApiResponse(code = 400, message = "Bad request."),
        @ApiResponse(code = 200, message = "unexpected error") })
    @PutMapping(
        value = "/test/{testId}/conf",
        produces = { "text/plain" },
        consumes = { "application/json" }
    )
    default ResponseEntity<String> updateConf(@ApiParam(value = "",required=true) @PathVariable("testId") String testId,@ApiParam(value = "" ,required=true )  @Valid @RequestBody Conf conf) {
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }

}
