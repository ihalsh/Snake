package com.packt.snake

import com.badlogic.gdx.graphics.Color.BLACK
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.packt.snake.entities.Apple
import com.packt.snake.entities.Snake
import ktx.app.KtxScreen
import ktx.app.clearScreen
import ktx.graphics.use

class GameScreen : KtxScreen {

    private val batch = SpriteBatch()
    private val snake = Snake()
    private val apple = Apple(snake = snake)


    override fun render(delta: Float) {

        //update snake
        snake.update(delta, apple)

        //update apple
        apple.update(snake)

        clearScreen(BLACK.r, BLACK.g, BLACK.b)

        batch.use {
            snake.render(it)
            apple.render(it)
        }
    }

    override fun dispose() {
        batch.dispose()
    }
}
