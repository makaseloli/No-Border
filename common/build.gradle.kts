plugins {
    `java-library`
}

repositories {
    mavenCentral()
}

dependencies {
    // Keep the shared classpath independent from any specific loader.
    compileOnly(libs.slf4j.api)
}

java.toolchain {
    languageVersion = JavaLanguageVersion.of(17)
}
