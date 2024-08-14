package Sweet.System;

public class Feedback {
    private String feedback;
    private String adminReply;

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

    @Override
    public String toString() {
        return "User's Feedback: " + feedback;
    }
}
