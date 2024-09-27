package com.example.postsmvi.paging

interface Paging<Key, Item> {
    suspend fun loadNextItems()
    fun reset()
}