package Utilities;
 
import java.util.Properties;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javafx.application.Application;
import org.apache.log4j.Logger;
import TestLib.Automation_properties;
 
public class StatusMail {
 
    public final static String PROPERTYFILENAME = "config/mail.properties";
    private static Properties mailProperties = new Properties();
    final public static Logger logger = Logger.getLogger(StatusMail.class);
    public static int totalTCcount = 0;
    public static int passTCcount = 0;
    public static int failTCcount = 0;
    public static String testPassPercentage = null;
    public static String testPassRate = null;
 
    public static int SUMMARYFLAG;
 
    public static String getCleanedEmails(String rawEmails) {
        if (rawEmails == null || rawEmails.trim().isEmpty()) {
            return "qatest9717@gmail.com"; // fallback if null or empty
        }
        return rawEmails.trim();
    }
 
    public static String to = getCleanedEmails(Automation_properties.getInstance().getProperty("ReportEmail")) + ",qatest9717@gmail.com";
    public static String cc = "qatest9717@gmail.com";
    public static String subject = "Smoke Test Reports";
    public static String attachmentPath = "";
    public static String attachmentPath1 = "";
 
    public static void sendMail() throws Exception {
        System.out.println("TO email from config: " + Automation_properties.getInstance().getProperty("ReportEmail"));
        subject = "Smoke Test Reports";
        attachmentPath = HTMLPreparation.generateMail("exectionReport");
 
        PieChartGenerator pie = new PieChartGenerator();
        if (totalTCcount - failTCcount > 0) {
            pie.setTestPassed(totalTCcount - failTCcount);
        }
        if (failTCcount > 0) {
            pie.setTestFailed(failTCcount);
        }
 
        Application.launch(PieChartGenerator.class);
        triggerSendMail();
    }
 
    public static void sendHealthCheckReport() throws Exception {
        subject = "Health Check Report";
        attachmentPath = HTMLPreparation.generateMail("healthReport");
        triggerSendMail();
    }
 
    public static void triggerSendMail() {
        String userName = "nagarajusada31@gmail.com";
        String passWord = "qoqj yvat suvn biuw"; // App password
 
        String host = "smtp.gmail.com";
        String port = "465";
 
        Properties props = new Properties();
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", port);
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.ssl.enable", "true");
        props.put("mail.smtp.socketFactory.port", port);
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.ssl.trust", host);
        props.put("mail.smtp.debug", "true");
 
        try {
            Session session = Session.getInstance(props, new javax.mail.Authenticator() {
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(userName, passWord);
                }
            });
 
            MimeMessage msg = new MimeMessage(session);
            msg.setFrom(new InternetAddress(userName));
 
            System.out.println("TO: [" + to + "]");
            System.out.println("CC: [" + cc + "]");
 
            msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to.trim()));
            msg.setRecipients(Message.RecipientType.CC, InternetAddress.parse(cc.trim()));
            msg.setSubject(subject);
 
            Multipart multipart = new MimeMultipart();
 
            // Attach HTML report
            MimeBodyPart htmlPart = new MimeBodyPart();
            DataSource source = new FileDataSource(attachmentPath);
            htmlPart.setDataHandler(new DataHandler(source));
            htmlPart.setFileName("ExecutionReport.html");
            multipart.addBodyPart(htmlPart);
 
            // Attach Pie Chart image
            MimeBodyPart imagePart = new MimeBodyPart();
            DataSource fds = new FileDataSource(System.getProperty("user.dir") + "/test-output/automationPichart.png");
            imagePart.setDataHandler(new DataHandler(fds));
            imagePart.setFileName("TestPieChart.png");
            imagePart.setHeader("Content-ID", "<image>");
            multipart.addBodyPart(imagePart);
 
            msg.setContent(multipart);
 
            Transport.send(msg);
            System.out.println("✅ Mail sent successfully using Gmail SMTP SSL (port 465)");
 
        } catch (Exception e) {
            e.printStackTrace();
        }
 
        // Optional second method using manual transport
        try {
            Session session = Session.getDefaultInstance(props, null);
            MimeMessage msg = new MimeMessage(session);
            msg.setSubject(subject);
 
            Multipart multipart = new MimeMultipart();
            MimeBodyPart messageBodyPart = new MimeBodyPart();
 
            DataSource source = new FileDataSource(attachmentPath);
            messageBodyPart.setDataHandler(new DataHandler(source));
            multipart.addBodyPart(messageBodyPart);
 
            messageBodyPart = new MimeBodyPart();
            DataSource fds = new FileDataSource(System.getProperty("user.dir") + "/test-output/automationPichart.png");
            messageBodyPart.setDataHandler(new DataHandler(fds));
            messageBodyPart.setHeader("Content-ID", "<image>");
            multipart.addBodyPart(messageBodyPart);
 
            msg.setContent(multipart);
            msg.setFrom(new InternetAddress(userName));
            msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to.trim()));
            msg.setRecipients(Message.RecipientType.CC, InternetAddress.parse(cc.trim()));
            msg.saveChanges();
 
            Transport transport = session.getTransport("smtp");
            transport.connect(host, userName, passWord);
 
            System.out.println("connected mail ");
 
            transport.sendMessage(msg, msg.getAllRecipients());
 
            transport.close();
            System.out.println("Mail Sent");
 
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}