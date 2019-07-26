package com.fpt.pubsub;
import com.google.api.gax.core.CredentialsProvider;
import com.google.auth.Credentials;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.pubsub.v1.Publisher;
import com.google.protobuf.ByteString;
import com.google.pubsub.v1.ProjectTopicName;
import com.google.pubsub.v1.PubsubMessage;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class PublisherDemo extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ProjectTopicName projectTopicName = ProjectTopicName.of("valiant-lambda-243404", "sub1");
        Publisher publisher = Publisher.newBuilder(projectTopicName).setCredentialsProvider(new CredentialsProvider() {
            @Override
            public Credentials getCredentials() throws IOException {
                return GoogleCredentials.fromStream(getClass().getClassLoader().getResourceAsStream("pubsub-key.json"));
            }
        }).build();

        ByteString byteString = ByteString.copyFromUtf8(req.getParameter("message"));
        PubsubMessage pubsubMessage = PubsubMessage.newBuilder().setData(byteString).build();
        resp.getWriter().print("Sent message success");
    }
}

