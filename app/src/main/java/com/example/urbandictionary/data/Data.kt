package com.example.urbandictionary.data

data class Data(
    val author: String,
    val current_vote: String,
    val defid: Int,
    val definition: String,
    val example: String,
    val permalink: String,
    val thumbs_down: Int,
    val thumbs_up: Int,
    val word: String,
    val written_on: String
)