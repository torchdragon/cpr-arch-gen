package builder

import kotlinx.html.TBODY
import kotlinx.html.td
import kotlinx.html.tr

data class Floor(
    val level: Int,
    val components: List<Encounterable>
) {
    private val styleClass = if (level % 2 == 0) "floor-alt" else "floor"

    fun render(parent: TBODY) {
        parent.tr {
            td(styleClass) {
                +"$level"
            }
            td(styleClass) {
                if (components.isEmpty()) {
                    +"---"
                } else {
                    components.forEach { component ->
                        component.render(this)
                    }
                }
            }
        }
    }
}
