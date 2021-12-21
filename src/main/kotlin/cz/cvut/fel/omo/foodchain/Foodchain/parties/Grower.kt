package cz.cvut.fel.omo.foodchain.Foodchain.parties

import cz.cvut.fel.omo.foodchain.Foodchain.products.Crop
import cz.cvut.fel.omo.foodchain.Foodchain.Field
import cz.cvut.fel.omo.foodchain.Foodchain.Generator


class Grower(subjectName : String, identier : Int, location : String, amountOfMoney : Double)
    : BaseParty(subjectName, identier, location, amountOfMoney) {

    val fields : List<Field> = setInitialField()
    var supplies : List<Crop> = setInitalSupplies()

    fun setInitialField() : List<Field>{
        val generator : Generator = Generator()
        return generator.generateFields()
    }

    fun setInitalSupplies() : List<Crop>{
        val generator : Generator = Generator()
        return generator.generateCrops()
    }

    fun raiseField(){
        for(field in fields){
            if(amountOfMoney >= field.getCrop().getProductionCost()){
                field.setRaised(true)
                amountOfMoney -= field.getCrop().getProductionCost();
            }
            else if(field.isRaised()){
                field.setRaised(false)
            } else {
                field.decreaseProduction()
            }
        }
    }

    fun harvest() : List<Crop>{
        var harvestedCrop = emptyList<Crop>()
        for(field in fields){
            // TODO if splnena rustova podminka
            harvestedCrop.toMutableList().add(field.getCrop())
            field.resetField()
        }
        return harvestedCrop; //TODO proces, prodata doplnit harvest
    }

    fun transportSupplies(){
        Transport.TransportCompany.takeCropSupplies(supplies)
        supplies = emptyList();
    }

    fun sellSupplies() {
        for(supply in supplies){
            this.amountOfMoney += supply.getAmount() * supply.getShopPrice()
        }
    }
}









