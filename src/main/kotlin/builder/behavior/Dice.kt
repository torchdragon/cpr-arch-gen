package builder.behavior

import kotlin.random.Random

class Dice {
    companion object {
        val rollD6 = { Random.nextInt(1, 6) }
        val roll3d6 = { rollD6() + rollD6() + rollD6() }
    }
}
