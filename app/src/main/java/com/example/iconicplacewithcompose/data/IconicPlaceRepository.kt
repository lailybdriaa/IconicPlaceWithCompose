package com.example.iconicplacewithcompose.data

import com.example.iconicplacewithcompose.model.FakeIconicPlaceDataSource
import com.example.iconicplacewithcompose.model.FavoritePlace
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map

class IconicPlaceRepository {

    private val favoritePlaces = mutableListOf<FavoritePlace>()

    init {
        if (favoritePlaces.isEmpty()) {
            FakeIconicPlaceDataSource.dummyIconicPlaces.forEach {
                favoritePlaces.add(FavoritePlace(it, false))
            }
        }
    }

    private fun getAllIconicPlace(): Flow<List<FavoritePlace>> {
        return flowOf(favoritePlaces)
    }

    fun getFavoritePlaceById(iconicPlaceId: Long): FavoritePlace {
        return favoritePlaces.first {
            it.iconicPlace.id == iconicPlaceId
        }
    }

    fun updateFavoritePlace(iconicPlaceId: Long, newCountValue: Boolean): Flow<Boolean> {
        val index = favoritePlaces.indexOfFirst { it.iconicPlace.id == iconicPlaceId }
        val result = if (index >= 0) {
            val favoritePlace = favoritePlaces[index]
            favoritePlaces[index] =
                favoritePlace.copy(iconicPlace = favoritePlace.iconicPlace, isFavorited = newCountValue)
            true
        } else {
            false
        }
        return flowOf(result)
    }

    fun getAddedFavoritePlace(): Flow<List<FavoritePlace>> {
        return getAllIconicPlace()
            .map { favoritePlaces ->
                favoritePlaces.filter { favoritePlace ->
                    favoritePlace.isFavorited
                }
            }
    }

    fun searchIconicPlace(query: String): Flow<List<FavoritePlace>>{
        return getAllIconicPlace()
            .map { favoritePlaces ->
                favoritePlaces.filter { favoritePlace ->
                    favoritePlace.iconicPlace.name.contains(query, ignoreCase = true)
                }
            }
    }

    companion object {
        @Volatile
        private var instance: IconicPlaceRepository? = null

        fun getInstance(): IconicPlaceRepository =
            instance ?: synchronized(this) {
                IconicPlaceRepository().apply {
                    instance = this
                }
            }
    }
}