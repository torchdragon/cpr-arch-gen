package builder

import roll3d6
import rollD6

object ArchitectureSource {

    private val DEFAULT = listOf(Encounterable.File(6,""))

    private const val DIFFICULTY_LOBBY = "LOBBY"
    private const val DIFFICULTY_BASIC = "BASIC"
    private const val DIFFICULTY_STANDARD = "STANDARD"
    private const val DIFFICULTY_UNCOMMON = "UNCOMMON"
    private const val DIFFICULTY_ADVANCED = "ADVANCED"

    val availableDifficulties = listOf(Difficulty.Basic, Difficulty.Standard, Difficulty.Uncommon, Difficulty.Advanced)

    sealed class Difficulty(
        val label: String,
        private val source: Map<Int, List<Encounterable>>,
        private val resolver: () -> Int
    ) {
        object Lobby : Difficulty(
            DIFFICULTY_LOBBY,
            mapOf(
                1 to listOf(Encounterable.File(6, "")),
                2 to listOf(Encounterable.Password(6)),
                3 to listOf(Encounterable.Password(8)),
                4 to listOf(Encounterable.Skunk),
                5 to listOf(Encounterable.Wisp),
                6 to listOf(Encounterable.Killer)
            ),
            rollD6)

        object Basic : Difficulty(
            DIFFICULTY_BASIC,
            mapOf(
                3 to listOf(Encounterable.Hellhound),
                4 to listOf(Encounterable.Sabertooth),
                5 to listOf(Encounterable.Raven, Encounterable.Raven),
                6 to listOf(Encounterable.Hellhound),
                7 to listOf(Encounterable.Wisp),
                8 to listOf(Encounterable.Raven),
                9 to listOf(Encounterable.Password(6)),
                10 to listOf(Encounterable.File(6, "")),
                11 to listOf(Encounterable.ControlNode(6, "")),
                12 to listOf(Encounterable.Password(6)),
                13 to listOf(Encounterable.Skunk),
                14 to listOf(Encounterable.Asp),
                15 to listOf(Encounterable.Scorpion),
                16 to listOf(Encounterable.Killer, Encounterable.Skunk),
                17 to listOf(Encounterable.Wisp, Encounterable.Wisp, Encounterable.Wisp),
                18 to listOf(Encounterable.Liche)
            ),
            roll3d6)

        object Standard : Difficulty(
            DIFFICULTY_STANDARD,
            mapOf(
                3 to listOf(Encounterable.Hellhound, Encounterable.Hellhound),
                4 to listOf(Encounterable.Hellhound, Encounterable.Killer),
                5 to listOf(Encounterable.Skunk, Encounterable.Skunk),
                6 to listOf(Encounterable.Sabertooth),
                7 to listOf(Encounterable.Scorpion),
                8 to listOf(Encounterable.Hellhound),
                9 to listOf(Encounterable.Password(8)),
                10 to listOf(Encounterable.File(8, "")),
                11 to listOf(Encounterable.ControlNode(8, "")),
                12 to listOf(Encounterable.Password(8)),
                13 to listOf(Encounterable.Asp),
                14 to listOf(Encounterable.Killer),
                15 to listOf(Encounterable.Liche),
                16 to listOf(Encounterable.Asp),
                17 to listOf(Encounterable.Raven, Encounterable.Raven, Encounterable.Raven),
                18 to listOf(Encounterable.Liche, Encounterable.Raven)
            ), roll3d6)

        object Uncommon : Difficulty(
            DIFFICULTY_UNCOMMON,
            mapOf(
                3 to listOf(Encounterable.Kraken),
                4 to listOf(Encounterable.Hellhound, Encounterable.Scorpion),
                5 to listOf(Encounterable.Hellhound, Encounterable.Killer),
                6 to listOf(Encounterable.Raven, Encounterable.Raven),
                7 to listOf(Encounterable.Sabertooth),
                8 to listOf(Encounterable.Hellhound),
                9 to listOf(Encounterable.Password(10)),
                10 to listOf(Encounterable.File(10, "")),
                11 to listOf(Encounterable.ControlNode(10, "")),
                12 to listOf(Encounterable.Password(10)),
                13 to listOf(Encounterable.Killer),
                14 to listOf(Encounterable.Liche),
                15 to listOf(Encounterable.Dragon),
                16 to listOf(Encounterable.Asp, Encounterable.Raven),
                17 to listOf(Encounterable.Dragon, Encounterable.Wisp),
                18 to listOf(Encounterable.Giant)
            ), roll3d6)

        object Advanced : Difficulty(
            DIFFICULTY_ADVANCED,
            mapOf(
                3 to listOf(Encounterable.Hellhound, Encounterable.Hellhound, Encounterable.Hellhound),
                4 to listOf(Encounterable.Asp, Encounterable.Asp),
                5 to listOf(Encounterable.Hellhound, Encounterable.Liche),
                6 to listOf(Encounterable.Wisp, Encounterable.Wisp, Encounterable.Wisp),
                7 to listOf(Encounterable.Hellhound, Encounterable.Sabertooth),
                8 to listOf(Encounterable.Kraken),
                9 to listOf(Encounterable.Password(12)),
                10 to listOf(Encounterable.File(12, "")),
                11 to listOf(Encounterable.ControlNode(12, "")),
                12 to listOf(Encounterable.Password(12)),
                13 to listOf(Encounterable.Giant),
                14 to listOf(Encounterable.Dragon),
                15 to listOf(Encounterable.Killer, Encounterable.Scorpion),
                16 to listOf(Encounterable.Kraken),
                17 to listOf(Encounterable.Raven, Encounterable.Wisp, Encounterable.Hellhound),
                18 to listOf(Encounterable.Dragon, Encounterable.Dragon)
            ), roll3d6)

        fun get(): List<Encounterable> {
            return source.getOrElse(resolver(), { DEFAULT })
        }
    }
}
