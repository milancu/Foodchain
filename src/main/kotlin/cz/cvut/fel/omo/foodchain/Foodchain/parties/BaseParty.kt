package cz.cvut.fel.omo.foodchain.Foodchain.parties

import java.util.*

/**
 * Base party
 *
 * @constructor Create empty Base party
 */
open class BaseParty(
    private val subjectName: String,
    private val location: String,
    protected var amountOfMoney: Double
) {

    private val identifier : UUID = UUID.randomUUID()

    /**
     * Get subject name
     *
     * @return
     */
    @JvmName("getName")
    fun getSubjectName() : String{
        return subjectName
    }

    /**
     * Get identifier
     *
     * @return
     */
    @JvmName("getId")
    fun getIdentifier() : UUID{
        return identifier
    }

    /**
     * Get location
     *
     * @return
     */
    @JvmName("getLoc")
    fun getLocation() : String{
        return location
    }

    /**
     * Get amount of money
     *
     * @return
     */
    @JvmName("getMoney")
    fun getAmountOfMoney() : Double{
        return amountOfMoney
    }

    /**
     * Change amount of money
     *
     * @param value
     */
    fun changeAmountOfMoney(value : Double){
        amountOfMoney += value
    }

    /**
     * Take money
     *
     * @param value
     */
    fun takeMoney(value : Double){
        amountOfMoney += value
    }
}