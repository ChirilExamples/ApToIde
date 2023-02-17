package com.example.apptoide.data.structure

data class Data(
    val hidden: Int,
    val limit: Int,
    val list: List<AppInf>,
    val next: Int,
    val offset: Int,
    val total: Int
)
