package cz.cvut.fel.omo.foodchain.Foodchain.Strategy.ProductStrategy

import cz.cvut.fel.omo.foodchain.Foodchain.Observer.Report
import cz.cvut.fel.omo.foodchain.Foodchain.enums.ProductType
import cz.cvut.fel.omo.foodchain.Foodchain.products.Crop
import cz.cvut.fel.omo.foodchain.Foodchain.products.CropProduct
import cz.cvut.fel.omo.foodchain.Foodchain.products.Product

/**
 * Legumes crop strategy
 *
 * @constructor Create empty Legumes crop strategy
 */
class LegumesCropStrategy : ProcessorCropStrategy {
    override fun execute(crop: Crop): Product {
        val random: Int = (1..2).random()
        when (random) {
            1 -> {
                val product =  CropProduct(
                    "Raw" + crop.getName().toString(),
                    ProductType.LEGUMES,
                    crop.getShopPrice() * 1.2,
                    crop.getShopPrice(),
                    1,
                    "kg",
                    crop.getUUID(),
                    crop.getState().changeToNextState()
                )
                product.attach(Report)
                product.notifyUpdate()
                return product
            }
            else -> {
                val product =  CropProduct(
                    "Can of " + crop.getName().toString(),
                    ProductType.CANS,
                    crop.getShopPrice() * 1.4,
                    crop.getShopPrice() + 5,
                    1,
                    "kg",
                    crop.getUUID(),
                    crop.getState().changeToNextState()
                )
                product.attach(Report)
                product.notifyUpdate()
                return product
            }
        }
    }
}