package org.example.prototype;

public class Email implements Copyable<Email> {

    private String subject;
    private String sender;
    private String body;

    Email(String subject, String sender, String body) {
        this.subject = subject;
        this.sender = sender;
        this.body = body;
    }

    Email(Email e) {
        this.subject = e.subject;
        this.body = e.body;
        this.sender = e.sender;
    }

    @Override
    public Email copy() {
        return new Email(this);
    }

    public String getBody() {
        return this.body;
    }

    public String getSender() {
        return this.sender;
    }

    public String getSubject() {
        return this.subject;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }
}
