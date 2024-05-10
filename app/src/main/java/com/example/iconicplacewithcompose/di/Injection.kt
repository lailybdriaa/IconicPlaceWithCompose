package com.example.iconicplacewithcompose.di

import com.example.iconicplacewithcompose.data.IconicPlaceRepository


object Injection {
    fun provideRepository(): IconicPlaceRepository {
        return IconicPlaceRepository.getInstance()
    }
}