package shryne.gui

import shryne.Event
import shryne.gui.element.Element
import java.awt.Graphics2D
import java.awt.Image
import java.awt.image.ImageObserver
import java.io.File
import javax.imageio.ImageIO

class Image(path: String = "images/snake-head.png") : Element {
    private val picture by lazy { ImageIO.read(File(path)) }

    override fun draw(g: Graphics2D) {
        g.drawImage(picture, 0, 0, 100, 100
        ) { img, infoflags, x, y, width, height -> TODO("Not yet implemented") }
    }

    override fun event(e: Event) {

    }

    override fun tic() {

    }

}