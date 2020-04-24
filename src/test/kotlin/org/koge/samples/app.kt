package org.koge.samples

import org.koge.engine.Game
import org.koge.engine.event.key.Key
import org.koge.engine.event.key.KeyDownEvent
import org.koge.engine.event.mouse.*
import org.koge.engine.graphics.Graphics
import org.koge.game.sprite.Sprite
import kotlin.math.sin

class MyGame(width: Int, height: Int, title: String) : Game(width, height, title) {

    private var sprite = Sprite("/textures/boss.gif")
    var xMouse=0.0
    var yMouse=0.0
    override fun init() {
        sprite.init()
        font.init(java.awt.Font(java.awt.Font.MONOSPACED, java.awt.Font.PLAIN, 16), true)
        debugMode=true
    }

    override fun run(fps: Int) {
        sprite.moveX(0.5f)
        sprite.moveY(sin(sprite.position.x) *10)
    }

    override fun render(g: Graphics) {
        g.draw(sprite)
        g.drawText("Dummy text",10f,52f)
    }

    override fun destroy() {
        sprite.destroy()
    }

    override fun keyDown(e: KeyDownEvent) {
        when(e.key){
            Key.KEY_UP -> sprite.moveY(-5f)
            Key.KEY_DOWN -> sprite.moveY(5f)
            Key.KEY_LEFT -> sprite.moveX(-5f)
            Key.KEY_RIGHT -> sprite.moveX(5f)
        }
    }

    override fun mouseButtonPressed(e: MousePressedEvent) {
        println(e.button)
    }

    override fun mouseButtonReleased(e: MouseReleasedEvent) {
        println(e.button)
    }

    override fun mouseCursorEnter(e: MouseCursorEnterEvent) {
        println(e.entered)
    }

    override fun mouseMoved(e: MouseMovedEvent) {
        xMouse = e.xpos
        yMouse = e.ypos
    }

    override fun mouseScrolled(e: MouseScrollEvent) {
        println("${e.xoffset} ${e.yoffset}")
    }
}

fun main(){
    MyGame(800, 600, "Koge Game").apply {
        start()
    }
}