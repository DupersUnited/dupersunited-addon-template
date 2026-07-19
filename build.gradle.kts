plugins {
    alias(libs.plugins.fabric.loom)
}

base {
    archivesName.set(providers.gradleProperty("archives_base_name"))
    version = providers.gradleProperty("version").get()
    group = providers.gradleProperty("maven_group").get()
}

repositories {
    maven {
        name = "DupersWtf"
        url = uri("https://maven.dupers.wtf/releases")
    }
}

dependencies {
    minecraft(libs.minecraft)
    mappings(variantOf(libs.yarn) { classifier("v2") })
    modImplementation(libs.fabric.loader)
    modImplementation(libs.fabric.api)

    modImplementation(libs.dupersunited)
}

tasks {
    processResources {
        val properties = mapOf(
            "version" to project.version
        )

        inputs.properties(properties)
        filesMatching("fabric.mod.json") {
            expand(properties)
        }
    }

    java {
        toolchain {
            languageVersion.set(JavaLanguageVersion.of(21))
        }
    }

    jar {
        inputs.property("archivesName", project.base.archivesName.get())

        from("LICENSE") {
            rename { "${it}_${inputs.properties["archivesName"]}" }
        }
    }

    withType<JavaCompile>().configureEach {
        options.encoding = "UTF-8"
        options.release.set(21)
    }
}
