plugins {
    java
    `java-library`
    `maven-publish`
}

group = "lbaena"
version = "1.1"

java {
    sourceCompatibility = JavaVersion.VERSION_11
    targetCompatibility = JavaVersion.VERSION_11
}

repositories {
    mavenCentral()
}

dependencies {
    val javafxVersion = "17.0.10"
    val os = System.getProperty("os.name").lowercase()
    val platform = when {
        os.contains("win") -> "win"
        os.contains("mac") -> "mac"
        else -> "linux"
    }

    implementation("org.openjfx:javafx-graphics:$javafxVersion:$platform")
    implementation("org.openjfx:javafx-controls:$javafxVersion:$platform")
    implementation("org.openjfx:javafx-base:$javafxVersion:$platform")

    compileOnly("org.mariadb.jdbc:mariadb-java-client:3.3.3")
    compileOnly("org.postgresql:postgresql:42.7.3")
    compileOnly("org.xerial:sqlite-jdbc:3.45.3.0")

    testImplementation(platform("org.junit:junit-bom:5.10.2"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")

    testRuntimeOnly("org.mariadb.jdbc:mariadb-java-client:3.3.3")
    testRuntimeOnly("org.postgresql:postgresql:42.7.3")
    testRuntimeOnly("org.xerial:sqlite-jdbc:3.45.3.0")
}
tasks.withType<JavaCompile> {
    options.encoding = "UTF-8"
}

tasks.test {
    useJUnitPlatform()
}

tasks.javadoc {
    options.encoding = "UTF-8"
}

val gitLabPrivateToken: String by project
val gitLabProjectId: String by project

val sourcesJar by tasks.registering(Jar::class) {
    from(sourceSets.main.get().allJava)
    archiveClassifier.set("sources")
}

