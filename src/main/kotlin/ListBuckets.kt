import kotlinx.coroutines.runBlocking

fun main(): Unit = runBlocking {
    val s3Client = getS3Client()

    println("Listing all buckets")
    println()

    s3Client.use { s3 ->
        s3.listBuckets().buckets?.forEach {
            println(it.name)
        }
    }
}
