# Assignment 6
A program that simulates a farm.
## Current implementation
Type gradle run in order to run main class Start.java
## Early Design pattern Ideas
Early ideas planned for design patterns with this project
### AbstractFactory for animals
Animals will be created using the AbstractFactory design pattern in order to easily
create similar objects such as animals.
### Decorator for farms
In order to make it easy to add upgrades to a farm, I will use the decorator
pattern to make this easy.
### Observer for messaging suppliers
The observer pattern will work well for farmers being able to sent messages to 
observers (suppliers) 

## Design patterns used
The design patterns that ended up being used in project

### Factory for Animals
Animals are made using the factory pattern. There are 3 types of animals that
can be made, pig, cow, and sheep. This pattern worked well becuase it gives
the program much more flexibility if more animal types need to be added.
### Mediator for Farm Manager and cycles
Mediator pattern worked well for informing components in the program of when
a cycle changed
### Decorator for Animal Affinities
Using the decorator pattern really helped to easily and dynamically add 
different features to animals
