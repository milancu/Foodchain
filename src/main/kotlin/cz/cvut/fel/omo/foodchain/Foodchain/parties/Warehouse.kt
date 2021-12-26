package cz.cvut.fel.omo.foodchain.Foodchain.parties

import cz.cvut.fel.omo.foodchain.Foodchain.enums.ProductType
import cz.cvut.fel.omo.foodchain.Foodchain.products.Product

/**
 * Warehouse
 *
 * @constructor Create empty Warehouse
 */
class Warehouse{

    private var storagedProducts : ArrayList<Product> = ArrayList()

    /**
     * Get storaged products
     *
     * @return
     */
    @JvmName("getProducts")
    fun getStoragedProducts() : ArrayList<Product>{
        val toReturn : ArrayList<Product> = storagedProducts
        storagedProducts = ArrayList()
        return toReturn
    }

    /**
     * Get specific products
     *
     * @param wantedType
     * @return
     */
    fun getSpecificProducts(wantedType : ProductType) : ArrayList<Product>{
        val toReturn : ArrayList<Product> = ArrayList()
        for(product in storagedProducts){
            if(product.getProductType() == wantedType){
                toReturn.add(product)
                storagedProducts.remove(product)
            }
        }
        return toReturn
    }

    /**
     * Take in
     *
     * @param products
     */
    fun takeIn(products : ArrayList<Product>) {
        for(product in products){
            takeIn(product)
        }
    }

    /**
     * Take in
     *
     * @param product
     */
    private fun takeIn(product : Product){
        storagedProducts.add(product)
    }

    /**
     * Get stock size
     *
     * @return
     */
    fun getStockSize() : Int{
        return storagedProducts.size
    }

    /**
     * Warehouse management payment
     *
     * @return
     */
    fun warehouseManagementPayment() : Double{
        var costs = 0.0
        for(product in storagedProducts){
            costs += product.getAmount() * 10.0
        }
        return costs
    }
}