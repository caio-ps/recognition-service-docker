package br.com.caiosousa.recognition;

public class CompareFacesResult {

	private Float similarity;
	private Boolean samePerson;
	
	public static CompareFacesResult build(final Float similarity) {
		return new CompareFacesResult(similarity);
	}
	
	private CompareFacesResult(final Float similarity) {
		super();
		this.similarity = similarity;
		this.samePerson = similarity > 85.0;
	}

	public Float getSimilarity() {
		return similarity;
	}

	public Boolean getSamePerson() {
		return samePerson;
	}

}
