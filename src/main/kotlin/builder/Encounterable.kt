package builder

import kotlinx.html.TD
import kotlinx.html.div

sealed class Encounterable(
    private val label: String,
    private val dv: Int,
    private val contents: String? = null
) {
    fun render(parent: TD) {
        parent.div {
            +label
            if (dv > 0) {
                +": $LABEL_DV $dv"
            }
        }
    }

    class Password(simpleDV: Int) : Encounterable(LABEL_PASSWORD, simpleDV)
    class ControlNode(
        simpleDV: Int,
        device: String
    ) : Encounterable(LABEL_CONTROL_NODE, simpleDV, device)
    class File(
        simpleDV: Int,
        contents: String
    ) : Encounterable(LABEL_FILE, simpleDV, contents)
    abstract class Ice(
        name: String
    ) : Encounterable(name, 0)
    object Hellhound : Ice("Hellhound")
    object Sabertooth : Ice("Sabertooth")
    object Raven : Ice("Raven")
    object Wisp : Ice("Wisp")
    object Skunk : Ice("Skunk")
    object Asp : Ice("Asp")
    object Scorpion : Ice("Scorpion")
    object Killer : Ice("Killer")
    object Liche : Ice("Liche")
    object Kraken : Ice("Kraken")
    object Dragon : Ice("Dragon")
    object Giant : Ice("Giant")

    companion object {
        const val LABEL_DV = "DV"
        const val LABEL_PASSWORD = "Password"
        const val LABEL_CONTROL_NODE = "Control Node"
        const val LABEL_FILE = "File"
        const val LABEL_ICE = "Ice"
    }
}
