package org.example.prototype;

public class PremiumEmail extends Email {

    private String premiumContent;

    PremiumEmail(String subject, String sender, String body, String premiumContent) {
        super(subject, sender, body);
        this.premiumContent = premiumContent;
    }

    PremiumEmail(PremiumEmail e) {
        super(e);
        this.premiumContent = e.premiumContent;
    }

    @Override
    public PremiumEmail copy() {
        return new PremiumEmail(this);
    }

    public String getPremiumContent() {
        return this.premiumContent;
    }

    public void setPremiumContent(String premiumContent) {
        this.premiumContent = premiumContent;
    }
}
