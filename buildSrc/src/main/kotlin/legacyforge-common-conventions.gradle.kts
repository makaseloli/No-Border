import net.meatwo310.mdk.build.*

plugins {
    `java-library`
    idea
    id("net.neoforged.moddev.legacyforge")
}

val modId: String by project
val minecraftVersion: String by project

dependencies {
    api(project(":common"))
    compileOnly("${versionCatalog.module(VersionCatalogLibrary.Mixin)}:${versionCatalog.version(VersionCatalogVersion.Mixin)}")
    annotationProcessor("${versionCatalog.module(VersionCatalogLibrary.Mixin)}:${versionCatalog.version(VersionCatalogVersion.Mixin)}:processor")
}

base {
    archivesName = "$modId-$minecraftVersion-common"
}

java.toolchain {
    languageVersion = JavaLanguageVersion.of(17)
    @Suppress("UnstableApiUsage")
    vendor = JvmVendorSpec.JETBRAINS
}

// Use a separate variable to avoid shadowing by LegacyForgeExtension.getMinecraftVersion()
val mcpVer = minecraftVersion
legacyForge {
    mcpVersion = mcpVer
}

mixin {
    add(sourceSets.main.get(), "$modId.refmap.json")
    config("$modId.mixins.json")
}

sourceSets.main.get().output.dir(
    mapOf("builtBy" to tasks.named("compileJava")),
    layout.buildDirectory.dir("mixin")
)

tasks.jar {
    duplicatesStrategy = DuplicatesStrategy.EXCLUDE
}
