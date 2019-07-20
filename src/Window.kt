import java.awt.Dimension
import java.awt.Graphics
import java.awt.Graphics2D
import java.awt.event.KeyEvent
import java.awt.event.KeyListener
import javax.swing.JComponent
import javax.swing.JFrame
import java.awt.event.ActionEvent
import java.awt.event.ActionListener
import javax.swing.Timer


class Window(private val winSize: Size) {
    constructor(w: Int, h: Int): this(Size(w, h))

    private val frame = JFrame("Snake").apply {
        defaultCloseOperation = JFrame.EXIT_ON_CLOSE
        val direction = Direction()
        val content = GameScreen(
            Size({ width }, { height }),
            direction
        )
        contentPane = object : JComponent() {
            override fun paintComponent(g: Graphics) {
                super.paintComponent(g)
                content.draw(g as Graphics2D)
            }
        }
        contentPane.addKeyListener(
            object : KeyListener {
                override fun keyTyped(e: KeyEvent?) {}

                override fun keyPressed(e: KeyEvent?) {
                    when (e?.keyCode) {
                        KeyEvent.VK_UP -> direction.apply {
                            x = 0
                            y = -1
                            println("Hey")
                        }
                        KeyEvent.VK_DOWN -> direction.apply {
                            x = 0
                            y = 1
                        }
                        KeyEvent.VK_LEFT -> direction.apply {
                            x = -1
                            y = 0
                        }
                        KeyEvent.VK_RIGHT -> direction.apply {
                            x = 1
                            y = 0
                        }
                    }
                }

                override fun keyReleased(e: KeyEvent?) {

                }
            }
        )
        contentPane.preferredSize = Dimension(winSize.w.toInt(), winSize.h.toInt())
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