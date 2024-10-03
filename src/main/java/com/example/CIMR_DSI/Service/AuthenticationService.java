package com.example.CIMR_DSI.Service;

import java.io.UnsupportedEncodingException;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import net.bytebuddy.utility.RandomString;
import com.example.CIMR_DSI.Model.Collaborateur;
import com.example.CIMR_DSI.Model.CollaborateurTrimestre;
import com.example.CIMR_DSI.Model.Projet;
import com.example.CIMR_DSI.Model.Trimestre;
import com.example.CIMR_DSI.Repo.CollaborateurRepository;
import com.example.CIMR_DSI.Repo.ProjetRepository;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Service
public class AuthenticationService {

    @Autowired
    CollaborateurRepository collaborateurRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    CollaborateurTrimestreService collaborateurTrimestreService;

    @Autowired
    TrimestreService trimestreService;

    @Autowired
    ProjetRepository projetRepository;

    public Collaborateur signup(Collaborateur input) {
        Collaborateur Collaborateur = new Collaborateur();
        Collaborateur.setPassword(passwordEncoder.encode(input.getPassword()));
        Collaborateur.setEmail(input.getEmail());
        ;

        return collaborateurRepository.save(Collaborateur);
    }

    @Autowired
    private JavaMailSender mailSender;

    public void register(Collaborateur user, String siteURL)
            throws UnsupportedEncodingException, MessagingException {

        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        user.setRole("user");
        String randomCode = RandomString.make(64);
        user.setVerificationCode(randomCode);
        user.setEnabled(false);

        collaborateurRepository.save(user);

        Trimestre trimestreActual = trimestreService.FindCurrentTrimestre();

        if (trimestreActual != null) {

            int congee = user.getDroitdecongee();
            CollaborateurTrimestre collaborateurTrimestre = new CollaborateurTrimestre();

            collaborateurTrimestre.setTotalNetcongee(trimestreActual.getTotaldisponibledejour() - congee);

            int x = (int) (collaborateurTrimestre.getTotalNetcongee()
                    * (trimestreActual.getCoefficientmaintence() / 100));
            collaborateurTrimestre.setMaintenence(x);

            collaborateurTrimestre.setChargedisponible(collaborateurTrimestre.getTotalNetcongee() - x);
            collaborateurTrimestre.setAnalyse((int) (collaborateurTrimestre.getTotalNetcongee() * 0.2));
            collaborateurTrimestre.setControleQualite((int) (collaborateurTrimestre.getTotalNetcongee() * 0));
            collaborateurTrimestre.setIntegrationcoordination((int) (collaborateurTrimestre.getTotalNetcongee() * 0.3));
            collaborateurTrimestre
                    .setChargecompetence(collaborateurTrimestre.getTotalNetcongee() - collaborateurTrimestre.getSum());

            collaborateurTrimestreService.assignCollaborateurToTrimestre(user.getId(), trimestreActual.getId(),
                    collaborateurTrimestre);
        }

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
                "        <img src=\"https://ci3.googleusercontent.com/meips/ADKq_Nb3d7dlSQd5IE4IfJqv8GFxT8xcFVON9gnsObzs6wyl6zwbsRoFtkNZjX2IR67bO4TghqB7N-nlsuyhTK1PQU2XMWd-=s0-d-e1-ft#https://www.cimr.ma/file/2015/01/logo-cimr.png\"\r\n"
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

    public void sendAssigningEmail(Collaborateur collaborateur, Projet p)
            throws MessagingException, UnsupportedEncodingException {

        Collaborateur user = collaborateurRepository.findByIdNotOptional(collaborateur.getId());

        Projet projet = projetRepository.findByIdNotOptional(p.getId());

        String fromAddress = "paypalabiad@gmail.com";
        String senderName = "CIMR DSI";
        String subject = "Projet Assignée DSI";
        String content = "<!DOCTYPE html>\r\n" + //
                "<html lang=\"fr\">\r\n" + //
                "<head>\r\n" + //
                "    <meta charset=\"UTF-8\">\r\n" + //
                "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\r\n" + //
                "    <title>Assignation de projet</title>\r\n" + //
                "</head>\r\n" + //
                "<body style=\"font-family: Arial, sans-serif; line-height: 1.6; color: #333;\">\r\n" + //
                "    <div style=\"max-width: 600px; margin: 0 auto; padding: 20px;\">\r\n" + //
                " <img src=\"https://www.cimr.ma/file/2015/01/logo-cimr.png\" alt=\"Logo de l'entreprise\"\r\n" + //
                "            style=\"max-width: 200px; margin-bottom: 20px;\">" + //
                "        <h2 style=\"color: #78B51F;\">Nouveau projet assigné</h2>\r\n" + //
                "        <p>Cher(e) [[name]] [[prenom]],</p>\r\n" + //
                "        <p>Nous avons le plaisir de vous informer qu'un nouveau projet vous a été assigné. Voici les détails :</p>\r\n"
                + //
                "        <p style=\"background-color: #f0f0f0; padding: 15px; border-radius: 5px;\">\r\n" + //
                "            <strong>Nom du projet :</strong> [[NOM_PROJET]]<br>\r\n" + //
                "            <strong>Date de début :</strong> [[DATE_DEBUT]]<br>\r\n" + //
                "            <strong>Date de fin prévue :</strong> [[DATE_FIN]]\r\n" + //
                "        </p>\r\n" + //
                "        <p>Pour accéder aux détails complets du projet et commencer à travailler, veuillez cliquer sur le bouton ci-dessous :</p>\r\n"
                + //
                "        <p>\r\n" + //
                "            <a href=\"[[URL_PROJET]]\" style=\"display: inline-block; padding: 10px 20px; background-color: #78B51F; color: #ffffff; text-decoration: none; border-radius: 5px; margin: 20px 0;\">Accéder au projet</a>\r\n"
                + //
                "        </p>\r\n" + //
                "        <p>Si le bouton ne fonctionne pas, vous pouvez copier et coller le lien suivant dans votre navigateur :</p>\r\n"
                + //
                "        <p>[[URL_PROJET]]</p>\r\n" + //
                "        <p>Nous sommes convaincus que vos compétences et votre expertise seront précieuses pour la réussite de ce projet. N'hésitez pas à contacter votre responsable si vous avez des questions ou besoin d'informations supplémentaires.</p>\r\n"
                + //
                "        <p>Bonne chance pour ce nouveau défi !</p>\r\n" + //
                "        <p>Cordialement,<br>L'équipe de gestion de projet</p>\r\n" + //
                "    </div>\r\n" + //
                "</body>\r\n" + //
                "</html>";

        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);
        String toAddress = user.getEmail();
        helper.setFrom(fromAddress, senderName);
        helper.setTo(toAddress);
        helper.setSubject(subject);

        content = content.replace("[[name]]", user.getNom());
        content = content.replace("[[prenom]]", user.getPrenom());
        content = content.replace("[[NOM_PROJET]]", projet.getTitre());
        content = content.replace("[[DATE_DEBUT]]", projet.getDatedebut());
        content = content.replace("[[DATE_FIN]]", projet.getDatelimie());

        helper.setText(content, true);
        new Thread(() -> {
            mailSender.send(message);
        }).start();

    }

    public boolean verify(String verificationCode) {
        Collaborateur user = collaborateurRepository.findByVerificationCode(verificationCode);

        if (user == null) {
            return false;
        } else {
            user.setVerificationCode(null);
            user.setEnabled(true);
            collaborateurRepository.save(user);

            return true;
        }

    }

    public Collaborateur authenticate(Collaborateur input) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        input.getEmail(),
                        input.getPassword()));

        return collaborateurRepository.findByEmail(input.getEmail())
                .orElseThrow();
    }

}