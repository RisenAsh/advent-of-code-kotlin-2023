plugins {
    kotlin("jvm") version "1.9.21"
    id("org.jmailen.kotlinter") version "4.0.0"
}

sourceSets {
    main {
        kotlin.srcDir("src")
    }
}

allprojects {
    apply(plugin = "org.jmailen.kotlinter")
    repositories {
        mavenCentral()
    }
}

tasks.check {
    dependsOn("installKotlinterPrePushHook")
}

tasks {
    wrapper {
        gradleVersion = "8.5"
    }
}
