package com.packt.snake

import com.badlogic.gdx.Gdx.files
import com.badlogic.gdx.graphics.Color.BLACK
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import ktx.app.KtxScreen
import ktx.app.clearScreen
import ktx.graphics.use

class GameScreen : KtxScreen {

    private val batch = SpriteBatch()
    private val snakeHead = Texture(files.internal("snakehead.png"))
    private val snakeBody = Texture(files.internal("snakebody.png"))

    override fun render(delta: Float) {
        clearScreen(BLACK.r, BLACK.g, BLACK.b)
        batch.use { it.draw(snakeHead, 0f, 0f) }
    }
}