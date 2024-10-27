package com.maskarad.ratatoskr;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import java.io.IOException;
import java.nio.channels.SocketChannel;

public class MainController implements MessageListener {

    private SocketChannel client;

    @FXML
    private Button button_establishConnection;

    @FXML
    private Button button_sendMessage;

    @FXML
    private Button button_openServer;

    @FXML
    private TextArea textArea_RecievedMessages;

    @FXML
    private TextField textField_SendMessage;

    @FXML
    private TextField textField_YggAdress;

    @FXML
    private TextField textField_YggPort;

    @FXML
    void establishConnection(ActionEvent event) throws IOException {
        client = Client.connect(textField_YggAdress.getText(), Integer.parseInt(textField_YggPort.getText()));
    }

    @FXML
    void sendMessage(ActionEvent event) throws IOException {
        String message = textField_SendMessage.getText();
        Client.send(client, message);

        displayMessage("Sent: " + message);
    }

    @FXML
    void openServer(ActionEvent event) {
        Server.initialize(7990, this);
    }

    @Override
    public void onMessageReceived(String message) {
        displayMessage("Received: " + message);
    }

    private void displayMessage(String message) {
        Platform.runLater(() -> textArea_RecievedMessages.appendText(message + "\n"));
    }
}
