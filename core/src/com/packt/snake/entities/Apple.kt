package com.packt.snake.entities

import com.badlogic.gdx.Gdx.graphics
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.math.MathUtils
import com.badlogic.gdx.math.MathUtils.random
import com.badlogic.gdx.math.Vector2
import com.packt.snake.utils.Constants.Companion.APPLE
import com.packt.snake.utils.Constants.Companion.SNAKE_MOVEMENT

class Apple(val position: Vector2 = Vector2(),
            var appleAvailable: Boolean = false,
            private var snake: Snake) {

    fun update(snake: Snake) {
        if (snake.position == position) appleAvailable = false
    }

    fun render(batch: SpriteBatch) {
        checkAndPlaceApple()
        batch.draw(APPLE, position.x, position.y)
    }

    private fun checkAndPlaceApple() {
        if (!appleAvailable) {
            do {
                position.x = MathUtils.round(
                        random(graphics.width / SNAKE_MOVEMENT - 1)) * SNAKE_MOVEMENT
                position.y = MathUtils.round(
                        random(graphics.height / SNAKE_MOVEMENT - 1)) * SNAKE_MOVEMENT
                appleAvailable = true
            } while (position == snake.position || inSnake(snake, position))
        }
    }

    private fun inSnake(snake: Snake, newApplePosition: Vector2): Boolean {
        for (bodyPart in snake.bodyParts)
            if (bodyPart.bodyPartPosition == newApplePosition) return true
        return false
    }
}

