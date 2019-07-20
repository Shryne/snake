import java.awt.Dimension
import java.awt.Graphics
import java.awt.Graphics2D
import java.awt.event.*
import javax.swing.JComponent
import javax.swing.JFrame
import javax.swing.Timer


class Window(private val winSize: Size) {
    constructor(w: Int, h: Int): this(Size(w, h))

    private val frame = JFrame("Snake").apply {
        defaultCloseOperation = JFrame.EXIT_ON_CLOSE

        contentPane = object : JComponent() {
            val content = GameScreen(
                Size({ width }, { height })
            )

            init {
                Timer(0, ActionListener { content.tic() }).apply {
                    isRepeats = true
                    delay = 400
                }.start()
            }

            init {
                addKeyListener(
                    object : KeyAdapter() {
                        override fun keyPressed(e: KeyEvent?) {
                            when (e?.keyCode) {
                                KeyEvent.VK_UP -> content.event(Event.UP)
                                KeyEvent.VK_DOWN -> content.event(Event.DOWN)
                                KeyEvent.VK_LEFT -> content.event(Event.LEFT)
                                KeyEvent.VK_RIGHT -> content.event(Event.RIGHT)
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
        System.setProperty("awt.useSystemAAFontSettings","on")
        System.setProperty("swing.aatext", "true")
        contentPane.isFocusable = true
        contentPane.requestFocusInWindow()

        Timer(0, ActionListener { repaint() }).apply {
            isRepeats = true
            delay = 17
        }.start()

    }

    fun show() {
        frame.isVisible = true
    }
}