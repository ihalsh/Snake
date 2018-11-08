package com.packt.snake.entities

import com.badlogic.gdx.Gdx.files
import com.badlogic.gdx.Gdx.graphics
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.math.MathUtils
import com.badlogic.gdx.math.MathUtils.random
import com.badlogic.gdx.math.Vector2
import com.packt.snake.utils.Constants.Companion.SNAKE_MOVEMENT
import ktx.log.info

class Apple(private val position: Vector2 = Vector2(),
            private var appleAvailable: Boolean = false,
            private var snakePosition: Vector2 = Vector2()) {

    private val apple = Texture(files.internal("apple.png"))

    fun update(snakePosition: Vector2) {
        if (snakePosition == position) appleAvailable = false
    }

    fun render(batch: SpriteBatch) {
        checkAndPlaceApple()
        batch.draw(apple, position.x, position.y)
    }

    private fun checkAndPlaceApple() {
        if (!appleAvailable) {
            do {
                position.x = MathUtils.round(
                        random(graphics.width / SNAKE_MOVEMENT - 1)) * SNAKE_MOVEMENT
                position.y = MathUtils.round(
                        random(graphics.height / SNAKE_MOVEMENT - 1)) * SNAKE_MOVEMENT
                appleAvailable = true
                info { "Apple position: ${position.x}, ${position.y}" }
            } while (position.x == snakePosition.x && position.y == snakePosition.y)
        }
    }
}

