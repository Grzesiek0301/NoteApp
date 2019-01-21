package grzegorzurbanski.notebook.notebooksbapp.mail;

public interface FeedbackSender {

    void sendFeedback(String from, String name, String feedback);
}
