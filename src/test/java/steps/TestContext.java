package steps;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TestContext {

    private RequestSpecification requestSpec;
    private Response response;

    public RequestSpecification getRequestSpec() {
        return requestSpec;
    }

    public void setRequestSpec(RequestSpecification requestSpec) {
        this.requestSpec = requestSpec;
    }

    public Response getResponse() {
        return response;
    }

    public void setResponse(Response response) {
        this.response = response;
    }




}
