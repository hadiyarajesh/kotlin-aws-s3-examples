import aws.sdk.kotlin.services.s3.model.DeleteObjectRequest
import aws.sdk.kotlin.services.s3.model.ListObjectsRequest
import aws.sdk.kotlin.services.s3.model.NoSuchBucket
import aws.sdk.kotlin.services.s3.model.S3Exception
import kotlinx.coroutines.runBlocking

fun main(): Unit = runBlocking {
    val s3Client = getS3Client()

    val bucketName = "BUCKET_NAME"

    s3Client.use { s3 ->
        val listObjectRequest = ListObjectsRequest {
            bucket = bucketName
        }

        try {
            val listObjectResponse = s3.listObjects(listObjectRequest)

            println("Deleting all objects in bucket $bucketName")
            println()

            listObjectResponse.contents?.forEach { s3Object ->
                println("Deleting object name: ${s3Object.key} size: ${getHumanReadableByteCount(s3Object.size)}")

                val deleteObjectsRequest = DeleteObjectRequest {
                    bucket = bucketName
                    key = s3Object.key
                }
                s3.deleteObject(deleteObjectsRequest)
            }
        } catch (e: NoSuchBucket) {
            println("Bucket $bucketName not found")
            return@use
        } catch (e: S3Exception) {
            println(e.message)
            return@use
        }
    }
}
