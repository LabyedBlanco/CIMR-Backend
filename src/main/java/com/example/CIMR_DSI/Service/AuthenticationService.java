package com.example.CIMR_DSI.Service;

import java.io.UnsupportedEncodingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import net.bytebuddy.utility.RandomString;
import com.example.CIMR_DSI.Model.Collaborateur;
import com.example.CIMR_DSI.Repo.CollaborateurRepository;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Service
public class AuthenticationService {

    @Autowired
    CollaborateurRepository CollaborateurRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    AuthenticationManager authenticationManager;

    public Collaborateur signup(Collaborateur input) {
        Collaborateur Collaborateur = new Collaborateur();
        Collaborateur.setPassword(passwordEncoder.encode(input.getPassword()));
        Collaborateur.setEmail(input.getEmail());
        ;

        return CollaborateurRepository.save(Collaborateur);
    }

    @Autowired
    private JavaMailSender mailSender;

    public void register(Collaborateur user, String siteURL)
            throws UnsupportedEncodingException, MessagingException {

        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);

        String randomCode = RandomString.make(64);
        user.setVerificationCode(randomCode);
        user.setEnabled(false);

        CollaborateurRepository.save(user);

        sendVerificationEmail(user, siteURL);
    }

    private void sendVerificationEmail(Collaborateur user, String siteURL)
            throws MessagingException, UnsupportedEncodingException {
        String toAddress = user.getEmail();
        String fromAddress = "paypalabiad@gmail.com";
        String senderName = "CIMR DSI";
        String subject = "Please verify your registration";
        String content = "<!DOCTYPE html>\r\n" + //
                "<html lang=\"fr\">\r\n" + //
                "\r\n" + //
                "<head>\r\n" + //
                "    <meta charset=\"UTF-8\">\r\n" + //
                "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\r\n" + //
                "    <title>Vérification d'inscription</title>\r\n" + //
                "</head>\r\n" + //
                "\r\n" + //
                "<body\r\n" + //
                "    style=\"font-family: Arial, sans-serif; line-height: 1.6; color: #333; margin: 0; padding: 0; background-color: #f4f4f4;\">\r\n"
                + //
                "    <div style=\"max-width: 600px; margin: 0 auto; padding: 20px; background-color: #ffffff; text-align: center;\">\r\n"
                + //
                "        <img src=\"https://scontent.fcmn1-2.fna.fbcdn.net/v/t39.30808-6/450557014_894734739363815_8944287630749459341_n.jpg?_nc_cat=105&ccb=1-7&_nc_sid=6ee11a&_nc_eui2=AeEt4rdJ2UlV6Ibpm5sYi6M9EcQXBExXuPMRxBcETFe480kvu23oIh8qbYE-d-SwgVK0ytCmIriE3YVTv76jbTJK&_nc_ohc=giRqn_cBCysQ7kNvgFx_jez&_nc_ht=scontent.fcmn1-2.fna&_nc_gid=ACVl1K3Eoj6sF8b_86Mn3ko&oh=00_AYD5iQxzFNCfoagcIozAD6yrt8WAiF2pQ-Mr1TgdAPLeRg&oe=66F3664E\"\r\n"
                + //
                "            alt=\"Logo de l'entreprise\" style=\"max-width: 200px; margin-bottom: 20px;\">\r\n" + //
                "        <h1>Bienvenue chez CIMR </h1>\r\n" + //
                "        <p>Cher(e) [[name]] [[prenom]],</p>\r\n" + //
                "        <p style=\"\">Nous sommes ravis de vous accueillir dans notre équipe. Pour finaliser votre\r\n"
                + //
                "            inscription, veuillez cliquer\r\n" + //
                "            sur le bouton ci-dessous pour vérifier votre adresse e-mail :</p>\r\n" + //
                "        <p>\r\n" + //
                "            <a href=\"[[URL]]\"\r\n" + //
                "                style=\"display: inline-block; padding: 10px 20px; background-color: #78B51F; color: #ffffff; text-decoration: none; border-radius: 5px; margin: 20px 0;\">Vérifier\r\n"
                + //
                "                mon adresse e-mail</a>\r\n" + //
                "        </p>\r\n" + //
                "        <p>Si le bouton ne fonctionne pas, vous pouvez copier et coller le lien suivant dans votre navigateur :</p>\r\n"
                + //
                "        <p>[[URL]]</p>\r\n" + //
                "        <p>Cette vérification est une étape importante pour sécuriser votre compte et vous permettre d'accéder à tous\r\n"
                + //
                "            nos outils collaboratifs.</p>\r\n" + //
                "\r\n" + //
                "        <p>Nous sommes impatients de travailler avec vous !</p>\r\n" + //
                "        <p>Cordialement,<br></p>\r\n" + //
                "    </div>\r\n" + //
                "</body>\r\n" + //
                "\r\n" + //
                "</html>";

        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);

        helper.setFrom(fromAddress, senderName);
        helper.setTo(toAddress);
        helper.setSubject(subject);

        content = content.replace("[[name]]", user.getNom());
        content = content.replace("[[prenom]]", user.getPrenom());
        String verifyURL = siteURL + "/auth/verify?code=" + user.getVerificationCode();

        content = content.replace("[[URL]]", verifyURL);

        helper.setText(content, true);

        mailSender.send(message);

    }

    public boolean verify(String verificationCode) {
        Collaborateur user = CollaborateurRepository.findByVerificationCode(verificationCode);

        if (user == null) {
            return false;
        } else {
            user.setVerificationCode(null);
            user.setEnabled(true);
            CollaborateurRepository.save(user);

            return true;
        }

    }

    public Collaborateur authenticate(Collaborateur input) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        input.getEmail(),
                        input.getPassword()));

        return CollaborateurRepository.findByEmail(input.getEmail())
                .orElseThrow();
    }

}