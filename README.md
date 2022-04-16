# snake
Snake - but a little different. Setter and getter are avoided in this project
by using observers instead.

Example (pseudocode):
```kotlin
// instead of:
if (isPressed(Key.W)) {
    snake.y--
}

// observer:
Snake(
    Pos(
        x = 0,
        y = Scalar { if (isPressed(Key.W)) value++ }
    )
)
```
Instead of setting the values from outside, the `Pos` object is able to 
determine itself. And because `Snake` draws itself, there is no need to
expose its data.

## Additional thoughts
It would be easier to program in this paradigm if every class would be based
on an interface. String for example is a final class and only a few methods
are based on `CharSequence`. Additionally, most methods take `String` instead
of its interface.