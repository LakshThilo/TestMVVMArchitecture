package com.example.mvvmarchitechture.model

data class BookResponse(
    val items: List<ItemDescription>
)

data class ItemDescription(
    val volumeInfo: VolumeInfoDescription
)

data class VolumeInfoDescription(
    val title: String,
    val authors: List<String>,
    val industryIdentifiers: List<IndustryDescription>?
)

data class IndustryDescription(
    val type: String,
    val identifier: String
)