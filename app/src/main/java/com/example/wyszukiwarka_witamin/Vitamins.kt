package com.example.wyszukiwarka_witamin

data class Vitamins(
    val id: String,
    val objawy_nadmiaru: List<String>,
    val objawy_niedoboru: List<String>,
    val wystepowanie: List<String>
)