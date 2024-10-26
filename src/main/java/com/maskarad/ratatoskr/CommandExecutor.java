package com.maskarad.ratatoskr;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class CommandExecutor {

    public static String runCommand(String command) {
        StringBuilder output = new StringBuilder();

        try {
            Process process = Runtime.getRuntime().exec(command);

            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                output.append(line).append("\n");
            }

            int exitCode = process.waitFor();
            if (exitCode != 0) {
                System.err.println("Command exited with code: " + exitCode);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return output.toString();
    }
}

