
package springDemo.test.rosette.action;

import com.basistech.rosette.api.HttpRosetteAPI;
import com.basistech.rosette.apimodel.SyntaxDependenciesResponse;
import com.basistech.rosette.apimodel.DocumentRequest;

import java.io.IOException;

public final class SyntaxDependenciesExample {
	public static void main(String[] args) {
		try {
			new SyntaxDependenciesExample().run();
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(1);
		}
	}

	private void run() throws IOException {
		String syntaxDependenciesData = "Yoshinori Ohsumi, a Japanese cell biologist, was awarded the Nobel Prize in Physiology or Medicine on Monday.";
		HttpRosetteAPI rosetteApi = new HttpRosetteAPI.Builder().key(TranslatorMainAction.APIKEY)
				.url("https://api.rosette.com:443/rest/v1/syntax/dependencies/").build();
		// The api object creates an http client, but to provide your own:
		// api.httpClient(CloseableHttpClient)
		DocumentRequest<?> request = new DocumentRequest.Builder<>().content(syntaxDependenciesData).build();
		SyntaxDependenciesResponse response = rosetteApi.perform(HttpRosetteAPI.SYNTAX_DEPENDENCIES_SERVICE_PATH,
				request, SyntaxDependenciesResponse.class);
		// System.out.println(responseToJson(response));
	}


}
