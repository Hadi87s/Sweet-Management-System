package Sweet.System;

public class Feedback {
    private String message;
    private String relatedProduct;

    public Feedback(String feedback, String relatedProduct) {
        this.message = feedback;
        this.relatedProduct = relatedProduct;
    }

    public Feedback() {

    }

    public Feedback(String feedback) {
        this.message = feedback;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }


    public String getRelatedProduct() {
        return relatedProduct;
    }

    public void setRelatedProduct(String relatedProduct) {
        this.relatedProduct = relatedProduct;
    }


}
