package resource;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;


@Path("/calculator")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CalculatorRestResource {

    @GET
    @Path("/add")
    public Response add(@QueryParam("a") double a, @QueryParam("b") double b) {
        return Response.ok(new CalculationResult(a + b)).build();
    }

    @GET
    @Path("/subtract")
    public Response subtract(@QueryParam("a") double a, @QueryParam("b") double b) {
        return Response.ok(new CalculationResult(a - b)).build();
    }

    @GET
    @Path("/multiply")
    public Response multiply(@QueryParam("a") double a, @QueryParam("b") double b) {
        return Response.ok(new CalculationResult(a * b)).build();
    }

    @GET
    @Path("/divide")
    public Response divide(@QueryParam("a") double a, @QueryParam("b") double b) {
        if (b == 0) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(new ErrorResponse("Cannot divide by zero"))
                    .build();
        }
        return Response.ok(new CalculationResult(a / b)).build();
    }

    // Data classes for responses
    public static class CalculationResult {
        private double result;

        public CalculationResult() {
        }

        public CalculationResult(double result) {
            this.result = result;
        }

        public double getResult() {
            return result;
        }

        public void setResult(double result) {
            this.result = result;
        }
    }

    public static class ErrorResponse {
        private String message;

        public ErrorResponse() {
        }

        public ErrorResponse(String message) {
            this.message = message;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }
    }
}
