package com.packt.snake

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.math.Vector2
import com.packt.snake.Constants.Companion.MOVE_TIME
import com.packt.snake.Constants.Companion.RIGHT
import com.packt.snake.Constants.Companion.LEFT
import com.packt.snake.Constants.Companion.UP
import com.packt.snake.Constants.Companion.DOWN
import com.packt.snake.Constants.Companion.SNAKE_MOVEMENT

class Snake (private var position:Vector2 = Vector2(),
             private var snakeDirection:Int = RIGHT) {

    private val snakeHead = Texture(Gdx.files.internal("snakehead.png"))
    private val snakeBody = Texture(Gdx.files.internal("snakebody.png"))
    private var timer = Constants.MOVE_TIME

    fun update (delta: Float) {

        //Update time
        timer -= delta
        if (timer <= 0) {
            timer = MOVE_TIME
            when(snakeDirection) {
                RIGHT -> position.x += SNAKE_MOVEMENT
                LEFT -> position.x -= SNAKE_MOVEMENT
                UP -> position.y += SNAKE_MOVEMENT
                DOWN -> position.y -= SNAKE_MOVEMENT
            }
            checkForOutOfBounds()
        }
    }

    fun render (batch: SpriteBatch) {

        batch.draw(snakeHead, position.x, position.y)
    }

    private fun checkForOutOfBounds() {
        if (position.x >= Gdx.graphics.width) position.x = 0f
        if (position.x < 0) position.x = Gdx.graphics.width - SNAKE_MOVEMENT
        if (position.y >= Gdx.graphics.height) position.y = 0f
        if (position.y < 0) position.y = Gdx.graphics.height - SNAKE_MOVEMENT
    }
}