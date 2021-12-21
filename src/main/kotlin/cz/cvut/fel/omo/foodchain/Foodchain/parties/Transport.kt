package cz.cvut.fel.omo.foodchain.Foodchain.parties

import cz.cvut.fel.omo.foodchain.Foodchain.products.Crop
import cz.cvut.fel.omo.foodchain.Foodchain.products.Product

class Transport{
    companion object TransportCompany {
        var name : String = "Transport S.R.O"
        var identifier : String = "12345678"
        var amountOfMoney : Double = 10000.00
        var cropSupplies : ArrayList<Crop> = ArrayList()
        var products : ArrayList<Product> = ArrayList()

        fun transportCropSuplies() : ArrayList<Crop>{
            var toTransport : ArrayList<Crop> = cropSupplies
            for(supply in cropSupplies){
                amountOfMoney += supply.getShopPrice() * supply.getAmount() * 0.1 // TODO od koho si je vezme
            }
            cropSupplies = ArrayList()
            return toTransport
        }

        fun transportProducts() : ArrayList<Product>{
            var toTransport : ArrayList<Product> = products
            for(product in products){
                amountOfMoney += product.getShopPrice() * product.getAmount() * 0.1 //TODO invoice smerem od transportu
            }
            products = ArrayList()
            return toTransport
        }

        fun takeCropSupplies(supplies : ArrayList<Crop>){
            for(supply in supplies){
                cropSupplies.add(supply)
            }
        }

        fun takeProducts(products : ArrayList<Product>){
            for(product in products){
                products.add(product)
            }
        }

    }
        // Tansprot price 0.1 plodiny, pocita se z ostatnich
}