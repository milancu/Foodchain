package cz.cvut.fel.omo.foodchain.Foodchain.products

import cz.cvut.fel.omo.foodchain.Foodchain.enums.CropName
import cz.cvut.fel.omo.foodchain.Foodchain.enums.CropType
import java.util.*

class Crop {
    private val name: CropName
    private val type : CropType
    private var amount: Int
    private val uuid : UUID
    private var shopPrice : Double
    private var productionCost : Double
    private val growthTime : Int

    constructor(name: CropName, type : CropType, amount: Int, growthTime: Int) {
        this.name = name
        this.type = type
        this.amount = amount
        this.uuid = UUID.randomUUID()
        this.shopPrice = name.shopPrice
        this.productionCost = shopPrice * 0.01
        this.growthTime = name.growthTime
    }

    fun getName() : CropName{
        return name
    }

    fun getAmount() : Int{
        return amount
    }

    fun getProductionCost() : Double{
        return productionCost
    }

    fun getShopPrice() : Double{
        return shopPrice
    }

    fun getType() : CropType{
        return type
    }

    fun setAmount(value : Int){
        amount = value
    }
}