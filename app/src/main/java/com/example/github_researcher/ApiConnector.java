package com.example.github_researcher;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class ApiConnector {
    private static HttpURLConnection connection;

    public JSONObject connect(String nickname) {

        BufferedReader reader;
        String line;
        StringBuffer responseContent = new StringBuffer();

        try {
            URL url = new URL("https://api.github.com/search/repositories?q=user:" + nickname);
            connection = (HttpURLConnection) url.openConnection(); //open connection

            //Request setup
            connection.setRequestMethod("GET");
            connection.setConnectTimeout(5000);
            connection.setReadTimeout(5000);

            int status = connection.getResponseCode();
            if (status > 299) { //if conncetion is not avaible
                reader = new BufferedReader(new InputStreamReader(connection.getErrorStream()));
                while ((line = reader.readLine()) != null) {
                    responseContent.append(line);
                }
                reader.close();
            } else { //if connection is succesfull
                reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                while ((line = reader.readLine()) != null) {
                    responseContent.append(line);
                }
                reader.close();
            }


            JSONObject jsonObject = new JSONObject(responseContent.toString());

            return jsonObject;


        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        } finally {
            connection.disconnect();
        }
        return new JSONObject();
    }

    public JSONObject connectLanguages(String nickname, String repo) {
        BufferedReader reader;
        String line;
        StringBuffer responseContent = new StringBuffer();

        try {
            URL url = new URL("https://api.github.com/repos/" + nickname + "/" + repo + "/languages");
            connection = (HttpURLConnection) url.openConnection(); //open connection

            //Request setup
            connection.setRequestMethod("GET");
            connection.setConnectTimeout(5000);
            connection.setReadTimeout(5000);

            int status = connection.getResponseCode();

            if (status > 299) { //if conncetion is not avaible
                reader = new BufferedReader(new InputStreamReader(connection.getErrorStream()));
                while ((line = reader.readLine()) != null) {
                    responseContent.append(line);
                }
                reader.close();
            } else { //if connection is succesfull
                reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                while ((line = reader.readLine()) != null) {
                    responseContent.append(line);
                }
                reader.close();
            }

            JSONObject jsonObject = new JSONObject(responseContent.toString());

            return jsonObject;

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        } finally {
            connection.disconnect();
        }
        return new JSONObject();
    }

    public String connectUpdatedat(String nickname, String repoName) {

        BufferedReader reader;
        String line;
        StringBuffer responseContent = new StringBuffer();
        String updatedAt;

        try {
            URL url = new URL("https://api.github.com/repos/" + nickname + "/" + repoName);
            connection = (HttpURLConnection) url.openConnection(); //open connection

            //Request setup
            connection.setRequestMethod("GET");
            connection.setConnectTimeout(5000);
            connection.setReadTimeout(5000);

            int status = connection.getResponseCode();

            if (status > 299) { //if conncetion is not avaible
                reader = new BufferedReader(new InputStreamReader(connection.getErrorStream()));
                while ((line = reader.readLine()) != null) {
                    responseContent.append(line);
                }
                reader.close();
            } else { //if connection is succesfull
                reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                while ((line = reader.readLine()) != null) {
                    responseContent.append(line);
                }
                reader.close();
            }


            JSONObject jsonObject = new JSONObject(responseContent.toString());


            updatedAt = jsonObject.get("pushed_at").toString();

            return updatedAt;


        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        } finally {
            connection.disconnect();
        }
        return new String();
    }

    public void loadRepos(JSONObject object, ArrayList<String> repos) {

        try {
            JSONArray arr = object.getJSONArray("items");

            for (int i = 0; i < arr.length(); i++) {
                repos.add((String) arr.getJSONObject(i).get("name"));
            }
        } catch (JSONException ex) {
            ex.printStackTrace();
        }
    }

}


