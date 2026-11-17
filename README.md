# Marshall Music Player for Android

![Marshall logo](branding/Marshall-logo-2160x2160.png)

Marshall is an Android music player source project adapted from the BitChord 1.9.1 Android source. It retains the Android app architecture and presents Marshall branding. The separate Windows Marshall project is not included or modified.

Repository: [bhagyarajkota45/Marshall-Android](https://github.com/bhagyarajkota45/Marshall-Android)

## Features

- Search, browse, stream, and manage music, queues, playlists, favorites, downloads, and local files.
- Background playback, media controls, audio effects, equalizer, crossfade, and playback options.
- Word-timed lyrics with active-word highlighting, plus translation and romanization when supported by the source.
- Matching Spotify Canvas clips in player and lyrics views, with static artwork as a fallback.
- Existing Google, Spotify, Discord, Last.fm, ListenBrainz, and Listen Together integration flows.
- Android phone, TV, and Wear OS modules.

Feature and setup details are documented in [FEATURE_STATUS.md](FEATURE_STATUS.md).

## Technology

- Kotlin, Jetpack Compose, and Kotlin Multiplatform.
- Android Gradle Plugin with the included Gradle wrapper.
- Native C++ audio components.
- Optional Go Listen Together backend in `backend/`.

## Screenshots

No in-app screenshots are currently included in this repository. The image above is the project logo, not an app screenshot.

## Build and install

Requirements: JDK 17, Android SDK Platform 37, Android NDK `28.2.13676358`, and CMake `3.22.1`. The included Gradle wrapper uses Gradle 8.14.4.

Copy `local.properties.example` to `local.properties`, then set `sdk.dir` to your Android SDK location. `local.properties` is machine-specific and ignored by Git.

On Windows, build the production-debug phone app with:

```powershell
.\gradlew.bat :app:assembleProdDebug --no-daemon --console=plain
```

Install it on a connected device with USB debugging enabled:

```powershell
.\gradlew.bat :app:installProdDebug --no-daemon --console=plain
```

On macOS or Linux, use `./gradlew` with the same Gradle tasks.

Run the phone and shared-module unit tests with:

```powershell
.\gradlew.bat :app:testProdDebugUnitTest :shared:jvmTest :sharedUi:jvmTest --no-daemon --console=plain
```

## Local integration configuration

Provider credentials and deployment-specific values belong in ignored `local.properties` or environment variables, never in source control. Optional properties include `LASTFM_API_KEY`, `LASTFM_SECRET`, `POTOKEN_GOOGLE_API_KEY`, `POTOKEN_REQUEST_KEY`, `UPDATE_LATEST_RELEASE_URL`, `DISCORD_PROJECT_URL`, `LISTEN_TOGETHER_SERVER`, and `ADDON_AUTH_SERVER`. The PoToken request path needs its local key values; the project builds without them, but that path will not be configured.

The app retains upstream compatibility service hosts where no Marshall-owned endpoint was supplied. Listen Together and addon authentication can be redirected with `LISTEN_TOGETHER_SERVER` and `ADDON_AUTH_SERVER`. The updater and Discord project link remain inactive until `UPDATE_LATEST_RELEASE_URL` and `DISCORD_PROJECT_URL` are configured. Google and Spotify sign-in may require provider-side registration for the Android package ID `com.marshall.music`.

## Issues and contributions

Please report bugs and propose changes in the [GitHub issue tracker](https://github.com/bhagyarajkota45/Marshall-Android/issues). Read [CONTRIBUTING.md](CONTRIBUTING.md) before opening a pull request.

## License and attribution

This project retains its upstream GPLv3 license, copyright and source notices, and third-party attributions. See [LICENSE](LICENSE), [UPSTREAM_ATTRIBUTION.md](UPSTREAM_ATTRIBUTION.md), and [ADDITIONAL.md](ADDITIONAL.md).
