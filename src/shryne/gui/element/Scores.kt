package shryne.gui.element

import shryne.Event
import shryne.area.Pos
import shryne.area.Size
import java.awt.Font
import java.awt.Graphics2D
import kotlin.math.max

/**
 * Represents the score and the high score.
 */
class Scores(
    private val windowSize: Size,
    private val pos: Pos = Pos(),
    private val size: Int = 20
) : Element {

    private var fontInitialized = false
    private var ascent: Int = 0
    private var descent: Int = 0

    val height: Int get() = ascent + descent + 1

    private var highScore = 0
    private var text = "HighScore: 0, Score: 0"
    var score = 0
        set(value) {
            field = value
            highScore = max(highScore, value)
            text = "HighScore: $highScore, Score: $value"
        }

    override fun draw(g: Graphics2D) {
        if (!fontInitialized) {
            g.font = Font("Calibri", Font.PLAIN, size)
            ascent = g.fontMetrics.getLineMetrics(text, g).ascent.toInt()
            descent = g.fontMetrics.getLineMetrics(text, g).descent.toInt()
        }
        g.drawString(text, pos.x.toInt(), pos.y.toInt() + ascent)
    }

    override fun event(e: Event) = Unit
    override fun tic() = Unit
}