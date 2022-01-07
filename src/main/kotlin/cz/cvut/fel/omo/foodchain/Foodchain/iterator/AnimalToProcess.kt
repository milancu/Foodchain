package cz.cvut.fel.omo.foodchain.Foodchain.iterator

import cz.cvut.fel.omo.foodchain.Foodchain.animals.BaseAnimal

class AnimalToProcess : CustomIterator {

    private var animalList : ArrayList<BaseAnimal>;

    constructor() {
        this.animalList = ArrayList<BaseAnimal>()
    }

    override fun hasNext(): Boolean {
        return !animalList.isEmpty()
    }

    override fun next(): BaseAnimal {
        val animal = animalList[0]
        animalList.removeAt(0)
        return animal;
    }

    fun add(animal: BaseAnimal) {
        animalList.add(animal)
    }

    fun clearList() {
        animalList = ArrayList<BaseAnimal>();
    }

    @JvmName("getAnimalList1")
    fun getAnimalList(): ArrayList<BaseAnimal> {
        return animalList
    }

    fun getSize(): Int {
        return animalList.size
    }
}