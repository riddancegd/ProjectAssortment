package com.example.gamedelete.fruitgame.presentation

import com.example.gamedelete.fruitgame.presentation.domain.FruitRepository

class TestFruitRepository : FruitRepository {
    override fun getFruits() = listOf(
        "apple",
        "orange",
        "pineapple",
        "pear",
        "banana"
    )
}