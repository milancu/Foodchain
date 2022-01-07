package cz.cvut.fel.omo.foodchain.Foodchain.parties

import cz.cvut.fel.omo.foodchain.Foodchain.statics.Config
import cz.cvut.fel.omo.foodchain.Foodchain.Invoice
import cz.cvut.fel.omo.foodchain.Foodchain.strategies.customer_strategy.*
import cz.cvut.fel.omo.foodchain.Foodchain.products.Product
import org.slf4j.LoggerFactory

/**
 * Customer
 *
 * @constructor
 *
 * @param subjectName
 * @param location
 * @param amountOfMoney
 */
class Customer(subjectName: String, location: String, amountOfMoney: Double) :
    BaseParty(subjectName, location, amountOfMoney) {

    private val basicStrategy : BasicStrategy = BasicStrategy()
    private val easterEggStrategy : EasterEggStrategy = EasterEggStrategy()
    private val meatLoverStrategy : MeatLoverStrategy = MeatLoverStrategy()
    private val randomStrategy : RandomStrategy = RandomStrategy()
    private val veganStrategy : VeganStrategy = VeganStrategy()

    private val logger = LoggerFactory.getLogger(javaClass)

    private var salary: Double = (Config.SALARY_MIN..Config.SALARY_MAX).random().toDouble()
    private var context : CustomerContext = chooseStrategy()
    private var creditCardDebts : ArrayList<Invoice> = ArrayList()

    private fun chooseStrategy() : CustomerContext{
        val context : CustomerContext
        when((1..5).random()){
            1 -> {
                context = CustomerContext(basicStrategy)
                return context
            }
            2 -> {
                context = CustomerContext(easterEggStrategy)
                return context
            }
            3 -> {
                context = CustomerContext(meatLoverStrategy)
                return context
            }
            4 -> {
                context = CustomerContext(randomStrategy)
                return context
            }
            else -> {
                context = CustomerContext(veganStrategy)
                return context
            }
        }
    }

    /**
     * Receive salary
     *
     */
    fun receiveSalary() {
        this.amountOfMoney += salary
    }

    /**
     * Buy products
     *
     * @param products
     * @return
     */// Produkty nebudeme ukladat, budou vyrazeny z obehu, stejne by se nezpracovavaly, nebo resetovaly
    fun buyProducts(products: ArrayList<Product>): Pair<Double, ArrayList<Product>> {
        return context.goShopping(products)
    }

    /**
     * Pay for shopping
     *
     * @param recipe
     */
    fun payForShopping(recipe : Invoice){
        if(recipe.getPrice() <= this.amountOfMoney){
            amountOfMoney -= recipe.getPrice()
            recipe.payInvoice()
            recipe.getContractor().takeMoney(recipe.getPrice())
            recipe.notifyPaid()
        } else {
            creditCardDebts.add(recipe)
            logger.info("Vznika dluh na kreditni karte v castce " + recipe.getPrice())
            recipe.notifyUnpaid()
        }
    }

    /**
     * Pay debts
     *
     */
    fun payDebts(){
        val toRemove : ArrayList<Invoice> = ArrayList()
        for(invoice in creditCardDebts){
            if(amountOfMoney >= invoice.getPrice()){
                toRemove.add(invoice)
                payForShopping(invoice)
            }
        }
        for(invoice in toRemove){
            logger.info("Penize za " + invoice.getCode() + " splaceny")
            creditCardDebts.remove(invoice)
        }
    }


}


