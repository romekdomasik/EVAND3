package com.technipixl.exo1

data class DetailMarvelResponse (
    val data: DataDetailResponse
)
data class DataDetailResponse(
    val results: List<CharacterDetail>
)
data class CharacterDetail(
    val id: String,
    val name: String,
    val thumbnail: Thumbnail
)