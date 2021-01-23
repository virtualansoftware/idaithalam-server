/**
 * NOTE: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech) (5.0.0).
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */
package ch.inss.idaiserver.api;

import ch.inss.idaiserver.model.HealthResponse;
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
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2021-01-23T13:14:27.767932+01:00[Europe/Zurich]")
@Validated
@Api(value = "health", description = "the health API")
public interface HealthApi {

    default Optional<NativeWebRequest> getRequest() {
        return Optional.empty();
    }

    /**
     * GET /health : The health check of this service.
     * The health check of this service.
     *
     * @return OK (status code 200)
     *         or Server not available. (status code 503)
     */
    @ApiOperation(value = "The health check of this service.", nickname = "health", notes = "The health check of this service.", response = HealthResponse.class, tags={ "health", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "OK", response = HealthResponse.class),
        @ApiResponse(code = 503, message = "Server not available.") })
    @GetMapping(
        value = "/health",
        produces = { "application/json" }
    )
    default ResponseEntity<HealthResponse> health() {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType: MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "{ \"status\" : \"UP\" }";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
            }
        });
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }

}
