package gr.aueb.cf.masterpeakers.service;

public interface IEmailService {
    void sendSimpleMessage(String to, String subject, String text);
}

