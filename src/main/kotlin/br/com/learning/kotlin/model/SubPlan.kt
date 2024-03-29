package br.com.learning.kotlin.model

class SubPlan(
    type: String,
    val monthlyPay: Double,
    val quantityGames: Int,
    val valueDiscount: Double,
    id: Int = 0,
) : Plan(type, id) {
    override fun getValue(rent: Rent): Double {
        val totalGamesInMonth = rent.gamer.gamesInMonth(rent.period.startDate.monthValue).size + 1

        return if (totalGamesInMonth <= quantityGames) {
            0.0
        } else {
            var originValue = super.getValue(rent)

            if (rent.gamer.avg > 8) {
                originValue -= originValue * valueDiscount
            }
            originValue
        }
    }

    override fun toString(): String {
        return "Type=$type, \n" +
                "Id=$id, \n" +
                "MonthlyPay=$monthlyPay, \n" +
                "QuantityGames=$quantityGames, \n" +
                "ValueDiscount=$valueDiscount)"
    }
}