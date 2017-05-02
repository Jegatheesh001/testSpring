package springDemo.test.rosette.action;

import com.basistech.rosette.api.HttpRosetteAPI;
import com.basistech.rosette.apimodel.PingResponse;

import java.io.IOException;

public final class LanguageTranslator {
	
	String apiKey=TranslatorMainAction.APIKEY;
	
    public static void main(String[] args) {
        try {
            new LanguageTranslator().run();
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    private void run() throws IOException {
        HttpRosetteAPI rosetteApi = new HttpRosetteAPI.Builder().key(apiKey).url("https://api.rosette.com:443/rest/v1/name-translation/").build();
        PingResponse response = rosetteApi.ping();
       // System.out.println(responseToJson(response));
    }
}


