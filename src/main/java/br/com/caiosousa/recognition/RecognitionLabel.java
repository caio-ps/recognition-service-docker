package br.com.caiosousa.recognition;

public class RecognitionLabel {

	private String name;
	private Float confidence;

	public static RecognitionLabel build(final String name, final Float confidence) {
		return new RecognitionLabel(name, confidence);
	}
	
	private RecognitionLabel(final String name, final Float confidence) {
		super();
		this.name = name;
		this.confidence = confidence;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Float getConfidence() {
		return confidence;
	}

	public void setConfidence(Float confidence) {
		this.confidence = confidence;
	}

}
