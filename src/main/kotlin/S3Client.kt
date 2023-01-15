import aws.sdk.kotlin.runtime.auth.credentials.StaticCredentialsProvider
import aws.sdk.kotlin.runtime.endpoint.AwsEndpoint
import aws.sdk.kotlin.runtime.endpoint.StaticEndpointResolver
import aws.sdk.kotlin.services.s3.S3Client
import aws.smithy.kotlin.runtime.http.endpoints.Endpoint

fun getS3Client(): S3Client {
    return S3Client(
        S3Client.Config {
            region = Constants.region

            credentialsProvider = StaticCredentialsProvider.Builder().apply {
                accessKeyId = Constants.accessKey
                secretAccessKey = Constants.secretKey
            }.build()

            endpointResolver = StaticEndpointResolver(AwsEndpoint(endpoint = Endpoint(uri = Constants.endpoint)))
        }
    )
}
