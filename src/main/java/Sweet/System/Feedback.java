package Sweet.System;

public class Feedback {
    private String feedback;
    private String adminReply;
    private String relatedProduct;

    public Feedback(String feedback, String relatedProduct) {
        this.feedback = feedback;
        this.relatedProduct = relatedProduct;
    }

    public Feedback(String feedback) {
        this.feedback = feedback;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }

    public String getAdminReply() {
        return adminReply;
    }

    public void setAdminReply(String adminReply) {
        this.adminReply = adminReply;
    }

    public String getRelatedProduct() {
        return relatedProduct;
    }

    public void setRelatedProduct(String relatedProduct) {
        this.relatedProduct = relatedProduct;
    }

    @Override
    public String toString() {
        return "User's Feedback: " + feedback;
    }
}
