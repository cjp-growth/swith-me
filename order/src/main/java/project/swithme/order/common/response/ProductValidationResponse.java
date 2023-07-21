package project.swithme.order.common.response;

public class ProductValidationResponse {

    private boolean valid;

    private ProductValidationResponse() {
    }

    public ProductValidationResponse(boolean valid) {
        this.valid = valid;
    }

    public boolean isValid() {
        return valid;
    }

    @Override
    public String toString() {
        return String.format("Valid: %s", valid);
    }
}
