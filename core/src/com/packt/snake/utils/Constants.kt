package com.packt.snake.utils

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Texture

class Constants {
    companion object {

        //Snake
        const val MOVE_TIME = 0.5f
        const val SNAKE_MOVEMENT = 32f
        const val RIGHT = 0
        const val LEFT = 1
        const val UP = 2
        const val DOWN = 3
        val SNAKE_HEAD = Texture(Gdx.files.internal("snakehead.png"))

        //Snake body
        val SNAKE_BODY = Texture(Gdx.files.internal("snakebody.png"))

        //Apple
        val APPLE = Texture(Gdx.files.internal("apple.png"))
    }
}