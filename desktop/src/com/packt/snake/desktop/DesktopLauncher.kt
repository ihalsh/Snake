package com.packt.snake.desktop

import com.badlogic.gdx.backends.lwjgl.LwjglApplication
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration
import com.packt.snake.SnakeGame

object DesktopLauncher {
    @JvmStatic
    fun main(arg: Array<String>) {
        val config = LwjglApplicationConfiguration()
        config.width = 800//640
        config.height = 600//480
        LwjglApplication(SnakeGame(), config)
    }
}
