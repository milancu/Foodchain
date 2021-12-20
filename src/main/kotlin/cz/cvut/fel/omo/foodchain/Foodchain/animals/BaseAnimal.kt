package cz.cvut.fel.omo.foodchain.Foodchain.animals

import cz.cvut.fel.omo.foodchain.Foodchain.enums.AnimalType
import cz.cvut.fel.omo.foodchain.Foodchain.enums.CropType
import cz.cvut.fel.omo.foodchain.Foodchain.parties.Farmer
import cz.cvut.fel.omo.foodchain.Foodchain.products.Crop

open class BaseAnimal {
    private var weight: Double // TODO val podle toho, jestli budeme pracovat s vyvojem zvirete
    private val feed: CropType
    private var foodConsumption: Int
    private var age: Int
    private var price: Double

    constructor(animalType: AnimalType) {
        this.weight = animalType.weight
        this.feed = CropType.CEREAL
        this.foodConsumption = animalType.foodConsumption
        this.age = animalType.age
        this.price = animalType.price

    }

    // funkce eat
    fun eatFeed(owner: Farmer, feed: Crop) {
        for (resource in owner.resources) {
            if (resource.getName() == feed.getName() && resource.getAmount() >= feed.getAmount()) {
                owner.decreaseResource(feed)
            }
        }
        println("Dej jim nazrat vole, nemas zasoby hajzle")
    }
}