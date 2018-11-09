package com.packt.snake

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.Color.BLACK
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.graphics.glutils.ShapeRenderer
import com.packt.snake.entities.Apple
import com.packt.snake.entities.Snake
import com.packt.snake.utils.Constants.Companion.SNAKE_MOVEMENT
import com.packt.snake.utils.Constants.Companion.STATE.GAME_OVER
import com.packt.snake.utils.Constants.Companion.STATE.PLAYING
import ktx.app.KtxScreen
import ktx.app.clearScreen
import ktx.graphics.use

class GameScreen : KtxScreen {

    private val batch = SpriteBatch()
    private val renderer = ShapeRenderer()
    private val snake = Snake()
    private val apple = Apple(snake = snake)

    override fun render(delta: Float) {

        clearScreen(BLACK.r, BLACK.g, BLACK.b)

        when (snake.gameState) {
            PLAYING -> {
                //update snake
                snake.update(delta, apple)
                //update apple
                apple.update(snake)
            }

            GAME_OVER -> {
            } //TODO
        }

        //Draw grid
        with(renderer) {
            setAutoShapeType(true)
            begin(ShapeRenderer.ShapeType.Line)
            color = Color.GRAY
            for (i in 0..Gdx.graphics.width step 32) {
                for (j in 0..Gdx.graphics.height step 32) {
                    rect(i.toFloat(), j.toFloat(), SNAKE_MOVEMENT, SNAKE_MOVEMENT)
                }
            }
            end()
        }

        //render snake and the apple
        batch.use {
            snake.render(it)
            apple.render(it)
        }
    }

    override fun dispose() {
        batch.dispose()
    }
}
