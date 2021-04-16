import builder.ArchitectureSource
import builder.Floor
import kotlinx.html.div
import kotlinx.html.dom.append
import kotlinx.browser.document
import kotlinx.browser.window
import kotlinx.dom.addClass
import kotlinx.dom.clear
import kotlinx.dom.removeClass
import kotlinx.html.InputType
import kotlinx.html.js.*
import org.w3c.dom.*
import kotlin.random.Random

fun main() {
    window.onload = { document.body?.initialize() }
}

const val DEFAULT_FLOORS = 10
val rollD6 = { Random.nextInt(1, 6) }
val roll3d6 = { rollD6() + rollD6() + rollD6() }

val floors = mutableListOf<Floor>()

var floorInput : HTMLInputElement? = null
var architectureRoot : HTMLElement? = null
var currentDifficulty: ArchitectureSource.Difficulty = ArchitectureSource.Difficulty.Basic
var difficultyElements: MutableList<HTMLButtonElement> = mutableListOf()

fun HTMLElement.initialize() {
    append {
        div {
            ArchitectureSource.availableDifficulties.forEach { difficulty ->
                button(classes = if (difficulty == currentDifficulty) "difficulty-selected" else "difficulty" ) {
                    +difficulty.label
                    onClickFunction = {
                        updateDifficulties(difficulty)
                    }
                }.also {
                    difficultyElements.add(it)
                }
            }
        }
        div {
            button {
                +"Full Generate"
                onClickFunction = {
                    floorInput?.value = roll3d6().toString()
                    generateAndDisplay(floorInput?.value?.toIntOrNull())
                }
            }
            floorInput = input {
                type = InputType.number
                value = DEFAULT_FLOORS.toString()
            }
            button {
                +"Generate"
                onClickFunction = {
                    generateAndDisplay(floorInput?.value?.toIntOrNull())
                }
            }
        }
        architectureRoot = div {

        }
    }
}

private fun generateAndDisplay(floorCount: Int?) {
    floors.clear()
    floors.addAll(generateArchitecture(floorCount ?: DEFAULT_FLOORS))
    architectureRoot?.displayArchitecture()
}

private fun updateDifficulties(difficulty: ArchitectureSource.Difficulty) {
    currentDifficulty = difficulty
    difficultyElements.forEach {
        it.removeClass("difficulty")
        it.removeClass("difficulty-selected")
        if (it.textContent == difficulty.label) {
            it.addClass("difficulty-selected")
        }
    }
}

fun generateArchitecture(floorCount: Int): List<Floor> {
    return (1 .. floorCount).map { level ->
        Floor(
            level,
            if (level < 3) {
                ArchitectureSource.Difficulty.Lobby.get()
            } else {
                currentDifficulty.get()
            }
        )
    }
}

fun HTMLElement.displayArchitecture() {
    clear()
    append {
        table("architecture_container") {
            thead("heading") {
                tr {
                    td {
                        +"Floor"
                    }
                    td {
                        +"Components"
                    }
                }
            }
            tbody {
                floors.forEach { floor ->
                    floor.render(this)
                }
            }
        }
    }
}
