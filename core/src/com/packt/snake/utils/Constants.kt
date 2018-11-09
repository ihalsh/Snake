package com.packt.snake.utils

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Texture

class Constants {
    companion object {

        //General
        const val WORLD_WIDTH = 640f
        const val WORLD_HEIGHT = 480f
        const val GAME_OVER_TEXT = "Game Over...\n Tap SPACE to restart!"

        //Snake
        const val MOVE_TIME = 0.25f
        const val SNAKE_MOVEMENT = 32f
        const val RIGHT = 0
        const val LEFT = 1
        const val UP = 2
        const val DOWN = 3
        val SNAKE_HEAD = Texture(Gdx.files.internal("snakehead.png"))
        const val POINTS_PER_APPLE = 20

        //Snake body
        val SNAKE_BODY = Texture(Gdx.files.internal("snakebody.png"))

        //Apple
        val APPLE = Texture(Gdx.files.internal("apple.png"))

        enum class STATE {
            PLAYING, GAME_OVER
        }
    }
}