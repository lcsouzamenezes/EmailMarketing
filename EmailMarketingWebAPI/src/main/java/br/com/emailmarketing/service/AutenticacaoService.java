package br.com.emailmarketing.service;

import br.com.emailmarketing.exception.SistemaException;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

@Service
public class AutenticacaoService {

    private static String OAUTH2_URL = "http://localhost:8001/oauth/token?grant_type=password&username=param1&password=param2";

    public String autenticar(String usuario, String senha){
        HttpURLConnection httpURLConnection = null;
        String token = null;
        try {
            String oauthUrl = OAUTH2_URL;
            oauthUrl = oauthUrl.replace("param1",usuario);
            oauthUrl = oauthUrl.replace("param2",senha);

            URL url = new URL(oauthUrl);
            httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setRequestProperty("Authorization", "Basic bXktdHJ1c3RlZC1jbGllbnQ6c2VjcmV0");
            httpURLConnection.setRequestProperty("Accept", "application/json");

            if (httpURLConnection.getResponseCode() == 200) {
                BufferedReader br = new BufferedReader(new InputStreamReader((httpURLConnection.getInputStream())));
                String output;
                while ((output = br.readLine()) != null) {
                    token = output;
                }
            }
        }
        catch (MalformedURLException ex) {
            throw new SistemaException(ex);
        }
        catch (IOException ex) {
            throw new SistemaException(ex);
        }
        finally {
            httpURLConnection.disconnect();
        }
        return token;
    }

}
