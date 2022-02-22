package api;


import io.restassured.response.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Used for storing the relevant details of the response
 *
 * @param <T> The Type of response object to deserialize the response into
 */
public class ApiResponse<T> {

  private final T responseObject;
  private final Response response;

  Logger logger = LoggerFactory.getLogger(ApiResponse.class);

  public ApiResponse(Class<T> model, Response response) {
    this.response = response;

    try {
      this.responseObject = response.as(model);
    } catch (Exception e) {
      if (response.statusCode() == 200) {
        logger.warn("Response successful (HTTP 200) but could not parse api response as " + model
            .getName());
      }
      throw e;
    }
  }

  public T getResponseObject() {
    return responseObject;
  }

  public Response getResponse() {
    return response;
  }

}
