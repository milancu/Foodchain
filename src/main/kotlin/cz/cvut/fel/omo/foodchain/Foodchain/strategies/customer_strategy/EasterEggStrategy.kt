package cz.cvut.fel.omo.foodchain.Foodchain.strategies.customer_strategy

import cz.cvut.fel.omo.foodchain.Foodchain.enums.ProductType
import cz.cvut.fel.omo.foodchain.Foodchain.products.Product
import org.slf4j.LoggerFactory

/**
 * Easter egg strategy
 *
 * @constructor Create empty Easter egg strategy
 */
class EasterEggStrategy : CustomerStrategy {

    private val logger = LoggerFactory.getLogger(javaClass)

    override fun execute(products: ArrayList<Product>): Pair<Double, ArrayList<Product>> {
        var spended = 0.0
        val toRemove: ArrayList<Product> = ArrayList()

        for (product in products) {
            val random: Int = (0..1).random()
            val randomSize: Int = (5..15).random()
            if (random == 1 || product.getProductType() == ProductType.XXX) {
                if (product.getAmount() >= randomSize) {
                    product.decreaseAmount(randomSize)
                    product.notifyPurchased(product.getAmount())
                    spended += randomSize * product.getShopPrice()
                } else {
                    toRemove.add(product)
                    logger.info("" + product.getProductType() + " " + product.getName() + " byl vyprodan")
                    product.notifySoldOut()
                    product.getState().changeToNextState()
                }
            }
        }
        val productsToReturn = removeProducts(products, toRemove)
        return Pair(spended, productsToReturn)
        /*return spended*/
    }

    /**
     * Remove products
     *
     * @param original
     * @param toRemove
     */
    private fun removeProducts(original : ArrayList<Product>, toRemove : ArrayList<Product>) : ArrayList<Product>{
        for(product in toRemove){
            original.remove(product)
        }
        return original
    }
}