# text-based-dungeon-game
## A turn-based dungeon game where players fight dungeon dwellers to gain experience, level up, and get stronger

## Idea Notes (for myself)
- Allow small passive healing for the player (maybe the enemies too) after every turn (just like how stamina regeneration)

## Technical Notes (for myself, again)
- Uses inheritance (almost everywhere, actually, which is not great), abstraction, encapsulation, and polymorphism
- Uses the principles above, but still obviously rough around the edges...
- Instead of making a subclass for everything, make a subclass when only the behavior differs, not the data
- Some classes, such as 'Scene', does too many things
- If an action is related to an object, make that object possess the action (should be obvious by now)
- For example, 'Scene' decides and holds the behavior of an entity regenerating stamina, instead of the entities themselves holding that behavior themselves (since entities themselves literally do the regenerating)
- Utilize the 'Composition' principle more for loose coupling and flexibility in class definitions
- Usable kills are currently cheaply defined by making individual classes, making it rigid in data and behavior
- To fix the issue above, use composition (as mentioned earlier), such as the skill having an "effect" data
- This provides more flexibility for making a skill with multiple effects, such as healing AND damaging at the same time
