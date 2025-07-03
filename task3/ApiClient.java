package task3;

import java.io.*;
import java.net.*;
import java.nio.charset.StandardCharsets;

public class ApiClient {
    private String baseUrl;

    public ApiClient(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    public String createBlogPost(String title, String content, String author) throws Exception {
        String endpoint = baseUrl + "?api=blogs";
        String jsonData = String.format(
                "{\"title\":\"%s\",\"content\":\"%s\",\"author\":\"%s\"}",
                escapeJson(title), escapeJson(content), escapeJson(author)
        );

        return sendPostRequest(endpoint, jsonData);
    }

    public String getAllBlogPosts() throws Exception {
        String endpoint = baseUrl + "?api=blogs";
        return sendGetRequest(endpoint);
    }

    public String getStatistics() throws Exception {
        String endpoint = baseUrl + "?api=stats";
        return sendGetRequest(endpoint);
    }

    private String sendGetRequest(String urlString) throws Exception {
        URL url = new URL(urlString);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.setRequestProperty("Accept", "application/json");
        connection.setRequestProperty("Content-Type", "application/json");
        connection.setConnectTimeout(10000);
        connection.setReadTimeout(10000);

        int responseCode = connection.getResponseCode();

        BufferedReader reader;
        if (responseCode >= 200 && responseCode < 300) {
            reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        } else {
            reader = new BufferedReader(new InputStreamReader(connection.getErrorStream()));
        }

        StringBuilder response = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            response.append(line).append("\n");
        }
        reader.close();

        String responseText = response.toString();

        // Check if response is JSON or HTML
        if (responseText.trim().startsWith("{") || responseText.trim().startsWith("[")) {
            return responseText;
        } else if (responseText.contains("<!DOCTYPE html>")) {
            throw new Exception("Server returned HTML instead of JSON. Check API endpoint configuration.");
        }

        if (responseCode >= 400) {
            throw new Exception("HTTP Error " + responseCode + ": " + responseText);
        }

        return responseText;
    }

    private String sendPostRequest(String urlString, String jsonData) throws Exception {
        URL url = new URL(urlString);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("POST");
        connection.setRequestProperty("Content-Type", "application/json");
        connection.setRequestProperty("Accept", "application/json");
        connection.setConnectTimeout(10000);
        connection.setReadTimeout(10000);
        connection.setDoOutput(true);

        try (OutputStream os = connection.getOutputStream()) {
            byte[] input = jsonData.getBytes(StandardCharsets.UTF_8);
            os.write(input, 0, input.length);
        }

        int responseCode = connection.getResponseCode();

        BufferedReader reader;
        if (responseCode >= 200 && responseCode < 300) {
            reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        } else {
            reader = new BufferedReader(new InputStreamReader(connection.getErrorStream()));
        }

        StringBuilder response = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            response.append(line).append("\n");
        }
        reader.close();

        if (responseCode >= 400) {
            throw new Exception("HTTP Error " + responseCode + ": " + response.toString());
        }

        return response.toString();
    }

    private String escapeJson(String input) {
        if (input == null) return "";
        return input.replace("\\", "\\\\")
                .replace("\"", "\\\"")
                .replace("\n", "\\n")
                .replace("\r", "\\r")
                .replace("\t", "\\t");
    }
}