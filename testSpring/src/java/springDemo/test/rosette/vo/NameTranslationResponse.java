package springDemo.test.rosette.vo;

public class NameTranslationResponse {

	private Integer confidence; // (number): The confidence of the translation,
	private String targetLanguage; // (string): ISO 639-3 code for thetranslation language,
	private String targetScheme; // (string): transliteration scheme for the translation,
	private String targetScript; // (string): ISO 15924 code for the translation script,
	private String translation; // (string): The translation

	public NameTranslationResponse() {
	}

	// --------------------- Getters & Setters ----------------------//
	
	public Integer getConfidence() {
		return confidence;
	}

	public void setConfidence(Integer confidence) {
		this.confidence = confidence;
	}

	public String getTargetLanguage() {
		return targetLanguage;
	}

	public void setTargetLanguage(String targetLanguage) {
		this.targetLanguage = targetLanguage;
	}

	public String getTargetScheme() {
		return targetScheme;
	}

	public void setTargetScheme(String targetScheme) {
		this.targetScheme = targetScheme;
	}

	public String getTargetScript() {
		return targetScript;
	}

	public void setTargetScript(String targetScript) {
		this.targetScript = targetScript;
	}

	public String getTranslation() {
		return translation;
	}

	public void setTranslation(String translation) {
		this.translation = translation;
	}
	
}
