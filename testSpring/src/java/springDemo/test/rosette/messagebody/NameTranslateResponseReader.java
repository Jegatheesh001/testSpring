package springDemo.test.rosette.messagebody;

import java.io.IOException;
import java.io.InputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import javax.ws.rs.Consumes;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.MessageBodyReader;
import javax.ws.rs.ext.Provider;

import com.basistech.rosette.apimodel.NameTranslationResponse;

@Provider
@Consumes(MediaType.APPLICATION_JSON)
public class NameTranslateResponseReader implements MessageBodyReader<NameTranslationResponse> {

	@Override
	public boolean isReadable(Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType) {
		return com.basistech.rosette.apimodel.NameTranslationResponse.class.isAssignableFrom(type);
	}

	@Override
	public NameTranslationResponse readFrom(Class<NameTranslationResponse> response, Type genericType,
			Annotation[] annotations, MediaType mediaType, MultivaluedMap<String, String> httpHeaders,
			InputStream entityStream) throws IOException, WebApplicationException {
		System.out.println(response.getMethods());
		return null;
	}

}
