package com.packt.snake

import com.badlogic.gdx.Gdx.graphics
import com.badlogic.gdx.Gdx.input
import com.badlogic.gdx.Input.Keys.SPACE
import com.badlogic.gdx.graphics.Camera
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.Color.BLACK
import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.graphics.g2d.BitmapFont
import com.badlogic.gdx.graphics.g2d.GlyphLayout
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.graphics.glutils.ShapeRenderer
import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.utils.Align
import com.badlogic.gdx.utils.viewport.FitViewport
import com.packt.snake.entities.Apple
import com.packt.snake.entities.Snake
import com.packt.snake.utils.Constants.Companion.GAME_OVER_TEXT
import com.packt.snake.utils.Constants.Companion.RIGHT
import com.packt.snake.utils.Constants.Companion.SNAKE_MOVEMENT
import com.packt.snake.utils.Constants.Companion.STATE.GAME_OVER
import com.packt.snake.utils.Constants.Companion.STATE.PLAYING
import com.packt.snake.utils.Constants.Companion.WORLD_HEIGHT
import com.packt.snake.utils.Constants.Companion.WORLD_WIDTH
import ktx.app.KtxScreen
import ktx.app.clearScreen
import ktx.graphics.use

class GameScreen : KtxScreen {

    private val camera: Camera = OrthographicCamera(graphics.width.toFloat(),
            graphics.height.toFloat())
    private val viewport: FitViewport = FitViewport(WORLD_WIDTH, WORLD_HEIGHT, camera)
    private val batch = SpriteBatch()
    private val renderer = ShapeRenderer()
    private val font = BitmapFont()
    private val layout = GlyphLayout()
    private val snake = Snake(viewport = viewport)
    private val apple = Apple(viewport = viewport, snake = snake)

    override fun show() {
        camera.position.set(WORLD_WIDTH / 2, WORLD_HEIGHT / 2, 0f)
        camera.update()
    }

    override fun render(delta: Float) {

        viewport.apply()

        batch.projectionMatrix = camera.projection
        batch.transformMatrix = camera.view

        clearScreen(BLACK.r, BLACK.g, BLACK.b)

        when (snake.gameState) {
            PLAYING -> {
                //update snake
                snake.update(delta, apple)
                //update apple
                apple.update(snake)

                //Draw grid
                drawGrid()

                //render snake and the apple
                batch.use {
                    snake.render(it)
                    apple.render(it)
                    font.draw(it,
                            "Score: ${snake.score}",
                            SNAKE_MOVEMENT / 4,
                            viewport.worldHeight - SNAKE_MOVEMENT / 4,
                            0f,
                            Align.left,
                            false)
                }
            }

            GAME_OVER -> {
                layout.setText(font, GAME_OVER_TEXT)
                batch.use {
                    snake.render(it)
                    apple.render(it)
                    font.draw(it,
                            "Score: ${snake.score}",
                            SNAKE_MOVEMENT / 4,
                            viewport.worldHeight - SNAKE_MOVEMENT / 4,
                            0f,
                            Align.left,
                            false)
                    font.draw(it,
                            GAME_OVER_TEXT,
                            (viewport.worldWidth / 2),
                            viewport.worldHeight / 2 + layout.height,
                            0f,
                            Align.center,
                            false)
                }
                checkForRestart()
            }
        }
    }

    private fun checkForRestart() {
        if (input.isKeyPressed(SPACE)) doRestart()
    }

    private fun doRestart() {

        with(snake) {
            position = Vector2()
            snakeDirection = RIGHT
            bodyParts.clear()
            gameState = PLAYING
            score = 0
        }
        apple.appleAvailable = false
    }

    private fun drawGrid() {
        with(renderer) {
            projectionMatrix = camera.projection
            transformMatrix = camera.view
            setAutoShapeType(true)
            begin(ShapeRenderer.ShapeType.Line)
            color = Color.GRAY
            for (i in 0..viewport.worldWidth.toInt() step 32) {
                for (j in 0..viewport.worldHeight.toInt() step 32) {
                    rect(i.toFloat(), j.toFloat(), SNAKE_MOVEMENT, SNAKE_MOVEMENT)
                }
            }
            end()
        }
    }

    override fun resize(width: Int, height: Int) {
        viewport.update(width, height, true)
    }

    override fun dispose() {
        batch.dispose()
    }
}
