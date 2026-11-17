# Feature and parity status

## Confirmed in the source

- The Android application source is based on the supplied 1.9.1 mobile source tree. Its Android app, shared UI, shared data, TV, Wear OS, and native support modules are retained.
- First-party Java/Kotlin package declarations and Android application IDs use `com.marshall.music`; the app label is Marshall.
- The main phone UI, playback, library, search, queue, settings, and account flows are preserved from the Android source.
- Lyrics models carry word-level timings and the lyrics UI highlights the active word against playback time. Translation and romanization paths are present.
- The player and lyrics screens can display matching Spotify Canvas clips, with static album artwork as the fallback when no clip is available.
- The supplied Marshall logo replaces first-party app marks, launcher choices, Compose logo resources, and Wear OS marks. The original 2160×2160 file is preserved separately.
- Native JNI entry points and the bit-perfect symbol export map match the Marshall Android package; the built arm64 libraries expose the expected symbols.
- A Marshall pink accent and dark surface colors were applied using the Windows Marshall app as a reference; its source project was not edited or copied into this deliverable.
- The app updater and Discord project link have no default Marshall destination. Both remain inactive until their Marshall-owned URLs are configured.

## Parity limits and setup still needed

- The Android source keeps the original Android navigation and layouts; exact screen-by-screen parity with the Windows app has not been verified.
- The LastWave-specific lyric timing, translation/romanization, and animated Canvas behavior already existed in the supplied Android source and were preserved. No LastWave code or artwork was copied.
- Listen Together, addon authentication, and invite-link compatibility still depend on the existing upstream service hosts unless you configure Marshall-owned endpoints. No Marshall backend credentials or deployed service were provided.
- OAuth sign-in may need Google/Spotify provider registration for `com.marshall.music`. Last.fm keys are optional and supplied locally.
- Physical device testing has not been performed as part of this source package. Review the build result and test on the target phones before making an APK.

## Build and test result

- `:app:assembleProdDebug` completed successfully, including the phone app's native libraries for arm64-v8a, armeabi-v7a, and x86_64, plus the paired Wear OS module.
- `:app:testProdDebugUnitTest`: 1,259 tests, 0 failures, 1 skipped.
- `:shared:jvmTest`: 122 tests, 0 failures.
- `:sharedUi:jvmTest`: 42 tests, 0 failures.
- Verification used JDK 17, Gradle 8.14.4, SDK 37, NDK 28.2.13676358, and CMake 3.22.1. The project now pins the same Gradle and NDK versions for VS Code builds.
- Android Gradle Plugin 8.10.1 warns that it was tested through compile SDK 36 when compiling against SDK 37; the build nevertheless completed.
- Newer base-language strings missing from some locale files now use English fallback text so the locale completeness check passes; those labels may remain English in those languages.

The delivered ZIP contains source only; it does not contain an APK. The TV module is retained but was not included in the phone build task. The optional Go Listen Together backend was not separately compiled or tested because Go is not installed in the build environment.
