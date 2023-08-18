package com.technipixl.exo1

    data class MarvelResponse(val data: DataResponse)

    data class Marvel(val id: String, val name: String, val thumbnail: Thumbnail )

    data class DataResponse(val results: List<Marvel>)

    data class Thumbnail(val path: String, val extension: String)
