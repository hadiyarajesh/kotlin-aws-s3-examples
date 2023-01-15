import aws.sdk.kotlin.services.s3.model.DeleteBucketRequest
import aws.sdk.kotlin.services.s3.model.S3Exception
import kotlinx.coroutines.runBlocking

fun main(): Unit = runBlocking {
    val s3Client = getS3Client()

    val bucketName = "BUCKET_NAME"

    s3Client.use { s3 ->
        val deleteBucketRequest = DeleteBucketRequest {
            bucket = bucketName
        }

        println("Deleting bucket $bucketName")
        println()

        try {
            s3.deleteBucket(deleteBucketRequest)
            println("Bucket $bucketName successfully deleted")
        } catch (e: S3Exception) {
            println(e.message)
        }
    }
}
