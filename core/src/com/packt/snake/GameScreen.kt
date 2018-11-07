package com.packt.snake

import com.badlogic.gdx.graphics.Color.BLACK
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import ktx.app.KtxScreen
import ktx.app.clearScreen
import ktx.graphics.use

class GameScreen : KtxScreen {
    private val batch = SpriteBatch()
    private val snake = Snake()

    override fun render(delta: Float) {

        //update snake
        snake.update(delta)

        clearScreen(BLACK.r, BLACK.g, BLACK.b)

        batch.use { snake.render(it) }
    }

    override fun dispose() {
        batch.dispose()
    }
}
