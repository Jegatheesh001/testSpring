package springDemo.test.rosette.vo;

public class NameTranslationRequest  {

	private String name; //(string): a name to be translated,
	private String targetLanguage;// (string): ISO 639-3 code for the translation language,
	private String entityType; //(string, optional): the entity type of the name: PERSON, LOCATION, or ORGANIZATION,
	private String sourceLanguageOfOrigin; //(string, optional): ISO 639-3 code for the name's language of origin,
	private String sourceLanguageOfUse; //(string, optional): ISO 639-3 code for the name's language of use,
	private String sourceScript; //(string, optional): ISO 15924 code for the name's script,
	private String targetScheme; //(string, optional): transliteration scheme for the translation,
	private String targetScript; //(string, optional): ISO 15924 code for the translation script

	public NameTranslationRequest() {
	}

	public NameTranslationRequest(String name, String targetLanguage) {
		this.name = name;
		this.targetLanguage = targetLanguage;
	}
	
	// --------------------- Getters & Setters ----------------------//

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTargetLanguage() {
		return targetLanguage;
	}

	public void setTargetLanguage(String targetLanguage) {
		this.targetLanguage = targetLanguage;
	}

	public String getEntityType() {
		return entityType;
	}

	public void setEntityType(String entityType) {
		this.entityType = entityType;
	}

	public String getSourceLanguageOfOrigin() {
		return sourceLanguageOfOrigin;
	}

	public void setSourceLanguageOfOrigin(String sourceLanguageOfOrigin) {
		this.sourceLanguageOfOrigin = sourceLanguageOfOrigin;
	}

	public String getSourceLanguageOfUse() {
		return sourceLanguageOfUse;
	}

	public void setSourceLanguageOfUse(String sourceLanguageOfUse) {
		this.sourceLanguageOfUse = sourceLanguageOfUse;
	}

	public String getSourceScript() {
		return sourceScript;
	}

	public void setSourceScript(String sourceScript) {
		this.sourceScript = sourceScript;
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

}
