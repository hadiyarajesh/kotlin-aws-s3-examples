import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.7.21"
}

group = "com.hadiyarajesh"
version = "1.0"

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.slf4j:slf4j-simple:2.0.5")
    implementation("aws.sdk.kotlin:s3:0.17.5-beta")
    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "11"
}
