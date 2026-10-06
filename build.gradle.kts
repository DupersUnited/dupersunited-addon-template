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
    // remove when 1.0.6 releases, in template & for addon devs - khao
    // https://github.com/DupersUnited/dupersunited-mod/commit/782ea8caf2be942131f62d68948eb49725fa1dc9
    maven {
        url = uri("https://maven.xpple.dev/maven2")
    }
}

dependencies {
    minecraft(libs.minecraft)
    implementation(libs.fabric.loader)
    implementation(libs.fabric.api)

    implementation(libs.dupersunited)
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
            languageVersion.set(JavaLanguageVersion.of(25))
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
        options.release.set(25)
    }
}
