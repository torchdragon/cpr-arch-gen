package builder

import builder.behavior.Dice

class ArchitectureBuilder {

    val floors = mutableListOf<Floor>()

    var currentDifficulty: ArchitectureSource.Difficulty = ArchitectureSource.Difficulty.Basic

    fun generate(floorCount: Int? = null) {
        floors.clear()
        floors.addAll(
            (1 .. (floorCount ?: Dice.roll3d6())).map { level ->
                Floor(
                    level,
                    if (level < 3) {
                        ArchitectureSource.Difficulty.Lobby.get()
                    } else {
                        currentDifficulty.get()
                    }
                )
            }
        )
    }
}
