## Recognition Servicer (dockerized)

### Install docker image (on docker hub):
$ docker pull caiopereirasousa/recognition-service

### Running a container
$ docker run -p <port_number>:8080 -e S3_BUCKET_NAME=<your_s3_bucket> -e AWS_ACCESS_KEY_ID=<your_aws_access_key> -e AWS_SECRET_ACCESS_KEY=<your_aws_secret_access> -d caiopereirasousa/recognition-service

### Parameters
Replace <port_number> with the number of the port you want to access the REST APIs.
Replace the other parameters with your AWS information.

### Environment variables
* __S3_BUCKET_NAME__ : The S3 bucket where the images have been uploaded. The _"/detectLabelsFromStorage"_ and _"/matchFacesWithStorage"_ endpoints use this bucket to search for the images informed in the request parameters.
* __AWS_ACCESS_KEY_ID__ : AWS credential used to access rekognition and S3 services
* __AWS_SECRET_ACCESS_KEY__ : AWS credential used to access rekognition and S3 services

### API Documentation
After running a container, access the Swagger API Documentation: _http://<host>:<port>/swagger-ui.html_