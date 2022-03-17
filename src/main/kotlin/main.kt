const val MASTER = "masterCard"
const val MAESTRO = "maestro"
const val VK = "vk"
const val VISA = "visa"
const val MIR = "mir"
const val commissionMasterMaestro = 0.06
const val commissionVisaMir = 0.075
const val minCommissionVisaMir = 3500
const val maxTransferWithoutCommission = 7500000
const val addCommissionMasterMaestro = 2000

fun main() {
    println(transfer(amount = 8000000))
    println(transfer(MASTER, 5000000, 8000000))
    println(transfer(MAESTRO, 7600000, 8000000))
    println(transfer(accountType = MASTER, amount = 8000000))
    println(transfer(accountType = VISA, amount = 8000000))
    println(transfer(accountType = VISA, amount = 8000000))
    println(transfer(accountType = MIR, amount = 5000))
}

fun transfer(accountType: String = VK, previous: Long = 0, amount: Long): Number {
    val toTransfer = when (accountType) {
        VK -> amount
        MASTER, MAESTRO ->
            if (previous <= maxTransferWithoutCommission) amount
            else amount - (amount * commissionMasterMaestro) - addCommissionMasterMaestro
        VISA, MIR ->
            if (amount * commissionVisaMir < minCommissionVisaMir) amount - minCommissionVisaMir
            else amount - (amount * commissionVisaMir)
        else -> 0
    }
    return toTransfer
}


