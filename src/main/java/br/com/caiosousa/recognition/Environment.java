package br.com.caiosousa.recognition;

public enum Environment {
	
	S3_BUCKET_NAME, AWS_ACCESS_KEY_ID, AWS_SECRET_ACCESS_KEY;
	
	public String value() {
		return System.getenv(this.name());
	}
	
}
