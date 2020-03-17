package GameLogic;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class HandleApi {


    // This function handles the API call to get all the player data. Also for the bonus.
    // This function gets json as string
    public static String getResponseBody(String apiURL) throws IOException {
        BufferedReader reader;
        String line;
        StringBuffer responseBody = new StringBuffer();

        URL url = new URL(apiURL);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        while((line = reader.readLine()) != null) {
            responseBody.append(line);
        }
        reader.close();
        return responseBody.toString();
    }

    // Returns json string as an object
    public static PlayerMarket getJsonObject() throws IOException {
        String api = "https://fantasy.premierleague.com/api/bootstrap-static/";
        return new Gson().fromJson(getResponseBody(api), PlayerMarket.class);
    }
}
