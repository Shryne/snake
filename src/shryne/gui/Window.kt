package shryne.gui

import shryne.Event
import shryne.gui.element.GameScreen
import shryne.area.Size
import shryne.gui.element.Snake
import java.awt.Dimension
import java.awt.Graphics
import java.awt.Graphics2D
import java.awt.event.*
import javax.swing.JComponent
import javax.swing.JFrame
import javax.swing.Timer
import kotlin.math.max

/**
 * The window containing the game. The window will be positioned at the center.
 *
 * @param winSize The size of the window.
 */
class Window(private val winSize: Size) {
    /**
     * @param w The width of the window.
     * @param h The height of the window.
     */
    constructor(w: Int, h: Int) : this(Size(w, h))

    private val frame by lazy {
        JFrame("Snake").apply {
            defaultCloseOperation = JFrame.EXIT_ON_CLOSE

            contentPane = object : JComponent() {
                val content = GameScreen(width, height)
                init {
                    val timer = Timer(0) {
                        content.tic()
                    }.apply {
                        isRepeats = true
                        addActionListener {
                            delay = max(
                                MIN_DELAY,
                                MAX_DELAY - (
                                    content.snakeLength - Snake.START_LENGTH
                                    ) * 10
                            )
                        }
                    }
                    timer.start()
                    addKeyListener(
                        object : KeyAdapter() {
                            override fun keyPressed(e: KeyEvent) {
                                when (e.keyCode) {
                                    KeyEvent.VK_UP, KeyEvent.VK_W
                                    -> content.event(Event.UP)
                                    KeyEvent.VK_DOWN, KeyEvent.VK_S
                                    -> content.event(Event.DOWN)
                                    KeyEvent.VK_LEFT, KeyEvent.VK_A
                                    -> content.event(Event.LEFT)
                                    KeyEvent.VK_RIGHT, KeyEvent.VK_D
                                    -> content.event(Event.RIGHT)
                                }
                            }
                        }
                    )

                }

                override fun paintComponent(g: Graphics) {
                    super.paintComponent(g)
                    content.draw(g as Graphics2D)
                }
            }
            contentPane.preferredSize = Dimension(
                winSize.w.toInt(), winSize.h.toInt()
            )
            pack()
            setLocationRelativeTo(null)
            System.setProperty("awt.useSystemAAFontSettings", "on")
            System.setProperty("swing.aatext", "true")
            contentPane.isFocusable = true
            contentPane.requestFocusInWindow()

            // because swing doesn't update the screen by itself:
            Timer(0) {
                repaint()
            }.apply {
                isRepeats = true
                delay = 17
            }.start()
        }
    }

    fun show() {
        frame.isVisible = true
    }

    companion object {
        private const val MIN_DELAY = 80
        private const val MAX_DELAY = 400
    }
}
