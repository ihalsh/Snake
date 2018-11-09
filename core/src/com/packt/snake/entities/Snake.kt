package com.packt.snake.entities

import com.badlogic.gdx.Gdx.graphics
import com.badlogic.gdx.Gdx.input
import com.badlogic.gdx.Input
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.utils.Array
import com.packt.snake.utils.Constants
import com.packt.snake.utils.Constants.Companion.DOWN
import com.packt.snake.utils.Constants.Companion.LEFT
import com.packt.snake.utils.Constants.Companion.MOVE_TIME
import com.packt.snake.utils.Constants.Companion.RIGHT
import com.packt.snake.utils.Constants.Companion.SNAKE_BODY
import com.packt.snake.utils.Constants.Companion.SNAKE_HEAD
import com.packt.snake.utils.Constants.Companion.SNAKE_MOVEMENT
import com.packt.snake.utils.Constants.Companion.STATE.GAME_OVER
import com.packt.snake.utils.Constants.Companion.STATE.PLAYING
import com.packt.snake.utils.Constants.Companion.UP

class Snake(var position: Vector2 = Vector2(),
            private var snakeDirection: Int = RIGHT) {

    private var timer = Constants.MOVE_TIME
    val bodyParts = Array<BodyPart>()
    private var previousPosition = Vector2()
    private var directionSet = false
    var gameState = PLAYING

    fun update(delta: Float, apple: Apple) {

        //Update time
        timer -= delta

        //Update direction
        queryInput()

        if (timer <= 0) {
            timer = MOVE_TIME
            previousPosition.set(position)
            when (snakeDirection) {
                RIGHT -> position.x += SNAKE_MOVEMENT
                LEFT -> position.x -= SNAKE_MOVEMENT
                UP -> position.y += SNAKE_MOVEMENT
                DOWN -> position.y -= SNAKE_MOVEMENT
            }
            updateBodyPartsPosition(previousPosition)
            checkForOutOfBounds()
            checkSnakeBodyCollision()
            directionSet = false
        }
        checkAppleCollision(position, apple)
    }

    fun render(batch: SpriteBatch) {
        batch.draw(SNAKE_HEAD, position.x, position.y)
        for (bodyPart in bodyParts) bodyPart.render(batch)
    }

    private fun checkAppleCollision(position: Vector2, apple: Apple) {

        if (apple.appleAvailable && apple.position.x == position.x && apple.position.y == position.y) {
            val bodyPart = BodyPart()
            bodyPart.updateBodyPosition(position)
            bodyParts.insert(0, bodyPart)
        }
    }

    private fun updateBodyPartsPosition(previousPosition: Vector2) {
        if (bodyParts.size > 0) {
            val bodyPart = bodyParts.removeIndex(0)
            bodyPart.updateBodyPosition(previousPosition)
            bodyParts.add(bodyPart)
        }
    }

    private fun checkForOutOfBounds() {
        if (position.x >= graphics.width) position.x = 0f
        if (position.x < 0) position.x = graphics.width - SNAKE_MOVEMENT
        if (position.y >= graphics.height) position.y = 0f
        if (position.y < 0) position.y = graphics.height - SNAKE_MOVEMENT
    }

    private fun checkSnakeBodyCollision() {
        for (bodyPart in bodyParts)
            if (bodyPart.bodyPartPosition == position) {
                gameState = GAME_OVER
            }
    }

//----DIRECTION UPDATE START

    private fun queryInput() {
        when {
            input.isKeyPressed(Input.Keys.LEFT) -> updateDirection(LEFT)
            input.isKeyPressed(Input.Keys.RIGHT) -> updateDirection(RIGHT)
            input.isKeyPressed(Input.Keys.UP) -> updateDirection(UP)
            input.isKeyPressed(Input.Keys.DOWN) -> updateDirection(DOWN)
        }
    }

    private fun updateDirection(newSnakeDirection: Int) {
        if (!directionSet && snakeDirection != newSnakeDirection) {
            directionSet = true
            when (newSnakeDirection) {
                LEFT -> updateIfNotOppositeDirection(newSnakeDirection, RIGHT)
                RIGHT -> updateIfNotOppositeDirection(newSnakeDirection, LEFT)
                UP -> updateIfNotOppositeDirection(newSnakeDirection, DOWN)
                DOWN -> updateIfNotOppositeDirection(newSnakeDirection, UP)
            }
        }
    }

    private fun updateIfNotOppositeDirection(newSnakeDirection: Int, oppositeDirection: Int) {
        if (snakeDirection != oppositeDirection || bodyParts.size == 0)
            snakeDirection = newSnakeDirection
    }

//----DIRECTION UPDATE END

    inner class BodyPart(val bodyPartPosition: Vector2 = Vector2()) {

        fun updateBodyPosition(position: Vector2) {
            bodyPartPosition.set(position)
        }

        fun render(batch: SpriteBatch) {
            if (bodyPartPosition != position)
                batch.draw(SNAKE_BODY, bodyPartPosition.x, bodyPartPosition.y)
        }
    }
}