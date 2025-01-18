package com.example.gamedelete.fruitgame.presentation.domain

interface FruitRepository {
    fun getFruits() : List<String>
}