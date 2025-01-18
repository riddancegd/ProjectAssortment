package com.example.gamedelete.fruitgame.presentation.data

import com.example.gamedelete.fruitgame.presentation.domain.FruitRepository

class FruitRepositoryImpl : FruitRepository{
    override fun getFruits(): List<String> {
        return listOf(
            "apple",
            "orange",
            "pineapple",
            "pear",
            "banana"
        )
    }

}