
package springDemo.test.rosette.action;

import com.basistech.rosette.api.HttpRosetteAPI;
import com.basistech.rosette.api.common.AbstractRosetteAPI;
import com.basistech.rosette.apimodel.NameTranslationRequest;
import com.basistech.rosette.apimodel.NameTranslationResponse;
import com.basistech.util.LanguageCode;

import java.io.IOException;

public class NameTranslationExample {
	
    public static void main(String[] args) {
        try {
            new NameTranslationExample().run();
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    public void run() throws IOException {
        String translatedNameData = "معمر محمد أبو منيار القذاف";
        NameTranslationRequest request = new NameTranslationRequest.Builder(translatedNameData, LanguageCode.ENGLISH)
                .build();

        HttpRosetteAPI rosetteApi = new HttpRosetteAPI.Builder()
                                    .key(TranslatorMainAction.APIKEY)
                                    .url("https://api.rosette.com:443/rest/v1/name-translation/")
                                    .build();
        System.out.println(""+rosetteApi.USER_AGENT_STR);
        //The api object creates an http client, but to provide your own:
        //api.httpClient(CloseableHttpClient)
        NameTranslationResponse response = rosetteApi.perform(AbstractRosetteAPI.NAME_TRANSLATION_SERVICE_PATH, request, NameTranslationResponse.class);
     // System.out.println(responseToJson(response));
        System.out.println("");
    }
}

