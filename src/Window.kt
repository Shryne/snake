import java.awt.Dimension
import java.awt.Graphics
import java.awt.Graphics2D
import java.awt.event.*
import javax.swing.JComponent
import javax.swing.JFrame
import javax.swing.Timer
import kotlin.math.max

class Window(private val winSize: Size) {
    constructor(w: Int, h: Int): this(Size(w, h))

    companion object {
        private const val MIN_DELAY = 80
        private const val MAX_DELAY = 400
    }

    private val frame = lazy {
        JFrame("Snake").apply {
            defaultCloseOperation = JFrame.EXIT_ON_CLOSE

            contentPane = object : JComponent() {
                val content = GameScreen(
                    Size({ width }, { height })
                )

                init {
                    val timer = Timer(0, ActionListener { content.tic() }).apply {
                        isRepeats = true
                    }
                    timer.addActionListener {
                        timer.delay = max(
                            MIN_DELAY,
                            MAX_DELAY - (
                                content.snakeLength - Snake.START_LENGTH
                                ) * 10
                        )
                    }
                    timer.start()
                    addKeyListener(
                        object : KeyAdapter() {
                            override fun keyPressed(e: KeyEvent?) {
                                when (e?.keyCode) {
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

            Timer(0, ActionListener { repaint() }).apply {
                isRepeats = true
                delay = 17
            }.start()
        }
    }

    fun show() {
        frame.value.isVisible = true
    }

}