package com.packt.snake.entities

import com.badlogic.gdx.Gdx.*
import com.badlogic.gdx.Input
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.math.Vector2
import com.packt.snake.utils.Constants
import com.packt.snake.utils.Constants.Companion.DOWN
import com.packt.snake.utils.Constants.Companion.LEFT
import com.packt.snake.utils.Constants.Companion.MOVE_TIME
import com.packt.snake.utils.Constants.Companion.RIGHT
import com.packt.snake.utils.Constants.Companion.SNAKE_MOVEMENT
import com.packt.snake.utils.Constants.Companion.UP

class Snake(private var position: Vector2 = Vector2(),
            private var snakeDirection: Int = RIGHT) {

    private val snakeHead = Texture(files.internal("snakehead.png"))
    private val snakeBody = Texture(files.internal("snakebody.png"))
    private var timer = Constants.MOVE_TIME

    fun update(delta: Float) {

        //Update time
        timer -= delta

        //Update direction
        queryInput()

        if (timer <= 0) {
            timer = MOVE_TIME
            when (snakeDirection) {
                RIGHT -> position.x += SNAKE_MOVEMENT
                LEFT -> position.x -= SNAKE_MOVEMENT
                UP -> position.y += SNAKE_MOVEMENT
                DOWN -> position.y -= SNAKE_MOVEMENT
            }
            checkForOutOfBounds()
        }
    }

    fun render(batch: SpriteBatch) {

        batch.draw(snakeHead, position.x, position.y)
    }

    private fun checkForOutOfBounds() {
        if (position.x >= graphics.width) position.x = 0f
        if (position.x < 0) position.x = graphics.width - SNAKE_MOVEMENT
        if (position.y >= graphics.height) position.y = 0f
        if (position.y < 0) position.y = graphics.height - SNAKE_MOVEMENT
    }

    private fun queryInput() {
        when {
            input.isKeyPressed(Input.Keys.LEFT) -> snakeDirection = LEFT
            input.isKeyPressed(Input.Keys.RIGHT) -> snakeDirection = RIGHT
            input.isKeyPressed(Input.Keys.UP) -> snakeDirection = UP
            input.isKeyPressed(Input.Keys.DOWN) -> snakeDirection = DOWN
        }
    }
}