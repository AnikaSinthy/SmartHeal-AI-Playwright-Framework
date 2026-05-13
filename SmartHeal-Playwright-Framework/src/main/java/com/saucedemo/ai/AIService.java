package com.saucedemo.ai;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import org.json.JSONObject;
import org.json.JSONArray;

public class AIService {
    private static final String API_KEY = "AIzaSyACxex6koJhRC9UV5N-1oPJksA09PJJATU";
    private static final String URL = "https://generativelanguage.googleapis.com/v1beta/models/gemini-pro:generateContent?key=" + API_KEY;

    public static String getAIResponse(String prompt) {
        try {
            JSONObject body = new JSONObject().put("contents", new JSONArray().put(new JSONObject()
                    .put("parts", new JSONArray().put(new JSONObject().put("text", prompt)))));

            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder().uri(URI.create(URL))
                    .header("Content-Type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(body.toString())).build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            return new JSONObject(response.body()).getJSONArray("candidates").getJSONObject(0)
                    .getJSONObject("content").getJSONArray("parts").getJSONObject(0).getString("text").trim();
        } catch (Exception e) {
            return "ERROR";
        }
    }

    public static String getHealedSelector(String html, String brokenSelector) {
        String prompt = "HTML: " + html + ". CSS selector '" + brokenSelector + "' failed. Find the new one. Return ONLY the selector string.";
        return getAIResponse(prompt);
    }

    public static boolean auditUI(String html, String requirement) {
        String prompt = "Review this UI HTML: " + html.substring(0, Math.min(html.length(), 1500)) +
                ". Requirement: " + requirement + ". Return 'PASS' if it looks correct, 'FAIL' if not. ONLY ONE WORD.";
        return getAIResponse(prompt).contains("PASS");
    }
}