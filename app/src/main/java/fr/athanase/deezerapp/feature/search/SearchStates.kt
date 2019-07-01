package fr.athanase.deezerapp.feature.search

import fr.athanase.entites.Album
import fr.athanase.entites.Artist
import fr.athanase.entites.Playlist
import fr.athanase.entites.Track

sealed class SearchState{
    class UpdateArtist(val artist: ArtistState) : SearchState()
    class UpdateAlbum(val albums: AlbumState) : SearchState()
    class UpdateTracks(val tracks: TrackState) : SearchState()
    class UpdatePlaylist(val playlists: PlaylistState) : SearchState()
}

sealed class ArtistState {
    class Content(val artist: List<Artist>) : ArtistState()
    object Empty : ArtistState()
}

sealed class AlbumState {
    class Content(val albums: List<Album>) : AlbumState()
    object Empty : AlbumState()
}

sealed class TrackState {
    class Content(val tracks: List<Track>) : TrackState()
    object Empty : TrackState()
}

sealed class PlaylistState {
    class Content(val playlists: List<Playlist>) : PlaylistState()
    object Empty : PlaylistState()
}