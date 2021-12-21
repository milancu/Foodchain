package cz.cvut.fel.omo.foodchain.Foodchain.products

import cz.cvut.fel.omo.foodchain.Foodchain.enums.ProductType
import java.util.*

// TODO bude potreba neco pridavat nevystacime si s Product a Crop a name

open class Product(
    private val name: String,
    private var productType: ProductType,
    private var shopPrice: Double,
    private var productionCost: Double,
    private var amount: Int,
    private val unit: String
) {
    private val uuid : UUID

    init {
        this.uuid = UUID.randomUUID()
        this.productType = ProductType.NOTSET
    }

    fun getProductType() : ProductType{
        return productType
    }

    fun getShopPrice() : Double{
        return shopPrice
    }

    fun getAmount() : Int{
        return amount
    }
}