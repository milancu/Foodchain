package cz.cvut.fel.omo.foodchain.Foodchain

import cz.cvut.fel.omo.foodchain.Foodchain.enums.InvoiceType
import cz.cvut.fel.omo.foodchain.Foodchain.parties.*
import cz.cvut.fel.omo.foodchain.Foodchain.products.Crop
import cz.cvut.fel.omo.foodchain.Foodchain.products.Product

class Request {
    companion object requestService{

        // TODO kam ukladat faktury

        fun requestTransportToProcessor(grower: Grower, processor: Processor, crops : ArrayList<Crop>){
            println("Proces transportace (from farmer " + grower.getIdentifier() + " to processor) " + processor.getIdentifier())
            // Faktury
            var money : Double = 0.0
            for(crop in crops){
                money += crop.getAmount() * crop.getShopPrice()
            }
            var invoice : Invoice = Invoice(processor, grower, money, InvoiceType.CROP)
            println("Vznik faktury " + invoice.getCode())

            grower.transportSupplies()

            processor.takeCropSupplies(Transport.transportCropSuplies()) // todo invoice processorem a transportem
            processor.payForInvoice(invoice)
            println()
        }

        fun requestTransportToMeatFactory(farmer: Farmer, processor: Processor, crops : ArrayList<Crop>){
            println("Proces transportace (from farmer " + farmer.getIdentifier() + " to processor) " + processor.getIdentifier())
            // Faktury
            var money : Double = 0.0
            for(crop in crops){
                money += crop.getAmount() * crop.getShopPrice()
            }
            var invoice : Invoice = Invoice(processor, farmer, money, InvoiceType.CROP)
            println("Vznik faktury " + invoice.getCode())

            //farmer.transportSupplies() // TODO

            processor.takeCropSupplies(Transport.transportCropSuplies()) // todo invoice FAKTORY a transportem
            processor.payForInvoice(invoice) // TODO predelej tu factory
        }

        fun requestTransportToWarehouse(processor : Processor, retailer: Retailer){
            var money : Double = 0.0
            processor.transportProducts()
            for(product in Transport.transportProducts()){
                money += product.getAmount() * product.getShopPrice()
            }
            var invoice : Invoice = Invoice(retailer, processor, money, InvoiceType.PRODUCT)

            processor.transportProducts()
            println("Vznik faktury " + invoice.getCode())

            retailer.buyProducts(Transport.transportProducts()) // todo invoice retailer a transport
            retailer.payForInvoice(invoice)
        }

    }
}