package com.marshall.music

import com.marshall.music.data.M3u8PlaylistExporter
import com.marshall.music.data.model.Song
import org.junit.Assert.assertEquals
import org.junit.Test

class M3u8PlaylistExporterTest {
    @Test
    fun prepare_writesExtendedM3uForFilesystemTracksAndSkipsProviderOnlyUris() {
        val songs = listOf(
            Song(
                videoId = "one",
                title = "Track\nOne",
                artist = "Artist",
                thumbnailUrl = null,
                durationText = "3:45",
                localUri = "content://media/audio/1",
                localPath = "/storage/emulated/0/Music/one.mp3",
            ),
            Song(
                videoId = "two",
                title = "Provider-only",
                artist = "Artist",
                thumbnailUrl = null,
                localUri = "content://provider/audio/2",
            ),
        )

        val export = M3u8PlaylistExporter.prepare("Road trip", songs)

        assertEquals("Road trip.m3u8", export.fileName)
        assertEquals(1, export.includedTracks)
        assertEquals(1, export.skippedTracks)
        assertEquals(
            "#EXTM3U\n#EXTINF:225,Artist - Track One\n/storage/emulated/0/Music/one.mp3\n",
            export.contents,
        )
    }

    @Test
    fun fileName_replacesUnsafeCharactersAndAvoidsDuplicateExtension() {
        assertEquals("Road__trip_.m3u8", M3u8PlaylistExporter.fileName("Road:/trip?.M3U8"))
        assertEquals("Playlist.m3u8", M3u8PlaylistExporter.fileName("  "))
    }
}
