package com.packt.snake

import com.badlogic.gdx.Screen
import ktx.app.KtxGame

class SnakeGame : KtxGame<Screen>() {

    override fun create() {
        // Registering GameScreen in the game object
        // it will be accessible through GameScreen class:
        addScreen(GameScreen())

        // Changing current screen to the registered instance of the
        // GameScreen class:
        setScreen<GameScreen>()
    }
}
