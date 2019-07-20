import java.awt.Font
import java.awt.Graphics2D

class Labels(private val windowSize: Size, val pos: Pos = Pos(), private val size: Int = 20) : Element {
    private var fontInitialized = false
    private var ascent: Int = 0
    private var descent: Int = 0
    val height: Int
        get() = ascent + descent + 1

    override fun draw(g: Graphics2D) {
        val text = "HighScore: 0, Score: 0"
        if (!fontInitialized) {
            g.font = Font("Calibri", Font.PLAIN, size)
            ascent = g.fontMetrics.getLineMetrics(text, g).ascent.toInt()
            descent = g.fontMetrics.getLineMetrics(text, g).descent.toInt()
        }

        g.drawString(text, pos.x.toInt(), pos.y.toInt() + ascent)
    }

}