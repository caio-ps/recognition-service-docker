package br.com.caiosousa.recognition;

public enum Environment {
	
	S3_BUCKET_NAME;
	
	public String value() {
		return System.getenv(this.name());
	}
	
}
