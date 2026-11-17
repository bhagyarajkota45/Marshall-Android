package com.marshall.music.data

import com.marshall.music.data.model.Song
import com.marshall.music.data.model.durationMillis
import java.net.URI

/** Builds a plain UTF-8 extended M3U playlist from tracks with local file paths. */
object M3u8PlaylistExporter {
    data class Export(
        val fileName: String,
        val contents: String,
        val includedTracks: Int,
        val skippedTracks: Int,
    )

    fun prepare(title: String, songs: List<Song>): Export {
        val entries = songs.mapNotNull { song ->
            val location = song.exportLocation() ?: return@mapNotNull null
            val durationSeconds = (song.durationMillis() / 1_000L).takeIf { it > 0L } ?: -1L
            val label = "${song.artist.m3uLine()} - ${song.title.m3uLine()}"
            "$durationSeconds,$label\n$location"
        }
        val contents = buildString {
            append("#EXTM3U\n")
            entries.forEach { append("#EXTINF:").append(it).append('\n') }
        }
        return Export(
            fileName = fileName(title),
            contents = contents,
            includedTracks = entries.size,
            skippedTracks = songs.size - entries.size,
        )
    }

    fun fileName(title: String): String {
        var base = title.trim().map { character ->
            if (character.isISOControl() || character in "\\/:*?\"<>|") '_' else character
        }.joinToString("").trim().trimEnd('.')
        if (base.endsWith(".m3u8", ignoreCase = true)) base = base.dropLast(5).trimEnd('.')
        return "${base.take(100).ifBlank { "Playlist" }}.m3u8"
    }

    private fun Song.exportLocation(): String? {
        localPath?.trim()?.takeIf { it.startsWith('/') }?.let { return it.m3uLine() }
        val fileUri = localUri?.takeIf { it.startsWith("file://", ignoreCase = true) } ?: return null
        return runCatching { URI(fileUri).path }
            .getOrNull()
            ?.takeIf { it.startsWith('/') }
            ?.m3uLine()
    }

    private fun String.m3uLine(): String = replace('\r', ' ').replace('\n', ' ').trim()
}
