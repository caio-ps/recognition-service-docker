package br.com.caiosousa.recognition;


import java.nio.ByteBuffer;
import java.util.Base64;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;

import software.amazon.awssdk.core.regions.Region;
import software.amazon.awssdk.services.rekognition.RekognitionClient;
import software.amazon.awssdk.services.rekognition.model.CompareFacesRequest;
import software.amazon.awssdk.services.rekognition.model.DetectLabelsRequest;
import software.amazon.awssdk.services.rekognition.model.Image;
import software.amazon.awssdk.services.rekognition.model.S3Object;

@RestController
public class RecognitionController {
	
	private static final String KEY_TEMPLATE = "%s.%s";

	@RequestMapping(path = "/detectLabelsFromStorage", method = RequestMethod.GET)
	public List<RecognitionLabel> detectLabelsFromStorage(@RequestParam(name = "imageName", defaultValue = "erro.png") final String imageName) {

		final var labels = rekognitionClient().detectLabels(DetectLabelsRequest.builder()
				.image(Image.builder()
						.s3Object(S3Object.builder()
								.bucket(Environment.S3_BUCKET_NAME.value())
								.name(imageName).build()).build()).build());
		
		return labels.labels()
				.stream()
				.map((label) -> RecognitionLabel.build(label.name(), label.confidence()))
				.collect(Collectors.toList());

	}

	@RequestMapping(path = "/matchFacesWithStorage", method = RequestMethod.POST)
	public CompareFacesResult detectFacesFromStorage(@RequestBody final ImageUpload imageUpload) throws JsonProcessingException {

		final var comparision = rekognitionClient().compareFaces(CompareFacesRequest.builder()
				.sourceImage(Image.builder()
						.bytes(ByteBuffer.wrap(Base64.getDecoder().decode(imageUpload.getContent()))).build())
				.targetImage(Image.builder()
						.s3Object(S3Object.builder()
								.bucket(Environment.S3_BUCKET_NAME.value())
								.name(String.format(KEY_TEMPLATE, imageUpload.getName(), imageUpload.getExtension())).build()).build()).build());
		
		final var details = comparision.faceMatches();
		
		if (details.size() != 1) {
			return CompareFacesResult.build(0F);
		} else {
			return CompareFacesResult.build(details.get(0).similarity());
		}
		
	}
	
	@RequestMapping(path = "/detectLabels", method = RequestMethod.POST)
	public List<RecognitionLabel> detectLabels(@RequestBody ImageUpload imageUpload) {

		final var labels = rekognitionClient().detectLabels(DetectLabelsRequest.builder()
				.image(Image.builder()
						.bytes(ByteBuffer.wrap(Base64.getDecoder().decode(imageUpload.getContent()))).build()).build());
		
		return labels.labels()
				.stream()
				.map((label) -> RecognitionLabel.build(label.name(), label.confidence()))
				.collect(Collectors.toList());

	}
	
	private RekognitionClient rekognitionClient() {
		return RekognitionClient.builder()
				.region(Region.US_WEST_2)
				.build();
	}

}
