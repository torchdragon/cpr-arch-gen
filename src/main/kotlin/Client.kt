import builder.ArchitectureBuilder
import builder.ArchitectureSource
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

fun main() {
    window.onload = {
        document.body?.initialize()
        generateAndDisplay(null)
    }
}

val archBuilder = ArchitectureBuilder()
val difficultyElements: MutableList<HTMLButtonElement> = mutableListOf()

var floorInput : HTMLInputElement? = null
var architectureRoot : HTMLElement? = null

fun HTMLElement.initialize() {
    append {
        div {
            ArchitectureSource.availableDifficulties.forEach { difficulty ->
                button(classes = if (difficulty == archBuilder.currentDifficulty) "difficulty-selected" else "difficulty" ) {
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
                    generateAndDisplay(null)
                }
            }
            floorInput = input {
                type = InputType.number
            }
            button {
                +"Generate"
                onClickFunction = {
                    generateAndDisplay(floorInput?.value?.toIntOrNull())
                }
            }
        }
        architectureRoot = div { }
    }
}

private fun generateAndDisplay(floorCount: Int?) {
    archBuilder.generate(floorCount)
    floorInput?.value = archBuilder.floors.size.toString()
    architectureRoot?.displayArchitecture()
}

private fun updateDifficulties(difficulty: ArchitectureSource.Difficulty) {
    archBuilder.currentDifficulty = difficulty
    difficultyElements.forEach {
        it.removeClass("difficulty")
        it.removeClass("difficulty-selected")
        if (it.textContent == difficulty.label) {
            it.addClass("difficulty-selected")
        }
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
                archBuilder.floors.forEach { floor ->
                    floor.render(this)
                }
            }
        }
    }
}
