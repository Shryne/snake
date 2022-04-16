package shryne.gui

import shryne.Event
import shryne.area.Area
import shryne.gui.element.Element
import java.awt.Graphics2D
import java.awt.Image
import java.awt.image.ImageObserver
import java.io.File
import javax.imageio.ImageIO

class Image(path: String, private var area: Area) : Element {
    private val picture by lazy { ImageIO.read(File(path)) }

    override fun draw(g: Graphics2D) {
        area.applyOn { x, y, w, h ->
            g.drawImage(picture, x, y, w, h, nullObserver)
        }
    }

    override fun event(e: Event) {

    }

    override fun tic() {

    }

    private companion object {
        private val nullObserver = ImageObserver { _, _, _, _, _, _ -> true }
    }
}
