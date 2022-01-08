package cz.cvut.fel.omo.foodchain.Foodchain.parties

import cz.cvut.fel.omo.foodchain.Foodchain.products.Crop
import cz.cvut.fel.omo.foodchain.Foodchain.products.Meat
import cz.cvut.fel.omo.foodchain.Foodchain.products.MeatProduct
import cz.cvut.fel.omo.foodchain.Foodchain.products.Product
import org.slf4j.LoggerFactory
import java.util.*
import kotlin.collections.ArrayList

/**
 * Transport
 *
 * @constructor Create empty Transport
 */
class Transport{
    companion object TransportCompany {

        private var amountOfMoney : Double = 10000.00
        var transport : BaseParty = BaseParty("Transport S.R.O", "Unknown", amountOfMoney)

        private val logger = LoggerFactory.getLogger(javaClass)

        private var cropSupplies : ArrayList<Crop> = ArrayList()
        private var products : ArrayList<Product> = ArrayList()
        private var meats : ArrayList<MeatProduct> = ArrayList()

        fun transportCropSuplies() : ArrayList<Crop>{
            val toTransport : ArrayList<Crop> = cropSupplies
            for(supply in cropSupplies){
                transport.changeAmountOfMoney((supply.getShopPrice() * supply.getAmount() * 0.1))
            }
            this.cropSupplies = ArrayList()
            return toTransport
        }

        fun transportProducts() : ArrayList<Product>{
            val toTransport : ArrayList<Product> = products
            for(product in products){
                transport.changeAmountOfMoney((product.getShopPrice() * product.getAmount() * 0.1))
            }
            this.products = ArrayList()
            return toTransport
        }

        fun transportMeats() : ArrayList<MeatProduct>{
            val toTransport : ArrayList<MeatProduct> = meats
            for(meat in meats){
                transport.changeAmountOfMoney((meat.getShopPrice() * meat.getAmount() * 0.1))
            }
            this.meats = ArrayList()
            return toTransport
        }

        fun takeCropSupplies(supplies : ArrayList<Crop>){
            if(supplies.size == 0) return
            for(supply in supplies){
                this.cropSupplies.add(supply)
                supply.notifyTransport();
            }
        }

        fun takeProducts(products : ArrayList<Product>){
            for(product in products){
                this.products.add(product)
                product.notifyTransport();
            }
        }

        fun takeMeat(meats : ArrayList<MeatProduct>){
            logger.info("transport prebira masa : " + meats.size)
            for(meat in meats){
                this.meats.add(meat)
                meat.notifyTransport();
            }
            logger.info("Transport prevzal masa : " + this.meats.size)
        }


        fun cargoDeduction(){
            var costs = 0.0
            for(meat in meats){
                costs += meat.getShopPrice() * meat.getAmount() * 0.025
            }
            for(crop in cropSupplies){
                costs += crop.getShopPrice() * crop.getAmount() * 0.025
            }
            for(product in products){
                costs += product.getShopPrice() * product.getAmount() * 0.025
            }
            transport.changeAmountOfMoney(-costs)
            logger.info("Dopravni spolecnost na nakladech utratila: $costs")
        }
    }
}